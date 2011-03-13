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

import com.jidesoft.action.CommandBar;

import java.util.Map;
import java.util.HashMap;
import java.awt.*;

import cz.cvut.promod.services.actionService.actionUtils.ProModAction;
import cz.cvut.promod.services.toolBarService.utils.InsertToolBarItemResult;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.gui.ModelerModel;

import javax.swing.*;

import org.apache.log4j.Logger;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:51:01, 10.10.2009
 */

/**
 * An implementation of ToolBarControlService.
 */
public class ToolBarControlServiceImpl implements ToolBarControlService{

    private static final Logger LOG = Logger.getLogger(ToolBarControlServiceImpl.class);

    private final Map<String, CommandBar> commandBars;

    /**
     * Constructs ToolBarControlServiceImpl class.
     */
    public ToolBarControlServiceImpl() {
        commandBars = new HashMap<String, CommandBar>();
    }

    /** {@inheritDoc} */
    public CommandBar getCommandBar(final String notationIdentifier) {
        if(notationIdentifier == null){
            return null;
        }

        if(ModelerSession.getNotationService().existNotation(notationIdentifier)
                || notationIdentifier.equals(ModelerModel.MODELER_IDENTIFIER)){
            
            if(commandBars.containsKey(notationIdentifier)){
                return commandBars.get(notationIdentifier);
            }

            final CommandBar commandBar = new CommandBar();
            commandBars.put(notationIdentifier, commandBar);
            return commandBar;
        }

        return null;
    }

    /** {@inheritDoc} */
    public InsertToolBarItemResult addAction(final String notationIdentifier, final ProModAction action) {
        if(action == null
                || action.getValue(AbstractAction.NAME) == null
                || ((String)action.getValue(AbstractAction.NAME)).isEmpty()){
            
            return InsertToolBarItemResult.UNDEFINED_ACTION;
        }

        if(!ModelerSession.getNotationService().existNotation(notationIdentifier)){
            LOG.error("Not possible to insert action to the tool bar of not existing notation.");
            return InsertToolBarItemResult.INVALID_NOTATION;
        }

        if(!ModelerSession.getActionControlService().isRegisteredAction(action)){
            LOG.error("Not possible to insert unregistered action.");            
            return InsertToolBarItemResult.UNREGISTERED_ACTION;
        }

        if(!action.getNotationIdentifier().equals(notationIdentifier)){
            LOG.error("Not possible to insert action registered with another notation.");
            return InsertToolBarItemResult.UNREGISTERED_ACTION;
        }

        final CommandBar commandBar = getCommandBar(notationIdentifier);

        if(!isUniqueAction(commandBar, action)){
            LOG.info("Skipping insertion of duplicate action," +
                    " action identifier: " + action.getActionIdentifier() +
                    ", notation identifier: " + action.getNotationIdentifier() +
                    ", module identifier: " + action.getModuleIdentifier());

            return InsertToolBarItemResult.DUPLICATE_ACTION;
        }

        commandBar.add(action);

        return InsertToolBarItemResult.SUCCESS;
    }

    /**
     * Checks whether there is no the same action already inserted in one tool bar. It uses just reference
     * comparison (==) for action equality.
     *
     * @param commandBar is the status bar to be search
     * @param action is the action to be found
     * @return true if there is already no such a action in the command bar, false otherwise  
     */
    private boolean isUniqueAction(final CommandBar commandBar, final ProModAction action) {
        for(final Component component : commandBar.getComponents()){
            if(component instanceof JButton){
                final JButton button = (JButton) component;
                final Action buttonAction = button.getAction();

                if( buttonAction != null && buttonAction == action){
                    return false;
                }
            }
        }

        return true;
    }

    /** {@inheritDoc} */
    public boolean check() {
        return true;
    }
    
}
