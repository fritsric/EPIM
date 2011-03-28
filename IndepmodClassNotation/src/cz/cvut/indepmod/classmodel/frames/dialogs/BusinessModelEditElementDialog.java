package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import java.awt.Frame;
import javax.swing.JPanel;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 5.3.2011
 * Time: 14:33:26
 * @author Lucky
 *
 * This class represents the Edit Class dialog for business diagram.
 * It hides anotations and methods.
 */
public class BusinessModelEditElementDialog extends AbstractEditElementDialog {

    public BusinessModelEditElementDialog(
            Frame owner,
            ClassModelGraph graph,
            DefaultGraphCell cell,
            AbstractElementModel classModel) {
        super(owner, graph, cell, classModel);
    }

    @Override
    protected JPanel initAnotationPanel() {
        return null;
    }

    @Override
    protected JPanel initMethodPanel() {
        return null;
    }

    @Override
    protected JPanel initPropertyPanel() {
        return null;
    }

}
