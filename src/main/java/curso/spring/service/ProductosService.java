package curso.spring.service;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import curso.spring.model.Productos;
import curso.spring.model.Usuarios;
import curso.spring.repository.ProductosRepository;
import curso.spring.service.uploadFile.FileUploadUtil;

@Service
public class ProductosService {

	Logger logger = LogManager.getLogger(ProductosService.class);

	 Date date = new Date();
	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	 
	@Autowired
	private ProductosRepository productRepo;

	@Autowired
	private CategoriasService cs;
	
	@Autowired
	private ImpuestosService is;
	
	

	public void editProduct(Productos producto) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse("0000-00-00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		producto.setFechaBaja(d);
		producto.setFechaAlta(date);
		productRepo.save(producto);
	}

	/**
	 * Da de baja producto
	 * @param id
	 */
	public void deleteProduct(Integer id) {

		Productos p = productRepo.getById(id);
		p.setBaja(true);
		p.setFechaBaja(date);
		productRepo.save(p);
		
	}
	
	/**
	 * Dar de Alta producto fuera de catalogo
	 * @param id
	 */
	public void darAlta(int id) {
		Productos p = productRepo.getById(id);
		p.setBaja(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse("0000-00-00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		p.setFechaBaja(date);
		productRepo.save(p);		
	}
 

	public void addProduct(Productos producto) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date d = null;
		try {
			d = sdf.parse("00-00-0000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		producto.setFechaBaja(d);
		producto.setFechaAlta(date);
		productRepo.save(producto);
	}

	public List<Productos> getAllProducts(){

		List<Productos> listProducts = productRepo.findAll();

		return listProducts;
	}
	
	public List<Productos> getAllProductsCatalog(){

		List<Productos> listProducts = productRepo.getAllProductsCatalog();

		return listProducts;
	}


	public Productos getProductoById(Integer id) {

		return productRepo.getById(id);

	}
	
	//Con findById
	public Productos getProductById(int id) {

		return productRepo.findById(id);

	}


	//Buscar
	public List<Productos> getProductsByCat(Integer id_categoria) {

		List<Productos> listProducts = productRepo.getProductsByCategoria(id_categoria);

		return listProducts;

	}
	
	public List<Productos> getProductsByPrecio(Double precio, Double precio2) {

		List<Productos> listProducts = productRepo.getProductsByPrecio(precio, precio2);

		return listProducts;

	}
	
	

	// Operacion payment actualizar stock
	public void actualizarStockCompra(Integer id, Integer unidades) {
		
		Productos producto = getProductoById(id);
		
		producto.setStock(producto.getStock() - unidades);
		try {
			editProduct(producto);

		} catch (Exception e) {
			logger.error(e + "Error actualizando stock Producto");
		}
	}
	
	public void actualizarStockCancelacion(Integer id, Integer unidades) {
		
		Productos producto = getProductoById(id);
		
		producto.setStock(producto.getStock() + unidades);
		
		try {
			editProduct(producto);
		} catch (Exception e) {
			logger.error(e + "Error actualizando stock Producto tras cancelación");
		}
		
	}

	

	//Excel
	/**
	 * Descargar Excel con los productos
	 */
	public void descargarExcelProductos() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  

		List<Productos> list = getAllProducts();

		//File fichero = new File("src/main/resources/static/ficheros/productos.xls");
		File fichero = new File("ficheros/productos.xls");

		try {
			WritableWorkbook w = Workbook.createWorkbook(fichero);

			//Nombre de la hoja
			WritableSheet sheet = w.createSheet("Productos", 0);

			//columna fila contenido

			jxl.write.Label id = new jxl.write.Label(0, 0, "Id Producto");
			sheet.addCell(id);

			jxl.write.Label nombre = new jxl.write.Label(1, 0, "Nombre");
			sheet.addCell(nombre);

			jxl.write.Label idCat = new jxl.write.Label(2, 0, "Id Categoria");
			sheet.addCell(idCat);

			jxl.write.Label descripcion = new jxl.write.Label(3, 0, "Descripción");
			sheet.addCell(descripcion);

			jxl.write.Label precio = new jxl.write.Label(4, 0, "Precio");
			sheet.addCell(precio);
			
			jxl.write.Label unidades = new jxl.write.Label(5, 0, "Stock");
			sheet.addCell(unidades);

			jxl.write.Label fechaAlta = new jxl.write.Label(6, 0, "Fecha Alta");
			sheet.addCell(fechaAlta);

			jxl.write.Label imagen = new jxl.write.Label(7, 0, "Imagen");
			sheet.addCell(imagen);
			

			int row = 1;

			for (Productos producto : list) {


				int id_product = producto.getId();
				jxl.write.Number idp = new jxl.write.Number(0, row, id_product);
				sheet.addCell(idp);

				jxl.write.Label name = new jxl.write.Label(1, row, producto.getNombre());
				sheet.addCell(name);

				jxl.write.Number idCatg = new jxl.write.Number(2, row, producto.getCategoria().getId());
				sheet.addCell(idCatg);

				jxl.write.Label description = new jxl.write.Label(3, row, producto.getDescripcion());
				sheet.addCell(description);

				jxl.write.Number price = new jxl.write.Number(4, row, producto.getPrecio());
				sheet.addCell(price);

				jxl.write.Number stock = new jxl.write.Number(5, row, producto.getStock());
				sheet.addCell(stock);

				jxl.write.Label dateAlta = new jxl.write.Label(6, row, producto.getFechaAlta().toString() );
				sheet.addCell(dateAlta);

				jxl.write.Label image = new jxl.write.Label(7, row, producto.getImagen());
				sheet.addCell(image);

				row++;

			}

			w.write();
			w.close();
		

		} catch (Exception e) {
			logger.error(e + "Error Exportar Excel");
		}

	}

	/**
	 * Importar Excel con productos y añadir a Base de Datos
	 * @throws BiffException
	 * @throws IOException
	 */
	public void importarExcelProductos() throws BiffException, IOException {
		

		Productos producto = new Productos();
		File fichero = new File("ficheros/products.xls");

		Workbook w = Workbook.getWorkbook(fichero);
    	
    	//Se lee la primera hoja de la excel
    	Sheet sheet = w.getSheet(0);

    	
    	
    	 
    	Date fechaAlta = null; 
    	
    	

		for (int f=1; f<sheet.getRows(); f++) {
			//String contenido = " ";
	    	List<Object> list = new ArrayList <Object>();

			for(int c=0;c<sheet.getColumns();c++) {
				
				list.add(sheet.getCell(c,f).getContents());
				
			}
			
			producto.setId(0);
			producto.setNombre((String) list.get(1));
			producto.setCategoria(cs.getCategoriaById(Integer.parseInt(list.get(2).toString())));
			//producto.setId_categoria(Integer.parseInt(list.get(2).toString()));
			producto.setDescripcion((String) list.get(3));
			producto.setPrecio(Double.parseDouble(list.get(4).toString()));
			producto.setStock(Integer.parseInt(list.get(5).toString()));
			producto.setFechaAlta(fechaAlta);
			
			String sDate1="0000-00-00";  
		    Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			producto.setFechaBaja(date1);		
			producto.setImagen((String) list.get(7));
			producto.setBaja(false);
			
			producto.setImpuesto(is.getImpuestoById(1));
			
			addProduct(producto);
		}


	}
	/**
	 * Selecciona el producto por la busqueda del usuario
	 * @param keyword
	 * @return
	 */
    public List<Productos> searchByKeyword(String keyword){
    	List<Productos> producto = productRepo.search(keyword);
    	return producto;

    }
    
    public int checkStock(int id_producto){

    	return productRepo.checkStock(id_producto);
    }

    
	
}
