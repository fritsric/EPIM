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

package cz.cvut.promod.epc.modelFactory.epcGraphItemModels;

import cz.cvut.promod.epc.workspace.cell.EPCVertexRenderer;

import java.util.UUID;
import java.util.Map;
import java.util.Hashtable;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.*;

import com.jgraph.components.labels.CellConstants;
import org.jgraph.graph.GraphConstants;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 10:32:01, 19.1.2010
 *
 * Represents the Message element of the EPCNotation plugin.
 */
public class MessageModel extends EPCNoteItem implements EPCIdentifiableVertex {

    public static final int INIT_WIDTH = 50;
    public static final int INIT_HEIGHT = 25;

    public static final int DEFAULT_INSET = 5;

    private final UUID uuid;


    public MessageModel(final UUID uuid){
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "    ";
    }

    /**
     * Initialize new vertex attributes.
     *
     * @param point is the point where new vertex is supposed to be inserted
     * @return a proper attribute map for new vertex
     */
    public static Map installAttributes(final Point2D point) {
        final Map map = new Hashtable();

        map.put(CellConstants.VERTEXSHAPE, EPCVertexRenderer.SHAPE_EPC_ENVELOPE);
        GraphConstants.setBounds(map, new Rectangle2D.Double(point.getX(), point.getY(), INIT_WIDTH, INIT_HEIGHT));
        GraphConstants.setEditable(map, false);
        GraphConstants.setBorderColor(map, Color.black);
        GraphConstants.setOpaque(map, true);
        GraphConstants.setInset(map, DEFAULT_INSET);
        GraphConstants.setSizeable(map, false);

        return map;
    }

    public UUID getUuid() {
        return uuid;
    }

}
