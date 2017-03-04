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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.mapper.PersistenceMapper;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Store} to establish a mapping between {@link Resource}s and {@link fr.inria.atlanmod.neoemf.data.PersistenceBackend}s.
 */
@ParametersAreNonnullByDefault
public interface PersistentStore extends Store, PersistenceMapper {

    /**
     * Saves the modifications of the owned {@link EObject}s in the persistence back-end.
     */
    void save();

    /**
     * Returns the {@link Resource} to persist and access.
     *
     * @return the resource to persist and access
     */
    @Nullable
    PersistentResource resource();

    /**
     * Returns the resolved {@link PersistentEObject} identified by the given {@code id} or {@code null}.
     *
     * @param id the identifier of the {@link PersistentEObject} to resolve
     *
     * @return the resolved {@link PersistentEObject}, or {@code null} if no {@link PersistentEObject} can be resolved
     */
    PersistentEObject object(Id id);

    /**
     * Back-end specific computation of {@link Resource#getAllContents()}.
     *
     * @param metaclass the {@link EClass} to compute the instances of
     * @param strict    {@code true} if the lookup searches for strict instances
     *
     * @return an {@link EList} containing all the {@link EObject}s that are instances of the given {@link EClass}
     *
     * @throws UnsupportedOperationException if the back-end does not support custom all instances computation
     */
    Iterable<EObject> allInstances(EClass metaclass, boolean strict);
}
