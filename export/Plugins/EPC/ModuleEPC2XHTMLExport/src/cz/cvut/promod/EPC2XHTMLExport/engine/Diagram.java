package cz.cvut.promod.EPC2XHTMLExport.engine;

import cz.cvut.promod.epc.modelFactory.epcGraphItemModels.*;
import org.jgraph.graph.GraphModel;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Nov 7, 2010
 * Time: 7:12:55 PM
 *
 * This class can read EPC diagram specific JGraph data and store it
 * in its own format, from which it can be easily gotten. This format
 * consists of a list of nodes and List of links. Each link links two
 * nodes and has a direction. Nodes have lists of incoming and outgoing
 * links. This means that everything is well cross referenced and can be
 * queried separately. For the data structure of nodes and links @see Node,
 * @see Link and @see NodeLogic (a special type of node).
 */
public class Diagram {

    private String m_name;
    private ArrayList<Node> m_nodes;
    private ArrayList<Link> m_links;

    /**
     * The constructor takes a reference to the GraphModel used to reconstruct the
     * graph data in a new custom format.
     *
     * @param model - the graph data
     * @param name  - the name of the diagram
     */
    public Diagram(GraphModel model, String name) {
        m_name = name;
        m_nodes = new ArrayList<Node>();
        m_links = new ArrayList<Link>();

        int cnt = model.getRootCount();
        ArrayList<Object> links = new ArrayList<Object>();

        for (int i = 0; i < cnt; ++i) {
            Object o = model.getRootAt(i);
            if (model.isEdge(o))
                links.add(o);
            else
                m_nodes.add(loadNode(model.getValue(o)));
        }

        for (int i = 0; i < links.size(); ++i) {
            Object o = links.get(i);
            Node f = loadNode(model.getValue(model.getParent(model.getSource(o))));
            f = getNode(f.getUUID());
            Node t = loadNode(model.getValue(model.getParent(model.getTarget(o))));
            t = getNode(t.getUUID());
            addLink(f, t, loadLink(model.getValue(o), f, t));
        }
    }

    /**
     * @return the list of nodes
     */
    public ArrayList<Node> getNodes() {
        return m_nodes;
    }

    /**
     * @return the list of links
     */
    public ArrayList<Link> getLinks() {
        return m_links;
    }

    /**
     * @return the name of this diagram
     */
    public String getName() {
        return m_name;
    }

    private Node getNode(String uuid) {
        for (int i = 0; i < m_nodes.size(); ++i)
            if (m_nodes.get(i).getUUID().equals(uuid))
                return m_nodes.get(i);
        return null;
    }

    private EPCEditableVertex translateNode(Object o) {
        if      (o instanceof ApplicationSoftwareModel)
            return (ApplicationSoftwareModel)o;
        else if (o instanceof ComputerHWModel)
            return (ComputerHWModel)o;
        else if (o instanceof DeliverableModel)
            return (DeliverableModel)o;
        else if (o instanceof EventModel)
            return (EventModel)o;
        else if (o instanceof FunctionModel)
            return (FunctionModel)o;
        else if (o instanceof GoalModel)
            return (GoalModel)o;
        else if (o instanceof InformationObjectModel)
            return (InformationObjectModel)o;
        else if (o instanceof MachineModel)
            return (MachineModel)o;
        else if (o instanceof OrganizationRoleModel)
            return (OrganizationRoleModel)o;
        else if (o instanceof OrganizationUnitModel)
            return (OrganizationUnitModel)o;
        return null;
    }

    private Node.Type translateNodeType(Object o) {
        if      (o instanceof ApplicationSoftwareModel)
            return Node.Type.eApplicationSoftware;
        else if (o instanceof ComputerHWModel)
            return Node.Type.eComputerHW;
        else if (o instanceof DeliverableModel)
            return Node.Type.eDeliverable;
        else if (o instanceof EventModel)
            return Node.Type.eEvent;
        else if (o instanceof FunctionModel)
            return Node.Type.eFunction;
        else if (o instanceof GoalModel)
            return Node.Type.eGoal;
        else if (o instanceof InformationObjectModel)
            return Node.Type.eInformationObject;
        else if (o instanceof LogicFunctionModel)
            return Node.Type.eLogicFunction;
        else if (o instanceof MachineModel)
            return Node.Type.eMachine;
        else if (o instanceof MessageModel)
            return Node.Type.eMessage;
        else if (o instanceof OrganizationRoleModel)
            return Node.Type.eOrganizationRole;
        else if (o instanceof OrganizationUnitModel)
            return Node.Type.eOrganizationUnit;
        return null;
    }

    private NodeLogic.LogicType translateLogicType(LogicFunctionModel.LogicOperator type){
        switch (type) {
            case AND     : return NodeLogic.LogicType.eAND;
            case OR      : return NodeLogic.LogicType.eOR;
            case XOR     : return NodeLogic.LogicType.eXOR;
            case AND_OR  : return NodeLogic.LogicType.eAND_OR;
            case AND_XOR : return NodeLogic.LogicType.eAND_XOR;
            case OR_AND  : return NodeLogic.LogicType.eOR_AND;
            case OR_XOR  : return NodeLogic.LogicType.eOR_XOR;
            case XOR_AND : return NodeLogic.LogicType.eXOR_AND;
            case XOR_OR  : return NodeLogic.LogicType.eXOR_OR;
        }
        return null;
    }

    private Node loadNode(Object o) {

        Node.Type type = translateNodeType(o);

        if        (type == Node.Type.eLogicFunction) {
            LogicFunctionModel logic = (LogicFunctionModel)o;
            return new NodeLogic(type,
                                 logic.getUuid().toString(),
                                 logic.toString(),
                                 logic.getNote(),
                                 translateLogicType(logic.getOperator()),
                                 logic.getCondition1(),
                                 logic.getCondition2());

        } else if (type == Node.Type.eMessage) {
            MessageModel message = (MessageModel)o;
            return new Node(type,
                            message.getUuid().toString(),
                            message.toString(),
                            message.getNote());
        }

        EPCEditableVertex node = translateNode(o);
        return new Node(type,
                        node.getUuid().toString(),
                        node.getName(),
                        node.getNote());
    }

    private Link.Type translateLinkType(EdgeModel.EdgeType type) {
        switch (type) {
            case CONTROL_FLOW              : return Link.Type.eControl;
            case ORGANIZATION_FLOW         : return Link.Type.eOrganization;
            case INFORMATION_FLOW          : return Link.Type.eInformation;
            case INFORMATION_SERVICES_FLOW : return Link.Type.eInformationService;
            case MATERIAL_FLOW             : return Link.Type.eMaterial;
        }
        return null;
    }

    private Link loadLink(Object o, Node f, Node t) {
        EdgeModel link = (EdgeModel)o;
        
        return new Link(translateLinkType(link.getEdgeType()),
                        f,
                        t,
                        link.getName(),
                        link.getNote());
    }

    private void addLink(Node f, Node t, Link link) {
        m_links.add(link);
        
        for (int i = 0; i < m_nodes.size(); ++i) {
            if (m_nodes.get(i).getUUID().equals(f.getUUID()))
                m_nodes.get(i).addOutLink(link);
            if (m_nodes.get(i).getUUID().equals(t.getUUID()))
                m_nodes.get(i).addInLink(link);
        }
    }
}
