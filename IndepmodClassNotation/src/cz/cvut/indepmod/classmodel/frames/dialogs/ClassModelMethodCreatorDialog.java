package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.openide.windows.WindowManager;

/**
 * Date: 23.10.2010
 * Time: 13:23:01
 * @author Lucky
 */
public class ClassModelMethodCreatorDialog extends ClassModelMethodCreatorDialogView {
    private MethodModel returnValue = null;
    private Collection<TypeModel> availableTypes;
    

    public ClassModelMethodCreatorDialog(Frame owner, Collection<TypeModel> types) {
        super(owner);

        this.availableTypes = types;

        this.initValues();
        this.initAction();
        this.setSizes();
    }

    public MethodModel getMethod() {
        return this.returnValue;
    }

    private void initAction() {
        this.saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                TypeModel returnType = (TypeModel) typeBox.getSelectedItem();

                if (name.matches("^[A-Za-z][0-9A-Za-z]*$")) {
                    Set<IAttribute> attrs = new HashSet<IAttribute>();
                    int size = attributeListModel.size();
                    for (int i = 0; i < size; i++) {
                        AttributeModel a = (AttributeModel) attributeListModel.get(i);
                        attrs.add(a);
                    }

                    returnValue = new MethodModel(returnType, name, attrs);
                    dispose();
                }
            }
        });

        this.addAttrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame window = WindowManager.getDefault().getMainWindow();
                AttributeModel attr = new ClassModelAttributeCreatorDialog(
                        window,
                        availableTypes).getAttribute();

                if (attr != null) {
                    attributeListModel.addElement(attr);
                }
            }
        });

        this.remAttrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AttributeModel attr = (AttributeModel)attrList.getSelectedValue();
                if (attr != null) {
                    attributeListModel.removeElement(attr);
                }
            }
        });

        this.cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initValues() {
        this.typeBox.removeAllItems();
        for (TypeModel type : this.availableTypes) {
            this.typeBox.addItem(type);
        }
    }
}
