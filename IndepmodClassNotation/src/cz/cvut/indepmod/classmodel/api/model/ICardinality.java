package cz.cvut.indepmod.classmodel.api.model;

/**
 * Date: 16.2.2011
 * Time: 9:57:43
 * @author Lucky
 *
 * This interface represents the cardinality/parciality of an relation. Instances
 * of this interface returns two values - from and to.
 * Example of cardinalities:
 * - cardinality 1..* --> getFrom() returns 1, getTo() returns -1 (-1 is like *)
 * - cardinality 1 (or 1..1) --> both methods getFrom() and getTo() return 1.
 * - cardinality 2..4 --> getFrom() returns 2, getTo() return 4
 * - cardinality * (or 0..*) --> getFrom() returns 0, getTo() returns -1
 */
public interface ICardinality {

    public int getFrom();

    public int getTo();

}
