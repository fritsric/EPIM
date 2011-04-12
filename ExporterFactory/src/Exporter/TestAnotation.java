/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Jan Bažant
 */
public class TestAnotation implements IAnotation {
    public String fsName = "";
    public Collection<IAnotationValue> frAnotationVals = new ArrayList<IAnotationValue>();

    @Override
    public String getName() {
        return fsName;
    }

    @Override
    public Collection<IAnotationValue> getAttributes() {
        return frAnotationVals;
    }

}
