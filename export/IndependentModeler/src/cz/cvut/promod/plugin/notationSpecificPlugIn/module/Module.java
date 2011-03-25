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

package cz.cvut.promod.plugin.notationSpecificPlugIn.module;

import cz.cvut.promod.plugin.notationSpecificPlugIn.NotationSpecificPlugin;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 20:58:36, 12.10.2009
 */

/**
 * Module is notation specific type of plugin. It is supposed to extend some functionality of a notation. Modules can
 * add some dockable frames, use tool bar, status bar, menus, etc.
 *
 * Any module is always related to a notation.
 *
 * There are several possible ways how to instantiate the module. An author of a module has to provide the module's
 * definition with one of following types constructor. If the PluginLoaderService finds such a constructor, then it
 * uses this one. Priority/preferred constructor is determined using order rules. To get understand these rules then
 * check out comments about the Plugin Loader Service.
 *
 * @see cz.cvut.promod.services.pluginLoaderService.PluginLoaderService for more details about module instantiation 
 */
public interface Module extends NotationSpecificPlugin {

    /**
     * Returns the notation identifier which is the module related to.
     *
     * @return notation identifier
     */
    public String getRelatedNotationIdentifier();

}
