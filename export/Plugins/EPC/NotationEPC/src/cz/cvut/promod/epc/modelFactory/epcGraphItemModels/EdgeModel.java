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

package cz.cvut.promod.epc.modelFactory.epcGraphItemModels;

import java.util.UUID;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:34:37, 19.1.2010
 *
 * Represents the general edge element of the EPCNotation plugin.
 */
public class EdgeModel extends EPCEditableVertex {

    public static enum EdgeType {
        CONTROL_FLOW,
        ORGANIZATION_FLOW,
        INFORMATION_FLOW,
        INFORMATION_SERVICES_FLOW,
        MATERIAL_FLOW
    }

    private final EdgeType edgeType;

    public EdgeModel(final EdgeType edgeType){
        this.edgeType = edgeType;
    }

    public EdgeModel(final EdgeModel edgeModel, final String newName){
        this.edgeType = edgeModel.getEdgeType();
        setName(newName);
        setNote(edgeModel.getNote());
    }

    public EdgeType getEdgeType() {
        return edgeType;
    }

    @Override
    public String toString() {
        return name;
    }

    public UUID getUuid() {
        return null; // edges do not have uuid
    }
}
