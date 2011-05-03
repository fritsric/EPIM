package cz.cvut.promod.EPC2XHTMLExport.engine;

import cz.cvut.promod.EPC2XHTMLExport.resources.Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rusty Horse
 * Date: 20.10.2010
 * Time: 11:55:10
 *
 * This is descendant of @see Renderer which provides output to XHTML file and additional files like CSS and PNG image
 * saves into a folder of a same name like the XHTML file in same location. It uses the "folderTemplate.XHTML" template
 * stored in "resources/templates". The macro meta-chars are the curly brackets {}.
 */
public class XHTMLFolderRenderer extends Renderer{

    //name of theme file
    protected String theme;
    //files to render content
    protected File html;
    protected File layout;
    protected File style;
    protected File plot;
    protected File dir;

    protected TableDelimiters delimiters;

    /**
     * Constructs XHTML folder renderer.
     *
     * @param path
     * @param name
     */
    public XHTMLFolderRenderer(final String path, final String name, final String theme) {
        super(path, name);

        this.theme = theme;

        //Set paths to files
        String dirPath = path + name + System.getProperty("file.separator");

        html = new File(path + name + ".html");
        layout = new File(dirPath + "layout.css");
        style = new File(dirPath + "style.css");
        //plot = new File(dirPath + "plot.png");
        dir = new File(dirPath);

        delimiters = new TableDelimiters();
        delimiters.beforeTable = "<table>";
        delimiters.afterTable = "</tbody></table>";
        delimiters.beforeColumn = "<td>";
        delimiters.afterColumn = "</td>";
        delimiters.beforeRow = "<tr>";
        delimiters.afterRow = "</tr>";
        delimiters.beforeHeaderColumn = "<th>";
        delimiters.afterHeaderColumn = "</th>";
        delimiters.beforeHeaderRow = "<thead><tr>";
        delimiters.afterHeaderRow = "</tr></thead><tbody>";
        delimiters.beforeHeading = "<caption>";
        delimiters.afterHeading = "</caption>";
    }

    /**
     * Renders content to XHTML, CSS and PNG image file.
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
        String template = getResourceFileAsString("/templates/folderTemplate.xhtml");

        //Add system replacements
        Map<String, String> replacements = new Hashtable<String, String>();
        replacements.put("folder", name);
        replacements.put("title", title);
        replacements.put("nodeTable", nodeTable.toString("Nodes", delimiters));
        replacements.put("linkTable", linkTable.toString("Links", delimiters));

        FileOutputStream fos;

        dir.mkdirs();

        try{
            //write HTML
            html.createNewFile();

            fos = new FileOutputStream(html);
            fos.write(executeReplacements(template, replacements).getBytes("UTF-8"));
            fos.flush();
            fos.close();

        }catch (IOException e){
            throw new IOException("todo change error report" , e); //TODO change error report
        }

        try{
            //write CSS
            fos = new FileOutputStream(layout);
            fos.write(getResourceFileAsString("/templates/layout.css").getBytes("UTF-8"));
            fos.flush();
            fos.close();

        }catch (IOException e){
            throw new IOException("todo change error report" , e); //TODO change error report
        }

        try{
            fos = new FileOutputStream(style);
            fos.write(getResourceFileAsString("/themes/" + theme + ".css").getBytes("UTF-8"));
            fos.flush();
            fos.close();

        }catch (IOException e){
            throw new IOException("todo change error report" , e); //TODO change error report
        }


        try{
            //write plot
            if(image!=null)
                ImageIO.write(image, "png", plot);

        }catch (IOException e){
            throw new IOException("todo change error report" , e); //TODO change error report
        }
    }

    /**
     * Check if some file(s) which shall be rendered collides with existing file(s) of the same name.
     *
     * @return
     */
    @Override
    public boolean isColliding(){
        return html.exists() || ( dir.exists() && (dir.isDirectory() && dir.list().length > 0));
    }
}
