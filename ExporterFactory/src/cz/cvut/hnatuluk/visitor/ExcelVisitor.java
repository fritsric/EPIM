package cz.cvut.hnatuluk.visitor;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public interface ExcelVisitor {
    public void visitClassModel(Workbook wd, IClassModelModel model);
    public void visitClass(Sheet wd,IClass c);
    public void visitAnotation(Sheet wd, IAnotation a);
    public void visitRelation(Sheet wd,IRelation r);
    public void visitAtribute(Sheet wd,IAttribute a);
    public void visitMethod(Sheet wd, IMethod m);
    public void visitAnotationValue(Sheet wd, IAnotationValue av);   
}
