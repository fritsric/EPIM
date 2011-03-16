package cz.cvut.indepmod.classmodel.workspace;

import java.util.List;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphModel;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 30.9.2010
 * Time: 15:28:58
 */
public class ClassModelGraphModel extends DefaultGraphModel {

    public ClassModelGraphModel() {
        super();
    }

    public ClassModelGraphModel(final List roots, final AttributeMap attributes){
        super(roots, attributes);
    }

}
