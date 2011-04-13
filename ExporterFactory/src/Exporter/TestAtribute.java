/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Jan Bažant
 */
public class TestAtribute implements IAttribute {

    public Visibility frVisibility = Visibility.NONE;
    public String fsName = "";
    public IType frType = new TestType("");
    public Collection<IAnotation> frAnotations = new ArrayList<IAnotation>();

    public TestAtribute() {
        
    }
    
    public TestAtribute(Visibility irVis, String isName, IType irType, IAnotation... irAnotation)
    {
        frVisibility = irVis;
        fsName = isName;
        frType = irType;
        frAnotations.addAll(Arrays.asList(irAnotation));
    }
    
    @Override
    public Visibility getVisibility() {
        return frVisibility;
    }

    @Override
    public String getName() {
        return fsName;
    }

    @Override
    public IType getType() {
        return frType;
    }

    @Override
    public Collection<IAnotation> getAnotations() {
        return frAnotations;
    }

}
