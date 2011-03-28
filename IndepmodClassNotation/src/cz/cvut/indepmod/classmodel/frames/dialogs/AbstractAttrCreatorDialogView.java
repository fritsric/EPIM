package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.api.model.Visibility;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Date: 12.2.2011
 * Time: 16:18:40
 * @author Lucky
 */
public class AbstractAttrCreatorDialogView extends AbstractClassModelDialog {

    public static final String TITLE = Resources.getString("dialog_attribute_creator_title");

    public static final String ADD_ANOT_BUTTON_TITLE = Resources.getString("dialog_attribute_creator_add_anot");
    public static final String CREATE_BUTTON_TITLE = Resources.getString("dialog_attribute_creator_create");
    public static final String REM_ANOT_BUTTON_TITLE = Resources.getString("dialog_attribute_creator_rem_anot");
    public static final String ATTRIBUTE_TYPE_LABEL = Resources.getString("dialog_attribute_creator_attribute_type");
    public static final String ATTRIBUTE_NAME_LABEL = Resources.getString("dialog_attribute_creator_name");
    public static final String ATTRIBUTE_VISIBILITY_LABEL = Resources.getString("dialog_attribute_creator_visibility");
    public static final String ANOTATION_LIST_LABEL = Resources.getString("dialog_attribute_creator_anot_list");

    protected JLabel attributeTypeLabel = new JLabel(ATTRIBUTE_TYPE_LABEL);
    protected JLabel attributeNameLabel = new JLabel(ATTRIBUTE_NAME_LABEL);
    protected JLabel anotationListLabel = new JLabel(ANOTATION_LIST_LABEL);
    protected JLabel visibilityLabel = new JLabel(ATTRIBUTE_VISIBILITY_LABEL);
    protected JComboBox attributeType = new JComboBox();
    protected JComboBox attributeVisibility = new JComboBox();
    protected JTextField attributeName = new JTextField();
    protected JButton createButton = new JButton(CREATE_BUTTON_TITLE);
    protected JButton addAnotationButton = new JButton(ADD_ANOT_BUTTON_TITLE);
    protected JButton removeAnotationButton = new JButton(REM_ANOT_BUTTON_TITLE);
    
    protected JList anotationList = new JList();


    public AbstractAttrCreatorDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    public Object getSelectedAttributeType() {
        return this.attributeType.getSelectedItem();
    }

    public String getAttributeName() {
        return this.attributeName.getText();
    }

    public int getSelectedAnotationIndex() {
        return this.anotationList.getSelectedIndex();
    }

    public Visibility getSelectedVisibility() {
        return (Visibility) this.attributeVisibility.getSelectedItem();
    }

    protected JPanel initAttrNamePanel() {
        JPanel res = new JPanel(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.attributeNameLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        res.add(this.attributeName, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.attributeTypeLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        res.add(this.attributeType, c);

        return res;
    }

    protected JPanel initVisibilityPanel() {
        JPanel res = new JPanel(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        res.add(this.visibilityLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        res.add(this.attributeVisibility, c);

        return res;
    }

    protected JPanel initAnotationPanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new TitledBorder(ANOTATION_LIST_LABEL));
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

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        JPanel attrNamePanel = this.initAttrNamePanel();
        if (attrNamePanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 0, 2, 1);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            this.add(attrNamePanel, c);
        }

        JPanel visibilityPanel = this.initVisibilityPanel();
        if (visibilityPanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 1, 2, 1);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.5;
            this.add(visibilityPanel, c);
        }

        JPanel anotPanel = this.initAnotationPanel();
        if (anotPanel != null) {
            c = GridBagConstraintsUtils.createNewConstraints(0, 2, 2, 1);
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.5;
            c.weighty = 0.5;
            this.add(anotPanel, c);
        }
        
        c = GridBagConstraintsUtils.createNewConstraints(1, 3, 1, 1);
        c.anchor = GridBagConstraints.LINE_END;
        this.add(this.createButton, c);
    }

}
