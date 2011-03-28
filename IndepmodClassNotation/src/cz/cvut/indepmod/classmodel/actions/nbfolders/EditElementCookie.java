package cz.cvut.indepmod.classmodel.actions.nbfolders;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import org.openide.nodes.Node.Cookie;

/**
 * Date: 27.3.2011
 * Time: 16:17:29
 * @author Lucky
 */
public class EditElementCookie implements Cookie {

    private ClassModelAbstractAction action;

    public EditElementCookie(ClassModelAbstractAction action) {
        this.action = action;
    }

    public void edit() {
        this.action.actionPerformed(null);
    }

}
