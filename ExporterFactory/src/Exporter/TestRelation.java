/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;

/**
 *
 * @author Jan Bažant
 */
public class TestRelation implements IRelation {
    public IClass frStartCls = new TestClass();
    public IClass frEndCls = new TestClass();
    public ICardinality frStartCrd = new TestCardinality();
    public ICardinality frEndCrd = new TestCardinality();
    public RelationType frRelType = RelationType.RELATION;

    public TestRelation(IClass irClsFrom, IClass irClsTo, RelationType irRelType, ICardinality irCardinalityFrom, ICardinality irCardinalityTo)
    {
        frStartCls = irClsFrom;
        frEndCls = irClsTo;
        frStartCrd = irCardinalityFrom;
        frEndCrd = irCardinalityTo;
        frRelType = irRelType;
    }
    
    @Override
    public IClass getStartingClass() {
        return frStartCls;
    }

    @Override
    public IClass getEndingClass() {
        return frEndCls;
    }

    @Override
    public ICardinality getStartCardinality() {
        return frStartCrd;
    }

    @Override
    public ICardinality getEndCardinality() {
        return frEndCrd;
    }

    @Override
    public RelationType getRelationType() {
        return frRelType;
    }

}
