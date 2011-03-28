package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.Common;
import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Date: 23.3.2011
 * Time: 9:07:34
 * @author Lucky
 */
public class DiagramModelPersistenceTest {
private File f = new File(Common.FILE_NAME);
    private XMLEncoder encoder;

    @Before
    public void setUp() {
        try {
            this.encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
            this.encoder.setPersistenceDelegate(DiagramDataModel.class, new ClassModelDiagramModelPersistenceDelegate());
            this.encoder.setPersistenceDelegate(TypeModel.class, new TypeModelPersistenceDelegate());
            this.encoder.setExceptionListener(new ExceptionListener() {

                @Override
                public void exceptionThrown(Exception e) {
                    fail("Error during encoding, probably there is missing some of Persistence Delegates: " + e.getMessage());
                }
            });
        } catch (FileNotFoundException ex) {
            fail();
        }
    }

    @After
    public void tearDown() {
        f.delete();
    }

    /**
     * This test tries to encode, decode and verify TypeModel class
     */
    @Test
    public void testEncodeDecode() {
        DiagramDataModel model = new DiagramDataModel();
        model.addStereotype(Common.VAL2);
        model.addStereotype(Common.ANOT2);

        model.addDynamicDataType(new TypeModel(Common.CLASS_NAME2));
        model.addDynamicDataType(new TypeModel(Common.METHOD_NAME2));

        this.encoder.writeObject(model);
        this.encoder.close();

        model = (DiagramDataModel) this.decode(this.f);

        assertEquals(DiagramType.CLASS, model.getDiagramType());
        assertNotNull(model.getLayoutCache());

        boolean isVal2 = false;
        boolean isAnot2 = false;
        for (String stereotype : model.getStereotypes()) {
            if (stereotype.equals(Common.VAL2)) {
                isVal2 = true;
            } else if (stereotype.equals(Common.ANOT2)) {
                isAnot2 = true;
            } else if (stereotype.equals("")) {
                //Empty stereotype is there automatically
            } else {
                fail("There is another stereotype!");
            }
        }
        assertTrue(isAnot2);
        assertTrue(isVal2);

        boolean isClassName2 = false;
        boolean isMethodName2 = false;
        for (IType type : model.getDynamicDataTypes()) {
            if (type.getTypeName().equals(Common.CLASS_NAME2)) {
                isClassName2 = true;
            } else if (type.getTypeName().equals(Common.METHOD_NAME2)) {
                isMethodName2 = true;
            } else {
                fail("There is another dynamic data type!");
            }
        }
        assertTrue(isClassName2);
        assertTrue(isMethodName2);
    }


    private Object decode(File f) {
        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(this.f)));
            if (dec != null) {
                Object obj = dec.readObject();
                dec.close();
                return obj;
            }
        } catch (FileNotFoundException ex) {
            fail(ex.getMessage());
        }
        return null;
    }
}
