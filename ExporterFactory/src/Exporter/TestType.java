/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.IType;

/**
 *
 * @author Jan Bažant
 */
public class TestType implements IType {
    public String fsTypeName = "";
    
    public TestType(String isTypeName) {
        fsTypeName = isTypeName;
    }
    
    @Override
    public String getTypeName() {
        return fsTypeName;
    }

}
