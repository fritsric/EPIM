/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

/**
 *
 * @author Jan Bažant
 */
public class StyledText
{
    private Style frStyle = new Style();
    private String fsText = "";
    
    public StyledText(String isText, Style irStyle)
    {
        fsText = isText;
        frStyle = irStyle;
    }
    
    public String getText() { return fsText; }
    public Style getStyle() { return frStyle; }
}
