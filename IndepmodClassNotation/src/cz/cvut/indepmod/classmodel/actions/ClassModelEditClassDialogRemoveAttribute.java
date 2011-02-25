package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.event.ActionEvent;

/**
 * Date: 23.10.2010
 * Time: 11:43:39
 * @author Lucky
 *
 * This action is used for removing of an attribute from the class.
 */
public class ClassModelEditClassDialogRemoveAttribute extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Remove attribute";
    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelEditClassDialogRemoveAttribute(ClassModel model, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AttributeModel attr = this.dialog.getSelectedAttribute();
        if (attr != null) {
            this.model.removeAttribute(attr);
            this.dialog.updateCell();
        }
    }
}
