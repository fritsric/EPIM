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
 * Date: 22:34:23, 12.10.2009
 */

/**
 * NotationSpecificPlugins is holder for notation and it's related modules.
 *
 * It is not possible to change the list holding modules.
 */
public final class NotationSpecificPlugins {

    private final Notation notation;

    private final List<Module> modules;


    /**
     * Constructs new NotationSpecificPlugins for the specified notation.
     *
     * @param notation is the notation
     * @param modules unchangeable list of plugins
     */
    public NotationSpecificPlugins(final Notation notation, final List<Module> modules){
        this.notation = notation;

        this.modules = modules;
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

    /**
     * Returns dockable frame data defined by the notation and all it's related modules.
     *
     * @return dockable frame data defined by the notation and all it's related modules 
     */
    public Collection<DockableFrameData> getDockableFramesData(){
        final Collection<DockableFrameData> dockableFrameData = new HashSet<DockableFrameData>();

        final Set<DockableFrameData> notationFrameData = getNotation().getDockableFrames();

        if(notationFrameData != null){
            dockableFrameData.addAll(notationFrameData);
        }

        for(final Module module : getModules()){
            final Set<DockableFrameData> moduleFrameData = module.getDockableFrames();

            if(moduleFrameData != null){
                dockableFrameData.addAll(module.getDockableFrames());
            }
        }
        
        return dockableFrameData;
    }
}
