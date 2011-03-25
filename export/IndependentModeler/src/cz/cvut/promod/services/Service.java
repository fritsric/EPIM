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

package cz.cvut.promod.services;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 16:20:15, 10.10.2009
 */

/**
 * Common interface for all services.
 *
 * IMPORTANT NOTE! All services and their methods are supposed NOT to be used in (or by) a different thread
 * that Event Dispatcher Thread or Main Thread (during application start - init() method of any plugin).
 * Even all resources provided by services (generally ModelerSession), e.g. all instances of a ValueModel
 * interface etc., are supposed to be used only in Event Dispatcher Thread or Main Thread (during application start).
 *
 * One can use following code fragment if is not convinced about the thread identity:
 *
 * if(SwingUtilities.isEventDispatchThread()){
 *      usage of a service or a it's resource
 * }
 *
 * One can always delegate any program sequence to the Event Dispatcher thread too. Use:
 * 1) SwingUtilities.invokeAndWait(Runnable doRun) or
 * 2) SwingUtilities.invokeLater(Runnable doRun) - generally preferable solution.
 *
 * Usage of services and their resources form another thread than Event Dispatcher Thread can cause a
 * unpredictable inconsistency of application. One is supposed to always avoid using services and their
 * resources out of the Event Dispatcher Thread!
 *
 * @see cz.cvut.promod.services.ModelerSession
 * @see javax.swing.SwingUtilities 
 */
public interface Service {

    /**
     * Is the common method for all services and is invoked by the IndependentModelerMain class
     * @see cz.cvut.promod.IndependentModelerMain
     *
     * This method is invoked during ProMod start to check availability and integrity of services.

     * @return true if no error has occurred during check, false otherwise
     */
    public boolean check();

}
