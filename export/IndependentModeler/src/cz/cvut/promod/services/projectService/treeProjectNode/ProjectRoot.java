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

package cz.cvut.promod.services.projectService.treeProjectNode;

import cz.cvut.promod.services.projectService.ProjectService;

import java.io.File;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 21:02:45, 13.10.2009
 */

/**
 * Represents the project root object.
 */
public final class ProjectRoot implements ProjectContainer {

    private String displayName;

    /* location of the project file (having ProjectService.PROJECT_FILE_EXTENSION_NAME extension) is not taken into
        account during saving the project root */ 
    transient private String projectLocation;


    /**
     * Constructs a new instance of ProjectRoot class.
     *
     * @param displayName is the project name (will be trimmed)
     * @param projectLocation is the project location on the file system (will be trimmed)
     */
    public ProjectRoot(final String displayName, final String projectLocation){
        if(displayName != null){
            this.displayName = displayName.trim();
        }
        
        if(projectLocation != null){
            this.projectLocation = projectLocation.trim();
        }
    }

    /** {@inheritDoc} */
    public void setDisplayName(final String displayName) {
        this.displayName = displayName.trim();
    }

    /** {@inheritDoc} */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return the project file system location
     */
    public String getProjectLocation() {
        return projectLocation;
    }

    /**
     * Sets the file system location
     *
     * @param projectLocation is the new project file system location
     */
    public void setProjectLocation(final String projectLocation) {
        this.projectLocation = projectLocation.trim();
    }

    /**
     * Returns the project file.
     *
     * @return the project file
     */
    public File getProjectFile(){
        return new File(getProjectLocation(), getDisplayName() + ProjectService.PROJECT_FILE_EXTENSION);
    }

    @Override
    public String toString(){
        return getDisplayName();
    }

}