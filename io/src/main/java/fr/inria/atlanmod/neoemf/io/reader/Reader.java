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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.InputNotifier;
import fr.inria.atlanmod.neoemf.io.processor.Processor;

import java.io.InputStream;

/**
 * A {@link InputNotifier} able to read a file.
 * </p>
 * It correspond to the head of the parsing process in case of an import.
 */
public interface Reader extends InputNotifier<Processor> {

    /**
     * Creates a series of processors in order to build and analyze the read struture.
     *
     * @return a processor, or several embedded
     */
    Processor defaultProcessor();

    /**
     * Reads a stream and notifies registered {@link Processor}.
     *
     * @param stream the stream to read
     */
    void read(InputStream stream) throws Exception;
}