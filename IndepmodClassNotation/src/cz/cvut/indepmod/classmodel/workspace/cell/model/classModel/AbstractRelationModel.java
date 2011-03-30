package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IElement;
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

    private String name;

    public AbstractRelationModel() {
        this.cell = null;
        this.name = null;
    }

    public void setCell(DefaultEdge cell) {
        this.cell = cell;
    }

    public DefaultEdge getCell() {
        return cell;
    }

    @Override
    public IElement getStartingClass() {
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getSource();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IElement clazz = (IElement) c.getUserObject();

        return clazz;
    }

    @Override
    public IElement getEndingClass() {
        this.verifyCell();

        DefaultPort p = (DefaultPort) this.cell.getTarget();
        DefaultGraphCell c = (DefaultGraphCell) p.getParent();
        IElement clazz = (IElement) c.getUserObject();

        return clazz;
    }

    @Override
    public String getRelationName() {
        return this.name;
    }

    @Override
    public void setRelationName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private void verifyCell() {
        if (this.cell == null) {
            throw new NullPointerException("Cell of RelationModel was not sat.");
        }
    }
}
