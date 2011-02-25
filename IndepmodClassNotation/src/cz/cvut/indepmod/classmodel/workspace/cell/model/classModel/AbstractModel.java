package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Date: 21.10.2010
 * Time: 11:49:41
 * @author Lucky
 */
public abstract class AbstractModel {

    Set<ModelListener> lsnrs = new HashSet<ModelListener>();

    public void addListener(ModelListener lsnr) {
        this.lsnrs.add(lsnr);
    }

    public void removeListener(ModelListener lsnr) {
        this.lsnrs.remove(lsnr);
    }

    protected void fireModelChanged() {
        for (ModelListener lsnr: this.lsnrs) {
            lsnr.modelChanged();
        }
    }

}
