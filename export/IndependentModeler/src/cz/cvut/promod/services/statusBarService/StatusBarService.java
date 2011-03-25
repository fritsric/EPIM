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

package cz.cvut.promod.services.statusBarService;

import com.jidesoft.status.StatusBarItem;
import cz.cvut.promod.services.Service;
import cz.cvut.promod.services.statusBarService.utils.InsertStatusBarItemResult;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 14:53:26, 21.1.2010
 */

/**
 * StatusBarService allows developers to insert new status bar items into the notation specific status bar.
 */
public interface StatusBarService extends Service {

    /**
     * Appends new status bar item to the notation specific status bar.
     *
     * @param notationIdentifier is the identifier of notation into which status bar is the new item supposed to be
     * appended
     *
     * @param statusBarItem is the status bar item that is supposed to be appended
     *
     * @param itemLayout is the layout information, see JideBoxLayout.FIX, JideBoxLayout.FLEXIBLE, JideBoxLayout.VARY,
     * JideBoxLayout.FIX is the initial value in case of nullary info or invalid value
     *
     * @return any value from InsertStatusBarItemResult enumeration
     *
     * @see cz.cvut.promod.services.statusBarService.utils.InsertStatusBarItemResult for more detail info about return
     * values
     */
    public InsertStatusBarItemResult addStatusBarItem(final String notationIdentifier,
                                                      final StatusBarItem statusBarItem,
                                                      String itemLayout);

}
