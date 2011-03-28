package cz.cvut.indepmod.classmodel.diagramdata;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.util.ClassModelLibrary;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraphModel;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellViewFactory;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.jgraph.graph.GraphLayoutCache;

/**
 * This class stores information which have to be saved. It is information
 * like which type of diagram is used (business, class), used GraphLayoutCache,
 * static data types, ...
 * @author Lucky
 */
public class DiagramDataModel {

    /**
     * Layout cache of the graph. This object storest the information about
     * graph objects
     */
    private GraphLayoutCache layoutCache;

    /**
     * The type of the diagram (class or business diagram)
     */
    private DiagramType diagramType;

    /**
     * Set of static data types (String, int, ...)
     */
    private Set<IType> staticDataTypes;

    /**
     * Set of dynamic data types which were added by the user. These data
     * types are not classes created by the user. This types are types that
     * are not among static data types and among created classes. It is when
     * user want to add an attribut of e.g. JTextField type and type it by hand.
     */
    private Set<IType> dynamicDataTypes;

    private Set<String> stereotypes;

    public DiagramDataModel() {
        this(new GraphLayoutCache(
                new ClassModelGraphModel(),
                new ClassModelCellViewFactory()),
             DiagramType.CLASS);
    }

    public DiagramDataModel(GraphLayoutCache cache, DiagramType diagType) {
        this.layoutCache = cache;
        this.diagramType = diagType;

        if (this.layoutCache == null) {
            this.layoutCache = new GraphLayoutCache(
                    new ClassModelGraphModel(),
                    new ClassModelCellViewFactory());
        }

        this.dynamicDataTypes = new HashSet<IType>();
        this.stereotypes = new HashSet<String>();

        this.initStaticTypes();
        this.initDefaultStereotypes();
    }

    /**
     * Returns the GraphLayoutCache instance
     * @return
     */
    public GraphLayoutCache getLayoutCache() {
        return layoutCache;
    }

    /**
     * Returns the diagram type
     * @return
     */
    public DiagramType getDiagramType() {
        return diagramType;
    }

    /**
     * Returns a collection of static data types
     * @return
     */
    public Collection<IType> getStaticDataTypes() {
        return new HashSet<IType>(this.staticDataTypes);
    }

    /**
     * Returns a collection of dynamic data types
     * @return
     */
    public Collection<IType> getDynamicDataTypes() {
        return new HashSet<IType>(this.dynamicDataTypes);
    }

    /**
     * Returns a collection of all data types (static + dynamic)
     * @return
     */
    public Collection<IType> getDataTypes() {
        Collection<IType> res = ClassModelLibrary.joinTypeCollections(this.staticDataTypes, this.dynamicDataTypes);
        return res;
    }

    /**
     * Adds new dynamic data type (in case there is not such a type yet)
     * @param type
     */
    public void addDynamicDataType(IType type) {
        if (this.staticDataTypes.contains(type)) {
            return;
        }
        this.dynamicDataTypes.add(type);
    }

    /**
     * Returns a collection of stereotypes
     * @return
     */
    public Collection<String> getStereotypes() {
        return new HashSet<String>(this.stereotypes);
    }

    /**
     * Adds a new stereotype, but only if it is not already there.
     * @param stereotype
     */
    public void addStereotype(String stereotype) {
        this.stereotypes.add(stereotype);
    }

    /**
     * TODO - this will be loaded from a XML. Only temporary
     */
    private void initStaticTypes() {
        this.staticDataTypes = new HashSet<IType>();
        this.staticDataTypes.add(new TypeModel("Object"));
        this.staticDataTypes.add(new TypeModel("String"));
        this.staticDataTypes.add(new TypeModel("int"));
        this.staticDataTypes.add(new TypeModel("char"));
        this.staticDataTypes.add(new TypeModel("boolean"));
        this.staticDataTypes.add(new TypeModel("long"));
        this.staticDataTypes.add(new TypeModel("double"));
        this.staticDataTypes.add(new TypeModel("float"));
        this.staticDataTypes.add(new TypeModel("void"));
        this.staticDataTypes.add(new TypeModel(""));
    }

    private void initDefaultStereotypes() {
        this.stereotypes = new HashSet<String>();
        this.stereotypes.add(""); //Empty stereotype
        this.stereotypes.add("interface");
        this.stereotypes.add("enumeration");
    }
}