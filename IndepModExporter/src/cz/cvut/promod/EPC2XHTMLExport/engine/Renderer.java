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

import cz.prcek.help.rootPath.RootPathFactory;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocTigerDelegateImpl_150;

/**
 * Created by IntelliJ IDEA.
 * User: Vaclav Zuna
 * Date: Oct 6, 2010
 * Time: 4:04:39 PM
 *
 * This is a abstract predecessor of all renderes. It contains some logic common to all renderers and it specify methods
 * which every renderer must implement in order to they can be used in same context. The purpose of renderers is to be
 * able to write content files which are the export output and run simple template system for it so the changes of
 * the template do not affect the source code.
 *
 * The templates uses simple macro system. Macros are replaced by the generated content. Macros are surrounded by
 * meta-chars (concrete characters depends on concrete renderer) which defines them. If you wish to write a meta-char in
 * a normal way in text just double them and the renderer will ignore them.
 */
public abstract class Renderer {
    //path to folder for rendering
    protected String path;
    //name of file to write
    protected String name;

    /**
     * Constructs renderer.
     *
     * @param path path to the folder where shall be the files rendered
     * @param name name of main rendered file
     */
    public Renderer(final String path, final String name){
        this.path = path;
        this.name = name;
    }

    /**
     * Load file from Java resources as string.
     *
     * @param path path to the file to load
     * @return loaded file
     * @throws IOException
     */
    protected static String getResourceFileAsString(final String path) throws IOException{
        //InputStream in = path.getClass().getResourceAsStream(path);
        
        InputStream in = new java.io.FileInputStream(RootPathFactory.getTheRootPath() + "../../../../IndepModExporter/resources/" + path);//Předělat na resources!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuffer fileData = new StringBuffer(1000);

        char[] buf = new char[1024];

        int numRead=0;
        while((numRead = br.read(buf)) != -1){

            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);

        }

        br.close();

        return fileData.toString();
    }

    /**
     * Execute macro replacements using default meta characters - curly brackets.
     *
     * @param template template string containing macros
     * @param replacements
     * @return
     */
    protected static String executeReplacements(final String template, Map<String, String> replacements) {
        return executeReplacements(template, replacements, '{', '}');
    }

    /**
     * Execute macro replacements using given meta characters.
     *
     * @param template template string containing macros
     * @param replacements
     * @param metaCharacterOpen
     * @param metaCharacterClose
     * @return
     */
    protected static String executeReplacements(final String template, Map<String, String> replacements, final char metaCharacterOpen, final char metaCharacterClose) {

        StringBuffer output = new StringBuffer();
        char[] input = template.toCharArray();

        boolean replaceFlag = false;
        StringBuffer keyword = new StringBuffer();

        for(int i = 0; i < input.length; i++){
            if(replaceFlag){
                if(input[i] == metaCharacterOpen){
                    replaceFlag = false;
                    output.append(metaCharacterOpen);
                } else if(input[i] == metaCharacterClose){
                    replaceFlag = false;

                    String value = replacements.get(keyword.toString());
                    output.append(value == null ? "" : value);

                    keyword = new StringBuffer();
                }else{
                    keyword.append(input[i]);
                }
            }else{
                //copy
                if(input[i] == metaCharacterOpen){
                    replaceFlag = true;
                    continue;
                } else if(input[i] == metaCharacterClose){
                    i++;
                }

                output.append(input[i]);

            }
        }

        return output.toString();
    }

    /**
     * Render the content to the file(s).
     *
     * @param nodeTable
     * @param linkTable
     * @param title
     * @param image
     * @throws IOException
     */
    public abstract void render(final Table nodeTable, final Table linkTable, final String title, final BufferedImage image) throws IOException;

    /**
     * Check if some file(s) which shall be rendered collides with files of the same name.
     *
     * @return
     */
    public abstract boolean isColliding() /*throws IOException*/;
}
