/****************************************************************************
** This file may be used under the terms of the MIT licence:
**
** Permission is hereby granted, free of charge, to any person obtaining a copy
** of this software and associated documentation files (the "Software"), to deal
** in the Software without restriction, including without limitation the rights
** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
** copies of the Software, and to permit persons to whom the Software is
** furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included in
** all copies or substantial portions of the Software.
**
** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
** THE SOFTWARE.
****************************************************************************/

package cz.cvut.promod.EPC2XHTMLExport.engine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ondrej Machulda
 * Date: 7.11.2010
 * Time: 17:05:28
 */
public class NodeLogicTest {

    //private NodeLogic logic;

    @Before
    public void startUp() {
        //this.logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", logicType, "cond1", "cond2")
    }

    @Test
    public void testLogicType() {
        assertNotNull(NodeLogic.LogicType.valueOf("eAND"));
        assertNotNull(NodeLogic.LogicType.valueOf("eOR"));
        assertNotNull(NodeLogic.LogicType.valueOf("eXOR"));
        assertNotNull(NodeLogic.LogicType.valueOf("eAND_OR"));
        assertNotNull(NodeLogic.LogicType.valueOf("eAND_XOR"));
        assertNotNull(NodeLogic.LogicType.valueOf("eOR_AND"));
        assertNotNull(NodeLogic.LogicType.valueOf("eOR_XOR"));
        assertNotNull(NodeLogic.LogicType.valueOf("eXOR_AND"));
        assertNotNull(NodeLogic.LogicType.valueOf("eXOR_OR"));
    }

    @Test
    public void testLogicTypeEquals() {
        NodeLogic logic;

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "cond1", "cond2");
        assertEquals(NodeLogic.LogicType.eAND, logic.getLogicType());
        
        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND_XOR, "cond1", "cond2");
        assertEquals(NodeLogic.LogicType.eAND_XOR, logic.getLogicType());

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eXOR_OR, "cond1", "cond2");
        assertEquals(NodeLogic.LogicType.eXOR_OR, logic.getLogicType());
    }

    @Test
    public void testCond1Equals() {
        NodeLogic logic;

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "ABC", "");
        assertEquals("ABC", logic.getCondition1());

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "", "");
        assertEquals("", logic.getCondition1());

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, null, "");
        assertEquals(null, logic.getCondition1());

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "A very long \"enquoted\" condition", "");
        assertEquals("A very long \"enquoted\" condition", logic.getCondition1());

    }

    @Test
    public void testCond2Equals() {
        NodeLogic logic;

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "", "ABC");
        assertEquals("ABC", logic.getCondition2());

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "", "");
        assertEquals("", logic.getCondition2());

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "", null);
        assertEquals(null, logic.getCondition2());

        logic = new NodeLogic(Node.Type.eApplicationSoftware, "UUID", "name", "note", NodeLogic.LogicType.eAND, "", "A very long \"enquoted\" condition");
        assertEquals("A very long \"enquoted\" condition", logic.getCondition2());

    }


}
