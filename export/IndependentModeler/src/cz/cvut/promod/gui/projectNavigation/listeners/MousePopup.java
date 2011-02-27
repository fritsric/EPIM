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

package cz.cvut.promod.gui.projectNavigation.listeners;

import cz.cvut.promod.services.ModelerSession;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:10:30, 22.10.2009
 */

/**
 * Project navigation mouse popup menu listener.
 */
public class MousePopup extends MouseAdapter {

    final JPopupMenu popup;
    final JTree tree;

    public MousePopup(final JPopupMenu popup, final JTree tree){
        this.popup = popup;
        this.tree = tree;
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        showPopupMenu(event);
    }

    @Override
    public void mouseReleased(final MouseEvent event) {
        showPopupMenu(event);
    }

    /**
     * Shows the popup menu.
     *
     * @param event the mouse event
     */
    private void showPopupMenu(final MouseEvent event){
        if (event.isPopupTrigger()) {
            final TreePath treePath = tree.getPathForLocation(event.getX(), event.getY());

            if(treePath != null){
                ModelerSession.getProjectControlService().setSelectedItem(treePath);
                popup.show((Component)event.getSource(),event.getX(), event.getY());
            }
        }
    }
}
