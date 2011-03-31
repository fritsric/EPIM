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
 *         &lt;element ref="{}methodVisibility"/>
 *         &lt;element ref="{}methodName"/>
 *         &lt;element ref="{}methodReturnType"/>
 *         &lt;element ref="{}attrList"/>
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
@XmlRootElement(name = "method")
public class Method {

    @XmlElement(required = true)
    protected Label label;
    @XmlElement(required = true)
    protected MethodVisibility methodVisibility;
    @XmlElement(required = true)
    protected MethodName methodName;
    @XmlElement(required = true)
    protected MethodReturnType methodReturnType;
    @XmlElement(required = true)
    protected AttrList attrList;
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
     * Gets the value of the methodVisibility property.
     * 
     * @return
     *     possible object is
     *     {@link MethodVisibility }
     *     
     */
    public MethodVisibility getMethodVisibility() {
        return methodVisibility;
    }

    /**
     * Sets the value of the methodVisibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodVisibility }
     *     
     */
    public void setMethodVisibility(MethodVisibility value) {
        this.methodVisibility = value;
    }

    /**
     * Gets the value of the methodName property.
     * 
     * @return
     *     possible object is
     *     {@link MethodName }
     *     
     */
    public MethodName getMethodName() {
        return methodName;
    }

    /**
     * Sets the value of the methodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodName }
     *     
     */
    public void setMethodName(MethodName value) {
        this.methodName = value;
    }

    /**
     * Gets the value of the methodReturnType property.
     * 
     * @return
     *     possible object is
     *     {@link MethodReturnType }
     *     
     */
    public MethodReturnType getMethodReturnType() {
        return methodReturnType;
    }

    /**
     * Sets the value of the methodReturnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodReturnType }
     *     
     */
    public void setMethodReturnType(MethodReturnType value) {
        this.methodReturnType = value;
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
