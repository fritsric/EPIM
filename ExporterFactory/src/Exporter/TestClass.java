/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jan Bažant
 */
public class TestClass implements IClass {

    public Visibility frVisibility = Visibility.NONE;
    public Set<IAttribute> frAttributes = new HashSet<IAttribute>();
    public Set<IMethod> frMethods = new HashSet<IMethod>();
    public Collection<IRelation> frRelations = new ArrayList<IRelation>();
    public Set<IAnotation> frAnotations = new HashSet<IAnotation>();
    public String fsTypeName = "";
    
    public TestClass() {}
    
    public TestClass(String isTypeName, Visibility irVis)
    {
        fsTypeName = isTypeName;
        frVisibility = irVis;
    }

    @Override
    public Visibility getVisibility() {
        return frVisibility;
    }

    @Override
    public Set<? extends IAttribute> getAttributeModels() {
        return frAttributes;
    }

    @Override
    public Set<? extends IMethod> getMethodModels() {
        return frMethods;
    }

    @Override
    public Collection<? extends IRelation> getRelatedClass() {
        return frRelations;
    }

    @Override
    public Set<? extends IAnotation> getAnotations() {
        return frAnotations;
    }

    @Override
    public String getTypeName() {
        return fsTypeName;
    }

}
