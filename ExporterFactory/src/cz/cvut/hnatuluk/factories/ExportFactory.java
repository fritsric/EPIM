package cz.cvut.hnatuluk.factories;

import cz.cvut.hnatuluk.factories.ExcelFactory.ExcelOutputFactory;
import cz.cvut.hnatuluk.factories.WordFactory.WordOutputFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Lukáš Hnaťuk ČVUT FEL 2010
 */
public class ExportFactory {

    private ExportFactory() {
    }
    private static ConfigurableApplicationContext ctx;

    private static void init() {
        if (ctx == null) {
            ctx = new ClassPathXmlApplicationContext("cz/cvut/hnatuluk/factories/ExportFactory.xml");
            ctx.registerShutdownHook();
        }
    }

    public static ExcelOutputFactory getExcelFactory()  throws NoSuchBeanDefinitionException,BeansException{
        init();
        return (ExcelOutputFactory)ctx.getBean("ExcelFactory");
    }
    
    public static WordOutputFactory getWordFactory() throws NoSuchBeanDefinitionException,BeansException{
        init();
        return (WordOutputFactory)ctx.getBean("WordFactory");
    }
}
