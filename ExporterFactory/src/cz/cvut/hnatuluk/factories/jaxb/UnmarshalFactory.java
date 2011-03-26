/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.hnatuluk.factories.jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author prcek
 */
public class UnmarshalFactory<T> {
    private String name;
    
    private UnmarshalFactory(String c) {
        this.name = c;
    }
    
    public static <S extends Class> UnmarshalFactory createInstance(S t){
        return new UnmarshalFactory<S>(t.getPackage().getName());
    }
    
    public T unmarshal(File f) throws JAXBException{
        JAXBContext jc = JAXBContext.newInstance(name);
        Unmarshaller u = jc.createUnmarshaller();
        T t = (T) u.unmarshal(f);
        return t;
    }
}
