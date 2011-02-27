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

import java.util.List;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 1:22:36, 30.10.2009
 */

/**
 * Class that is a base class for all items in menus.
 */
public interface ModelerMenuItem {

    /**
     * Returns the list of relative items. Relives are all items in menu that have the same name (text).
     * All relatives return empty list (but not nullary), but one returns list containing references to all
     * other relative items (to make it possible to set them not visible).
     *
     * @return If the item is supposed to be hidden when not related notation is selected, than this methods
     * return an empty list, if it has no relatives then returns null, and if this is the item that is supposed
     * to be shown (event if it is disabled anyway) that it returns the non empty list of relatives.
     */
    public List<ModelerMenuItem> getListOfRelatives();

    /**
     * Sets the list of relative items. Relives are all items in menu that have the same name (text).
     * All relatives return empty list (but not nullary), but one returns list containing references to all
     * other relative items (to make it possible to set them not visible).
     *
     * @param relatives is the list of relatives
     */
    public void setListOfRelatives(List<ModelerMenuItem> relatives);

}
