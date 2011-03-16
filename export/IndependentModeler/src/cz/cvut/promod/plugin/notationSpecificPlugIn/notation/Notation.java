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

package cz.cvut.promod.plugin.notationSpecificPlugIn.notation;

import cz.cvut.promod.plugin.notationSpecificPlugIn.NotationSpecificPlugin;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.factory.DiagramModelFactory;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.localIOController.NotationLocalIOController;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;
import cz.cvut.promod.services.menuService.MenuService.MenuSeparator;
import cz.cvut.promod.services.menuService.utils.InsertMenuItemResult;
import cz.cvut.promod.services.menuService.utils.MenuItemPosition;

import javax.swing.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 20:58:30, 12.10.2009
 */

/**
 * Common interface for all plugins of the type Notation.
 */
public interface Notation extends NotationSpecificPlugin {

    /**
     * Returns the full notation name.
     *
     * @return the full notation name.
     */
    public String getFullName();

    /**
     * Returns the notation name abbreviation.
     *
     * @return the notation name abbreviation.
     */
    public String getAbbreviation();

    /**
     * Returns the notation specific icon that is used for items in the project navigation tree.
     *
     * @return the notation specific icon that is used for items in the project navigation tree
     */
    public Icon getNotationIcon();

    /**
     * Returns the notation specific tool tip that is used for items in the project navigation tree.
     *
     * @return the notation specific tool tip that is used for items in the project navigation tree
     */
    public String getNotationToolTip();

    /**
     * Intervenes the access to the workspace component implementation.
     *
     * @return a workspace component holder implementation
     */
    public NotationWorkspaceData getNotationWorkspaceData();

    /**
     * Returns a image that represents the notation specific preview.
     * The image is supposed to be 400 x 200 pixels.
     *
     * @return preview image
     */
    public ImageIcon getNotationPreviewImage();

    /**
     * Returns a short notation description.
     *
     * @return a short notation description
     */
    public String getNotationPreviewText();

    /**
     * Intervenes the access to the new (empty) notation specific diagrams factory.
     *
     * @return a factory for a empty diagrams creation
     */
    public DiagramModelFactory getDiagramModelFactory();

    /**
     * Intervenes the access to the notation specific file system diagram serialization mechanism.
     *
     * @return a controller for the notation specific file system diagram serialization mechanism 
     */
    public NotationLocalIOController getLocalIOController();

    /**
     * Adds an item into notation's workspace popup menu. Not all notations have popup menus.
     *
     * @return a status message
     */
     public InsertMenuItemResult addPopupMenuItem(ProModAction proModAction,
                                                 MenuItemPosition menuItemPosition,
                                                 MenuSeparator menuSeparator,
                                                 boolean checkable);

}
