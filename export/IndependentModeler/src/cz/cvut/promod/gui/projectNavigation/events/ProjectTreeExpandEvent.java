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

package cz.cvut.promod.gui.projectNavigation.events;

import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 12:54:20, 24.1.2010
 */

/**
 * Represent an extension of ActionEvent that hold tree path and depth for expanding/collapsing
 * project tree navigation.
 */
public class ProjectTreeExpandEvent extends ActionEvent{

    private final TreePath treePath;

    private final int depth; // if depth < 1 -> expand/collapse all

    private final boolean expand;

    /**
     * Constructs a new event for expand/collapse project tree action.
     *
     * @param source is the source of action
     * @param treePath is the root of subtree to be expanded/collapsed
     * @param depth is the depth for expanding, -1 stands for expand all
     * @param expand true for expanding, false for collapsing
     */
    public ProjectTreeExpandEvent(final Object source, 
                                  final TreePath treePath,
                                  final int depth, final boolean expand) {
        super(source, 0, null);

        this.treePath = treePath;
        this.depth = depth;
        this.expand = expand;
    }

    public TreePath getTreePath() {
        return treePath;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isExpand() {
        return expand;
    }
}
