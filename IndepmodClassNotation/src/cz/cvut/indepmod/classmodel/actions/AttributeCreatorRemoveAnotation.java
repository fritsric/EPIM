package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractAttrCreatorDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import java.awt.event.ActionEvent;

/**
 * Date: 20.3.2011
 * Time: 14:15:10
 * @author Lucky
 */
public class AttributeCreatorRemoveAnotation extends ClassModelAbstractAction {

    public static final String TITLE = Resources.getString("dialog_attribute_creator_rem_anot");
    private AbstractAttrCreatorDialog dialog;

    public AttributeCreatorRemoveAnotation(AbstractAttrCreatorDialog dialog) {
        super(TITLE, null);

        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = this.dialog.getSelectedAnotationIndex();
        if (index != -1) {
            this.dialog.removeAnotationAt(index);
        }
    }
}
