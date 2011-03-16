package cz.cvut.promod.EPC2XHTMLExport.engine;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Rusty Horse
 * Date: 25.11.2010
 * Time: 11:01:15
 *
 * This class is used to provide universal (syntax independent) table output. It can be filled with table data and then
 * used to obtain them in syntax specified in given @see TableDelimiters. It optimize the table to be more radable in
 * a way that it release empty columns.
 */

public class Table {

    /**
     * Anonymous class to represent colums.
     */
    class Column{
        LinkedList<String> values;
        boolean isEmpty;
        String header;

        Column(String header){
            values = new LinkedList<String>();
            isEmpty = true;
            this.header = header;
        }

        /**
         * Appends given string to new column cell.
         *
         * @param s
         */
        void addValue(String s){
            if(s == null || s.equals("") || s.equals(" ")) {
                values.addLast("");
            }else{
                isEmpty = false;
                values.addLast(s);
            }
        }
    }

    protected ArrayList<Column> colunms;
    protected boolean isOptimized = false;

    /**
     * Constructs Table with given count of Columns.
     *
     * @param columnCnt
     * @param arr columns titles
     */
    Table(int columnCnt, String[] arr){
        colunms = new ArrayList<Column>();

        for(int i = 0; i < columnCnt; i++){
            colunms.add(new Column(arr[i]));
        }
    }

    /**
     * Appends new row to the table.
     *
     * @param arr array of Strings with size of count of columns.
     */
    public void addRow(String[] arr){
        if(!isOptimized){
            for(int i = 0; i < colunms.size(); ++i){
                colunms.get(i).addValue(arr[i]);
            }
        }
    }

    /**
     * Deletes columns which were not used to store value. Call only when you do not intend to add another row.
     *
     * @return number of columns after optimization
     */
    public int optimizeCols(){
        if(!isOptimized){
            for(int j = 0; j < colunms.size();){
                if(colunms.get(j).isEmpty){
                    colunms.remove(j);
                }else{
                    j++;
                }
            }
        }

        isOptimized = true;

        return colunms.size();
    }

    /**
     * Exports table to string using given delimiters.
     *
     * @param heading heading of the table
     * @param del delimiters to use
     * @return text representation of table
     */
    public String toString(String heading, TableDelimiters del){
        //delete empty columns and if is none left, return
        if(optimizeCols() == 0){
            return "";
        }

        StringBuffer buf = new StringBuffer();

        buf.append(del.beforeTable);

        buf.append(del.beforeHeading);
        buf.append(heading);
        buf.append(del.afterHeading);

        //write headers
        buf.append(del.beforeHeaderRow);
        for(int j = 0; j < colunms.size() ;j++){
            buf.append(del.beforeHeaderColumn);
            buf.append(colunms.get(j).header);
            buf.append(del.afterHeaderColumn);
            if(j + 1 < colunms.size()){
                buf.append(del.betweenHeaderColumns);
            }
        }
        buf.append(del.afterHeaderRow);

        //write content
        for(int i = 0; i < colunms.get(0).values.size(); i++){
            buf.append(del.beforeRow);
            for(int j = 0; j < colunms.size(); j++){
                buf.append(del.beforeColumn);
                buf.append(colunms.get(j).values.get(i));
                buf.append(del.afterColumn);
                if(j + 1 < colunms.size()){
                    buf.append(del.betweenColumns);
                }
            }
            buf.append(del.afterRow);
        }

        buf.append(del.afterTable);

        return buf.toString();
    }
}