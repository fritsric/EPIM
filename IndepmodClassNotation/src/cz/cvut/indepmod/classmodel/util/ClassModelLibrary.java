package cz.cvut.indepmod.classmodel.util;

import cz.cvut.indepmod.classmodel.api.model.IType;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Date: 20.3.2011
 * Time: 9:19:17
 * @author Lucky
 */
public class ClassModelLibrary {

    public static Collection<IType> joinTypeCollections(
            Collection<? extends IType> types1,
            Collection<? extends IType> types2) {
        Collection<IType> res = new ArrayList<IType>(types1.size() + types2.size());

        for (IType type : types1) {
            res.add(type);
        }
        for (IType type : types2) {
            res.add(type);
        }

        return res;
    }

}
