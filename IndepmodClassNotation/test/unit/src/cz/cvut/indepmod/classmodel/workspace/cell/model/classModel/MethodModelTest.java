/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.Common;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class MethodModelTest {

    private MethodModel model;

    @Before
    public void setUp() {
        this.model = new MethodModel(new TypeModel(Common.TYPE_NAME), Common.METHOD_NAME, Common.getAttributes());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getType method, of class MethodModel.
     */
    @Test
    public void testGetType() {
        assertNotNull(model.getType());
        assertNotNull(model.getType().getTypeName());
        assertEquals(Common.TYPE_NAME, model.getType().getTypeName());
    }

    /**
     * Test of getName method, of class MethodModel.
     */
    @Test
    public void testGetName() {
        assertNotNull(model.getName());
        assertEquals(Common.METHOD_NAME, model.getName());
    }

    /**
     * Test of getAttributeModels method, of class MethodModel.
     */
    @Test
    public void testGetAttributeModels() {
        assertNotNull(model.getAttributeModels());
        Set<AttributeModel> atr = model.getAttributeModels();
        assertEquals(3, atr.size());
        for (AttributeModel a : atr) {
            assertNotNull(a);
        }
    }

    /**
     * Test of toString method, of class MethodModel.
     */
    @Test
    public void testToString() {
        assertNotNull(model.toString());
    }

}