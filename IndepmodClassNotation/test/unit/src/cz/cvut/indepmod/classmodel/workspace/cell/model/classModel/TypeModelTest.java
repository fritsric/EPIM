/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.Common;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class TypeModelTest {

    private TypeModel model;


    @Before
    public void setUp() {
        this.model = new TypeModel(Common.TYPE_NAME);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTypeName method, of class TypeModel.
     */
    @Test
    public void testGetTypeName() {
        assertEquals(Common.TYPE_NAME, this.model.getTypeName());
    }

    /**
     * Test of setTypeName method, of class TypeModel.
     */
    @Test
    public void testSetTypeName() {
        String name = "typeTest2";
        this.model.setTypeName(name);
        assertEquals(name, this.model.getTypeName());
    }

    /**
     * Test of toString method, of class TypeModel.
     */
    @Test
    public void testToString() {
        assertEquals(Common.TYPE_NAME, this.model.toString());
    }

}