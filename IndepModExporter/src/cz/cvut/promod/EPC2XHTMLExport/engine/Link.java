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

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Oct 6, 2010
 * Time: 4:03:41 PM
 *
 * Represents a link between two nodes (@see Node) in the @see Diagram.
 * This link has a type, specified in the public Type enumeration. The
 * constructor servers as the main setter while other methods are getters
 * for each separate member of this class. 
 */
public class Link {

    public enum Type {
        eControl,
        eOrganization,
        eInformation,
        eInformationService,
        eMaterial
    }

    protected Type m_type;
    protected Node m_from;
    protected Node m_to;
    protected String m_name;
    protected String m_note;

    public Link(Type type, Node from, Node to, String name, String note) {
        m_type = type;
        m_from = from;
        m_to = to;
        m_name = name;
        m_note = note;
    }

    public Type getType() {
        return m_type;
    }

    public Node getFrom() {
        return m_from;
    }

    public Node getTo() {
        return m_to;
    }

    public String getName() {
        return m_name;
    }

    public String getNote() {
        return m_note;
    }
}
