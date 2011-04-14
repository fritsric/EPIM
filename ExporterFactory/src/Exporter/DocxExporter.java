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
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
 *   - header
 *   - footer
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
    private String fsStyleTemplatePath = "";
    private IClassModelModel frData = null;
    private WordDocument frWordDocument = null;
    private Body frWordDocumentBody = null;
    private Hdr frHeader = null;
    private Ftr frFooter = null;
    private DocxHelpers frHelpers = null;
    private ArrayList<Node> frStyleElements = new ArrayList<Node>();

    public DocxExporter(
            String isTemplatePath, String isStyleTemplatePath, String isOutputPath, IClassModelModel irData)
    {
        fsTemplatePath = isTemplatePath;
        fsStyleTemplatePath = isStyleTemplatePath;
        fsOutputPath = isOutputPath;
        frData = irData;
    }

    public void Export() throws BadTemplateException, TemplateParseErrorExcpetion, DocxWriterException
    {
        Document lrTemplateDoc, lrStyleDoc;
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

        try
        {
            lrStyleDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fsStyleTemplatePath));
        }
        catch (Exception e)
        {
            throw new TemplateParseErrorExcpetion("Error when parsing style definitions: " + e.toString());
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

        NodeList lrStyleDefNodes = lrStyleDoc.getElementsByTagName("styleTemplate");

        if(lrStyleDefNodes.getLength() == 0)
            throw new BadTemplateException("Tag 'styleTemplate' not found!");

        lrStyleDefNodes = lrStyleDefNodes.item(0).getChildNodes();

        for(int i = 0; i < lrStyleDefNodes.getLength(); i++)
        {
            if(lrStyleDefNodes.item(i).getNodeName().equals("style"))
                frStyleElements.add(lrStyleDefNodes.item(i));
        }

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
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
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
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
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
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
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
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("anotationList")) {
                    ParseAnotationList((Collection<IAnotation>) lrClass.getAnotations(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("attrList")) {
                    ParseAttrList((Collection<IAttribute>) lrClass.getAttributeModels(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("classVisibility")) {
                    OutputSingleRow(lrClass.getVisibility().toString(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("methodList")) {
                    ParseMethodList((Collection<IMethod>) lrClass.getMethodModels(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("relationList")) {
                    ParseRelationList((Collection<IRelation>) lrClass.getRelatedClass(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("classname")) {
                    OutputSingleRow(lrClass.getTypeName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("oneRow")) {
                    ParseOneRowInClassContext(lrClass, lrCurrentNode.getChildNodes());
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
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
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
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("anotationName")) {
                    OutputSingleRow(lrAnotation.getName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("anotationAttrs")) {
                    ParseAnotationAttributes(lrAnotation.getAttributes(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("oneRow")) {
                    ParseOneRowInAnotationContext(lrAnotation, lrCurrentNode.getChildNodes());
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
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("anotationAttrName")) {
                    OutputListItem(lrAnotationValue.getName(), 0, GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("anotationAttrValueList")) {
                    ParseAnotationAttrValueList(lrAnotationValue.getValues(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("oneRow")) {
                    ParseOneRowInAnotationAttrsContext(lrAnotationValue, lrCurrentNode.getChildNodes());
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
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("anotationAttrValue")) {
                    OutputListItem(lsAnotationAttrValue, 1, GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("oneRow")) {
                    ParseOneRowInAnotationValueContext(lsAnotationAttrValue, lrCurrentNode.getChildNodes());
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
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
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
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("attrName")) {
                    OutputSingleRow(lrAttribute.getName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("attrVisibility")) {
                    OutputSingleRow(lrAttribute.getVisibility().toString(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("attrDatatype")) {
                    OutputSingleRow(lrAttribute.getType().getTypeName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("anotationList")) {
                    ParseAnotationList((Collection<IAnotation>) lrAttribute.getAnotations(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("oneRow")) {
                    ParseOneRowInAttributeContext(lrAttribute, lrCurrentNode.getChildNodes());
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
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
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
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("methodVisibility")) {
                    OutputSingleRow(lrMethod.getVisibility().toString(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("methodName")) {
                    OutputSingleRow(lrMethod.getName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("methodReturnType")) {
                    OutputSingleRow(lrMethod.getType().getTypeName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("attrList")) {
                    ParseAttrList((Collection<IAttribute>) lrMethod.getAttributeModels(), lrCurrentNode.getChildNodes());
                } else if (lsNodeName.equals("oneRow")) {
                    ParseOneRowInMethodContext(lrMethod, lrCurrentNode.getChildNodes());
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
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
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
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("relationFromClassname")) {
                    OutputSingleRow(lrRelation.getStartingClass().getTypeName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("relationToClassname")) {
                    OutputSingleRow(lrRelation.getEndingClass().getTypeName(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("relationType")) {
                    OutputSingleRow(GetRelationText(lrRelation.getRelationType()), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("relationCardinalityStartFrom")) {
                    OutputSingleRow(String.valueOf(lrRelation.getStartCardinality().getFrom()), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("relationCardinalityStartTo")) {
                    OutputSingleRow(String.valueOf(lrRelation.getStartCardinality().getTo()), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("relationCardinalityEndFrom")) {
                    OutputSingleRow(String.valueOf(lrRelation.getEndCardinality().getFrom()), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("relationCardinalityEndTo")) {
                    OutputSingleRow(String.valueOf(lrRelation.getEndCardinality().getTo()), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("oneRow")) {
                    ParseOneRowInRelationContext(lrRelation, lrCurrentNode.getChildNodes());
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
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            }
        }

        OutputHeader(lrTexts);
    }

    /**
     * Zpracuje tag footer, což je zápatí dokumentu.
     * @param irNodes 
     */
    private void ParseFooter(NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            }
        }

        OutputFooter(lrTexts);
    }

    /**
     * Zpracuje tag oneRow jako potomka tagu class.
     * @param irClass
     * @param irNodes 
     */
    private void ParseOneRowInClassContext(IClass irClass, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("classname")) {
                lrTexts.add(new StyledText(irClass.getTypeName(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("classVisibility")) {
                lrTexts.add(new StyledText(irClass.getVisibility().toString(), GetStyle(lrCurrentNode)));
            }
        }

        OutputOneRowMultipleTexts(lrTexts);
    }

    /**
     * Zpracuje tag oneRow jako potomka tagu anotation.
     * @param irAnotation
     * @param irNodes 
     */
    private void ParseOneRowInAnotationContext(IAnotation irAnotation, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("anotationName")) {
                lrTexts.add(new StyledText(irAnotation.getName(), GetStyle(lrCurrentNode)));
            }
        }

        OutputOneRowMultipleTexts(lrTexts);
    }

    /**
     * Zpracuje tag oneRow jako potomka tagu anotationAttrs.
     * @param irAnotationValue
     * @param irNodes 
     */
    private void ParseOneRowInAnotationAttrsContext(IAnotationValue irAnotationValue, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("anotationAttrName")) {
                lrTexts.add(new StyledText(irAnotationValue.getName(), GetStyle(lrCurrentNode)));
            }
        }

        OutputOneRowMultipleTexts(lrTexts);
    }

    /**
     * Zpracuje tag oneRow jako potomka tagu anotationAttrValueList.
     * @param isAnotationValue
     * @param irNodes 
     */
    private void ParseOneRowInAnotationValueContext(String isAnotationValue, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("anotationAttrValue")) {
                lrTexts.add(new StyledText(isAnotationValue, GetStyle(lrCurrentNode)));
            }
        }

        OutputOneRowMultipleTexts(lrTexts);
    }

    /**
     * Zpracuje tag oneRow jako potomka tagu attribute.
     * @param irAttribute
     * @param irNodes 
     */
    private void ParseOneRowInAttributeContext(IAttribute irAttribute, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("attrName")) {
                lrTexts.add(new StyledText(irAttribute.getName(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("attrVisibility")) {
                lrTexts.add(new StyledText(irAttribute.getVisibility().toString(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("attrDatatype")) {
                lrTexts.add(new StyledText(irAttribute.getType().getTypeName(), GetStyle(lrCurrentNode)));
            }
        }

        OutputOneRowMultipleTexts(lrTexts);
    }

    /**
     * Zpracuje tag oneRow jako potomka tagu method.
     * @param irMethod
     * @param irNodes 
     */
    private void ParseOneRowInMethodContext(IMethod irMethod, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("methodVisibility")) {
                lrTexts.add(new StyledText(irMethod.getVisibility().toString(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("methodName")) {
                lrTexts.add(new StyledText(irMethod.getName(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("methodReturnType")) {
                lrTexts.add(new StyledText(irMethod.getType().getTypeName(), GetStyle(lrCurrentNode)));
            }
        }

        OutputOneRowMultipleTexts(lrTexts);
    }

    /**
     * Zpracuje tag oneRow jako potomka tagu relation.
     * @param irRelation
     * @param irNodes 
     */
    private void ParseOneRowInRelationContext(IRelation irRelation, NodeList irNodes)
    {
        Node lrCurrentNode;
        String lsNodeName;
        ArrayList<StyledText> lrTexts = new ArrayList<StyledText>();

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                lrTexts.add(new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("relationFromClassname")) {
                lrTexts.add(new StyledText(irRelation.getStartingClass().getTypeName(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("relationToClassnameName")) {
                lrTexts.add(new StyledText(irRelation.getEndingClass().getTypeName(), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("relationType")) {
                lrTexts.add(new StyledText(GetRelationText(irRelation.getRelationType()), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("relationCardinalityStartFrom")) {
                lrTexts.add(new StyledText(String.valueOf(irRelation.getStartCardinality().getFrom()), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("relationCardinalityStartTo")) {
                lrTexts.add(new StyledText(String.valueOf(irRelation.getStartCardinality().getTo()), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("relationCardinalityEndFrom")) {
                lrTexts.add(new StyledText(String.valueOf(irRelation.getEndCardinality().getFrom()), GetStyle(lrCurrentNode)));
            } else if(lsNodeName.equals("relationCardinalityEndTo")) {
                lrTexts.add(new StyledText(String.valueOf(irRelation.getEndCardinality().getTo()), GetStyle(lrCurrentNode)));
            }
        }

        OutputOneRowMultipleTexts(lrTexts);
    }

    /**
     * Z načteného XML stylopisu vrátí tag style se zadanou hodnotou atributu name.
     * @param isStyleName
     * @return  Příslušný uzel, pokud je nalezen, jinak null.
     */
    private Node GetStyleElementByName(String isStyleName)
    {
        Node lrStyleAttribute;

        for(Node lrNode : frStyleElements)
        {
            lrStyleAttribute = lrNode.getAttributes().getNamedItem("name");

            if(lrStyleAttribute == null) // vůbec to ten atribut nemá
                continue;

            if(lrStyleAttribute.getNodeValue().equals(isStyleName))
                return lrNode;
        }

        return null;
    }

    /**
     * Provede ověření, zda zadaná hodnota barvy je správná. Docx potřebuje barvu ve formátu RRGGBB.
     * @param isColor
     * @return  Zadaný řetězec, pokud je v pořádku, jinak prázdný řetězec.
     */
    private String ValidateColor(String isColor)
    {
        String[] lrPossibleCharsArray = new String[] {
            "A", "B", "C", "D", "E", "F", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        ArrayList<String> lrPossibleChars = new ArrayList<String>(Arrays.asList(lrPossibleCharsArray));

        if(isColor.length() != 6)
            return "";

        for(int i = 0; i < 6; i++)
        {
            if(!lrPossibleChars.contains(String.valueOf(isColor.charAt(i)).toUpperCase()))
                return "";
        }

        return isColor;
    }

    /**
     * Vrátí stylu pro zadaný uzel.
     * Styl se uzlu nastavuje pomocí atributu style, jehož hodnota odkauje na existující styl
     * v XML stylopisu.
     * @param irNode
     * @return 
     */
    private Style GetStyle(Node irNode)
    {
        Node lrStyleAttribute = irNode.getAttributes().getNamedItem("style");
        Style lrStyle = new Style();

        // žádný styl to definovaný nemá
        if(lrStyleAttribute == null)
            return lrStyle;

        Node lrStyleDefNode = GetStyleElementByName(lrStyleAttribute.getNodeValue());

        if(lrStyleDefNode == null)
            return lrStyle;

        NodeList lrStyleAttribs = lrStyleDefNode.getChildNodes();
        NodeList lrFontAttrs;
        Node lrCurrentNode;
        String lsNodeName;
        String lsColor;
        int lnFontSize;

        for(int i = 0; i < lrStyleAttribs.getLength(); i++)
        {
            lrCurrentNode = lrStyleAttribs.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("font")) {
                lrFontAttrs = lrCurrentNode.getChildNodes();

                for(int j = 0; j < lrFontAttrs.getLength(); j++)
                {
                    lrCurrentNode = lrFontAttrs.item(j);
                    lsNodeName = lrCurrentNode.getNodeName();

                    if(lsNodeName.equals("family")) {
                        lrStyle.fsFontFamily = lrCurrentNode.getTextContent();
                    } else if(lsNodeName.equals("size")) {
                        try
                        {
                            // Velikost v OOXML je v polovinách pt, takže proto *2.
                            lnFontSize = Integer.parseInt(lrCurrentNode.getTextContent()) * 2;
                            lrStyle.fnFontSize = lnFontSize;
                        }
                        catch(NumberFormatException ex) // hehe, to není int
                        {
                        }
                    } else if(lsNodeName.equals("italics")) {
                        lrStyle.fbItalics = true;
                    } else if(lsNodeName.equals("underline")) {
                        lrStyle.fbUnderline = true;
                    } else if(lsNodeName.equals("bold")) {
                        lrStyle.fbBold = true;
                    } else if(lsNodeName.equals("strike")) {
                        lrStyle.fbStrike = true;
                    }
                }
            } else if(lsNodeName.equals("color")) {
                // Barva by měla být ve formátu RRGGBB
                lsColor = ValidateColor(lrCurrentNode.getTextContent());
                lrStyle.fsColor = lsColor;
            } else if(lsNodeName.equals("align")) {
                String lsAlign = lrCurrentNode.getTextContent();

                if(lsAlign.toLowerCase().equals("left")) {
                    lrStyle.frAlign = Align.LEFT;
                } else if(lsAlign.toLowerCase().equals("right")) {
                    lrStyle.frAlign = Align.RIGHT;
                } else if(lsAlign.toLowerCase().equals("center")) {
                    lrStyle.frAlign = Align.CENTER;
                } else if(lsAlign.toLowerCase().equals("justify")) {
                    lrStyle.frAlign = Align.JUSTIFY;
                }
            }
        }

        return lrStyle;
    }

    /**
     * Vrátí textový popisu typu vztahu.
     * @param irRelType
     * @return 
     */
    private String GetRelationText(RelationType irRelType)
    {
        switch(irRelType)
        {
            case RELATION:          return "vztah";
            case COMPOSITION:       return "kompozice";
            case AGREGATION:        return "agregace";
            case GENERALIZATION:    return "zobecnění";
            case REALISATION:       return "realizace";
            default:                return "<neznámý typ vztahu>";
        }
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
     * Do dokumentu vypíše odstavec, který obsahuje více textů s různým formátováním.
     * Styl odstavce (v současnosti zarovnání) se nastaví podle stylu prvního textu.
     * @param irTexts 
     */
    private void OutputOneRowMultipleTexts(ArrayList<StyledText> irTexts)
    {
        P lrPara;

        if(irTexts.size() > 0)
            lrPara = frHelpers.CreateDefaultParagraph(irTexts.get(0).getStyle());
        else
            lrPara = frHelpers.CreateDefaultParagraph();

        for(StyledText lrText : irTexts)
        {
            lrPara.getParagraphContent().add(frHelpers.CreateRunWithText(lrText.getText(), lrText.getStyle()));
        }

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
     * Zadané texty se zadanými styly zapíše do záhlaví.
     * @param isText
     * @param irStyle 
     */
    private void OutputHeader(ArrayList<StyledText> irTexts)
    {
        P lrPara;

        if(irTexts.size() > 0)
            lrPara = frHelpers.CreateDefaultParagraph(irTexts.get(0).getStyle());
        else
            lrPara = frHelpers.CreateDefaultParagraph();

        for(StyledText lrText : irTexts)
        {
            lrPara.getParagraphContent().add(frHelpers.CreateRunWithText(lrText.getText(), lrText.getStyle()));
        }

        OutputParagraphToHeader(lrPara);
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
     * Zadané texty se zadanými styly zapíše do záhlaví.
     * @param isText
     * @param irStyle 
     */
    private void OutputFooter(ArrayList<StyledText> irTexts)
    {
        P lrPara;

        if(irTexts.size() > 0)
            lrPara = frHelpers.CreateDefaultParagraph(irTexts.get(0).getStyle());
        else
            lrPara = frHelpers.CreateDefaultParagraph();

        for(StyledText lrText : irTexts)
        {
            lrPara.getParagraphContent().add(frHelpers.CreateRunWithText(lrText.getText(), lrText.getStyle()));
        }

        OutputParagraphToFooter(lrPara);
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

    /**
     * Zapíše položku seznamu se zadaným textem, formátem a úrovní.
     * @param isText    Text.
     * @param inLevel   Úroveň seznamu. 0 je nejmenší, 8 největší.
     */
    private void OutputListItem(String isText, int inLevel, Style irStyle)
    {
        OutputParagraphToBody(frHelpers.CreateListItem(isText, inLevel, irStyle));
    }

}
