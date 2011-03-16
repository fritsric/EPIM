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

import com.jidesoft.docking.DefaultDockingManager;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.swing.JideButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import cz.cvut.promod.gui.support.utils.NotationGuiHolder;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 18:38:43, 19.10.2009
 *
 * Implementation of an ActionListener that reacts on side panel buttons clicks. It set the
 * corresponding DockableFrame available.
 */
public class SideButtonListener implements ActionListener {

    private final DefaultDockingManager dockingManager;
    private final NotationGuiHolder specification;
    private final DockableFrame dockableFrame;

    public SideButtonListener( final NotationGuiHolder specification,
                               final DefaultDockingManager dockingManager,
                               final DockableFrame frame ){

        this.dockingManager = dockingManager;
        this.dockableFrame = frame;
        this.specification = specification;
    }

    public void actionPerformed( ActionEvent e ){
        final List<DockableFrame> dockableFrameList;
        final NotationGuiHolder.Position position = specification.getPosition(dockableFrame.getName());

        dockableFrameList = specification.getListOfFrames(position);

        if( dockableFrame.isHidden()){
            dockingManager.setFrameAvailable(dockableFrame.getName());
            dockingManager.showFrame(dockableFrame.getName());
            dockingManager.setFrameUnavailable(dockableFrame.getName());
        }

        if( !dockableFrame.isFloated() ){
            for( DockableFrame frame : dockableFrameList ){
                if( (!frame.isFloated()) && (frame != dockableFrame) ){
                    dockingManager.setFrameUnavailable(frame.getName());
                    final JideButton but = specification.getButton(frame.getName(), position);
                    but.setSelected(false);
                }
            }
        }

        if( dockableFrame.isAvailable() ){
            dockingManager.setFrameUnavailable(dockableFrame.getName());
            specification.getDockableFrameFacade(dockableFrame.getName()).setWasAvailable(false);
        } else{
            dockingManager.setFrameAvailable(dockableFrame.getName());
        }
    }
    
}