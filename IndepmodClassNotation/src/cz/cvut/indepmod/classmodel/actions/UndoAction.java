package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.resources.Resources;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 19:02:30
 * <p/>
 * This action makes UNDO action in Class model diagram
 */
public class UndoAction extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_undo");

    private static final Logger LOG = Logger.getLogger(UndoAction.class.getName());

    public UndoAction() {
        super(ACTION_NAME, null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
//        ProjectDiagram diagram = ModelerSession.getProjectControlService().getSelectedDiagram();
//        if (diagram.getDiagramModel() instanceof ClassModelDiagramModel) {
//            ((ClassModelDiagramModel) diagram.getDiagramModel()).getUndoManager().undo();
//        } else {
//            LOG.error("Diagram Model is not instance of ClassModelDiagramModel");
//        }
    }
}
