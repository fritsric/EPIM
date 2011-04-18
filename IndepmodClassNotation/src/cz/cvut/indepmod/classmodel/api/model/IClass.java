/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.api.model;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Lucky
 */
public interface IClass extends IType {

    /**
     * Returns the Visibility of this class
     * @return the Visibility of this class
     */
    public Visibility getVisibility();

    /**
     * Returns an attribute set
     *
     * @return an attribute set
     */
    public Set<? extends IAttribute> getAttributeModels();

    /**
     * Returns a method set
     *
     * @return a method set
     */
    public Set<? extends IMethod> getMethodModels();

    /**
     * Returns set of related methods
     * @return related method set
     */
    public Collection<? extends IRelation> getRelatedClass();

    /**
     * Returns a collection of anotations
     * @return a collection of anotations
     */
    public Set<? extends IAnotation> getAnotations();
}
