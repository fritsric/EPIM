package cz.cvut.indepmod.classmodel.api.model;

/**
 * This class represents a relation between two classes. Relations contains
 * information such as starting class (from where), ending class (to where),
 * starting cardinality, ending cardinality and the type of the relation
 * (relation, aggregation, composition, generalization, ...)
 *
 * @author Lucky
 */
public interface IRelation {

    /**
     * Returns the class from where this relation starts
     * @return starting class
     */
    public IElement getStartingClass();

    /**
     * Returns the class where this relation ends
     * @return ending class
     */
    public IElement getEndingClass();

    /**
     * Returns the starting cardinality
     * @return the starting cardinality or null if the cardinality is not set
     */
    public ICardinality getStartCardinality();

    /**
     * Returns the ending cardinality
     * @return the ending cardinality or null if the cardinality is not set
     */
    public ICardinality getEndCardinality();

    /**
     * Return the type of the relation (Generalization, Agregation, ...)
     * @return the type of the relation
     */
    public RelationType getRelationType();

    /**
     * Returns the name of the relation.
     * @return the name of the relation or null if the ralation does not have
     * any name.
     */
    public String getRelationName();

    /**
     * Sets new name of the relation
     * @param name new name of the relation or null if we want to remove its
     * name
     */
    public void setRelationName(String name);
}
