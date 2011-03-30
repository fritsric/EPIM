package cz.cvut.indepmod.classmodel.persistence.xml;

import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.AnotationAtributeModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.AnotationModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.AttributeModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.CardinalityPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.ClassModelDiagramModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.AbstractElementlPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.HierarchyRelationModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.MethodModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.RelationModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.persistence.xml.delegate.TypeModelPersistenceDelegate;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraphModel;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.HierarchyRelationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.beans.BeanInfo;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.ExceptionListener;
import java.beans.Expression;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PersistenceDelegate;
import java.beans.PropertyDescriptor;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.PortView;

/**
 * Date: 31.10.2010
 * Time: 10:01:30
 * @author Lucky
 *
 * This class is used for save an load classModelNotation from/in a XML file. Its methods serves to store the class
 * model in and load it again from an XML document. It uses
 * java.beans.XMLEncoder for persistence.
 */
public class ClassModelXMLCoder {

    public static final String CELL_PROPERTY = "cell";
    public static final String ATTRIBUTES_PROPERTY = "attributes";
    public static final String USER_OBJECT_PROPERTY = "userObject";
    public static final String ROOTS_PROPERTY = "roots";
    public static final String MODEl_PROPERTY = "model";
    public static final String FACTORY_PROPERTY = "factory";
    public static final String CELL_VIEWS_PROPERTY = "cellViews";
    public static final String HIDD_CELLS_PROPERTY = "hiddenCellViews";
    public static final String PARTIAL_PROPERTY = "partial";
    public static final String ROUNTING_SIMPLE_PROPERTY = "getROUTING_SIMPLE";
    public static final String ROUNTING_DEFAULT_PROPERTY = "getROUTING_DEFAULT";
    public static final String TRANSIENT_PROPERTY = "transient";
    private static final Logger LOG = Logger.getLogger(ClassModelXMLCoder.class.getName());
    private static ClassModelXMLCoder singleton;

    static {
        makeTransient(ClassModelVertexView.class);
        makeTransient(EdgeView.class);
        makeTransient(PortView.class);
    }

    public static ClassModelXMLCoder getInstance() {
        if (singleton == null) {
            singleton = new ClassModelXMLCoder();
        }

        return singleton;
    }

    private ClassModelXMLCoder() {
    }

    public void encode(DiagramDataModel diagramModel, OutputStream stream) {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(stream));

        this.initEncoder(encoder);
        encoder.writeObject(diagramModel);
        encoder.close();
    }

    public DiagramDataModel decode(InputStream stream) {
        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(stream));
            if (dec != null) {
                Object obj = dec.readObject();
                dec.close();
                return (DiagramDataModel) obj;
            }
        } catch (NoSuchElementException ex) {
            LOG.severe(ex.getMessage());
        }
        return null;
    }

    private void initEncoder(XMLEncoder encoder) {
        // Better debugging output, in case you need it
        encoder.setExceptionListener(new ExceptionListener() {

            @Override
            public void exceptionThrown(Exception e) {
                LOG.severe(e.getMessage());
            }
        });

        encoder.setPersistenceDelegate(DiagramDataModel.class,
                new ClassModelDiagramModelPersistenceDelegate());

        //GRAPH LAYOUT CACHE====================================================
        encoder.setPersistenceDelegate(GraphLayoutCache.class,
                new DefaultPersistenceDelegate(new String[]{
                    MODEl_PROPERTY,
                    FACTORY_PROPERTY,
                    CELL_VIEWS_PROPERTY,
                    HIDD_CELLS_PROPERTY,
                    PARTIAL_PROPERTY
                }));

        //GRAPH MODEL===========================================================
        encoder.setPersistenceDelegate(DefaultGraphModel.class,
                new DefaultPersistenceDelegate(new String[]{
                    ROOTS_PROPERTY,
                    ATTRIBUTES_PROPERTY
                }));

        encoder.setPersistenceDelegate(ClassModelGraphModel.class,
                new DefaultPersistenceDelegate(new String[]{
                    ROOTS_PROPERTY,
                    ATTRIBUTES_PROPERTY
                }));

        //GRAPH VERTICES========================================================
        encoder.setPersistenceDelegate(DefaultGraphCell.class,
                new DefaultPersistenceDelegate(
                new String[]{USER_OBJECT_PROPERTY}));

        encoder.setPersistenceDelegate(ClassModelClassCell.class,
                new DefaultPersistenceDelegate(
                new String[]{USER_OBJECT_PROPERTY}));

//        encoder.setPersistenceDelegate(ClassModelRelation.class,
//                new DefaultPersistenceDelegate(
//                new String[]{USER_OBJECT_PROPERTY}));


        //USER OBJECTS AND ITS SUBOBJECTS=======================================
        encoder.setPersistenceDelegate(AbstractElementModel.class, new AbstractElementlPersistenceDelegate());
        encoder.setPersistenceDelegate(AttributeModel.class, new AttributeModelPersistenceDelegate());
        encoder.setPersistenceDelegate(MethodModel.class, new MethodModelPersistenceDelegate());
        encoder.setPersistenceDelegate(TypeModel.class, new TypeModelPersistenceDelegate());
        encoder.setPersistenceDelegate(RelationModel.class, new RelationModelPersistenceDelegate());
        encoder.setPersistenceDelegate(HierarchyRelationModel.class, new HierarchyRelationModelPersistenceDelegate());
        encoder.setPersistenceDelegate(AnotationModel.class, new AnotationModelPersistenceDelegate());
        encoder.setPersistenceDelegate(AnotationAttributeModel.class, new AnotationAtributeModelPersistenceDelegate());
        encoder.setPersistenceDelegate(Cardinality.class, new CardinalityPersistenceDelegate());

        //GRAPH VIEWS===========================================================
//        encoder.setPersistenceDelegate(AbstractCellView.class,
//                new DefaultPersistenceDelegate(new String[]{
//                    CELL_PROPERTY,
//                    ATTRIBUTES_PROPERTY
//                }));

        encoder.setPersistenceDelegate(ClassModelVertexView.class,
                new DefaultPersistenceDelegate(new String[]{
                    CELL_PROPERTY
                }));

//        encoder.setPersistenceDelegate(PortView.class,
//                new DefaultPersistenceDelegate(new String[]{
//                    CELL_PROPERTY,
//                    ATTRIBUTES_PROPERTY
//                }));
//
//        encoder.setPersistenceDelegate(EdgeView.class,
//                new DefaultPersistenceDelegate(new String[]{
//                    CELL_PROPERTY,
//                    ATTRIBUTES_PROPERTY
//                }));

        //ROUTING===============================================================
        encoder.setPersistenceDelegate(
                DefaultEdge.DefaultRouting.class,
                new PersistenceDelegate() {

                    @Override
                    protected Expression instantiate(Object oldInstance, Encoder out) {
                        return new Expression(
                                oldInstance,
                                GraphConstants.class,
                                ROUNTING_SIMPLE_PROPERTY,
                                null);
                    }
                });

        encoder.setPersistenceDelegate(DefaultEdge.LoopRouting.class,
                new PersistenceDelegate() {

                    @Override
                    protected Expression instantiate(Object oldInstance, Encoder out) {
                        return new Expression(
                                oldInstance,
                                GraphConstants.class,
                                ROUNTING_DEFAULT_PROPERTY,
                                null);
                    }
                });

        encoder.setPersistenceDelegate(ArrayList.class, encoder.getPersistenceDelegate(List.class));

    }

    private static void makeTransient(Class clazz) {
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (final PropertyDescriptor descriptor : propertyDescriptors) {
                if (!descriptor.getName().equals(CELL_PROPERTY) && !descriptor.getName().equals(ATTRIBUTES_PROPERTY)) {
                    descriptor.setValue(TRANSIENT_PROPERTY, true);
                }
            }

        } catch (IntrospectionException e) {
            LOG.log(Level.SEVERE, "Unable to set some field(s) of {0} class transient.", clazz.getName());
        }
    }
}
