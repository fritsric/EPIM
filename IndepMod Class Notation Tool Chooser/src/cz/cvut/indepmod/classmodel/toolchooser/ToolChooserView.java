package cz.cvut.indepmod.classmodel.toolchooser;

import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import java.util.logging.Level;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;
import org.openide.windows.TopComponent;

/**
 * This class represents the view of the tool chooser. Tool Chooser will be used as a Dockable Frame. User will choose
 * an action that he/she wants (e.g. add Class) by the ToolChooser
 */
public class ToolChooserView extends TopComponent {

    public static final String LABEL_CONTROLL_PANEL = "Controll";
    public static final String LABEL_CLASS_PANEL = "Class";
    public static final String LABEL_RELATION_PANEL = "Relations";
    

    private static final Logger LOG = Logger.getLogger(ToolChooserView.class.getName());
    
    private final Icon CONTROL_ICON = createImageIcon("resource/control.gif");
    private final Icon CLASS_ICON = createImageIcon("resource/class.gif");
    private final Icon INTERFACE_ICON = createImageIcon("resource/interface.gif");
    private final Icon ENUMERATION_ICON = createImageIcon("resource/enum.gif");
    private final Icon RELATION_ICON = createImageIcon("resource/relation.gif");
    private final Icon GENERALIZATION_ICON = createImageIcon("resource/generalization.gif");
    private final Icon REALIZATION_ICON = createImageIcon("resource/realization.gif");
    private final Icon AGREGATION_ICON = createImageIcon("resource/agregation.gif");
    private final Icon COMPOSITION_ICON = createImageIcon("resource/composition.gif");

    private ButtonGroup buttonGroup = new ButtonGroup();
    protected JToggleButton controllButton = new JToggleButton(ToolChooserModel.TOOL_CONTROL_NAME, CONTROL_ICON);
    protected JToggleButton addClassButton = new JToggleButton(ToolChooserModel.TOOL_ADD_CLASS_NAME, CLASS_ICON);
    protected JToggleButton addInterfaceButton = new JToggleButton(ToolChooserModel.TOOL_ADD_INTERFACE_NAME, INTERFACE_ICON);
    protected JToggleButton addEnumerationButton = new JToggleButton(ToolChooserModel.TOOL_ADD_ENUMERATION_NAME, ENUMERATION_ICON);
    protected JToggleButton addRelationButton = new JToggleButton(RELATION_ICON);
    protected JToggleButton addGeneralizationButton = new JToggleButton(GENERALIZATION_ICON);
    protected JToggleButton addRealisationButton = new JToggleButton(REALIZATION_ICON);
    protected JToggleButton addCompositionButton = new JToggleButton(COMPOSITION_ICON);
    protected JToggleButton addAgregationButton = new JToggleButton(AGREGATION_ICON);

    public ToolChooserView() {
        this.initButtonGroup();
        this.initLayout();
    }

    public void disableToolChooser() {
        this.controllButton.setEnabled(false);
        this.addClassButton.setEnabled(false);
        this.addInterfaceButton.setEnabled(false);
        this.addEnumerationButton.setEnabled(false);
        this.addRelationButton.setEnabled(false);
        this.addGeneralizationButton.setEnabled(false);
        this.addRealisationButton.setEnabled(false);
        this.addCompositionButton.setEnabled(false);
        this.addAgregationButton.setEnabled(false);
    }

    public void enableToolChooser() {
        this.controllButton.setEnabled(true);
        this.addClassButton.setEnabled(true);
        this.addInterfaceButton.setEnabled(true);
        this.addEnumerationButton.setEnabled(true);
        this.addRelationButton.setEnabled(true);
        this.addGeneralizationButton.setEnabled(true);
        this.addRealisationButton.setEnabled(true);
        this.addCompositionButton.setEnabled(true);
        this.addAgregationButton.setEnabled(true);
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 10, 0);
        this.add(this.initControllPanel(), c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 10, 0);
        this.add(this.initClassPanel(), c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.initRelationPanel(), c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        this.add(new JPanel(), c);
    }

    private JPanel initClassPanel() {
        JPanel res = new JPanel();
        res.setBorder(new LineBorder(Color.DARK_GRAY));
        res.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        res.add(new JLabel(LABEL_CLASS_PANEL), c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 3, 0, 0);
        res.add(this.addClassButton, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        c.insets = new Insets(0, 3, 0, 0);
        res.add(this.addInterfaceButton, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(0, 3, 0, 0);
        res.add(this.addEnumerationButton, c);

        return res;
    }

    private JPanel initControllPanel() {
        JPanel res = new JPanel();
        res.setBorder(new LineBorder(Color.DARK_GRAY));
        res.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        res.add(new JLabel(LABEL_CONTROLL_PANEL), c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.insets = new Insets(0, 3, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.controllButton, c);

        return res;
    }

    private JPanel initRelationPanel() {
        JPanel res = new JPanel();
        res.setBorder(new LineBorder(Color.DARK_GRAY));
        res.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 0.5;
        res.add(new JLabel(LABEL_RELATION_PANEL), c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 3, 0, 0);
        res.add(this.addRelationButton, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = 0.5;
        c.gridy = 1;
        res.add(this.addGeneralizationButton, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(0, 0, 0, 3);
        res.add(this.addRealisationButton, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 3, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.addCompositionButton, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 3);
        c.anchor = GridBagConstraints.LINE_END;
        res.add(this.addAgregationButton, c);

        return res;
    }

    private void initButtonGroup() {
        this.buttonGroup.add(this.controllButton);
        this.buttonGroup.add(this.addClassButton);
        this.buttonGroup.add(this.addInterfaceButton);
        this.buttonGroup.add(this.addEnumerationButton);
        this.buttonGroup.add(this.addRelationButton);
        this.buttonGroup.add(this.addGeneralizationButton);
        this.buttonGroup.add(this.addRealisationButton);
        this.buttonGroup.add(this.addCompositionButton);
        this.buttonGroup.add(this.addAgregationButton);

        this.addRelationButton.setToolTipText(ToolChooserModel.TOOL_ADD_RELATION);
        this.addGeneralizationButton.setToolTipText(ToolChooserModel.TOOL_ADD_GENERALIZATION);
        this.addRealisationButton.setToolTipText(ToolChooserModel.TOOL_ADD_REALISATION);
        this.addAgregationButton.setToolTipText(ToolChooserModel.TOOL_ADD_AGREGATION);
        this.addCompositionButton.setToolTipText(ToolChooserModel.TOOL_ADD_COMPOSITION);
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            LOG.log(Level.SEVERE, "Couldn''t find file: {0}", path);
            return null;
        }
    }
}
