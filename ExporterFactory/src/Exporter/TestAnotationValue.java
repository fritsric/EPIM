/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Jan Bažant
 */
public class TestAnotationValue implements IAnotationValue {
    public String fsName = "";
    public Collection<String> frVals = new ArrayList<String>();

    public TestAnotationValue(String isName, String... irVals)
    {
        fsName = isName;
        frVals.addAll(Arrays.asList(irVals));
    }
    
    @Override
    public String getName() {
        return fsName;
    }

    @Override
    public Collection<String> getValues() {
        return frVals;
    }

}
