package cz.cvut.indepmod.classmodel;

import cz.cvut.indepmod.classmodel.workspace.ClassModelWorkspace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Lucky
 *
 * This action creates new ClassModelNotation editor
 */
public class OpenClassNotationAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassModelWorkspace workspace = new ClassModelWorkspace();
        workspace.open();
        workspace.requestActive();
    }

}
