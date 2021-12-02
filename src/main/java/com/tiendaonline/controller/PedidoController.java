package com.tiendaonline.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaonline.entity.Productos;
import com.tiendaonline.entity.UnidadesCarrito;
import com.tiendaonline.service.PedidoService;
import com.tiendaonline.service.ProductosService;
import com.tiendaonline.service.UnidadesCarritoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoS;


}
