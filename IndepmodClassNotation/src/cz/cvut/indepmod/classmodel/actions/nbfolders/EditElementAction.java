/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.indepmod.classmodel.actions.nbfolders;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public final class EditElementAction implements ActionListener {

    private final EditElementCookie context;

    public EditElementAction(EditElementCookie context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        this.context.edit();
    }
}
