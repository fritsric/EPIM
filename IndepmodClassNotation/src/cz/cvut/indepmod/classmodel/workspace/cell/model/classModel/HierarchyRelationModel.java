package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.RelationType;

/**
 * Date: 30.11.2010
 * Time: 15:42:12
 * @author Lucky
 *
 * This class represents the hierarchy relation (Generalization, Realisation)
 */
public class HierarchyRelationModel extends AbstractRelationModel {

    private RelationType type;

    public HierarchyRelationModel(RelationType type) {
        this.type = type;
    }

    @Override
    public Cardinality getStartCardinality() {
        return Cardinality.ONE;
    }

    @Override
    public Cardinality getEndCardinality() {
        return Cardinality.ONE;
    }

    @Override
    public RelationType getRelationType() {
        return this.type;
    }


}
