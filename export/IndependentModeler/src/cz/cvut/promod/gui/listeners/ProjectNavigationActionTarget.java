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

package cz.cvut.promod.gui.listeners;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;
import cz.cvut.promod.gui.support.utils.NotationGuiHolder;
import cz.cvut.promod.gui.projectNavigation.ProjectNavigation;
import cz.cvut.promod.gui.ModelerModel;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.LinkedList;

import com.jidesoft.docking.DockableFrame;
import com.jidesoft.docking.DockingManager;

import javax.swing.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 18:32:19, 19.10.2009
 *
 * Action that controls the visibility of high level navigation.
 */
public  class ProjectNavigationActionTarget extends ProModAction {

    private final DockingManager dockingManager;
    private final ProjectNavigation projectNavigationDockableFrame;
    private final ModelerModel model;

    public ProjectNavigationActionTarget(final String displayName, 
                                         final Icon icon,
                                         final KeyStroke keyStroke,
                                         final DockingManager dockingManager,
                                         final ProjectNavigation projectNavigationDockableFrame,
                                         final ModelerModel model){

        super(displayName, icon, keyStroke);

        this.dockingManager = dockingManager;
        this.projectNavigationDockableFrame = projectNavigationDockableFrame;
        this.model = model;
    }

    public void actionPerformed(ActionEvent event) {
        if( projectNavigationDockableFrame.isAvailable() ){
            dockingManager.setFrameUnavailable(projectNavigationDockableFrame.getName());
        } else{
            final String currentNotation = ModelerSession.getProjectService().getSelectedProjectNotationIndentifier();

            final NotationGuiHolder specification = model.getNotationGuiHolder(currentNotation);

            if( specification != null ){
                final List<DockableFrame> visibleDockableFrames = new LinkedList<DockableFrame>();

                for( final DockableFrame dockableFrame : specification.getListOfFrames(NotationGuiHolder.Position.LEFT) ){
                    if( dockableFrame.isAvailable() ){
                        visibleDockableFrames.add((dockableFrame));
                        specification.getButton(dockableFrame.getName(), NotationGuiHolder.Position.LEFT).doClick();
                    }
                }

                dockingManager.setFrameAvailable(projectNavigationDockableFrame.getName());

                for( final DockableFrame dockableFrame : visibleDockableFrames ){
                    specification.getButton(dockableFrame.getName(), NotationGuiHolder.Position.LEFT).doClick();
                }
            } else{
                dockingManager.setFrameAvailable(projectNavigationDockableFrame.getName());
            }
        }
    }

}