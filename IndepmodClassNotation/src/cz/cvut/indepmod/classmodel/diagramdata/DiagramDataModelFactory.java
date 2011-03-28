package cz.cvut.indepmod.classmodel.diagramdata;

/**
 * Created by IntelliJ IDEA.
 * User: martin
 * Date: 9.8.2010
 * Time: 13:24:06
 */
public class DiagramDataModelFactory {

    private static DiagramDataModelFactory singleton = new DiagramDataModelFactory();

    public static DiagramDataModelFactory getInstance() {
        return singleton;
    }


    public DiagramDataModel createNewDiagramModel() {
        return new DiagramDataModel();
    }
}
