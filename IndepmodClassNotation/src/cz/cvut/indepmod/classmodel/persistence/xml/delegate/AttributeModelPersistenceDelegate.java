package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.Statement;

/**
 * Date: 31.10.2010
 * Time: 14:51:11
 * @author Lucky
 *
 * This class is XML Persistence delegate for AttributeModel objects
 */
public class AttributeModelPersistenceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
        AttributeModel attr = (AttributeModel)oldInstance;

        for (IAnotation a : attr.getAnotations()) {
            out.writeStatement(new Statement(oldInstance, "addAnotation", new Object[] {a}));
        }
    }



    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        AttributeModel am = (AttributeModel) oldInstance;
        return new Expression(
                oldInstance,
                oldInstance.getClass(),
                "new",
                new Object[]{am.getType(), am.getName(), am.getVisibility()});
    }
}
