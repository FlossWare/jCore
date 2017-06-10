/*
 * Copyright (C) 2016 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.jcore.io;

import org.flossware.jcore.utils.net.UrlException;
import org.flossware.jcore.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the UrlException class.
 *
 * @author Scot P. Floess
 */
public class UrlExceptionTest {

    @Test
    public void test_default() {
        final UrlException exception = new UrlException();

        Assert.assertNull("Should be no cause", exception.getCause());
        Assert.assertNull("Should be no message", exception.getMessage());
    }

    @Test
    public void test_String() {
        final String msg = TestUtils.generateUniqueStr("Foo", "Bar");

        final UrlException exception = new UrlException(msg);

        Assert.assertNull("Should be no cause", exception.getCause());
        Assert.assertEquals("Should be same message", msg, exception.getMessage());
    }

    @Test
    public void test_Throwable() {
        final IllegalArgumentException cause = new IllegalArgumentException();

        final UrlException exception = new UrlException(cause);

        Assert.assertSame("Should be same cause", cause, exception.getCause());
        Assert.assertEquals("Should be no message", cause.getClass().getName(), exception.getMessage());
    }

    @Test
    public void test_String_Throwable() {
        final String msg = TestUtils.generateUniqueStr("Foo", "Bar");
        final IllegalArgumentException cause = new IllegalArgumentException();

        final UrlException exception = new UrlException(msg, cause);

        Assert.assertSame("Should be same cause", cause, exception.getCause());
        Assert.assertEquals("Should be same message", msg, exception.getMessage());
    }
}
