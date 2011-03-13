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

package cz.cvut.promod.EPC2XHTMLExport.engine;

import cz.cvut.promod.epc.modelFactory.epcGraphItemModels.*;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Ondrej Machulda
 * Date: 10.11.2010
 * Time: 11:32:28
 */
public class DiagramTest {

    private Diagram diagram;
    private JGraph graph;
    ArrayList<Node> nodes;
    ArrayList<Link> links;

    @Before
    public void startUp() {
        GraphModel model = new DefaultGraphModel();
        GraphLayoutCache view = new GraphLayoutCache(
                model,
                new DefaultCellViewFactory());
        this.graph = new JGraph(model, view);
        DefaultGraphCell[] cells = new DefaultGraphCell[4];


        cells[0] = new DefaultGraphCell(new String("Hello"));
        DefaultPort port0 = new DefaultPort();
        cells[0].add(port0);
        
        FunctionModel functionModel = new FunctionModel(new UUID(1234,5678));
        functionModel.setName("name 0");
        functionModel.setNote("note 0");
        cells[0].setUserObject(functionModel);

        cells[1] = new DefaultGraphCell(new String("World"));
        DefaultPort port1 = new DefaultPort();
        cells[1].add(port1);
        MessageModel messageModel = new MessageModel(new UUID(5678, 9012));
        messageModel.setNote("note 1");
        cells[1].setUserObject(messageModel);

        cells[2] = new DefaultGraphCell(new String("World"));
        DefaultPort port2 = new DefaultPort();
        cells[2].add(port2);
        LogicFunctionModel logicFunctionModel = new LogicFunctionModel(
                LogicFunctionModel.LogicOperator.XOR_OR,
                new UUID(3456, 7890));
        logicFunctionModel.setCondition1("cond 1");
        logicFunctionModel.setCondition2("cond 2");
        cells[2].setUserObject(logicFunctionModel);

        DefaultEdge edge = new DefaultEdge();
        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[1].getChildAt(0));
        EdgeModel edgeModel = new EdgeModel(EdgeModel.EdgeType.INFORMATION_SERVICES_FLOW);
        edgeModel.setName("edge name");
        edgeModel.setNote("edge note");
        edge.setUserObject(edgeModel);
        cells[3] = edge;
        
        this.graph.getGraphLayoutCache().insert(cells);

        this.diagram = new Diagram(model, "name");
        this.nodes = diagram.getNodes();
        this.links = diagram.getLinks();
        
    }

    @Test
    public void testNameEquals() {
        GraphModel model = new DefaultGraphModel();
        assertEquals("ABC", new Diagram(model, "ABC").getName());
        assertEquals(null, new Diagram(model, null).getName());
        assertEquals("", new Diagram(model, "").getName());
        assertEquals("A very very long \"enquoted\" node name", new Diagram(model, "A very very long \"enquoted\" node name").getName());
        assertEquals("!@#$%^&*()", new Diagram(model, "!@#$%^&*()").getName());
    }

    @Test
    public void testNode0Equals() {
        // cells[0]
        assertEquals(Node.Type.eFunction, this.nodes.get(0).getType());
        assertEquals(new UUID(1234,5678).toString(), this.nodes.get(0).getUUID());
        assertEquals("name 0", this.nodes.get(0).getName());
        assertEquals("note 0", this.nodes.get(0).getNote());
    }

    @Test
    public void testNode1Equals() {
        // cells[1]
        assertEquals(Node.Type.eMessage, this.nodes.get(1).getType());
        assertEquals(new UUID(5678,9012).toString(), this.nodes.get(1).getUUID());
        assertEquals("note 1", this.nodes.get(1).getNote());
    }
    @Test
    public void testNode2Equals() {
        // cells[2] - logicFunction specific
        assertEquals(Node.Type.eLogicFunction, this.nodes.get(2).getType());
        NodeLogic logicFunctionNode = (NodeLogic) diagram.getNodes().get(2);
        assertEquals(NodeLogic.LogicType.eXOR_OR, logicFunctionNode.getLogicType());
        assertEquals("cond 1", logicFunctionNode.getCondition1());
        assertEquals("cond 2", logicFunctionNode.getCondition2());

    }

    @Test
    public void testLink0Equals() {
        // cells[3]
        assertEquals(Link.Type.eInformationService, this.links.get(0).getType());
        assertEquals(this.nodes.get(0).getUUID(), this.links.get(0).getFrom().getUUID());
        assertEquals(this.nodes.get(1).getUUID(), this.links.get(0).getTo().getUUID());
        assertEquals("edge name", this.links.get(0).getName());
        assertEquals("edge note", this.links.get(0).getNote());

    }

    

}
