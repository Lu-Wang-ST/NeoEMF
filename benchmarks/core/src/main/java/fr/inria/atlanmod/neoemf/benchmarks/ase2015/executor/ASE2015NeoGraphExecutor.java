/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.ase2015.executor;

import fr.inria.atlanmod.neoemf.benchmarks.ase2015.ASE2015QueryExecutor;
import fr.inria.atlanmod.neoemf.benchmarks.ase2015.query.ASE2015QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.executor.NeoGraphExecutor;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions.StoreOption;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASE2015NeoGraphExecutor extends NeoGraphExecutor implements ASE2015QueryExecutor {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void queryASE2015GetBranchStatements(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            // Add the LoadedObjectCounter store
            List<StoreOption> storeOptions = new ArrayList<>();
//			storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            loadOpts.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
            resource.load(loadOpts);

            ASE2015QueryFactory.queryAse2015GetCommentsTagContent(resource).callWithMemoryAndTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015Grabats09(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            // Add the LoadedObjectCounter store
            List<StoreOption> storeOptions = new ArrayList<>();
//			storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            loadOpts.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
            resource.load(loadOpts);

            ASE2015QueryFactory.queryAse2015Grabats09(resource).callWithMemoryAndTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015InvisibleMethodDeclarations(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            // Add the LoadedObjectCounter store
            List<StoreOption> storeOptions = new ArrayList<>();
//			storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            loadOpts.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
            System.out.println(loadOpts);
            resource.load(loadOpts);

            ASE2015QueryFactory.queryInvisibleMethodDeclarations(resource).callWithMemoryAndTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015SpecificInvisibleMethodDeclarations(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            // Add the LoadedObjectCounter store
            List<StoreOption> storeOptions = new ArrayList<>();
//			storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            loadOpts.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
            resource.load(loadOpts);

            ASE2015QueryFactory.queryAse2015SpecificInvisibleMethodDeclarations(resource).callWithMemoryAndTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryASE2015ThrownExceptions(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoBlueprintsURI.NEO_GRAPH_SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

            URI uri = NeoBlueprintsURI.createNeoGraphURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoBlueprintsURI.NEO_GRAPH_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            // Add the LoadedObjectCounter store
            List<StoreOption> storeOptions = new ArrayList<>();
//			storeOptions.add(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING);
            storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
            loadOpts.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
            resource.load(loadOpts);

            ASE2015QueryFactory.queryAse2015ThrownExceptions(resource).callWithMemoryAndTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
