package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.EditRelationDialogSave;
import cz.cvut.indepmod.classmodel.api.model.IArrowableRelation;
import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jgraph.graph.DefaultEdge;

/**
 * Date: 21.11.2010
 * Time: 17:41:36
 * @author Lucky
 *
 * Dialog that is used for relation editing
 */
public class EditRelationDialog extends EditRelationDialogView implements ItemListener {

    private static final Logger LOG = Logger.getLogger(EditRelationDialog.class.getName());

    private ClassModelGraph graph;
    private DefaultEdge edge;
    private IArrowableRelation model;
    private boolean changed;

    public EditRelationDialog(Frame owner, ClassModelGraph graph, DefaultEdge edge, IArrowableRelation model) {
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
        return this.changed;
    }

    public ICardinality getStartingCardinality() {
        Cardinality res = (Cardinality) this.sourceCardinality.getSelectedItem();
        return res;
    }

    public ICardinality getEndingCardinality() {
        Cardinality res = (Cardinality) this.targetCardinality.getSelectedItem();
        return res;
    }

    public String getRelationName() {
        String res = this.nameField.getText().trim();
        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public boolean isArrowChecked() {
        return this.arrowCheck.isSelected();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        LOG.info("property changed");
        this.changed = true;
    }

    private void initValues() {
        this.nameField.setText(this.model.getRelationName());

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

        this.sourceCardinality.setSelectedItem(this.model.getStartCardinality());
        this.targetCardinality.setSelectedItem(this.model.getEndCardinality());

        this.arrowCheck.setSelected(this.model.isArrowOnEnd());
    }

    private void initHandlers() {
        this.sourceCardinality.addItemListener(this);
        this.targetCardinality.addItemListener(this);
        this.nameField.getDocument().addDocumentListener(new NameFieldDocListener());
        this.arrowCheck.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                changed = true;
            }
        });
    }

    private void initActions() {
        this.saveButton.setAction(new EditRelationDialogSave(this.edge, this, this.graph));
    }

    private class NameFieldDocListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            changed = true;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed = true;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed = true;
        }
    }
}
