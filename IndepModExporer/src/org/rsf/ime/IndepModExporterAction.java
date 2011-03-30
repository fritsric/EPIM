/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rsf.ime;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;

@ActionID(category = "Tools",
id = "org.rsf.ime.IndepModExporterAction")
@ActionRegistration(iconBase = "org/rsf/ime/process.gif",
displayName = "#CTL_IndepModExporterAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0, separatorBefore = -50, separatorAfter = 50)
})
@Messages("CTL_IndepModExporterAction=IndepMod Exporter")
public final class IndepModExporterAction implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
        IClassModelModel classModel = Utilities.actionsGlobalContext().lookup(IClassModelModel.class);
        if (classModel == null) {
            //Oznam uzivateli, ze neni v zadnem class modelu
            JOptionPane.showConfirmDialog((Component) null, "Nemate otevren class model!", "alert", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        } else {
            //Zacni generovat
            JOptionPane.showConfirmDialog((Component) null, "Otevreny class model ma: "+ classModel.getClasses().size() +" trid.", "alert", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Otevreny class model ma: "+ classModel.getClasses().size() +" trid.");
            // zde by asi byla funkce na predani modelu do nejakeho
            // objektu genetatr
            // napr:  Gener.generate(model);
            
        }
    }
}
