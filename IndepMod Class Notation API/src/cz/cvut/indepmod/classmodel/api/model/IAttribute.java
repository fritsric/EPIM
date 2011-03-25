/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.api.model;

import java.util.Collection;

/**
 *
 * @author Lucky
 */
public interface IAttribute {

    /**
     * Returns the Visibility of this Attribute
     * @return the Visibility of this Attribute
     */
    public Visibility getVisibility();

    /**
     * Returns the name of this attribute
     *
     * @return the name of this attribute
     */
    public String getName();

    /**
     * Returns Type instantion represeting the type of this attribute
     *
     * @return Type instantion
     */
    public IType getType();

    /**
     * Returns a collection of annotations that are related to this attribute
     * @return A collection of annotations
     */
    public Collection<IAnotation> getAnotations();

}
