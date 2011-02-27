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

package cz.cvut.promod.gui.dialogs.pluginsOverview;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import javax.swing.*;
import java.awt.*;

import com.jgoodies.forms.factories.Borders;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 16:38:25, 11.2.2010
 *
 * Implementation of the PluginsOverview dialog.
 */
public class PluginsOverviewDialogView extends JDialog {

    public static int HGAP = 10;
    public static int VGAP = 10;

    public static int LIST_WIDTH = 200;

    private static final Dimension DIMENSION = new Dimension(900, 600);

    protected JTabbedPane tabbedPane = ModelerSession.getComponentFactoryService().createTabbedPane(); 


    public PluginsOverviewDialogView(){
        setSize(DIMENSION);

        initLayout();
    }

    private void initLayout() {
        final JPanel panel = ModelerSession.getComponentFactoryService().createPanel();
        panel.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        panel.setLayout(new BorderLayout());
        panel.add(tabbedPane);
    }

}
