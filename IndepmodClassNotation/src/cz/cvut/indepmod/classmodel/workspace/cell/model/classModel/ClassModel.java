package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.Port;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 25.9.2010
 * Time: 10:16:40
 * <p/>
 * Model of a Class in the Class diagram.
 */
public class ClassModel extends TypeModel implements IClass {

    private static int counter = 0;
    private Set<MethodModel> methodModels;
    private Set<AttributeModel> attributeModels;
    private Set<AnotationModel> anotationModels;
    private Visibility visibility;
    private DefaultGraphCell cell;

    public ClassModel() {
        this("Class" + ++counter);
    }

    /**
     * Creates new ClassModel with no attributeModels and no methodModels
     *
     * @param name name of new class
     */
    public ClassModel(String name) {
        this(name, null, null, null);
    }

    public ClassModel(ClassModel model) {
        super(model.toString());

        this.methodModels = new HashSet<MethodModel>(model.getMethodModels());
        this.attributeModels = new HashSet<AttributeModel>(model.getAttributeModels());
        this.anotationModels = new HashSet<AnotationModel>(model.getAnotations());
        this.visibility = Visibility.PUBLIC;
        this.cell = model.cell;
    }

    /**
     * Creates new ClassModel
     *
     * @param name            name of new class
     * @param methodModels    Set of methodModels of this class. This class will create new Set according to this.
     * @param attributeModels Set of attributeModels of this class. This class will create new Set according to this.
     */
    public ClassModel(String name, Set<MethodModel> methodModels, Set<AttributeModel> attributeModels, Set<AnotationModel> anotationModels) {
        super(name);

        if (methodModels != null) {
            this.methodModels = new HashSet<MethodModel>(methodModels);
        } else {
            this.methodModels = new HashSet<MethodModel>();
        }

        if (attributeModels != null) {
            this.attributeModels = new HashSet<AttributeModel>(attributeModels);
        } else {
            this.attributeModels = new HashSet<AttributeModel>();
        }

        if (anotationModels != null) {
            this.anotationModels = new HashSet<AnotationModel>(anotationModels);
        } else {
            this.anotationModels = new HashSet<AnotationModel>();
        }

        this.visibility = Visibility.PUBLIC;
        this.cell = null;
    }

    public void setCell(DefaultGraphCell cell) {
        this.cell = cell; //TODO - shouldn't this throw an exception when the cell is already sat?
    }

    @Override
    public String toString() {
        return this.getTypeName();
    }

    /**
     * Returns a view of the method set
     *
     * @return a view of the method set
     */
    @Override
    public Set<MethodModel> getMethodModels() {
        //return Collections.unmodifiableSet(methodModels);
        return new HashSet<MethodModel>(this.methodModels);
    }

    /**
     * Returns a view of the attribute set
     *
     * @return a view of the attribute set
     */
    @Override
    public Set<AttributeModel> getAttributeModels() {
        //return Collections.unmodifiableSet(attributeModels);
        return new HashSet<AttributeModel>(this.attributeModels);
    }

    @Override
    public Set<AnotationModel> getAnotations() {
        return new HashSet<AnotationModel>(this.anotationModels);
    }

    public void addAnotation(AnotationModel anot) {
        if (anot != null) {
            this.anotationModels.add(anot);
            this.fireModelChanged();
        }
    }

    public void removeAnotation(AnotationModel anot) {
        this.anotationModels.remove(anot);
        this.fireModelChanged();
    }

    /**
     * Adds new attribute to this class model. If this attribute is already here
     * it will not be apended
     * @param attr new attribute to add
     */
    public void addAttribute(AttributeModel attr) {
        if (attr != null) {
            this.attributeModels.add(attr);
            this.fireModelChanged();
        }
    }

    /**
     * Removes the attribute from this class model (sure if the attribute is
     * here).
     * @param attr
     */
    public void removeAttribute(AttributeModel attr) {
        this.attributeModels.remove(attr);
        this.fireModelChanged();
    }

    /**
     * Removes the method from this class model if there is this method.
     * @param method a method to be removed from this model
     */
    public void removeMethod(MethodModel method) {
        this.methodModels.remove(method);
        this.fireModelChanged();
    }

    /**
     * Adds the method to this class if it is not already here
     * @param method a method to be added to this model
     */
    public void addMethod(MethodModel method) {
        if (method != null) {
            this.methodModels.add(method);
            this.fireModelChanged();
        }
    }

    @Override
    public Collection<? extends IRelation> getRelatedClass() {
        List<IRelation> res = new ArrayList<IRelation>();

        if (this.cell == null) {
            throw new NullPointerException("Cell of ClassModel was not sat.");
        }

        List children = this.cell.getChildren();
        for (Object childObj : children) {
            if (childObj instanceof Port) {
                Port p = (Port) childObj;
                Iterator it = p.edges();
                while (it.hasNext()) {
                    DefaultEdge e = (DefaultEdge) it.next();
                    Object o = e.getUserObject();
                    if (o == null) {
                        throw new NullPointerException("User object of the edge is null");
                    }

                    IRelation relation = (IRelation) o;
                    res.add(relation);
                }
            }
        }

        return res;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    
}
