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

package cz.cvut.promod.gui.settings;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 *
 * This Class represents Setting Page Panel. In this panel there will be individual components. There shouldn't be no
 * Ok/Cancel/Apply buttons. These buttons will be controlled by Independent Modeler. This class provides only Actions
 * which will be called when the OK/Cancel/Apply button is clicked.
 *
 * User: lucky
 * Date: 20.8.2010
 * Time: 18:49:04
 */
public abstract class SettingPagePanel extends JPanel {
    private List<ActionListener> applyActionEnabledListeners = new LinkedList<ActionListener>();

    /**
     * This panel doesn't need to be initialized in the constructor. Some Setting Pages won't event be printed, because
     * user won't select them.
     */
    public abstract void lazyInitialize();

    /**
     * Returns action which will be called when user clicks on the Apply button
     * @return Apply Action
     */
    public abstract AbstractAction getApplyAction();

    /**
     * Returns action which will be called when user clicks on the Cancel button
     * @return
     */
    public abstract AbstractAction getCancelAction();

    /**
     * Returns action which will be called when user clicks on the Ok button
     * @return
     */
    public abstract AbstractAction getOkAction();

    /**
     * Adds ActionListener which will be called when applyActionEnable is fired. This should be fired when user changes
     * something in the settings panel and thus Apply button should be enabled
     * @param lsnr Action Listener
     */
    public void addApplyActionEnabledListener(ActionListener lsnr) {
        if (! this.applyActionEnabledListeners.contains(lsnr)) {
            this.applyActionEnabledListeners.add(lsnr);
        }
    }

    /**
     * This method should be called when user changes something in the Setting Panel. This will tell Independent Modeler
     * that Apply button should be enabled
     */
    public void fireApplyActionEnable() {
        for (ActionListener action : this.applyActionEnabledListeners) {
            action.actionPerformed(null);
        }
    }
}
