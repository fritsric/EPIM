package cz.cvut.indepmod.classmodel.api.model;

import java.util.Collection;
import java.util.Set;

/**
 * Element represents Classes, Interfaces or Enumerations
 * @author Lucky
 */
public interface IElement extends IType {

    /**
     * Returns the type of the element. By this you can determine if the element
     * is a class, an interface or an enumeration
     * @return
     */
    public ElementType getElementType();

    /**
     * Returns the Visibility of this class
     * @return the Visibility of this class
     */
    public Visibility getVisibility();

    /**
     * Returns the stereotype. If there is no stereotype, return null
     * @return the stereotype or null if there is no stereotype
     */
    public String getStereotype();

    /**
     * Returns true if this element is abstract
     * @return
     */
    public boolean isAbstract();

    /**
     * Sets if this element is abstract or not
     * @param abstr
     */
    public void setAbstract(boolean abstr);

    /**
     * Returns an attribute set
     *
     * @return an attribute set
     */
    public Set<IAttribute> getAttributeModels();

    /**
     * Returns a method set
     *
     * @return a method set
     */
    public Set<IMethod> getMethodModels();

    /**
     * Returns set of related methods
     * @return related method set
     */
    public Collection<IRelation> getRelatedClass();

    /**
     * Returns a collection of anotations
     * @return a collection of anotations
     */
    public Set<IAnotation> getAnotations();

    /**
     * Adds an anotation
     * @param anot the anotation to be added
     */
    public void addAnotation(IAnotation anot);

    /**
     * Removes an anotation
     * @param anot The anotation to be removed
     */
    public void removeAnotation(IAnotation anot);

    /**
     * Adds new attribute to this class model. If this attribute is already here
     * it will not be apended
     * @param attr new attribute to add
     */
    public void addAttribute(IAttribute attr);

    /**
     * Removes the attribute from this class model (sure if the attribute is
     * here).
     * @param attr
     */
    public void removeAttribute(IAttribute attr);

    /**
     * Removes the method from this class model if there is this method.
     * @param method a method to be removed from this model
     */
    public void removeMethod(IMethod method);

    /**
     * Adds the method to this class if it is not already here
     * @param method a method to be added to this model
     */
    public void addMethod(IMethod method);

    /**
     * Sets the stereotype of this class
     * @param stereotype
     */
    public void setStereotype(String stereotype);
}
