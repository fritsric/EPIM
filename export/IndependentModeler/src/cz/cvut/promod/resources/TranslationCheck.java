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

package cz.cvut.promod.resources;

import org.apache.log4j.Logger;
import cz.cvut.promod.services.ModelerSession;

import java.util.MissingResourceException;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 16:38:10, 26.4.2010
 */

/**
 * Checks whether there is no missing translation. 
 */
public class TranslationCheck {

    private static Logger LOG = Logger.getLogger(TranslationCheck.class);

    public static final String[] translation_keys = {
            "modeler.ok",
            "modeler.yes",
            "modeler.no",
            "modeler.cancel",
            "modeler.apply",
            "modeler.plugin.notation",
            "modeler.plugin.module",
            "modeler.plugin.extension",
            "modeler.exit.save.all",
            "modeler.exit.skip.all",
            "modeler.exit",
            "modeler.exit.diagram.unsaved.change",
            "modeler.settings",
            "modeler.plugins.overview",
            "modeler.plugins.overview.notations.title",
            "modeler.plugins.overview.extension.title",
            "modeler.plugins.overview.identifier",
            "modeler.plugins.overview.name",
            "modeler.plugins.overview.description",
            "modeler.plugins.overview.abbreviation",
            "modeler.plugins.overview.full.name",
            "modeler.plugins.overview.tool.tip",
            "modeler.plugins.overview.file.ext",
            "modeler.dockable.frame.move",
            "modeler.dockable.frame.move.left",
            "modeler.dockable.frame.move.right",
            "modeler.dockable.frame.move.top",
            "modeler.dockable.frame.move.bottom",
            "modeler.configuration.title",
            "modeler.configuration.message",
            "modeler.configuration.message.detail",
            "modeler.configuration.hideButton",
            "modeler.configuration.missing.notation",
            "modeler.configuration.loaded.value",
            "modeler.configuration.actual.value",
            "modeler.configuration.missing.module",
            "modeler.configuration.missing.extension",
            "modeler.configuration.related.notation",
            "modeler.configuration.different.extension",
            "modeler.configuration.different.abbreviation",
            "modeler.configuration.different.name",
            "modeler.loadErrorsDialog.message",
            "modeler.loadErrorsDialog.log",
            "modeler.loadErrorsDialog.title",
            "modeler.loadErrorsDialog.hideButton",
            "modeler.loadErrorsDialog.propertyName",
            "modeler.loadErrorsDialog.fullClassName",
            "modeler.loadErrorsDialog.error",
            "modeler.loadErrorsDialog.error.message",
            "modeler.menu.file",
            "modeler.menu.edit",
            "modeler.action.exit",
            "modeler.action.new.project",
            "modeler.action.navigation",
            "modeler.action.project.load",
            "modeler.action.project.load.description",
            "modeler.action.project.load.error.loadfile.title",
            "modeler.action.project.load.error.loadfile",
            "modeler.action.project.load.error.sync.title",
            "modeler.action.project.load.error.sync.message",
            "modeler.action.project.save",
            "modeler.menu.project",
            "modeler.menu.window",
            "modeler.project.navigation.expand.all",
            "modeler.project.navigation.rename",
            "modeler.project.rename.description",
            "modeler.project.rename.duplicity",
            "modeler.project.rename.error",
            "modeler.project.rename.error.title",
            "modeler.project.navigation.collapse.all",
            "modeler.project.navigation.new.diagram",
            "modeler.project.navigation.new.subfolder",
            "modeler.project.navigation.add",
            "modeler.project.navigation.close.project",
            "modeler.project.navigation.refresh",
            "modeler.project.navigation.delete",
            "modeler.project.navigation.delete.diagram.title",
            "modeler.project.navigation.delete.diagram.message",
            "modeler.project.navigation.delete.diagram.error.message",
            "modeler.project.navigation.delete.subfolder.title",
            "modeler.project.navigation.delete.subfolder.message",
            "modeler.project.navigation.delete.subfolder.error.message",
            "modeler.project.navigation.delete.project.title",
            "modeler.project.navigation.delete.project.message",
            "modeler.project.navigation.add.subfolder",
            "modeler.project.navigation.add.diagram",
            "modeler.project.navigation.expand",
            "modeler.add.new.subfolder.dialog.title",
            "modeler.add.new.subfolder.dialog.label",
            "modeler.add.new.subfolder.dialog.confirm",
            "modeler.add.new.subfolder.dialog.error.disallowed",
            "modeler.add.new.subfolder.dialog.error.nameduplicity",
            "modeler.add.new.subfolder.dialog.error",
            "modeler.add.new.project.dialog.error.general",
            "modeler.add.new.project.dialog.error.relative",
            "modeler.add.new.project.dialog.error.existing.file",
            "modeler.add.new.project.dialog.error.duplicity",
            "modeler.add.new.project.dialog.error.shortName",
            "modeler.add.new.project.dialog.error.io",
            "modeler.add.new.project.dialog.error.disallowed",
            "modeler.add.new.diagram.dialog.title",
            "modeler.add.new.diagram.dialog.confirm",
            "modeler.add.new.diagram.dialog.tableTitle",
            "modeler.add.new.diagram.dialog.nameTitle",
            "modeler.add.new.diagram.dialog.noImage",
            "modeler.add.new.diagram.dialog.error.duplicity",
            "modeler.add.new.diagram.dialog.error.illegalParent",
            "modeler.add.new.diagram.dialog.error.illegalNotation",
            "modeler.add.new.diagram.dialog.error.disallowed",
            "modeler.add.new.diagram.dialog.error",
            "modeler.add.dialog.error.noSelectedProject",
            "modeler.add.dialog.error.noSelProjectTitle",
            "modeler.default.workspace.title",
            "modeler.default.workspace.notation.identifier",
            "modeler.default.workspace.diagram.name",
            "modeler.default.workspace.diagram.identifier",
            "project.service.sync.dialog.fromFS.title",
            "project.service.sync.dialog.fromPN.title",
            "project.service.sync.dialog.errorlist",
            "project.service.sync.dialog.searching",
            "project.service.sync.dialog.cancel",
            "project.service.sync.dialog.canceled",
            "project.service.sync.dialog.searching.done",
            "project.service.sync.dialog.treepatherror",
            "project.service.sync.dialog.default.error.message",
            "project.service.sync.dialog.invalidname.error.message",
            "project.service.sync.dialog.default.error.from",
            "project.service.sync.dialog.default.error.to",
            "project.service.sync.dialog.exception",
            "project.service.sync.dialog.directory.error",
            "project.service.sync.dialog.offset.error",
            "project.service.sync.dialog.invalid.treepath",
            "project.service.sync.dialog.invalid.parent",
            "project.service.sync.dialog.missing.project.file",
            "project.service.sync.dialog.diagram.overwritten",
            "project.service.sync.dialog.diagram.write",
            "project.service.sync.dialog.mkdir.error",
            "project.service.sync.dialog.delete.error",
            "project.service.sync.dialog.project.file.error",
            "project.service.sync.dialog.error.load.project.file",
            "project.service.sync.dialog.error.invalid.project.root",
            "project.service.sync.dialog.error.subtree",
            "project.service.sync.dialog.error.subtree.not.exist",
            "project.service.sync.dialog.project.root.error",
            "project.service.sync.dialog.subtree.build.error",
            "project.service.sync.dialog.diagram.load.error",
            "project.service.sync.dialog.diagram.error.identifier",
            "project.service.sync.dialog.diagram.error.extension",
            "project.service.sync.dialog.diagram.error.diagram",
            "modeler.user.service",
            "modeler.user.name",
            "modeler.user.title",
            "modeler.user.switch",
            "modeler.user.invalid.name",
            "pluginLoaderService.error.missing.xsd",
            "pluginLoaderService.error.noError",
            "pluginLoaderService.error.xmlParsing",
            "pluginLoaderService.error.className",
            "pluginLoaderService.error.findClass",
            "pluginLoaderService.error.classHierarchy",
            "pluginLoaderService.error.instantiation",
            "pluginLoaderService.error.nullaryNotationName",
            "pluginLoaderService.error.emptyNotationName",
            "pluginLoaderService.error.noModelFactory",
            "pluginLoaderService.error.invalidModelFactory",
            "pluginLoaderService.error.nullaryAbbreviation",
            "pluginLoaderService.error.emptyAbbreviation",
            "pluginLoaderService.error.ioController",
            "pluginLoaderService.error.nullaryFileExtension",
            "pluginLoaderService.error.emptyFileExtension",
            "pluginLoaderService.error.notationIdentifierDuplicity",
            "pluginLoaderService.error.nullaryPluginIdentifier",
            "pluginLoaderService.error.likeModelerpluginIdentifier",
            "pluginLoaderService.error.emptyPluginIdentifier",
            "pluginLoaderService.error.noRelatedNotationIdentifier",
            "pluginLoaderService.error.nullaryRelatedNotationIdentifier",
            "pluginLoaderService.error.emptyRelatedNotationIdentifier",
            "pluginLoaderService.error.moduleIdentifierDuplicity",
            "pluginLoaderService.error.extensionIdentifierDuplicity",
            "pluginLoaderService.error.nullaryExtensionDescription",
            "pluginLoaderService.error.nullaryPluginName",
            "pluginLoaderService.error.emptyPluginName",
            "pluginLoaderService.error.projectFileLikeNotation",
            "pluginLoaderService.error.notationExtensionDuplicity",
            "pluginLoaderService.error.too.long.identifier",
            "modeler.action.new.project.dialog",
    };

    /**
     * Checks whether no translation is missing.
     *
     * @return true if no translation is missing, false otherwise
     */
    public static boolean validateTranslations(){
        for(final String key : translation_keys){
            try{
                ModelerSession.getCommonResourceBundle().getString(key);
            } catch (MissingResourceException exception){
                LOG.error("Missing translation for key: " + key + ".");
                return false;                
            }
        }

        return true;
    }
}
