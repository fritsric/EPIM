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
public class AbstractClassModelDialog extends JDialog {

    public AbstractClassModelDialog(Frame owner, String title) {
        this(owner, title, true);
    }

    public AbstractClassModelDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public void setSizes() {
        this.pack();

        Rectangle abounds = getBounds();
        this.setSizes(abounds.width, abounds.height);
    }

    public void setSizes(int width, int height) {
        this.setSize(width, height);
        
        Dimension dim = getToolkit().getScreenSize();
        setLocation((dim.width - width) / 2,
                (dim.height - height) / 2);

        this.setMinimumSize(this.getSize());
        this.setVisible(true);
        this.requestFocus();
    }
}
