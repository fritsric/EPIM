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

import cz.cvut.promod.EPC2XHTMLExport.ui.ExporterSettingsModel;
import cz.cvut.promod.epc.workspace.EPCWorkspaceData;
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
public class NodeTest {

    private Node testNodeFrom;
    private Node testNodeTo;

    @Before
    public void startUp() {
        this.testNodeFrom = new Node(Node.Type.eApplicationSoftware, "testNodeFrom", "name", "note");
        this.testNodeTo = new Node(Node.Type.eMessage, "testNodeTo", "name", "note");
    }

    @Test
    public void testType() {
        // test only few of them
        assertNotNull(Node.Type.valueOf("eApplicationSoftware"));
        assertNotNull(Node.Type.valueOf("eComputerHW"));
        assertNotNull(Node.Type.valueOf("eInformationObject"));
        assertNotNull(Node.Type.valueOf("eOrganizationRole"));
        assertNotNull(Node.Type.valueOf("eOrganizationUnit"));
    }

    @Test
    public void testTypeEquals() {
        Node node;
        node = new Node(Node.Type.eApplicationSoftware, "ABC", "ABC", "ABC");
        assertEquals(Node.Type.eApplicationSoftware, node.getType());
        
        node = new Node(Node.Type.eGoal, "0000-0000-0000-0000", "", "");
        assertEquals(Node.Type.eGoal, node.getType());

        node = new Node(Node.Type.eOrganizationUnit, "1234567890", "A very long node name", "Some interesting note");
        assertEquals(Node.Type.eOrganizationUnit, node.getType());
    }

    @Test
    public void testUUIDEquals() {
        Node node;
        node = new Node(Node.Type.eApplicationSoftware, "ABC", "ABC", "ABC");
        assertEquals("ABC", node.getUUID());

        node = new Node(Node.Type.eGoal, "0000-0000-0000-0000", "", "");
        assertEquals("0000-0000-0000-0000", node.getUUID());

        node = new Node(Node.Type.eOrganizationUnit, "1234567890", "A very very long node name", "Some interesting note");
        assertEquals("1234567890", node.getUUID());

        node = new Node(Node.Type.eGoal, "!@#$%^&*()", "", "");
        assertEquals("!@#$%^&*()", node.getUUID());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentException() { // UUID cannot be null!
        Node node;
        node = new Node(Node.Type.eGoal, null, "", "");
    }

    @Test
    public void testNameEquals() {
        Node node;
        node = new Node(Node.Type.eApplicationSoftware, "ABC", "ABC", "ABC");
        assertEquals("ABC", node.getName());

        node = new Node(Node.Type.eGoal, "", "", "");
        assertEquals("", node.getName());

        node = new Node(Node.Type.eGoal, "", null, "");
        assertEquals(null, node.getName());

        node = new Node(Node.Type.eOrganizationUnit, "1234567890", "A very very long node name", "Some interesting note");
        assertEquals("A very very long node name", node.getName());

        node = new Node(Node.Type.eGoal, "", "!@#$%^&*()", "");
        assertEquals("!@#$%^&*()", node.getName());
    }

    @Test
    public void testNoteEquals() {
        Node node;
        node = new Node(Node.Type.eApplicationSoftware, "ABC", "ABC", "ABC");
        assertEquals("ABC", node.getNote());

        node = new Node(Node.Type.eGoal, "", "", "");
        assertEquals("", node.getNote());

        node = new Node(Node.Type.eGoal, "", "", null);
        assertEquals(null, node.getNote());

        node = new Node(Node.Type.eOrganizationUnit, "1234567890", "A very very long node name", "Some interesting note");
        assertEquals("Some interesting note", node.getNote());

        node = new Node(Node.Type.eGoal, "", "", "!@#$%^&*()");
        assertEquals("!@#$%^&*()", node.getNote());

        node = new Node(Node.Type.eGoal, "", "", "Note\nwith\nmultiple\nrows");
        assertEquals("Note\nwith\nmultiple\nrows", node.getNote());
    }

    @Test
    public void testInLinkEquals() {
        Link link1 = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo,  "node name", "node note");
        Link link2 = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo,  "node name", "node note");
        this.testNodeFrom.addInLink(link1);
        this.testNodeFrom.addInLink(link2);

        ArrayList<Link> links = this.testNodeFrom.getInLinks();
        assertEquals(link1, links.get(0));
        assertEquals(link2, links.get(1));
    }

    @Test
    public void testOutLinkEquals() {
        Link link1 = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo,  "node name", "node note");
        Link link2 = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo,  "node name", "node note");
        this.testNodeFrom.addOutLink(link1);
        this.testNodeFrom.addOutLink(link2);

        ArrayList<Link> links = this.testNodeFrom.getOutLinks();
        assertEquals(link1, links.get(0));
        assertEquals(link2, links.get(1));
    }
}
