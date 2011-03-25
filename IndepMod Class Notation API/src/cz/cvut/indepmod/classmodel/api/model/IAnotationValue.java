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
public interface IAnotationValue {

    public String getName();
    
    public Collection<String> getValues();

}
