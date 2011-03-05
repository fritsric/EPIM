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

package cz.cvut.promod.services.menuService.utils;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 10:53:22, 2.2.2010
 */

/**
 * Possible results for inserting methods defined in MenuService.
 */
public enum InsertMenuItemResult {

    /**
     * Menu item has been successfully inserted.
     */
    SUCCESS,

    /**
     * The action that was supposed to be inserted into the menu was not registered
     * by the ActionService. All actions inserted by the methods defined in MenuService into
     * any menu has been registered by the ActionService.
     */
    NOT_REGISTERED_ACTION,

    /**
     * Illegal or not valid placement information.
     * Note, that for inserting menu items into the main menu the hierarchical position cannot be an empty string, but
     * when one inserts menu items to the popup menus, the first level of this popup menu (visible immediately
     * when the popup menu is shown) and then an empty string in hierarchical position is expected.
     */
    WRONG_PLACEMENT,

    /**
     * Popup menu for required notation is not available. Possible reasons are that there is not such a notation
     * with given identifier or the notation doesn't support popup menu feature.
     */
    POPUP_NOT_SUPPORTED,

    /**
     * Undefined action cannot be inserted into any menu. The action cannot be null and the action name (text)
     * cannot be null or an empty string.
     */
    UNDEFINED_ACTION,

    /**
     * It is not possible to insert two same actions (to comparison is used mark ==, so the references are compared),
     * under one parent into any menu. So for example it is not possible to insert the same action twice under menu
     * "File", etc. When this kind of situation occur that this error state is returned.
     */
    DUPLICIT_ACTION
}
