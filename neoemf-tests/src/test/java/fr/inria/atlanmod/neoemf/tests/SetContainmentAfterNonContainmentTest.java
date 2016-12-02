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

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.store.DirectWriteBlueprintsStore;
import fr.inria.atlanmod.neoemf.map.datastore.store.DirectWriteMapStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

/**
 * Checks that adding a transient containment sub-tree to an
 * existing PersistentResource add all its elements to the
 * resource.
 */
public class SetContainmentAfterNonContainmentTest extends AllContainmentTest {

    @Test
    public void testAddContainmentSubtreeToPersistentResourceMapDB() {
        addContainmentSubtreeToPersistentResource(mapResource, DirectWriteMapStore.class);
    }

    @Test
    public void testAddContainmentSubtreeToPersistentResourceNeo4j() {
        addContainmentSubtreeToPersistentResource(neo4jResource, DirectWriteBlueprintsStore.class);
    }

    @Test
    public void testAddContainmentSubtreeToPersistentResourceTinker() {
        addContainmentSubtreeToPersistentResource(tinkerResource, DirectWriteBlueprintsStore.class);
    }

    protected void createResourceContent(PersistentResource r) {
        p1 = factory.createPack();
        p1.setName("p1");

        r.getContents().add(p1);

        p2 = factory.createPack();
        p2.setName("p2");
        p1.getPacks().add(p2);
        pc1 = factory.createPackContent();
        pc1.setName("pc1");
        p2.getOwnedContents().add(pc1);

        com1 = factory.createAbstractPackContentComment();
        com1.setContent("My Content");

        // Add using the non-containment reference
        p2.getNonContainmentRefComments().add(com1);

        // Then add the element to the resource tree using the containment reference
        pc1.getContainmentNoOppositeRefComment().add(com1);
    }

    private void addContainmentSubtreeToPersistentResource(PersistentResource persistentResource, Class<?> eStoreClass) {
        createResourceContent(persistentResource);

        assertThat(com1.eStore()).isInstanceOf(eStoreClass);
        assertThat(com1.resource()).isSameAs((Resource.Internal) persistentResource);

        // Check that the element has a container (it cannot be in the resource if it does not)
        assertThat(com1.eContainer()).isSameAs((EObject) pc1);
        assertThat(com1.eInternalContainer()).isSameAs((EObject) pc1);

        // Check that the element is in the containment reference list of its parent
        assertThat(pc1.getContainmentNoOppositeRefComment()).contains(com1);

        // Check everything is accessible from the resource
        assertThat(persistentResource.getAllContents()).hasSize(4);
    }
}