package cz.cvut.indepmod.classmodel.workspace.cell.components;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;
import javax.swing.border.LineBorder;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 25.9.2010
 * Time: 10:22:36
 * <p/>
 * The purpose of this class is to paint a Class representation according to its model
 */

public abstract class AbstractElementComponent extends JComponent {

    protected final AbstractElementModel model;

    public AbstractElementComponent(AbstractElementModel model) {
        this.model = model;

        this.initLayout();
    }

    @Override
    public Dimension getPreferredSize() {
        int width = 0;
        int height = 0;
        for (Component child : this.getComponents()) {
            Dimension prefSize = child.getPreferredSize();
            if (prefSize.width > width) {
                width = prefSize.width;
            }

            height += prefSize.height;
        }
        return new Dimension(width, height);
    }

    protected JLabel createClassNameLabel() {
        JLabel res = new JLabel(this.model.getTypeName());
        if (this.model.isAbstract()) {
            Font f = res.getFont().deriveFont(Font.ITALIC);
            res.setFont(f);
        }
        return res;
    }

    protected JLabel createStereotypeLabel() {
        return new JLabel("<<"+ this.model.getStereotype() +">>");
    }

    protected JLabel createAnnotationLabel(IAnotation anot) {
        return new JLabel(anot.toString());
    }

    protected JLabel createMethodLabel(IMethod method) {
        return new JLabel(method.toString());
    }

    protected JLabel createAttributeLabel(IAttribute attribute) {
        return new JLabel(attribute.toString());
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;

        this.add(this.getClassNamePanel(), c);

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(this.getAnotationPanel(), c);
        this.add(this.getAttributePanel(), c);

        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        this.add(this.getMethodPanel(), c);
    }

    private JPanel getClassNamePanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new LineBorder(Color.BLACK));

        int y = 0;
        if (this.model.getStereotype() != null) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = y++;
            res.add(this.createStereotypeLabel(), c);
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = y;
        res.add(this.createClassNameLabel(), c);

        return res;
    }

    private JPanel getAnotationPanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new LineBorder(Color.BLACK));

        Set<IAnotation> anots = this.model.getAnotations();
        for (IAnotation anot : anots ) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = GridBagConstraints.RELATIVE;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.LINE_START;
            res.add(this.createAnnotationLabel(anot), c);
        }

        return res;
    }

    private JPanel getAttributePanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new LineBorder(Color.BLACK));

        Set<IAttribute> attrs = this.model.getAttributeModels();
        for (IAttribute attr : attrs ) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = GridBagConstraints.RELATIVE;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.LINE_START;
            res.add(this.createAttributeLabel(attr), c);
        }

        return res;
    }

    private JPanel getMethodPanel() {
        JPanel res = new JPanel(new GridBagLayout());
        res.setBorder(new LineBorder(Color.BLACK));

        Set<IMethod> methods = this.model.getMethodModels();
        Iterator<IMethod> it = methods.iterator();
        while (it.hasNext()) {
            IMethod method = it.next();

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = GridBagConstraints.RELATIVE;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            if (!it.hasNext()) {
                c.weighty = 0.5;
            }
            res.add(this.createMethodLabel(method), c);
        }
        return res;
    }
}
