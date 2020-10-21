package com.isistan.despensa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isistan.despensa.dto.DTOFacturaClienteReporte;
import com.isistan.despensa.dto.DTOProductoMasVendido;
import com.isistan.despensa.dto.DTOReporteVentasDia;
import com.isistan.despensa.model.Factura;
import com.isistan.despensa.repository.FacturaRepository;

/**
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia
 * Esta clase se ocupa  de manejar los servicios rest de la clase Factura*/
@RestController
@RequestMapping("facturas")
public class FacturaControllerJPA {

	@Qualifier("facturaRepository")
	@Autowired
	private final FacturaRepository repository;

	/** Constructor que crea el Repository, y carga las facturas con el LoadData*/
	public FacturaControllerJPA(@Qualifier("facturaRepository") FacturaRepository repository) {
		this.repository = null;
	}

	/** Obtener todas las facturas
	 * @return retorna un Iterable<Factura> con todas las facturas*/
	@GetMapping("/") 
	@CrossOrigin
	public ResponseEntity<Iterable<Factura>> getFacturas() { 
		try {
			List<Factura> facturas = repository.findAll();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/** Obtener el reporte de los clientes con el monto total de compras
	 * @return retorna un Iterable<DTOFacturaClienteReporte> con todos los clientes y el monto total de sus compras*/
	@GetMapping("/reporteClientes") 
	@CrossOrigin
	public ResponseEntity<Iterable<DTOFacturaClienteReporte>> getReporteComprasClientes() { 
		try {
			List<DTOFacturaClienteReporte> facturas = repository.getReporteCliente();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/** Obtener reporte con las ventas por dia
	 * @return retorna un Iterable<DTOReporteVentasDia> con la fecha y el monto total de las ventas de ese dia*/
	@GetMapping("/reporteVentasDia") 
	@CrossOrigin
	public ResponseEntity<Iterable<DTOReporteVentasDia>> getReporteVentasDia() { 
		try {
			List<DTOReporteVentasDia> facturas = repository.getReporteVentasPorDia();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/** Obtener reporte con el producto mas vendido
	 * @return retorna un Iterable<DTOProductoMasVendido> con el producto y la cantidad de veces que fue vendido*/
	@GetMapping("/productoMasVendido") 
	@CrossOrigin
	public ResponseEntity<Iterable<DTOProductoMasVendido>> getProductoMasVendido() { 
		try {
			List<DTOProductoMasVendido> facturas = repository.getProductoMasVendido();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/** Inserta una factura en la base de datos 
	 * @param factura objeto Factura con sus elementos para persistir
	 * @return retorna el objeto creado, o en caso contrario retorna null con el status request
	 */@PostMapping("/")
	 @CrossOrigin
	 public ResponseEntity<Factura> newFactura(@RequestBody Factura f) {

		 try {
			 Integer cantCompras = repository.getComprasDiaPorUsuario(f.getCliente().getId(), f.getFecha());
			 if(cantCompras == null) {
				 return new ResponseEntity<>(repository.save(f), HttpStatus.CREATED);
			 } else if(cantCompras < 3 && cantCompras+f.getProductos().size() <=3) {
				 return new ResponseEntity<>(repository.save(f), HttpStatus.CREATED);
			 } else {
				 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			 }
		 } catch (Exception e) {
			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	 
	 /** Elimina una factura de la base de datos
		 * @param identificador de la factura a borrar (id Integer)
		 * @return retorna un String(mensaje) si la factura fue eliminada o si no se pudo eliminar con el status request
		 * */
	 @DeleteMapping("/{id}")
	 @CrossOrigin
	 public ResponseEntity<String> dropFactura(@PathVariable Integer id) { 
		 try {
			 if(repository.existsById(id)) {
				 repository.deleteById(id);
				 return new ResponseEntity<>("Se elimino la factura",HttpStatus.OK);
			 } else {
				 return new ResponseEntity<>("La factura no existe o no se pudo eliminar",HttpStatus.NOT_MODIFIED);
			 }
		 } catch (Exception e) {
			 return new ResponseEntity<>("La factura no existe o no se pudo eliminar",HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	 
	 /** Actualiza una factura de la base de datos
		 * @param identificador de la factura a actualizar (id Integer)
		 * @return retorna el objeto modificado, en caso contrario retorna un status request
		 * */
	 @PutMapping("/{id}")
	 @CrossOrigin
	 public ResponseEntity<Factura> updateFactura(@RequestBody Factura f, @PathVariable Integer id) { 
		 if (repository.existsById(id)) {
			 return new ResponseEntity<>(repository.save(f), HttpStatus.OK);
		 } else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
	 }


}
