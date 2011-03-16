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

import com.jgraph.components.labels.CellConstants;
import com.jgraph.components.labels.MultiLineVertexRenderer;

import java.util.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.*;

import org.jgraph.graph.GraphConstants;
import cz.cvut.promod.epc.workspace.cell.borders.InformationObjectBorder;
import cz.cvut.promod.epc.resources.Resources;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 19:16:41, 7.12.2009
 *
 * Represents the Information Object element of the EPCNotation plugin.
 */
public class InformationObjectModel extends EPCEditableVertex {

    private final static String DEFAULT_LABEL = Resources.getResources().getString("epc.vertex.info.object");

    public static final int DEFAULT_INSET = 5;

    private final UUID uuid;


    public InformationObjectModel(final UUID uuid){
        this.uuid = uuid;
        setName(DEFAULT_LABEL);
    }

    public InformationObjectModel(final InformationObjectModel informationObjectModel, final String name){
        setName(name);
        uuid = informationObjectModel.getUuid();
        setNote(informationObjectModel.getNote());
    }

    @Override
    public String toString(){
        return name;
    }

    /**
    /**
     * Initialize new vertex attributes.
     *
     * @param point is the point where new vertex is supposed to be inserted
     * @return a proper attribute map for new vertex
     */
    public static Map installAttributes(final Point2D point) {
        final Map map = new Hashtable();

        map.put(CellConstants.VERTEXSHAPE, MultiLineVertexRenderer.SHAPE_RECTANGLE);

        GraphConstants.setBounds(map, new Rectangle2D.Double(point.getX(), point.getY(), 0, 0));
        GraphConstants.setResize(map, true);
        GraphConstants.setBorder(map, new InformationObjectBorder(Color.BLACK));
        GraphConstants.setOpaque(map, true);
        GraphConstants.setInset(map, DEFAULT_INSET);

        return map;
    }

    public UUID getUuid() {
        return uuid;
    }

}
