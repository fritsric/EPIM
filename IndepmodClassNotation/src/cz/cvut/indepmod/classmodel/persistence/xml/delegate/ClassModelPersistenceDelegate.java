package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.Statement;
import java.util.Set;

/**
 * Date: 31.10.2010
 * Time: 14:49:43
 * @author Lucky
 *
 * This class is XML Persistence delegate for ClassModel objects
 */
public class ClassModelPersistenceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
        //super.initialize(type, oldInstance, newInstance, out);

        ClassModel cm = (ClassModel)oldInstance;
        Set<IMethod> methods = cm.getMethodModels();
        Set<IAttribute> attributes = cm.getAttributeModels();
        Set<IAnotation> anotations = cm.getAnotations();

        for (IMethod m : methods) {
            out.writeStatement(new Statement(oldInstance, "addMethod", new Object[] {m}));
        }

        for (IAttribute a : attributes) {
            out.writeStatement(new Statement(oldInstance, "addAttribute", new Object[] {a}));
        }

        for (IAnotation a : anotations) {
            out.writeStatement(new Statement(oldInstance, "addAnotation", new Object[] {a}));
        }
    }

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        ClassModel cm = (ClassModel)oldInstance;
        return new Expression(
                oldInstance,
                oldInstance.getClass(),
                "new",
                new Object[]{cm.getTypeName(), null, null, null});
    }


}
