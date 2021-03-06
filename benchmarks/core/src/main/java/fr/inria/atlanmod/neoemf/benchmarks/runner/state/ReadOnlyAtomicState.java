/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public class ReadOnlyAtomicState extends AtomicState {

    @Override
    protected void initResource(Package root) {
        super.initResource(root);

        root.setName("org.neoemf");
        root.setModel(create(JavaPackage.MODEL));
    }
}
