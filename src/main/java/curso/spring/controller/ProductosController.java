package curso.spring.controller;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import curso.spring.model.Productos;
import curso.spring.model.UnidadesCarrito;
import curso.spring.model.Valoraciones;
import curso.spring.service.CategoriasService;
import curso.spring.service.ImpuestosService;
import curso.spring.service.ProductosService;
import curso.spring.service.UnidadesCarritoService;
import curso.spring.service.ValoracionesService;
import curso.spring.service.uploadFile.FileUploadUtil;
import curso.spring.service.utils.Util;
import jxl.read.biff.BiffException;

/**
 * Controlador Gestion de pedidos
 * @author Gonzalo
 *
 */
@Controller
@RequestMapping("/product")
public class ProductosController {

	Logger logger = LogManager.getLogger(ProductosController.class);

	@Autowired
	ProductosService productoS;

	@Autowired
	ValoracionesService valServ;
	
	@Autowired
	CategoriasService catS;
	
	@Autowired
	ImpuestosService taxS;
	
	@Autowired
	Util util;

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id) {

		productoS.deleteProduct(id);

		return "redirect:/product/list";
	}
	
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable int id) {

		productoS.darAlta(id);

		return "redirect:/product/list";
	}

	@GetMapping("/edit/{id}")
	public String actualizacionForm(@PathVariable int id, Model model) {

		Productos p = productoS.getProductById(id);
		model.addAttribute("productos", p);
		
		model.addAttribute("list", catS.getAllCategorias());

		model.addAttribute("listTax", taxS.getAllImpuestos());

		

		return "product/edit";
	}

	@PostMapping("/edit/submit")
	public String actualizar(@ModelAttribute Productos producto) {
		productoS.editProduct(producto);
		return "redirect:/product/list";

	}

	@GetMapping("/add")
	public String addProduct(Model model) {

		model.addAttribute("producto", new Productos());
		model.addAttribute("list", catS.getAllCategorias());

		model.addAttribute("listTax", taxS.getAllImpuestos());

		return "product/new";
	}

	@PostMapping("/add/submit")
	public String submitProduct(@ModelAttribute Productos producto, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		producto.setImagen(fileName);
		
		productoS.addProduct(producto);
		
		String uploadDir = "src/main/resources/static/img/productos/";
		 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		model.addAttribute("list", catS.getAllCategorias());

		return "redirect:/product/list";	
	}	

	@GetMapping("/list")
	public String listProducts(Model model) {

		List<Productos> productList = productoS.getAllProducts();

		model.addAttribute("productList", productList);
		model.addAttribute("list", catS.getAllCategorias());


		return "product/list";
	}


	/**
	 * Añade unidad al carrito comprobando stock
	 * @param session
	 * @param model
	 * @param id
	 * @return "redirect:/payment" - carrito
	 */
	@GetMapping("/addCarrito/{id}")
	public String addProductCarrito(HttpSession session, Model model, @PathVariable int id) {

		UnidadesCarritoService uCarritoS = new UnidadesCarritoService();

		UnidadesCarrito linea = new UnidadesCarrito();

		ArrayList<UnidadesCarrito> carrito = (ArrayList<UnidadesCarrito>) session.getAttribute("carrito");

		Productos producto = productoS.getProductById(id);

		if (producto != null) {

			if (productoS.checkStock(producto.getId()) > 0) {

				linea = uCarritoS.getLineaByProduct(carrito, producto);

				if (linea != null) {

					linea.setUnidades(linea.getUnidades()+1);
					linea.setTotal(uCarritoS.calcularTotal(linea));

				} else {

					linea = uCarritoS.crearLineaCarrito(producto);
					carrito.add(linea);
				}
			} else {
				model.addAttribute("mensaje", "Falta Stock para ese producto");
			}
		} 

		session.setAttribute("unitsCarrito", uCarritoS.getUnitsCarrito(carrito));	

		return "redirect:/payment";
	} 
	
	
	@GetMapping("/addCarrito2/{id}")
	public String addProductCarrito2(HttpSession session, Model model, @PathVariable int id) {

		UnidadesCarritoService uCarritoS = new UnidadesCarritoService();

		UnidadesCarrito linea = new UnidadesCarrito();

		ArrayList<UnidadesCarrito> carrito = (ArrayList<UnidadesCarrito>) session.getAttribute("carrito");

		Productos producto = productoS.getProductById(id);

		if (producto != null) {

			if (productoS.checkStock(producto.getId()) > 0) {

				linea = uCarritoS.getLineaByProduct(carrito, producto);

				if (linea != null) {

					linea.setUnidades(linea.getUnidades()+1);
					linea.setTotal(uCarritoS.calcularTotal(linea));

				} else {

					linea = uCarritoS.crearLineaCarrito(producto);
					carrito.add(linea);
				}
			} else {
				model.addAttribute("mensaje", "Falta Stock para ese producto");
			}
		} 

		session.setAttribute("unitsCarrito", uCarritoS.getUnitsCarrito(carrito));	

		return "redirect:/";
	} 



	/**
	 * Eliminar unidad del carrito 
	 * @param session
	 * @param model
	 * @param id
	 * @return index
	 */ 
	@GetMapping("/delCarrito/{id}")
	public String eliminarProductCarrito(HttpSession session, Model model, @PathVariable int id) {

		UnidadesCarritoService uCarritoS = new UnidadesCarritoService();

		UnidadesCarrito linea = new UnidadesCarrito();

		ArrayList<UnidadesCarrito> carrito = (ArrayList<UnidadesCarrito>) session.getAttribute("carrito");

		Productos producto = productoS.getProductoById(id);

		if (producto != null) {

			linea = uCarritoS.getLineaByProduct(carrito, producto);

			if (linea != null) {

				linea.setUnidades(linea.getUnidades()-1);

				if (linea.getUnidades()==0) {

					carrito.remove(linea);

				} else {

					linea.setTotal(uCarritoS.calcularTotal(linea));
				}
			} 
		} 

		int uCart = uCarritoS.getUnitsCarrito(carrito);

		if (uCart == 0) {
			session.setAttribute("carrito", null);
		}

		session.setAttribute("unitsCarrito", uCarritoS.getUnitsCarrito(carrito));	

		return "redirect:/payment";
	} 

	/**
	 * Muestra detalles de Producto
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/detail/{id}")
	public String verDetalle(@PathVariable int id, Model model) {

		Productos producto = new Productos();

		producto = productoS.getProductoById(id);

		model.addAttribute("producto" , producto);

		List<Valoraciones> listVal = valServ.getValoracionesByIdProduct(id);


		int numVal = valServ.getNumValoraciones(listVal);

		if (numVal == 0) {
			model.addAttribute("numVal", numVal);
			return "product/detail";

		}
		int media = valServ.getMediaVal(listVal);
		model.addAttribute("numVal", numVal);
		model.addAttribute("media", media);
		model.addAttribute("listVal", listVal);

		return "product/detail";
	}

	/**
	 * Export excel Productos
	 * @return
	 */
	@GetMapping("/export")
	public String descargarExcel(HttpServletResponse response) {

		productoS.descargarExcelProductos();
		
		File fichero = new File("ficheros/productos.xls");
		
		try {
			util.descargarFichero(response, fichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return "redirect:/product/list";
	}

	/**
	 * Import Excel Productos
	 * @return
	 */
	@PostMapping("/import")
	public String importarExcel(@RequestParam("excel") MultipartFile multipartFile) {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				
		String uploadDir = "ficheros/";
		 
        try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			productoS.importarExcelProductos();
		} catch (BiffException e) {
			logger.error(e+"Error importando excel de productos");
		} catch (IOException e) {
			logger.error(e+"Error importando excel de productos");
		}

		return "redirect:/product/list";
	}

}
