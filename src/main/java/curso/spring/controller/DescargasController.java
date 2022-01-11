package curso.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import curso.spring.service.uploadFile.FileUploadUtil;

@Controller
public class DescargasController {
	
	
    @RequestMapping(value = "/download/pdf", method = RequestMethod.GET)
    public StreamingResponseBody getPDF(HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"factura.pdf\"");
        InputStream inputStream = new FileInputStream(new File("factura/factura.pdf"));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
    }
    
    
    @RequestMapping(value = "/download/excel", method = RequestMethod.GET)
    public StreamingResponseBody getExcel(HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"productos.xls\"");
        InputStream inputStream = new FileInputStream(new File("ficheros/productos.xls"));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
    }
    
    @RequestMapping(value = "/import/excel", method = RequestMethod.POST)
    public String importExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {

    	 String uploadDir = "ficheros/";
    	 
    	 FileUploadUtil.saveFile(uploadDir, "products.xls", multipartFile);
		
    	 return "redirect:/product/list";
    }
    
    
    
    
    
    
   
    
}	

