package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.ClassModelEditRelationDialogSave;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Logger;
import org.jgraph.graph.DefaultEdge;

/**
 * Date: 21.11.2010
 * Time: 17:41:36
 * @author Lucky
 *
 * Dialog that is used for relation editing
 */
public class ClassModelEditRelationDialog extends ClassModelEditRelationDialogView implements ItemListener {

    private static final Logger LOG = Logger.getLogger(ClassModelEditClassDialog.class.getName());

    private ClassModelGraph graph;
    private DefaultEdge edge;
    private RelationModel model;
    private boolean changed;

    public ClassModelEditRelationDialog(Frame owner, ClassModelGraph graph, DefaultEdge edge, RelationModel model) {
        super(owner);

        this.graph = graph;
        this.edge = edge;
        this.model = model;
        this.changed = false;

        this.initValues();
        this.initHandlers();
        this.initActions();
        this.setSizes();
    }

    public boolean isChanged() {
        return changed;
    }

    public Cardinality getStartingCardinality() {
        return (Cardinality) this.sourceCardinality.getSelectedItem();
    }

    public Cardinality getEndingCardinality() {
        return (Cardinality) this.targetCardinality.getSelectedItem();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        LOG.info("property changed");
        this.changed = true;
    }

    private void initValues() {
        this.sourceCardinality.removeAllItems();
        this.targetCardinality.removeAllItems();

        this.sourceCardinality.addItem(Cardinality.ZERO);
        this.sourceCardinality.addItem(Cardinality.ONE);
        this.sourceCardinality.addItem(Cardinality.ZERO_INFINITY);
        this.sourceCardinality.addItem(Cardinality.ONE_INFINITY);

        this.targetCardinality.addItem(Cardinality.ZERO);
        this.targetCardinality.addItem(Cardinality.ONE);
        this.targetCardinality.addItem(Cardinality.ZERO_INFINITY);
        this.targetCardinality.addItem(Cardinality.ONE_INFINITY);

        this.sourceCardinality.setSelectedItem(model.getStartCardinality());
        this.targetCardinality.setSelectedItem(model.getEndCardinality());
    }

    private void initHandlers() {
        this.sourceCardinality.addItemListener(this);
        this.targetCardinality.addItemListener(this);
    }

    private void initActions() {
        this.saveButton.setAction(new ClassModelEditRelationDialogSave(this.edge, this, this.graph));
    }

}
