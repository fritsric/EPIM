package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.util.GridBagConstraintsUtils;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Date: 5.3.2011
 * Time: 13:42:05
 * @author Lucky
 */
public abstract class AbstractEditElementDialogView extends AbstractClassModelDialog {

    public static final String TITLE = Resources.getString("dialog_edit_class_title");
    public static final String NAME_LABEL = Resources.getString("dialog_edit_class_name");
    public static final String STEREOTYPE_LABEL = Resources.getString("dialog_edit_class_stereotype");
    public static final String ABSTRACT_LABEL = Resources.getString("dialog_edit_class_abstract");
    public static final String ANOT_LABEL = Resources.getString("dialog_edit_class_anot_list");
    public static final String PROPERTY_LABEL = Resources.getString("dialog_edit_class_property_list");
    public static final String ATTRIBUTES_LABEL = Resources.getString("dialog_edit_class_attribute_list");
    public static final String METHODS_LABEL = Resources.getString("dialog_edit_class_method_list");
    public static final String SAVE_BUTTON = Resources.getString("dialog_edit_class_save");
    public static final String CANCEL_BUTTON = Resources.getString("dialog_edit_class_cancel");
    public static final String ADD_ATTRIBUTE_BUTTON = Resources.getString("dialog_edit_class_add_attribute");
    public static final String ADD_ANOT_BUTTON = Resources.getString("dialog_edit_class_add_anotation");
    public static final String ADD_METHOD_BUTTON = Resources.getString("dialog_edit_class_add_method");
    public static final String REMOVE_ATTRIBUTE_BUTTON = Resources.getString("dialog_edit_class_rem_attribute");
    public static final String REMOVE_METHOD_BUTTON = Resources.getString("dialog_edit_class_rem_method");
    public static final String REMOVE_ANOT_BUTTON = Resources.getString("dialog_edit_class_rem_anotation");
    protected JLabel stereotypeLabel = new JLabel(STEREOTYPE_LABEL);
    protected JComboBox stereotypeField = new JComboBox();
    protected JLabel classNameLabel = new JLabel(NAME_LABEL);
    protected JTextField classNameField = new JTextField();
    protected JCheckBox abstractCheckBox = new JCheckBox(ABSTRACT_LABEL);
    protected JButton addAnotationButton = new JButton(ADD_ANOT_BUTTON);
    protected JButton removeAnotationButton = new JButton(REMOVE_ANOT_BUTTON);
    protected JButton addAttributeButton = new JButton(ADD_ATTRIBUTE_BUTTON);
    protected JButton removeAttributeButton = new JButton(REMOVE_ATTRIBUTE_BUTTON);
    protected JButton addMethodButton = new JButton(ADD_METHOD_BUTTON);
    protected JButton removeMethodButton = new JButton(REMOVE_METHOD_BUTTON);
    protected JButton saveButton = new JButton(SAVE_BUTTON);
    protected JButton cancelButton = new JButton(CANCEL_BUTTON);
    protected JList anotationList = new JList();
    protected JList attributeList = new JList();
    protected JList methodList = new JList();

    public AbstractEditElementDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    protected JPanel initClassNamePanel() {
        JPanel res = new JPanel(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.classNameLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        res.add(this.classNameField, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.stereotypeLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        res.add(this.stereotypeField, c);

        return res;
    }

    protected JPanel initPropertyPanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new TitledBorder(PROPERTY_LABEL));
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.abstractCheckBox, c);

        return res;
    }

    protected JPanel initAnotationPanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new TitledBorder(ANOT_LABEL));
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 3);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        res.add(new JScrollPane(this.anotationList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        res.add(this.addAnotationButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        res.add(this.removeAnotationButton, c);

        return res;
    }

    protected JPanel initAttributePanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new TitledBorder(ATTRIBUTES_LABEL));
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 3);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        res.add(new JScrollPane(this.attributeList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        res.add(this.addAttributeButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        res.add(this.removeAttributeButton, c);

        return res;
    }

    protected JPanel initMethodPanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new TitledBorder(METHODS_LABEL));
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 3);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        res.add(new JScrollPane(this.methodList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        res.add(this.addMethodButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        res.add(this.removeMethodButton, c);

        return res;
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        JPanel classNamePanel = this.initClassNamePanel();
        if (classNamePanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 0, 2, 1);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            this.add(classNamePanel, c);
        }

        JPanel propertyPanel = this.initPropertyPanel();
        if (propertyPanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 1, 2, 1);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            this.add(propertyPanel, c);
        }

        JPanel anotPanel = this.initAnotationPanel();
        if (anotPanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 2, 2, 1);
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.5;
            c.weighty = 0.5;
            this.add(anotPanel, c);
        }

        JPanel attrPanel = this.initAttributePanel();
        if (attrPanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 3, 2, 1);
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.5;
            c.weighty = 0.5;
            this.add(attrPanel, c);
        }

        JPanel methodPanel = this.initMethodPanel();
        if (methodPanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 4, 2, 1);
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.5;
            c.weighty = 0.5;
            this.add(methodPanel, c);
        }

        c = GridBagConstraintsUtils.createNewConstraints(0, 5, 1, 1);
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        this.add(this.saveButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 5, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        this.add(this.cancelButton, c);
    }
}
