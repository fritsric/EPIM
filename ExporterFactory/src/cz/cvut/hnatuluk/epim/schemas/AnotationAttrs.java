//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.13 at 07:55:02 odp. CET 
//


package cz.cvut.hnatuluk.epim.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://www.w3schools.com}heading"/>
 *         &lt;element ref="{http://www.w3schools.com}anotationAttrName"/>
 *         &lt;element ref="{http://www.w3schools.com}anotationAttrValueList"/>
 *         &lt;element ref="{http://www.w3schools.com}staticText"/>
 *       &lt;/all>
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
@XmlRootElement(name = "anotationAttrs")
public class AnotationAttrs {

    @XmlElement(required = true)
    protected Heading heading;
    @XmlElement(required = true)
    protected Object anotationAttrName;
    @XmlElement(required = true)
    protected AnotationAttrValueList anotationAttrValueList;
    @XmlElement(required = true)
    protected StaticText staticText;

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
     * Gets the value of the anotationAttrName property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAnotationAttrName() {
        return anotationAttrName;
    }

    /**
     * Sets the value of the anotationAttrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAnotationAttrName(Object value) {
        this.anotationAttrName = value;
    }

    /**
     * Gets the value of the anotationAttrValueList property.
     * 
     * @return
     *     possible object is
     *     {@link AnotationAttrValueList }
     *     
     */
    public AnotationAttrValueList getAnotationAttrValueList() {
        return anotationAttrValueList;
    }

    /**
     * Sets the value of the anotationAttrValueList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnotationAttrValueList }
     *     
     */
    public void setAnotationAttrValueList(AnotationAttrValueList value) {
        this.anotationAttrValueList = value;
    }

    /**
     * Gets the value of the staticText property.
     * 
     * @return
     *     possible object is
     *     {@link StaticText }
     *     
     */
    public StaticText getStaticText() {
        return staticText;
    }

    /**
     * Sets the value of the staticText property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaticText }
     *     
     */
    public void setStaticText(StaticText value) {
        this.staticText = value;
    }

}