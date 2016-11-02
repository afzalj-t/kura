/**
 * Copyright (c) 2016 Eurotech and/or its affiliates
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Amit Kumar Mondal (admin@amitinside.com)
 */
package org.eclipse.kura.type;

import static org.eclipse.kura.Preconditions.checkNull;
import static org.eclipse.kura.type.DataType.BYTE_ARRAY;

import java.util.Arrays;

import org.eclipse.kura.annotation.Immutable;
import org.eclipse.kura.annotation.ThreadSafe;

/**
 * This class represents a {@link Byte[]} value as a {@link TypedValue}.
 */
@Immutable
@ThreadSafe
public final class ByteArrayValue implements TypedValue<byte[]> {

    /**
     * The actual contained value that will be represented as
     * {@link TypedValue}.
     */
    private final byte[] value;

    /**
     * Instantiates a new byte array value.
     *
     * @param value
     *            the value
     */
    public ByteArrayValue(final byte[] value) {
        checkNull(value, "Provided Typed Value cannot be null");
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(final TypedValue<byte[]> otherTypedValue) {
        checkNull(otherTypedValue, "Typed Value cannot be null");
        final byte[] otherValue = otherTypedValue.getValue();
        for (int i = 0, j = 0; (i < this.value.length) && (j < otherValue.length); i++, j++) {
            final int a = this.value[i] & 0xff;
            final int b = otherValue[j] & 0xff;
            if (a != b) {
                return a - b;
            }
        }
        return this.value.length - otherValue.length;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ByteArrayValue other = (ByteArrayValue) obj;
        if (!Arrays.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public DataType getType() {
        return BYTE_ARRAY;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] getValue() {
        return this.value;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + Arrays.hashCode(this.value);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ByteArrayValue [value=" + Arrays.toString(this.value) + "]";
    }

}