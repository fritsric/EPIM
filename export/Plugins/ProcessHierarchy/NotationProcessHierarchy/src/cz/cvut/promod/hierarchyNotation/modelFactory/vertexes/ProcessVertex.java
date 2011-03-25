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

package cz.cvut.promod.hierarchyNotation.modelFactory.vertexes;

import cz.cvut.promod.hierarchyNotation.resources.Resources;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectDiagramChange;
import cz.cvut.promod.services.projectService.treeProjectNode.listener.ProjectDiagramListener;

import java.util.UUID;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:22:41, 16.4.2010
 *
 * User object for the process hierarchy vertexes.
 */
public class ProcessVertex implements ProjectDiagramListener{

    private static final String PROCESS_LABEL = Resources.getResources().getString("hierarchy.workspace.process");

    private String name;

    /**
     * UUID referring to other elements.
     */
    private UUID uuid;

    /**
     * Constructs a new Process Vertex having the default name.
     */
    public ProcessVertex(){
        setName(PROCESS_LABEL);
    }

    /**
     * Constructs a new Process Vertex.
     *
     * @param oldProcessVertex the old vertex
     * @param newName is the new name
     */
    public ProcessVertex(final ProcessVertex oldProcessVertex, final String newName){
        setName(newName);
        setUuid(oldProcessVertex.getUuid());
    }

    /**
     * @return the UUID of associated diagram
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return the name of the vertex
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the vertex
     * @param name the new name
     */
    public void setName(final String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Sets the associated UUID.
     * @param uuid the associated UUID
     */
    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Listens the associated diagram changes.
     * @param payload is the change info
     */
    public void changePerformed(final ProjectDiagramChange payload) {
        switch (payload.getChangeType()){
            case DISPLAY_NAME:
                if(payload.getOldValue() instanceof String){
                    if(getName().equals(payload.getOldValue())){
                        setName(payload.getProjectDiagram().getDisplayName());        
                    }
                }

                break;
            case REMOVED_FROM_NAVIGATION:
                setName("");
                setUuid(null);
                break;
            case PROJECT_DIAGRAM_REPLACED:
                setName(payload.getProjectDiagram().getDisplayName());
                setUuid(payload.getProjectDiagram().getUuid());
        }
    }
}
