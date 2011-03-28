package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.HierarchyRelationModel;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;

/**
 * Date: 26.2.2011
 * Time: 19:25:39
 * @author LuckyV
 */
public class HierarchyRelationModelPersistenceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        HierarchyRelationModel model = (HierarchyRelationModel) oldInstance;
        return new Expression(
                oldInstance,
                oldInstance.getClass(),
                "new",
                new Object[]{model.getRelationType()});
    }
}
