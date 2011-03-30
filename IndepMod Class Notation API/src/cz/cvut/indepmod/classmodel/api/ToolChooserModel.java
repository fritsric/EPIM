package cz.cvut.indepmod.classmodel.api;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 26.9.2010
 * Time: 12:49:17
 */
//TODO - get static string data from resource bundle!
public class ToolChooserModel {

    public static final String TOOL_CHOOSER_TITLE = "ToolChooser";

    public static final String TOOL_CONTROL_NAME = "control";
    public static final String TOOL_ADD_CLASS_NAME = "class";
    public static final String TOOL_ADD_INTERFACE_NAME = "interface";
    public static final String TOOL_ADD_ENUMERATION_NAME = "enumeration";
    public static final String TOOL_ADD_RELATION = "relation";
    public static final String TOOL_ADD_GENERALIZATION = "generalization";
    public static final String TOOL_ADD_REALISATION = "realisation";
    public static final String TOOL_ADD_COMPOSITION = "composition";
    public static final String TOOL_ADD_AGREGATION = "agregation";

    public static enum Tool {
        TOOL_CONTROLL,
        TOOL_ADD_CLASS,
        TOOL_ADD_INTERFACE,
        TOOL_ADD_ENUMERATION,
        TOOL_ADD_RELATION,
        TOOL_ADD_GENERALIZATION,
        TOOL_ADD_REALISATION,
        TOOL_ADD_COMPOSITION,
        TOOL_ADD_AGREGATION
    }

    public static final String SELECTED_TOOL_PROPERTY = "selectedTool";


    private Tool selectedTool;
    private List<ToolChooserModelListener> lsnrs;


    public ToolChooserModel() {
        this.selectedTool = Tool.TOOL_CONTROLL;
        this.lsnrs = new LinkedList<ToolChooserModelListener>();
    }

    public Tool getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(Tool selectedTool) {
        Tool oldTool = this.selectedTool;
        this.selectedTool = selectedTool;

        if (! this.selectedTool.equals(oldTool)) {
            this.fireSelectedToolChanged();
        }
    }

    public void addListener(ToolChooserModelListener lsnr) {
        if (! this.lsnrs.contains(lsnr)) {
            this.lsnrs.add(lsnr);
        }
    }

    public void removeListener(ToolChooserModelListener lsnr) {
        this.lsnrs.remove(lsnr);
    }

    private void fireSelectedToolChanged() {
        for (ToolChooserModelListener lsnr : this.lsnrs) {
            lsnr.selectedToolChanged(this.getSelectedTool());
        }
    }
}
