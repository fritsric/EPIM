package cz.cvut.indepmod.classmodel.workspace.cell;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import org.jgraph.graph.DefaultGraphCellEditor;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 30.9.2010
 * Time: 11:39:32
 */
public class ClassModelVertexEditor extends DefaultGraphCellEditor {

    @Override
    public Object getCellEditorValue() {
        return new ClassModel(this.realEditor.getCellEditorValue().toString());
    }
}
