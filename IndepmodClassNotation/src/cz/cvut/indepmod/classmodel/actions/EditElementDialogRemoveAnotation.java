package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditElementDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import java.awt.event.ActionEvent;

/**
 * Date: 25.11.2010
 * Time: 18:14:49
 * @author Lucky
 */
public class EditElementDialogRemoveAnotation extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_rem_anot");
    private AbstractElementModel model;
    private AbstractEditElementDialog dialog;

    public EditElementDialogRemoveAnotation(AbstractElementModel model, AbstractEditElementDialog dialog) {
        super(ACTION_NAME, null);

        this.model = model;
        this.dialog = dialog;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        AnotationModel anotation = this.dialog.getSelectedAnotation();
        if (anotation != null) {
            this.model.removeAnotation(anotation);
            this.dialog.updateCell();
        }
    }

}
