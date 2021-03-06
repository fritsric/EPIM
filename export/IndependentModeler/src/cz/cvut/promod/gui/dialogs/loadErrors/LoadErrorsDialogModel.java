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

package cz.cvut.promod.gui.dialogs.loadErrors;

import cz.cvut.promod.services.pluginLoaderService.utils.PluginLoadErrors;
import cz.cvut.promod.services.ModelerSession;

import java.util.List;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:55:46, 12.12.2009
 *
 * The model component for LoadErrorsDialog.
 */
public class LoadErrorsDialogModel {

    private final List<PluginLoadErrors> errors;

    public static final String PROPERTY_NAME =
            ModelerSession.getCommonResourceBundle().getString("modeler.loadErrorsDialog.propertyName");

    public static final String FULL_CLASS_NAME =
            ModelerSession.getCommonResourceBundle().getString("modeler.loadErrorsDialog.fullClassName");

    public static final String ERROR =
            ModelerSession.getCommonResourceBundle().getString("modeler.loadErrorsDialog.error");

    public static final String MESSAGE =
        ModelerSession.getCommonResourceBundle().getString("modeler.loadErrorsDialog.error.message");


    public LoadErrorsDialogModel(final List<PluginLoadErrors> errors) {
        this.errors = errors;
    }

    public List<PluginLoadErrors> getErrors() {
        return errors;
    }
}
