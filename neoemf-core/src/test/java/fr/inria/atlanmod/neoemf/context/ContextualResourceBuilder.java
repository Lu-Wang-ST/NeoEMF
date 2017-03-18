/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A builder that manages the assembly and the construction of {@link PersistentResource}.
 */
final class ContextualResourceBuilder {

    /**
     * The {@link Context} of this helper.
     */
    private final Context context;

    /**
     * The {@link ResourceSet} used to create the resource.
     */
    private ResourceSet resourceSet;

    /**
     * The {@link URI} of the resource.
     */
    private URI uri;

    /**
     * Whether the {@link PersistentResource} is persistent.
     */
    private boolean isPersistent;

    /**
     * Constructs a new {@code ContextualResourceBuilder} with the given {@code ePackage}.
     *
     * @param context  the context of this builder
     * @param ePackage the {@link EPackage} associated to the built {@link PersistentResource}
     *
     * @see EPackage.Registry
     */
    public ContextualResourceBuilder(Context context, @Nullable EPackage ePackage) {
        this.context = checkNotNull(context);

        BackendFactoryRegistry.unregisterAll();
        BackendFactoryRegistry.register(context.uriScheme(), context.factory());

        initBuilder();

        Optional.ofNullable(ePackage)
                .ifPresent(p -> EPackage.Registry.INSTANCE.put(p.getNsURI(), p));
    }

    /**
     * Initializes this builder.
     */
    private void initBuilder() {
        isPersistent = false;
        uri = null;

        resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry()
                .getProtocolToFactoryMap()
                .put(context.uriScheme(), PersistentResourceFactory.getInstance());
    }

    /**
     * Returns all options of this helper.
     *
     * @return a map of options
     */
    private Map<String, Object> allOptions() {
        return context.optionsBuilder()
                .log()
                .autoSave(100)
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .asMap();
    }

    /**
     * Defines the {@link URI} of the created resource from another.
     *
     * @param uri the base {@link URI}
     *
     * @return this builder (for chaining)
     */
    public ContextualResourceBuilder uri(URI uri) {
        this.uri = context.createUri(uri);
        return this;
    }

    /**
     * Defines the {@link URI} of the created resource from a file.
     *
     * @param file the {@link File} associated to the created resource
     *
     * @return this builder (for chaining)
     */
    public ContextualResourceBuilder file(File file) {
        this.uri = context.createUri(file);
        return this;
    }

    /**
     * Defines the created resource as persistent.
     *
     * @return this builder (for chaining)
     */
    public final ContextualResourceBuilder persistent() {
        isPersistent = true;
        return this;
    }

    /**
     * Creates a {@link PersistentResource} according to the specified options.
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     */
    public PersistentResource createResource() throws IOException {
        PersistentResource resource = (PersistentResource) resourceSet.createResource(uri);
        if (isPersistent) {
            resource.save(allOptions());
        }

        initBuilder();

        return resource;
    }

    /**
     * Loads an existing {@link PersistentResource} according to the specified options.
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     */
    public PersistentResource loadResource() throws IOException {
        PersistentResource resource = (PersistentResource) resourceSet.createResource(uri);
        resource.load(allOptions());

        initBuilder();

        return resource;
    }

    /**
     * Creates a new {@link DataMapper} according to the specified options.
     *
     * @return a new {@link DataMapper}
     */
    public DataMapper createPersistentMapper() {
        //noinspection ConstantConditions
        return context.factory().createStore(context.factory().createPersistentBackend(uri, allOptions()), null, allOptions());
    }
}