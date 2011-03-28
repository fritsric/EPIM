package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.AttributeCreatorAddAnotation;
import cz.cvut.indepmod.classmodel.actions.AttributeCreatorCreate;
import cz.cvut.indepmod.classmodel.actions.AttributeCreatorRemoveAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * Date: 17.10.2010
 * Time: 14:32:19
 * @author Lucky
 */
public class AbstractAttrCreatorDialog extends AbstractAttrCreatorDialogView {
    private Collection<IType> availableTypes;
    private IAttribute returnValue;
    protected DefaultListModel anotationListModel;

    public AbstractAttrCreatorDialog(Frame owner) {
        this(owner, new ArrayList<IType>(0));
    }

    public AbstractAttrCreatorDialog(Frame owner, Collection<IType> types) {
        super(owner);

        this.availableTypes = types;
        this.returnValue = null;

        this.initComponentBehavior();
        this.initValues();
        this.initAction();
        this.setSizes();
    }

    public IAttribute getReturnValue() {
        return this.returnValue;
    }
    
    public void setReturnValue(IAttribute attribute) {
        this.returnValue = attribute;
    }

    public List<IAnotation> getAnotationList() {
        List<IAnotation> res = new ArrayList<IAnotation>(this.anotationListModel.getSize());

        for (int i = 0; i < this.anotationListModel.getSize(); i++) {
            res.add((IAnotation) this.anotationListModel.get(i));
        }

        return res;
    }

    public void addAnotation(IAnotation anotation) {
        this.anotationListModel.addElement(anotation);
    }

    public void removeAnotationAt(int index) {
        this.anotationListModel.remove(index);
    }

    private void initAction() {
        this.createButton.addActionListener(new AttributeCreatorCreate(this));
        this.addAnotationButton.addActionListener(new AttributeCreatorAddAnotation(this));
        this.removeAnotationButton.addActionListener(new AttributeCreatorRemoveAnotation(this));
    }

    private void initValues() {
        this.anotationListModel = new DefaultListModel();
        this.anotationList.setModel(this.anotationListModel);

        this.attributeType.removeAllItems();
        for (IType type : this.availableTypes) {
            this.attributeType.addItem(type);
        }

        this.attributeVisibility.removeAllItems();
        this.attributeVisibility.addItem(Visibility.PUBLIC);
        this.attributeVisibility.addItem(Visibility.PROTECTED);
        this.attributeVisibility.addItem(Visibility.PRIVATE);
        this.attributeVisibility.addItem(Visibility.NONE);
    }

    private void initComponentBehavior() {
        this.attributeType.setEditable(true);
    }
}
