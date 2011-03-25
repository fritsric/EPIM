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

package cz.cvut.promod.gui.dialogs.simpleTextFieldDialog.executors;

import cz.cvut.promod.gui.dialogs.simpleTextFieldDialog.SimpleTextFieldDialogExecutor;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.projectService.utils.ProjectServiceUtils;
import cz.cvut.promod.services.projectService.results.AddProjectItemResult;
import org.apache.log4j.Logger;

import javax.swing.tree.TreePath;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 0:38:55, 24.10.2009
 */
public class AddProjectSubFolderExecutor implements SimpleTextFieldDialogExecutor {

    private final Logger LOG = Logger.getLogger(AddProjectSubFolderExecutor.class);

    private static final String ILLEGAL_NAME_LABEL =
            ModelerSession.getCommonResourceBundle().getString("modeler.add.new.subfolder.dialog.error.disallowed");
    private static final String NAME_DUPLICATE_LABEL =
            ModelerSession.getCommonResourceBundle().getString("modeler.add.new.subfolder.dialog.error.nameduplicity");
    private static final String ERROR_LABEL =
            ModelerSession.getCommonResourceBundle().getString("modeler.add.new.subfolder.dialog.error");

    public String execute(final String text) {
        final AddProjectItemResult result = ModelerSession.getProjectControlService().addSubFolder(text, true);

        switch (result.getStatus()){
            case SUCCESS:
                mkdir(result.getTreePath());
                return null;
            case INVALID_NAME:
                return ILLEGAL_NAME_LABEL + ProjectServiceUtils.getDisallowedNameSymbols(',');
            case NAME_DUPLICITY:
                return NAME_DUPLICATE_LABEL;
            default:
                LOG.error("No such a result expected during project subfolder addition.");
                return ERROR_LABEL;
        }
    }

    /**
     * Make the dir structure.
     *
     * @param treePath is the tree path to the node to that is the dir structure supposed to make
     */
    private void mkdir(final TreePath treePath) {
        ModelerSession.getProjectControlService().synchronize(
                treePath,
                true, true, false, false
        );

    }

}
