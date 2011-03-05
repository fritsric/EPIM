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

import com.jidesoft.status.StatusBar;
import com.jidesoft.status.StatusBarItem;
import com.jidesoft.swing.JideBoxLayout;

import java.util.Map;
import java.util.HashMap;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.statusBarService.utils.InsertStatusBarItemResult;
import cz.cvut.promod.gui.ModelerModel;
import org.apache.log4j.Logger;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 14:54:23, 21.1.2010
 */

/**
 * StatusBarControlService implementation.
 */
public class StatusBarControlServiceImpl implements StatusBarControlService{

    private static final Logger LOG = Logger.getLogger(StatusBarControlServiceImpl.class);

    private final Map<String, StatusBar> statusBarMap;


    /**
     *  Constructs a new StatusBarControlServiceImpl instance.
     */
    public StatusBarControlServiceImpl() {
        statusBarMap = new HashMap<String, StatusBar>();
    }

     /** {@inheritDoc} */
    public StatusBar getStatusBar(final String notationIdentifier) {
         if(notationIdentifier == null){
             return null;
         }

        if(!ModelerSession.getNotationService().existNotation(notationIdentifier)
                && !notationIdentifier.equals(ModelerModel.MODELER_IDENTIFIER)){
            return null;
        }

        if(statusBarMap.containsKey(notationIdentifier)){
            return statusBarMap.get(notationIdentifier);
        }

        final StatusBar statusBar = ModelerSession.getComponentFactoryService().createStatusBar();
        statusBarMap.put(notationIdentifier, statusBar);

        return statusBar;        
    }

    /** {@inheritDoc} */
    public InsertStatusBarItemResult addStatusBarItem(final String notationIdentifier,
                                                      final StatusBarItem statusBarItem,
                                                      String itemLayout) {

        if(!ModelerSession.getNotationService().existNotation(notationIdentifier)
                && !ModelerModel.MODELER_IDENTIFIER.equals(notationIdentifier)){
            LOG.error("Not possible to insert action to the tool bar of not existing notation.");
            return InsertStatusBarItemResult.INVALID_NOTATION;
        }

        if(statusBarItem == null){
            LOG.error("Invalid status bar item to be inserted.");
            return InsertStatusBarItemResult.INVALID_STATUS_BAR_ITEM;            
        }

        if(itemLayout == null){
            LOG.info("Setting status bar layout to implicit value");
            itemLayout = JideBoxLayout.FIX;
        }

        final StatusBar statusBar = getStatusBar(notationIdentifier);

        if(!(itemLayout.equals(JideBoxLayout.FIX)
                || itemLayout.equals(JideBoxLayout.FLEXIBLE)
                || itemLayout.equals(JideBoxLayout.VARY))){

                itemLayout = JideBoxLayout.FIX;
        }

        statusBar.add(statusBarItem, itemLayout);

        return InsertStatusBarItemResult.SUCCESS;
    }

    /** {@inheritDoc} */
    public boolean check() {
        return true;
    }
    
}
