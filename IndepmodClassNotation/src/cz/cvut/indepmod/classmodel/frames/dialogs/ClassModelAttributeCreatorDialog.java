package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import org.openide.windows.WindowManager;

/**
 * Date: 17.10.2010
 * Time: 14:32:19
 * @author Lucky
 */
public class ClassModelAttributeCreatorDialog extends ClassModelAttributeCreatorDialogView {
    private Collection<TypeModel> availableTypes;
    private AttributeModel returnValue;

    public ClassModelAttributeCreatorDialog(Frame owner) {
        this(owner, new ArrayList<TypeModel>(0));
    }

    public ClassModelAttributeCreatorDialog(Frame owner, Collection<TypeModel> types) {
        super(owner);

        this.availableTypes = types;
        this.returnValue = null;

        this.initValues();
        this.initAction();
        this.setSizes();
    }

    public AttributeModel getAttribute() {
        return this.returnValue;
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TypeModel dateType = (TypeModel) attributeType.getSelectedItem();
                String name = attributeName.getText();

                //TODO - verify filled data!
                returnValue = new AttributeModel(dateType, name);

                Object[] objs = anotationListModel.toArray();
                for (int i = 0; i < objs.length; i++) {
                    returnValue.addAnotation((AnotationModel) objs[i]);
                }
                dispose();
            }
        });

        this.addAnotationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame window = WindowManager.getDefault().getMainWindow();
                AnotationModel anot = new ClassModelAnotationCreatorDialog(window).getAnotation();

                if (anot != null) {
                    anotationListModel.addElement(anot);
                }
            }
        });

        this.removeAnotationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = anotationList.getSelectedIndex();
                if (index != -1) {
                    anotationListModel.remove(index);
                }
            }
        });
    }

    private void initValues() {
        this.attributeType.removeAllItems();
        for (TypeModel type : this.availableTypes) {
            this.attributeType.addItem(type);
        }
//        if (this.returnValue != null) {
//            this.attributeName.setText(this.returnValue.getName());
//            this.attributeType.setSelectedItem(this.returnValue.getType());
//
//            this.attributeName.setEnabled(false);
//            this.attributeType.setEnabled(false);
//        }
    }
}
