/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.structure;

/**
 * A {@link RawFeature} representing a reference, which links several {@link RawElement}s.
 */
public class RawReference extends RawFeature {

    /**
     * The identifier of the referenced element.
     */
    private RawId idReference;

    /**
     * Whether this reference is a containment.
     */
    private boolean containment;

    /**
     * Constructs a new {@code RawReference} with the given {@code name}.
     *
     * @param name the name of this reference
     */
    public RawReference(String name) {
        super(name);
        this.containment = false;
    }

    /**
     * Converts an {@link RawAttribute} to a {@code RawReference}.
     *
     * @param attribute the attribute to convert
     *
     * @return a new reference
     */
    public static RawReference from(RawAttribute attribute) {
        RawReference reference = new RawReference(attribute.name());
        reference.id(attribute.id());
        reference.index(attribute.index());
        reference.idReference(RawId.original(attribute.value().toString()));
        return reference;
    }

    /**
     * Returns the identifier of the referenced element.
     *
     * @return the identifier
     */
    public RawId idReference() {
        return idReference;
    }

    /**
     * Defines the identifier of the referenced element.
     *
     * @param idReference the identifier
     */
    public void idReference(RawId idReference) {
        this.idReference = idReference;
    }

    @Override
    public boolean isReference() {
        return true;
    }

    /**
     * Returns whether this reference is a containment.
     *
     * @return {@code true} if this reference is a containment
     */
    public boolean containment() {
        return containment;
    }

    /**
     * Defines whether this reference is a containment.
     *
     * @param containment {@code true} if this reference is a containment
     */
    public void containment(boolean containment) {
        this.containment = containment;
    }
}