package cz.cvut.indepmod.classmodel.api.model;

/**
 *
 * @author Lucky
 */
public interface IArrowableRelation extends IRelation {

    /**
     * Returns true if there is an arrow on the end of the line
     * @return
     */
    public boolean isArrowOnEnd();

}
