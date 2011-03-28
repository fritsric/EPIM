package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditElementDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.factory.AbstractDialogFactory;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
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
public class EditElementDialogAddAttribute extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_add_attr");
    private AbstractElementModel model;
    private AbstractEditElementDialog dialog;

    public EditElementDialogAddAttribute(
            AbstractElementModel model,
            AbstractEditElementDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramType diagramType = Globals.getInstance().getActualDiagramData().getDiagramType();

        Frame window = WindowManager.getDefault().getMainWindow();
        AbstractDialogFactory factory = AbstractDialogFactory.getFactory(diagramType);
        IAttribute attr = factory.createAttributeCreatorDialog(
                this.dialog.getAllTypeModel()).getReturnValue();

        if (attr != null) {
            this.model.addAttribute(attr);
            this.dialog.updateCell();
        }
    }
}
