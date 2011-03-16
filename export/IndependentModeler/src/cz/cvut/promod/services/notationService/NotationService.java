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

package cz.cvut.promod.services.notationService;

import cz.cvut.promod.services.Service;
import cz.cvut.promod.services.pluginLoaderService.utils.NotationSpecificPlugins;
import cz.cvut.promod.services.pluginLoaderService.utils.PluginLoadErrors;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.Notation;
import cz.cvut.promod.plugin.notationSpecificPlugIn.notation.model.DiagramModel;

import java.util.List;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:47:34, 10.10.2009
 */

/**
 * Notation Service is a access point to all loaded notations and their related modules.
 */
public interface NotationService extends Service{

    /**
     * Returns a list of all notations.
     *
     * @return list of all notations
     */
    public List<Notation> getNotations();

    /**
     * Returns a list of all notation's identifiers.
     *
     * @return a list of all notation's identifiers
     */
    public List<String> getNotationsIdentifiers();

    /**
     * Returns a notation having the identifier specified.
     *
     * @param notationIdentifier is the plugin identifier of required notation
     *
     * @return a notation having the identifier specified or null if there is no such a notation
     */
    public Notation getNotation(final String notationIdentifier);

    /**
     * Returns an instance of NotationSpecificPlugins class containing the notation that is specified by the
     * identifier and all modules related to this notation.
     *
     * @param notationIdentifier is the identifier of the notation
     *
     * @return an object holding the notation and all it's modules
     */
    public NotationSpecificPlugins getNotationSpecificPlugins(final String notationIdentifier);

    /**
     * Creates an empty notation specific diagram model. This model holds diagram specific information (graphical and
     * other information).
     *
     * @param notationIdentifier is the notation identifier that is supposed to create the empty diagram model
     *
     * @return an empty diagram model of a required notation, null if there is not such a notation
     */
    public DiagramModel createEmptyNotationModel(final String notationIdentifier);

    /**
     * Checks whether there exists such a notation.
     *
     * @param notationIdentifier is the identifier of notation
     *
     * @return true if there is such a notation, false otherwise
     */
    public boolean existNotation(final String notationIdentifier);

    /**
     * Returns the file extension of diagrams of a specified notation.
     *
     * @param notationIdentifier is the identifier of notation
     *
     * @return the diagram's file extension of a specified notation, null there is no such a notation
     */
    public String getNotationFileExtension(final String notationIdentifier);

    /**
     * Returns identifier of a notation on the basis of this notation's file extension.
     *
     * @param notationFileExtension is the file extension
     *
     * @return notation identifier related to the file extension, null if there is no such a notation
     */
    public String getNotationIdentifier(final String notationFileExtension);

    /**
     * Returns the error information that have occurred during loading.
     *
     * @return list holding error information
     */
    public List<PluginLoadErrors> getErrors();

}
