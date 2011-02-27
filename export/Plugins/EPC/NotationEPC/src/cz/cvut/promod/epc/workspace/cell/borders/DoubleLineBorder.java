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

package cz.cvut.promod.epc.workspace.cell.borders;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 19:13:11, 7.12.2009
 *
 * The special border for Material Input/Output epc element.
 */
public class DoubleLineBorder extends AbstractBorder {

    private Color lineColor;

    public DoubleLineBorder(final Color color) {
        lineColor = color;
    }

    @Override
    public void paintBorder(final Component c,
                            final Graphics g,
                            final int x,
                            final int y,
                            final int width,
                            final int height) {

          g.setColor(lineColor);
          g.drawRect(x, y, width-1, height-1);
          g.drawRect(x+2, y+2, width-5, height-5);
    }

    public Color getLineColor() {
        return lineColor;
    }

    @Override
    public Insets getBorderInsets(final Component component) {
        return new Insets(3,3,3,3);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
