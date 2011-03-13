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

package cz.cvut.promod.ph.treeLayout;

import cz.cvut.promod.ph.treeLayout.actions.LayoutAction;
import cz.cvut.promod.ph.treeLayout.resources.Resources;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 11:22:31, 28.1.2010
 *
 * Model component for ProcessHierarchyTreeLayout plugin.
 */
public class ProcessHierarchyTreeLayoutModel{

    public static final String LAYOUT_ACTION_IDENTIFIER = "process.hierarchy.tree.layout.action"; 

    private final String relatedNotationIdentifier;

    private final ProModAction layoutAction;

    public static final String PROCESS_HIERARCHY_LABEL =
            Resources.getResources().getString("hierarchy.menu.label");
    public static final String LAYOUT_LABEL =
            Resources.getResources().getString("hierarchy.menu.layout");



    public ProcessHierarchyTreeLayoutModel(final String relatedNotationIdentifier){
        this.relatedNotationIdentifier = relatedNotationIdentifier;
        layoutAction = new LayoutAction(getRelatedNotationIdentifier());
    }


    public String getRelatedNotationIdentifier() {
        return relatedNotationIdentifier;
    }

    public String getIdentifier() {
        return "ProcessHierarchy.id";
    }

    public ProModAction getLayoutAction() {
        return layoutAction;
    }

    public String getName() {
        return "ProcessHierarchyTreeLayout";
    }

    public String getDescription() {
        return "Performs tree layouting of diagrams defined by the Process Hierarchy Notation (ProcessHierarchyNotation plugin).";
    }
}
