/*
 * MIT License
 *
 * Copyright (c) 2022 JUAN CALVOPINA M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.jcalvopinam.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * @author Juan Calvopina <juan.calvopina@gmail.com>
 */

public final class Utilities {

    public static final String NAMES = "Andrea:Juan:Isaac:Sandra:Michael:Annabel";
    public static final String LASTNAMES = "Bastidas:Calvopina:Newton:Ojeda:Patino:Cordova";
    private static final String COLON = ":";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_MATCH_FORMAT = "\\d{4}-\\d{2}-\\d{2}";

    private Utilities() {
    }

    /**
     * If the input parameter is integer, then converts the String input to Integer output else return null.
     *
     * @param input receives the input value.
     *
     * @return the value.
     */
    public static Long isNumber(String input) {
        Long output = 0l;
        if (Optional.ofNullable(input)
                    .isPresent()) {
            try {
                output = Long.parseLong(input);
            } catch (NumberFormatException nfe) {
                // TODO: fix it
            }
        }
        return output;
    }

    /**
     * Convert date from String to Date object.
     *
     * @param date receives the date in the String input.
     *
     * @return the date object.
     *
     * @throws ParseException throws the exception if the String can't be parsed.
     */
    public static Date matchDate(String date) {
        try {
            if (hasFormat(date)) {
                final DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                return formatter.parse(date);
            }
        } catch (ParseException pe) {
            // TODO: fix it
        }
        return null;
    }

    /**
     * Check if the string has a Date format.
     *
     * @param date receives the date in the String input.
     *
     * @return a boolean type.
     */
    private static boolean hasFormat(String date) {
        return date.matches(DATE_MATCH_FORMAT);
    }

    /**
     * Gets random user names.
     *
     * @return a random name.
     */
    public static String getRandomBy(final String text) {
        String[] wordsAsArray = text.split(COLON);
        int index = new Random().nextInt(wordsAsArray.length);
        return wordsAsArray[index];
    }

}
