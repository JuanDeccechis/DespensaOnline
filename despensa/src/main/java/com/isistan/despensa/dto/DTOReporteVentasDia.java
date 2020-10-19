package com.isistan.despensa.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public interface DTOReporteVentasDia {
	
	 Date  getFecha();

	 double getTotal();

}