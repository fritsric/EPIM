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
 *         &lt;element ref="{http://www.w3schools.com}modelImage"/>
 *         &lt;element ref="{http://www.w3schools.com}classList"/>
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
@XmlRootElement(name = "model")
public class Model {

    @XmlElement(required = true)
    protected Heading heading;
    @XmlElement(required = true)
    protected Object modelImage;
    @XmlElement(required = true)
    protected ClassList classList;
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
     * Gets the value of the modelImage property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getModelImage() {
        return modelImage;
    }

    /**
     * Sets the value of the modelImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setModelImage(Object value) {
        this.modelImage = value;
    }

    /**
     * Gets the value of the classList property.
     * 
     * @return
     *     possible object is
     *     {@link ClassList }
     *     
     */
    public ClassList getClassList() {
        return classList;
    }

    /**
     * Sets the value of the classList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassList }
     *     
     */
    public void setClassList(ClassList value) {
        this.classList = value;
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