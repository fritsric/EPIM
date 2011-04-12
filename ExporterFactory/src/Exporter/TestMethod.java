/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jan Bažant
 */
public class TestMethod implements IMethod {
    public Visibility frVisibility = Visibility.NONE;
    public HashSet<IAttribute> frAttrs = new HashSet<IAttribute>();
    public String fsName = "";
    public IType frType = new TestType("");

    @Override
    public Visibility getVisibility() {
        return frVisibility;
    }

    @Override
    public Set<? extends IAttribute> getAttributeModels() {
        return frAttrs;
    }

    @Override
    public String getName() {
        return fsName;
    }

    @Override
    public IType getType() {
        return frType;
    }

}
