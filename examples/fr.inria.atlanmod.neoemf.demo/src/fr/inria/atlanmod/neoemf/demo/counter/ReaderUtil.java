/*
 * Copyright (c) 2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */
package fr.inria.atlanmod.neoemf.demo.counter;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class ReaderUtil {

    /**
     * @param resource the resource to compute the size of
     * @return the number of elements in the containment tree of the Resource
     */
    public static int countElements(Resource resource) {
        Iterator<EObject> it = resource.getAllContents();
        int count = 0;
        while(it.hasNext()) {
            it.next();
            count++;
        }
        return count;
    }
    
}