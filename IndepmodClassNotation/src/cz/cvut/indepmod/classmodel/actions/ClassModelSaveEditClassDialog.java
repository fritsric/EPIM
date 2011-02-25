package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * Date: 23.10.2010
 * Time: 11:32:59
 * @author Lucky
 *
 * This action saves an edit dialog of the class (and changes the name of the
 * class)
 */
public class ClassModelSaveEditClassDialog extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Save";
    
    private static final Logger LOG = Logger.getLogger(ClassModelSaveEditClassDialog.class.getName());

    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelSaveEditClassDialog(ClassModel model, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newClassName = this.dialog.getClassName();
        if (!newClassName.equals(model.getTypeName())) {
            if (newClassName.matches("^[A-Za-z][0-9A-Za-z]*$")) {
                LOG.info("Changing the name of the class");
                model.setTypeName(newClassName);
            } else {
                LOG.warning("Bad name of the class!");
            }
        }
        this.dialog.updateCell();
        this.dialog.dispose();
    }
}
