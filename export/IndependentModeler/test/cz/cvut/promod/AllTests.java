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

package cz.cvut.promod;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import cz.cvut.promod.services.actionService.TestActionServiceImpl;
import cz.cvut.promod.services.toolBarService.TestToolBarControlServiceImpl;
import cz.cvut.promod.services.statusBarService.TestStatusBarControlServiceImpl;
import cz.cvut.promod.services.pluginLoaderService.TestPluginLoaderServiceImpl;
import cz.cvut.promod.services.projectService.*;
import cz.cvut.promod.services.notationService.TestNotationServiceImpl;
import cz.cvut.promod.services.extensionService.TestExtensionServiceImpl;
import cz.cvut.promod.services.menuService.TestMenuServiceImpl;
import cz.cvut.promod.services.userService.TestUserService;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 23:03:47, 18.4.2010
 *
 * Runs all JUnit tests.
 *
 * never run test from an medium, where the test is not allowed to write data (e.g. CD).
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestActionServiceImpl.class,
        TestToolBarControlServiceImpl.class,
        TestStatusBarControlServiceImpl.class,
        TestPluginLoaderServiceImpl.class,
        TestProjectControlServiceImplAddDiagram.class,
        TestProjectControlServiceImplAddSubfolder.class,
        TestProjectControlServiceImplAddProject.class,
        TestProjectService.class,
        TestIOOperations.class,
        TestSyncFromPN.class,
        TestSyncFromFS.class,
        TestNotationServiceImpl.class,
        TestExtensionServiceImpl.class,
        TestChangeDispatcherMechanism.class,
        TestMenuServiceImpl.class,
        TestUserService.class
})
public class AllTests {}
