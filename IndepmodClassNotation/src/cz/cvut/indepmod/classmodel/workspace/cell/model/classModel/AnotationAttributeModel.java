package cz.cvut.indepmod.classmodel.workspace.cell.model.classModel;

import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Date: 4.12.2010
 * Time: 17:35:43
 * @author Lucky
 */
public class AnotationAttributeModel extends AbstractModel implements IAnotationValue {

    private String name;
    private List<String> values;

    public AnotationAttributeModel(String name) {
        this.name = name;
        this.values = new ArrayList<String>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<String> getValues() {
        return new ArrayList<String>(this.values);
    }

    public void addValue(String value) {
        if (!this.values.contains(value)) {
            this.values.add(value);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.name);
        res.append(" = {");

        Iterator<String> it = this.values.iterator();
        while (it.hasNext()) {
            res.append(it.next());
            if (it.hasNext()) {
                res.append(", ");
            }
        }

        res.append("}");
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnotationAttributeModel other = (AnotationAttributeModel) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5 * this.name.hashCode();
        return hash;
    }


}
