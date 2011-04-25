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

import cz.cvut.indepmod.classmodel.api.model.ElementType;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import org.apache.log4j.Logger;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IRelation;

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
 * Edited: Dvorak Jan
 */
public class ExporterEngine {

    protected static final Logger LOG = Logger.getLogger(ExporterEngine.class);

    protected Renderer renderer;
    protected TableDelimiters delimiters;
    protected IClassModelModel workspace;
    protected String title;

    /**
     * Constructor crates renderers and prepare engin for exoport
     *
     * @param workspace
     * @param settings
     */
    public ExporterEngine(String path, String name, String theme, IClassModelModel model, RendererType type) {
        switch(type)
        {
            case DOKUWIKI:
                renderer = new DokuWikiRenderer(path, name);
            case LATEX:
                renderer = new LaTeXRenderer(path, name);
            case XHTML_FOLDER:
                renderer = new XHTMLFolderRenderer(path, name, theme);
            default:
                renderer = new XHTMLInlineRenderer(path, name, theme);
        }
        delimiters = new TableDelimiters();
        workspace = model;
        
        title = name;
    }

    /**
     * Generates table of nodes form Diagram.
     *
     * @return
     */
    protected Table generateNodeTable(final IClassModelModel diagram){
        Table nodeTable = new Table(4, new String[] {"Name", "Note", "Condition 1", "Condition 2"});
        
        for(IElement lrClass : workspace.getClasses())
        {
            String[] row;
            switch(lrClass.getElementType())
            {
                case INTERFACE:
                    row = new String[] {lrClass.getTypeName(), "Interface", null, null}; break;
                case ENUMERATION:
                    row = new String[] {lrClass.getTypeName(), "Enumeration", null, null}; break;
                default:
                    row = new String[] {lrClass.getTypeName(), (lrClass.isAbstract()?"Abstract class":" Class"), null, null}; break;
            }
            nodeTable.addRow(row);
        }
        
        return nodeTable;
    }

    /**
     * Generates table of links form Diagram.
     *
     * @return
     */
    protected Table generateLinkTable(final IClassModelModel diagram){
        Table linkTable = new Table(4, new String[] {"Name", "From", "To", "Note"});
        
        for(IElement lrClass : workspace.getClasses())
        {
            for(IRelation lrRelations : lrClass.getRelatedClass())
            {
                linkTable.addRow(new String[] {lrRelations.getRelationName(), lrRelations.getStartingClass().getTypeName(), lrRelations.getEndingClass().getTypeName(), ""});
            }
        }
       
        return linkTable;
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
        Table nodeTable = generateNodeTable(workspace);
        Table linkTable = generateLinkTable(workspace);
        renderer.render(nodeTable, linkTable, title, null);
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