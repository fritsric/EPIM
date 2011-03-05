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

package cz.cvut.promod.services.actionService;

import cz.cvut.promod.services.actionService.actionUtils.ProModAction;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:23:05, 2.11.2009
 */

/**
 * Module specific actions holder.
 */
public class ModuleSpecificActions {

    private final Logger LOG = Logger.getLogger(ModuleSpecificActions.class);

    private final Map<String, ProModAction> moduleActionsMap;

    public ModuleSpecificActions(){
        moduleActionsMap = new HashMap<String, ProModAction>();
    }

    /**
     * @param actionIdentifier is the action identifier
     * @return returns modules required action
     */
    public ProModAction getAction(final String actionIdentifier) {
        if(moduleActionsMap.containsKey(actionIdentifier)){
            return moduleActionsMap.get(actionIdentifier);
        }

        return null;
    }

    public void setAction(final String actionIdentifier, final ProModAction newProModAction) {
        if(moduleActionsMap.containsKey(actionIdentifier)){
            LOG.error("An attempt to insert the same notation action to the notation actions map, action identifier: " + actionIdentifier + ".");
        } else {
            moduleActionsMap.put(actionIdentifier, newProModAction);
        }
    }

    public void updateActionVisibility(final String notationIdentifier) {
        for(final ProModAction proModAction : moduleActionsMap.values()){
            proModAction.updateActionVisibility(notationIdentifier);
        }
    }

    /**
     * Tests whether there is no other already registered action with the same accelerator key definition
     * by this module.
     *
     * @param keyStroke is the accelerator key to be tested
     * @return true if there is such a action, false otherwise
     */
    public boolean existAcceleratorKey(final KeyStroke keyStroke) {
        for(final ProModAction action : moduleActionsMap.values()){
            final KeyStroke key = (KeyStroke) action.getValue(AbstractAction.ACCELERATOR_KEY);
            if(key != null && key.equals(keyStroke)){
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether there has been registered such a action earlier.
     *
     * @param action is the action to be checked
     * @return true if such a action has been already registered, false otherwise 
     */
    public boolean exist(final ProModAction action) {
        return moduleActionsMap.values().contains(action);
    }
}
