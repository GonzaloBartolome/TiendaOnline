package curso.spring.service.facturas;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import curso.spring.model.DetallesPedido;
import curso.spring.model.Pedidos;

public class EscribirPDF {

	/**
	 * Escribir pdf
	 * @param args
	 */
	public void escribirPDF(Pedidos pedido, List<DetallesPedido> list) {
		// TODO Auto-generated method stub
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		
	    try {      
	    	//Obtenemos la instancia del archivo a utilizar
	    	//writer = PdfWriter.getInstance(documento, new FileOutputStream("/Users/Newland/eclipse-spring/TiendaOnline_Gonzalo_Bartolome_Boya7/factura/factura.pdf"));
	    	
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream("factura/factura.pdf"));
		    //Para insertar cabeceras/pies en todas las p�ginas
	    	writer.setPageEvent(new PDFHeaderFooter());
	        
		    //Abrimos el documento para edici�n
		    documento.open();
		    
		    //PARRAFOS
			Paragraph paragraph = new Paragraph();
			//String contenido = "esto es un p�rrafo";
			//paragraph.setSpacingBefore(100);
			paragraph.add("\n\n");
			//String font = "Sans";
			//float tamanno = 11;
			//int style = Font.BOLD;
			//BaseColor color = BaseColor.BLACK;
			//float spacBefore = 0;
			//float spacAfter = 5;
		    
			//paragraph.setAlignment(Element.ALIGN_CENTER);
		    //paragraph.setFont(new Font(FontFactory.getFont(font, tamanno, style, color)));
		    //paragraph.add("esto es una p�rrafo");
		    //paragraph.setSpacingBefore(spacBefore);
		    //paragraph.setSpacingAfter(spacAfter);
			
			String numFactura = "Nº Factura: "+ pedido.getNumFactura().toString() + "\n\n";
			paragraph.add(numFactura);
			
			

			String dni = "";
	    	if(pedido.getUsuario().getDni()!=null) dni = pedido.getUsuario().getDni();
	    	
	    	paragraph.add(pedido.getUsuario().getNombre() + "\n" + pedido.getUsuario().getApellido1() + " " + pedido.getUsuario().getApellido2()+ "\n" + dni + "\n");
		    
	    	String direccion = "";
	    	if(pedido.getUsuario().getDireccion()!=null) direccion += "\n" + pedido.getUsuario().getDireccion() + "\n";
	    	if(pedido.getUsuario().getLocalidad()!=null) direccion += pedido.getUsuario().getLocalidad() + " ";
	    	if(pedido.getUsuario().getProvincia()!=null) direccion += pedido.getUsuario().getProvincia() + " ";
	    	if(pedido.getUsuario().getTelefono()!=null) direccion += "- " + pedido.getUsuario().getTelefono() + "\n\n\n\n";
	    	
	    	
	    	paragraph.add(direccion);
	    	
	    
			
			
	    	documento.add(paragraph);

	    	
			

	    	//TABLAS
		    
			//Instanciamos una tabla de X columnas
		    PdfPTable tabla = new PdfPTable(4);
		    
		    //Ancho de cada columna
	        int[] values = new int[]{160,80,80,80};
	        tabla.setWidths(values);
		    //tabla.setWidthPercentage(new Float(100));
		    
		    //Phrase phrase = new Phrase("contenido de la celda", 
		    //new Font(FontFactory.getFont("Sans", 9, Font.BOLD, BaseColor.BLACK)));
		    //Phrase phrase = new Phrase("contenido de la celda");
		    Phrase texto = new Phrase("Producto");
		    Phrase texto2 = new Phrase("Precio");
		    Phrase texto3 = new Phrase("Unidades");
		    Phrase texto4 = new Phrase("Total");

			PdfPCell cabecera = new PdfPCell(texto);
			PdfPCell cabecera2 = new PdfPCell(texto2);
			PdfPCell cabecera3 = new PdfPCell(texto3);
			PdfPCell cabecera4 = new PdfPCell(texto4);

			
			//Propiedades concretas de formato
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
			
			cabecera3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera3.setBorderWidth(1);
			
			cabecera4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera4.setBorderWidth(1);
		    //celda.setBorderColor(BaseColor.WHITE);
			//cabecera.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		    //celda.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		    //celda.setPaddingBottom(3);
		    
			
			
			
		    tabla.addCell(cabecera);
		    tabla.addCell(cabecera2);
		    tabla.addCell(cabecera3);		   
		    tabla.addCell(cabecera4);
		    
		    Double total = new Double(0);
		    
		    for (DetallesPedido detalle : list) {
		    tabla.addCell(detalle.getProducto().getNombre());
		    tabla.addCell(detalle.getPrecioUnidad().toString() + " €");
		    tabla.addCell(detalle.getUnidades().toString());
		    tabla.addCell(detalle.getTotal().toString() + " €");
		    }
		    
		    tabla.addCell("");
		    tabla.addCell("");
		    tabla.addCell("");
		    tabla.addCell("");
		    
		    tabla.addCell(""); tabla.addCell(""); tabla.addCell(""); tabla.addCell("Total: " + pedido.getTotal() + " €");
		    
		    
		    
		    //documento.add(tabla);
		    
		    /*
		    PdfPTable tabla = new PdfPTable(4);
		    //tabla.addCell(cabecera);
		    //tabla.addCell(cabecera);
		    //tabla.addCell(cabecera);
		    
		    //tabla.addCell(celda);
		    
		    tabla.addCell(new PdfPCell(new Phrase("1")));
		    tabla.addCell(new PdfPCell(new Phrase("2")));
		    tabla.addCell(new PdfPCell(new Phrase("3")));
		    tabla.addCell(new PdfPCell(new Phrase("4")));
		    
		    //tabla.addCell(new PdfPCell(new Phrase("contenido de la celda")));
		    //tabla.addCell(celda);
		    //tabla.addCell(celda);
		    //tabla.completeRow();
		    //tabla.addCell(celda);
*/
		    documento.add(tabla);
		    
		    Paragraph paragraph2 = new Paragraph();
			//String contenido = "esto es un p�rrafo";
			//paragraph.setSpacingBefore(100);
			paragraph2.add("\n\n\n\n");
			
			documento.add(paragraph2);
			
			
		    PdfPTable tabla2 = new PdfPTable(2);
		    
		    //Ancho de cada columna
	        int[] values2 = new int[]{160,80};
	        tabla2.setWidths(values2);
		    //tabla.setWidthPercentage(new Float(100));
		    
		    //Phrase phrase = new Phrase("contenido de la celda", 
		    //new Font(FontFactory.getFont("Sans", 9, Font.BOLD, BaseColor.BLACK)));
		    //Phrase phrase = new Phrase("contenido de la celda");
		    Phrase text = new Phrase("Metodo de Pago");
		    Phrase text2 = new Phrase("Total");
		  
			PdfPCell header = new PdfPCell(text);
			PdfPCell header2 = new PdfPCell(text2);
		    
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(1);
			
			header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header2.setBorderWidth(1);
			
			tabla2.addCell(header);
			tabla2.addCell(header2);
		    
			tabla2.addCell("* " + pedido.getMetodoPago().getMetodoPago().toString());
			tabla2.addCell(pedido.getTotal().toString());
		    
		    
		    documento.add(tabla2);
			
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
			
	    } catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	    }
	    
	   /*try {
	        File path = new File("/Users/Newland/eclipse-spring/TiendaOnline_Gonzalo_Bartolome_Boya4/factura/factura.pdf");
	        Desktop.getDesktop().open(path);
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    */
	}
	
	
	
}
