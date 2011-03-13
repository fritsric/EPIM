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

package cz.cvut.promod.epc.workspace.cell;

import org.jgraph.graph.PortView;
import org.jgraph.graph.PortRenderer;
import org.jgraph.graph.CellViewRenderer;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

import cz.cvut.promod.epc.resources.Resources;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 19:50:18, 7.12.2009
 *
 * Special implementation of the PortView for the EPCNotation plugin.
 */
public class EPCPortView extends PortView {

    private static PortRenderer RENDERER = null;

    private static ImageIcon portIcon = Resources.getIcon(Resources.PORTS + Resources.PORT_BLUE);


    public EPCPortView(final Object cell){
        super(cell);

        RENDERER = new EPCPortRenderer(portIcon);
    }

    @Override
    public Rectangle2D getBounds() {
		if (portIcon != null) {
            final Point2D point = getLocation();
            final Rectangle2D bounds = new Rectangle2D.Double();
            int width = portIcon.getIconWidth();
            int height = portIcon.getIconHeight();
            double x = 0;
            double y = 0;

            if(point != null){
                final Point2D pointClone = (Point2D) point.clone();
                x = pointClone.getX() - width / 2;
                y = pointClone.getY() - height / 2;
            }

            bounds.setFrame(x, y, width, height);

            return bounds;
		}
        
		return super.getBounds();
	}

    @Override
    public CellViewRenderer getRenderer() {
        return RENDERER;
    }
}
