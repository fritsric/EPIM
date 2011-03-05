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

import java.util.Map;
import java.util.Hashtable;
import java.util.UUID;
import java.awt.geom.Point2D;
import java.awt.*;

import org.jgraph.graph.GraphConstants;
import org.apache.log4j.Logger;

import javax.swing.*;

import cz.cvut.promod.epc.workspace.cell.EPCVertexRenderer;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 18:17:14, 7.12.2009
 *
 * Represents the general Logical Function element of the EPCNotation plugin.
 */
public class LogicFunctionModel extends EPCNoteItem implements EPCIdentifiableVertex {

    public enum LogicOperator {
        AND,
        OR,
        XOR,
        AND_OR,
        AND_XOR,
        OR_AND,
        OR_XOR,
        XOR_AND,
        XOR_OR
    }

    private static final Logger LOG = Logger.getLogger(LogicFunctionModel.class);

    public static final String AND_UNICODE = "\u2227";
    public static final String OR_UNICODE = "\u2228";
    public static final String XOR_UNICODE = "\u22BB";

    public static final Font font = new Font(new JLabel().getName(), Font.BOLD, 25);

    public static final int DIAMETER = 35;

    private final String name;

    private final UUID uuid;

    private final LogicOperator operator;

    public final static String CONDITION_1_PROPERTY = "condition1";
    private String condition1;

    public final static String CONDITION_2_PROPERTY = "condition2";
    private String condition2;

    
    public LogicFunctionModel(final LogicOperator operator, final UUID uuid){
        this.operator = operator;
        this.uuid = uuid;

        switch (operator){
            case AND:
                name = AND_UNICODE;
                break;
            case OR:
                name = OR_UNICODE;
                break;
            case XOR:
                name = XOR_UNICODE;
                break;
            default:
                name = "";
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public LogicOperator getOperator() {
        return operator;
    }

    public String getCondition1() {
        return condition1;
    }

    public void setCondition1(final String condition) {
        this.condition1 = condition;
    }

    public String getCondition2() {
        return condition2;
    }

    public void setCondition2(final String condition2) {
        this.condition2 = condition2;
    }

    public static Map installAttributes(final Point2D point, final LogicOperator logicOperator) {
        final Map map = new Hashtable();

        switch (logicOperator){
            case AND:
            case OR:
            case XOR:
                map.put(CellConstants.VERTEXSHAPE, MultiLineVertexRenderer.SHAPE_CIRCLE);
                break;
            case AND_OR:
                map.put(CellConstants.VERTEXSHAPE, EPCVertexRenderer.SHAPE_EPC_LOGIC_AND_OR);
                break;
            case AND_XOR:
                map.put(CellConstants.VERTEXSHAPE, EPCVertexRenderer.SHAPE_EPC_LOGIC_AND_XOR);
                break;
            case OR_AND:
                map.put(CellConstants.VERTEXSHAPE, EPCVertexRenderer.SHAPE_EPC_LOGIC_OR_AND);
                break;
            case OR_XOR:
                map.put(CellConstants.VERTEXSHAPE, EPCVertexRenderer.SHAPE_EPC_LOGIC_OR_XOR);
                break;
            case XOR_AND:
                map.put(CellConstants.VERTEXSHAPE, EPCVertexRenderer.SHAPE_EPC_LOGIC_XOR_AND);
                break;
            case XOR_OR:
                map.put(CellConstants.VERTEXSHAPE, EPCVertexRenderer.SHAPE_EPC_LOGIC_XOR_OR);
                break;
            default:
                LOG.error("No such a type of logical function");
        }

        GraphConstants.setBounds(map, new Rectangle.Double(point.getX(), point.getY(), DIAMETER, DIAMETER));
        GraphConstants.setOpaque(map, true);
        GraphConstants.setBorderColor(map, Color.black);
        GraphConstants.setFont(map, font);
        GraphConstants.setEditable(map, false);
        GraphConstants.setAutoSize(map, false);
        GraphConstants.setSizeable(map, false);

        return map;
    }

    @Override
    public String toString() {
        return name;
    }
}
