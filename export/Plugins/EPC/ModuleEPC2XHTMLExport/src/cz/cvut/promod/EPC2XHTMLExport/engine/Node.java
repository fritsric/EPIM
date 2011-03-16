package cz.cvut.promod.EPC2XHTMLExport.engine;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Nov 7, 2010
 * Time: 7:10:27 PM
 *
 * Represents a single node in the @see Diagram. Can have any number of
 * incoming and outgoing links to other nodes. Each node falls into one of the
 * categories described in the public Type enumeration.
 *
 * The constructor servers as the general setter, while other methods are getters
 * for all of the members of this class.
 */
public class Node {

    public enum Type {
        eApplicationSoftware,
        eComputerHW,
        eDeliverable,
        eEvent,
        eFunction,
        eGoal,
        eInformationObject,
        eLogicFunction,
        eMachine,
        eMessage,
        eOrganizationRole,
        eOrganizationUnit
    }

    protected Type m_type;
    protected String m_UUID;
    protected String m_name;
    protected String m_note;
    protected ArrayList<Link> m_in_links;
    protected ArrayList<Link> m_out_links;

    public Node(Type type, String UUID, String name, String note)
            throws IllegalArgumentException {
        if (UUID == null) throw new IllegalArgumentException("Node ERROR: null UUID");

        m_type = type;
        m_UUID = UUID;
        m_name = name;
        m_note = note;
        m_in_links = new ArrayList<Link>();
        m_out_links = new ArrayList<Link>();
    }

    public Type getType() {
        return m_type;
    }

    public String getUUID() {
        return m_UUID;
    }

    public String getName() {
        return m_name;
    }

    public String getNote() {
        return m_note;
    }

    public ArrayList<Link> getInLinks() {
        return m_in_links;
    }

    public void addInLink(Link link) {
        m_in_links.add(link);
    }

    public ArrayList<Link> getOutLinks() {
        return m_out_links;
    }

    public void addOutLink(Link link) {
        m_out_links.add(link);
    }
}
