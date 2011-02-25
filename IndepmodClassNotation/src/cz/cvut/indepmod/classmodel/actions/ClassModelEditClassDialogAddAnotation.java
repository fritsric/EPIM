package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelAnotationCreatorDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import org.openide.windows.WindowManager;

/**
 * Date: 25.11.2010
 * Time: 17:57:34
 * @author Lucky
 */
public class ClassModelEditClassDialogAddAnotation extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Add attribute";
    private static final Logger LOG = Logger.getLogger(ClassModelEditClassDialogAddAnotation.class.getName());
    private ClassModel model;
    private ClassModelEditClassDialog dialog;

    public ClassModelEditClassDialogAddAnotation(ClassModel model, ClassModelEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        AnotationModel anot = new ClassModelAnotationCreatorDialog(window).getAnotation();

        if (anot != null) {
            this.model.addAnotation(anot);
            this.dialog.updateCell();
            LOG.info("Added anotation");
        } else {
            LOG.info("Anotation was not added");
        }
    }



}
