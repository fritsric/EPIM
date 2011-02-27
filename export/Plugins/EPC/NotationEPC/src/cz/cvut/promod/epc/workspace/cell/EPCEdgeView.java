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

package cz.cvut.promod.epc.workspace.cell;

import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphCellEditor;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 19:31:47, 21.1.2010
 *
 * Special implementation of the EdgeView for the EPCNotation plugin. 
 */
public class EPCEdgeView extends EdgeView {

    /**
     * EPCGraphCellEditor class overrides getCellEditorValue() method. For details
     * @see cz.cvut.promod.epc.workspace.cell.EPCGraphCellEditor
     */
    private static final GraphCellEditor epcCellEditor = new EPCGraphCellEditor();    


    public EPCEdgeView(final Object cell){
        super(cell);
    }

    @Override
    public GraphCellEditor getEditor() {
        return epcCellEditor;
    }
}
