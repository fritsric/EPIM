package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
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
public abstract class AbstractElementModel extends TypeModel implements IElement {
    
    private Set<IMethod> methodModels;
    private Set<IAttribute> attributeModels;
    private Set<IAnotation> anotationModels;
    private Visibility visibility;
    private String stereotype;
    private boolean isElemAbstract;
    private DefaultGraphCell cell;

    /**
     * Creates new AbstractElementModel with no attributeModels and no methodModels
     *
     * @param name name of new class
     */
    public AbstractElementModel(String name) {
        this(name, null, null, null);
    }

    public AbstractElementModel(AbstractElementModel model) {
        super(model.toString());

        this.methodModels = new HashSet<IMethod>(model.getMethodModels());
        this.attributeModels = new HashSet<IAttribute>(model.getAttributeModels());
        this.anotationModels = new HashSet<IAnotation>(model.getAnotations());
        this.visibility = Visibility.PUBLIC;
        this.stereotype = model.getStereotype();
        this.isElemAbstract = model.isElemAbstract;
        this.cell = model.cell;
    }

    /**
     * Creates new AbstractElementModel
     *
     * @param name            name of new class
     * @param methodModels    Set of methodModels of this class. This class will create new Set according to this.
     * @param attributeModels Set of attributeModels of this class. This class will create new Set according to this.
     */
    public AbstractElementModel(String name, Set<IMethod> methodModels, Set<IAttribute> attributeModels, Set<IAnotation> anotationModels) {
        super(name);

        if (methodModels != null) {
            this.methodModels = new HashSet<IMethod>(methodModels);
        } else {
            this.methodModels = new HashSet<IMethod>();
        }

        if (attributeModels != null) {
            this.attributeModels = new HashSet<IAttribute>(attributeModels);
        } else {
            this.attributeModels = new HashSet<IAttribute>();
        }

        if (anotationModels != null) {
            this.anotationModels = new HashSet<IAnotation>(anotationModels);
        } else {
            this.anotationModels = new HashSet<IAnotation>();
        }

        this.visibility = Visibility.PUBLIC;
        this.stereotype = null;
        this.isElemAbstract = false;
        this.cell = null;
    }

    public void setCell(DefaultGraphCell cell) {
        this.cell = cell;
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
    public Set<IMethod> getMethodModels() {
        return new HashSet<IMethod>(this.methodModels);
    }

    /**
     * Returns a view of the attribute set
     *
     * @return a view of the attribute set
     */
    @Override
    public Set<IAttribute> getAttributeModels() {
        return new HashSet<IAttribute>(this.attributeModels);
    }

    @Override
    public Set<IAnotation> getAnotations() {
        return new HashSet<IAnotation>(this.anotationModels);
    }

    @Override
    public void addAnotation(IAnotation anot) {
        if (anot != null) {
            this.anotationModels.add(anot);
            this.fireModelChanged();
        }
    }

    @Override
    public void removeAnotation(IAnotation anot) {
        this.anotationModels.remove(anot);
        this.fireModelChanged();
    }

    @Override
    public void addAttribute(IAttribute attr) {
        if (attr != null) {
            this.attributeModels.add(attr);
            this.fireModelChanged();
        }
    }

    
    @Override
    public void removeAttribute(IAttribute attr) {
        this.attributeModels.remove(attr);
        this.fireModelChanged();
    }

    @Override
    public void removeMethod(IMethod method) {
        this.methodModels.remove(method);
        this.fireModelChanged();
    }

    @Override
    public void addMethod(IMethod method) {
        if (method != null) {
            this.methodModels.add(method);
            this.fireModelChanged();
        }
    }

    @Override
    public Collection<IRelation> getRelatedClass() {
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

    @Override
    public String getStereotype() {
        return this.stereotype;
    }

    @Override
    public void setStereotype(String stereotype) {
        this.stereotype = stereotype;
    }

    @Override
    public boolean isAbstract() {
        return this.isElemAbstract;
    }

    @Override
    public void setAbstract(boolean abstr) {
        this.isElemAbstract = abstr;
    }
}
