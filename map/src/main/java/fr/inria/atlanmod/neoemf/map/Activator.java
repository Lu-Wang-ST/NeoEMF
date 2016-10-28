/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;

public class Activator implements BundleActivator {
    
    private static BundleContext context;
    
    static BundleContext getContext() {
        return context;
    }
    
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        NeoLogger.info("NeoEMF Map plugin started");
        if (!PersistenceBackendFactoryRegistry.isRegistered(NeoMapURI.NEO_MAP_SCHEME)) {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME,
                    MapPersistenceBackendFactory.getInstance());
            NeoLogger.info("Map persistence backend registered");
        }
    }
    
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    };
}