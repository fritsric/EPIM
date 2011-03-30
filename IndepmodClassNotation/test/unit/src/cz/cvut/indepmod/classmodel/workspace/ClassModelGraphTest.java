package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.Common;
import java.util.Iterator;
import java.util.Collection;
import java.awt.Rectangle;
import java.util.HashMap;
import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModelFactory;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
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
public class ClassModelGraphTest {

    private ClassModelGraph graph;

    @Before
    public void setUp() {
        this.graph = new ClassModelGraph(
                new HashMap<Class<? extends ClassModelAbstractAction>, ClassModelAbstractAction>(),
                new ToolChooserModel(),
                DiagramDataModelFactory.getInstance().createNewDiagramModel().getLayoutCache());

        DefaultGraphCell cell = new DefaultGraphCell();
        cell.setUserObject(new ClassModel(Common.CLASS_NAME));
        GraphConstants.setBounds(cell.getAttributes(), new Rectangle.Double(10, 10, 100, 60));
        this.graph.getGraphLayoutCache().insert(cell);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAllClasses() {
        Collection<IElement> types = this.graph.getAllClasses();
        assertEquals(1, types.size());

        Iterator<IElement> it = types.iterator();
        boolean isThere = false;
        while (it.hasNext()) {
            IElement model = it.next();
            assertNotNull(model);
            if (model.toString().equals(Common.CLASS_NAME) && model.getTypeName().equals(Common.CLASS_NAME)) {
                isThere = true;
            }
        }
        assertTrue(isThere);
    }

    /**
     * Test of insertCell method, of class ClassModelGraph.
     */
    @Test
    public void testInsertCell() {
    }

}