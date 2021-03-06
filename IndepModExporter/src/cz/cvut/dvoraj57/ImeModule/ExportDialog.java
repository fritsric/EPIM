/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExportDialog.java
 *
 * Created on 2.5.2011, 18:08:48
 */
package cz.cvut.dvoraj57.ImeModule;

import Exporter.DocxExporter;
import Exporter.XlsxExporter;

import cz.cvut.hnatuluk.Exception.BadTemplateException;
import cz.cvut.hnatuluk.Exception.DocxWriterException;
import cz.cvut.hnatuluk.Exception.TemplateParseErrorExcpetion;
import cz.cvut.hnatuluk.Exception.XlsxWriterException;

import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.promod.EPC2XHTMLExport.engine.ExporterEngine;
import cz.cvut.promod.EPC2XHTMLExport.engine.RendererType;

import java.io.File;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.openide.util.Exceptions;
import org.openide.util.Utilities;

/**
 *
 * @author Richard
 */
public class ExportDialog extends javax.swing.JDialog {
    
    String ext = ".txt", desc = "Dokuwiki";
    enum format {
        DOKUWIKI, LATEX, HTML_I, HTML_F, DOCX, XLSX
    }
    format f = format.DOKUWIKI;
    
    /** Creates new form ExportDialog */
    public ExportDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ExportButton = new javax.swing.JButton();
        Format = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        template = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ExportButton.setText(org.openide.util.NbBundle.getMessage(ExportDialog.class, "ExportDialog.ExportButton.text")); // NOI18N
        ExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportButtonActionPerformed(evt);
            }
        });

        Format.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dokuwiki", "Latex", "XHTML Folder", "XHTML Inline", "docx", "xlsx" }));
        Format.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormatActionPerformed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ExportDialog.class, "ExportDialog.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ExportDialog.class, "ExportDialog.jLabel2.text")); // NOI18N
        jLabel2.setFocusable(false);

        jLabel3.setText(org.openide.util.NbBundle.getMessage(ExportDialog.class, "ExportDialog.jLabel3.text")); // NOI18N

        template.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "classicBlackTheme", "conservativeBlueTheme" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addComponent(ExportButton)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Format, 0, 140, Short.MAX_VALUE)
                            .addComponent(template, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Format, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(template, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExportButton)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportButtonActionPerformed

        IClassModelModel classModel = Utilities.actionsGlobalContext().lookup(IClassModelModel.class);

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        final String e = ext, d = desc;
        chooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(e) || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return d;
            }
        });
        int r = chooser.showSaveDialog(this);

        if (r != JFileChooser.APPROVE_OPTION) {
            return;
        }
		
        jLabel2.setText("Working...");
		
        ExporterEngine ee = null;
        DocxExporter docx = null;
        XlsxExporter xlsx = null;
        
        try {
            String file = chooser.getSelectedFile().getName(), path = chooser.getSelectedFile().getParent() + File.separator;
            if (file.endsWith(ext))
                file = file.substring(0, file.length() - ext.length());
            //jLabel1.setText(file + " " + path);
            switch (f) {
                case DOKUWIKI:
                    ee = new ExporterEngine(path, file, "", classModel, RendererType.DOKUWIKI);
                    ee.export();
                    break;
                case LATEX:
                    ee = new ExporterEngine(path, file, "", classModel, RendererType.LATEX);
                    ee.export();
                    break;
                case HTML_I:
                    ee = new ExporterEngine(path, file, template.getSelectedItem().toString(), classModel, RendererType.XHTML_INLINE);
                    ee.export();
                    break;
                case HTML_F:
                    ee = new ExporterEngine(path, file, template.getSelectedItem().toString(), classModel, RendererType.XHTML_FOLDER);
                    ee.export();
                    break;
                case DOCX:
                    docx = new DocxExporter("../templates/template.xml", "../templates/styles.xml", path + file + ext, classModel);
                    docx.Export();
                    break;
                case XLSX:
                    xlsx = new XlsxExporter("../templates/template.xml", "../templates/styles.xml", path + file + ext, classModel);
                    xlsx.Export();

            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (DocxWriterException ex) {
            Exceptions.printStackTrace(ex);
        } catch (BadTemplateException ex) {
            Exceptions.printStackTrace(ex);
        } catch (TemplateParseErrorExcpetion ex) {
            Exceptions.printStackTrace(ex);
        } catch (XlsxWriterException ex) {
            Exceptions.printStackTrace(ex);
        }
		jLabel2.setText("Success!");
    }//GEN-LAST:event_ExportButtonActionPerformed

	private void FormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormatActionPerformed

        switch (Format.getSelectedIndex()) {
            case 0:
                ext = ".txt";
                desc = "Dokuwiki";
                f = format.DOKUWIKI;
                break;
            case 1:
                ext = ".tex";
                desc = "Latex";
                f = format.LATEX;
                break;
            case 2:
                ext = ".html";
                desc = "HTML";
                f = format.HTML_I;
            case 3:
                ext = ".html";
                desc = "HTML";
                f = format.HTML_F;
                break;
            case 4:
                ext = ".docx";
                desc = "DOCX";
                f = format.DOCX;
                break;
            case 5:
                ext = ".xlsx";
                desc = "XLSX";
                f = format.XLSX;
                break;
            default:
            }
		template.removeAllItems();
		switch (f) {
			case HTML_I:
			case HTML_F:
				template.addItem("conservativeBlueTheme");
				template.addItem("classicBlackTheme");
				break;
			default:
				template.addItem("Default");
		}
		jLabel2.setText("");
	}//GEN-LAST:event_FormatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ExportDialog dialog = new ExportDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExportButton;
    private javax.swing.JComboBox Format;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox template;
    // End of variables declaration//GEN-END:variables
}
