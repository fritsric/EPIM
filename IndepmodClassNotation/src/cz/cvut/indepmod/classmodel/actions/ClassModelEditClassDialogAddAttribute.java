package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelAttributeCreatorDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import org.openide.windows.WindowManager;

/**
 * Date: 23.10.2010
 * Time: 11:40:10
 * @author Lucky
 *
 * This action is used for adding of an attribute into a class (into it's model)
 * When this action is performed, it will open next dialog for attribute creation.
 * After the attribute is created, this action will insert the attribute into the class.
 */
public class ClassModelEditClassDialogAddAttribute extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Add attribute";
    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelEditClassDialogAddAttribute(ClassModel model, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        AttributeModel attr = new ClassModelAttributeCreatorDialog(
                window,
                this.dialog.getAllTypeModel()).getAttribute();

        if (attr != null) {
            this.model.addAttribute(attr);
            this.dialog.updateCell();
        }
    }
}
