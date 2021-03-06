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
 * Date: 19:06:36, 7.12.2009
 *
 * The special border for Information Object epc element.
 */
public class InformationObjectBorder extends AbstractBorder {

    private final int FIRST_VERTICAL_LINE_OFFSET = 7;
    private final int SECOND_VERTICAL_LINE_OFFSET = 10;

    private final Insets INSETS = new Insets(1,9,1,9);

    private Color lineColor;

    public InformationObjectBorder(final Color color) {
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
        g.drawRect(x + FIRST_VERTICAL_LINE_OFFSET, y, width - (2 * FIRST_VERTICAL_LINE_OFFSET), height-1);
        g.drawRect(x + SECOND_VERTICAL_LINE_OFFSET, y, width - (2 * SECOND_VERTICAL_LINE_OFFSET), height-1);
    }

    @Override
    public Insets getBorderInsets(final Component component) {
        return INSETS;
    }

    public Color getLineColor() {
        return lineColor;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

}
