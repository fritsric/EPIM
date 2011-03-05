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

package cz.cvut.promod.services.userService;

import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.PresentationModel;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 17:51:26, 10.10.2009
 */

/**
 * UserService implementation
 */
public class UserServiceImpl extends Model implements UserService{

    public static String USER_PROPERTY = "user";    
    private String user;

    private final PresentationModel<UserServiceImpl> presentationModel = new PresentationModel<UserServiceImpl>(this);
    private final ValueModel userValueModel = presentationModel.getModel(USER_PROPERTY);

    /** {@inheritDoc} */
    public String getUser() {
        return user;
    }

    /** {@inheritDoc} */
    public void setUser(final String user) {
        final String oldUser = this.user;
        this.user = user;
        firePropertyChange(USER_PROPERTY, oldUser, user);
    }

    /** {@inheritDoc} */
    public ValueModel getUserValueModel() {
        return userValueModel;
    }

    /** {@inheritDoc} */
    public boolean check() {            
        return true;
    }

}
