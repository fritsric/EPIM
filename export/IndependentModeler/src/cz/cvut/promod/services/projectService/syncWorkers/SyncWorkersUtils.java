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

package cz.cvut.promod.services.projectService.syncWorkers;

import cz.cvut.promod.services.projectService.utils.ProjectServiceUtils;
import cz.cvut.promod.services.projectService.results.AddProjectItemStatus;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectItem;
import cz.cvut.promod.services.projectService.dialogs.SyncDialog;
import cz.cvut.promod.services.ModelerSession;

import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

import org.apache.log4j.Logger;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 0:37:58, 14.11.2009
 */

/**
 * Sync util class.
 */
public class SyncWorkersUtils {

    private static final Logger LOG = Logger.getLogger(SyncWorkersUtils.class);

    private static final String TREEPATH_ELEMENTS_DELIMITER = " -> ";

    private static final String TO_LABEL =
            ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.default.error.to");
    private static final String FROM_LABEL =
            ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.default.error.from");
    private static final String ERROR_LABEL =
            ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.default.error.message");
    private static final String INVALID_NAME_LABEL =
            ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.invalidname.error.message");

    /**
     * Report the sync error.
     *
     * @param addProjectItemStatus diagram/error status
     * @param fsSubtreeLocation FS location of the item
     * @param treePath location in the project navigation tree
     * @param toFS if true, the sync is from PN to FS, false in other way
     * @return error message
     */
    public static String reportDiagramError(final AddProjectItemStatus addProjectItemStatus,
                                            final File fsSubtreeLocation,
                                            final TreePath treePath,
                                            final boolean toFS) {
        String message = "";

        final String to;
        final String from;

        if(toFS){
            from = getMessageString(treePath);
            to = getMessageString(fsSubtreeLocation);
        } else {
            from = getMessageString(fsSubtreeLocation);  
            to = getMessageString(treePath);
        }

        switch (addProjectItemStatus){
            case SUCCESS:
                break;
            case INVALID_NAME:
                message = INVALID_NAME_LABEL;
                message += " " + ProjectServiceUtils.getDisallowedNameSymbols(',');
                break;
            default:
                message = ERROR_LABEL;

        }

        message +=  SyncDialog.NEW_LINE_MARK +
                    "   >> " + FROM_LABEL + " " +
                    from +
                    SyncDialog.NEW_LINE_MARK +
                    "   >> " + TO_LABEL + " " +
                    to;

        return message;
    }

    /**
     * @param file is a file on FS
     * @return the full file name
     */
    public static String getMessageString(final File file){
        return file.getAbsolutePath();
    }

    /**
     * @param treePath is location of a item in the project navigation
     * @return error message of the item
     */
    public static String getMessageString(final TreePath treePath){
        StringBuffer locationBuffer = new StringBuffer();

        final int itemNumber = treePath.getPathCount() - 1;

        for(int i = 1; i <= itemNumber; i++){

            try{
                ProjectItem projectItem = (ProjectItem) ((DefaultMutableTreeNode) treePath.getPathComponent(i)).getUserObject();

                locationBuffer.append(projectItem.getDisplayName());

                if(i != itemNumber){
                    locationBuffer.append(TREEPATH_ELEMENTS_DELIMITER);
                }

            } catch (Exception exception){
                LOG.error("Illegal node in project tree.");
                locationBuffer = new StringBuffer(
                        ModelerSession.getCommonResourceBundle().getString("project.service.sync.dialog.treepatherror"));

                break;
            }
        }

        return locationBuffer.toString();
    }

}