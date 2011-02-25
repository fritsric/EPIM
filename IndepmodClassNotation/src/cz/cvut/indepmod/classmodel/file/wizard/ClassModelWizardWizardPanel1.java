/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.indepmod.classmodel.file.wizard;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class ClassModelWizardWizardPanel1 implements WizardDescriptor.Panel {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private Component component;
    private WizardDescriptor wd;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new ClassModelWizardVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    @Override
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...), then:
        // return someCondition();
        // and when this condition changes (last form field filled in...) then:
        // fireChangeEvent();
        // and uncomment the complicated stuff below.
    }

    @Override
    public final void addChangeListener(ChangeListener l) {
    }

    @Override
    public final void removeChangeListener(ChangeListener l) {
    }
    /*
    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0
    public final void addChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.add(l);
    }
    }
    public final void removeChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.remove(l);
    }
    }
    protected final void fireChangeEvent() {
    Iterator<ChangeListener> it;
    synchronized (listeners) {
    it = new HashSet<ChangeListener>(listeners).iterator();
    }
    ChangeEvent ev = new ChangeEvent(this);
    while (it.hasNext()) {
    it.next().stateChanged(ev);
    }
    }
     */

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    @Override
    public void readSettings(Object settings) {
        this.wd = (WizardDescriptor) settings;
        ClassModelWizardVisualPanel1 panel = ((ClassModelWizardVisualPanel1)this.getComponent());

        DiagramType type = (DiagramType)this.wd.getProperty(NewFileConstants.TYPE);
        if (type == null || type == DiagramType.CLASS) {
            panel.setClassModelSelected(true);
        } else {
            panel.setBusinessModelSelected(true);
        }

        String fileName = Templates.getTargetName(wd);
        panel.setFileName(fileName);
    }

    @Override
    public void storeSettings(Object settings) {
        ClassModelWizardVisualPanel1 panel = ((ClassModelWizardVisualPanel1)this.getComponent());

        Templates.setTargetName(wd, panel.getFileName());

        if (panel.isClassModelSelected()) {
            this.wd.putProperty(NewFileConstants.TYPE, DiagramType.CLASS);
        } else if (panel.isBusinessModelSelected()) {
            this.wd.putProperty(NewFileConstants.TYPE, DiagramType.BUSINESS);
        } else {
            //Throw an exception?
        }
    }
}
