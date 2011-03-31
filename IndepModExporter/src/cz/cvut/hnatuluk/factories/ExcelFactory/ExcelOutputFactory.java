package cz.cvut.hnatuluk.factories.ExcelFactory;

import cz.cvut.hnatuluk.Exception.BadExtensionException;
import cz.cvut.hnatuluk.Exception.FileNotEditableException;
import cz.cvut.hnatuluk.checkers.FileChecker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class ExcelOutputFactory {

    public static enum EXTENSIONS {

        XLS, XLSX, NONE;

        public static EXTENSIONS getExtension(String s) {
            if (s.toUpperCase().endsWith(XLS.toString())) {
                return XLS;
            } else if (s.toUpperCase().endsWith(XLSX.toString())) {
                return XLSX;
            } else {
                return NONE;
        
            }
        }
    }
    private FileChecker excellChecker;

    public void setExcellChecker(FileChecker excellChecker) {
        this.excellChecker = excellChecker;
    }
    
    public File createOutputFile(File f) throws BadExtensionException, FileNotEditableException, IOException {
        if (!excellChecker.accept(f)) {
            throw new BadExtensionException(String.format("The filename %s is not acceptable for Excell Files", f.getName()));
        }
        if (!f.exists() || f.delete()) {
            File ret = new File(f.getAbsolutePath());
            ret.createNewFile();
            return ret;
        } else {
            throw new FileNotEditableException(String.format("The file %s can not be edit", f.getName()));
        }
    }

    public File createOutputFile(String f) throws BadExtensionException, FileNotEditableException, IOException {
        return createOutputFile(new File(f));
    }

    public Workbook createOutputWorkBook(File f) throws BadExtensionException {
        String filename = f.getName();
        switch(EXTENSIONS.getExtension(filename)){
            case XLS :{
                return new HSSFWorkbook();
            }
            case XLSX:{
                return new XSSFWorkbook();
            }  
            case NONE:{}
            default:{                
                throw new BadExtensionException(String.format("The filename %s is not acceptable for Excell Files", f.getName()));
            }
        }
    }

    public Workbook createOutWorkbook(String f) throws BadExtensionException {
        return createOutputWorkBook(new File(f));
    }
    
    public void write(File f, Workbook w) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(f);
        w.write(fos);
        fos.close();
    }
    
    
}
