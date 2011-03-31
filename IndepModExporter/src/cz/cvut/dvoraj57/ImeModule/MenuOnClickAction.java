/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.dvoraj57.ImeModule;

import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

@ActionID(category = "Tools",
id = "cz.cvut.dvoraj57.ImeModule.MenuOnClickAction")
@ActionRegistration(iconBase = "cz/cvut/dvoraj57/ImeModule/proc.png",
displayName = "#CTL_MenuOnClickAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0, separatorAfter = 50)
})
//@Messages("CTL_MenuOnClickAction=MenuOnClickAction")
@Messages("CTL_MenuOnClickAction=IndepMod Exporter")
public final class MenuOnClickAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        IClassModelModel classModel = Utilities.actionsGlobalContext().lookup(IClassModelModel.class);
        if (classModel == null) {
            System.out.println("Nemate otevren class model");
             JOptionPane.showMessageDialog(null, "Nemate otevren class model");
        } else {
            System.out.println("Otevreny class model ma: "+ classModel.getClasses().size() +" trid.");
            JOptionPane.showMessageDialog(null, "Otevreny class model ma: "+ classModel.getClasses().size() +" trid.");
        }
    }
}
