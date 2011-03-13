package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import org.openide.windows.WindowManager;

/**
 * Date: 25.11.2010
 * Time: 18:00:07
 * @author Lucky
 */
public class ClassModelAnotationCreatorDialog extends ClassModelAnotationCreatorDialogView {

    private static final Logger LOG = Logger.getLogger(ClassModelAnotationCreatorDialog.class.getName());

    private AnotationModel returnValue;

    public ClassModelAnotationCreatorDialog(Frame owner) {
        this(owner, null);
    }

    public ClassModelAnotationCreatorDialog(Frame owner, AnotationModel returnValue) {
        super(owner);

        this.returnValue = returnValue;
        this.initAction();
        this.setSizes();
    }

    public AnotationModel getAnotation() {
        return this.returnValue;
    }

    private void initAction() {
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = anotationName.getText();
                //TODO - verify filled data!
                returnValue = new AnotationModel(name);

                Object[] atrList = valueListModel.toArray();
                for (int i = 0; i < atrList.length; i++) {
                    returnValue.addAttribute((AnotationAttributeModel) atrList[i]);
                }
                dispose();
            }
        });

        this.addValueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame window = WindowManager.getDefault().getMainWindow();
                AnotationAttributeModel atr = new ClassModelAnotationAttributeCreatorDialog(window).getReturnValue();

                if (atr != null) {
                    valueListModel.addElement(atr);
                    LOG.info("Added Anotation Attribute.");
                } else {
                    LOG.info("Anotation Attribute was not added.");
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
