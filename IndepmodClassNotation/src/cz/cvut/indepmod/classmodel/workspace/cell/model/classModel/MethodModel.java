package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 9:52:44
 */
public class MethodModel extends AbstractModel implements IMethod {

    private TypeModel type;
    private String name;
    private Set<IAttribute> attributeModels;
    private Visibility visibility;

    public MethodModel(TypeModel typeModel, String name, Set<IAttribute> attributeModels) {
        this(typeModel, name, attributeModels, Visibility.PUBLIC);
    }

    public MethodModel(TypeModel typeModel, String name, Set<IAttribute> attributeModels, Visibility v) {
        this.visibility = v;
        this.type = typeModel;
        this.name = name;

        if (attributeModels != null) {
            this.attributeModels = new HashSet<IAttribute>(attributeModels);
        } else {
            this.attributeModels = new HashSet<IAttribute>();
        }
    }

    /**
     * Returns Type instantion represeting the return type of this method
     *
     * @return Type instantion
     */
    @Override
    public TypeModel getType() {
        return this.type;
    }

    /**
     * Returns the name of the method
     *
     * @return name of the method
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns an unmodifiable view of the attributes set
     *
     * @return an unmodifiable view of the attributes set
     */
    @Override
    public Set<IAttribute> getAttributeModels() {
        //return Collections.unmodifiableSet(this.attributeModels);
        return new HashSet<IAttribute>(this.attributeModels);
    }

    @Override
    public String toString() {
        StringBuilder bfr = new StringBuilder(30);
        bfr.append(this.visibility.toString());
        bfr.append(" ");
        bfr.append(this.type.toString());
        bfr.append(" ");
        bfr.append(this.name);
        bfr.append("(");

        boolean comma = false;
        for (IAttribute attr : this.getAttributeModels()) {
            if (comma) {
                bfr.append(", ");
            } else {
                comma = true;
            }
            
            bfr.append(attr.getType());
        }

        bfr.append(")");

        return bfr.toString();
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }
}
