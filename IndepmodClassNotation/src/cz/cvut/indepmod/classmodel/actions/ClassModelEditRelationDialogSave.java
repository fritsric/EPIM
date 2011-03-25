package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.ClassModelEditRelationDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Map;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;

/**
 * Date: 21.11.2010
 * Time: 18:08:40
 * @author Lucky
 */
public class ClassModelEditRelationDialogSave extends ClassModelAbstractAction {

    public static final String ACTION_NAME = "Save";

    private DefaultEdge edge;
    private ClassModelEditRelationDialog dialog;
    private ClassModelGraph graph;

    public ClassModelEditRelationDialogSave(DefaultEdge edge, ClassModelEditRelationDialog dialog, ClassModelGraph graph) {
        super(ACTION_NAME, null);

        this.edge = edge;
        this.dialog = dialog;
        this.graph = graph;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.dialog.isChanged()) {
            Cardinality startCard = this.dialog.getStartingCardinality();
            Cardinality endCard = this.dialog.getEndingCardinality();

            Map attrMap = new Hashtable();
            GraphConstants.setExtraLabels(attrMap, new Cardinality[]{startCard, endCard});
            this.graph.getGraphLayoutCache().editCell(edge, attrMap);
        }
        this.dialog.dispose();
    }

}
