package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.Common;
import org.jgraph.graph.DefaultEdge;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel.Tool;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellFactory;
import org.jgraph.graph.Edge;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.Port;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class ClassModelTest {

    private ClassModel model;
    private ClassModel model2;
    private ClassModel model3;

    @Before
    public void setUp() {
        Set<AttributeModel> attributes = new HashSet<AttributeModel>();
        attributes.add(new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME));
        attributes.add(new AttributeModel(new TypeModel(Common.TYPE_NAME2), Common.ATTRIBUTE_NAME2));
        attributes.add(new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME2));

        Set<MethodModel> methods = new HashSet<MethodModel>();
        methods.add(new MethodModel(new TypeModel(Common.TYPE_NAME), Common.METHOD_NAME, null));
        methods.add(new MethodModel(new TypeModel(Common.TYPE_NAME2), Common.METHOD_NAME2, null));

        Set<AnotationModel> anotations = new HashSet<AnotationModel>();
        anotations.add(new AnotationModel(Common.ANOT1));
        anotations.add(new AnotationModel(Common.ANOT2));
        anotations.add(new AnotationModel(Common.ANOT3));

        this.model = new ClassModel(Common.CLASS_NAME, methods, attributes, anotations);
        this.model2 = new ClassModel(Common.CLASS_NAME2, methods, attributes, anotations);
        this.model3 = new ClassModel(Common.CLASS_NAME3, methods, attributes, anotations);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testClassInitialization() {
        ClassModel m = null;

        m = new ClassModel();
        assertTrue(m.getAttributeModels().isEmpty());
        assertTrue(m.getMethodModels().isEmpty());
        assertNotNull(m.getTypeName());
        assertNotNull(m.toString());

        m = new ClassModel(Common.CLASS_NAME);
        assertTrue(m.getAttributeModels().isEmpty());
        assertTrue(m.getMethodModels().isEmpty());
        assertNotNull(m.getTypeName());
        assertNotNull(m.toString());
        assertEquals(Common.CLASS_NAME, m.getTypeName());
        assertEquals(Common.CLASS_NAME, m.toString());

        m = new ClassModel(Common.CLASS_NAME, Common.getMethods(), Common.getAttributes(), Common.getAnotations());
        assertNotNull(m.getAttributeModels());
        assertNotNull(m.getMethodModels());
        assertNotNull(m.toString());
        assertNotNull(m.getTypeName());
        assertNotNull(m.getAnotations());
        assertEquals(3, m.getAttributeModels().size());
        assertEquals(2, m.getMethodModels().size());
        assertEquals(3, m.getAnotations().size());
        assertEquals(Common.CLASS_NAME, m.getTypeName());
        assertEquals(Common.CLASS_NAME, m.toString());

        this.doAnotationTest(m);
    }

    /**
     * Test of toString method, of class ClassModel.
     */
    @Test
    public void testToString() {
        assertNotNull(model.toString());
    }

    /**
     * Test of getMethodModels method, of class ClassModel.
     */
    @Test
    public void testGetMethodModels() {
        assertNotNull(model.getMethodModels());
        assertEquals(2, model.getMethodModels().size());
    }

    /**
     * Test of getAttributeModels method, of class ClassModel.
     */
    @Test
    public void testGetAttributeModels() {
        assertNotNull(model.getAttributeModels());
        assertEquals(3, model.getAttributeModels().size());
    }

    @Test
    public void testGetAnotationModels() {
        assertNotNull(model.getAnotations());
        assertEquals(3, model.getAnotations().size());
        this.doAnotationTest(model);
    }

    /**
     * Test of addAttribute method, of class ClassModel.
     */
    @Test
    public void testAddAttribute() {
        ClassModel m = new ClassModel();
        assertEquals(0, m.getAttributeModels().size());

        m.addAttribute(new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME));
        assertEquals(1, m.getAttributeModels().size());

        AttributeModel a = m.getAttributeModels().iterator().next();
        assertEquals(Common.TYPE_NAME, a.getType().getTypeName());
        assertEquals(Common.ATTRIBUTE_NAME, a.getName());
    }

    /**
     * Test of removeAttribute method, of class ClassModel.
     */
    @Test
    public void testRemoveAttribute() {
        ClassModel m = new ClassModel();
        assertEquals(0, m.getAttributeModels().size());

        m.addAttribute(new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME));
        assertEquals(1, m.getAttributeModels().size());

        m.removeAttribute(m.getAttributeModels().iterator().next());
        assertEquals(0, m.getAttributeModels().size());
    }

    /**
     * Test of removeMethod method, of class ClassModel.
     */
    @Test
    public void testRemoveMethod() {
        ClassModel m = new ClassModel(Common.CLASS_NAME);

        assertEquals(0, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(Common.TYPE_NAME), Common.METHOD_NAME, null));
        assertEquals(1, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(Common.TYPE_NAME2), Common.METHOD_NAME2, null));
        assertEquals(2, m.getMethodModels().size());

        m.removeMethod(m.getMethodModels().iterator().next());
        assertEquals(1, m.getMethodModels().size());

        m.removeMethod(m.getMethodModels().iterator().next());
        assertEquals(0, m.getMethodModels().size());
    }

    /**
     * Test of addMethod method, of class ClassModel.
     */
    @Test
    public void testAddMethod() {
        ClassModel m = new ClassModel(Common.CLASS_NAME);

        assertEquals(0, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(Common.TYPE_NAME), Common.METHOD_NAME, null));
        assertEquals(1, m.getMethodModels().size());

        m.addMethod(new MethodModel(new TypeModel(Common.TYPE_NAME2), Common.METHOD_NAME2, null));
        assertEquals(2, m.getMethodModels().size());
    }

    @Test
    public void testAddAndRemoveAnotation() {
        ClassModel m = new ClassModel(Common.CLASS_NAME);

        AnotationModel anotMod1 = new AnotationModel(Common.ANOT1);
        AnotationModel anotMod2 = new AnotationModel(Common.ANOT2);
        AnotationModel anotMod3 = new AnotationModel(Common.ANOT3);

        assertEquals(0, m.getAnotations().size());

        m.addAnotation(null);
        assertEquals(0, m.getAnotations().size());

        m.addAnotation(anotMod1);
        assertEquals(1, m.getAnotations().size());

        m.addAnotation(anotMod2);
        assertEquals(2, m.getAnotations().size());

        m.addAnotation(anotMod2);
        assertEquals(2, m.getAnotations().size());

        m.addAnotation(anotMod3);
        assertEquals(3, m.getAnotations().size());

        m.addAnotation(anotMod3);
        assertEquals(3, m.getAnotations().size());

        this.doAnotationTest(m);

        m.removeAnotation(new AnotationModel("tralala"));
        assertEquals(3, m.getAnotations().size());

        m.removeAnotation(anotMod2);
        assertEquals(2, m.getAnotations().size());

        m.removeAnotation(anotMod3);
        assertEquals(1, m.getAnotations().size());

        m.removeAnotation(anotMod1);
        assertEquals(0, m.getAnotations().size());
    }

    @Test
    public void testGetRelations() {
        GraphLayoutCache cache = new GraphLayoutCache();
        JGraph graph = new JGraph(cache);

        ClassModelClassCell cell1 = new ClassModelClassCell(this.model);
        ClassModelClassCell cell2 = new ClassModelClassCell(this.model2);
        ClassModelClassCell cell3 = new ClassModelClassCell(this.model3);

        DefaultPort p1 = new DefaultPort();
        DefaultPort p2 = new DefaultPort();
        DefaultPort p3 = new DefaultPort();

        cell1.add(p1);
        cell2.add(p2);
        cell3.add(p3);

        DefaultEdge edge = ClassModelCellFactory.createEdge(Tool.TOOL_ADD_RELATION);
        DefaultEdge edge2 = ClassModelCellFactory.createEdge(Tool.TOOL_ADD_RELATION);
        DefaultEdge edge3 = ClassModelCellFactory.createEdge(Tool.TOOL_ADD_RELATION);
        this.initEdge(edge, p1, p2);
        this.initEdge(edge2, p2, p3);
        this.initEdge(edge3, p3, p1);

        cache.insert(edge);
        cache.insert(edge2);
        cache.insert(edge3);

        Iterator<? extends IRelation> it = this.model.getRelatedClass().iterator();
        boolean e1 = false;
        boolean e3 = false;
        for (int i = 0; i < 2; i++) {
            IRelation r = it.next();
            if (r.getStartingClass().equals(this.model)) {
                e1 = true;
                assertEquals(r.getStartingClass().getTypeName(), this.model, r.getStartingClass());
                assertEquals(this.model2, r.getEndingClass());
                assertEquals(Cardinality.ONE, r.getStartCardinality());
                assertEquals(Cardinality.ONE, r.getEndCardinality());
                assertEquals(RelationType.RELATION, r.getRelationType());
            } else if (r.getStartingClass().equals(this.model3)) {
                e3 = true;
                assertEquals(this.model, r.getEndingClass());
                assertEquals(this.model3, r.getStartingClass());
                assertEquals(Cardinality.ONE, r.getStartCardinality());
                assertEquals(Cardinality.ONE, r.getEndCardinality());
                assertEquals(RelationType.RELATION, r.getRelationType());
            }
        }

        assertFalse("There is another relation which shouldn't be there!", it.hasNext());
        assertTrue("edge 1 was not there!", e1);
        assertTrue("edge 3 was not there!", e3);
    }

    private void initEdge(Edge edge, Port startPort, Port endPort) {
        edge.setSource(startPort);
        edge.setTarget(endPort);
    }


    private void doAnotationTest(ClassModel cm) {
        boolean isAnot1 = false, isAnot2 = false, isAnot3 = false;
        for (AnotationModel anot : model.getAnotations()) {
            if (anot.getName().equals(Common.ANOT1)) {
                isAnot1 = true;
            } else if (anot.getName().equals(Common.ANOT2)) {
                isAnot2 = true;
            } else if (anot.getName().equals(Common.ANOT3)) {
                isAnot3 = true;
            }
        }

        assertTrue(isAnot1);
        assertTrue(isAnot2);
        assertTrue(isAnot3);
    }
}
