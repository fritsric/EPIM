package cz.cvut.indepmod.classmodel.frames.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JDialog;

/**
 * Date: 17.10.2010
 * Time: 12:10:02
 * @author Lucky
 */
public class ClassModelAbstractDialog extends JDialog {

    public ClassModelAbstractDialog(Frame owner, String title) {
        super(owner, title, true);
    }

    public void setSizes() {
        this.pack();

        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2,
                (dim.height - abounds.height) / 2);

        this.setMinimumSize(this.getSize());
        this.setVisible(true);
        this.requestFocus();
    }
}
