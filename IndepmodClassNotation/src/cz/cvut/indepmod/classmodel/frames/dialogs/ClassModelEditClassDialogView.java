package cz.cvut.indepmod.classmodel.frames.dialogs;


import cz.cvut.indepmod.classmodel.util.GridBagConstraintsUtils;
import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 7.10.2010
 * Time: 11:14:49
 * <p/>
 * This class represents a dialog view which will be used for editing of a class (class in a notation's diagram)
 */
//TODO - static string data should be loaded by resource bundle
public class ClassModelEditClassDialogView extends ClassModelAbstractDialog {

    public static final String TITLE = "Class Edit";
    public static final String NAME_LABEL = "Name";
    public static final String ANOT_LABEL = "Anotations";
    public static final String ATTRIBUTES_LABEL = "Attributes";
    public static final String METHODS_LABEL = "Methods";
    public static final String SAVE_BUTTON = "Edit";
    public static final String CANCEL_BUTTON = "Cancel";
    public static final String ADD_ATTRIBUTE_BUTTON = "Add Atribute";
    public static final String ADD_ANOT_BUTTON = "Add Anotation";
    public static final String ADD_METHOD_BUTTON = "Add Method";
    public static final String REMOVE_ATTRIBUTE_BUTTON = "Del Atribute";
    public static final String REMOVE_METHOD_BUTTON = "Del Method";
    public static final String REMOVE_ANOT_BUTTON = "Del Anotation";
    //public static final String EDIT_ATTRIBUTE_BUTTON = "Edit Attribute";

    protected JLabel classNameLabel = new JLabel(NAME_LABEL);
    protected JLabel anotationsLabel = new JLabel(ANOT_LABEL);
    protected JLabel attributesLabel = new JLabel(ATTRIBUTES_LABEL);
    protected JLabel methodsLabel = new JLabel(METHODS_LABEL);
    protected JTextField classNameField = new JTextField();
    protected JButton addAnotationButton = new JButton(ADD_ANOT_BUTTON);
    protected JButton removeAnotationButton = new JButton(REMOVE_ANOT_BUTTON);
    protected JButton addAttributeButton = new JButton(ADD_ATTRIBUTE_BUTTON);
    protected JButton removeAttributeButton = new JButton(REMOVE_ATTRIBUTE_BUTTON);
    //protected JButton editAttributeButton = new JButton(EDIT_ATTRIBUTE_BUTTON);
    protected JButton addMethodButton = new JButton(ADD_METHOD_BUTTON);
    protected JButton removeMethodButton = new JButton(REMOVE_METHOD_BUTTON);
    protected JButton saveButton = new JButton(SAVE_BUTTON);
    protected JButton cancelButton = new JButton(CANCEL_BUTTON);
    protected JList anotationList = new JList();
    protected JList attributeList = new JList();
    protected JList methodList = new JList();


    public ClassModelEditClassDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = null;

        c = GridBagConstraintsUtils.createNewConstraints(0, 0, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.classNameLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 0, 2, 1);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.classNameField, c);

        c = GridBagConstraintsUtils.createNewConstraints(0, 1, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.anotationsLabel, c);
        
        c = GridBagConstraintsUtils.createNewConstraints(1, 1, 1, 3);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(this.anotationList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 1, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.addAnotationButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 2, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.removeAnotationButton, c);

        
        c = GridBagConstraintsUtils.createNewConstraints(0, 4, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.attributesLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 4, 1, 3);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(this.attributeList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 4, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.addAttributeButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 5, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.removeAttributeButton, c);

        
        c = GridBagConstraintsUtils.createNewConstraints(0, 7, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.methodsLabel, c);

        c = GridBagConstraintsUtils.createNewConstraints(1, 7, 1, 3);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(this.methodList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 7, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.addMethodButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 8, 1, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.removeMethodButton, c);


        c = GridBagConstraintsUtils.createNewConstraints(1, 10, 1, 1);
        c.anchor = GridBagConstraints.LINE_END;
        this.add(this.saveButton, c);

        c = GridBagConstraintsUtils.createNewConstraints(2, 10, 1, 1);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(this.cancelButton, c);
    }
}
