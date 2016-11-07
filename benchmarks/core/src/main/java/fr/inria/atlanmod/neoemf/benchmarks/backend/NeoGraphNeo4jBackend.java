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

package fr.inria.atlanmod.neoemf.benchmarks.backend;

import fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;

import java.util.HashMap;
import java.util.Map;

public class NeoGraphNeo4jBackend extends NeoGraphBackend {

    @Override
    public String getResourceName() {
        return "neo4j.resource";
    }

    @Override
    public Map<Object, Object> getLoadOptions() {
        Map<Object, Object> loadOpts = new HashMap<>();
        loadOpts.put(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);
        return loadOpts;
    }

    @Override
    public Map<Object, Object> getSaveOptions() {
        Map<Object, Object> saveOpts = new HashMap<>();
        saveOpts.put(BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE, BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);
        return saveOpts;
    }
}
