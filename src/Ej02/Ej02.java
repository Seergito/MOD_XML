package Ej02;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ej02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Document DOM=UtilidadesDOM.generararbolDOMfichero("DOM.xml");
			Element raiz=DOM.getDocumentElement();
			NodeList lista=raiz.getElementsByTagName("item");
			Element channel=(Element) raiz.getElementsByTagName("channel").item(0);
			
			Element eliminar=null;
			
			Comment comentario=DOM.createComment("Noticia añadida por código");
			
			boolean mod=false;
			boolean del=false;
			
			for (int i = 0; i < lista.getLength(); i++) {
				Element eitem=(Element) lista.item(i);
				Element etitulo=(Element) eitem.getElementsByTagName("title").item(0);
			
				if(etitulo.getTextContent().toString().equals("Nuevo curso Diseño Gráfico")) {
					etitulo.setTextContent("Curso de Diseño Gráfico");
					mod=true;
				}
				else if(etitulo.getTextContent().toString().equals("Nuevo Curso de guiones de cine")){
					eliminar=eitem;
					del=true;
				}else if(etitulo.getTextContent().toString().equals("Microsoft Office nivel medio")) {
					channel.insertBefore(comentario, eitem);
				}	
			}
			if(mod==true) {
				System.out.println("Elemento modificado...");
				UtilidadesDOM.crearficheroxml(DOM, "DOM_MOD.xml");
			}else {
				System.out.println("No se ha podido modificar el elemento...");
			}
			if(del==true) {
				System.out.println("Se ha eliminado el Elemento");
				channel.removeChild(eliminar);
				UtilidadesDOM.crearficheroxml(DOM, "DOM_DEL.xml");
			}else {
				System.out.println("No se ha podido eliminar el elemento");
			}	
		} catch (Exception e) {e.printStackTrace();}

	}

}
