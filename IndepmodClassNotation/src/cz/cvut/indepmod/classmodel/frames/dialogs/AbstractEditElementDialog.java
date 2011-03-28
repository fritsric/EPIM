package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.actions.CancelEditElementDialog;
import cz.cvut.indepmod.classmodel.actions.EditElementDialogAddAnotation;
import cz.cvut.indepmod.classmodel.actions.EditElementDialogAddAttribute;
import cz.cvut.indepmod.classmodel.actions.EditElementDialogAddMethod;
import cz.cvut.indepmod.classmodel.actions.EditElementDialogRemoveAnotation;
import cz.cvut.indepmod.classmodel.actions.EditElementDialogRemoveAttribute;
import cz.cvut.indepmod.classmodel.actions.EditElementDialogRemoveMethod;
import cz.cvut.indepmod.classmodel.actions.SaveEditElementDialog;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.util.ClassModelLibrary;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ModelListener;
import java.awt.Frame;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

/**
 * Date: 5.3.2011
 * Time: 18:13:34
 * @author Lucky
 */
public class AbstractEditElementDialog extends AbstractEditElementDialogView implements ModelListener {

    private static final int DEFAULT_WIDTH = 350;
    private static final int DEFAULT_HEIGTH = 450;

    private ClassModelGraph graph;
    private DefaultGraphCell cell;
    private AbstractElementModel elementModel;

    private DefaultListModel attributeListModel;
    private DefaultListModel methodListModel;
    private DefaultListModel anotationListModel;

    public AbstractEditElementDialog(
            Frame owner,
            ClassModelGraph graph,
            DefaultGraphCell cell,
            AbstractElementModel classModel) {
        super(owner);

        this.graph = graph;
        this.cell = cell;
        this.elementModel = classModel;

        this.attributeListModel = new DefaultListModel();
        this.methodListModel = new DefaultListModel();
        this.anotationListModel = new DefaultListModel();

        this.initValues();
        this.initAction();
        this.initHandlers();
        this.setSizes(DEFAULT_WIDTH, DEFAULT_HEIGTH);
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

    public String getStereotype() {
        String res = ((String)this.stereotypeField.getSelectedItem()).trim();
        if (res.isEmpty()) {
            return null;
        } else {
            /**
             * This is here because user can add new stereotype. If the stereotype
             * is already in diagram data, it will not be added.
             */
            Globals.getInstance().getActualDiagramData().addStereotype(res);
            return res;
        }
    }

    public boolean isCheckedAbstract() {
        return this.abstractCheckBox.isSelected();
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
    public Collection<IType> getAllTypeModel() {
        Collection<IElement> classes = this.graph.getAllClasses();
        Collection<IType> staticTypes = Globals.getInstance().getActualDiagramData().getDataTypes();
        return ClassModelLibrary.joinTypeCollections(classes, staticTypes);
    }

    @Override
    public void dispose() {
        this.elementModel.removeListener(this);
        super.dispose();
    }

    @Override
    public void modelChanged() {
        this.loadAttributeListValues();
        this.loadMethodsListValues();
        this.loadAnotationListValues();
    }

    /**
     * Initializes event handlers
     */
    private void initHandlers() {
        this.elementModel.addListener(this);
    }

    /**
     * Initializes values in the dialog according to the class model
     */
    private void initValues() {
        Collection<String> stereotypes = Globals.getInstance().getActualDiagramData().getStereotypes();
        String stereotype = this.elementModel.getStereotype() == null ? "" : this.elementModel.getStereotype();
        String typeName = this.elementModel.getTypeName();

        this.stereotypeField.removeAllItems();
        this.stereotypeField.setEditable(true);
        for (String s : stereotypes) {
            this.stereotypeField.addItem(s);
        }
        this.stereotypeField.setSelectedItem(stereotype);
        
        this.classNameField.setText(typeName);
        this.classNameField.selectAll();

        this.abstractCheckBox.setSelected(this.elementModel.isAbstract());

        this.attributeList.setModel(this.attributeListModel);
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
        Set<IAnotation> anots = this.elementModel.getAnotations();
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
        Set<IAttribute> attributes = this.elementModel.getAttributeModels();
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
        Set<IMethod> methods = this.elementModel.getMethodModels();
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
        this.removeAttributeButton.addActionListener(new EditElementDialogRemoveAttribute(this.elementModel, this));
        this.addAnotationButton.addActionListener(new EditElementDialogAddAnotation(elementModel, this));
        this.addAttributeButton.addActionListener(new EditElementDialogAddAttribute(this.elementModel, this));
        this.addMethodButton.addActionListener(new EditElementDialogAddMethod(this.elementModel, this));
        this.removeAnotationButton.addActionListener(new EditElementDialogRemoveAnotation(this.elementModel, this));
        this.removeMethodButton.addActionListener(new EditElementDialogRemoveMethod(this.elementModel, this));
        this.saveButton.addActionListener(new SaveEditElementDialog(this.elementModel, this));
        this.cancelButton.addActionListener(new CancelEditElementDialog(this));
    }
}
