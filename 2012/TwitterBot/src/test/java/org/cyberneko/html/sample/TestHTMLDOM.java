package org.cyberneko.html.sample;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TestHTMLDOM {
    public static void main(String[] argv) throws Exception {
    	
    	String url = "http://www.lawson.co.jp/index.html";
    	
        DOMParser parser = new DOMParser();
        //for (int i = 0; i < argv.length; i++) {
            parser.parse(url);
            //System.out.println( parser.getDocument().getDocumentElement().getTextContent() );
            
            Element element = parser.getDocument().getDocumentElement();
            
            
            
            System.out.println(element.getTextContent());
            
            
            //print(parser.getDocument(), "");
        //}
    }
    public static void print(Node node, String indent) {
        System.out.println(indent+node.getClass().getName());
        Node child = node.getFirstChild();
        while (child != null) {
            print(child, indent+" ");
            child = child.getNextSibling();
        }
    }
}