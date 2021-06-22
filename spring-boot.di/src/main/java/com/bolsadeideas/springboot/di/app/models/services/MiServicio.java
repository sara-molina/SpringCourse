package com.bolsadeideas.springboot.di.app.models.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component("miServicioSimple")
public class MiServicio implements IServicio{

	public String operacion() {
		return "ejecutando algún proceso...";
	}
}
