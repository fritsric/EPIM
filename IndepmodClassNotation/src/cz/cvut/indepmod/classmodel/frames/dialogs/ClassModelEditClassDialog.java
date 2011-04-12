package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.ClassModelCancelEditClassDialog;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogAddAnotation;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogAddAttribute;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogAddMethod;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogRemoveAnotation;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogRemoveAttribute;
import cz.cvut.indepmod.classmodel.actions.ClassModelEditClassDialogRemoveMethod;
import cz.cvut.indepmod.classmodel.actions.ClassModelSaveEditClassDialog;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ModelListener;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.jgraph.graph.GraphConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 7.10.2010
 * Time: 11:41:40
 *
 * This class represents dialog which is used for editing of a class.
 */
public class ClassModelEditClassDialog extends ClassModelEditClassDialogView implements ModelListener {

    private static final Logger LOG = Logger.getLogger(ClassModelEditClassDialog.class.getName());
    private ClassModel classModel;
    private ClassModelGraph graph;
    private DefaultGraphCell cell;

    private DefaultListModel attributeListModel;
    private DefaultListModel methodListModel;
    private DefaultListModel anotationListModel;

    public ClassModelEditClassDialog(Frame owner, ClassModelGraph graph, DefaultGraphCell cell, ClassModel cellModel) {
        super(owner);
        this.classModel = cellModel;
        this.graph = graph;
        this.cell = cell;

        this.attributeListModel = new DefaultListModel();
        this.methodListModel = new DefaultListModel();
        this.anotationListModel = new DefaultListModel();

        this.initValues();
        this.initAction();
        this.initHandlers();
        this.setSizes();
    }

    /**
     * Returns Selected Anotation in the attribute list
     * @return selected anotation
     */
    public AnotationModel getSelectedAnotation() {
        return (AnotationModel) this.anotationList.getSelectedValue();
    }

    /**
     * Returns Selected Attribute in the attribute list
     * @return selected attribute
     */
    public AttributeModel getSelectedAttribute() {
        return (AttributeModel) this.attributeList.getSelectedValue();
    }

    /**
     * Returns selected Method in the methods list
     * @return selected method
     */
    public MethodModel getSelectedMethod() {
        return (MethodModel) this.methodList.getSelectedValue();
    }

    /**
     * Returns filled name of the class
     * @return name of the class which is filled in the dialog
     */
    public String getClassName() {
        return this.classNameField.getText();
    }

    /**
     * This method is called when there is a change in the model and thus the
     * cell's view should be updated (e.g. attribute action adds an attribute)
     */
    public void updateCell() {
        Map<Object, Object> attributes = new HashMap<Object, Object>();
        GraphConstants.setResize(attributes, true);
        this.graph.getGraphLayoutCache().editCell(this.cell, attributes);
    }

    /**
     * Returns all Types from the graph
     * @return All types which are used in the graph
     */
    public Collection<TypeModel> getAllTypeModel() {
        return this.graph.getAllTypes();
    }

    /**
     * Initializes event handlers
     */
    private void initHandlers() {
        this.classModel.addListener(this);
    }

    /**
     * Initializes values in the dialog according to the class model
     */
    private void initValues() {
        String typeName = this.classModel.getTypeName();

        this.classNameField.setText(typeName);
        this.classNameField.setSelectionStart(0);
        this.classNameField.setSelectionEnd(typeName.length());

        this.attributeList.setModel(this.attributeListModel); //TODO - List in  JAVA SE 7 is a generic type
        this.methodList.setModel(this.methodListModel);
        this.anotationList.setModel(this.anotationListModel);

        this.loadAnotationListValues();
        this.loadAttributeListValues();
        this.loadMethodsListValues();
    }

    /**
     * Loads list of anotations into the anotation list which is situated in the
     * dialog
     */
    private void loadAnotationListValues() {
        Set<IAnotation> anots = this.classModel.getAnotations();
        this.anotationListModel.clear();
        for (IAnotation anot : anots) {
            this.anotationListModel.addElement(anot);
        }
    }

    /**
     * Loads list of attributes into the attribute list which is situated in the
     * dialog
     */
    private void loadAttributeListValues() {
        Set<IAttribute> attributes = this.classModel.getAttributeModels();
        this.attributeListModel.clear();
        for (IAttribute attr : attributes) {
            this.attributeListModel.addElement(attr);
        }
    }

    /**
     * Loads list of attributes into the attribute list which is situated in the
     * dialog
     */
    private void loadMethodsListValues() {
        Set<IMethod> methods = this.classModel.getMethodModels();
        this.methodListModel.clear();
        for (IMethod method : methods) {
            this.methodListModel.addElement(method);
        }
    }

    /**
     * Initializes actions (for saving, canceling, ...)
     */
    private void initAction() {
        //this.editAttributeButton.addActionListener(new ClassModelEditClassDialogEditAttribute(this));
        this.removeAttributeButton.addActionListener(new ClassModelEditClassDialogRemoveAttribute(this.classModel, this));
        this.addAnotationButton.addActionListener(new ClassModelEditClassDialogAddAnotation(classModel, this));
        this.addAttributeButton.addActionListener(new ClassModelEditClassDialogAddAttribute(this.classModel, this));
        this.addMethodButton.addActionListener(new ClassModelEditClassDialogAddMethod(this.classModel, this));
        this.removeAnotationButton.addActionListener(new ClassModelEditClassDialogRemoveAnotation(this.classModel, this));
        this.removeMethodButton.addActionListener(new ClassModelEditClassDialogRemoveMethod(this.classModel, this));
        this.saveButton.addActionListener(new ClassModelSaveEditClassDialog(this.classModel, this));
        this.cancelButton.addActionListener(new ClassModelCancelEditClassDialog(this));
    }

    @Override
    /**
     * Disposes this dialog
     */
    public void dispose() {
        this.classModel.removeListener(this);
        super.dispose();
    }

    @Override
    public void modelChanged() {
        this.loadAttributeListValues();
        this.loadMethodsListValues();
        this.loadAnotationListValues();
    }
}
