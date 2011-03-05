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

package cz.cvut.promod.epc.resources;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.net.URL;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 21:24:00, 7.12.2009
 *
 * Resources holder of the EPCNotation plugin.
 */
public class Resources {

    private static final Logger LOG = Logger.getLogger(Resources.class);

    public static final String RESOURCES_FILE = "epc";

    public static final String ICONS = "icons/";
    public static final String PORTS = "ports/";

    public static final String DIAGRAM = "diagramEPC.png";    

    public static final String PORT_BLUE = "portBlue.png";

    public static final String PREVIEW = "epcPreview.png";

    public static final String ELEMENT = "element.png";
    public static final String CONNECTOR = "connector.png";

    private final static Map<String, ImageIcon>  icons = new HashMap<String, ImageIcon>();

    private static ResourceBundle resources = null;

    /**
     * Returns the required icon.
     * @param resourceName is the name of the icon.
     * @return the required icon, null if there is no such an icon
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
     * Loads the required icon.
     * @param resourceName is the name of the icon
     * @return the required action, null if there is no such an action
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
     * Returns resources fot this module.
     *
     * @return the resource bundle
     */
    public static ResourceBundle getResources(){
        if(resources == null){
            try{
                resources = ResourceBundle.getBundle(RESOURCES_FILE);

            } catch (MissingResourceException e){
                LOG.error("Couldn't load the resource bundle file.");
            }
        }

        return resources;
    }
    

}