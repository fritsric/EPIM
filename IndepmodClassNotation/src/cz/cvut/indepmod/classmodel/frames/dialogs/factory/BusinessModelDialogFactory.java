package cz.cvut.indepmod.classmodel.frames.dialogs.factory;

import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractAttrCreatorDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditElementDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.BusinessModelAttrCreatorDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.BusinessModelEditElementDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import java.util.Collection;
import org.jgraph.graph.DefaultGraphCell;
import org.openide.windows.WindowManager;

/**
 * Date: 5.3.2011
 * Time: 10:39:40
 * @author Lucky
 */
public class BusinessModelDialogFactory extends AbstractDialogFactory {

    @Override
    public AbstractEditElementDialog createEditClassDialog(ClassModelGraph graph, DefaultGraphCell cell, AbstractElementModel model) {
        AbstractEditElementDialog dialog = new BusinessModelEditElementDialog(
                WindowManager.getDefault().getMainWindow(),
                graph, 
                cell, 
                model);
        return dialog;
    }

    @Override
    public AbstractAttrCreatorDialog createAttributeCreatorDialog(Collection<IType> types) {
        AbstractAttrCreatorDialog dialog = new BusinessModelAttrCreatorDialog(
                WindowManager.getDefault().getMainWindow(),
                types);
        return dialog;
    }

}
