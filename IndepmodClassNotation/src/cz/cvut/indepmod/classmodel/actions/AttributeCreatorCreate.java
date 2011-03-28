package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractAttrCreatorDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.event.ActionEvent;

/**
 * Date: 20.3.2011
 * Time: 13:50:26
 * @author Lucky
 */
public class AttributeCreatorCreate extends ClassModelAbstractAction {

    public static final String TITLE = Resources.getString("dialog_anotation_attribute_creator_create_button_name");
    private AbstractAttrCreatorDialog dialog;

    public AttributeCreatorCreate(AbstractAttrCreatorDialog dialog) {
        super(TITLE, null);

        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramDataModel diagramData = Globals.getInstance().getActualDiagramData();

        String name = this.dialog.getAttributeName();
        Object dataTypeObj = this.dialog.getSelectedAttributeType();
        Visibility visibility = this.dialog.getSelectedVisibility();

        TypeModel dataType;
        if (dataTypeObj instanceof String) {
            dataType = new TypeModel((String) dataTypeObj);
            diagramData.addDynamicDataType(dataType);
        } else {
            dataType = (TypeModel) dataTypeObj;
        }

        IAttribute attribute = new AttributeModel(dataType, name, visibility);

        for (IAnotation anot : this.dialog.getAnotationList()) {
            attribute.addAnotation(anot);
        }

        this.dialog.setReturnValue(attribute);
        this.dialog.dispose();
    }
}
