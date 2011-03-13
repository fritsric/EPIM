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

package cz.cvut.promod.memory;

import com.jidesoft.status.EmptyStatusBarItem;
import com.jidesoft.status.MemoryStatusBarItem;
import com.jidesoft.status.ResizeStatusBarItem;
import com.jidesoft.swing.JideBoxLayout;
import cz.cvut.promod.gui.ModelerModel;
import cz.cvut.promod.gui.settings.SettingPageData;
import cz.cvut.promod.plugin.extension.Extension;
import cz.cvut.promod.services.ModelerSession;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 1:20:52, 26.1.2010
 *
 * Extension that appends memory indicator and resizer to the modeler's status bar.
 */
public class MemoryIndicatorExtension implements Extension {

    private static final Logger LOG = Logger.getLogger(MemoryIndicatorExtension.class);

    private final MemoryIndicatorExtensionModel model;

    public MemoryIndicatorExtension(final File propertiesFile) throws InstantiationException{
        final Properties properties = new Properties();
        try {
            properties.load(new FileReader(propertiesFile));
        } catch (IOException e) {
            LOG.error("Properties for the MemoryIndicator Extension couldn't be read.", e);
            throw new InstantiationException("Mandatory properties couldn't be read.");
        }

        model = new MemoryIndicatorExtensionModel(properties);
    }

    /** {@inheritDoc} */
    public String getIdentifier() {
        return model.getIdentifier();
    }

    /** {@inheritDoc} */
    public String getName() {
        return model.getName();
    }

    /** {@inheritDoc} */
    public String getDescription() {
        return model.getDescription();
    }

    public void init() {
        ModelerSession.getStatusBarService().addStatusBarItem(
                ModelerModel.MODELER_IDENTIFIER, new EmptyStatusBarItem(), JideBoxLayout.VARY
        );

        if(model.isMemoryIndicatorVisible()){
            ModelerSession.getStatusBarService().addStatusBarItem(
                    ModelerModel.MODELER_IDENTIFIER, new MemoryStatusBarItem(), model.getPosition()
            );
        }

        if(model.isResizerVisible()){
            ModelerSession.getStatusBarService().addStatusBarItem(
                    ModelerModel.MODELER_IDENTIFIER, new ResizeStatusBarItem(), JideBoxLayout.FIX
            );
        }
    }

    public void finish() {

    }

    public List<SettingPageData> getSettingPages() {
        return null;
    }
}
