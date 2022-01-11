package curso.spring.service.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class Util {

	public ResourceBundle cargarIdioma(String code) {

		Locale locale = new Locale(code);
		ResourceBundle rb = ResourceBundle.getBundle("idioma", locale);

		return rb;
	}
	
	public void descargarFichero(HttpServletResponse response, File fichero) throws IOException {
		

		if (fichero.exists()) {

			//get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(fichero.getName());
			if (mimeType == null) {
				//unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			/**
			 * In a regular HTTP response, the Content-Disposition response header is a
			 * header indicating if the content is expected to be displayed inline in the
			 * browser, that is, as a Web page or as part of a Web page, or as an
			 * attachment, that is downloaded and saved locally.
			 * 
			 */

			/**
			 * Here we have mentioned it to show inline
			 */
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fichero.getName() + "\""));

			 //Here we have mentioned it to show as attachment
			 //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) fichero.length());

			InputStream inputStream = null;
		
			inputStream = new BufferedInputStream(new FileInputStream(fichero));
			
			FileCopyUtils.copy(inputStream, response.getOutputStream());
			
		}
	}	
}
