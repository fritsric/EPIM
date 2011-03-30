package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IArrowableRelation;
import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import org.jgraph.graph.GraphConstants;

/**
 * Date: 12.11.2010
 * Time: 19:20:47
 * @author Lucky
 *
 * Model of the relation.
 */
public class RelationModel extends AbstractRelationModel implements IArrowableRelation {

    private RelationType type;
    
    public RelationModel(RelationType type) {
        this.type = type;
        this.cell = null;
    }

    @Override
    public ICardinality getStartCardinality() {
        return this.getCardinality(0);
    }

    @Override
    public ICardinality getEndCardinality() {
        return this.getCardinality(1);
    }

    @Override
    public RelationType getRelationType() {
        return this.type;
    }

    @Override
    public boolean isArrowOnEnd() {
        return this.getLineEnd() == GraphConstants.ARROW_SIMPLE;
    }

    private int getLineEnd() {
        return GraphConstants.getLineEnd(this.cell.getAttributes());
    }

    private Cardinality getCardinality(int index) {
        Cardinality res = null;
        try {
            Object[] cardinalities = GraphConstants.getExtraLabels(this.cell.getAttributes());
            res = (Cardinality) cardinalities[index];
        } catch (ClassCastException ex) {
            throw new ClassCastException("There is not Cardinality instance in the Extra Label of the edge!");
        } catch (NullPointerException ex) {
            throw new NullPointerException("There is no Extra Label in the edge!");
        }

        return res;
    }
}
