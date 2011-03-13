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

import java.util.List;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 1:41:08, 26.1.2010
 */

/**
 * Implementation of Extension Service.
 */
public class ExtensionServiceImpl implements ExtensionService {

    private final List<Extension> extensionsList;


    public ExtensionServiceImpl(final List<Extension> extensionsList) {
        this.extensionsList = extensionsList;
    }

    /** {@inheritDoc} */
    public Extension getExtension(final String identifier) {
        for(final Extension extension : extensionsList){
            if(extension.getIdentifier().equals(identifier)){
                return extension;
            }
        }

        return null;
    }

    /** {@inheritDoc} */
    public List<Extension> getExtensions() {
        return extensionsList;
    }

    /** {@inheritDoc} */
    public boolean check() {
        return true;
    }
}
