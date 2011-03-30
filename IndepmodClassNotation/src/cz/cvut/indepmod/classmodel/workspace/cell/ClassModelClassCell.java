package cz.cvut.indepmod.classmodel.workspace.cell;

import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import javax.swing.tree.MutableTreeNode;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 12.11.2010
 * Time: 18:34:14
 * @author Lucky
 *
 * This class is special case of DefaultGraphCell which is created to insert
 * AbstractElementModel instance as a User object. User Object does not implicitly know
 * who is its cell (DefaultGraphInstance). But AbstractElementModel instance need to know
 * this because it provides its relations by asking the cell for its relations.
 */
public class ClassModelClassCell extends DefaultGraphCell {

    public ClassModelClassCell(Object o, AttributeMap am, MutableTreeNode[] mtns) {
        super(o, am, mtns);
        this.initUserObjectPointer(o);
    }

    public ClassModelClassCell(Object o, AttributeMap am) {
        super(o, am);
        this.initUserObjectPointer(o);
    }

    public ClassModelClassCell(Object o) {
        super(o);
        this.initUserObjectPointer(o);
    }

    public ClassModelClassCell() {
    }

    @Override
    public void setUserObject(Object userObject) {
        super.setUserObject(userObject);
        this.initUserObjectPointer(userObject);
    }



    /**
     * If the object in the parameter is AbstractElementModel instance, set its cell
     * pointer to this object. AbstractElementModel user object needs to know to which
     * cell it belongs because it returns relations according to this cell.
     * @param o Object, which could be the AbstractElementModel insance
     */
    private void initUserObjectPointer(Object o) {
        if (o instanceof AbstractElementModel) {
            AbstractElementModel cm = (AbstractElementModel) o;
            cm.setCell(this);
        }
    }
}
