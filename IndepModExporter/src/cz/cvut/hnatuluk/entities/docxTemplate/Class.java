//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.26 at 08:51:35 odp. CET 
//


package cz.cvut.hnatuluk.entities.docxTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}label"/>
 *         &lt;element ref="{}heading"/>
 *         &lt;element ref="{}anotationList"/>
 *         &lt;element ref="{}attrList"/>
 *         &lt;element ref="{}classVisibility"/>
 *         &lt;element ref="{}methodList"/>
 *         &lt;element ref="{}relationList"/>
 *         &lt;element ref="{}classname"/>
 *       &lt;/all>
 *       &lt;attribute name="style" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "class")
public class Class {

    @XmlElement(required = true)
    protected Label label;
    @XmlElement(required = true)
    protected Heading heading;
    @XmlElement(required = true)
    protected AnotationList anotationList;
    @XmlElement(required = true)
    protected AttrList attrList;
    @XmlElement(required = true)
    protected ClassVisibility classVisibility;
    @XmlElement(required = true)
    protected MethodList methodList;
    @XmlElement(required = true)
    protected RelationList relationList;
    @XmlElement(required = true)
    protected Classname classname;
    @XmlAttribute(name = "style")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String style;

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link Label }
     *     
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link Label }
     *     
     */
    public void setLabel(Label value) {
        this.label = value;
    }

    /**
     * Gets the value of the heading property.
     * 
     * @return
     *     possible object is
     *     {@link Heading }
     *     
     */
    public Heading getHeading() {
        return heading;
    }

    /**
     * Sets the value of the heading property.
     * 
     * @param value
     *     allowed object is
     *     {@link Heading }
     *     
     */
    public void setHeading(Heading value) {
        this.heading = value;
    }

    /**
     * Gets the value of the anotationList property.
     * 
     * @return
     *     possible object is
     *     {@link AnotationList }
     *     
     */
    public AnotationList getAnotationList() {
        return anotationList;
    }

    /**
     * Sets the value of the anotationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnotationList }
     *     
     */
    public void setAnotationList(AnotationList value) {
        this.anotationList = value;
    }

    /**
     * Gets the value of the attrList property.
     * 
     * @return
     *     possible object is
     *     {@link AttrList }
     *     
     */
    public AttrList getAttrList() {
        return attrList;
    }

    /**
     * Sets the value of the attrList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrList }
     *     
     */
    public void setAttrList(AttrList value) {
        this.attrList = value;
    }

    /**
     * Gets the value of the classVisibility property.
     * 
     * @return
     *     possible object is
     *     {@link ClassVisibility }
     *     
     */
    public ClassVisibility getClassVisibility() {
        return classVisibility;
    }

    /**
     * Sets the value of the classVisibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassVisibility }
     *     
     */
    public void setClassVisibility(ClassVisibility value) {
        this.classVisibility = value;
    }

    /**
     * Gets the value of the methodList property.
     * 
     * @return
     *     possible object is
     *     {@link MethodList }
     *     
     */
    public MethodList getMethodList() {
        return methodList;
    }

    /**
     * Sets the value of the methodList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodList }
     *     
     */
    public void setMethodList(MethodList value) {
        this.methodList = value;
    }

    /**
     * Gets the value of the relationList property.
     * 
     * @return
     *     possible object is
     *     {@link RelationList }
     *     
     */
    public RelationList getRelationList() {
        return relationList;
    }

    /**
     * Sets the value of the relationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationList }
     *     
     */
    public void setRelationList(RelationList value) {
        this.relationList = value;
    }

    /**
     * Gets the value of the classname property.
     * 
     * @return
     *     possible object is
     *     {@link Classname }
     *     
     */
    public Classname getClassname() {
        return classname;
    }

    /**
     * Sets the value of the classname property.
     * 
     * @param value
     *     allowed object is
     *     {@link Classname }
     *     
     */
    public void setClassname(Classname value) {
        this.classname = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

}
