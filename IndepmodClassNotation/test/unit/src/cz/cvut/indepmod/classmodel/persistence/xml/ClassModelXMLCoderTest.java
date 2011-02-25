package cz.cvut.indepmod.classmodel.persistence.xml;

import java.awt.geom.Point2D;
import cz.cvut.indepmod.classmodel.Common;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import java.util.Collection;
import org.jgraph.graph.Edge;
import org.jgraph.graph.Port;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel;
import org.jgraph.graph.DefaultPort;
import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.modelFactory.ClassModelDiagramModelFactory;
import cz.cvut.indepmod.classmodel.modelFactory.diagramModel.ClassModelDiagramDataModel;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class ClassModelXMLCoderTest {

    public static final String FILE_NAME = "TestClass";
    private ClassModelGraph graph;
    private ClassModelDiagramDataModel diagramModel;

    public ClassModelXMLCoderTest() {
    }

    @Before
    public void setUp() {
        this.diagramModel = ClassModelDiagramModelFactory.getInstance().createNewDiagramModel();
        this.graph = new ClassModelGraph(
                new HashMap<String, ClassModelAbstractAction>(),
                new ToolChooserModel(), this.diagramModel);
        this.graph.setGraphLayoutCache(this.diagramModel.getLayoutCache());
    }

    @After
    public void tearDown() {
        File f = new File(FILE_NAME);
        f.delete();
    }

    @Test
    public void testSimpleEncodeDecode() throws FileNotFoundException {
        ClassModel mod1 = new ClassModel(Common.CLASS_NAME);
        ClassModel mod2 = new ClassModel(Common.CLASS_NAME2);
        ClassModelClassCell cell1 = new ClassModelClassCell(mod1);
        ClassModelClassCell cell2 = new ClassModelClassCell(mod2);
        GraphConstants.setBounds(cell1.getAttributes(), new Rectangle.Double(10, 10, 100, 60));
        GraphConstants.setBounds(cell2.getAttributes(), new Rectangle.Double(100, 25, 100, 60));
        this.graph.getGraphLayoutCache().insert(cell1);
        this.graph.getGraphLayoutCache().insert(cell2);

        DefaultPort p1 = new DefaultPort();
        DefaultPort p2 = new DefaultPort();
        cell1.add(p1);
        cell2.add(p2);
        ClassModelRelation edge = new ClassModelRelation(new RelationModel(RelationType.RELATION));
        this.initEdge(edge, p1, p2);
        this.graph.getGraphLayoutCache().insert(edge);

        File file = new File(FILE_NAME);
        FileOutputStream fos = new FileOutputStream(file);
        ClassModelXMLCoder encoder = ClassModelXMLCoder.getInstance();
        encoder.encode(this.diagramModel, fos);

        FileInputStream fis = new FileInputStream(file);
        this.graph.setGraphLayoutCache(encoder.decode(fis).getLayoutCache());

        assertEquals(3, this.graph.getModel().getRootCount());

        boolean isThereClass1 = false;
        boolean isThereClass2 = false;
        for (TypeModel m : this.graph.getAllTypes()) {
            if (m.getTypeName().equals(Common.CLASS_NAME)) {
                isThereClass1 = true;
            }

            if (m.getTypeName().equals(Common.CLASS_NAME2)) {
                isThereClass2 = true;
            }
        }
        assertTrue("Cell 1 is not there after decode", isThereClass1);
        assertTrue("Cell 2 is not there after decode", isThereClass2);

        DefaultGraphCell root = (DefaultGraphCell) this.graph.getRoots()[0];
        ClassModel model = (ClassModel) root.getUserObject();
        assertEquals(Common.CLASS_NAME, model.getTypeName());
        assertTrue(model.getAttributeModels().isEmpty());
        assertTrue(model.getMethodModels().isEmpty());

        root = (DefaultGraphCell) this.graph.getRoots()[1];
        model = (ClassModel) root.getUserObject();

        assertEquals(Common.CLASS_NAME2, model.getTypeName());
        assertTrue(model.getAttributeModels().isEmpty());
        assertTrue(model.getMethodModels().isEmpty());

        Collection<? extends IRelation> rels = model.getRelatedClass();
        assertEquals(1, rels.size());
        IRelation r = rels.iterator().next();
        IClass c1 = r.getStartingClass();
        IClass c2 = r.getEndingClass();
        assertEquals(mod1.getTypeName(), c1.getTypeName());
        assertEquals(mod2.getTypeName(), c2.getTypeName());
        assertEquals(Cardinality.ONE, r.getStartCardinality());
        assertEquals(Cardinality.ONE, r.getEndCardinality());
    }

    private void initEdge(Edge edge, Port startPort, Port endPort) {
        edge.setSource(startPort);
        edge.setTarget(endPort);

        Cardinality[] labels = {Cardinality.ONE, Cardinality.ONE};
        Point2D[] labPos = {new Point2D.Double(GraphConstants.PERMILLE / 8, 20), new Point2D.Double(GraphConstants.PERMILLE * 7 / 8, 20)};
        GraphConstants.setExtraLabelPositions(edge.getAttributes(), labPos);
        GraphConstants.setExtraLabels(edge.getAttributes(), labels);

        GraphConstants.setEndFill(edge.getAttributes(), true);
        GraphConstants.setLineStyle(edge.getAttributes(), GraphConstants.STYLE_ORTHOGONAL);
        GraphConstants.setLabelAlongEdge(edge.getAttributes(), false);
        GraphConstants.setEditable(edge.getAttributes(), true);
        GraphConstants.setMoveable(edge.getAttributes(), true);
        GraphConstants.setDisconnectable(edge.getAttributes(), false);
    }
}
