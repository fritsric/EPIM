package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.resources.Resources;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

/**
 * Date: 23.10.2010
 * Time: 11:29:34
 * @author Lucky
 *
 * This action is used for Closing of the class edit dialog
 */
public class CancelEditElementDialog extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_cancel_edit_class_name");

    private JDialog dialog;

    public CancelEditElementDialog(JDialog dialog) {
        super(ACTION_NAME, null);

        this.dialog = dialog;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        this.dialog.dispose();
    }

}
