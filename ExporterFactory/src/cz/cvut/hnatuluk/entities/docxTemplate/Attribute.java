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
 *         &lt;element ref="{}attrName"/>
 *         &lt;element ref="{}attrVisibility"/>
 *         &lt;element ref="{}attrDatatype"/>
 *         &lt;element ref="{}anotationList"/>
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
@XmlRootElement(name = "attribute")
public class Attribute {

    @XmlElement(required = true)
    protected Label label;
    @XmlElement(required = true)
    protected AttrName attrName;
    @XmlElement(required = true)
    protected AttrVisibility attrVisibility;
    @XmlElement(required = true)
    protected AttrDatatype attrDatatype;
    @XmlElement(required = true)
    protected AnotationList anotationList;
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
     * Gets the value of the attrName property.
     * 
     * @return
     *     possible object is
     *     {@link AttrName }
     *     
     */
    public AttrName getAttrName() {
        return attrName;
    }

    /**
     * Sets the value of the attrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrName }
     *     
     */
    public void setAttrName(AttrName value) {
        this.attrName = value;
    }

    /**
     * Gets the value of the attrVisibility property.
     * 
     * @return
     *     possible object is
     *     {@link AttrVisibility }
     *     
     */
    public AttrVisibility getAttrVisibility() {
        return attrVisibility;
    }

    /**
     * Sets the value of the attrVisibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrVisibility }
     *     
     */
    public void setAttrVisibility(AttrVisibility value) {
        this.attrVisibility = value;
    }

    /**
     * Gets the value of the attrDatatype property.
     * 
     * @return
     *     possible object is
     *     {@link AttrDatatype }
     *     
     */
    public AttrDatatype getAttrDatatype() {
        return attrDatatype;
    }

    /**
     * Sets the value of the attrDatatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrDatatype }
     *     
     */
    public void setAttrDatatype(AttrDatatype value) {
        this.attrDatatype = value;
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
