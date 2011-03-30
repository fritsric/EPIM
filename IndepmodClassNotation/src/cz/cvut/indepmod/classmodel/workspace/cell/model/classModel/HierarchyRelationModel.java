package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.ICardinality;
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
        this.cell = null;
    }

    @Override
    public ICardinality getStartCardinality() {
        return Cardinality.ONE;
    }

    @Override
    public ICardinality getEndCardinality() {
        return Cardinality.ONE;
    }

    @Override
    public RelationType getRelationType() {
        return this.type;
    }

}
