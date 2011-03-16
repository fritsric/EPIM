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

package cz.cvut.promod.epcImageExport.settings;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.SpinnerAdapterFactory;
import com.jgoodies.forms.factories.Borders;
import cz.cvut.promod.epcImageExport.frames.imageExport.ImageExportModel;
import cz.cvut.promod.epcImageExport.resources.Resources;
import cz.cvut.promod.gui.settings.SettingPagePanel;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:21:09, 24.1.2010
 *
 * The common dialog setting page of the EPCImageExportModule plugin. 
 */
public class ImageExportSettings extends SettingPagePanel {

    private static final String INSET_LABEL = Resources.getResources().getString("epc.imageexport.settings.inset");

    private final JLabel titleLabel = ModelerSession.getComponentFactoryService().createLabel(INSET_LABEL);

    protected JSpinner insetSpinner = ModelerSession.getComponentFactoryService().createSpinner();

    private final int H_GAP = 0;
    private final int V_GAP = 20;

    private final SpinnerNumberModel insetSpinnerModel;

    private final PresentationModel<ImageExportModel> presentation;

    private final ApplyAction applyAction = new ApplyAction();
    private final CancelAction cancelAction = new CancelAction();


    public ImageExportSettings(final PresentationModel<ImageExportModel> presentation) {
        //super(INSET_LABEL);

        this.presentation = presentation;

        insetSpinnerModel =
            SpinnerAdapterFactory.createNumberAdapter(
                    presentation.getBufferedModel(ImageExportModel.PROPERTY_INSET),
                    ImageExportModel.INIT_INSET,
                    ImageExportModel.MIN_INSET,
                    ImageExportModel.MAX_INSET,
                    ImageExportModel.INIT_SPINNER_STEP);
    }


    public void lazyInitialize() {
        initLayout();

        insetSpinner.setModel(insetSpinnerModel);

        initEventHandling();        
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

    private void initEventHandling() {
        insetSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                //fireButtonEvent(ButtonEvent.ENABLE_BUTTON, ButtonNames.APPLY);
                fireApplyActionEnable();
            }
        });
    }

    private void initLayout() {
        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        setLayout(new  FlowLayout(FlowLayout.LEFT, H_GAP, V_GAP));

        titleLabel.setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));
        
        add(titleLabel);
        add(insetSpinner);
    }

    /**
     * Commits the Trigger used to buffer the editor contents.
     */
    private final class ApplyAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            presentation.triggerCommit();
        }

    }


    /**
     * Flushed the Trigger used to buffer the editor contents.
     */
    private final class CancelAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            presentation.triggerFlush();
        }

    }

}
