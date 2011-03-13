package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;

/**
 * Date: 31.10.2010
 * Time: 14:50:45
 * @author Lucky
 *
 * This class is XML Persistence delegate for MethodModel objects
 */
public class MethodModelPersistenceDelegate extends DefaultPersistenceDelegate {

//    @Override
//    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
//        MethodModel mm = (MethodModel) oldInstance;
//        super.initialize(type, oldInstance, newInstance, out);
//
//        out.writeStatement(new Statement(oldInstance, "addAttribute", mm.getAttributeModels()));
//    }



    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        MethodModel mm = (MethodModel) oldInstance;
        return new Expression(
                oldInstance,
                oldInstance.getClass(),
                "new",
                new Object[]{mm.getType(), mm.getName(), mm.getAttributeModels()});
    }

}
