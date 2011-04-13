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
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.wml.Body;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.P;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * TODO: doplnit xsd:
 *
 * Přidat tag <oneRow> jako potomka tagů:
 * - anotation
 * - anotationAttrs
 * - anotationAttrValueList
 * - attribute
 * - method
 * - class
 * - //TODO
 * 
 * a možní potomci:
 * - anotationName
 * 
 * - anotationAttrName
 * 
 * - anotationAttrValue
 * 
 * - attrName
 * - attrVisibility
 * - attrDatatype
 * 
 * - methodVisibility
 * - methodName
 * - methodReturnType
 * 
 * - classVisibility
 * - classname
 * 
 * - staticText
 * 
 * Zrušit tag heading, nahradí ho oneRow.
 * 
 * TODO2: další změny:
 * - staticText může mít style
 * - style nedává smysl u tagů:
 *   - model
 *   - classList
 *   - class
 *   - methodList
 *   - method
 *   - relationList
 *   - relation
 *   - anotationList
 *   - anotation
 *   - anotationAttrs
 *   - anotationAttrValueList
 *   - attrList
 *   - attribute
 * Zatím to tam možná nechat, dal by se tak třeba globálně nastavit určitý styl pro všechno, co je
 * v tom tagu obsaženo. To teď ale nebudu implementovat.
 */

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
    private DocxHelpers frHelpers = null;

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
            frHelpers = new DocxHelpers(frWordDocument.getCreateFactory());
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
                OutputSingleRow(lrCurrentNode.getTextContent());
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
                OutputSingleRow(lrCurrentNode.getTextContent());
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
                OutputSingleRow(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("class")) {
                ParseClasses(lrCurrentNode.getChildNodes());
            }
        }
    }

    /**
     * Zpracuje tag class.
     * @param irNodes 
     */
    private void ParseClasses(NodeList irNodes)
    {
        if(frData == null || frData.getClasses() == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(IClass lrClass : frData.getClasses())
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("heading")) {
                    //ParseHeading(lrClass, lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("anotationList")) {
                    ParseAnotationList((Collection<IAnotation>) lrClass.getAnotations(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("attrList")) {
                    ParseAttrList((Collection<IAttribute>) lrClass.getAttributeModels(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("classVisibility")) {
                    OutputSingleRow(lrClass.getVisibility().toString());
                } else if (lsNodeName.equals("methodList")) {
                    ParseMethodList((Collection<IMethod>) lrClass.getMethodModels(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("relationList")) {
                    ParseRelationList((Collection<IRelation>) lrClass.getRelatedClass(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("classname")) {
                    OutputSingleRow(lrClass.getTypeName());
                }
            }
        }
    }

    /**
     * Zpracuje tag anotationList.
     * @param irAnotationList
     * @param irNodes 
     */
    private void ParseAnotationList(Collection<IAnotation> irAnotationList, NodeList irNodes)
    {
        if(irAnotationList == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleRow(lrCurrentNode.getTextContent());
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
    private void ParseAnotation(Collection<IAnotation> irAnotationList, NodeList irNodes)
    {
        if(irAnotationList == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(IAnotation lrAnotation : irAnotationList)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("anotationName")) {
                    OutputSingleRow(lrAnotation.getName());
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
        if(irAnotationAttrs == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(IAnotationValue lrAnotationValue : irAnotationAttrs)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("anotationAttrName")) {
                    OutputListItem(lrAnotationValue.getName(), 0);
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
        if(irAnotationAttrValues == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(String lsAnotationAttrValue : irAnotationAttrValues)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("anotationAttrValue")) {
                    OutputListItem(lsAnotationAttrValue, 1);
                }
            }
        }
    }
    
    /**
     * Zpracuje tag attrList.
     * @param irAttributes
     * @param irNodes 
     */
    private void ParseAttrList(Collection<IAttribute> irAttributes, NodeList irNodes)
    {
        if(irAttributes == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleRow(lrCurrentNode.getTextContent());
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
    private void ParseAttributes(Collection<IAttribute> irAttributes, NodeList irNodes)
    {
        if(irAttributes == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(IAttribute lrAttribute : irAttributes)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("attrName")) {
                    OutputSingleRow(lrAttribute.getName());
                } else if (lsNodeName.equals("attrVisibility")) {
                    OutputSingleRow(lrAttribute.getVisibility().toString());
                } else if (lsNodeName.equals("attrDatatype")) {
                    OutputSingleRow(lrAttribute.getType().getTypeName());
                } else if (lsNodeName.equals("anotationList")) {
                    ParseAnotationList((Collection<IAnotation>) lrAttribute.getAnotations(), lrCurrentNode.getChildNodes());
                }
            }
        }
    }
    
    /**
     * Zpracuje tag methodList.
     * @param irMethodList
     * @param irNodes 
     */
    private void ParseMethodList(Collection<IMethod> irMethodList, NodeList irNodes)
    {
        if(irMethodList == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleRow(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("method")) {
                ParseMethods(irMethodList, lrCurrentNode.getChildNodes());
            }
        }
    }

    /**
     * Zpracuje tag method.
     * @param irMethodList
     * @param irNodes 
     */
    private void ParseMethods(Collection<IMethod> irMethodList, NodeList irNodes)
    {
        if(irMethodList == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(IMethod lrMethod : irMethodList)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("methodVisibility")) {
                    OutputSingleRow(lrMethod.getVisibility().toString());
                } else if (lsNodeName.equals("methodName")) {
                    OutputSingleRow(lrMethod.getName());
                } else if (lsNodeName.equals("methodReturnType")) {
                    OutputSingleRow(lrMethod.getType().getTypeName());
                } else if (lsNodeName.equals("attrList")) {
                    ParseAttrList((Collection<IAttribute>) lrMethod.getAttributeModels(), lrCurrentNode.getChildNodes());
                }
            }
        }
    }

    /**
     * Zpracuje tag relationList.
     * @param irRelations
     * @param irNodes 
     */
    private void ParseRelationList(Collection<IRelation> irRelations, NodeList irNodes)
    {
        if(irRelations == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleRow(lrCurrentNode.getTextContent());
            } else if(lsNodeName.equals("relation")) {
                ParseRelations(irRelations, lrCurrentNode.getChildNodes());
            }
        }
    }

    /**
     * Zpracuje tag relation.
     * @param irRelations
     * @param irNodes 
     */
    private void ParseRelations(Collection<IRelation> irRelations, NodeList irNodes)
    {
        if(irRelations == null)
            return;

        Node lrCurrentNode;
        String lsNodeName;

        for(IRelation lrRelation : irRelations)
        {
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent());
                } else if (lsNodeName.equals("relationFromClassname")) {
                    OutputSingleRow(lrRelation.getStartingClass().getTypeName());
                } else if (lsNodeName.equals("relationToClassname")) {
                    OutputSingleRow(lrRelation.getEndingClass().getTypeName());
                } else if (lsNodeName.equals("relationType")) {
                    String lsOutput = "";

                    switch(lrRelation.getRelationType())
                    {
                        case RELATION:          lsOutput = "vztah";     break;
                        case COMPOSITION:       lsOutput = "kompozice"; break;
                        case AGREGATION:        lsOutput = "agregace";  break;
                        case GENERALIZATION:    lsOutput = "zobecnění"; break;
                        case REALISATION:       lsOutput = "realizace"; break;
                        default:                lsOutput = "<neznámý typ vztahu>";
                    }

                    OutputSingleRow(lsOutput);
                } else if (lsNodeName.equals("relationCardinalityStartFrom")) {
                    OutputSingleRow(String.valueOf(lrRelation.getStartCardinality().getFrom()));
                }  else if (lsNodeName.equals("relationCardinalityStartTo")) {
                    OutputSingleRow(String.valueOf(lrRelation.getStartCardinality().getTo()));
                }  else if (lsNodeName.equals("relationCardinalityEndFrom")) {
                    OutputSingleRow(String.valueOf(lrRelation.getEndCardinality().getFrom()));
                }  else if (lsNodeName.equals("relationCardinalityEndTo")) {
                    OutputSingleRow(String.valueOf(lrRelation.getEndCardinality().getTo()));
                }
            }
        }
    }

    /**
     * Zpracuje tag header, což je záhlaví dokumentu.
     * @param irNodes 
     */
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

    /**
     * Zpracuje tag footer, což je zápatí dokumentu.
     * @param irNodes 
     */
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

    /**
     * Přidá odstavec do těla dokumentu.
     * @param irPara 
     */
    private void OutputParagraphToBody(P irPara)
    {
        frWordDocumentBody.getEGBlockLevelElts().add(irPara);
    }

    /**
     * Přidá odstavec do záhlaví.
     * @param irPara 
     */
    private void OutputParagraphToHeader(P irPara)
    {
        frHeader.getEGBlockLevelElts().add(irPara);
    }

    /**
     * Přidá odstavec do zápatí.
     * @param irPara 
     */
    private void OutputParagraphToFooter(P irPara)
    {
        frFooter.getEGBlockLevelElts().add(irPara);
    }

    /**
     * Do dokumentu zapíše jeden řádek textu.
     * @param isText Text k zapsání.
     */
    private void OutputSingleRow(String isText)
    {
        P lrPara = frHelpers.CreateParagraphWithText(isText);
        OutputParagraphToBody(lrPara);
    }

    /**
     * Do dokumentu zapíše jeden řádek textu s definovaným stylováním.
     * @param isText
     * @param irStyle 
     */
    private void OutputSingleRow(String isText, Style irStyle)
    {
        P lrPara = frHelpers.CreateParagraphWithText(isText, irStyle);
        OutputParagraphToBody(lrPara);
    }

    /**
     * Zadaný text zapíše do záhlaví.
     * @param isText 
     */
    private void OutputHeader(String isText)
    {
        OutputParagraphToHeader(frHelpers.CreateParagraphWithText(isText));
    }

    /**
     * Zadaný text se zadaným stylem zapíše do záhlaví.
     * @param isText
     * @param irStyle 
     */
    private void OutputHeader(String isText, Style irStyle)
    {
        OutputParagraphToHeader(frHelpers.CreateParagraphWithText(isText, irStyle));
    }

    /**
     * Zadaný text zapíše do zápatí.
     * @param isText 
     */
    private void OutputFooter(String isText)
    {
        OutputParagraphToFooter(frHelpers.CreateParagraphWithText(isText));
    }

    /**
     * Zadaný text se zadaným stylem zapíše do zápatí.
     * @param isText
     * @param irStyle 
     */
    private void OutputFooter(String isText, Style irStyle)
    {
        OutputParagraphToFooter(frHelpers.CreateParagraphWithText(isText, irStyle));
    }

    /**
     * Zapíše seznam textů jako položky seznamu se zadanou úrovní.
     * Zatím nevyužito, ale může se hodit.
     * @param irTexts   Seznam textů k zapsání.
     * @param inLevel   Úroveň seznamu. 0 je nejmenší, 8 největší.
     */
    private void OutputList(List<String> irTexts, int inLevel)
    {
        for(String lsText : irTexts)
        {
            OutputListItem(lsText, inLevel);
        }
    }

    /**
     * Zapíše položku seznamu se zadaným textem a úrovní.
     * @param isText    Text.
     * @param inLevel   Úroveň seznamu. 0 je nejmenší, 8 největší.
     */
    private void OutputListItem(String isText, int inLevel)
    {
        OutputParagraphToBody(frHelpers.CreateListItem(isText, inLevel));
    }
}
