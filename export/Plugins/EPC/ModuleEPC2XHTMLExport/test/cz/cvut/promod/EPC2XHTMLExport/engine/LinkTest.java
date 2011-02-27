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
public class LinkTest {

    private Node testNodeFrom;
    private Node testNodeTo;

    @Before
    public void startUp() {
        this.testNodeFrom = new Node(Node.Type.eApplicationSoftware, "testNodeFromUUID", "name", "note");
        this.testNodeTo = new Node(Node.Type.eEvent, "testNodeToUUID", "name", "note");
    }

    @Test
    public void testType() {
        assertNotNull(Link.Type.valueOf("eControl"));
        assertNotNull(Link.Type.valueOf("eOrganization"));
        assertNotNull(Link.Type.valueOf("eInformation"));
        assertNotNull(Link.Type.valueOf("eInformationService"));
        assertNotNull(Link.Type.valueOf("eMaterial"));
    }

    @Test
    public void testTypeEquals() {
        Link link;
        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name", "note");
        assertEquals(Link.Type.eControl, link.getType());

        link = new Link(Link.Type.eInformation, this.testNodeFrom, this.testNodeTo, "name", "note");
        assertEquals(Link.Type.eInformation, link.getType());

        link = new Link(Link.Type.eMaterial, this.testNodeFrom, this.testNodeTo, "name", "note");
        assertEquals(Link.Type.eMaterial, link.getType());
    }

    @Test
    public void testToLinkEquals() {
        Link link;
        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name", "note");
        assertEquals(this.testNodeTo, link.getTo());
    }

    @Test
    public void testFromLinkEquals() {
        Link link;
        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name", "note");
        assertEquals(this.testNodeFrom, link.getFrom());
    }

    @Test
    public void testNameEquals() {
        Link link;
        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "ABC", "note");
        assertEquals("ABC", link.getName());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, null, "note");
        assertEquals(null, link.getName());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "", "note");
        assertEquals("", link.getName());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo,
                        "A very very long \"enquoted\" node name", "note");
        assertEquals("A very very long \"enquoted\" node name", link.getName());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "!@#$%^&*()", "note");
        assertEquals("!@#$%^&*()", link.getName());
    }

    @Test
    public void testNoteEquals() {
        Link link;
        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name", "ABC");
        assertEquals("ABC", link.getNote());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name", null);
        assertEquals(null, link.getNote());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name", "");
        assertEquals("", link.getNote());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name",
                        "A very very long \"enquoted\" note\nwith\nmultiple\nrows");
        assertEquals("A very very long \"enquoted\" note\nwith\nmultiple\nrows", link.getNote());

        link = new Link(Link.Type.eControl, this.testNodeFrom, this.testNodeTo, "name", "!@#$%^&*()");
        assertEquals("!@#$%^&*()", link.getNote());
    }

}
