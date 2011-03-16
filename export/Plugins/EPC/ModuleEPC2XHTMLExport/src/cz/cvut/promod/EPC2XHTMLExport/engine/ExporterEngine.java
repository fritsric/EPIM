/****************************************************************************
** This file may be used under the terms of the MIT licence:
**
** Permission is hereby granted, free of charge, to any person obtaining a copy
** of this software and associated documentation files (the "Software"), to deal
** in the Software without restriction, including without limitation the rights
** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
** copies of the Software, and to permit persons to whom the Software is
** furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included in
** all copies or substantial portions of the Software.
**
** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
** THE SOFTWARE.
****************************************************************************/

package cz.cvut.promod.EPC2XHTMLExport.engine;

import cz.cvut.promod.EPC2XHTMLExport.ui.ExporterSettingsModel;
import cz.cvut.promod.epc.workspace.EPCWorkspaceData;
import org.apache.log4j.Logger;
import org.jgraph.JGraph;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Oct 6, 2010
 * Time: 4:00:58 PM
 *
 * This class is the main engine of creating our plugin output (the export). The export is a complex process and here
 * are all its part coordinated and called in proper order. The only public method is export() which is called from
 * GUI Controller when user pushes the "export" button. It has several protected methods which are used to generate
 * content for the @see Renderer.
 */
public class ExporterEngine {

    protected static final Logger LOG = Logger.getLogger(ExporterEngine.class);

    protected final EPCWorkspaceData workspace;
    protected final ExporterSettingsModel settings;

    protected Renderer renderer;
    protected TableDelimiters delimiters;

    /**
     * Constructor crates renderers and prepare engin for exoport
     *
     * @param workspace
     * @param settings
     */
    public ExporterEngine(final EPCWorkspaceData workspace, final ExporterSettingsModel settings) {
        this.workspace = workspace;
        this.settings = settings;

        delimiters = new TableDelimiters();

        switch (settings.getFormat()){

            //Used for XHTML inline renderer
            /*
            case eXHTMLFile:
                try {
                    renderer = new XHTMLInlineRenderer(settings.getPath(), settings.getName(), settings.getTheme());
                }
                catch (ExporterSettingsModel.ExportThemeException e) {}
                break;
            */

            case eXHTMLFolder:
                try {
                    renderer = new XHTMLFolderRenderer(settings.getPath(), settings.getName(), settings.getTheme());
                }
                catch (ExporterSettingsModel.ExportThemeException e) {}

                break;
            case eDokuWiki:
                renderer = new DokuWikiRenderer(settings.getPath(), settings.getName());

                break;
            case eLaTeXFolder:
                renderer = new LaTeXRenderer(settings.getPath(), settings.getName());

                break;
        }
    }

    /**
     * Generates table of nodes form Diagram.
     *
     * @param diagram
     * @return
     */
    protected Table generateNodeTable(final Diagram diagram){
        Table nodeTable = new Table(4, new String[] {"Name", "Note", "Condition 1", "Condition 2"});

        if(settings.getNotes()){
            Iterator<Node> it = diagram.getNodes().iterator();
            Node tmp;

            while(it.hasNext()){
                tmp = it.next();

                String[] row;

                if(tmp instanceof NodeLogic){
                    row = new String[] {
                                tmp.getName(),
                                tmp.getNote(),
                                ((NodeLogic) tmp).getCondition1(),
                                ((NodeLogic) tmp).getCondition2()
                          };
                }else{
                    row = new String[] {
                                tmp.getName(),
                                tmp.getNote(),
                                null,
                                null
                          };
                }

                nodeTable.addRow(row);

            }
        }
        
        return nodeTable;
    }

    /**
     * Generates table of links form Diagram.
     *
     * @param diagram
     * @return
     */
    protected Table generateLinkTable(final Diagram diagram){
        boolean isNotes = settings.getNotes();
        Iterator<Node> itn = diagram.getNodes().iterator();
        Iterator<Link> itl;
        Node node;
        Link link;

        Table linkTable = new Table(4, new String[] {"Name", "From", "To", "Note"});

        while(itn.hasNext()){
            node = itn.next();
            itl = node.getOutLinks().iterator();

            while(itl.hasNext()){
                link = itl.next();

                linkTable.addRow(
                        new String[] {
                                link.getName(),
                                node.getName(),
                                link.getTo().getName(),
                                isNotes ? link.getNote() : ""
                        }
                );
            }
        }

        return linkTable;
    }

    /**
     * Exports plot to image.
     *
     * @param graph
     * @return
     */
    protected BufferedImage generateImage(final JGraph graph){
        return graph.getImage(Color.WHITE, 20);
    }

    /*
    //Method used to be used to generate SVG Plot.

    protected String generatePlot(final JGraph graph) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SVGGraphWriter writer = new SVGGraphWriter();
        SVGGraphWriter.TITLE_VSPACING = 0;
        writer.write(baos,"",graph.getGraphLayoutCache(),0);
        return new String(baos.toByteArray());        
    }
    */

    /**
     * Execute the export.
     *
     * @throws IOException when IO error occures
     */
    public void export() throws IOException{
        Diagram diagram = new Diagram(workspace.getGraph().getModel(), settings.getTitle());;

        Table nodeTable = generateNodeTable(diagram);
        Table linkTable = generateLinkTable(diagram);

        renderer.render(nodeTable, linkTable, diagram.getName(), generateImage(workspace.getGraph()));
    }

    /**
     * Check if some files already exists.
     *
     * @return tru when if found collision with aready existing file
     */
    public boolean isColliding(){
        return renderer.isColliding();
    }
}