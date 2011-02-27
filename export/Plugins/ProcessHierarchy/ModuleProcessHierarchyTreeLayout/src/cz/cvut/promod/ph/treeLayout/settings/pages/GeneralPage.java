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

package cz.cvut.promod.ph.treeLayout.settings.pages;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import cz.cvut.promod.gui.settings.SettingPagePanel;
import cz.cvut.promod.ph.treeLayout.settings.TreeLayoutSettings;
import cz.cvut.promod.ph.treeLayout.settings.TreeLayoutSettingsModel;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:46:10, 28.1.2010
 *
 * GeneralPage represents the common settings dialog page defined by ProcessHierarchyTreeLayout plugin. 
 */
public class GeneralPage extends SettingPagePanel{

    private final JRadioButton topButton = ModelerSession.getComponentFactoryService().createRadioButton("top");
    private final JRadioButton centerButton = ModelerSession.getComponentFactoryService().createRadioButton("center");
    private final JRadioButton bottomButton = ModelerSession.getComponentFactoryService().createRadioButton("bottom");

    private AbstractAction applyAction;
    private AbstractAction cancelAction;

    public GeneralPage(){
        //super("Process Hierarchy Tree Layout");
        initEventHandling();
    }

    private void initEventHandling() {
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(topButton);
        buttonGroup.add(centerButton);
        buttonGroup.add(bottomButton);

        this.applyAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(topButton.isSelected()){
                    TreeLayoutSettings.getInstance().setVerticalLayout(TreeLayoutSettingsModel.VerticalLayout.TOP);

                } else if(centerButton.isSelected()) {
                    TreeLayoutSettings.getInstance().setVerticalLayout(TreeLayoutSettingsModel.VerticalLayout.CENTER);

                } else if(bottomButton.isSelected()){
                    TreeLayoutSettings.getInstance().setVerticalLayout(TreeLayoutSettingsModel.VerticalLayout.BOTTOM);
                }
            }
        };

        this.cancelAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                init();
            }
        };

        topButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                fireApplyActionEnable();
            }
        });

        centerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                fireApplyActionEnable();
            }
        });

        bottomButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                fireApplyActionEnable();
            }
        });
    }

    private void init() {
        switch (TreeLayoutSettings.getInstance().getVerticalLayout()){
            case TOP:
                topButton.setSelected(true);
                break;
            case BOTTOM:
                bottomButton.setSelected(true);
                break;
            case CENTER:
                centerButton.setSelected(true);
                break;
        }
    }

    public void lazyInitialize() {
        initLayout();
        init();
    }

    @Override
    public AbstractAction getApplyAction() {
        return this.applyAction;
    }

    @Override
    public AbstractAction getCancelAction() {
        return this.cancelAction;
    }

    @Override
    public AbstractAction getOkAction() {
        return this.applyAction;
    }

    private void initLayout() {
        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        final FormLayout layout = new FormLayout(
                "pref",
                "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref"
        );

        final CellConstraints cellConstraints = new CellConstraints();

        final PanelBuilder panelBuilder = new PanelBuilder(layout);

        int row = 1;
        panelBuilder.addSeparator("Process vertical layout", cellConstraints.xy(1, row));

        row += 2;
        panelBuilder.add(topButton, cellConstraints.xy(1,row));

        row += 2;
        panelBuilder.add(centerButton, cellConstraints.xy(1,row));

        row += 2;
        panelBuilder.add(bottomButton, cellConstraints.xy(1,row));

        setLayout(new BorderLayout());
        add(panelBuilder.getPanel(), BorderLayout.CENTER);
    }
}
