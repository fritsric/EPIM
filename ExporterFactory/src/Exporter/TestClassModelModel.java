/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Jan Bažant
 */
public class TestClassModelModel implements IClassModelModel {

    public Collection<IClass> frClasses = new ArrayList<IClass>();

    @Override
    public Collection<? extends IClass> getClasses() {
        return frClasses;
    }

    @Override
    public DiagramType getDiagramType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
