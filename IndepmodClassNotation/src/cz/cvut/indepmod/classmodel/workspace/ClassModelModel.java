package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.util.Collection;

/**
 * Date: 7.11.2010
 * Time: 11:59:45
 * @author Lucky
 */
public class ClassModelModel implements IClassModelModel {

    private ClassModelGraph graph;

    public ClassModelModel(ClassModelGraph graph) {
        this.graph = graph;
    }

    @Override
    public Collection<ClassModel> getClasses() {
        return this.graph.getAllClasses();
    }

    @Override
    public DiagramType getDiagramType() {
        return this.graph.getDiagramType();
    }

}
