package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.resources.Resources;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 19:06:03
 * <p/>
 * This action makes REDO in Class Model diagram
 */
public class RedoAction extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_redo");

    private static final Logger LOG = Logger.getLogger(RedoAction.class.getName());

    public RedoAction() {
        super(ACTION_NAME, null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
//        ProjectDiagram diagram = ModelerSession.getProjectControlService().getSelectedDiagram();
//        if (diagram.getDiagramModel() instanceof ClassModelDiagramModel) {
//            ((ClassModelDiagramModel) diagram.getDiagramModel()).getUndoManager().redo();
//        } else {
//            LOG.error("Diagram Model is not instance of ClassModelDiagramModel");
//        }
    }
}
