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

package cz.cvut.promod.epcImageExport.frames.imageExport;

import com.jgoodies.binding.beans.Model;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:48:51, 13.12.2009
 *
 * The model component of the Image Export dockable frame.
 */
public class ImageExportModel extends Model {

    public static String EXPORT_AS_LABEL_RES = "epc.image.export.as.label";
    public static String EXPORT_BUTTON_RES = "epc.image.export.button";
    public static String PNG_OPTIONS_INSET_RES = "epc.image.export.png.inset";

    public static String FRAME_TITLE_RES = "epc.image.export.frame.label";

    public static String PNG_EXTENSION = ".png";
    public static String PNG_FORMAT_NAME = "png";

    public static final int MIN_INSET = 0;
    public static final int MAX_INSET = 100;
    public static final int EXTENT = 0;
    public static final int INIT_INSET = 5;
    public static final int INIT_SPINNER_STEP = 1;
    public static final String PROPERTY_INSET = "inset";
    private int inset = 20;

    public int getInset() {
        return inset;
    }

    public void setInset(final int inset) {
        final int oldValue = this.inset;
        this.inset = inset;
        firePropertyChange(PROPERTY_INSET, oldValue, inset);
    }

}
