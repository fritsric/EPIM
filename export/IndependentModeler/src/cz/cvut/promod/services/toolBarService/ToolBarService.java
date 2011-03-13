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

package cz.cvut.promod.services.toolBarService;

import cz.cvut.promod.services.Service;
import cz.cvut.promod.services.toolBarService.utils.InsertToolBarItemResult;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:46:23, 10.10.2009
 */

/**
 * ToolBarService allows developer to insert (append) simple action to the notation specific tool bar.  
 */
public interface ToolBarService extends Service{

    /**
     * Adds new action to the tool bar of specified notation.
     *
     * @param notationIdentifier is the identifier of the notation to which is tool bar is the action supposed to be added.
     * @param action the action that is supposed to be added
     * @return the result in form of InsertToolBarItemResult
     *
     * @see cz.cvut.promod.services.toolBarService.utils.InsertToolBarItemResult
     */
    public InsertToolBarItemResult addAction(final String notationIdentifier, final ProModAction action);   

}
