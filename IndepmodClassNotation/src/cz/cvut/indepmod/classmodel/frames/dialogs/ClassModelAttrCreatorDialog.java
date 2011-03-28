package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.api.model.IType;
import java.awt.Frame;
import java.util.Collection;

/**
 * Date: 13.3.2011
 * Time: 15:08:56
 * @author Lucky
 */
public class ClassModelAttrCreatorDialog extends AbstractAttrCreatorDialog {

    public ClassModelAttrCreatorDialog(Frame owner, Collection<IType> types) {
        super(owner, types);
    }

    public ClassModelAttrCreatorDialog(Frame owner) {
        super(owner);
    }

}
