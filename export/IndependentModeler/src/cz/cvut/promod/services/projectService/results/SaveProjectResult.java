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

package cz.cvut.promod.services.projectService.results;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 20:37:47, 8.2.2010
 */

/**
 * Possible result of project root saving file system operation.
 */
public enum SaveProjectResult {

    /**
     * No error has occurred. The project root has been successfully saved.
     */
    SUCCESS,

    /**
     * Used for signaling run-time failure of writing file system operations.
     */
    IOERROR,

    /**
     * Represents a xml error during creating of a structure of xml project file. This error should practically NEVER
     * happened - represents inner error of saving algorithm. 
     */
    XML_ERROR,

    /**
     * This error occurs when one tries to save project root that represents by an invalid tree path, e.g. not valid
     * tree path, or no project root specified by the tree path.
     */
    INVALID_TREE_PATH

}
