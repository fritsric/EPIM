package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.util.GridBagConstraintsUtils;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Date: 12.2.2011
 * Time: 16:41:13
 * @author Lucky
 */
public class MethodCreatorDialogView extends AbstractClassModelDialog {

    public static final String TITLE = Resources.getString("dialog_method_creator_title");

    public static final String ADD_ATTR_BUTTON = Resources.getString("dialog_method_creator_add_attr");
    public static final String CANCEL_BUTTON = Resources.getString("dialog_method_creator_cancel");
    public static final String LABEL_ATTRIBUTE = Resources.getString("dialog_method_creator_attr_list");
    public static final String LABEL_NAME = Resources.getString("dialog_method_creator_name");
    public static final String LABEL_TYPE = Resources.getString("dialog_method_creator_type");
    public static final String LABEL_VISIBILITY = Resources.getString("dialog_method_creator_visibility");
    public static final String REM_ATTR_BUTTON = Resources.getString("dialog_method_creator_rem_attr");
    public static final String SAVE_BUTTON = Resources.getString("dialog_method_creator_save");

    protected JLabel nameLabel = new JLabel(LABEL_NAME);
    protected JLabel typeLabel = new JLabel(LABEL_TYPE);
    protected JLabel visibilityLabel = new JLabel(LABEL_VISIBILITY);
    protected JLabel attrLabel = new JLabel(LABEL_ATTRIBUTE);
    protected JTextField nameField = new JTextField();
    protected JComboBox typeBox = new JComboBox();
    protected JComboBox visibilityBox = new JComboBox();
    protected JButton saveButton = new JButton(SAVE_BUTTON);
    protected JButton cancelButton = new JButton(CANCEL_BUTTON);
    protected JButton addAttrButton = new JButton(ADD_ATTR_BUTTON);
    protected JButton remAttrButton = new JButton(REM_ATTR_BUTTON);
    protected DefaultListModel attributeListModel = new DefaultListModel();
    protected JList attrList = new JList(this.attributeListModel);


    public MethodCreatorDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.nameLabel,c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 2, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        this.add(this.nameField, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.typeLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 2, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        this.add(this.typeBox, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 2, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.visibilityLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 2, 2, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        this.add(this.visibilityBox, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 3, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.attrLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 3, 1, 3);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        this.add(new JScrollPane(this.attrList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 3, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.addAttrButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 4, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.remAttrButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 6, 1, 1);
        c.anchor = GridBagConstraints.LINE_END;
        this.add(this.saveButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 6, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.cancelButton, c);
    }
}
