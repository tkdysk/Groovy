package org.cyberneko.html.sample;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//
//  Traversing document object tree by NekoHTML
//  http://asistobe851.web.fc2.com/JAVA/MyEssentialOfJava/DomParser/traversing.html
//
/**
* $Id: DOMTraverser.java,v 1.7 2009/08/23 14:54:23 kishi Exp kishi $
* @author KISHI Yasuhiro
*/
public class DOMTraverser {
    public static void main ( String args[] ) throws Exception {
        DOMTraverser traverser = new DOMTraverser ( "http://d.hatena.ne.jp/nmomose/20120210/tenno" );
        traverser.traverse();
    }
	
	
	
    private String uriStr;
    private DOMParser parser =null;

    public DOMTraverser ( String uriStr ) throws Exception {
        this.uriStr = uriStr;
        parser = new DOMParser();
        parser.setFeature ( "http://xml.org/sax/features/namespaces", false );
    }

    public void traverse() throws Exception {

        parser.parse ( uriStr );
        Document document = parser.getDocument();

        //-------------------------------------------------------------
        // get root element --> as you know, that is HTML tag.
        //-------------------------------------------------------------
        Node root = document.getDocumentElement();

        System.out.print ( root.getNodeName() );
        System.out.println ( getAttrInfo ( root ) );
        System.out.println();

        NodeList nodeList = root.getChildNodes();
        System.out.println ( "\t/** the # of children is " + nodeList.getLength() + "*/" );
        System.out.println ();

        //-------------------------------------------------------------
        // get started with traversing
        //-------------------------------------------------------------
        doTraverse ( root,1 );

    }

    private static String doubleQuote ( String value ) {
        return "\"" + value + "\"";
    }

    private String getAttrInfo ( Node node ) {
        StringBuilder sb = new StringBuilder();
        NamedNodeMap attrMap = node.getAttributes();
        int attrs = attrMap.getLength();
        for ( int iAttr = 0; iAttr < attrs; iAttr++ ) {
            Node attr = attrMap.item ( iAttr );
            sb.append ( " @[" + attr.getNodeName() + "=" + doubleQuote ( attr.getNodeValue() ) + "]"  + " " );
        }
        return sb.toString();
    }

    private void doTraverse ( Node parent, int indent ) {
        Node child = parent.getFirstChild();
        while ( child != null ) {
            String nodeName = child.getNodeName();
            int nodeType = child.getNodeType();

            if ( nodeType == Node.ELEMENT_NODE ) {
                //------------------------------------------------
                // indent according to the depth
                // ------------------------------------------------
                for ( int i=0; i< indent ; i++ ) {
                    System.out.print ( "  " );
                }

                //------------------------------------------------
                // print element and attributes
                // ------------------------------------------------
                System.out.print ( nodeName + "(" + indent + ")" );
                System.out.print ( getAttrInfo ( child ) );

                //------------------------------------------------
                // print if this node's child is TEXT_NODE
                // ------------------------------------------------
                Node textNode = child.getFirstChild();
                if ( textNode != null && textNode.getNodeType() == Node.TEXT_NODE ) {
                    String nodeValue = textNode.getNodeValue().trim();
                    // erase unneeded spaces
                    nodeValue = nodeValue.replaceAll ( "\r", "" ).replaceAll ( "\n", "" ).replaceAll ( "\\s+", " " );
                    System.out.print ( nodeValue );
                }
                System.out.println();

            }

            //------------------------------------------------
            // go forward to its child node
            // ------------------------------------------------
            doTraverse ( child, indent + 1 );

            child = child.getNextSibling();
        }
    }
}

