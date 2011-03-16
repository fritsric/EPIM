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

package cz.cvut.promod.plugin.notationSpecificPlugIn.notation.workspace;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 14:36:37, 7.11.2009
 */

/**
 * This interface is intended to be implemented by the workspace components. Every-time the actual model
 * is being changes, the host application (modeler) invokes these methods.
 *
 * It is up to notation plugin developers whether they use this interface when implementing the workspace
 * component or not. It is not required.
 *
 * This order of method invocation is used when switching working models (diagrams):
 * 1. finish()
 * 2. over()
 */
public interface UpdatableWorkspaceComponent {

    /**
     * This method is invoked on the workspace component when the actual model is being changed and the workspace
     * component is going to be active.
     */
    public void update();

    /**
     * This method is invoked on the workspace component when the actual model is being changed. This method is invoked
     * event if the the workspace component is  going to be inactivated.
     */
    public void over();
    
}
