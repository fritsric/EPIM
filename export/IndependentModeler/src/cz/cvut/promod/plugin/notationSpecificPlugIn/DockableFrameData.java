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

package cz.cvut.promod.plugin.notationSpecificPlugIn;

import cz.cvut.promod.gui.support.utils.NotationGuiHolder;

import javax.swing.*;
import java.util.Set;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 21:21:31, 12.10.2009
 */

/**
 * Defines a dockable frame component and some meta data related to the frame.
 */
public interface DockableFrameData {

    /**
     * Returns the graphics component that is supposed to be displayed on the dockable frame.
     *
     * @return the component that will be displayed on the dockable frame
     */
    public JComponent getDockableFrameComponent();

    /**
     * Returns specification of initial dockable frame position.
     *
     * @return initial dockable frame position
     */
    public NotationGuiHolder.Position getInitialPosition();

    /**
     * Indicated whether this dockable frame should be maximizable or not. If yes,
     * special 'maximize' icon appears on the dockable frame.
     *
     * @return true if the dockable windows is supposed to be maximizable, false otherwise
     */
    public boolean isMaximizable();

    /**
     * Returns possible application windows borders where this dockable frame could be docked.
     *
     * @return allowed sides where to dock the dockable frame
     */
    public Set<NotationGuiHolder.Position> getAllowedDockableFramePositions();

    /**
     * Returns the specification of initial dockable frame state.
     *
     * @return initial dockable frame state
     */
    public InitialState getInitialState();

    /**
     * Returns the dockable frame title.
     *
     * @return dockable frame title
     */
    public String getDockableFrameTitle();

    /**
     * Returns dockable frame associated button icon.
     *
     * @return an icon for associated button
     */
    public Icon getButtonIcon();

    
    /**
     * Possible initial states of dockable frame.
     */
    public static enum InitialState{
        /**
         * The dockable frame will be initially hidden and docked.
         */
        HIDDEN,

        /**
         * The dockable frame will be initially visible and docked.
         */
        OPENED,

        /**
         * The dockable frame will be initially visible and floated.
         */
        FLOATED
    }

}
