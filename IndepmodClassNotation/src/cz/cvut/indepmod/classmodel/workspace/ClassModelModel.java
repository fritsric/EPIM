package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import java.awt.Image;
import java.util.Collection;

/**
 * Date: 7.11.2010
 * Time: 11:59:45
 * @author Lucky
 */
public class ClassModelModel implements IClassModelModel {

    private ClassModelGraph graph;
    private DiagramDataModel dataModel;

    public ClassModelModel(ClassModelGraph graph, DiagramDataModel dataModel) {
        this.graph = graph;
        this.dataModel = dataModel;
    }

    @Override
    public Collection<IElement> getClasses() {
        return this.graph.getAllClasses();
    }

    @Override
    public DiagramType getDiagramType() {
        return this.dataModel.getDiagramType();
    }

    @Override
    public Image getDiagramImage(int width, int height) {
        return this.graph.createImage(width, height);
    }
}