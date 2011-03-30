package cz.cvut.indepmod.classmodel.persistence.xml;

import java.util.Iterator;
import java.util.Set;
import java.awt.geom.Point2D;
import cz.cvut.indepmod.classmodel.Common;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import java.util.Collection;
import org.jgraph.graph.Edge;
import org.jgraph.graph.Port;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel;
import org.jgraph.graph.DefaultPort;
import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModelFactory;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.InterfaceModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
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
    private DiagramDataModel diagramModel;

    public ClassModelXMLCoderTest() {
    }

    @Before
    public void setUp() {
        this.diagramModel = DiagramDataModelFactory.getInstance().createNewDiagramModel();
        this.graph = new ClassModelGraph(
                new HashMap<Class<? extends ClassModelAbstractAction>, ClassModelAbstractAction>(),
                new ToolChooserModel(), this.diagramModel.getLayoutCache());
        this.graph.setGraphLayoutCache(this.diagramModel.getLayoutCache());
    }

    @After
    public void tearDown() {
        File f = new File(FILE_NAME);
        f.delete();
    }

    @Test
    public void testInterfaceEncodeDecode() throws FileNotFoundException {
        AbstractElementModel mod1 = new InterfaceModel(Common.CLASS_NAME);
        ClassModelClassCell cell1 = new ClassModelClassCell(mod1);
        this.graph.getGraphLayoutCache().insert(cell1);


        File file = new File(FILE_NAME);
        FileOutputStream fos = new FileOutputStream(file);
        ClassModelXMLCoder encoder = ClassModelXMLCoder.getInstance();
        encoder.encode(this.diagramModel, fos);

        FileInputStream fis = new FileInputStream(file);
        this.graph.setGraphLayoutCache(encoder.decode(fis).getLayoutCache());

        IElement el = this.graph.getAllClasses().iterator().next();
        assertEquals(Common.CLASS_NAME, el.getTypeName());
    }

    @Test
    public void testSimpleEncodeDecode() throws FileNotFoundException {
        AbstractElementModel mod1 = new ClassModel(Common.CLASS_NAME);
        AbstractElementModel mod2 = new ClassModel(Common.CLASS_NAME2);
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
        RelationModel relModel = new RelationModel(RelationType.RELATION);
        relModel.setRelationName(Common.VAL3);
        ClassModelRelation edge = new ClassModelRelation(relModel);
        this.initEdge(edge, p1, p2);
        this.graph.getGraphLayoutCache().insert(edge);

        mod1.setStereotype(Common.VAL2);

        AnotationModel anot = new AnotationModel(Common.ANOT2);
        AnotationAttributeModel anotAtr = new AnotationAttributeModel(Common.ATTRIBUTE_NAME2);
        anotAtr.addValue(Common.VAL2);
        anotAtr.addValue(Common.VAL3);
        anot.addAttribute(anotAtr);
        mod1.addAnotation(anot);

        AttributeModel atr = new AttributeModel(mod1, Common.ATTRIBUTE_NAME, Visibility.PROTECTED);
        mod1.addAttribute(atr);
        AnotationModel anot2 = new AnotationModel(Common.ANOT3);
        atr.addAnotation(anot);

        Set<IAttribute> atrList = new HashSet<IAttribute>();
        atrList.add(new AttributeModel(mod2, Common.ATTRIBUTE_NAME2));
        MethodModel method = new MethodModel(mod1, Common.METHOD_NAME, atrList, Visibility.PRIVATE);
        mod1.addMethod(method);

        File file = new File(FILE_NAME);
        FileOutputStream fos = new FileOutputStream(file);
        ClassModelXMLCoder encoder = ClassModelXMLCoder.getInstance();
        encoder.encode(this.diagramModel, fos);

        FileInputStream fis = new FileInputStream(file);
        this.graph.setGraphLayoutCache(encoder.decode(fis).getLayoutCache());

        assertEquals(3, this.graph.getModel().getRootCount());

        boolean isThereClass1 = false;
        boolean isThereClass2 = false;
        for (IType m : this.graph.getAllClasses()) {
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
        AbstractElementModel model = (AbstractElementModel) root.getUserObject();
        assertEquals(Common.CLASS_NAME, model.getTypeName());
        assertEquals(Common.VAL2, model.getStereotype());
        assertEquals(1, model.getAttributeModels().size());
        assertEquals(1, model.getMethodModels().size());
        assertEquals(1, model.getAnotations().size());
        assertEquals(Common.ANOT2, model.getAnotations().iterator().next().getName());
        assertEquals(1, model.getAnotations().iterator().next().getAttributes().size());
        assertEquals(Common.ATTRIBUTE_NAME2, model.getAnotations().iterator().next().getAttributes().iterator().next().getName());
        assertEquals(2, model.getAnotations().iterator().next().getAttributes().iterator().next().getValues().size());
        Iterator<String> it = model.getAnotations().iterator().next().getAttributes().iterator().next().getValues().iterator();
        assertEquals(Common.VAL2, it.next());
        assertEquals(Common.VAL3, it.next());

        assertEquals(1, model.getAttributeModels().size());
        assertEquals(Common.ATTRIBUTE_NAME, model.getAttributeModels().iterator().next().getName());
        assertEquals(Visibility.PROTECTED, model.getAttributeModels().iterator().next().getVisibility());
        assertEquals(model, model.getAttributeModels().iterator().next().getType());
        assertEquals(1, model.getAttributeModels().iterator().next().getAnotations().size());

        assertEquals(1, model.getMethodModels().size());
        assertEquals(Common.METHOD_NAME, model.getMethodModels().iterator().next().getName());
        assertEquals(Visibility.PRIVATE, model.getMethodModels().iterator().next().getVisibility());
        assertEquals(model, model.getMethodModels().iterator().next().getType());
        assertEquals(1, model.getMethodModels().iterator().next().getAttributeModels().size());
        assertEquals(Common.ATTRIBUTE_NAME2, model.getMethodModels().iterator().next().getAttributeModels().iterator().next().getName());

        root = (DefaultGraphCell) this.graph.getRoots()[1];
        model = (AbstractElementModel) root.getUserObject();

        assertEquals(Common.CLASS_NAME2, model.getTypeName());
        assertTrue(model.getAttributeModels().isEmpty());
        assertTrue(model.getMethodModels().isEmpty());

        Collection<? extends IRelation> rels = model.getRelatedClass();
        assertEquals(1, rels.size());
        IRelation r = rels.iterator().next();
        IElement c1 = r.getStartingClass();
        IElement c2 = r.getEndingClass();
        assertEquals(mod1.getTypeName(), c1.getTypeName());
        assertEquals(mod2.getTypeName(), c2.getTypeName());
        assertEquals(Cardinality.ONE, r.getStartCardinality());
        assertEquals(Cardinality.ONE, r.getEndCardinality());
        assertEquals(Common.VAL3, r.getRelationName());
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
