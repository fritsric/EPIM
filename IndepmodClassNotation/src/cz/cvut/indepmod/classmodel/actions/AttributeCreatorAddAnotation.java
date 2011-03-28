package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractAttrCreatorDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.AnotationCreatorDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import org.openide.windows.WindowManager;

/**
 * Date: 20.3.2011
 * Time: 14:11:29
 * @author Lucky
 */
public class AttributeCreatorAddAnotation extends ClassModelAbstractAction {

    public static final String TITLE = Resources.getString("dialog_attribute_creator_add_anot");
    private AbstractAttrCreatorDialog dialog;

    public AttributeCreatorAddAnotation(AbstractAttrCreatorDialog dialog) {
        super(TITLE, null);

        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        IAnotation anot = new AnotationCreatorDialog(window).getAnotation();

        if (anot != null) {
            this.dialog.addAnotation(anot);
        }
    }
}
