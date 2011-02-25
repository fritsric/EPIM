package cz.cvut.indepmod.classmodel.modelFactory.diagramModel;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraphModel;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellViewFactory;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.jgraph.graph.GraphLayoutCache;

/**
 * This class stores information which have to be saved. It is information
 * like which type of diagram is used (business, class), used GraphLayoutCache,
 * static data types, ...
 * @author Lucky
 */
public class ClassModelDiagramDataModel {

    private GraphLayoutCache layoutCache;
    private DiagramType diagramType;
    private Set<TypeModel> staticDataTypes;

    public ClassModelDiagramDataModel() {
        this.layoutCache = new GraphLayoutCache(
                new ClassModelGraphModel(),
                new ClassModelCellViewFactory());
        this.diagramType = DiagramType.CLASS;

        this.initStaticTypes();
    }

    public ClassModelDiagramDataModel(GraphLayoutCache layoutCache, DiagramType diagramType) {
        this.layoutCache = layoutCache;
        this.diagramType = diagramType;

        if (this.layoutCache == null) {
            this.layoutCache = new GraphLayoutCache(
                    new ClassModelGraphModel(),
                    new ClassModelCellViewFactory());
        }

        this.initStaticTypes();
    }

    public GraphLayoutCache getLayoutCache() {
        return layoutCache;
    }

    public DiagramType getDiagramType() {
        return diagramType;
    }

    public Set<TypeModel> getStaticDataTypes() {
        return Collections.unmodifiableSet(this.staticDataTypes);
    }


    /**
     * TODO - this will be loaded from a XML. Only temporary
     */
    private void initStaticTypes() {
        this.staticDataTypes = new HashSet<TypeModel>();
        this.staticDataTypes.add(new TypeModel("Object"));
        this.staticDataTypes.add(new TypeModel("String"));
        this.staticDataTypes.add(new TypeModel("int"));
        this.staticDataTypes.add(new TypeModel("char"));
        this.staticDataTypes.add(new TypeModel("boolean"));
        this.staticDataTypes.add(new TypeModel("long"));
        this.staticDataTypes.add(new TypeModel("double"));
        this.staticDataTypes.add(new TypeModel("float"));
    }
}