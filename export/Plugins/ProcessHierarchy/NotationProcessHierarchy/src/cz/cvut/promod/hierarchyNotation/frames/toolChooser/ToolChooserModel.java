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

package cz.cvut.promod.hierarchyNotation.frames.toolChooser;

import cz.cvut.promod.gui.support.utils.NotationGuiHolder;
import cz.cvut.promod.hierarchyNotation.resources.Resources;

import java.util.Set;
import java.util.HashSet;

import com.jgoodies.binding.beans.Model;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 12:48:44, 29.11.2009
 *
 * The Model component for the ToolChooser dockable frame.
 */
public class ToolChooserModel extends Model {

    public enum Tool {
        CONTROL,
        ADD_PROCESS,
        ADD_EDGE,
        REMOVE
    }

    public static final String TITLE_LABEL = Resources.getResources().getString("hierarchy.frame.tools.title");

    public static final String PROPERTY_SELECTED_TOOL = "selectedTool";
    private Tool selectedTool;

    private Set<NotationGuiHolder.Position> allowedSides;

    private final String name;


    public ToolChooserModel(String name){
        this.name = name;

        allowedSides = new HashSet<NotationGuiHolder.Position>();
        allowedSides.add(NotationGuiHolder.Position.LEFT);
        allowedSides.add(NotationGuiHolder.Position.RIGHT);
        allowedSides.add(NotationGuiHolder.Position.TOP);
        allowedSides.add(NotationGuiHolder.Position.BOTTOM);
    }

    public Set<NotationGuiHolder.Position> getAllowedSides() {
        return allowedSides;
    }

    public void setSelectedTool(final Tool selectedTool) {
        final Tool oldValue = this.selectedTool;
        this.selectedTool = selectedTool;
        firePropertyChange(PROPERTY_SELECTED_TOOL, oldValue, selectedTool);
    }

    public Tool getSelectedTool() {
        return selectedTool;
    }

    public String getName() {
        return name;
    }
}
