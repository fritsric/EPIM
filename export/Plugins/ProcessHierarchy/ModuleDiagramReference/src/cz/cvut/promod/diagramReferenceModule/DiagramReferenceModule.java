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

package cz.cvut.promod.diagramReferenceModule;

import cz.cvut.promod.gui.settings.SettingPageData;
import cz.cvut.promod.hierarchyNotation.workspace.ProcessHierarchyWorkspaceData;
import cz.cvut.promod.plugin.notationSpecificPlugIn.DockableFrameData;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.Notation;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.NotationWorkspaceData;
import cz.cvut.promod.services.ModelerSession;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 15:53:19, 16.4.2010
 *
 * Implementation of DiagramReferenceModule plugin.
 */
public class DiagramReferenceModule implements cz.cvut.promod.plugin.notationSpecificPlugIn.module.Module{

    private static String IDENTIFIER_KEY = "identifier";

    private final Logger LOG = Logger.getLogger(DiagramReferenceModule.class);

    private final DiagramReferenceModuleModel model;


    public DiagramReferenceModule(final File file, final String notationIdentifier) throws InstantiationException, IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        final String identifier = properties.getProperty(IDENTIFIER_KEY);

        if(identifier == null){
            throw new InstantiationException();
        }

        model = new DiagramReferenceModuleModel(notationIdentifier, identifier);
    }

    public String getName() {
        return model.getName();
    }

    public String getDescription() {
        return model.getDescription();
    }

    public String getRelatedNotationIdentifier() {
        return model.getNotationIdentifier();
    }

    public Set<DockableFrameData> getDockableFrames() {
        return model.getDockableFrames();
    }

    public String getIdentifier() {
        return model.getIdentifier();
    }

    public void init() {
        initSelectionListener();
    }

    private void initSelectionListener() {
        final Notation notation = ModelerSession.getNotationService().getNotation(getRelatedNotationIdentifier());

        final NotationWorkspaceData workspace = notation.getNotationWorkspaceData();

        if(workspace instanceof ProcessHierarchyWorkspaceData){
            final ProcessHierarchyWorkspaceData workspaceData = (ProcessHierarchyWorkspaceData) workspace;

            workspaceData.getGraph().addGraphSelectionListener(model.getReferenceFrame());

            model.getReferenceFrame().setGraph(workspaceData.getGraph());

        } else {
            LOG.fatal("Invalid workspace component.");
        }
    }

    public void finish() {
        LOG.info("Diagram Reference Module is performing the finish sequence.");
    }

    public List<SettingPageData> getSettingPages() {
        return null;
    }

}
