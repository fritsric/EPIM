package cz.cvut.indepmod.classmodel.workspace.cell;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractRelationModel;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultEdge;

/**
 * Date: 21.11.2010
 * Time: 11:01:11
 * @author Lucky
 *
 * This class extends the DefaultEdge. When an user object is of
 * AbstractRelationModel type, the pointer of thah object is sat to this instance.
 */
public class ClassModelRelation extends DefaultEdge {

    public ClassModelRelation(Object o, AttributeMap am) {
        super(o, am);
        this.initUserObjectPointer(o);
    }

    public ClassModelRelation(Object o) {
        super(o);
        this.initUserObjectPointer(o);
    }

    public ClassModelRelation() {
        super();
    }

    @Override
    public void setUserObject(Object userObject) {
        super.setUserObject(userObject);
        this.initUserObjectPointer(userObject);
    }

    /**
     * If the object in the parameter is AbstractRelationModel instance, set its pointer
     * to this cell. RelationModel instance need to know its cell because it
     * gathers the data (such as start class, end class, ...) from this object
     * @param o Object, which could be the RelationModel instance
     */
    private void initUserObjectPointer(Object o) {
        if (o instanceof AbstractRelationModel) {
            AbstractRelationModel m = (AbstractRelationModel) o;
            m.setCell(this);
        }
    }



}
