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

package cz.cvut.promod.plugin.notationSpecificPlugIn.notation.model;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:27:56, 31.10.2009
 */

/**
 * DiagramModel interface is base for all class holding actual diagram. Every diagram is represented by an instance
 * of ProjectDiagram, which basically represents an item in project tree navigation.
 *
 * DiagramModel is supposed to be a holder for actual diagram data like vertexes, connections (edges), etc.
 */
public interface DiagramModel {

    /**
     * Adds a listener of any change that has occurred in diagram.
     * ProjectDiagram registers a listener during initialization time to be informed about all changes
     * in diagram and then change state of it's 'changed' variable.
     *
     * @see cz.cvut.promod.services.projectService.treeProjectNode.ProjectDiagram for more info 
     *
     * @param listener is an instance of DiagramModelChangeListener class that will be notified when any change in
     * an instance of DiagramModel occurs
     */
    public void addChangeListener(final DiagramModelChangeListener listener);

    /**
     * This method is invoked on the diagram model when the actual model is being changed and this diagram
     * is going to be active.
     *
     * Can be used e.g. for initial settings and initialization of event handling mechanism when
     * the diagram is opened for the first time - e.g. after load.
     */
    public void update();

    /**
     * This method is invoked on the diagram when the actual model is being changed and this diagram is going
     * to be inactive.
     */
    public void over();

}
