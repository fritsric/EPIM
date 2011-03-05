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

package cz.cvut.promod.epc.frames.graphOptions;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;
import cz.cvut.promod.epc.resources.Resources;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.Hashtable;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 0:29:49, 27.11.2009
 *
 * The View component of the GraphOptions dockable frame.
 */
public class GraphOptionsView extends JPanel {

    private final Logger LOG = Logger.getLogger(GraphOptionsModel.class);

    private final int GRID_SPINNER_COLUMNS = 2;
    private final int SCALE_SPINNER_COLUMNS = 3;

    private final JLabel movableBelowZeroLabel = ModelerSession.getComponentFactoryService().createLabel(
            Resources.getResources().getString(GraphOptionsModel.BELOW_ZERO_RES)
    );
    protected final JCheckBox movableBelowZeroCheckBox = ModelerSession.getComponentFactoryService().createCheckBox();

    private final JLabel gridLabel = ModelerSession.getComponentFactoryService().createLabel(
            Resources.getResources().getString(GraphOptionsModel.USE_GRID_RES)
    );
    protected final JCheckBox gridCheckBox = ModelerSession.getComponentFactoryService().createCheckBox();

    private final JLabel cellSizeLabel = ModelerSession.getComponentFactoryService().createLabel(
            Resources.getResources().getString(GraphOptionsModel.CELL_SIZE_RES)
    );
    protected JSpinner cellSizeSpinner = ModelerSession.getComponentFactoryService().createSpinner();

    private final JLabel viewGridLabel = ModelerSession.getComponentFactoryService().createLabel(
            Resources.getResources().getString(GraphOptionsModel.VIEW_GRID_RES)
    );
    protected final JCheckBox viewGridCheckBox = ModelerSession.getComponentFactoryService().createCheckBox();

    private final JLabel scaleLabel = ModelerSession.getComponentFactoryService().createLabel(
        Resources.getResources().getString(GraphOptionsModel.GRAPH_SCALE_RES)
    );
    protected final JSlider scaleSlider = ModelerSession.getComponentFactoryService().createSlider();
    protected final JSpinner scaleSpinner = ModelerSession.getComponentFactoryService().createSpinner();
    protected final JButton initialSizeButton = ModelerSession.getComponentFactoryService().createButton(
            Resources.getResources().getString(GraphOptionsModel.GRAPH_SCALE_100_RES), null);

    // this button takes statusUserTitleLabel from it's statusUserTitleLabel
    protected final JButton refreshButton = ModelerSession.getComponentFactoryService().createButton("", null);

    public GraphOptionsView(){
        initLayout();

        getSpinnerTextField(cellSizeSpinner).setColumns(GRID_SPINNER_COLUMNS);
        getSpinnerTextField(scaleSpinner).setColumns(SCALE_SPINNER_COLUMNS);

        initScaleSlider();       
    }

    private void initScaleSlider() {
        scaleSlider.setMinimum(GraphOptionsModel.MIN_SCALE);
        scaleSlider.setMaximum(GraphOptionsModel.MAX_SCALE);

        scaleSlider.setPaintLabels(true);
        scaleSlider.setMajorTickSpacing(100);        
        scaleSlider.setMinorTickSpacing(20);

        final Hashtable table = new Hashtable();
        table.put(new Integer(GraphOptionsModel.MIN_SCALE), ModelerSession.getComponentFactoryService().createLabel("min"));
        table.put(new Integer(100), ModelerSession.getComponentFactoryService().createLabel("100%"));
        table.put(new Integer(GraphOptionsModel.MAX_SCALE), ModelerSession.getComponentFactoryService().createLabel("max"));

        scaleSlider.setLabelTable(table);
    }

    private void initLayout() {
        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        final FormLayout layout = new FormLayout(
                /* columns */"pref, 3dlu, pref, pref:grow",
                /* rows */
                /* notation specific */
                    /* scale */" pref, 3dlu, " +
                    /* scale slider */ "pref, 3dlu," +
                    /* scale button */ "pref, 25dlu," +
                    /* grid */  "pref, 3dlu," +
                    /* grid */  "pref, 3dlu," +
                    /* grid */  "pref, 25dlu," +
                    /* anti aliasing */ "pref"        
            );

        final CellConstraints cellConstraints = new CellConstraints();
        final PanelBuilder builder = new PanelBuilder(layout);

        int row = 1;
        builder.add(scaleLabel, cellConstraints.xy(1, row));
        builder.add(scaleSpinner, cellConstraints.xy(3, row));

        row += 2;
        builder.add(scaleSlider, cellConstraints.xyw(1, row, layout.getColumnCount()));

        row += 2;
        builder.add(initialSizeButton, cellConstraints.xy(1, row));

        row += 2;
        builder.add(gridLabel, cellConstraints.xy(1, row));
        builder.add(gridCheckBox, cellConstraints.xy(3, row));

        row += 2;
        builder.add(viewGridLabel, cellConstraints.xy(1, row));
        builder.add(viewGridCheckBox, cellConstraints.xy(3, row));

        row += 2;
        builder.add(cellSizeLabel, cellConstraints.xy(1, row));
        builder.add(cellSizeSpinner, cellConstraints.xy(3, row));

        row += 2;
        builder.add(movableBelowZeroLabel, cellConstraints.xy(1, row));
        builder.add(movableBelowZeroCheckBox, cellConstraints.xy(3, row));


        setLayout(new BorderLayout());

        add(builder.getPanel(), BorderLayout.NORTH);
        add(refreshButton, BorderLayout.SOUTH);
    }

    public JFormattedTextField getSpinnerTextField(final JSpinner spinner) {
        final JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            LOG.error("Unexpected editor of JSpinner.");
            return null;
        }
    }

}