package cz.cvut.indepmod.classmodel.file;

import cz.cvut.indepmod.classmodel.workspace.ClassModelWorkspace;
import org.openide.cookies.CloseCookie;
import org.openide.cookies.OpenCookie;
import org.openide.loaders.MultiDataObject.Entry;
import org.openide.loaders.OpenSupport;
import org.openide.windows.CloneableTopComponent;

/**
 * Date: 6.11.2010
 * Time: 12:36:00
 * @author Lucky
 */
class ClassModelXMLOpenSupport extends OpenSupport implements OpenCookie, CloseCookie {

    public ClassModelXMLOpenSupport(Entry primaryEntry) {
        super(primaryEntry);
    }

    @Override
    protected CloneableTopComponent createCloneableTopComponent() {
        ClassModelXMLDataObject dobj = (ClassModelXMLDataObject) entry.getDataObject();

        ClassModelWorkspace workspace = new ClassModelWorkspace(dobj);
        workspace.setName(dobj.getName());
        return workspace;
    }

}
