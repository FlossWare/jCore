/*
 * Copyright (C) 2017 Scot P. Floess
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
package org.flossware.jcore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcore.utils.io.IoException;
import org.flossware.jcore.utils.io.IoUtils;

/**
 * Properties utility class.
 *
 * @author Scot P. Floess
 */
public final class PropertiesUtils {
    /**
     * Our logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PropertiesUtils.class.getName());

    /**
     * Default constructor not allowed.
     */
    private PropertiesUtils() {
    }

    /**
     * Return the logger.
     *
     * @return our logger.
     */
    static Logger getLogger() {
        return LOGGER;
    }

    public static Properties createProperties(final InputStream inputStream, final boolean closeStream) {
        final Properties retVal = new Properties();

        try {
            retVal.load(ObjectUtils.ensureObject(inputStream, "Must provide an input stream!"));
            return retVal;
        } catch (final Exception exception) {
            LoggerUtils.log(getLogger(), Level.WARNING, "Trouble reading input stream!", exception);

            throw new IoException(exception);
        } finally {
            if (closeStream) {
                IoUtils.close(inputStream);
            }
        }
    }

    public static Properties createProperties(final InputStream inputStream) {
        return createProperties(inputStream, false);
    }

    public static Properties createProperties(final Reader reader, final boolean closeStream) {
        final Properties retVal = new Properties();

        try {
            retVal.load(ObjectUtils.ensureObject(reader, "Must provide a reader!"));

            return retVal;
        } catch (final Exception exception) {
            LoggerUtils.log(getLogger(), Level.WARNING, "Trouble reading input stream!", exception);

            throw new IoException(exception);
        } finally {
            if (closeStream) {
                IoUtils.close(reader);
            }
        }
    }

    public static Properties createProperties(final Reader reader) {
        return createProperties(reader, false);
    }

    public static Properties createProperties(final File file) {
        try {
            return createProperties(new FileInputStream(ObjectUtils.ensureObject(file, "Must provide a file!")), true);
        } catch (final FileNotFoundException fileNotFoundException) {
            LoggerUtils.log(getLogger(), Level.WARNING, "Trouble reading input stream!", fileNotFoundException);

            throw new IoException(fileNotFoundException);
        }
    }

    public static Properties createProperties(final String resource) {
        return createProperties(PropertiesUtils.class.getResourceAsStream(resource), true);
    }
}
