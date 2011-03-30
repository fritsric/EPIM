package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.ElementType;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import java.util.Set;

/**
 * Date: 24.3.2011
 * Time: 17:04:27
 * @author Lucky
 */
public class ClassModel extends AbstractElementModel {

    private static int counter = 0;

    public ClassModel() {
        super("Class"+ ++counter);
    }

    public ClassModel(String name) {
        super(name);
    }

    public ClassModel(String name, Set<IMethod> methodModels, Set<IAttribute> attributeModels, Set<IAnotation> anotationModels) {
        super(name, methodModels, attributeModels, anotationModels);
    }

    public ClassModel(AbstractElementModel model) {
        super(model);
    }



    @Override
    public ElementType getElementType() {
        return ElementType.CLASS;
    }

}
