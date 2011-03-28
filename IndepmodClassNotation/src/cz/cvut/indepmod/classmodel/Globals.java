package cz.cvut.indepmod.classmodel;

import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import org.openide.util.Utilities;

/**
 * Date: 20.3.2011
 * Time: 9:01:24
 * @author Lucky
 */
public class Globals {
    
    private static Globals instance;

    private Globals() {

    }

    public static Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    /**
     * Returns the DiagramDataModel instance of active workspace. This instance
     * is gathered from the lookup of active workspace.
     * @return Actual instance of DiagramDataModel
     */
    public DiagramDataModel getActualDiagramData() {
        DiagramDataModel res = Utilities.actionsGlobalContext().lookup(DiagramDataModel.class);
        return res;
    }

}
