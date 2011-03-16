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

package cz.cvut.promod.services.extensionService;

import cz.cvut.promod.plugin.extension.Extension;
import cz.cvut.promod.services.Service;

import java.util.List;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 1:40:57, 26.1.2010
 */

/**
 * Extension Service is a access point to all loaded extensions.
 */
public interface ExtensionService extends Service {

    /**
     * Returns the extension that is specified by the identifier.
     *
     * @param identifier is the extension's identifier
     *
     * @return required extension, null if there is not such a extension
     */
    public Extension getExtension(final String identifier);

    /**
     * Returns a list of all extensions.
     *
     * @return a list of all extensions
     */
    public List<Extension> getExtensions();

}
