package cz.cvut.indepmod.classmodel.api.model;

import java.util.Collection;

/**
 *
 * @author Lucky
 */
public interface IAnotationValue {

    public String getName();
    
    public Collection<String> getValues();

    public void addValue(String value);

}
