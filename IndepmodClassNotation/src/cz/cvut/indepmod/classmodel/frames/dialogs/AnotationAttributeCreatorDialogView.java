package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.util.GridBagConstraintsUtils;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Date: 12.2.2011
 * Time: 10:51:17
 * @author Lucky
 */
public class AnotationAttributeCreatorDialogView extends AbstractClassModelDialog {

    public static final String DIALOG_TITLE = Resources.getString("dialog_anotation_attribute_creator_title");
    public static final String ANOTATION_ATRIBUTE_NAME_LABEL = Resources.getString("dialog_anotation_attribute_creator_atribute_name");
    public static final String VALUE_LIST_LABEL = Resources.getString("dialog_anotation_attribute_creator_value_list");
    public static final String VALUE_NAME_LABEL = Resources.getString("dialog_anotation_attribute_creator_value_name");
    public static final String CREATE_BUTTON_NAME = Resources.getString("dialog_anotation_attribute_creator_create_button_name");
    public static final String ADD_VALUE_BUTTON_NAME = Resources.getString("dialog_anotation_attribute_creator_add_value_button_name");
    public static final String REMOVE_VALUE_BUTTON_NAME = Resources.getString("dialog_anotation_attribute_creator_remove_value_button_name");

    protected JLabel anotAtrNameLabel = new JLabel(ANOTATION_ATRIBUTE_NAME_LABEL);
    protected JLabel valueListLabel = new JLabel(VALUE_LIST_LABEL);
    protected JLabel valueNameLabel = new JLabel(VALUE_NAME_LABEL);
    protected JTextField anotAtrName = new JTextField();
    protected JTextField valueName = new JTextField();
    protected JButton createButton = new JButton(CREATE_BUTTON_NAME);
    protected JButton addValueButton = new JButton(ADD_VALUE_BUTTON_NAME);
    protected JButton removeValueButton = new JButton(REMOVE_VALUE_BUTTON_NAME);

    protected DefaultListModel valueListModel = new DefaultListModel();
    protected JList valueList = new JList(this.valueListModel);
    

    public AnotationAttributeCreatorDialogView(Frame owner) {
        super(owner, DIALOG_TITLE);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.anotAtrNameLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 2, 1);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.anotAtrName, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(valueListLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 2);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(this.valueList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);
        
        c = GridBagConstraintsUtils.createNewConstraints(2, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.removeValueButton, c);
        
        c = GridBagConstraintsUtils.createNewConstraints(0, 3, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(valueNameLabel, c);
        
        c = GridBagConstraintsUtils.createNewConstraints(1, 3, 1, 1);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.valueName, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 3, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.addValueButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 4, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.createButton, c);
    }

}
