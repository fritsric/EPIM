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

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by IntelliJ IDEA.
 * User: Ondrej Machulda
 * Date: 7.11.2010
 * Time: 17:05:28
 */
public class LaTeXRendererTest {

    private String path = "../Plugins/EPC/ModuleEPC2XHTMLExport/test/cz/cvut/promod/EPC2XHTMLExport/files/";

    @Test
    public void testFileIsColliding() {
        LaTeXRenderer renderer = new LaTeXRenderer(this.path,
                                             "LaTeXCollide");
        assertEquals(true, renderer.isColliding());
    }

    @Test
    public void testPlotFileIsColliding() {
        LaTeXRenderer renderer = new LaTeXRenderer(this.path,
                                             "LaTeXPlotCollide");
        assertEquals(true, renderer.isColliding());
    }

    @Test
    public void testIsNotColliding() {
        LaTeXRenderer renderer = new LaTeXRenderer(this.path,
                                             "testNotColliding");
        assertEquals(false, renderer.isColliding());
    }


}
