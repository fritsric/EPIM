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

package cz.cvut.promod.services.pluginLoaderService.utils;

import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.Notation;
import cz.cvut.promod.plugin.notationSpecificPlugIn.module.Module;
import cz.cvut.promod.plugin.notationSpecificPlugIn.DockableFrameData;

import java.util.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 0:32:00, 22.4.2010
 */

/**
 * Implementation very similar to the NotationSpecificPlugins class, but this one is used just
 * for application start-up purposes and it is possible to add or removes modules.
 *
 * Only PluginLoaderService uses this class.
 */
public class ModifiableNotationSpecificPlugins {

    private final Notation notation;

    private final List<Module> modules;


    /**
     * Constructs new ModifiableNotationSpecificPlugins for the specified notation.
     *
     * @param notation is the notation
     */
    public ModifiableNotationSpecificPlugins(final Notation notation){
        this.notation = notation;

        modules = new LinkedList<Module>();
    }

    /**
     * Adds a new module related to the notation.
     *
     * @param module new module
     */
    public void addModule(final Module module){
        modules.add(module);
    }

    /**
     * Returns notation object.
     *
     * @return notation object
     */
    public Notation getNotation() {
        return notation;
    }

    /**
     * Returns list of notation in the same order as they were specified in plugin configuration file and have been
     * instantiated.
     *
     * @return list of modules
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Returns already stored module according to it's identifier.
     *
     * @param moduleIdentifier is the identifier of required module
     * @return module, if any with given identifier is found, null otherwise
     */
    public Module getModule(final String moduleIdentifier){
        for(final Module module : modules){
            if(module.getIdentifier().equals(moduleIdentifier)){
                return module;
            }
        }

        return null;
    }

    /**
     * Checks whether a module with given identifier has been already loaded and stored.
     *
     * @param moduleIdentifier is the required module's identifier
     * @return true, if such a module has already been loaded and stored, false otherwise
     */
    public boolean existModule(final String moduleIdentifier){
        return getModule(moduleIdentifier) != null;

    }

}
