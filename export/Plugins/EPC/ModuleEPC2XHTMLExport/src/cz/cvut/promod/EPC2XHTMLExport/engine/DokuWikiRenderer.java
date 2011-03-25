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
 * Time: 23:38:31
 *
 *
 * This is descendant of @see Renderer which provides output to text file of a DokuWiki
 * http://www.dokuwiki.org/dokuwiki notation. The PNG image included by the page is saved in same same location.
 * Note that you need to change path to the image in output file after uploading it to a webserver. This rendere uses
 * the "DokuWikiTemplate.txt" template stored in "resources/templates". The macro meta-chars are the curly brackets {}.
 */
public class DokuWikiRenderer extends Renderer{
    
    //file to render content
    protected File file;
    protected File plot;

    protected TableDelimiters delimiters;

    /**
     * Constructs DokuWiki renderer.
     *
     * @param path
     * @param name
     */
    public DokuWikiRenderer(final String path, final String name) {
        super(path, name);

        file = new File(path + name + ".txt");
        plot = new File(path + name + ".png");

        delimiters = new TableDelimiters();
        delimiters.beforeColumn = "| ";
        delimiters.afterColumn = " ";
        delimiters.afterRow= "|\n";
        delimiters.beforeHeaderColumn= "^ ";
        delimiters.afterHeaderColumn= " ";
        delimiters.afterHeaderRow= "^\n";
        delimiters.beforeHeading= "=== ";
        delimiters.afterHeading= " ===\n";
    }

    /**
     * Renders content to text file using DokuWiki syntax.
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
        String template = getResourceFileAsString("/templates/DokuWikiTemplate.txt");

        //Add system replacements
        Map<String, String> replacements = new Hashtable<String, String>();
        replacements.put("title", title);
        replacements.put("imageName", name + ".png");
        replacements.put("nodeTable", nodeTable.toString("Nodes", delimiters));
        replacements.put("linkTable", linkTable.toString("Links", delimiters));

        try{
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(executeReplacements(template, replacements).getBytes("UTF-8"));
            fos.flush();
            fos.close();

        }catch (IOException e){
            throw new IOException("todo change error report" , e); //TODO change error report
        }

        try{
            //write plot
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
    public boolean isColliding() {
        return file.exists() || plot.exists();
    }
}
