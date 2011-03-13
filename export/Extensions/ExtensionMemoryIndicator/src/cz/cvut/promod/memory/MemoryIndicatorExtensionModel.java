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

package cz.cvut.promod.memory;

import com.jidesoft.swing.JideBoxLayout;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * ProMod, master thesis project
 * User: Petr Zverina, petr.zverina@gmail.com
 * Date: 10:50:46, 28.1.2010
 *
 * Implementation of a model component for MemoryIndicatorExtension extension.
 */
public class MemoryIndicatorExtensionModel {

    private static final Logger LOG = Logger.getLogger(MemoryIndicatorExtensionModel.class);

    public static final String MEM_INDICATOR_VISIBILITY_KEY = "memoryIndicator.memoryIndicator.visible";
    public static final String MEM_INDICATOR_SIZE_KEY = "memoryIndicator.memoryIndicator.size";
    public static final String RESIZER_VISIBILITY_KEY = "memoryIndicator.resizer.visible";

    public static final String IDENTIFIER_KEY = "memoryIndicator.memoryIndicator.identifier";
    public static final String NAME_KEY = "memoryIndicator.memoryIndicator.name";
    public static final String DESCRIPTION_KEY = "memoryIndicator.memoryIndicator.description";

    public static final String VARY_POSITION = "vary";
    public static final String FLEXIBLE_POSITION = "flexible";

    private static final String MEM_INDICATOR_VISIBLE_DEFAULT = "true";
    private static final String RESIZER_VISIBLE_DEFAULT = "true";
    private static final String SIZE_VISIBLE_DEFAULT = JideBoxLayout.FLEXIBLE;

    public static final String NAME_DEFAULT = "Memory status bar indicator";
    public static final String DESCRIPTION_DEFAULT = "Basic modeler status bar extension.";

    private final boolean memoryIndicatorVisible;
    private final boolean resizerVisible;
    private final String position;

    private final String identifier;
    private final String description;
    private final String name;


    public MemoryIndicatorExtensionModel(final Properties properties) throws InstantiationException{
        try{
            if(!properties.containsKey(MEM_INDICATOR_VISIBILITY_KEY)){
                LOG.info("Missing property " + MEM_INDICATOR_VISIBILITY_KEY + ". Using default value.");
            }
            memoryIndicatorVisible = Boolean.parseBoolean(properties.getProperty(MEM_INDICATOR_VISIBILITY_KEY, MEM_INDICATOR_VISIBLE_DEFAULT).trim());

            if(!properties.containsKey(RESIZER_VISIBILITY_KEY)){
                LOG.info("Missing property " + RESIZER_VISIBILITY_KEY + ". Using default value.");
            }
            resizerVisible = Boolean.parseBoolean(properties.getProperty(RESIZER_VISIBILITY_KEY, RESIZER_VISIBLE_DEFAULT).trim());

            final String size = properties.getProperty(MEM_INDICATOR_SIZE_KEY).trim();
            if(size.equalsIgnoreCase(VARY_POSITION)){
                position = JideBoxLayout.VARY;
            } else if(size.equalsIgnoreCase(FLEXIBLE_POSITION)){
                position = JideBoxLayout.FLEXIBLE;
            } else {
                LOG.info("Missing property " + MEM_INDICATOR_SIZE_KEY + ". Using default value.");
                position = SIZE_VISIBLE_DEFAULT;
            }

            if(!properties.containsKey(NAME_KEY)){
                LOG.info("Missing property " + NAME_KEY + ". Using default value.");
            }
            name = properties.getProperty(NAME_KEY, NAME_DEFAULT).trim();

            if(!properties.containsKey(DESCRIPTION_KEY)){
                LOG.info("Missing property " + DESCRIPTION_KEY + ". Using default value.");
            }
            description = properties.getProperty(DESCRIPTION_KEY, DESCRIPTION_DEFAULT).trim();

            if(!properties.containsKey(IDENTIFIER_KEY)){
                LOG.info("Missing property " + IDENTIFIER_KEY + ". Stopping instantiation.");
                throw new InstantiationException("Missing property " + IDENTIFIER_KEY + ". Stopping instantiation.");
            }
            identifier = properties.getProperty(IDENTIFIER_KEY);            

        } catch (Exception exception){
            LOG.error("An error has occurred.", exception);
            throw new InstantiationException(exception.getMessage());
        }
    }

    public String getPosition() {
        return position;
    }

    public boolean isResizerVisible() {
        return resizerVisible;
    }

    public boolean isMemoryIndicatorVisible() {
        return memoryIndicatorVisible;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
