/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.indepmod.classmodel.api.model;

/**
 *
 * @author Lucky
 */
public interface IType {

    /**
     * Returns the name of this data type
     *
     * @return the name
     */
    public String getTypeName();

    /**
     * Sets the type name
     * @param name new type name
     */
    public void setTypeName(String name);

}
