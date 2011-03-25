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

package cz.cvut.promod.plugin.notationSpecificPlugIn.notation.utils;

import cz.cvut.promod.services.ModelerSession;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.NotationWorkspaceData;

import javax.swing.*;

import java.awt.*;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 22:25:38, 18.10.2009
 */

/**
 * Default implementation of NotationWorkspaceData interface. This class is used in case of
 * emergency and during testing to insert an instance of an empty JPanel containing just textual
 * form of notation identifier. This class is used when there is not own implementation of
 * NotationWorkspaceData available in loaded plugin notation.
 */
public class NotationWorkspaceDataDefault implements NotationWorkspaceData {

    private final String notationIdentifier;

    private final DefaultUpdatableWorkspaceComponent defaultUpdatableWorkspaceComponent;

    public NotationWorkspaceDataDefault(final String notationIdentifier){
        this.notationIdentifier = notationIdentifier;

        defaultUpdatableWorkspaceComponent = new DefaultUpdatableWorkspaceComponent(notationIdentifier);
    }

    public JComponent getWorkspaceComponentSingleton() {
        return defaultUpdatableWorkspaceComponent; 
    }
}
