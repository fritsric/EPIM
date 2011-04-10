/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exporter;

import cz.cvut.hnatuluk.Exception.BadTemplateException;
import cz.cvut.hnatuluk.Exception.DocxWriterException;
import cz.cvut.hnatuluk.Exception.TemplateParseErrorExcpetion;
import cz.cvut.hnatuluk.factories.ExportFactory;
import cz.cvut.hnatuluk.factories.WordFactory.WordDocument;
import cz.cvut.hnatuluk.factories.WordFactory.WordOutputFactory;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import java.io.File;
import java.util.Collection;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.wml.Body;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.PStyle;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jan Bažant
 * konvence názvů proměnných:
 * 1. písmeno = scope: i - inupt, o - input+output, l - local, f - field
 * 2. písmeno = typ: s - string, r - objekt / reference
 */
public class DocxExporter {
    private String fsTemplatePath = "";
    private String fsOutputPath = "";
    private IClassModelModel frData = null;
    private WordDocument frWordDocument = null;
    private Body frWordDocumentBody = null;
    private Hdr frHeader = null;
    private Ftr frFooter = null;
    private ObjectFactory frOF = null;
    
    public DocxExporter(String isTemplatePath, String isOutputPath, IClassModelModel irData)
    {
        fsTemplatePath = isTemplatePath;
        fsOutputPath = isOutputPath;
        frData = irData;
    }

    public void Export() throws BadTemplateException, TemplateParseErrorExcpetion, DocxWriterException
    {
        Document lrTemplateDoc;
        WordOutputFactory lrWOF;
        File lrOutputFile;

        try
        {
            lrTemplateDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fsTemplatePath));
        }
        catch(Exception e)
        {
            throw new TemplateParseErrorExcpetion("Error when parsing template: " + e.toString());
        }

        lrWOF = ExportFactory.getWordFactory();

        try
        {
            lrOutputFile = lrWOF.createOutputFile(fsOutputPath);
            frWordDocument = lrWOF.createOutputWorkbook(lrOutputFile);
            frWordDocumentBody = frWordDocument.getDocumentBody();
            frHeader = frWordDocument.getHeader();
            frFooter = frWordDocument.getFooter();
            frOF = frWordDocument.getCreateFactory();
        }
        catch(Exception e)
        {
            throw new DocxWriterException("Error when creating output: " + e.toString());
        }

        NodeList lrNodes = lrTemplateDoc.getElementsByTagName("docxTemplate");

        if(lrNodes.getLength() == 0)
            throw new BadTemplateException("Tag 'docxTemplate' not found!");
        
        ParseDocxTemplate(lrNodes.item(0).getChildNodes());
        
        try
        {
            lrWOF.write(lrOutputFile, frWordDocument);
        }
        catch(Docx4JException e)
        {
            throw new DocxWriterException("Error when writing output!");
        }
    }

    /**
     * Zpracuje tag docxTemplate.
     * @param irData
     * @param orWordDoc
     * @param irNodes 
     */
    private void ParseDocxTemplate(NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("header")) {
                ParseHeader(lrCurrentNode.getChildNodes());
            } else if(lsNodeName.equals("footer")) {
                ParseFooter(lrCurrentNode.getChildNodes());
            } else if(lsNodeName.equals("staticText")) {
                OutputSingleStaticText(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("model")) {
                ParseModel(lrCurrentNode.getChildNodes());
            }
        }
    }

    /**
     * Zpracuje tag model.
     * @param irData
     * @param orWordDoc
     * @param irNodes 
     */
    private void ParseModel(NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("modelImage")) {
                // TODO: až bude možné nějak dostat obrázek, nacpat ho tam
            } else if(lsNodeName.equals("staticText")) {
                OutputSingleStaticText(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("classList")) {
                ParseClassList(lrCurrentNode.getChildNodes());
            }
        }
    }

    /**
     * Zpracuje tag classList.
     * @param irData
     * @param orWordDoc
     * @param irNodes 
     */
    private void ParseClassList(NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleStaticText(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("class")) {
                ParseClasses(lrCurrentNode.getChildNodes());
            }
        }
    }
    
    private void ParseClasses(NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        
        if(frData == null)
            return;

        for(IClass lrClass : frData.getClasses())
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleStaticText(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("heading")) {
                    ParseHeading(lrClass, lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("anotationList")) {
                    ParseAnotationList((Set<IAnotation>) lrClass.getAnotations(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("attrList")) {
                    ParseAttrList((Set<IAttribute>) lrClass.getAttributeModels(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("classVisibility")) {
                    //lrClass.getVisibility();
                } else if (lsNodeName.equals("methodList")) {
                    ParseMethodList((Set<IMethod>) lrClass.getMethodModels(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("relationList")) {
                    
                } else if (lsNodeName.equals("classname")) {
                    //lrClass.getTypeName();
                }
            }
        }
    }

    /**
     * Zpracuje tag anotationList.
     * @param irAnotationList
     * @param irNodes 
     */
    private void ParseAnotationList(Set<IAnotation> irAnotationList, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {

            } else if(lsNodeName.equals("anotation")) {
                ParseAnotation(irAnotationList, lrCurrentNode.getChildNodes());
            }
        }
    }

    /**
     * Zpracuje tag anotation.
     * @param irAnotationList
     * @param irNodes 
     */
    private void ParseAnotation(Set<IAnotation> irAnotationList, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(IAnotation lrAnotation : irAnotationList)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleStaticText(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("anotationName")) {
                    //lrAnotation.getName();
                } else if (lsNodeName.equals("anotationAttrs")) {
                    ParseAnotationAttributes(lrAnotation.getAttributes(), lrCurrentNode.getChildNodes());
                }
            }
        }
    }

    /**
     * Zpracuje tag anotationAttrs.
     * @param irAnotationAttrs
     * @param irNodes 
     */
    private void ParseAnotationAttributes(Collection<IAnotationValue> irAnotationAttrs, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(IAnotationValue lrAnotationValue : irAnotationAttrs)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleStaticText(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("anotationAttrName")) {
                    //lrAnotationValue.getName();
                } else if (lsNodeName.equals("anotationAttrValueList")) {
                    ParseAnotationAttrValueList(lrAnotationValue.getValues(), lrCurrentNode.getChildNodes());
                }
            }
        }
    }

    /**
     * Zpracuje tag anotationAttrValueList.
     * @param irAnotationAttrValues
     * @param irNodes 
     */
    private void ParseAnotationAttrValueList(Collection<String> irAnotationAttrValues, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(String lsAnotationAttrValue : irAnotationAttrValues)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleStaticText(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("anotationAttrValue")) {
                    // lsAnotationAttrValue
                }
            }
        }
    }
    
    /**
     * Zpracuje tag attrList.
     * @param irAttributes
     * @param irNodes 
     */
    private void ParseAttrList(Set<IAttribute> irAttributes, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleStaticText(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("attribute")) {
                ParseAttributes(irAttributes, lrCurrentNode.getChildNodes());
            }
        }
    }
    
    /**
     * Zpracuje tag attribute.
     * @param irAttributes
     * @param irNodes 
     */
    private void ParseAttributes(Set<IAttribute> irAttributes, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(IAttribute lrAttribute : irAttributes)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleStaticText(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("attrName")) {
                    //lrAttribute.getName();
                } else if (lsNodeName.equals("attrVisibility")) {
                    //lrAttribute.getVisibility();
                } else if (lsNodeName.equals("attrDataType")) {
                    //lrAttribute.getType();
                } else if (lsNodeName.equals("anotationList")) {
                    //ParseAnotationList(lrAttribute.getAnotations(), lrCurrentNode.getChildNodes());
                }
            }
        }
    }
    
    private void ParseMethodList(Set<IMethod> irMethodList, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleStaticText(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("method")) {
                ParseMethods(irMethodList, lrCurrentNode.getChildNodes());
            }
        }
    }

    private void ParseMethods(Set<IMethod> irMethodList, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(IMethod lrMethod : irMethodList)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleStaticText(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("methodVisibility")) {
                    //lrMethod.getVisibility();
                } else if (lsNodeName.equals("methodName")) {
                    //lrMethod.getName();
                } else if (lsNodeName.equals("methodReturnType")) {
                    //lrMethod.getType();
                } else if (lsNodeName.equals("attrList")) {
                    ParseAttrList((Set<IAttribute>) lrMethod.getAttributeModels(), lrCurrentNode.getChildNodes());
                }
            }
        }
    }
    
    private void ParseHeader(NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        String lsWholeText = "";

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lsWholeText += lrCurrentNode.getTextContent(); 
            }
        }

        OutputHeader(lsWholeText);
    }

    private void ParseFooter(NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        String lsWholeText = "";

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lsWholeText += lrCurrentNode.getTextContent(); 
            }
        }
        
        OutputFooter(lsWholeText);
    }

    private void ParseHeading(IClass irClass, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                //OutputSingleStaticText(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("classname")) {
                //irClass.getTypeName();
            }
        }
    }

    private void OutputSingleStaticText(String isText)
    {
        P lrPara = CreateParagraphWithText(isText);
        // a celý to dám do dokumentu
        frWordDocumentBody.getEGBlockLevelElts().add(lrPara);
    }
    
    private P CreateParagraphWithText(String isText)
    {
        // Vytvořit prvky...
        P lrPara = frOF.createP();
        PPr lrParaProps = frOF.createPPr();
        R lrRun = frOF.createR();

        // defaultní styl
        PStyle lrParaStyle = frOF.createPPrBasePStyle();
        lrParaStyle.setVal("my style hard style");

        // samotný text
        Text lrText = frOF.createText();
        lrText.setValue(isText);
        lrRun.getRunContent().add(lrText);

        // odstavci nastavím styl...
        lrParaProps.setPStyle(lrParaStyle);
        lrPara.getParagraphContent().add(lrParaProps);
        // ... a přidám text
        lrPara.getParagraphContent().add(lrRun);
        
        return lrPara;
    }
    
    private void OutputHeader(String isText)
    {
        frHeader.getEGBlockLevelElts().add(CreateParagraphWithText(isText));
    }
    
    private void OutputFooter(String isText)
    {
        frFooter.getEGBlockLevelElts().add(CreateParagraphWithText(isText));
    }
}
