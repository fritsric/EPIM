package cz.cvut.promod.EPC2XHTMLExport.engine;



import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Created by IntelliJ IDEA.
 * User: Robin Hood
 * Date: 14.12.2010
 * Time: 18:02:52
 * To change this template use File | Settings | File Templates.
 */
public class TableTest {

    private String result = "beforeTablebeforeHeadingheadingafterHeadingbeforeHeaderRowbeforeHeaderColumnFullafterHeaderColumnbetweenHeaderColumnsbeforeHeaderColumnNot fullafterHeaderColumnafterHeaderRowbeforeRowbeforeColumn11afterColumnbetweenColumnsbeforeColumnafterColumnafterRowbeforeRowbeforeColumn21afterColumnbetweenColumnsbeforeColumn22afterColumnafterRowbeforeRowbeforeColumn31afterColumnbetweenColumnsbeforeColumnafterColumnafterRowbeforeRowbeforeColumn41afterColumnbetweenColumnsbeforeColumn24afterColumnafterRowbeforeRowbeforeColumn51afterColumnbetweenColumnsbeforeColumn25afterColumnafterRowafterTable";

    @Test
    public void testComplex(){
        Table table = new Table(3, new String[] {"Full", "Not full", "Empty"});

        table.addRow(new String[] {"11", "", ""});
        table.addRow(new String[] {"21", "22", ""});
        table.addRow(new String[] {"31", "", ""});
        table.addRow(new String[] {"41", "24", ""});
        table.addRow(new String[] {"51", "25", ""});

        assertEquals(table.optimizeCols(), 2);

        TableDelimiters del = new TableDelimiters();
        del.beforeTable = "beforeTable";
        del.afterTable = "afterTable";
        del.beforeColumn = "beforeColumn";
        del.betweenColumns = "betweenColumns";
        del.afterColumn = "afterColumn";
        del.beforeRow= "beforeRow";
        del.afterRow= "afterRow";
        del.beforeHeaderColumn= "beforeHeaderColumn";
        del.afterHeaderColumn= "afterHeaderColumn";
        del.betweenHeaderColumns = "betweenHeaderColumns";
        del.beforeHeaderRow= "beforeHeaderRow";
        del.afterHeaderRow= "afterHeaderRow";
        del.beforeHeading= "beforeHeading";
        del.afterHeading= "afterHeading";

        String s= table.toString("heading", del);

        assertEquals(s, result);

    }
}
