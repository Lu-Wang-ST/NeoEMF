/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackend}.
 */
@ParametersAreNonnullByDefault
public abstract class MapDbAdapter extends AbstractPersistentLocalAdapter {

    /**
     * A {@link MapDbAdapter} with a mapping with indices.
     */
    @AdapterName("mapdb-i")
    public static final class WithIndices extends MapDbAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new MapDbConfig().withIndices();
        }
    }

    /**
     * A {@link MapDbAdapter} with a mapping with arrays.
     */
    @AdapterName("mapdb-a")
    public static final class WithArrays extends MapDbAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new MapDbConfig().withArrays();
        }
    }

    /**
     * A {@link MapDbAdapter} with a mapping with lists.
     */
    @AdapterName("mapdb-l")
    public static final class WithLists extends MapDbAdapter {

        @Nonnull
        @Override
        protected ImmutableConfig createConfig() {
            return new MapDbConfig().withLists();
        }
    }
}
