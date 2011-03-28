package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditElementDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
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
public class SaveEditElementDialog extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_save");
    private static final Logger LOG = Logger.getLogger(SaveEditElementDialog.class.getName());
    private AbstractElementModel model;
    private AbstractEditElementDialog dialog;

    public SaveEditElementDialog(AbstractElementModel model, AbstractEditElementDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramType diagramType = Globals.getInstance().getActualDiagramData().getDiagramType();
        boolean isOk = false;
        switch (diagramType) {
            case CLASS:
                isOk = this.classDiagramEditSave();
                break;
            case BUSINESS:
                isOk = this.businessDiagramEditSave();
                break;
        }

        if (isOk) {
            this.dialog.updateCell();
            this.dialog.dispose();
        }
    }

    private boolean classDiagramEditSave() {
        String stereotype = this.dialog.getStereotype();
        String newClassName = this.dialog.getClassName();
        boolean isAbstract = this.dialog.isCheckedAbstract();

        if (newClassName.matches("^([A-Za-z][0-9A-Za-z]*::)?[A-Za-z][0-9A-Za-z]*$")) {
            LOG.info("Changing the name of the class (class diagram)");
            model.setTypeName(newClassName);
            model.setStereotype(stereotype);
            model.setAbstract(isAbstract);
            return true;
        } else {
            LOG.warning("Bad name of the class! (class diagram)");
            return false;
        }
    }

    private boolean businessDiagramEditSave() {
        String stereotype = this.dialog.getStereotype();
        String newClassName = this.dialog.getClassName();

        if (!newClassName.isEmpty()) {
            LOG.info("Changing the name of the class (business diagram)");
            model.setTypeName(newClassName);
            model.setStereotype(stereotype);
            return true;
        } else {
            LOG.warning("Bad name of the class! (business diagram)");
            return false;
        }
    }
}
