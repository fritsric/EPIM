package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditElementDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.AnotationCreatorDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import org.openide.windows.WindowManager;

/**
 * Date: 25.11.2010
 * Time: 17:57:34
 * @author Lucky
 */
public class EditElementDialogAddAnotation extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_add_anot");
    private static final Logger LOG = Logger.getLogger(EditElementDialogAddAnotation.class.getName());
    private AbstractElementModel model;
    private AbstractEditElementDialog dialog;

    public EditElementDialogAddAnotation(AbstractElementModel model, AbstractEditElementDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        IAnotation anot = new AnotationCreatorDialog(window).getAnotation();

        if (anot != null) {
            this.model.addAnotation(anot);
            this.dialog.updateCell();
            LOG.info("Added anotation");
        } else {
            LOG.info("Anotation was not added");
        }
    }
}
