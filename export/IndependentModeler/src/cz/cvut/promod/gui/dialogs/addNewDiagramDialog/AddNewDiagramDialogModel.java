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

package cz.cvut.promod.gui.dialogs.addNewDiagramDialog;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.Notation;

import javax.swing.event.ListDataListener;
import javax.swing.*;
import java.util.List;
import java.util.LinkedList;

import com.jgoodies.binding.beans.Model;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 15:59:01, 24.10.2009
 *
 * A Model component for AddNewDiagramDialog.
 */
public class AddNewDiagramDialogModel extends Model implements ListModel {

    public static final String PROPERTY_NEW_DIAGRAM_NAME = "newDiagramName";
    private String newDiagramName;

    public static final String PROPERTY_SELECTED_NOTATION_IDENTIFIER = "selectedNotationIdentifier";
    private String selectedNotationIdentifier;

    private final List<NotationLabelWrapper> notationNames;

    public AddNewDiagramDialogModel() {
        notationNames = new LinkedList<NotationLabelWrapper>();

        for(final Notation notation : ModelerSession.getNotationService().getNotations()){
            notationNames.add(new NotationLabelWrapper(notation.getIdentifier(), notation.getFullName()));
        }
    }

    public int getSize() {
        return notationNames.size();
    }

    public Object getElementAt(int index) {
        return notationNames.get(index);
    }

    public void addListDataListener(ListDataListener l) {}

    public void removeListDataListener(ListDataListener l) {}

    public String getNewDiagramName() {
        return newDiagramName;
    }

    public void setNewDiagramName(final String newDiagramName) {
        final String oldNewDiagramName = this.newDiagramName;
        this.newDiagramName = newDiagramName;
        firePropertyChange(PROPERTY_NEW_DIAGRAM_NAME, oldNewDiagramName, newDiagramName);
    }

    public String getSelectedNotationIdentifier() {
        return selectedNotationIdentifier;
    }

    public void setSelectedNotationIdentifier(final String selectedNotationIdentifier) {
        final String oldSelectedNotationIdentifier = this.selectedNotationIdentifier;
        this.selectedNotationIdentifier = selectedNotationIdentifier;
        firePropertyChange(PROPERTY_SELECTED_NOTATION_IDENTIFIER, oldSelectedNotationIdentifier, selectedNotationIdentifier);
    }

    /**
     * Represents an item in the list of notations.
     */
    protected static class NotationLabelWrapper{

        private final String identifier;
        private final String displayName;

        private NotationLabelWrapper(final String identifier, final String displayName) {
            this.identifier = identifier;
            this.displayName = displayName;
        }

        public String getIdentifier() {
            return identifier;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
