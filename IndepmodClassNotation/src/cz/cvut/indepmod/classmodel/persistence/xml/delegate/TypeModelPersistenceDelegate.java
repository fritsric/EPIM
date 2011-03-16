package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;

/**
 * Date: 31.10.2010
 * Time: 14:51:46
 * @author Lucky
 *
 * This class is XML Persistence delegate for TypeModel objects
 */
public class TypeModelPersistenceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        TypeModel m = (TypeModel) oldInstance;
        return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[]{m.getTypeName()});
    }



}
