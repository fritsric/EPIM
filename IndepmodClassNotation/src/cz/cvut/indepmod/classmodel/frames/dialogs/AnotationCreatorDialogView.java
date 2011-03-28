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
 * Time: 10:35:00
 * @author Lucky
 */
public class AnotationCreatorDialogView extends AbstractClassModelDialog {
    
    public static final String TITLE = Resources.getString("dialog_anotation_creator_title");

    public static final String ANOTATION_NAME_LABEL = Resources.getString("dialog_anotation_creator_anotation_name");
    public static final String CREATE_BUTTON_TITLE = Resources.getString("dialog_anotation_creator_create_button");
    public static final String VALUE_LIST_LABEL_TITLE = Resources.getString("dialog_anotation_creator_value_list");
    public static final String ADD_VALUE_NAME = Resources.getString("dialog_anotation_creator_add_value");
    public static final String REMOVE_VALUE_NAME = Resources.getString("dialog_anotation_creator_remove_value");

    protected JLabel anotationNameLabel = new JLabel(ANOTATION_NAME_LABEL);
    protected JTextField anotationName = new JTextField();
    protected JLabel valueListLabel = new JLabel(VALUE_LIST_LABEL_TITLE);
    protected JButton createButton = new JButton(CREATE_BUTTON_TITLE);
    protected JButton addValueButton = new JButton(ADD_VALUE_NAME);
    protected JButton removeValueButton = new JButton(REMOVE_VALUE_NAME);
    
    protected DefaultListModel valueListModel = new DefaultListModel();
    protected JList valueList = new JList(valueListModel);

     public AnotationCreatorDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.anotationNameLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 2, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        this.add(this.anotationName, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.valueListLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 3);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        this.add(new JScrollPane(this.valueList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.addValueButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 2, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.removeValueButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 4, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.createButton, c);
    }

}
