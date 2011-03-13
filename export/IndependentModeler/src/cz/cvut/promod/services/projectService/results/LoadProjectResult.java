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

package cz.cvut.promod.services.projectService.results;

import cz.cvut.promod.services.projectService.treeProjectNode.ProjectRoot;

import java.util.List;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:32:57, 8.2.2010
 */

/**
 * Class for representing a return value of loadProject(String) method defined in ProjectControlService interface.
 *
 * If the ProjectRoot and event the message are null, it was not possible to load file. Possible reason is not valid
 * project file.
 */
public class LoadProjectResult{

    private final ProjectRoot projectRoot;

    private final List<ConfigurationDifference> messages;


    public LoadProjectResult(final ProjectRoot projectRoot, final List<ConfigurationDifference> messages) {
        this.projectRoot = projectRoot;
        this.messages = messages;
    }

    /**
     * Returns the project root.
     *
     * @return the loaded project root.
     */
    public ProjectRoot getProjectRoot() {
        return projectRoot;
    }

    /**
     * Returns list of messages about ProMod configuration differences.
     *
     * @return list of messages about ProMod configuration differences
     */
    public List<ConfigurationDifference> getMessages() {
        return messages;
    }
}
