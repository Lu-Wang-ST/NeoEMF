/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.config;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import fr.inria.atlanmod.neoemf.bind.FactoryBinding;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import java.nio.file.Path;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.config.Config} that creates Blueprints TinkerGraph specific configuration.
 * <p>
 * All features are all optional: configuration can be created using all or none of them.
 */
@Component(service = Config.class, scope = ServiceScope.PROTOTYPE)
@FactoryBinding(factory = BlueprintsBackendFactory.class, variant = "tinkergraph")
@ParametersAreNonnullByDefault
public class BlueprintsTinkerConfig extends BaseBlueprintsConfig<BlueprintsTinkerConfig> {

    /**
     * The base prefix for all natives options related to {@link TinkerGraph} under Blueprints.
     */
    static final String TINKER_PREFIX = createKey(BLUEPRINTS_PREFIX, "tg");

    /**
     * Constructs a new {@code BlueprintsTinkerConfig} with default settings.
     */
    public BlueprintsTinkerConfig() {
        setGraph(TinkerGraph.class);
        setFileType(TinkerGraph.FileType.GRAPHML);
    }

    @Override
    public void setLocation(Path directory) {
        addOption(createKey(TINKER_PREFIX, "directory"), directory.toString());
    }

    @Nonnull
    @Override
    protected Predicate<String> isReadOnlyKey() {
        return super.isReadOnlyKey()
                .or(s -> s.equals(createKey(TINKER_PREFIX, "file-type")));
    }

    /**
     * Defines the given {@link com.tinkerpop.blueprints.impls.tg.TinkerGraph} {@code fileType} in this configuration.
     *
     * @param fileType the graph type
     */
    protected void setFileType(TinkerGraph.FileType fileType) {
        addOption(createKey(TINKER_PREFIX, "file-type"), fileType.name());
    }
}
