package cz.cvut.indepmod.classmodel.file;

import cz.cvut.indepmod.classmodel.modelFactory.diagramModel.ClassModelDiagramDataModel;
import cz.cvut.indepmod.classmodel.persistence.xml.ClassModelXMLCoder;
import cz.cvut.indepmod.classmodel.workspace.ClassModelWorkspace;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

/**
 * Date: 6.11.2010
 * Time: 9:24:57
 * @author Lucky
 */
public class ClassModelSaveCookie implements SaveCookie {

    private ClassModelWorkspace workspace;
    private ClassModelDiagramDataModel diagramModel;

    public ClassModelSaveCookie(ClassModelWorkspace workspace, ClassModelDiagramDataModel diagramModel) {
        this.workspace = workspace;
        this.diagramModel = diagramModel;
    }

    @Override
    public void save() throws IOException {
        DataObject dataObj = this.workspace.getLookup().lookup(DataObject.class);
        if (dataObj != null) {
            OutputStream outputStream = dataObj.getPrimaryFile().getOutputStream();
            ClassModelXMLCoder.getInstance().encode(this.diagramModel, outputStream);
            this.workspace.setModified(false);
        } else {
            
        }
    }

    private void save(File f) throws IOException {
        
    }

}
