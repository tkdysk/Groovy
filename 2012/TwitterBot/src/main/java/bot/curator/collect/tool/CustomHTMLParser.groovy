package bot.curator.collect.tool

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Traversing document object tree by NekoHTML
 * http://asistobe851.web.fc2.com/JAVA/MyEssentialOfJava/DomParser/traversing.html
 */
class CustomHTMLParser {

	private String uriStr
	private DOMParser parser =null

	public CustomHTMLParser ( String uriStr ) throws Exception {
		this.uriStr = uriStr
		parser = new DOMParser()
		parser.setFeature ( "http://xml.org/sax/features/namespaces", false )
	}

	public List<String> getContents() throws Exception {

		parser.parse ( uriStr )
		Document document = parser.getDocument()

		//-------------------------------------------------------------
		// get root element --> as you know, that is HTML tag.
		//-------------------------------------------------------------
		Node root = document.getDocumentElement()

		//System.out.print ( root.getNodeName() );
		//System.out.println ( getAttrInfo ( root ) );
		//System.out.println();

		//NodeList nodeList = root.getChildNodes()
		//System.out.println ( "\t/** the # of children is " + nodeList.getLength() + "*/" )
		//System.out.println ();

		//-------------------------------------------------------------
		// get started with traversing
		//-------------------------------------------------------------
		List<String> contents = []
		
		doTraverse ( root, 1, contents )
		
		return contents
	}

//	private static String doubleQuote ( String value ) {
//		return "\"" + value + "\""
//	}
//
//	private String getAttrInfo ( Node node ) {
//		StringBuilder sb = new StringBuilder()
//		NamedNodeMap attrMap = node.getAttributes()
//		int attrs = attrMap.getLength()
//		for ( int iAttr = 0; iAttr < attrs; iAttr++ ) {
//			Node attr = attrMap.item ( iAttr )
//			sb.append ( " @[" + attr.getNodeName() + "=" + doubleQuote ( attr.getNodeValue() ) + "]"  + " " )
//		}
//		return sb.toString()
//	}

	private def doTraverse ( Node parent, int indent, List<String> contents ) {
		Node child = parent.getFirstChild();
		while ( child != null ) {
			String nodeName = child.getNodeName();
			int nodeType = child.getNodeType();

			if ( nodeType == Node.ELEMENT_NODE &&
			nodeName!="SCRIPT" && nodeName!="STYLE" && nodeName!="META" && nodeName!="BR"
			) {
				//------------------------------------------------
				// indent according to the depth
				// ------------------------------------------------
				//for ( int i=0; i< indent ; i++ ) {
				//	System.out.print ( "  " );
				//}

				//------------------------------------------------
				// print element and attributes
				// ------------------------------------------------
				//System.out.print ( nodeName + "(" + indent + ")" );
				//System.out.print ( getAttrInfo ( child ) );

				//------------------------------------------------
				// print if this node's child is TEXT_NODE
				// ------------------------------------------------
				Node textNode = child.getFirstChild();
				if ( textNode != null && textNode.getNodeType() == Node.TEXT_NODE ) {
					String nodeValue = textNode.getNodeValue().trim();
					// erase unneeded spaces
					nodeValue = nodeValue.replaceAll ( "\r", "" ).replaceAll ( "\n", "" ).replaceAll ( "\\s+", " " )
					.replaceAll ( /[ 　]/, " " )
					//System.out.print ( nodeValue );
					if(nodeValue.trim()) contents << nodeValue
					//if(!(nodeValue.replaceAll( /[　 ]/, "" ).isEmpty())){
					//	println ( "「"+nodeValue.replaceAll( /[ 　]/, "" )+"」"+nodeValue.replaceAll( /[ 　]/, "" ).isEmpty() )
					//}
				}
				//System.out.println();

			}

			//------------------------------------------------
			// go forward to its child node
			// ------------------------------------------------
			doTraverse ( child, indent + 1, contents );

			child = child.getNextSibling();
		}
	}
}
