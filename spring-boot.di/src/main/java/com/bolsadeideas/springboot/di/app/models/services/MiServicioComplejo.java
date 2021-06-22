package com.bolsadeideas.springboot.di.app.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component("miServicioComplejo")
//@Primary

public class MiServicioComplejo implements IServicio{

	public String operacion() {
		return "ejecutando algún proceso complicado...";
	}
}
