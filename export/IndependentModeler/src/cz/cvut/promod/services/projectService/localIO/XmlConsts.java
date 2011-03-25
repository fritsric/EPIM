/****************************************************************************
** This file may be used under the terms of the MIT licence:
**
** Permission is hereby granted, free of charge, to any person obtaining a copy
** of this software and associated documentation files (the "Software"), to deal
** in the Software without restriction, including without limitation the rights
** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
** copies of the Software, and to permit persons to whom the Software is
** furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included in
** all copies or substantial portions of the Software.
**
** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
** THE SOFTWARE.
****************************************************************************/

package cz.cvut.promod.services.projectService.localIO;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 1:26:22, 29.10.2009
 */

/**
 * Holds project file specific xml constants.
 */
public abstract class XmlConsts {

    public static final String ENCODING = "UTF-8";
    public static final String VERSION = "1.0";

    public final static String XSD_SCHEMA_LANGUAGE_KEY  = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    public final static String XSD_SCHEMA_LANGUAGE_VALUE  = "http://www.w3.org/2001/XMLSchema";

    public final static String XSD_SCHEMA_SOURCE_KEY  = "http://java.sun.com/xml/jaxp/properties/schemaSource";    

    public static final String PROMOD_PROJECT_COMMENT =
            "ProMod project file. Avoid of unnecssary manul changes in this file!";

    public static final String ROOT_ELEMENT = "promod-project";

    public static final String BASIC_INFO_ELEMENT = "basics";
    public static final String LAST_NAME_ELEMENT = "last-name";
    public static final String LAST_LOCATION_ELEMENT = "last-location";
    public static final String LAST_DATE_ELEMENT = "date";
    public static final String LAST_USER_ELEMENT = "user";

    public static final String CONFIGURATION_ELEMENT = "configuration";
    public static final String PLUGINS_ELEMENT = "plugins";

    public static final String NOTATION_ELEMENT = "notation";
    public static final String MODULE_ELEMENT = "module";
    public static final String EXTENSION_ELEMENT = "extension";

    public static final String IDENTIFIER_ATTRIBUTE = "identifier";
    public static final String FULL_NAME_ATTRIBUTE = "full-name";
    public static final String ABBREVIATION_ATTRIBUTE = "abbreviation";
    public static final String EXTENSION_ATTRIBUTE = "extension";
    public static final String DESCRIPTION_ATTRIBUTE = "description";


    public static final String INDEND_AMOUNT_PROPERTY = "{http://xml.apache.org/xslt}indent-amount";
    public static final String INDEND_AMOUNT = "2";
    public static final String YES = "yes";
    public static final String NO = "no";

    public static final String METHOD = "xml";

}
