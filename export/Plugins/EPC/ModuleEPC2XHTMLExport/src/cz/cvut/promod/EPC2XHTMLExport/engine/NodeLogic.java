package cz.cvut.promod.EPC2XHTMLExport.engine;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Nov 8, 2010
 * Time: 9:14:20 AM
 *
 * A special type of @see Node. Represents a logical function, one of those
 * described in the public enumeration LogicType. It has some exclusive members
 * and getters for those members, but the concept is the same as in its parent class 
 */
public class NodeLogic extends Node {

    public enum LogicType {
        eAND,
        eOR,
        eXOR,
        eAND_OR,
        eAND_XOR,
        eOR_AND,
        eOR_XOR,
        eXOR_AND,
        eXOR_OR
    }

    protected LogicType m_logicType;
    protected String m_condition1;
    protected String m_condition2;

    public NodeLogic(Type type, String UUID, String name, String note,
                     LogicType logicType, String condition1, String condition2) {
        super(type, UUID, name, note);
        m_logicType = logicType;
        m_condition1 = condition1;
        m_condition2 = condition2;
    }

    public LogicType getLogicType() {
        return m_logicType;
    }

    public String getCondition1() {
        return m_condition1;
    }

    public String getCondition2() {
        return m_condition2;
    }
}
