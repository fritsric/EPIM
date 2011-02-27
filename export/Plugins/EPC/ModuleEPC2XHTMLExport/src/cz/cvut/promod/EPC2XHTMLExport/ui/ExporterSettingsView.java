/****************************************************************************
** This file may be used under the terms of the MIT licence:
**
** Permission is hereby granted, free of charge, to any person obtaining a copy
** of this software and associated documentation files (the "Software"), to deal
** in the Software without restriction, including without limitation the rights
** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
** copies of the Software, and to permit persons to whom the Software is
** furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included in
** all copies or substantial portions of the Software.
**
** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
** THE SOFTWARE.
****************************************************************************/

package cz.cvut.promod.EPC2XHTMLExport.ui;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import cz.cvut.promod.EPC2XHTMLExport.resources.Resources;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.componentFactoryService.ComponentFactoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFileChooser;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Oct 6, 2010
 * Time: 4:06:05 PM
 */
public class ExporterSettingsView extends JPanel {

    private static final String LABEL_DIR_RES = "epc.export.xhtml.ui.label.dir";
    private static final String LABEL_NAME_RES = "epc.export.xhtml.ui.label.name";
    private static final String LABEL_THEME_RES = "epc.export.xhtml.ui.label.theme";
    private static final String LABEL_NOTES_RES = "epc.export.xhtml.ui.label.notes";
    private static final String LABEL_EXPORT_RES = "epc.export.xhtml.ui.label.export";

    private static final String BUTTON_EXPORT_RES = "epc.export.xhtml.ui.button.export";
    private static final String BUTTON_BROWSE_RES = "epc.export.xhtml.ui.button.browse";

    //private static final String RADIO_SINGLE_RES = "epc.export.xhtml.ui.radio.singlefile";
    private static final String RADIO_FOLDER_RES = "epc.export.xhtml.ui.radio.folder";
    private static final String RADIO_WIKI_RES = "epc.export.xhtml.ui.radio.wiki";
    private static final String RADIO_LaTex_RES = "epc.export.xhtml.ui.radio.latex";


    private static final String RADIO_DONOTHING_RES = "epc.export.xhtml.ui.radio.donothing";
    private static final String RADIO_OPENWITHDEFAULT_RES = "epc.export.xhtml.ui.radio.openwithdefault";
    private static final String RADIO_OPENWITH_RES = "epc.export.xhtml.ui.radio.openwith";

    private static final String THEME_CLASSICBLACK_RES = "epc.export.xhtml.theme.classicblack";
    private static final String THEME_CONSERVATIVEBLUE_RES = "epc.export.xhtml.theme.conservativeblue";

    protected final JPanel Panel_OptionsPanel = ModelerSession.getComponentFactoryService().createPanel();

    // consider more relevant names for ui components
    protected final JLabel Label_Path = new JLabel(Resources.getStrRes(LABEL_DIR_RES));
    protected final JLabel Label_Theme = new JLabel(Resources.getStrRes(LABEL_THEME_RES));
    protected final JLabel Label_Notes = new JLabel(Resources.getStrRes(LABEL_NOTES_RES));
    protected final JLabel Label_Name = new JLabel(Resources.getStrRes(LABEL_NAME_RES));
    protected final JLabel Label_Export = new JLabel(Resources.getStrRes(LABEL_EXPORT_RES));

    protected final JButton Button_Browse = new JButton(Resources.getStrRes(BUTTON_BROWSE_RES));
    protected final JButton Button_Export = new JButton(Resources.getStrRes(BUTTON_EXPORT_RES));
    protected final JButton Button_OpenWith = new JButton(Resources.getStrRes(BUTTON_BROWSE_RES));

    protected final JTextField TextField_Path = new JTextField();
    protected final JTextField TextField_Name = new JTextField();
    protected final JTextField TextField_OpenWith = new JTextField();

    //protected final JRadioButton RadioButton_File = new JRadioButton(Resources.getStrRes(RADIO_SINGLE_RES), true);
    protected final JRadioButton RadioButton_Folder = new JRadioButton(Resources.getStrRes(RADIO_FOLDER_RES), false);
    protected final JRadioButton RadioButton_DokuWiki = new JRadioButton(Resources.getStrRes(RADIO_WIKI_RES), false);
    protected final JRadioButton RadioButton_LaTeX = new JRadioButton(Resources.getStrRes(RADIO_LaTex_RES), false);

    protected final JRadioButton RadioButton_DoNothing = new JRadioButton(Resources.getStrRes(RADIO_DONOTHING_RES), true);
    protected final JRadioButton RadioButton_OpenWithDefault = new JRadioButton(Resources.getStrRes(RADIO_OPENWITHDEFAULT_RES), false);
    protected final JRadioButton RadioButton_OpenWithNone = new JRadioButton(Resources.getStrRes(RADIO_OPENWITH_RES), false);

    protected final JCheckBox CheckBox_ShowNotes = new JCheckBox();

    protected JComboBox ComboBox_Format = new JComboBox();

    protected final ButtonGroup ButtonGroup_FileOrFolder = new ButtonGroup();

    protected final ButtonGroup ButtonGroup_OpenWith = new ButtonGroup();


    public ExporterSettingsView() {
        initGroup();
        initComboBox();
        initLayout();
    }

    public void registerListener(ActionListener listener) {
        Button_Export.addActionListener(listener);

        TextField_OpenWith.addMouseListener(new MouseAdapter(){

            public void mouseClicked(MouseEvent e) {                
                if(TextField_OpenWith.getText().equalsIgnoreCase("Choose an application..."))
                    TextField_OpenWith.setText("");                

            }
        });

        Button_Browse.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                Browse();
            }
        });

        Button_OpenWith.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                BrowseToOpenWith();
            }
        });        

        RadioButton_LaTeX.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
                if(ComboBox_Format.isEnabled())
                    ComboBox_Format.setEnabled(false);
                else
                    ComboBox_Format.setEnabled(true);
            }
        });

        RadioButton_DokuWiki.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
                if(ComboBox_Format.isEnabled())
                    ComboBox_Format.setEnabled(false);
                else
                    ComboBox_Format.setEnabled(true);
            }
        });
    }

    public void Browse() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            TextField_Path.setText(fileChooser.getSelectedFile().toString());
        }
    }

    private void BrowseToOpenWith(){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Open");
            chooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                TextField_OpenWith.setText(chooser.getSelectedFile().toString());
              }
    }


    private void initGroup(){
        //ButtonGroup_FileOrFolder.add(RadioButton_File);
        ButtonGroup_FileOrFolder.add(RadioButton_Folder);
        ButtonGroup_FileOrFolder.add(RadioButton_DokuWiki);
        ButtonGroup_FileOrFolder.add(RadioButton_LaTeX);
        ButtonGroup_OpenWith.add(RadioButton_DoNothing);
        ButtonGroup_OpenWith.add(RadioButton_OpenWithDefault);
        ButtonGroup_OpenWith.add(RadioButton_OpenWithNone);
    }

    private void initComboBox() {
        ComboBox_Format.addItem(Resources.getStrRes(THEME_CLASSICBLACK_RES));
        ComboBox_Format.addItem(Resources.getStrRes(THEME_CONSERVATIVEBLUE_RES));
    }

    protected final CardLayout cardLayout = new CardLayout();   

    private void initLayout() {
        Panel_OptionsPanel.setLayout(cardLayout);

        setBorder(Borders.createEmptyBorder(ComponentFactoryService.DEFAULT_FORM_BORDER));

        setLayout(new FormLayout(
                "pref, 3dlu, 50dlu,80dlu, 10dlu, pref, 3dlu, pref, pref:grow",
                "pref, 30dlu,pref, 30dlu,pref, 30dlu,pref,fill:pref:grow"
        ));
        final CellConstraints cellConstraints = new CellConstraints();
        add(Label_Path, cellConstraints.xy(1,1));
        add(TextField_Path, cellConstraints.xyw(3,1,2));
        add(Button_Browse, cellConstraints.xy(6,1));
        add(Button_Export, cellConstraints.xy(8,1));
        add(Label_Notes, cellConstraints.xy(1,4));
        add(TextField_Name, cellConstraints.xyw(3,2,2));
        add(Label_Theme, cellConstraints.xy(1,3));
        add(ComboBox_Format, cellConstraints.xyw(3,3,2));
        add(Label_Name, cellConstraints.xy(1,2));
        add(CheckBox_ShowNotes, cellConstraints.xy(3,4));
       // add(RadioButton_File, cellConstraints.xy(6,2));
        add(RadioButton_Folder, cellConstraints.xy(6,3));
        add(RadioButton_DokuWiki, cellConstraints.xy(6,4));
        add(RadioButton_LaTeX, cellConstraints.xy(6,5));
        add(Label_Export, cellConstraints.xy(1,5));
        add(RadioButton_DoNothing, cellConstraints.xyw(3,5,2));
        add(RadioButton_OpenWithDefault, cellConstraints.xyw(3,6,2));
        add(RadioButton_OpenWithNone, cellConstraints.xy(3,7));
        add(TextField_OpenWith, cellConstraints.xy(4,7));
        add(Button_OpenWith, cellConstraints.xy(6,7));
        
    }

    public String getDir() {
        return TextField_Path.getText();
    }

    public void setDir(String dir) {
        TextField_Path.setText(dir);
    }

    public String getName() {
        return TextField_Name.getText();
    }

    public void setName(String name) {
        TextField_Name.setText(name);
    }

    public boolean getNotes() {
        return CheckBox_ShowNotes.isSelected();
    }

    public void setNotes(boolean notes) {
        CheckBox_ShowNotes.setSelected(notes);
    }


    public ExporterSettingsModel.ExportTheme getTheme() {
        if (ComboBox_Format.getSelectedIndex() == 0)
            return ExporterSettingsModel.ExportTheme.eClassicBlack;
        else
            return ExporterSettingsModel.ExportTheme.eConservativeBlue;
    }

    public void setTheme(int i) {
        ComboBox_Format.setSelectedIndex(i);
    }

    public ExporterSettingsModel.ExportFormat getFormat() {
       // if (RadioButton_File.isSelected())
       //     return ExporterSettingsModel.ExportFormat.eXHTMLFile;
        if (RadioButton_Folder.isSelected())
            return ExporterSettingsModel.ExportFormat.eXHTMLFolder;
        if (RadioButton_DokuWiki.isSelected())
            return ExporterSettingsModel.ExportFormat.eDokuWiki;
        return ExporterSettingsModel.ExportFormat.eLaTeXFolder;
    }

    public void setFormat(ExporterSettingsModel.ExportFormat format) {
      //  RadioButton_File.setSelected(false);
        RadioButton_Folder.setSelected(false);
        RadioButton_DokuWiki.setSelected(false);
        RadioButton_LaTeX.setSelected(false);
        switch (format) {
            /*
            case eXHTMLFile:
                RadioButton_File.setSelected(true);
                break;
            */
            case eXHTMLFolder:
                RadioButton_Folder.setSelected(true);
                break;
            case eDokuWiki:
                RadioButton_DokuWiki.setSelected(true);
                break;
            default:
                RadioButton_LaTeX.setSelected(true);
                break;
        }
    }

    public void setOpenWithChoice(int choice) {
        switch (choice) {
            case 0 : RadioButton_DoNothing.setSelected(true);
                     RadioButton_OpenWithDefault.setSelected(false);
                     RadioButton_OpenWithNone.setSelected(false);
                     break;
            case 1 : RadioButton_DoNothing.setSelected(false);
                     RadioButton_OpenWithDefault.setSelected(true);
                     RadioButton_OpenWithNone.setSelected(false);
                     break;
            case 2 : RadioButton_DoNothing.setSelected(false);
                     RadioButton_OpenWithDefault.setSelected(false);
                     RadioButton_OpenWithNone.setSelected(true);
                     break;
        }
    }

    public int getOpenWithChoice() {
        if (RadioButton_DoNothing.isSelected())       return 0;
        if (RadioButton_OpenWithDefault.isSelected()) return 1;
        if (RadioButton_OpenWithNone.isSelected())    return 2;
        return -1;
    }

    public void setOpenWithPath(String path) {
        TextField_OpenWith.setText(path);
    }

    public String getOpenWithPath() {
        return TextField_OpenWith.getText();
    }

    public JButton getExportButton(){
        return Button_Export;        
    }

    public JButton getBrowseButton(){
        return Button_Browse;        
    }

    public JRadioButton getFolderRadioButton(){
        return RadioButton_Folder;
    }

    public JRadioButton getLatexRadioButton(){
        return RadioButton_LaTeX;
    }

    public JRadioButton getDokuWikiRadioButton(){
        return RadioButton_DokuWiki;
    }

    public JComboBox getComboBox_Format(){
        return ComboBox_Format;        
    }

}