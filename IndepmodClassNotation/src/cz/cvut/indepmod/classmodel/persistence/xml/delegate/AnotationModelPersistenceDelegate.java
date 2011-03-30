package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.Statement;

/**
 * Date: 25.11.2010
 * Time: 17:17:56
 * @author Lucky
 */
public class AnotationModelPersistenceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
        AnotationModel am = (AnotationModel)oldInstance;

        for (IAnotationValue val : am.getAttributes()) {
            out.writeStatement(new Statement(oldInstance, "addAttribute", new Object[] {val}));
        }
    }



    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        AnotationModel am = (AnotationModel) oldInstance;
        return new Expression(
                oldInstance,
                oldInstance.getClass(),
                "new",
                new Object[]{am.getName()});
    }
}
