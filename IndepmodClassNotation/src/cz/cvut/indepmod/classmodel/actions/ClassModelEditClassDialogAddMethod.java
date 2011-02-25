package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelAttributeCreatorDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelMethodCreatorDialog;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import org.openide.windows.WindowManager;

/**
 * Date: 23.10.2010
 * Time: 12:40:57
 * @author Lucky
 */
public class ClassModelEditClassDialogAddMethod extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Add method";

    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelEditClassDialogAddMethod(ClassModel model, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        MethodModel method = new ClassModelMethodCreatorDialog(
                window,
                this.dialog.getAllTypeModel()).getMethod();

        if (method != null) {
            this.model.addMethod(method);
            this.dialog.updateCell();
        }
    }

}
