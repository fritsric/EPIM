/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.hnatuluk.visitor;

import cz.cvut.hnatuluk.factories.WordFactory.WordDocument;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public interface WordVisitor {
    public void visitClassModel(WordDocument wd, IClassModelModel model);
    public void visitClass(WordDocument wd,IClass c);
    public void visitAnotation(WordDocument wd, IAnotation a);
    public void visitRelation(WordDocument wd,IRelation r);
    public void visitAtribute(WordDocument wd,IAttribute a);
    public void visitMethod(WordDocument wd, IMethod m);
    public void visitAnotationValue(WordDocument wd, IAnotationValue av);    
}
