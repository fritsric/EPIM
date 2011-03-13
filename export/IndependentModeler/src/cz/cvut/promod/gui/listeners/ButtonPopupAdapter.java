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

import com.jidesoft.swing.JideButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 18:10:20, 19.10.2009
 *
 * Is the listeners showing the popup menu on the buttons controlling the dockable frame visibility.
 */
public class ButtonPopupAdapter extends MouseAdapter {

    private final JideButton owner;
    private final JPopupMenu menu;

    public ButtonPopupAdapter( final JideButton owner, final JPopupMenu menu ){
        this.owner = owner;
        this.menu = menu;
    }

    public void mousePressed( final MouseEvent e ){
        maybepopup(e);
    }

    public void mouseReleased( final MouseEvent e ){
        maybepopup(e);
    }

    private void maybepopup( final MouseEvent e ){
        if( e.isPopupTrigger() && owner.isEnabled() ){
          menu.show(owner, e.getX(), e.getY());
        }
    }
}
