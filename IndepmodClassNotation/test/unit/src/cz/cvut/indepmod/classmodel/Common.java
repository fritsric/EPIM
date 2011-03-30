package cz.cvut.indepmod.classmodel;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.util.HashSet;
import java.util.Set;

/**
 * Date: 25.11.2010
 * Time: 17:01:56
 * @author Lucky
 */
public class Common {

    public static final String CLASS_NAME = "TestClass";
    public static final String CLASS_NAME2 = "FooBarClass";
    public static final String CLASS_NAME3 = "MyFooClassForTestXYZ123";
    public static final String TYPE_NAME = "TYPETEST";
    public static final String TYPE_NAME2 = "testType";
    public static final String ATTRIBUTE_NAME = "AttrIbuteTeStTtTtttT";
    public static final String ATTRIBUTE_NAME2 = "attrTest";
    public static final String METHOD_NAME = "METHODnameTest";
    public static final String METHOD_NAME2 = "methodTest";
    public static final String FILE_NAME = "TestFile.xml";
    public static final String ANOT1 = "an1";
    public static final String ANOT2 = "barfoo";
    public static final String ANOT3 = "anotation3";
    public static final String VAL1 = "fooValue";
    public static final String VAL2 = "barBatFoo";
    public static final String VAL3 = "tst";
    public static final String INT_NAME = "int";
    public static final String SINGLETON_NAME = "singleton";
    public static final String COUNT_NAME = "count";
    private static Set<IAttribute> atts = null;
    private static Set<IMethod> methods = null;
    private static Set<IAnotation> anotations = null;

    public static Set<IAttribute> getAttributes() {
        if (atts == null) {
            atts = new HashSet<IAttribute>();
            atts.add(new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME));
            atts.add(new AttributeModel(new TypeModel(Common.TYPE_NAME2), Common.ATTRIBUTE_NAME2));
            atts.add(new AttributeModel(new TypeModel(Common.TYPE_NAME), Common.ATTRIBUTE_NAME2));
        }

        return atts;
    }

    public static Set<IMethod> getMethods() {
        if (methods == null) {
            methods = new HashSet<IMethod>();
            methods.add(new MethodModel(new TypeModel(Common.TYPE_NAME), Common.METHOD_NAME, null));
            methods.add(new MethodModel(new TypeModel(Common.TYPE_NAME2), Common.METHOD_NAME2, null));
        }

        return methods;
    }

    public static Set<IAnotation> getAnotations() {
        if (anotations == null) {
            anotations = new HashSet<IAnotation>();
            anotations.add(new AnotationModel(Common.ANOT1));
            anotations.add(new AnotationModel(Common.ANOT2));
            anotations.add(new AnotationModel(Common.ANOT3));
        }

        return anotations;
    }
}
