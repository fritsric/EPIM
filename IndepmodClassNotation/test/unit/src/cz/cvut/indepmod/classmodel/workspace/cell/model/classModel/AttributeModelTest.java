/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import java.util.HashSet;
import java.util.Set;
import cz.cvut.indepmod.classmodel.Common;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class AttributeModelTest {
    private AttributeModel model;

    @Before
    public void setUp() {
        this.model = new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getType method, of class AttributeModel.
     */
    @Test
    public void testGetType() {
        assertNotNull(model.getType());
        assertEquals(Common.TYPE_NAME, this.model.getType().getTypeName());
    }

    /**
     * Test of getName method, of class AttributeModel.
     */
    @Test
    public void testGetName() {
        assertNotNull(model.getName());
        assertEquals(Common.ATTRIBUTE_NAME, this.model.getName());
    }

    @Test
    public void testAnotations() {
        this.model.addAnotation(new AnotationModel(Common.ANOT1));
        this.model.addAnotation(new AnotationModel(Common.ANOT2));

        assertEquals(2, this.model.getAnotations().size());

        boolean isAnot1 = false, isAnot2 = false;
        for (IAnotation anot : this.model.getAnotations()) {
            if (anot.getName().equals(Common.ANOT1)) {
                isAnot1 = true;
            } else if (anot.getName().equals(Common.ANOT2)) {
                isAnot2 = true;
            } else {
                fail("There is anotation that I did not insert");
            }
        }

        assertTrue(isAnot1);
        assertTrue(isAnot1);

        this.model.addAnotation(null);
        assertEquals(2, this.model.getAnotations().size());

        this.model.addAnotation(new AnotationModel(Common.ANOT1));
        assertEquals(2, this.model.getAnotations().size());

        this.model.addAnotation(new AnotationModel(Common.ANOT2));
        assertEquals(2, this.model.getAnotations().size());
    }

}