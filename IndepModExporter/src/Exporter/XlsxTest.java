/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exporter;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.ElementType;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;
import java.util.Set;

import org.jgraph.graph.DefaultGraphCell;
/**
 *
 * @author foxnet
 */
public class XlsxTest implements IClassModelModel {    
    private Collection<IElement> frTestClasses;
    private Set<IMethod> frMethodSet;
    private Set<IAttribute> frClassAttributeModels;
    private Set<IAttribute> frMethodAttributeModels;
    private Set<IAnotation> frAnnotationModels;
    private AbstractElementModel frElementModel;   
    private DefaultGraphCell frGraphCell;
    
    public XlsxTest()
    {
        frTestClasses = new ArrayList<IElement>();
        frMethodSet = new HashSet<IMethod>();
        frClassAttributeModels = new HashSet<IAttribute>();
        frMethodAttributeModels = new HashSet<IAttribute>();
        frAnnotationModels = new HashSet<IAnotation>();        

        frMethodAttributeModels.add(new AttributeModel(new TypeModel("Typ Atributu"),"Jmeno Atributu Metody"));
        frClassAttributeModels.add(new AttributeModel(new TypeModel("Typ Atributu"),"Jmeno Atributu Tridy"));
        frMethodSet.add(new MethodModel(new TypeModel("Typ Metody"), "Jmeno metody 1", frMethodAttributeModels));
        frAnnotationModels.add(new AnotationModel("Jmeno Anotace"));
        frGraphCell = new DefaultGraphCell();        
        frElementModel = new AbstractElementModel("Moje Trida",frMethodSet,frClassAttributeModels,frAnnotationModels)
                               {
                                    @Override
                                   public ElementType getElementType() {
                                       return ElementType.CLASS;
                                   }
                               };
        frElementModel.setCell(frGraphCell);
        frTestClasses.add(frElementModel);
    }
    
    @Override
    public Collection<IElement> getClasses() {
        
        return frTestClasses;
    }

    @Override
    public DiagramType getDiagramType() {
        return DiagramType.CLASS;
    }

    @Override
    public Image getDiagramImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
}
