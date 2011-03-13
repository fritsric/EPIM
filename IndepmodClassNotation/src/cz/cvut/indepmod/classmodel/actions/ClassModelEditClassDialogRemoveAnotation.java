package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.event.ActionEvent;

/**
 * Date: 25.11.2010
 * Time: 18:14:49
 * @author Lucky
 */
public class ClassModelEditClassDialogRemoveAnotation extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Remove anotation";
    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelEditClassDialogRemoveAnotation(ClassModel model, ClassModelEditClassDialog dialog) {
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
