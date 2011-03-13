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

package cz.cvut.promod.services.toolBarService.utils;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 11:53:01, 4.2.2010
 */

/**
 * Possible results for inserting a new action into the notation specific tool bar.
 */
public enum InsertToolBarItemResult {

    /**
     * The action has been successfully added into the tool bar.
     */
    SUCCESS,

    /**
     * The action is either null or does not have name or has an empty string as it's name.
     */
    UNDEFINED_ACTION,

    /**
     * It is not possible to insert any action into the tool bar of not existing notation.
     */
    INVALID_NOTATION,

    /**
     * It is not possible to insert action that is not registered by the ActionService.
     */
    UNREGISTERED_ACTION,

    /**
     * It is not possible to insert the same action twice into the same tool bar.
     */
    DUPLICATE_ACTION
}
