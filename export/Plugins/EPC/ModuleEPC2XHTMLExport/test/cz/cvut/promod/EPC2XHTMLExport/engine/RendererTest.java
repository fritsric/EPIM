package cz.cvut.promod.EPC2XHTMLExport.engine;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Map;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Robin Hood
 * Date: 14.12.2010
 * Time: 18:40:29
 * To change this template use File | Settings | File Templates.
 */
public class RendererTest {
    String template = "LLorem ipsum dolor sit {()()()} amet consectetuer consequat laoreet iaculis eget enim. Diam malesuada interdum pretium id justo Donec vel Vivamus et wisi. \n" +
        "{1}\n" +
        "Enim convallis pretium vel parturient Aenean  consectetuer {2}}} dapibus lacus tortor facilisis. Elit et eget vel tristique volutpat{{}} et condimentum id lacus Nullam. Ac massa pellentesque quis ac urna Ut suscipit id magna elit. \n" +
        "\n" +
        "Tincidunt {{ urna elit sem non augue aliquam egestas nibh volutpat lacinia. Felis malesuada.()()\n";

    String repRes = "LLorem ipsum dolor sit závorky amet consectetuer consequat laoreet iaculis eget enim. Diam malesuada interdum pretium id justo Donec vel Vivamus et wisi. \n" +
            "jedna\n" +
            "Enim convallis pretium vel parturient Aenean  consectetuer dva} dapibus lacus tortor facilisis. Elit et eget vel tristique volutpat{} et condimentum id lacus Nullam. Ac massa pellentesque quis ac urna Ut suscipit id magna elit. \n" +
            "\n" +
            "Tincidunt { urna elit sem non augue aliquam egestas nibh volutpat lacinia. Felis malesuada.()()\n";

    @Test
    public void testExecuteReplacements(){
        Map<String, String> rep = new Hashtable<String, String>();
        rep.put("1", "jedna");
        rep.put("2", "dva");
        rep.put("__", "podtržítko");
        rep.put("()()()", "závorky");

        String s = Renderer.executeReplacements(template, rep);

        assertEquals(s, repRes);
    }
}
