/**
 *
 *
 *
 *          WARNING - THIS CLASS IS BURIED AND SHALL NOT BE USED
 *          See the class doc for the explanation.
 *
 *
 *
 */


package cz.cvut.promod.EPC2XHTMLExport.engine;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rusty Horse
 * Date: 20.10.2010
 * Time: 11:49:41
 *
 * This is descendant of @see Renderer which provides output to a single XHTML file with no additional files. All
 * content including SVG image of graph is inlined. It uses the "inlineTemplate.XHTML" template
 * stored in "resources/templates". The macro meta-chars are the curly brackets {}.
 *
 * Although this class is fully operational it support by @see ExporterEngine was ended. This happened because of
 * impossibility to get some reasonable SVG output form EPC diagram which is required by this class. If you find
 * the way to get it, feel free to use this renderer. Note that the SVG processing is quite slow.
 */
public class XHTMLInlineRenderer  extends Renderer {

    //name of theme file
    protected String theme;
    //file to render content
    protected File file;

    protected TableDelimiters delimiters;

    /**
     * Constructs XHTML inline renderer.
     *
     * @param path
     * @param name
     */
    public XHTMLInlineRenderer(final String path, final String name, final String theme) {
        super(path, name);

        this.theme = theme;

        file = new File(path + name + ".html");

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
     * Renders content to single XHTML file.
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
        String template = getResourceFileAsString("/templates/inlineTemplate.xhtml");
        //Add system replacements
        Map<String, String> replacements = new Hashtable<String, String>();
        replacements.put("_layout", getResourceFileAsString("/templates/layout.css"));
        replacements.put("_theme", getResourceFileAsString("/themes/" + theme + ".css"));
       /* replacements.put("_plot", new SAX().parse(replacements.get("plot"))); *///FIXME give somehow SVG plot
        replacements.put("title", title);
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
    }

    /**
     * Check if file which shall be rendered collides with existing file of the same name.
     *
     * @return
     */
    @Override
    public boolean isColliding(){
        return file.exists();
    }


    class SAX {

        public String parse(String input) {

            MySVGContentHandler handler = new MySVGContentHandler();

            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes("UTF-8"));
                XMLReader parser = XMLReaderFactory.createXMLReader();
                InputSource source = new InputSource(bais);
                parser.setContentHandler(handler);
                parser.parse(source);

            } catch (Exception e) {
                //TODO add error report
            }

            return handler.getSvg();

        }


        class MySVGContentHandler implements ContentHandler {

            protected Locator locator;

            protected StringBuffer output;


            boolean firstTag = true;

            MySVGContentHandler() {
                this.output = new StringBuffer();
            }

            public String getSvg(){
                return output.toString();
            }

            public void setDocumentLocator(Locator locator) {
                this.locator = locator;
            }

            public void startDocument() throws SAXException {

                // ...

            }

            public void endDocument() throws SAXException {

                // ...

            }


            public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

                if(firstTag){
                    firstTag = false;
                    output.append("<svg:" + localName + " xmlns:svg=\"http://www.w3.org/2000/svg\" ");
                    for(int i = 0; i < atts.getLength(); i++){
                        output.append(atts.getLocalName(i) + "=\"" + atts.getValue(i) + "\" ");
                    }
                    output.append(">");

                }else{
                    output.append("<svg:" + localName + " ");
                    for(int i = 0; i < atts.getLength(); i++){
                        output.append(atts.getLocalName(i) + "=\"" + atts.getValue(i) + "\" ");
                    }
                    output.append(">");
                }
            }

            public void endElement(String uri, String localName, String qName) throws SAXException {
                output.append("</svg:" + localName + ">");
            }

            public void characters(char[] ch, int start, int length) throws SAXException {
                output.append(new String(ch, start, length));
            }

            public void startPrefixMapping(String prefix, String uri) throws SAXException {

                // ...

            }

            public void endPrefixMapping(String prefix) throws SAXException {

                // ...

            }

            public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

                // ...

            }

            public void processingInstruction(String target, String data) throws SAXException {

              // ...

            }

            public void skippedEntity(String name) throws SAXException {

              // ...

            }
        }
    }
}