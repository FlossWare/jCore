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
package org.flossware.jcore.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcore.utils.collections.ArrayUtils;

/**
 * String utility class.
 *
 * @author Scot P. Floess
 */
public final class StringUtils {
    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(StringUtils.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }
    /**
     * Default separator.
     */
    private static final String DEFAULT_SEPARATOR = "";

    /**
     * Return true if <code>str</code> is null or empty.
     *
     * @param str the string to examine.
     *
     * @return true if <code>str</code> is null or empty.
     */
    public static boolean isBlank(final String str) {
        return LoggerUtils.logAndReturn(getLogger(), Level.FINEST, "Is string blank [{0}] for string [{1}]", null == str || "".equals(str.trim()), str);
    }

    /**
     * Checks <code>str</code> to ensureObject it is not null nor empty.
     *
     * @param str      The string to inspect to ensureObject its not null nor empty.
     * @param errorMsg The error message within the raised exception if <code>str</code> is null or empty.
     *
     * @return str if it is not null or empty.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static String ensureString(final String str, final String errorMsg) throws IllegalArgumentException {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(errorMsg);
        }

        return str;
    }

    /**
     * Checks <code>str</code> to ensureObject it is not null nor empty.
     *
     * @param str The string to inspect to ensureObject its not null nor empty.
     *
     * @return str if it is not null or empty.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static String ensureString(final String str) throws IllegalArgumentException {
        return ensureString(str, ObjectUtils.DEFAULT_ERROR_MSG);
    }

    /**
     * Return true if a separator can be appended or false if not. To append, the index must be less than or equal to the array's
     * length - 2.
     *
     * @param index the place within the array we are processing.
     * @param objs  the array of objects being processed.
     *
     * @return true if we can append a separator or false if not.
     */
    static boolean isSeparatorAppendable(final String separator, final int index, final Object... objs) {
        return LoggerUtils.logAndReturn(getLogger(), Level.FINEST, "Is the separator appendable [{0}] for index [{1}]", null != objs && index <= (objs.length - 2) && !objs[index].toString().endsWith(separator), index);
    }

    /**
     * Concat objects together in <code>stringBuilder</code> using the toString of the <code>objs</code>.
     *
     * @param stringBuilder    the string builder form whom we will concatenate.
     * @param isSeparatorAtEnd if true denotes we will always have the separator appended.
     * @param separator        the separator to use between concatenation.
     * @param objs             the objects to concatenate.
     *
     * @return the string builder with concatendated data.
     */
    public static StringBuilder concatWithSeparator(final StringBuilder stringBuilder, final boolean isSeparatorAtEnd, final String separator, Object... objs) {
        ArrayUtils.ensureArray(objs, "Must have a list of objects to concat!");

        for (int index = 0; index < objs.length; index++) {
            stringBuilder.append(objs[index]);

            if (isSeparatorAppendable(separator, index, objs)) {
                stringBuilder.append(separator);
            }
        }

        if (isSeparatorAtEnd) {
            stringBuilder.append(separator);
        }

        LoggerUtils.log(getLogger(), Level.FINEST, "Returning [{0}]", stringBuilder);

        return stringBuilder;
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param isSeparatorAtEnd if true denotes we will always have the separator appended.
     * @param separator        the separator to use between concatenation.
     * @param objs             the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concatWithSeparator(final boolean isSeparatorAtEnd, final String separator, Object... objs) {
        return concatWithSeparator(new StringBuilder(), isSeparatorAtEnd, separator, objs).toString();
    }

    /**
     * Concat objects together in <code>stringBuilder</code> using the toString of the <code>objs</code>.
     *
     * @param stringBuilder the string builder form whom we will concatenate.
     * @param separator     the separator to use between concatenation.
     * @param objs          the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static StringBuilder concatWithSeparator(final StringBuilder stringBuilder, final String separator, Object... objs) {
        return concatWithSeparator(stringBuilder, false, separator, objs);
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param separator the separator to use between concatenation.
     * @param objs      the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concatWithSeparator(final String separator, Object... objs) {
        return concatWithSeparator(false, separator, objs);
    }

    /**
     * Concat objects together in <code>stringBuilder</code> using the toString of the <code>objs</code>.
     *
     * @param stringBuilder the string builder form whom we will concatenate.
     * @param objs          the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static StringBuilder concat(final StringBuilder stringBuilder, Object... objs) {
        return concatWithSeparator(stringBuilder, DEFAULT_SEPARATOR, objs);
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param objs the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concat(Object... objs) {
        return concatWithSeparator(DEFAULT_SEPARATOR, objs);
    }

    /**
     * Return true if <code>str</code> contains <code>contains</code>.
     *
     * @param str      is the string to examine for containing <code>contains</code>.
     * @param contains is the string to see if <code>str</code> is contained.
     *
     * @return true if <code>str</code> contains <code>contains</code>.
     */
    public static boolean isContained(final String str, final String contains) {
        final boolean retVal = (null == str) ? false : str.contains(contains);

        LoggerUtils.log(getLogger(), Level.FINEST, "Exception messsage contained result [{0}] for [{1}] message [{2}]", retVal, contains, str);

        return retVal;
    }

    /**
     * Default constructor not allowed.
     */
    private StringUtils() {
    }
}
