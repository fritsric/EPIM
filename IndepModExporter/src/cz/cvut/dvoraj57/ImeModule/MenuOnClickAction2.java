/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.dvoraj57.ImeModule;

import Exporter.XlsxExporter;
import Exporter.XlsxTest;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.workspace.ClassModelModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

/**
 *
 * @author Michal Salát
 */

@ActionID(category = "Tools",
id = "cz.cvut.dvoraj57.ImeModule.MenuOnClickAction2")
@ActionRegistration(iconBase = "cz/cvut/dvoraj57/ImeModule/proc.png",
displayName = "#CTL_MenuOnClickAction2")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0, separatorAfter = 50)
})
//@Messages("CTL_MenuOnClickAction2=MenuOnClickAction2")
@Messages("CTL_MenuOnClickAction2=IndepMod Exporter XLSx")
public final class MenuOnClickAction2 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        IClassModelModel classModel = new XlsxTest();
        if (classModel == null) {
            System.out.println("Nemate otevren class model");
             JOptionPane.showMessageDialog(null, "Nemate otevren class model");
        } else {
            System.out.println("Otevreny class model ma: "+ classModel.getClasses().size() +" trid.");
            JOptionPane.showMessageDialog(null, "Otevreny class model ma: "+ classModel.getClasses().size() +" trid.");
            JOptionPane.showMessageDialog(null, "Exportuji do C:/export.html");
            XlsxExporter xe = new XlsxExporter("/home/foxnet/EPIM/template.xml", "/home/foxnet/EPIM/style.xml", "/home/foxnet/EPIM/out.xlsx", classModel);
            try {
                xe.Export();
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}