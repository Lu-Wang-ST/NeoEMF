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

package fr.inria.atlanmod.neoemf.io.beans;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import static java.util.Objects.isNull;

/**
 * A namespace identified by a prefix and a URI.
 */
public class Namespace {

    private static final Namespace DEFAULT = new Namespace("ecore", "http://www.eclipse.org/emf/2002/Ecore");
    private final String prefix;
    private final String uri;

    private Namespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    public static Namespace getDefault() {
        return DEFAULT;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getUri() {
        return uri;
    }

    /**
     * Registry of all declared {@link Namespace namespaces}.
     */
    public static class Registry {

        private static Registry INSTANCE;
        /**
         * Registered {@link Namespace namespaces} identified by their prefix.
         */
        private final Cache<String, Namespace> namespacesByPrefix;
        /**
         * Registered {@link Namespace namespaces} identified by their URI.
         */
        private final Cache<String, Namespace> namespacesByUri;

        private Registry() {
            namespacesByPrefix = CacheBuilder.newBuilder().build();
            namespacesByUri = CacheBuilder.newBuilder().build();
        }

        public static Registry getInstance() {
            if (isNull(INSTANCE)) {
                INSTANCE = new Registry();
            }
            return INSTANCE;
        }

        public Iterable<String> getPrefixes() {
            return namespacesByPrefix.asMap().keySet();
        }

        /**
         * Returns a {@link Namespace namespace} identified by the given {@code prefix}, or {@code null} if no
         * namespace is registered with this {@code prefix}.
         *
         * @param prefix the prefix of the desired namespace
         *
         * @return a {@link Namespace namespace} identified by the given {@code prefix}, or {@code null} if no namespace
         *         is registered with this {@code prefix}
         */
        public Namespace getFromPrefix(String prefix) {
            if (isNull(prefix)) {
                return null;
            }
            return namespacesByPrefix.getIfPresent(prefix);
        }

        /**
         * Returns a {@link Namespace namespace} identified by the given {@code uri}, or {@code null} if no
         * namespace is registered with this {@code uri}.
         *
         * @param uri the URI of the desired namespace
         *
         * @return a {@link Namespace namespace} identified by the given {@code uri}, or {@code null} if no namespace is
         *         registered with this {@code uri}.
         */
        public Namespace getFromUri(String uri) {
            if (isNull(uri)) {
                return null;
            }
            return namespacesByUri.getIfPresent(uri);
        }

        /**
         * Registers a new {@link Namespace namespace} with the given {@code prefix} and {@code uri}.
         *
         * @param prefix the prefix of the new namespace
         * @param uri    the uri associated with the prefix
         */
        public void register(String prefix, String uri) {
            Namespace ns = new Namespace(prefix, uri);
            namespacesByPrefix.put(prefix, ns);
            namespacesByUri.put(uri, ns);
        }

        /**
         * Cleans the registry.
         */
        public void clean() {
            namespacesByPrefix.invalidateAll();
            namespacesByUri.invalidateAll();
        }
    }
}
