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

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import cz.cvut.promod.services.pluginLoaderService.errorHandling.PluginLoaderErrorHandler;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 2:13:43, 9.2.2010
 */
public class ProjectFileXmlErrorHandler implements ErrorHandler {

    private static final Logger LOG = Logger.getLogger(PluginLoaderErrorHandler.class);

    /** {@inheritDoc} */
    public void warning(SAXParseException exception) throws SAXException {
        LOG.warn("Project loader XSD validating and parsing warning.", exception);
    }

    /** {@inheritDoc} */
    public void error(SAXParseException exception) throws SAXException {
        LOG.warn("Project loader XSD validating and parsing error.", exception);
        throw new SAXException(exception);
    }

    /** {@inheritDoc} */
    public void fatalError(SAXParseException exception) throws SAXException {
        LOG.warn("Project loader XSD validating and parsing fatal error.", exception);
        throw new SAXException(exception);
    }

}
