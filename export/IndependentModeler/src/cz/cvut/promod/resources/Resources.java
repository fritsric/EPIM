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

package cz.cvut.promod.resources;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;
import java.io.File;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:35:06, 7.12.2009
 */

/**
 * Resources class is used to load & store all necessary resources like likes, etc.
 */
public class Resources {

    private static final Logger LOG = Logger.getLogger(Resources.class);

    public static final String NAVIGATION = "navigation/";
    public static final String CONFIG = "config/";
    public static final String MODELER = "modeler/";

    public final static String PLUGINS_XSD_FILE = "plugins.xsd";
    public final static String PROJECT_XSD_FILE = "project.xsd"; 

    public static final String ICONS = "icons/";

    public static final String DIAGRAM_ICON = "projectDiagram.png";
    public static final String PROJECT_ICON = "projectIcon.png";
    public static final String SUBFOLDER_ICON = "subfolderIcon.png";
    public static final String EXPAND_ALL_ICON = "expandAll.png";
    public static final String COLLAPSE_ICON = "collapse.png";
    public static final String DIAGRAM_ADD_ICON = "pageAdd.png";
    public static final String FOLDER_ADD_ICON = "folderAdd.png";

    public static final String DELETE_ICON = "delete.png";
    public static final String SAVE_ALL_ICON = "saveAll.png";
    public static final String NEW_PROJECT_ICON = "newProject.png";
    public static final String OPEN_ICON = "open.png";
    public static final String RENAME_ICON = "rename.png";

    private final static Map<String, ImageIcon> icons = new HashMap<String, ImageIcon>();
    private final static Map<String, File> configFiles = new HashMap<String, File>();


    /**
     * Returns the icon that has been already loaded or tries to load this icon.
     *
     * @param resourceName is the full name of required resource (= icon)
     * @return the required icon if no error(s) occurred, null otherwise
     */
    public static ImageIcon getIcon(final String resourceName){
        ImageIcon imageIcon;

        if(icons.containsKey(resourceName)){
            imageIcon = icons.get(resourceName);

        } else {
            imageIcon = loadIcon(resourceName);

            if(imageIcon != null){
                icons.put(resourceName, imageIcon);
            }
        }

        return imageIcon;
    }

    /**
     * Loads the icon.
     *
     * @param resourceName is the full name of the icon
     * @return the required icon or null if the there is no resource with given resourceName
     */
    private static ImageIcon loadIcon(final String resourceName) {
        final URL systemResource = ClassLoader.getSystemResource(resourceName);

        if(systemResource == null){
            LOG.error("Resource " + resourceName + " couldn't be found.");
            return null;
        }

        return new ImageIcon(systemResource);
    }

    /**
     * Returns the resource that has been already loaded or tries to load this icon.
     *
     * @param resourceName is the full name of required resource
     * @return the required icon if no error(s) occurred, null otherwise
     */
    public static File getConfigFile(final String resourceName){
        File configFile;

        if(icons.containsKey(resourceName)){
            configFile = configFiles.get(resourceName);

        } else {
            configFile = loadConfigFile(resourceName);

            if(configFile != null){
                configFiles.put(resourceName, configFile);
            }
        }

        return configFile;
    }

    /**
     * Loads the config resources.
     *
     * @param resourceName is the full name of the resource
     * @return the required resource or null if the there is no resource with given resourceName
     */
    private static File loadConfigFile(final String resourceName) {
        final URL systemResource = ClassLoader.getSystemResource(resourceName);

        if(systemResource == null){
            LOG.error("Resource " + resourceName + " couldn't be found.");
            return null;
        }

        return new File(systemResource.getFile());
    }

}

