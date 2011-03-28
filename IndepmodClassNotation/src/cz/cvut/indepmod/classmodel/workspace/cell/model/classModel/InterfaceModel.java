package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.ElementType;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import java.util.Set;

/**
 * Date: 24.3.2011
 * Time: 17:06:15
 * @author Lucky
 */
public class InterfaceModel extends AbstractElementModel {

    private static int counter = 0;

    private static final String DEFAULT_STEREOTYPE = "interface";

    public InterfaceModel() {
        super("Interface"+ ++counter);

        this.setStereotype(DEFAULT_STEREOTYPE);
    }

    public InterfaceModel(String name) {
        super(name);

        this.setStereotype(DEFAULT_STEREOTYPE);
    }

    public InterfaceModel(String name, Set<IMethod> methodModels, Set<IAttribute> attributeModels, Set<IAnotation> anotationModels) {
        super(name, methodModels, attributeModels, anotationModels);

        this.setStereotype(DEFAULT_STEREOTYPE);
    }

    public InterfaceModel(AbstractElementModel model) {
        super(model);

        this.setStereotype(DEFAULT_STEREOTYPE);
    }



    @Override
    public ElementType getElementType() {
        return ElementType.INTERFACE;
    }

}
