/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author Jan Bažant
 */
public class StyledText
{
    private Style frStyle = new Style();
    private CellStyle frCellStyle = null;
    private String fsText = "";
    
    public StyledText(String isText, Style irStyle)
    {
        fsText = isText;
        frStyle = irStyle;
    }
    
    public StyledText(String isText, CellStyle irStyle)
    {
        fsText = isText;
        frCellStyle = irStyle;
    }
    
    public String getText() { return fsText; }
    public Style getStyle() { return frStyle; }
    public CellStyle getCellStyle() { return frCellStyle; }
}
