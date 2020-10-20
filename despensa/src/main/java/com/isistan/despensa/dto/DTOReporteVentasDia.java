package com.isistan.despensa.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public interface DTOReporteVentasDia {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
	 Date  getFecha();

	 Float getTotal();

}