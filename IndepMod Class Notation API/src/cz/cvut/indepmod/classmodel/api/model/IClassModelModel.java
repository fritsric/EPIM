package cz.cvut.indepmod.classmodel.api.model;

import java.util.Collection;

/**
 *
 * @author Lucky
 *
 * Instances of this interface should return List of all Classes from the Class
 * Model
 */
public interface IClassModelModel {

    public Collection<? extends IClass> getClasses();

    public DiagramType getDiagramType();

}
