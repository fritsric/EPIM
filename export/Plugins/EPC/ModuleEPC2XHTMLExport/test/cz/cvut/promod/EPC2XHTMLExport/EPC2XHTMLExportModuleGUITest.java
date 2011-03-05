package cz.cvut.promod.EPC2XHTMLExport;

/**
 * Created by IntelliJ IDEA.
 * User: mrazt
 * Date: 5.11.2010
 * Time: 14:29:47
 * To change this template use File | Settings | File Templates.
 */

import cz.cvut.promod.EPC2XHTMLExport.ui.ExporterSettingsView;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryServiceImpl;
import junit.extensions.abbot.*;
import abbot.tester.ComponentTester;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EPC2XHTMLExportModuleGUITest extends ComponentTestFixture {

    public EPC2XHTMLExportModuleGUITest(String name) { super(name); }

    public static void main(String[] args) {
        TestHelper.runTests(args,  EPC2XHTMLExportModuleGUITest.class);
    }

    private String clickType;
    private ComponentTester tester;
    protected void setUp() {
            tester = ComponentTester.getTester(ExporterSettingsView.class);
        }

        public void testClick() {
            ModelerSession.setComponentFactoryService(new ComponentFactoryServiceImpl());

            ExporterSettingsView view = new ExporterSettingsView();
            JPanel pane = new JPanel();

            JButton Export = view.getExportButton();
            JButton Browse = view.getBrowseButton();
            JRadioButton LaTex = view.getLatexRadioButton();
            JRadioButton Pdf = view.getDokuWikiRadioButton();
            JRadioButton Folder = view.getFolderRadioButton();
            final JComboBox ComboBox_Format = view.getComboBox_Format();
            ButtonGroup ButtonGroup_FileOrFolder = new ButtonGroup();

            ButtonGroup_FileOrFolder.add(Pdf);
            ButtonGroup_FileOrFolder.add(LaTex);
            ButtonGroup_FileOrFolder.add(Folder);

            ActionListener al = new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    clickType = ev.getActionCommand();
                }                
            };

            LaTex.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(ComboBox_Format.isEnabled())
                        ComboBox_Format.setEnabled(false);
                    else
                        ComboBox_Format.setEnabled(true);
                }
            });

            Pdf.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(ComboBox_Format.isEnabled())
                        ComboBox_Format.setEnabled(false);
                    else
                        ComboBox_Format.setEnabled(true);
                }
            });
            

            Export.addActionListener(al);
            Browse.addActionListener(al);
            Folder.addActionListener(al);

            pane.add(Export);
            pane.add(Browse);
            pane.add(LaTex);
            pane.add(Pdf);
            pane.add(Folder);
            pane.add(ComboBox_Format);            

            showFrame(pane);            
            
            //Test - ButtonExport
            clickType = null;
            tester.actionClick(Export);
            assertEquals("Export", Export.getText(), clickType.toString());

            //Test - ButtonBrowse
            clickType = null;
            tester.actionClick(Browse);
            assertEquals("Browse", Browse.getText(), clickType.toString());

            //Test - disable ComboBox_Format - LaTex
            clickType = null;
            tester.actionClick(LaTex);
            assertEquals(false, ComboBox_Format.isEnabled());

            //Test - enable ComboBox_Format - Folder
            clickType = null;
            tester.actionClick(Folder);
            assertEquals(true, ComboBox_Format.isEnabled());

            //Test - disable ComboBox_Format - PDF
            clickType = null;
            tester.actionClick(Pdf);
            assertEquals(false, ComboBox_Format.isEnabled());

            //Test - enable ComboBox_Format - Folder
            clickType = null;
            tester.actionClick(Folder);
            assertEquals(true, ComboBox_Format.isEnabled());
        }

    private int count = 0;
       public void testRepeatedFire() {
           ExporterSettingsView view = new ExporterSettingsView();

           JButton Export = view.getExportButton();

           ActionListener al = new ActionListener() {
               public void actionPerformed(ActionEvent ev) {
                   ++count;
               }
           };
           Export.addActionListener(al);
           showFrame(Export);

           // Hold the button down for 5 seconds
           tester.mousePress(Export);
           tester.actionDelay(500);
           tester.actionClick(Export);
           tester.actionClick(Export);
           tester.mouseRelease();
           assertTrue("Didn't get any repeated events", count > 1);
       }

}