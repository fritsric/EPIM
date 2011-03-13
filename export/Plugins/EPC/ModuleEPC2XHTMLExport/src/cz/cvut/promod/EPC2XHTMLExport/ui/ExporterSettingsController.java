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

package cz.cvut.promod.EPC2XHTMLExport.ui;

import com.jgoodies.binding.PresentationModel;
import cz.cvut.promod.EPC2XHTMLExport.EPC2XHTMLExportModule;
import cz.cvut.promod.EPC2XHTMLExport.engine.ExporterEngine;
import cz.cvut.promod.EPC2XHTMLExport.resources.Resources;
import cz.cvut.promod.epc.workspace.EPCWorkspaceData;
import cz.cvut.promod.gui.support.utils.NotationGuiHolder;
import cz.cvut.promod.plugin.notationSpecificPlugIn.DockableFrameData;
import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.services.actionService.actionUtils.ProModAction;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectDiagram;
import cz.cvut.promod.services.projectService.treeProjectNode.ProjectRoot;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Oct 6, 2010
 * Time: 4:05:38 PM
 *
 * This is the controller (see MVC pattern) of the only part of this module that interacts
 * with the user. The Exporter Settings dockable window gathers user input to alter the way
 * the diagram is exported. The controller creates the view and model part and serves as a
 * mean of communication between them and the ExporterEngine. It interprets the data the user
 * has given to the view class, saves it in the model and if the input is valid it is forwarded
 * to the ExporterEngine, which produces output.
 */
public class ExporterSettingsController extends ExporterSettingsView
                                        implements DockableFrameData,
                                                   ActionListener {

    private static final Logger LOG = Logger.getLogger(ExporterSettingsController.class);

    private final static String FRAME_TITLE_RES = "epc.export.xhtml.framelabel";
    private final static String ERR_DIALOG_TITLE_RES = "epc.export.xhtml.error.title";
    private final static String ERR_DIALOG_MESSAGE_RES = "epc.export.xhtml.error.message";
    private final static String ERR_DIALOG_TITLE_OPEN_RES = "epc.export.xhtml.error.title.open";
    private final static String ERR_DIALOG_MESSAGE_OPEN_RES = "epc.export.xhtml.error.message.open";
    private final static String SUC_DIALOG_TITLE_RES = "epc.export.xhtml.success.title";
    private final static String SUC_DIALOG_MESSAGE_RES = "epc.export.xhtml.success.message";

    private final static String CONFIRM_DIALOG_MESSAGE_OVEWRITE = "epc.export.xhtml.confirm.message";
    private final static String CONFIRM_DIALOG_TITLE_OVEWRITE = "epc.export.xhtml.confirm.title";

    private static final String CFG_FORMAT_RES = "epc.export.xhtml.config.format";
    private static final String CFG_THEME_RES = "epc.export.xhtml.config.theme";
    private static final String CFG_NOTES_RES = "epc.export.xhtml.config.notes";
    private static final String CFG_OPENWITHCHOICE_RES = "epc.export.xhtml.config.openwithchoice";
    private static final String CFG_OPENWITHPATH_RES = "epc.export.xhtml.config.openwithpath";

    private String EPC2XHTML_EXPORT_ACTION_RES;

    private ExporterSettingsView view;
    private ExporterSettingsModel model;
    private final EPCWorkspaceData workspace;

    private ProModAction action;

    private final PresentationModel<ExporterSettingsModel> presentationModel;

    /**
     * The constructor creates the view component and registers as its listener, it also creates
     * the model component initializes the received actions.
     *
     * @param epcWorkspaceData - graph data
     * @param actions          - actions related to this module, includes the export action
     * @param EPC2XHTML_EXPORT_ACTION_RES - resource identifier of the export action
     */
    public ExporterSettingsController(final EPCWorkspaceData epcWorkspaceData,
                                      final Map<String, ProModAction> actions,
                                      final String EPC2XHTML_EXPORT_ACTION_RES) {

        this.EPC2XHTML_EXPORT_ACTION_RES = EPC2XHTML_EXPORT_ACTION_RES;

        model = new ExporterSettingsModel();
        view  = new ExporterSettingsView();
        view.registerListener(this);
        workspace = epcWorkspaceData;

        presentationModel = new PresentationModel<ExporterSettingsModel>(model);

        initActions(actions);
    }


    private void initActions(final Map<String, ProModAction> actions) {
        action = new ProModAction(
                Resources.getStrRes(EPC2XHTML_EXPORT_ACTION_RES),
                null,
                null) {

            public void actionPerformed(ActionEvent event) {
                export();
            }
        };
        actions.put(EPC2XHTML_EXPORT_ACTION_RES, action);
    }

    /**
     * @obsolete
     */
    public String getDockableFrameName() {
        return "epc.xhtml.export.xyz";
    }

    /**
     * @return the view component for display purposes
     */
    public JComponent getDockableFrameComponent() {
        return view;
    }

    /**
     * @return the initial position of the dockable frame in which the view component resides
     */
    public NotationGuiHolder.Position getInitialPosition() {
        return NotationGuiHolder.Position.BOTTOM;
    }

    /**
     * @return whether the windows is maximalizable or not
     */
    public boolean isMaximizable() {
        return false;
    }

    /**
     * @return all available docking positions of the dockable frame
     */
    public Set<NotationGuiHolder.Position> getAllowedDockableFramePositions() {
        final Set<NotationGuiHolder.Position> positions = new HashSet<NotationGuiHolder.Position>();
        positions.add(NotationGuiHolder.Position.BOTTOM);
        positions.add(NotationGuiHolder.Position.TOP);

        return positions;
    }

    /**
     * @returns the InitialState (visibility) of the dockable frame
     */
    public InitialState getInitialState() {
        return InitialState.HIDDEN;
    }

    /**
     * @return the title of the dockable frame
     */
    public String getDockableFrameTitle() {
        return Resources.getStrRes(FRAME_TITLE_RES);
    }

    public Icon getButtonIcon() {
        return null;
    }

    public PresentationModel<ExporterSettingsModel> getPresentationModel() {
        return presentationModel;
    }


    /**
     * Callback from the view component when the user chooses to initiate the export action.
     * This saves the state of the gui, saving the choices of the user for future use (this
     * persists even through application restart). The export can fail for many reasons, and
     * and all results are handled to the user via dialog messages. The post export action
     * (e.g. opening the exported document using the default application) is also handled here.
     */
    public void actionPerformed(ActionEvent e) {
        export();
    }

    private void export() {
        saveConfig();

        model.setPath(view.getDir() + System.getProperty("file.separator"));
        model.setName(view.getName());
        model.setNotes(view.getNotes());
        model.setFormat(view.getFormat());
        model.setTheme(view.getTheme());
        model.setOpenWithChoice(view.getOpenWithChoice());
        model.setOpenWithPath(view.getOpenWithPath());

        ProjectDiagram diagram = ModelerSession.getProjectService().getSelectedDiagram();
        if (diagram == null) 
            model.setTitle("null");
        else
            model.setTitle(diagram.getDisplayName());

        ExporterEngine engine = new ExporterEngine(workspace, model);

        // test whether the file already exists
        if(engine.isColliding()){
            LOG.info("epc to xhtml export detected existing file(s) collision");

            if(
                    JOptionPane.showConfirmDialog(
                            view,
                            Resources.getStrRes(CONFIRM_DIALOG_MESSAGE_OVEWRITE),
                            Resources.getStrRes(CONFIRM_DIALOG_TITLE_OVEWRITE),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    ) == JOptionPane.NO_OPTION
            ){
                LOG.info("epc to xhtml export canceled because of existing file(s) collision");
                return;
            }
            LOG.info("epc to xhtml export continues");
        }

        // attempts to export the diagram using previously chosen settings
        try{
            engine.export();
        }catch (IOException ex){
            String message = ex.getMessage();
            if (message == null || message.isEmpty())
                message = Resources.getStrRes(ERR_DIALOG_MESSAGE_RES);
            JOptionPane.showMessageDialog(
                    view,
                    message,
                    Resources.getStrRes(ERR_DIALOG_TITLE_RES),
                    JOptionPane.ERROR_MESSAGE);
            LOG.error("epc to xhtml export failed");
            return;
        }


        int open = view.getOpenWithChoice();
        String url = model.getPath() + model.getName();

        switch (model.getFormat()) {
            case eXHTMLFolder:
                url += ".html";
                break;
            case eDokuWiki:
                url += ".txt";
                break;
            case eLaTeXFolder:
                url += ".tex";
                break;
        }

        // performs the post export action
        if (open == 0) {
            JOptionPane.showMessageDialog(
                    view,
                    Resources.getStrRes(SUC_DIALOG_MESSAGE_RES),
                    Resources.getStrRes(SUC_DIALOG_TITLE_RES),
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (open == 1) {
            boolean success = false;
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop dt = Desktop.getDesktop();
                    if (dt.isSupported(Desktop.Action.OPEN)) {
                        dt.open(new File(url));
                        success = true;
                    }
                }
            } catch (IOException ioe) {}
            if (!success) {
                JOptionPane.showMessageDialog(
                    view,
                    Resources.getStrRes(ERR_DIALOG_MESSAGE_OPEN_RES),
                    Resources.getStrRes(ERR_DIALOG_TITLE_OPEN_RES),
                    JOptionPane.ERROR_MESSAGE);
                LOG.error("could not open default the file in the default application!");
            }
        } else {
            String osName = System.getProperty("os.name");

            try {
                if (osName.startsWith("Mac OS")) {
                    // NOT TESTED
                    Runtime.getRuntime().exec(view.getOpenWithPath() + " \"" + url +"\"");
                } else if (osName.startsWith("Windows")) {
                    // TESTED WITH firefox, chrome
                    Runtime.getRuntime().exec(view.getOpenWithPath() + " \"" + url +"\"");
                } else { //assume Unix or Linux
                    // NOT TESTED
                    Runtime.getRuntime().exec(view.getOpenWithPath() + " \"" + url +"\"");
                }
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(
                    view,
                    Resources.getStrRes(ERR_DIALOG_MESSAGE_OPEN_RES),
                    Resources.getStrRes(ERR_DIALOG_TITLE_OPEN_RES),
                    JOptionPane.ERROR_MESSAGE);
                LOG.error("could not open url with specified application");
            }
        }
        LOG.info("epc to xhtml export succeeded");


    }

    /**
     * Loads persistent GUI data from the properties file. This mean that the user
     * will not have to choose the same choices all over again when (s)he needs to repeat a task.
     */
    public void loadConfig() {
        // not sure if this actually returns information about the selected or opened project/diagram
        // it does not work as expected for now (configs do, just the project/diagram part does not)
        ProjectRoot project    = ModelerSession.getProjectService().getSelectedProject();
        ProjectDiagram diagram = ModelerSession.getProjectService().getSelectedDiagram();

        if (project != null)
            view.setDir(project.getProjectLocation());
        else
            view.setDir(System.getProperty("user.home"));

        if (project!= null && diagram != null)
            view.setName(project.getDisplayName() + "_" + diagram.getDisplayName());
        else
            view.setName("epcdiagram");


        String cfg;
        int i;
        Properties properties = EPC2XHTMLExportModule.properties;

        cfg = properties.getProperty(CFG_FORMAT_RES);
        if (cfg != null && !cfg.isEmpty()) {
            i = (int)cfg.charAt(0) - 48;
            ExporterSettingsModel.ExportFormat formats[] = ExporterSettingsModel.ExportFormat.values();
            if (i < 0 || i > formats.length)
                LOG.error("ERROR: invalid property '" + CFG_FORMAT_RES + "' value '" + i + "'");
            else
                view.setFormat(formats[i]);
        }

        cfg = properties.getProperty(CFG_THEME_RES);
        if (cfg != null && !cfg.isEmpty()) {
            i = (int)cfg.charAt(0) - 48;
            if (i > 0 && i < 2)
                view.setTheme(i);
        }

        cfg = properties.getProperty(CFG_NOTES_RES);
        if (cfg != null && !cfg.isEmpty()) {
            i = (int)cfg.charAt(0) - 48;
            if (i == 0)
                view.setNotes(false);
            if (i == 1)
                view.setNotes(true);
        }

        cfg = properties.getProperty(CFG_OPENWITHCHOICE_RES);
        if (cfg != null && !cfg.isEmpty()) {
            i = (int)cfg.charAt(0) - 48;
            view.setOpenWithChoice(i);
        }

        cfg = properties.getProperty(CFG_OPENWITHPATH_RES);
        if (cfg != null && !cfg.isEmpty())
            view.setOpenWithPath(cfg);
    }

    /**
     * Saves the persistent GUI data, @see loadConfig() 
     */
    public void saveConfig() {

        Properties properties = EPC2XHTMLExportModule.properties;

        String cfg;

        switch (view.getFormat()) {
            case eXHTMLFolder:
                cfg = "0";
                break;
            case eDokuWiki:
                cfg = "1";
                break;
            default:
                cfg = "2";
                break;
        }
        properties.setProperty(CFG_FORMAT_RES, cfg);

        switch (view.getTheme()) {
            case eClassicBlack:
                cfg = "0";
                break;
            default:
                cfg = "3";
                break;
        }
        properties.setProperty(CFG_THEME_RES, cfg);

        cfg = view.getNotes() ? "1" : "0";
        properties.setProperty(CFG_NOTES_RES, cfg);

        cfg = Integer.toString(view.getOpenWithChoice());
        properties.setProperty(CFG_OPENWITHCHOICE_RES, cfg);

        cfg = view.getOpenWithPath();
        if (cfg.isEmpty()) cfg = "Choose an application...";
        properties.setProperty(CFG_OPENWITHPATH_RES, cfg);

        try {
            properties.store(new FileOutputStream(EPC2XHTMLExportModule.propertiesFile), "");
        }
        catch (Exception e) {
            LOG.error("failed to store config!");
        }
    }
}
