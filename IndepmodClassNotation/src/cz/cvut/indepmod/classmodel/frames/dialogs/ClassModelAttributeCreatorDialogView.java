package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.util.GridBagConstraintsUtils;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Date: 12.2.2011
 * Time: 16:18:40
 * @author Lucky
 */
public class ClassModelAttributeCreatorDialogView extends ClassModelAbstractDialog {

    public static final String TITLE = "Create Attribute Dialog";

    public static final String ADD_ANOT_BUTTON_TITLE = "Add Anotation";
    public static final String CREATE_BUTTON_TITLE = "Create";
    public static final String REM_ANOT_BUTTON_TITLE = "Rem Anotation";
    public static final String ATTRIBUTE_TYPE_LABEL = "Data Type: ";
    public static final String ATTRIBUTE_NAME_LABEL = "Name: ";
    public static final String ANOTATION_LIST_LABEL = "Anotations: ";

    protected JLabel attributeTypeLabel = new JLabel(ATTRIBUTE_TYPE_LABEL);
    protected JLabel attributeNameLabel = new JLabel(ATTRIBUTE_NAME_LABEL);
    protected JLabel anotationListLabel = new JLabel(ANOTATION_LIST_LABEL);
    protected JComboBox attributeType = new JComboBox();
    protected JTextField attributeName = new JTextField();
    protected JButton createButton = new JButton(CREATE_BUTTON_TITLE);
    protected JButton addAnotationButton = new JButton(ADD_ANOT_BUTTON_TITLE);
    protected JButton removeAnotationButton = new JButton(REM_ANOT_BUTTON_TITLE);
    protected DefaultListModel anotationListModel = new DefaultListModel();
    protected JList anotationList = new JList(anotationListModel);


    public ClassModelAttributeCreatorDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(attributeNameLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 2, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        this.add(this.attributeName, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.attributeTypeLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 2, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        this.add(this.attributeType, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 2, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.anotationListLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 2, 1, 3);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        this.add(new JScrollPane(this.anotationList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 2, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.addAnotationButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 3, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.removeAnotationButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 5, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.createButton, c);
    }

}
