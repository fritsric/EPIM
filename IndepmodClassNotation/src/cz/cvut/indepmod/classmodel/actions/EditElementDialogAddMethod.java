package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditElementDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.MethodCreatorDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import org.openide.windows.WindowManager;

/**
 * Date: 23.10.2010
 * Time: 12:40:57
 * @author Lucky
 */
public class EditElementDialogAddMethod extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_add_method");

    private AbstractElementModel model;
    private AbstractEditElementDialog dialog;

    public EditElementDialogAddMethod(AbstractElementModel model, AbstractEditElementDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        MethodModel method = new MethodCreatorDialog(
                window,
                this.dialog.getAllTypeModel()).getReturnValue();

        if (method != null) {
            this.model.addMethod(method);
            this.dialog.updateCell();
        }
    }

}
