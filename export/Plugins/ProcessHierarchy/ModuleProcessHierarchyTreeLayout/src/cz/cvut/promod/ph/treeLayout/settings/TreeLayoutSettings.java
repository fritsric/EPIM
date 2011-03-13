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

package cz.cvut.promod.ph.treeLayout.settings;

import cz.cvut.promod.gui.settings.SettingPageData;
import cz.cvut.promod.ph.treeLayout.settings.pages.GeneralPage;

import java.util.LinkedList;
import java.util.List;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:47:22, 28.1.2010
 *
 * TreeLayoutSettings represents a setting page fpr the common Settings Dialog.
 */
public class TreeLayoutSettings {

    private final List<SettingPageData> settingPages;

    private static TreeLayoutSettings instance;

    private final TreeLayoutSettingsModel model;

    public TreeLayoutSettings(){
        settingPages = new LinkedList<SettingPageData>();

        model = new TreeLayoutSettingsModel();

        initPages();
    }

    private void initPages() {
        GeneralPage genPage = new GeneralPage();
        SettingPageData setPageData = new SettingPageData("Process Hierarchy Tree Layout", null, genPage);
        settingPages.add(setPageData);
    }

    public List<SettingPageData> getSettingPages() {
        return settingPages;
    }

    public TreeLayoutSettingsModel.VerticalLayout getVerticalLayout(){
        return model.getVerticalLayout();
    }

    public void setVerticalLayout(final TreeLayoutSettingsModel.VerticalLayout verticalLayout){
        model.setVerticalLayout(verticalLayout);
    }

    public static TreeLayoutSettings getInstance() {
        return instance;
    }

    public static void setInstance(final TreeLayoutSettings instance) {
        TreeLayoutSettings.instance = instance;
    }

}