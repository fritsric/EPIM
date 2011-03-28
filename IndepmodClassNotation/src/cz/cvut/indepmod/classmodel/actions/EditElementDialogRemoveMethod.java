package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditElementDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.awt.event.ActionEvent;

/**
 * Date: 23.10.2010
 * Time: 12:43:07
 * @author Lucky
 */
public class EditElementDialogRemoveMethod extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_rem_method");
    private AbstractElementModel model;
    private AbstractEditElementDialog dialog;

    public EditElementDialogRemoveMethod(AbstractElementModel model, AbstractEditElementDialog dialog) {
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
