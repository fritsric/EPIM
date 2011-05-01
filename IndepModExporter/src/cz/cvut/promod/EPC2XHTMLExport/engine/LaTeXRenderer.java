package cz.cvut.promod.EPC2XHTMLExport.engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rusty Horse
 * Date: 30.11.2010
 * Time: 23:40:29
 *
 * This is descendant of @see Renderer which provides output to LaTeX file. The PNG image included by the page is saved
 * in same same location. This rendere uses the "LaTeXTemplate.tex" template stored in "resources/templates". 
 * The macro meta-chars are the parentheses ().
 */
public class LaTeXRenderer extends Renderer{
    
    //file to render content
    protected File file;
    protected File plot;

    protected TableDelimiters delimiters;

    /**
     * Constructs LaTeX renderer.
     *
     * @param path
     * @param name
     */
    public LaTeXRenderer(final String path, final String name) {
        super(path, name);

        file = new File(path + name + ".tex");
        plot = new File(path + name + ".png");

        delimiters = new TableDelimiters();
        delimiters.afterTable = "\\end{tabular}\n\\end{center}\n";
        delimiters.betweenColumns = " & ";
        delimiters.afterRow= " \\\\ \\hline\n";
        delimiters.afterHeaderRow= " \\\\ \\hline\n";
        delimiters.betweenHeaderColumns= " & ";
        delimiters.afterHeading= "} \\\\ \\hline\n";
    }

    /**
     * Renders content to LaTeX file and to PNG image file.
     * 
     * @param nodeTable
     * @param linkTable
     * @param title
     * @param image
     * @throws IOException
     */
    @Override
    public void render(final Table nodeTable, final Table linkTable, final String title, final BufferedImage image) throws IOException {
        //Get template
        String template = getResourceFileAsString("/templates/LaTeXTemplate.tex");

        //Add replacements
        Map<String, String> replacements = new Hashtable<String, String>();
        replacements.put("imageName", name + ".png");
        replacements.put("title", title);

        //Prepare specific delimiters for node table
        StringBuffer buf = new StringBuffer();
        buf.append("\\begin{center}\n\\begin{tabular}{ |");
        for(int i = 0; i < nodeTable.optimizeCols(); i++){
            buf.append(" l | ");
        }
        buf.append(" }\\hline\n");
        delimiters.beforeTable = buf.toString();
        delimiters.beforeHeading= "\\multicolumn{" + nodeTable.optimizeCols() + "}{|c|}{";

        replacements.put("nodeTable", nodeTable.toString("Nodes", delimiters));

        //Prepare specific delimiters for link table
        buf = new StringBuffer();
        buf.append("\\begin{center}\n\\begin{tabular}{ |");
        for(int i = 0; i < linkTable.optimizeCols(); i++){
            buf.append(" l | ");
        }
        buf.append(" }\\hline\n");
        delimiters.beforeTable = buf.toString();
        delimiters.beforeHeading= "\\multicolumn{" + linkTable.optimizeCols() + "}{|c|}{";

        replacements.put("linkTable", linkTable.toString("Links", delimiters));

        try{
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(executeReplacements(template, replacements, '(', ')').getBytes("UTF-8"));
            fos.flush();
            fos.close();

        }catch (IOException e){
            throw new IOException("todo change error report" , e); //TODO change error report
        }

        try{
            //write plot
            if(image!= null)
            {
                ImageIO.write(image, "png", plot);
            }

        }catch (IOException e){
            throw new IOException("todo change error report" , e); //TODO change error report
        }
    }

    /**
     * Check if some file(s) which shall be rendered collides existing with file(s) of the same name.
     *
     * @return
     */
    @Override
    public boolean isColliding() {
        return file.exists() || plot.exists();
    }
}
