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

package cz.cvut.promod.gui.dialogs.simpleTextFieldDialog;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.PresentationModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:49:43, 23.10.2009
 *
 * A implementation of SimpleTextFieldDialog.
 */
public class SimpleTextFieldDialog extends SimpleTextFieldDialogView{

    private final SimpleTextFieldDialogModel model = new SimpleTextFieldDialogModel();

    private final PresentationModel<SimpleTextFieldDialogModel> presentation = new PresentationModel<SimpleTextFieldDialogModel>(model);

    private final SimpleTextFieldDialogExecutor executor;

    
    public SimpleTextFieldDialog(final String title,
                                 final String textLabelString,
                                 final String inputTextFieldText,
                                 final String confirmButtonText,
                                 final String cancelButtonString,
                                 final SimpleTextFieldDialogExecutor executor,
                                 final Component locateRelativeToComponent,
                                 final boolean modal){

        if(locateRelativeToComponent != null){
            setLocationRelativeTo(locateRelativeToComponent);
        }

        //init texts
        setTitle(title);
        
        textLabel.setText(textLabelString);
        confirmButton.setText(confirmButtonText);
        cancelButton.setText(cancelButtonString);

        this.executor = executor;

        initBinding();

        inputTextField.setText(inputTextFieldText);

        setModal(modal);

        initEventHandling();

        getRootPane().setDefaultButton(confirmButton);

        setVisible(true);
    }

    private void initBinding() {
        Bindings.bind(inputTextField, presentation.getModel(SimpleTextFieldDialogModel.PROPERTY_SUBFOLDER_NAME));
    }

    private void initEventHandling() {
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                disposeDialog();
            }
        });

        confirmButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                final String result = executor.execute(model.getInputText());

                if(result == null){
                    disposeDialog();
                } else {
                    errorLabel.setText(result);
                }
            }
        });

        getRootPane().registerKeyboardAction(new ActionListener(){
                    public void actionPerformed(ActionEvent actionEvent) {
                        disposeDialog();
                    }
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void disposeDialog(){
        setVisible(false);
        dispose();
    }

}
