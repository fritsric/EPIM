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

package cz.cvut.promod.services.projectService.dialogs;

import javax.swing.*;
import java.awt.event.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:05:47, 13.11.2009
 */

/**
 * SyncDialog controller.
 */
public class SyncDialog extends SyncDialogView{

    public static final String NEW_LINE_MARK = "\n";

    private SwingWorker swingWorker = null;

    /**
     * Creates a new SyncDialog.
     *
     * @param title is the SyncDialog title.
     */
    public SyncDialog(final String title){
        super(title);

        initEventHandling();

        okButton.setEnabled(false);
        cancelButton.setEnabled(false);

        getRootPane().setDefaultButton(okButton);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setModal(true);
    }

    /**
     * Initialize event handling.
     */
    private void initEventHandling() {
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                disposeDialog();
            }
        });

        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                swingWorker.cancel(true);
            }
        });

        getRootPane().registerKeyboardAction(new ActionListener(){
                    public void actionPerformed(ActionEvent actionEvent) {
                       escape();
                    }
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                escape();
            }
        });
    }

    /**
     * When the EXC or cross buton is pressed.
     */
    private void escape() {
        if(okButton.isEnabled()){
            disposeDialog();
        }
    }

    /**
     * Controls dialogs final appearance.
     *
     * @param errors if true the dialog remains visible, false -> the dialog will be disposed
     */
    public void syncComplete(final boolean errors){
        if(errors){
            okButton.setEnabled(true);
        } else {
            disposeDialog();
        }
    }

    /**
     * Sets the cancel button enabled.
     */
    public void disableCancelButton(){
        cancelButton.setEnabled(false);
    }

    /**
     * Disposes the dialog.
     */
    public void disposeDialog(){
        setVisible(false);
        dispose();
    }

    /**
     * Appends a new error message to the synchronization error dialog.
     *
     * @param message is the error message
     *
     * @param addEmptyLine if true then there will be added an empty line after this error message
     */
    public void appendErrorInfo(final String message, final boolean addEmptyLine){
        errorsTextArea.append(message);

        errorsTextArea.append(NEW_LINE_MARK);

        if(addEmptyLine){
            errorsTextArea.append(NEW_LINE_MARK);
        }
    }

    /**
     * Update current sync position.
     * @param text the location
     */
    public void updatePosition(final String text){
        currentSearchLocationLabel.setText(text);
    }

    /**
     * Sets the sync worker.
     * @param swingWorker the sync worker.
     */
    public void setSyncWorker(final SwingWorker swingWorker) {
        if(swingWorker == null){
            return;
        }

        this.swingWorker = swingWorker;

        cancelButton.setEnabled(true);
    }
}