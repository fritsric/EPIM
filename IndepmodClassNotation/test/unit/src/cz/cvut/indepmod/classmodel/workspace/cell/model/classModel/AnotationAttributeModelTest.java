/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.Common;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class AnotationAttributeModelTest {

    private AnotationAttributeModel model;

    @Before
    public void setUp() {
        this.model = new AnotationAttributeModel(Common.ATTRIBUTE_NAME);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class AnotationAttributeModel.
     */
    @Test
    public void testGetName() {
        assertNotNull(model.getName());
        assertEquals(Common.ATTRIBUTE_NAME, this.model.getName());
    }

    /**
     * Test of getValues method, of class AnotationAttributeModel.
     */
    @Test
    public void testAddGetValues() {
        this.model.addValue(Common.VAL1);
        this.model.addValue(Common.VAL2);

        assertEquals(2, this.model.getValues().size());

        boolean isVal1 = false, isVal2 = false;
        for (String s : this.model.getValues()) {
            if (s.equals(Common.VAL1)) {
                isVal1 = true;
            } else if (s.equals(Common.VAL2)) {
                isVal2 = true;
            } else {
                fail("There is a value that I did not add");
            }
        }

        assertTrue(isVal1);
        assertTrue(isVal1);

        this.model.addValue(new String(Common.VAL1));

        assertEquals(2, this.model.getValues().size());
    }
}