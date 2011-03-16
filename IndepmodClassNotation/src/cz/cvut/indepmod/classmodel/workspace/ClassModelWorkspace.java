package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.modelFactory.ClassModelDiagramModelFactory;
import cz.cvut.indepmod.classmodel.file.ClassModelSaveCookie;
import cz.cvut.indepmod.classmodel.file.ClassModelXMLDataObject;
import cz.cvut.indepmod.classmodel.modelFactory.diagramModel.ClassModelDiagramDataModel;
import cz.cvut.indepmod.classmodel.persistence.xml.ClassModelXMLCoder;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.CloneableTopComponent;
import org.openide.windows.TopComponent;

/**
 * This class represents a Workspace of class model notation. It contains JGraph.
 * @author Lucky
 */
public class ClassModelWorkspace extends CloneableTopComponent implements GraphModelListener {

    private static final Logger LOG = Logger.getLogger(ClassModelWorkspace.class.getName());

    private ClassModelDiagramDataModel diagramDataModel;
    
    private ClassModelGraph graph;
    private ClassModelModel classModelAPI;
    private Map<String, ClassModelAbstractAction> actions;
    private ToolChooserModel selectedTool;
    private ClassModelSaveCookie saveCookie;
    private InstanceContent lookupContent = new InstanceContent();
    private boolean modified;

    public ClassModelWorkspace() {
        this.diagramDataModel = ClassModelDiagramModelFactory.getInstance().createNewDiagramModel();
        this.init();
    }

    public ClassModelWorkspace(ClassModelXMLDataObject dataObject) {
        try {
            InputStream inStream = dataObject.getPrimaryFile().getInputStream();
            this.diagramDataModel = ClassModelXMLCoder.getInstance().decode(inStream);
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, "File could not be opened: {0}", ex.getMessage());
        }

        if (this.diagramDataModel != null) {
            this.init();
        } else {
            this.diagramDataModel = ClassModelDiagramModelFactory.getInstance().createNewDiagramModel();
            this.init();
        }
        this.lookupContent.add(dataObject);
    }

    public ClassModelWorkspace(ClassModelDiagramDataModel diagramModel) {
        this.diagramDataModel = diagramModel;
        this.init();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }

    @Override
    public void graphChanged(GraphModelEvent gme) {
        this.setModified(true);
    }

    public void setModified(boolean modified) {
        if (!this.modified && modified) {
            this.modified = modified;
            this.lookupContent.add(this.saveCookie);
        } else if (this.modified && !modified) {
            this.modified = modified;
            this.lookupContent.remove(this.saveCookie);
        }
    }

//===========================PRIVATE METHODS====================================
    private void init() {
        this.actions = new HashMap<String, ClassModelAbstractAction>();
        this.selectedTool = new ToolChooserModel();
        this.graph = new ClassModelGraph(this.actions, this.selectedTool, this.diagramDataModel);
        this.classModelAPI = new ClassModelModel(this.graph);
        this.saveCookie = new ClassModelSaveCookie(this, this.diagramDataModel);
        this.modified = false;

        this.graph.setMarqueeHandler(new ClassModelMarqueeHandler(this.graph, this.selectedTool, this.actions));
        this.graph.setGraphLayoutCache(this.diagramDataModel.getLayoutCache()); //TODO: THIS SHOULD BE ADDED THROUGH CONSTRUCTOR
        this.graph.getModel().addGraphModelListener(this);

        this.initLookup();
        //this.initActions();
        //this.initPopupMenu();
        this.initLayout();
    }

    /**
     * This method set the layout manager and add the Graph to the panel
     */
    private void initLayout() {
        this.setLayout(new GridLayout(1, 1));
        this.add(new JScrollPane(this.graph));
    }

//    /**
//     * This method inititalizes actions
//     */
//    private void initActions() {
//        this.actions.put(
//                ClassModelUndoAction.ACTION_NAME,
//                new ClassModelUndoAction());
//
//        this.actions.put(
//                ClassModelRedoAction.ACTION_NAME,
//                new ClassModelRedoAction());
//    }

//    private void initPopupMenu() {
//        ClassModelAbstractAction deleteAction = this.actions.get(ClassModelDeleteAction.ACTION_NAME);
//        deleteAction.setEnabled(true);
//
//        ClassModelAbstractAction editAction = this.actions.get(ClassModelEditAction.ACTION_NAME);
//
//        this.popupMenu.add(deleteAction);
//        this.popupMenu.add(editAction);
//    }

    private void initLookup() {
        this.associateLookup(new AbstractLookup(this.lookupContent));
        this.lookupContent.add(this.selectedTool);
        this.lookupContent.add(this.classModelAPI);
    }
}
