package com.bolsadeideas.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.services.IServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicioComplejo;

@Configuration
public class AppConfig {
	
	@Bean("miServicioSimple")
	public IServicio registrarMiServicio() {
		return new MiServicio();
	}
	
	@Primary
	@Bean("miServicioComplejo")
	public IServicio registrarMiServicioComplejo() {
		return new MiServicioComplejo();
	}
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems(){
		Producto producto1 = new Producto("Cámara", 100);
		Producto producto2 = new Producto("Bici", 200);
		
		ItemFactura linea1= new ItemFactura(producto1, 2);
		ItemFactura linea2= new ItemFactura(producto2, 4);
		
		return Arrays.asList(linea1,linea2);
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina(){
		Producto producto1 = new Producto("Monitor LG", 400);
		Producto producto2 = new Producto("Notebook", 500);
		Producto producto3 = new Producto("impresora HP", 500);
		Producto producto4 = new Producto("Escritorio", 500);
		
		ItemFactura linea1= new ItemFactura(producto1, 1);
		ItemFactura linea2= new ItemFactura(producto2, 2);
		ItemFactura linea3= new ItemFactura(producto3, 1);
		ItemFactura linea4= new ItemFactura(producto4, 2);
		
		return Arrays.asList(linea1,linea2,linea3,linea4);
	}
}
