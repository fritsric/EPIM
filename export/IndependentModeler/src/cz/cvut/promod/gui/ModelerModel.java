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

package cz.cvut.promod.gui;

import cz.cvut.promod.gui.support.utils.NotationGuiHolder;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.jidesoft.dialog.AbstractDialogPage;
import com.jidesoft.dialog.PageList;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 16:09:51, 10.10.2009
 */

/**
 * Data holder for the Modeler class.
 */
public class ModelerModel {

    public static final String APPLICATION_NAME = "ProMod";

    public static final String MODELER_IDENTIFIER = "modeler";

    private String selectedNotation = null; //must be null due to initial view update invoked by Modeler class constructor

    private Map<String, NotationGuiHolder> notationGuiHolders = new HashMap<String, NotationGuiHolder>();

    private final PageList settingPagesModel = new PageList();

    /**
     * @return NotationGuiHolder collection
     */
    public Collection<NotationGuiHolder> getNotationGuiHolders(){
      return notationGuiHolders.values();
    }

    /**
     * @param notationIdentifier is the notation identifier
     * @return required NotationGuiHolder
     */
    public NotationGuiHolder getNotationGuiHolder( final String notationIdentifier ){
      return notationGuiHolders.get(notationIdentifier);
    }

    /**
     * Adds a new NotationGuiHolder.
     * @param notationGuiHolder is the new NotationGuiHolder
     */
    public void addNotationGuiHolder( final NotationGuiHolder notationGuiHolder){
      notationGuiHolders.put(notationGuiHolder.getNotationIdentifier(), notationGuiHolder);
    }

    /**
     * @return selected notation identifier
     */
    public String getSelectedNotation() {
        return selectedNotation;
    }

    /**
     * Sets selected notation identifier
     * @param selectedNotation newly selected identifier
     */
    public void setSelectedNotation(final String selectedNotation) {
        this.selectedNotation = selectedNotation;
    }

    /**
     * Adds settings pages to the common settings dialog.
     * @param pages are the pages to be added
     */
    public void addSettingPages(final List<AbstractDialogPage> pages) {
        for(final AbstractDialogPage page : pages){
            settingPagesModel.append(page);
        }
    }

    /**
     * @return returns the model holding all settings pages  
     */
    public PageList getSettingPagesModel() {
        return settingPagesModel;
    }
}
