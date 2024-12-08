/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.validator.routines.checkdigit;

import org.apache.commons.validator.GenericValidator;

/**
 * <strong>IBAN</strong> (International Bank Account Number) Check Digit calculation/validation.
 * <p>
 * This routine is based on the ISO 7064 Mod 97,10 check digit calculation routine.
 * <p>
 * The two check digit characters in a IBAN number are the third and fourth characters
 * in the code. For <em>check digit</em> calculation/validation the first four characters are moved
 * to the end of the code.
 *  So {@code CCDDnnnnnnn} becomes {@code nnnnnnnCCDD} (where
 *  {@code CC} is the country code and {@code DD} is the check digit). For
 *  check digit calculation the check digit value should be set to zero (i.e.
 *  {@code CC00nnnnnnn} in this example.
 * <p>
 * Note: the class does not check the format of the IBAN number, only the check digits.
 * <p>
 * For further information see
 *  <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">Wikipedia -
 *  IBAN number</a>.
 *
 * @since 1.4
 */
public final class IBANCheckDigit extends Modulus97CheckDigit {

    private static final int MIN_CODE_LEN = 5;

    private static final long serialVersionUID = -3600191725934382801L;

    /** Singleton Check Digit instance */
    private static final IBANCheckDigit INSTANCE = new IBANCheckDigit();

    /**
     * Gets the singleton instance of this validator.
     * @return A singleton instance of the class.
     */
    public static CheckDigit getInstance() {
        return INSTANCE;
    }

    /**
     * Calculate the <em>Check Digit</em> for an IBAN code.
     * <p>
     * <strong>Note:</strong> The check digit is the third and fourth
     * characters and is set to the value "{@code 00}".
     *
     * @param code The code to calculate the Check Digit for
     * @return The calculated Check Digit as 2 numeric decimal characters, e.g. "42"
     * @throws CheckDigitException if an error occurs calculating
     * the check digit for the specified code
     */
    @Override
    public String calculate(String code) throws CheckDigitException {
        if (GenericValidator.isBlankOrNull(code)) {
            throw new CheckDigitException(CheckDigitException.MISSING_CODE);
        }
        if (code.length() < MIN_CODE_LEN) {
            throw new CheckDigitException(CheckDigitException.invalidCode(code, "Code length=" + code.length()));
        }
        code = code.substring(4) + code.substring(0, 2); // CHECKSTYLE IGNORE MagicNumber
        return super.calculate(code);
//        final int m = getModulus();
//        final int cm = calculateModulus(code, false);
//        // now compute what checksum will be congruent to 1 mod M
//        int checksum = (m - cm + 1) % m;
//        // check digits can be from 02-98 (00 and 01 are not possible)
//        return toCheckDigit(checksum > 1 ? checksum : checksum + m);
    }

    /**
     * Validate the check digit of an IBAN code.
     *
     * @param code The code to validate
     * @return {@code true} if the check digit is valid, otherwise
     * {@code false}
     */
    @Override
    public boolean isValid(final String code) {
        if (code == null || code.length() < MIN_CODE_LEN) {
            return false;
        }
//        final String check = code.substring(2, 4); // CHECKSTYLE IGNORE MagicNumber
//        if ("00".equals(check) || "01".equals(check) || "99".equals(check)) {
//            return false;
//        }
//        try {
//            String cd = calculate(code);
//            return cd.equals(check);
//        } catch (final CheckDigitException ex) {
//            return false;
//        }
        return super.isValid(code.substring(4) + code.substring(0, 4)); // CHECKSTYLE IGNORE MagicNumber
    }

}
