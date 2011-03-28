package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import java.awt.Frame;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 5.3.2011
 * Time: 14:32:31
 * @author Lucky
 */
public class ClassModelEditElementDialog extends AbstractEditElementDialog {

    public ClassModelEditElementDialog(
            Frame owner,
            ClassModelGraph graph,
            DefaultGraphCell cell,
            AbstractElementModel classModel) {
        super(owner, graph, cell, classModel);
    }

    
}
