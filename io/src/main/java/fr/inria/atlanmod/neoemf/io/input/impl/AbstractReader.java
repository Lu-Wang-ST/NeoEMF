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

package fr.inria.atlanmod.neoemf.io.input.impl;

import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.input.Reader;
import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;
import fr.inria.atlanmod.neoemf.io.internal.impl.AbstractInternalNotifier;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract implementation of a {@link Reader reader} that notifies {@link InternalHandler internal handlers}.
 */
public abstract class AbstractReader extends AbstractInternalNotifier implements Reader {

    protected void processStartDocument() throws Exception {
        notifyStartDocument();
    }

    /**
     * Processes a {@link Namespace} declaration.
     *
     * @param prefix the prefix
     * @param uri the uri associated with the {@code prefix}
     *
     * @see fr.inria.atlanmod.neoemf.io.beans.Namespace.Registry#register(String, String)
     */
    protected void processNamespace(String prefix, String uri) {
        Namespace.Registry.getInstance().register(prefix, uri);
    }

    protected void processEndDocument() throws Exception {
        notifyEndDocument();
    }

    protected static String format(String prefix, String value) {
        checkNotNull(value);

        return (isNull(prefix) ? "" : prefix + ':') + value;
    }
}
