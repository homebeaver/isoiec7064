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
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.Modulus97CheckDigit;

/**
 * LEI (Legal Entity Identifier) Validator.
 * <p>
 * The Legal Entity Identifier (LEI) is a unique global identifier for legal entities participating in financial transactions.
 * Also known as an LEI code or LEI number, see <A HREF="https://en.wikipedia.org/wiki/Legal_Entity_Identifier">Wikipedia</A>.
 * The schema can be found in the most recent version of the
 *  <A HREF="https://en.wikipedia.org/wiki/ISO/IEC_6523">ISO 6523 ICD</A> list.
 *  The prefix of this specfic ICD identifier is 0199.
 * </p>
 * There are two formats, the actual style and the old style.
 * <p>
 * Example: <br>
 * <code>54930084UKLVMY22DS16</code> for G.E. Financing GmbH
 * <br>
 * <code>213800WSGIIZCXF1P572</code> Jaguar Land Rover Ltd
 * <br>
 * <code>M07J9MTYHFCSVRBV2631</code> RWE AG (old Style, before 2012.Nov.30)
 * </p>
 */
public class LEIValidator {

    private static final String[] FORMAT = new String[]
        { "^(\\d{4})([A-Z0-9]{14})(\\d{2})$" // (LOU)(two reserved + LEI)(two mandatory check digits)
        , "^([A-Z0-9]{18})(\\d{2})$"         // old Style
        };

    private static final int CODE_LEN = 20;

    private static final CodeValidator VALIDATOR =
        new CodeValidator(new RegexValidator(FORMAT), CODE_LEN, Modulus97CheckDigit.getInstance());

    /** The singleton instance which uses the default formats */
    private static final LEIValidator DEFAULT_LEI_VALIDATOR = new LEIValidator();

    /**
     * Return a singleton instance of the validator using the default formats
     *
     * @return A singleton instance of the validator
     */
    public static LEIValidator getInstance() {
        return DEFAULT_LEI_VALIDATOR;
    }

    /**
     * Validate a LEI
     *
     * @param id The value validation is being performed on
     * @return <code>true</code> if the value is valid
     */
    public boolean isValid(String id) {
        return VALIDATOR.isValid(id);
    }

}
