package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.frames.dialogs.EditRelationDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
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
public class EditRelationDialogSave extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_relation_dialog_save");

    private DefaultEdge edge;
    private EditRelationDialog dialog;
    private ClassModelGraph graph;

    public EditRelationDialogSave(DefaultEdge edge, EditRelationDialog dialog, ClassModelGraph graph) {
        super(ACTION_NAME, null);

        this.edge = edge;
        this.dialog = dialog;
        this.graph = graph;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.dialog.isChanged()) {
            ICardinality startCard = this.dialog.getStartingCardinality();
            ICardinality endCard = this.dialog.getEndingCardinality();
            String relationName = this.dialog.getRelationName();

            int lineEnd = GraphConstants.getLineEnd(edge.getAttributes());
            int newLineEnd = this.dialog.isArrowChecked() ? GraphConstants.ARROW_SIMPLE : GraphConstants.ARROW_NONE;

            IRelation userObj = (IRelation) this.edge.getUserObject();
            userObj.setRelationName(relationName);


            Map attrMap = new Hashtable();
            GraphConstants.setExtraLabels(attrMap, new ICardinality[]{startCard, endCard});
            if (lineEnd != newLineEnd) {
                GraphConstants.setLineEnd(attrMap, newLineEnd);
            }

            this.graph.getGraphLayoutCache().editCell(edge, attrMap);
        }
        this.dialog.dispose();
    }

}
