package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.awt.event.ActionEvent;

/**
 * Date: 23.10.2010
 * Time: 12:43:07
 * @author Lucky
 */
public class ClassModelEditClassDialogRemoveMethod extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Remove attribute";
    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelEditClassDialogRemoveMethod(ClassModel model, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        MethodModel method = this.dialog.getSelectedMethod();
        if (method != null) {
            this.model.removeMethod(method);
            this.dialog.updateCell();
        }
    }

}
