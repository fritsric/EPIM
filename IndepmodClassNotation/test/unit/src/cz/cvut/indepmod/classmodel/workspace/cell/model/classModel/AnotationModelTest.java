/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.Common;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucky
 */
public class AnotationModelTest {

    AnotationModel model;

    @Before
    public void setUp() {
        this.model = new AnotationModel(Common.ANOT1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class AnotationModel.
     */
    @Test
    public void testGetName() {
        assertNotNull(this.model.getName());
        assertEquals(Common.ANOT1, this.model.getName());
    }

    /**
     * Test of getAttributes method, of class AnotationModel.
     */
    @Test
    public void testAddGetAttributes() {
        AnotationAttributeModel atr1 = new AnotationAttributeModel(Common.ATTRIBUTE_NAME);
        AnotationAttributeModel atr2 = new AnotationAttributeModel(Common.ATTRIBUTE_NAME2);
        AnotationAttributeModel atr1X = new AnotationAttributeModel(Common.ATTRIBUTE_NAME);

        assertEquals(0, this.model.getAttributes().size());

        this.model.addAttribute(atr1);
        this.model.addAttribute(atr2);

        assertEquals(2, this.model.getAttributes().size());

        boolean isAtr1 = true, isAtr2 = false;
        for (IAnotationValue val : this.model.getAttributes()) {
            if (val.getName().equals(Common.ATTRIBUTE_NAME)) {
                isAtr1 = true;
            } else if (val.getName().equals(Common.ATTRIBUTE_NAME2)) {
                isAtr2 = true;
            } else {
                fail("There is another Anotation Value!");
            }
        }

        assertTrue(isAtr1);
        assertTrue(isAtr2);

        this.model.addAttribute(atr1X);
        assertEquals(2, this.model.getAttributes().size());

        this.model.addAttribute(null);
        assertEquals(2, this.model.getAttributes().size());
    }
}