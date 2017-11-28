/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;

import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithIndices;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link BerkeleyDbBackend} that use a {@link ManyValueWithIndices} mapping for storing features.
 *
 * @see BerkeleyDbBackendFactory
 */
@ParametersAreNonnullByDefault
class BerkeleyDbBackendIndices extends AbstractBerkeleyDbBackend implements ManyValueWithIndices {

    /**
     * A persistent map that stores many-feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link ManyFeatureBean}.
     */
    @Nonnull
    private final Database manyFeatures;

    /**
     * Constructs a new {@code BerkeleyDbBackendIndices} wrapping the provided {@code environment}.
     *
     * @param environment    the database environment
     * @param databaseConfig the database configuration
     *
     * @see BerkeleyDbBackendFactory
     */
    protected BerkeleyDbBackendIndices(Environment environment, DatabaseConfig databaseConfig) {
        super(environment, databaseConfig);

        this.manyFeatures = environment.openDatabase(null, "features/many", databaseConfig);
    }

    @Nonnull
    @Override
    protected Completable asyncClose() {
        // Close the map
        Action closeFunc = manyFeatures::close;

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(closeFunc);

        return dispatcher().submit(databaseQuery)
                .andThen(super.asyncClose());
    }

    @Override
    protected void innerCopyTo(DataMapper target) {
        super.innerCopyTo(target);

        BerkeleyDbBackendIndices to = BerkeleyDbBackendIndices.class.cast(target);
        this.copy(manyFeatures, to.manyFeatures);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return Optional.ofNullable(get(manyFeatures, key, serializers.forManyFeature(), serializers.forAny()));
    }

    @Override
    public <V> void innerValueFor(ManyFeatureBean key, @Nullable V value) {
        checkNotNull(key, "key");

        if (nonNull(value)) {
            put(manyFeatures, key, value, serializers.forManyFeature(), serializers.forAny());
        }
        else {
            delete(manyFeatures, key, serializers.forManyFeature());
        }
    }
}
