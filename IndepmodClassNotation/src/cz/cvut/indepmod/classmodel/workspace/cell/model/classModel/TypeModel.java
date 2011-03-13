package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IType;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 9:52:03
 * <p/>
 * This class represents the data type (Class name, return type of method or data type of an attribute)
 */
public class TypeModel extends AbstractModel implements IType {

    private String typeName;

    /**
     * Creates new Type
     *
     * @param name name of the type
     */
    public TypeModel(String name) {
        this.typeName = name;
    }

    /**
     * Returns the name of this data type
     *
     * @return the name
     */
    @Override
    public String getTypeName() {
        return typeName;
    }

    /**
     * Change the name of this data type
     * @param typeName new name of this data type
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}
