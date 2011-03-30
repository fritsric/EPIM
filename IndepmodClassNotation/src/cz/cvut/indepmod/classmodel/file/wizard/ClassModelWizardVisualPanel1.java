package cz.cvut.indepmod.classmodel.file.wizard;

import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.util.GridBagConstraintsUtils;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Date: 19.2.2011
 * Time: 17:59:15
 * @author Lucky
 */
public class ClassModelWizardVisualPanel1 extends JPanel {

    public static final String NAME_LABEL_TEXT = Resources.getString("new_file_wizard_panel1_name_label");
    public static final String TYPE_LABEL_TEXT = Resources.getString("new_file_wizard_panel1_model_type_label");
    public static final String CLASS_MODEL_RADIO_TEXT = Resources.getString("new_file_wizard_panel1_class_model_label");
    public static final String BUSINESS_MODEL_RADIO_TEXT = Resources.getString("new_file_wizard_panel1_business_model_label");

    private JLabel nameLabel = new JLabel(NAME_LABEL_TEXT);
    private JLabel typeLabel = new JLabel(TYPE_LABEL_TEXT);
    private JTextField nameField = new JTextField();

    private JRadioButton classModelRadio = new JRadioButton(CLASS_MODEL_RADIO_TEXT);
    private JRadioButton businessModelRadio = new JRadioButton(BUSINESS_MODEL_RADIO_TEXT);
    private ButtonGroup radioButtonGroup = new ButtonGroup();

    public ClassModelWizardVisualPanel1() {
        this.initLayout();
    }

    public String getFileName() {
        return this.nameField.getText();
    }

    public void setFileName(String fileName) {
        this.nameField.setText(fileName);
    }

    public boolean isClassModelSelected() {
        return this.classModelRadio.isSelected();
    }

    public boolean isBusinessModelSelected() {
        return this.businessModelRadio.isSelected();
    }

    public void setClassModelSelected(boolean selected) {
        this.classModelRadio.setSelected(selected);
    }

    public void setBusinessModelSelected(boolean selected) {
        this.businessModelRadio.setSelected(selected);
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        this.radioButtonGroup.add(this.classModelRadio);
        this.radioButtonGroup.add(this.businessModelRadio);

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.nameLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        this.add(this.nameField, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.typeLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 1);
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.classModelRadio, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 2, 1, 1);
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.businessModelRadio, c);
    }

}
