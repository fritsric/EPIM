package cz.cvut.indepmod.classmodel.api;

/**
 * This interface will be implemented by classes that want to listen on
 * ToolChooserModel state changed
 * @author Lucky
 */
public interface ToolChooserModelListener {

    public void selectedToolChanged(ToolChooserModel.Tool newTool);
}
