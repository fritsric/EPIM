package cz.cvut.indepmod.classmodel.toolchooser;

import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.ToolChooserModelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 26.9.2010
 * Time: 13:23:06
 * <p/>
 * This Class represents the Tool Chooser. User will choose the tool (e.g. tool for adding a new class or relation
 * between two classes) they want to use.
 */
public class ToolChooser extends ToolChooserView {

    private static final String PREFERRED_ID = "ToolChooserView";
    private static final String DISPLAY_NAME = "Tool Chooser";
    private static final Logger LOG = Logger.getLogger(ToolChooser.class.getName());
    private static ToolChooser instance;
    private ToolChooserModel model;
    private Lookup.Result<ToolChooserModel> modelLookup;
    private ToolChooserModelLookupListener toolChooserLookupLsnr;
    private ToolChooserModelChangeListener toolChooserChangeLsnr;

    private ToolChooser() {
        this.model = null;
        this.toolChooserLookupLsnr = new ToolChooserModelLookupListener();
        this.toolChooserChangeLsnr = new ToolChooserModelChangeListener();
        this.setDisplayName(DISPLAY_NAME);
        this.disableToolChooser();

        this.initActions();
    }

    @Override
    public List<Mode> availableModes(List<Mode> modes) {
        for (Mode mode : modes) {
            if (!mode.getName().equals("rightSlidingSide")) {
                modes.remove(mode);
            }
        }
        return modes;
    }

    public ToolChooserModel getModel() {
        return model;
    }

    public void setModel(ToolChooserModel model) {
        this.model = model;
    }

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized ToolChooser getDefault() {
        if (instance == null) {
            instance = new ToolChooser();
        }
        return instance;
    }

    /**
     * Obtain the MyViewerTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ToolChooser findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            LOG.warning("Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ToolChooser) {
            return (ToolChooser) win;
        }
        LOG.warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public void open() {
        Mode m = WindowManager.getDefault().findMode("properties");
        if (m != null) {
            m.dockInto(this);
        }
        super.open();
    }

    @Override
    protected void componentClosed() {
        this.modelLookup.removeLookupListener(this.toolChooserLookupLsnr);
        this.modelLookup = null;
    }

    @Override
    protected void componentOpened() {
        this.modelLookup = Utilities.actionsGlobalContext().lookup(new Lookup.Template<ToolChooserModel>(ToolChooserModel.class));
        this.modelLookup.addLookupListener(this.toolChooserLookupLsnr);
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    private void initActions() {
        this.controllButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("controll tool choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_CONTROLL);
            }
        });

        this.addClassButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("addClass tool choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_CLASS);
            }
        });

        this.addInterfaceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("addInterface tool choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_INTERFACE);
            }
        });

        this.addEnumerationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("addEnumeration tool choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_ENUMERATION);
            }
        });

        this.addRelationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("addRelation choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_RELATION);
            }
        });

        this.addGeneralizationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("add inheritance choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_GENERALIZATION);
            }
        });

        this.addRealisationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("add realisation choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_REALISATION);
            }
        });

        this.addCompositionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("add composition choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_COMPOSITION);
            }
        });

        this.addAgregationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LOG.fine("add agregation choosed");
                model.setSelectedTool(ToolChooserModel.Tool.TOOL_ADD_AGREGATION);
            }
        });
    }

    private class ToolChooserModelLookupListener implements LookupListener {

        @Override
        public void resultChanged(LookupEvent le) {
            Lookup.Result r = (Lookup.Result) le.getSource();
            Collection c = r.allInstances();
            if (c.isEmpty()) {
                LOG.fine("There is no model, i am not changing it.");
                //model.removeListener(toolChooserChangeLsnr);
                //model = null;
            } else {
                LOG.fine("There is a model, i am switching to it.");
                if (model != null) {
                    model.removeListener(toolChooserChangeLsnr);
                }

                ToolChooserModel toolModel = (ToolChooserModel) c.iterator().next();
                model = toolModel;
                model.addListener(toolChooserChangeLsnr);
                enableToolChooser();
                toolChooserChangeLsnr.selectedToolChanged(model.getSelectedTool());
            }
        }
    }

    private class ToolChooserModelChangeListener implements ToolChooserModelListener {

        @Override
        public void selectedToolChanged(ToolChooserModel.Tool newTool) {
            ToolChooserModel.Tool tool = newTool;
            switch (tool) {
                case TOOL_CONTROLL:
                    controllButton.doClick();
                    break;
                case TOOL_ADD_CLASS:
                    addClassButton.doClick();
                    break;
                case TOOL_ADD_INTERFACE:
                    addInterfaceButton.doClick();
                    break;
                case TOOL_ADD_ENUMERATION:
                    addEnumerationButton.doClick();
                    break;
                case TOOL_ADD_RELATION:
                    addRelationButton.doClick();
                    break;
                case TOOL_ADD_COMPOSITION:
                    addCompositionButton.doClick();
                    break;
                case TOOL_ADD_AGREGATION:
                    addAgregationButton.doClick();
                    break;
                case TOOL_ADD_GENERALIZATION:
                    addGeneralizationButton.doClick();
                    break;
                case TOOL_ADD_REALISATION:
                    addRealisationButton.doClick();
                    break;
                default:
                    LOG.severe("Unknown tool was selected!");
            }
        }
    }
}
