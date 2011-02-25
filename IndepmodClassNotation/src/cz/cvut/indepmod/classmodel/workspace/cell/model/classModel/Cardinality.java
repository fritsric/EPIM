package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.ICardinality;

/**
 * Date: 16.2.2011
 * Time: 10:06:34
 * @author Lucky
 */
public class Cardinality implements ICardinality {

    public static final Cardinality ZERO = new Cardinality(0, 0);
    public static final Cardinality ZERO_INFINITY = new Cardinality(0, -1);
    public static final Cardinality ONE = new Cardinality(1, 1);
    public static final Cardinality ONE_INFINITY = new Cardinality(1, -1);

    private int from;
    private int to;

    public Cardinality(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        if (this.from == this.to) {
            return this.from +"";
        } else {
            StringBuilder res = new StringBuilder(6);
            res.append(this.from);
            res.append("..");
            res.append(this.to == -1 ? "*" : this.to);
            return res.toString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cardinality other = (Cardinality) obj;
        if (this.from != other.from) {
            return false;
        }
        if (this.to != other.to) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.from;
        hash = 89 * hash + this.to;
        return hash;
    }
}
