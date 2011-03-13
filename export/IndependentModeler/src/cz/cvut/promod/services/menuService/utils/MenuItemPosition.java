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

package cz.cvut.promod.services.menuService.utils;

import org.apache.log4j.Logger;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 1:46:34, 2.2.2010
 */

/**
 * Specifies the menu item hierarchical and relative or absolute position in menu.
 */
public final class MenuItemPosition{

    private static final Logger LOG = Logger.getLogger(MenuItemPosition.class);

    /**
     * Specifies relative menu item position
     */
    public static enum PlacementStyle {
        /** First item in menu. */
        FIRST,

        /** Last item in menu. */
        LAST,

        /** Group menu item having the same text. Last if there is no name duplicity. */
        GROUPED
    }

    private final PositionInfo positionInfo;
    private final String hierarchicalPlacement;

    /**
     * Constructs new MenuItemPosition holding hierarchical placement information.
     *
     * @param hierarchicalPlacement is the info about hierarchical position for new menu item
     */
    public MenuItemPosition(final String hierarchicalPlacement){
        positionInfo = new PositionInfo();
        this.hierarchicalPlacement = hierarchicalPlacement;
    }

    /**
     * Constructs new MenuItemPosition holding hierarchical placement information and relative position.
     *
     * @param hierarchicalPlacement is the info about hierarchical position for new menu item
     *
     * @param placementStyle defines relative menu item position in the menu
     */
    public MenuItemPosition(final String hierarchicalPlacement, final PlacementStyle placementStyle){
        this(hierarchicalPlacement);

        positionInfo.setRelativePosition(placementStyle);
    }

    /**
     * Constructs new MenuItemPosition holding hierarchical placement information and absolute position.
     *
     * @param hierarchicalPlacement is the info about hierarchical position for new menu item
     *
     * @param position defines absolute menu item position in the menu
     */
    public MenuItemPosition(final String hierarchicalPlacement, final int position){
        this(hierarchicalPlacement);

        if(position < 0){
            LOG.error("Wrong menu position for menu item. Position has been set to zero.");
            positionInfo.setPosition(0);
        } else {
            positionInfo.setPosition(position);
        }
    }

    /**
     * Returns required hierarchical placement information.
     *
     * @return hierarchical placement information
     */
    public String getHierarchicalPlacement() {
        return hierarchicalPlacement;
    }

    /**
     * Returns absolute position for menu item.
     *
     * @return absolute position
     */
    public int getPosition() {
        return positionInfo.getAbsolutePosition();
    }

    /**
     * Returns relative position for menu item.
     *
     * @return relative position
     */
    public PlacementStyle getPlacementStyle() {
        return positionInfo.getRelativePosition();
    }

    /**
     * Returns information about relative and/or absolute position.
     *
     * @return information about relative and/or absolute position
     */
    public PositionInfo getPositionInfo() {
        return positionInfo;
    }

    /**
     * Holds the absolute and/or relative position information.
     */
    public static final class PositionInfo{

        /**
         * If position > 0 -> then use position (absolute positioning), else use placementStyle (relative positioning)
         */
        private int position = -1;

        /**
         * Relative positioning.
         */
        private PlacementStyle placementStyle = PlacementStyle.LAST;

        /**
         * Returns absolute positioning information.
         *
         * @return absolute positioning information
         */
        public int getAbsolutePosition() {
            return position;
        }

        /**
         * Returns relative positioning information.
         *
         * @return relative positioning information
         */
        public PlacementStyle getRelativePosition() {
            return placementStyle;
        }

        /**
         * Sets absolute positioning information.
         *
         * @param position is the new absolute position
         */
        public void setPosition(final int position) {
            this.position = position;
        }

        /**
         * Sets relative positioning information.
         *
         * @param placementStyle is the new relative position
         */
        public void setRelativePosition(final PlacementStyle placementStyle) {
            this.placementStyle = placementStyle;
        }
    }
}
