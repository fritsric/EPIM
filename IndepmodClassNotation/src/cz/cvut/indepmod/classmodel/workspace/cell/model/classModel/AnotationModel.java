package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Date: 25.11.2010
 * Time: 16:24:20
 * @author Lucky
 */
public class AnotationModel extends AbstractModel implements IAnotation {

    private String name;
    private List<IAnotationValue> values;

    public AnotationModel(String name) {
        this.name = name;
        this.values = new ArrayList<IAnotationValue>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<IAnotationValue> getAttributes() {
        return new ArrayList<IAnotationValue>(this.values);
    }

    public void addAttribute(IAnotationValue attr) {
        if (attr != null && !this.values.contains(attr)) {
            this.values.add(attr);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("@");
        res.append(this.name);

        if (this.values.isEmpty()) {
            return res.toString();
        } else {
            res.append(" (");

            Iterator<IAnotationValue> it = this.values.iterator();
            while (it.hasNext()) {
                res.append(it.next().toString());
                if (it.hasNext()) {
                    res.append(", ");
                }
            }

            res.append(")");
            return res.toString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnotationModel other = (AnotationModel) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }


}
