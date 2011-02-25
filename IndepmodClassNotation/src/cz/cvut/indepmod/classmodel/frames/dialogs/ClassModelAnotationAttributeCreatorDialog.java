package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Date: 4.12.2010
 * Time: 17:33:28
 * @author Lucky
 */
public class ClassModelAnotationAttributeCreatorDialog extends ClassModelAnotationAttributeCreatorDialogView {

    private AnotationAttributeModel returnValue;

    public ClassModelAnotationAttributeCreatorDialog(Frame owner) {
        super(owner);

        this.returnValue = null;
        this.initAction();
        this.setSizes();
    }

    public AnotationAttributeModel getReturnValue() {
        return returnValue;
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = anotAtrName.getText();
                //TODO - verify filled data
                returnValue = new AnotationAttributeModel(name);

                Object[] objs = valueListModel.toArray();
                for (int i = 0; i < objs.length; i++) {
                    returnValue.addValue((String) objs[i]);
                }

                dispose();
            }
        });

        this.addValueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = valueName.getText();

                if (! name.isEmpty()) {
                    valueListModel.addElement(name);
                }
            }
        });

        this.removeValueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = valueList.getSelectedIndex();
                if (index != -1) {
                    valueListModel.remove(index);
                }
            }
        });
    }


}
