package Exporter;

import cz.cvut.hnatuluk.Exception.BadTemplateException;
import cz.cvut.hnatuluk.Exception.XlsxWriterException;
import cz.cvut.hnatuluk.Exception.TemplateParseErrorExcpetion;
import cz.cvut.hnatuluk.factories.ExportFactory;
import cz.cvut.hnatuluk.factories.ExcelFactory.ExcelOutputFactory;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Michal Salat
 */
public class XlsxExporter {
    private String fsTemplatePath = "";
    private String fsOutputPath = "";
    private String fsStyleTemplatePath = "";
    private IClassModelModel frData = null;
    private Workbook frExcelDocument = null;
    private Sheet frExcelSheet = null;
    private Header frHeader = null;
    private Footer frFooter = null;
    private DocxHelpers frHelpers = null;
    private Row frRow = null;
    private Cell frCell = null;
    private ArrayList<Node> frStyleElements = new ArrayList<Node>();
    private int frRowCount = 0;
    
    public XlsxExporter(
            String isTemplatePath, String isStyleTemplatePath, String isOutputPath, IClassModelModel irData)
    {
        fsTemplatePath = isTemplatePath;
        fsStyleTemplatePath = isStyleTemplatePath;
        fsOutputPath = isOutputPath;
        frData = irData;
    }
    
        public void Export() throws BadTemplateException, TemplateParseErrorExcpetion, XlsxWriterException
    {
        Document lrTemplateDoc, lrStyleDoc;
        ExcelOutputFactory lrEOF;
        File lrOutputFile;

        
        /* Template + Style definitions parsing
         * XML -> DOM
         */
        
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
        
        lrEOF = ExportFactory.getExcelFactory();

        try
        {
            lrOutputFile = lrEOF.createOutputFile(fsOutputPath);
            frExcelDocument = lrEOF.createOutputWorkBook(lrOutputFile);
            frExcelSheet = frExcelDocument.createSheet("Model");
            frHeader = frExcelSheet.getHeader();
            frFooter = frExcelSheet.getFooter();
            //frHelpers = new DocxHelpers(frWordDocument.getCreateFactory());
        }
        catch(Exception e)
        {
            throw new XlsxWriterException("Error when creating output: " + e.toString());
        }

        NodeList lrNodes = lrTemplateDoc.getElementsByTagName("docxTemplate");

        if(lrNodes.getLength() == 0)
            throw new BadTemplateException("Tag 'Template' not found!");

        NodeList lrStyleDefNodes = lrStyleDoc.getElementsByTagName("styleTemplate");

        if(lrStyleDefNodes.getLength() == 0)
            throw new BadTemplateException("Tag 'styleTemplate' not found!");

        lrStyleDefNodes = lrStyleDefNodes.item(0).getChildNodes();

        for(int i = 0; i < lrStyleDefNodes.getLength(); i++)
        {
            if(lrStyleDefNodes.item(i).getNodeName().equals("style"))
                frStyleElements.add(lrStyleDefNodes.item(i));
        }

        ParseTemplate(lrNodes.item(0).getChildNodes());

        try
        {
            lrEOF.write(lrOutputFile, frExcelDocument);
        }
        catch(Exception e)
        {
            throw new XlsxWriterException("Error when writing output!");
        }
    }
        
        /**
     * Zpracuje tag docxTemplate.
     * @param irData
     * @param orWordDoc
     * @param irNodes 
     */
    private void ParseTemplate(NodeList irNodes)
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

        for(IElement lrClass : frData.getClasses())
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
                    OutputListItem(lrAnotationValue.getName(), frRowCount , 1, GetStyle(lrCurrentNode));
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
                    OutputListItem(lsAnotationAttrValue, frRowCount, 1, GetStyle(lrCurrentNode));
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
            int lrRow = frRowCount;
            for(int i = 0; i < irNodes.getLength(); i++)
            {
                lrCurrentNode = irNodes.item(i);
                lsNodeName = lrCurrentNode.getNodeName();

                if(lsNodeName.equals("staticText")) {
                    OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("attrName")) {
                    OutputListItem(lrAttribute.getName(), lrRow, 1, GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("attrVisibility")) {
                    OutputListItem(lrAttribute.getVisibility().toString(), lrRow, 2, GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("attrDatatype")) {
                    OutputListItem(lrAttribute.getType().getTypeName(), lrRow, 0, GetStyle(lrCurrentNode));
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
        StyledText lrTextAfter = null;

        for(int i = 0; i < irNodes.getLength(); i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();

            if(lsNodeName.equals("staticText")) {
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
            } else if(lsNodeName.equals("method")) {
                ParseMethods(irMethodList, lrCurrentNode.getChildNodes());
            } else if(lsNodeName.equals("label")) {
                lrTextAfter = ParseLabel(lrCurrentNode.getChildNodes());
            }
        }
        
        if (lrTextAfter != null)
            OutputSingleRow(lrTextAfter.getText(), lrTextAfter.getCellStyle());
    }

    /**
     * Zpracuje tag method.
     * @param irMethodList
     * @param irNodes 
     */
    private void ParseMethods(Collection<IMethod> irMethodList, NodeList irNodes)
    {
        int lrRow = frRowCount;
        
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
                    OutputListItem(lrMethod.getVisibility().toString(), lrRow, 2, GetStyle(lrCurrentNode));                    
                } else if (lsNodeName.equals("methodName")) {
                    OutputListItem(lrMethod.getName(), lrRow, 1, GetStyle(lrCurrentNode));
                } else if (lsNodeName.equals("methodReturnType")) {
                    OutputListItem(lrMethod.getType().getTypeName(), lrRow, 0, GetStyle(lrCurrentNode));
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
    private void ParseOneRowInClassContext(IElement irClass, NodeList irNodes)
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
            return "000000";

        for(int i = 0; i < 6; i++)
        {
            if(!lrPossibleChars.contains(String.valueOf(isColor.charAt(i)).toUpperCase()))
                return "000000";
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
    private CellStyle GetStyle(Node irNode)
    {
        Node lrStyleAttribute = irNode.getAttributes().getNamedItem("style");
        CellStyle lrStyle = frExcelDocument.createCellStyle();
        Font lrFont = frExcelDocument.createFont();
        /*
         * definujeme vlastní, defaultní styl
         */
        
        //pismo 10
        lrFont.setFontHeightInPoints((short) 10);
        //barva cerna
        lrFont.setColor((short) 0 );
        lrStyle.setFont(lrFont);
        
        /*
         * žádný styl to definovaný nemá
        */
        
        if(lrStyleAttribute == null)
        {            
            return lrStyle;
        }

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
                        lrFont.setFontName(lrCurrentNode.getTextContent());
                    } else if(lsNodeName.equals("size")) {
                        try
                        {
                            // Velikost v OOXML je v polovinách pt, takže proto *2.
                            lrFont.setFontHeightInPoints(Short.parseShort(lrCurrentNode.getTextContent()));
                        }
                        catch(NumberFormatException ex) // hehe, to není int
                        {
                        }
                    } else if(lsNodeName.equals("italics")) {
                        lrFont.setItalic(true);
                    } else if(lsNodeName.equals("underline")) {
                        lrFont.setUnderline(Font.U_SINGLE);
                    } else if(lsNodeName.equals("bold")) {
                        lrFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
                    } else if(lsNodeName.equals("strike")) {
                        lrFont.setStrikeout(true);
                    }
                }
            } else if(lsNodeName.equals("color")) {
                // Barva by měla být ve formátu RRGGBB
                lsColor = ValidateColor(lrCurrentNode.getTextContent());
                //Barva je v hex
                lrFont.setColor((short)Integer.parseInt(lsColor, 16));
            } else if(lsNodeName.equals("align")) {
                String lsAlign = lrCurrentNode.getTextContent();

                if(lsAlign.toLowerCase().equals("left")) {
                    lrStyle.setAlignment(CellStyle.ALIGN_LEFT);
                } else if(lsAlign.toLowerCase().equals("right")) {
                    lrStyle.setAlignment(CellStyle.ALIGN_RIGHT);
                } else if(lsAlign.toLowerCase().equals("center")) {
                    lrStyle.setAlignment(CellStyle.ALIGN_CENTER);
                } else if(lsAlign.toLowerCase().equals("justify")) {
                    lrStyle.setAlignment(CellStyle.ALIGN_JUSTIFY);
                }
            }
        }
        lrStyle.setFont(lrFont);
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
     * Do dokumentu zapíše jeden řádek textu.
     * @param isText Text k zapsání.
     */
    private void OutputSingleRow(String isText)
    {
       Row lrRow = frExcelSheet.createRow(frRowCount++);
       Cell lrCell = lrRow.createCell(0);
       lrCell.setCellValue(isText);
    }

    /**
     * Do dokumentu zapíše jeden řádek textu s definovaným stylováním.
     * @param isText
     * @param irStyle 
     */
    private void OutputSingleRow(String isText, CellStyle irStyle)
    {
        Row lrRow = frExcelSheet.createRow(frRowCount++);
       Cell lrCell = lrRow.createCell(0);
       lrCell.setCellStyle(irStyle);
       lrCell.setCellValue(isText);       
    }

    /**
     * Do dokumentu vypíše odstavec, který obsahuje více textů s různým formátováním.
     * Styl odstavce (v současnosti zarovnání) se nastaví podle stylu prvního textu.
     * @param irTexts 
     */
    private void OutputOneRowMultipleTexts(ArrayList<StyledText> irTexts)
    {
       Row lrRow = frExcelSheet.createRow(frRowCount++);
       int i = 0;       
        
        for(StyledText lrText : irTexts)
        {
            Cell lrCell = lrRow.createCell(i);
            lrCell.setCellStyle(lrText.getCellStyle());
            lrCell.setCellValue(lrText.getText());
            i++;
        }        
    }

    /**
     * Zadaný text zapíše do záhlaví.
     * Záhlaví, zdá se, nepodporuje styly!
     * @param isText 
     */
    private void OutputHeader(String isText)
    {
        frHeader.setCenter(isText);        
    }

    /**
     * Zadané texty se zadanými styly zapíše do záhlaví.
     * @param isText
     * @param irStyle 
     */
    private void OutputHeader(ArrayList<StyledText> irTexts)
    {
        String header = "";   

        for(StyledText lrText : irTexts)
        {
            header+=lrText.getText()+'\n';
        }

        OutputHeader(header);
    }
    
    /**
     * Zadaný text zapíše do zápatí.
     * @param isText 
     */
    private void OutputFooter(String isText)
    {
        frFooter.setCenter(isText);
    }

    /**
     * Zadané texty se zadanými styly zapíše do záhlaví.
     * @param isText
     * @param irStyle 
     */
    private void OutputFooter(ArrayList<StyledText> irTexts)
    {
        String footer = "";   

        for(StyledText lrText : irTexts)
        {
            footer+=lrText.getText()+"\n";
        }

        OutputFooter(footer);
    }

    /**
     * Zapíše položku seznamu se zadaným textem, formátem a úrovní.
     * @param isText    Text.
     * @param inLevel   Úroveň seznamu. 0 je nejmenší, 8 největší - ZATÍM NEVYUŽITO.
     */
    private void OutputListItem(String isText, int inRow, int inLevel, CellStyle irStyle)
    {
        Row lrRow;
        
        if (inRow <frRowCount && inRow >=0) lrRow = frExcelSheet.getRow(inRow);
        else lrRow=frExcelSheet.createRow(frRowCount++);

        Cell lrCell = lrRow.createCell(inLevel);
        lrCell.setCellStyle(irStyle);
        lrCell.setCellValue(isText);
    }

    private StyledText ParseLabel(NodeList irNodes) {
        int lrCnt = irNodes.getLength();
        int i = 0;
        
        if (lrCnt == 0) return null;
               
        StyledText lrRes=null;
        Node lrCurrentNode;
        String lsNodeName;
        
        for(i=0; i<lrCnt; i++)
        {
            lrCurrentNode = irNodes.item(i);
            lsNodeName = lrCurrentNode.getNodeName();
            if (lsNodeName.equals("labelBefore"))
            {
                OutputSingleRow(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
            } else if (lsNodeName.equals("labelAfter"))
            {
                lrRes = new StyledText(lrCurrentNode.getTextContent(), GetStyle(lrCurrentNode));
            }
        }
        
        return lrRes;
    }
    
}
   