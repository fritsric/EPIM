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

import java.util.List;
import java.util.LinkedList;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:50:27, 4.2.2010
 */

/**
 * Holds loaded info about the plugin(s) from the plugin configuration file.
 */
public class PluginDetails{

    private final String clazz;

    private final String config;

    private final String alias;

    private final List<PluginDetails> modules;

    /**
     * Constructs new PluginDetails object.
     *
     * @param clazz is the plugin's class ful qualified name
     * @param config is the plugin's configuration file
     * @param alias is the plugin's alias
     */
    public PluginDetails(final String clazz, final String config, final String alias) {
        this.clazz = clazz;
        this.config = config;
        this.alias = alias;

        modules = new LinkedList<PluginDetails>();
    }

    /**
     * Notations have it's modules, these modules are stored in list
     *
     * Do not use with extensions!
     *
     * @param pluginDetails is the plugin detail of the module
     */
    public void addPluginDetails(final PluginDetails pluginDetails){
        modules.add(pluginDetails);
    }

    /**
     * Returns the full qualified class name.
     *
     * @return full qualified class name
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Returns the plugin's configuration class.
     *
     * @return plugin's configuration file
     */
    public String getConfig() {
        return config;
    }

    /**
     * Returns the plugin's alias.
     *
     * @return plugin's alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Returns all modules of the notation.
     *
     * Do not use with extensions!
     *
     * @return the list of notation's modules.
     */
    public List<PluginDetails> getModules() {
        return modules;
    }

    /**
     * Pops loaded module = returns module in style FIFO
     *
     * Do not use with extensions.
     *
     * @return the most earlier loaded module in the list 
     */
    public PluginDetails pop() {
        if(modules.size() != 0){
            return  modules.remove(0);
        }

        return null;
    }
}
