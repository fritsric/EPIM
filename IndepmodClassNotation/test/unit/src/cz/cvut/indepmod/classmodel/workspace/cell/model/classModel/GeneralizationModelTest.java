/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.RelationType;
import org.jgraph.graph.Edge;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.Port;
import cz.cvut.indepmod.classmodel.Common;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation;
import org.jgraph.graph.DefaultPort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class GeneralizationModelTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGeneralization() {
        AbstractElementModel model1 = new ClassModel(Common.CLASS_NAME, Common.getMethods(), Common.getAttributes(), Common.getAnotations());
        AbstractElementModel model2 = new ClassModel(Common.CLASS_NAME, Common.getMethods(), Common.getAttributes(), Common.getAnotations());

        ClassModelClassCell cell1 = new ClassModelClassCell(model1);
        ClassModelClassCell cell2 = new ClassModelClassCell(model2);

        DefaultPort p1 = new DefaultPort();
        DefaultPort p2 = new DefaultPort();

        cell1.add(p1);
        cell2.add(p2);

        ClassModelRelation edge = new ClassModelRelation(new HierarchyRelationModel(RelationType.GENERALIZATION));
        this.initEdge(edge, p1, p2);

        HierarchyRelationModel rel = (HierarchyRelationModel) edge.getUserObject();
        assertEquals(RelationType.GENERALIZATION, rel.getRelationType());
        assertEquals(model1, rel.getStartingClass());
        assertEquals(model2, rel.getEndingClass());
        assertEquals(Cardinality.ONE, rel.getStartCardinality());
        assertEquals(Cardinality.ONE, rel.getEndCardinality());
    }

    private void initEdge(Edge edge, Port startPort, Port endPort) {
        edge.setSource(startPort);
        edge.setTarget(endPort);

        GraphConstants.setEndFill(edge.getAttributes(), false);
        GraphConstants.setLineStyle(edge.getAttributes(), GraphConstants.STYLE_ORTHOGONAL);
        GraphConstants.setLabelAlongEdge(edge.getAttributes(), false);
        GraphConstants.setEditable(edge.getAttributes(), false);
        GraphConstants.setMoveable(edge.getAttributes(), true);
        GraphConstants.setDisconnectable(edge.getAttributes(), false);
    }
}