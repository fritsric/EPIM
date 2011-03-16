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

package cz.cvut.promod.gui.settings.utils;

import com.jidesoft.dialog.AbstractDialogPage;

import javax.swing.*;
import java.awt.*;

import cz.cvut.promod.services.ModelerSession;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 16:32:45, 24.1.2010
 *
 *
 * Basic settings of the host application for the settings dialog.
 */
public class BasicSettingPage extends AbstractDialogPage {

    private static final String SETTINGS_LABEL = ModelerSession.getCommonResourceBundle().getString("modeler.settings");

    private final JLabel label = ModelerSession.getComponentFactoryService().createLabel("");


    public BasicSettingPage(final String name) {
        super(name);
    }

    public BasicSettingPage(final String name, final Icon icon) {
        super(name, icon);
    }

    public void lazyInitialize() {
        label.setText("[" + getName() + "] " + SETTINGS_LABEL);
        
        initLayout();
    }

    public void initLayout() {
        setLayout(new BorderLayout());

        label.setHorizontalAlignment(JLabel.CENTER);

        add(label, BorderLayout.CENTER);
    }

}
