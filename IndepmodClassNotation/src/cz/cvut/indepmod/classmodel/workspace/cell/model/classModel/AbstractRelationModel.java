package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;

/**
 * Date: 30.11.2010
 * Time: 15:43:51
 * @author Lucky
 * Abstract class of relations (composition, agregation, generalization, implementation, ...)
 * Instances of this class is used as an User Object of
 * the Edge (Edge in the JGraph...). Cell which owns this instance should
 * set the pointer to itself - information about Classes and Cardinalities
 * are gathered from the Edge...
 */
public abstract class AbstractRelationModel extends AbstractModel implements IRelation {

    protected DefaultEdge cell;

    public AbstractRelationModel() {
        this.cell = null;
    }

    public void setCell(DefaultEdge cell) {
        this.cell = cell;
    }

    @Override
    public IClass getStartingClass() {
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getSource();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IClass clazz = (IClass) c.getUserObject();

        return clazz;
    }

    @Override
    public IClass getEndingClass() {
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getTarget();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IClass clazz = (IClass) c.getUserObject();

        return clazz;
    }

    @Override
    public String toString() {
        return "";
    }

    private void verifyCell() {
        if (this.cell == null) {
            throw new NullPointerException("Cell of RelationModel was not sat.");
        }
    }
}
