/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.indepmod.classmodel.api.model.ICardinality;

/**
 *
 * @author Jan Bažant
 */
public class TestCardinality implements ICardinality {

    public int fnFrom = -1;
    public int fnTo = -1;

    @Override
    public int getFrom() {
        return fnFrom;
    }

    @Override
    public int getTo() {
        return fnTo;
    }

}
