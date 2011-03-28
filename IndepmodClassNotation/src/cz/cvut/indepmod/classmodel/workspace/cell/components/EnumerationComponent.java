package cz.cvut.indepmod.classmodel.workspace.cell.components;

import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import javax.swing.JLabel;

/**
 * Date: 24.3.2011
 * Time: 18:55:37
 * @author Lucky
 */
public class EnumerationComponent extends AbstractElementComponent {

    public EnumerationComponent(AbstractElementModel model) {
        super(model);
    }

    @Override
    protected JLabel createAttributeLabel(IAttribute attribute) {
        return new JLabel(attribute.getName());
    }

}
