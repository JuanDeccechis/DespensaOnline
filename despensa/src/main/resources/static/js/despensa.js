"use strict";
let formHTML;
let tableHTML;
let baseUrl = "http://localhost:8080/";

document.querySelector("#clientes").addEventListener("click", crearContenidoClientes);
document.querySelector("#facturas").addEventListener("click", crearContenidoFactura);
document.querySelector("#productos").addEventListener("click", crearContenidoProducto);
//document.querySelector("#relaciones").addEventListener("click", crearContenidoRelaciones);

formHTML = document.querySelector("#form");
tableHTML = document.querySelector("#table");


// crea el formulario y el thead de estudiantes
function crearContenidoFactura() {
    formHTML.innerHTML = '';

    let campoId = document.createElement("input");
     let campoFecha = document.createElement("input");
     let campoCliente = document.createElement("input");
     let campoProducto1 = document.createElement("input");
     let campoProducto2 = document.createElement("input");
     let campoProducto3 = document.createElement("input");
    //campoId
    campoId.setAttribute('id', 'idFactura');
    campoId.setAttribute('value', '');
    campoId.setAttribute('placeHolder', 'id Factura');
    formHTML.appendChild(campoId);
    //campoFecha
    campoFecha.setAttribute('id', 'Fecha');
    campoFecha.setAttribute('value', '');
    campoFecha.setAttribute('type', 'date');
    formHTML.appendChild(campoFecha);

     campoCliente.setAttribute('id', 'idCliente');
     campoCliente.setAttribute('value', '');
     campoCliente.setAttribute('placeholder', 'id cliente');
    formHTML.appendChild(campoCliente);
    campoProducto1.setAttribute('id', 'idProducto1');
     campoProducto1.setAttribute('value', '');
     campoProducto1.setAttribute('placeholder', 'id producto 1');
    formHTML.appendChild(campoProducto1);
    campoProducto2.setAttribute('id', 'idProducto2');
    campoProducto2.setAttribute('value', '');
    campoProducto2.setAttribute('placeholder', 'id producto 2');
    formHTML.appendChild(campoProducto2);
    campoProducto3.setAttribute('id', 'idProducto3');
    campoProducto3.setAttribute('value', '');
    campoProducto3.setAttribute('placeholder', 'id producto 3');
    formHTML.appendChild(campoProducto3);

    let botonPost = document.createElement("button");
    botonPost.setAttribute('class', 'btn-primary');
    botonPost.setAttribute('id', 'postEstudiante');
    botonPost.addEventListener("click", addFactura);
    botonPost.textContent = 'agregar factura';
    formHTML.appendChild(botonPost);

    let botonUpdate = document.createElement("button");
    botonUpdate.setAttribute('class', 'btn-primary');
    botonUpdate.setAttribute('id', 'getFactura');
    botonUpdate.addEventListener("click", updateFactura);
    botonUpdate.textContent = 'actualizar factura';
    formHTML.appendChild(botonUpdate);


    let botonGet = document.createElement("button");
    botonGet.setAttribute('class', 'btn-primary');
    botonGet.setAttribute('id', 'getEstudiantes');
    botonGet.addEventListener("click", getFacturas);
    botonGet.textContent = 'listar facturas';
    formHTML.appendChild(botonGet);

    let botonDelete = document.createElement("button");
    botonDelete.setAttribute('class', 'btn-danger');
    botonDelete.setAttribute('id', 'deleteFactura');
    botonDelete.addEventListener("click", deleteFactura);
    botonDelete.textContent = 'borrar factura por ID';
    formHTML.appendChild(botonDelete);

    let botonGetReporteVentasDia = document.createElement("button");
  botonGetReporteVentasDia.setAttribute('class', 'btn-primary');
  botonGetReporteVentasDia.setAttribute('id', 'getReporte');
  botonGetReporteVentasDia.addEventListener("click", getReporteVentasDia);
  botonGetReporteVentasDia.textContent = 'generar reporte ventas diarias';
  formHTML.appendChild(botonGetReporteVentasDia);

;

    // colThead.appendChild(colTr);
     let colTbody = document.createElement("tbody");
    // tableHTML.innerHTML = '';
    // tableHTML.appendChild(colThead);
     tableHTML.appendChild(colTbody);
}

//crea el formulario y el thead de clientes
function crearContenidoClientes() {
    formHTML.innerHTML = '';
//campoId
    let campoId = document.createElement("input");
    campoId.setAttribute('id', 'idCliente');
    campoId.setAttribute('value', '');
    campoId.setAttribute('placeHolder', 'id cliente');
    formHTML.appendChild(campoId);
    let campoNombre = document.createElement("input");
    campoNombre.setAttribute('id', 'nombreCliente');
    campoNombre.setAttribute('value', '');
    campoNombre.setAttribute('placeHolder', 'nombre cliente');
    formHTML.appendChild(campoNombre);


    let campoApellido = document.createElement("input");
    campoApellido.setAttribute('id', 'apellidoCliente');
    campoApellido.setAttribute('value', '');
    campoApellido.setAttribute('placeHolder', 'apellido cliente');
    formHTML.appendChild(campoApellido);


    let campoDni = document.createElement("input");
    campoDni.setAttribute('id', 'dniCliente');
    campoDni.setAttribute('value', '');
    campoDni.setAttribute('placeHolder', 'dni cliente');
    formHTML.appendChild(campoDni);

    let botonPost = document.createElement("button");
    botonPost.setAttribute('class', 'btn-primary');
    botonPost.setAttribute('id', 'postCliente');
    botonPost.addEventListener("click", addCliente);
    botonPost.textContent = 'agregar cliente';
    formHTML.appendChild(botonPost);

    let botonGet = document.createElement("button");
    botonGet.setAttribute('class', 'btn-primary');
    botonGet.setAttribute('id', 'getClientes');
    botonGet.addEventListener("click", getClientes);
    botonGet.textContent = 'listar clientes';
    formHTML.appendChild(botonGet);

    let botonDelete = document.createElement("button");
    botonDelete.setAttribute('class', 'btn-danger');
    botonDelete.setAttribute('id', 'deleteCliente');
    botonDelete.addEventListener("click", deleteCliente);
    botonDelete.textContent = 'borrar cliente por ID';
    formHTML.appendChild(botonDelete);

    let botonUpdate = document.createElement("button");
    botonUpdate.setAttribute('class', 'btn-primary');
    botonUpdate.setAttribute('id', 'getClientes');
    botonUpdate.addEventListener("click", updateCliente);
    botonUpdate.textContent = 'actualizar cliente';
    formHTML.appendChild(botonUpdate);
    //getReporte
    let botonGetReporte = document.createElement("button");
    botonGetReporte.setAttribute('class', 'btn-primary');
    botonGetReporte.setAttribute('id', 'getReporte');
    botonGetReporte.addEventListener("click", getReporteTotalCliente);
    botonGetReporte.textContent = 'generar reporte cliente';
    formHTML.appendChild(botonGetReporte);

    let colThead = document.createElement("thead");
    let colTr = document.createElement("tr");
    let colThID = document.createElement("th");
    let contenido = document.createTextNode("ID");
    colThID.appendChild(contenido);
    colTr.appendChild(colThID);

    let colThNombre = document.createElement("th");
    contenido = document.createTextNode("Nombre");
    colThNombre.appendChild(contenido);
    colTr.appendChild(colThNombre);

    let colThApellido = document.createElement("th");
    contenido = document.createTextNode("Apellido");
    colThApellido.appendChild(contenido);
    colTr.appendChild(colThApellido);

    let colThDni = document.createElement("th");
    contenido = document.createTextNode("Dni");
    colThDni.appendChild(contenido);
    colTr.appendChild(colThDni);
    colThead.appendChild(colTr);

    let colTbody = document.createElement("tbody");
    colTbody.setAttribute("id", "tbody");
    tableHTML.innerHTML = '';
    tableHTML.appendChild(colThead);
    tableHTML.appendChild(colTbody);
}

//crear contenido producto
function crearContenidoProducto(){
    formHTML.innerHTML = '';

    let campoId = document.createElement("input");
    campoId.setAttribute('id', 'idProducto');
    campoId.setAttribute('value', '');
    campoId.setAttribute('placeHolder', 'id producto');
    formHTML.appendChild(campoId);

    let campoNombre = document.createElement("input");
    campoNombre.setAttribute('id', 'nombreProducto');
    campoNombre.setAttribute('value', '');
    campoNombre.setAttribute('placeHolder', 'nombre producto');
    formHTML.appendChild(campoNombre);

    let campoDescripcion = document.createElement("input");
    campoDescripcion.setAttribute('id', 'descripcion');
    campoDescripcion.setAttribute('value', '');
    campoDescripcion.setAttribute('placeHolder', 'descripcion producto');
    formHTML.appendChild(campoDescripcion);

    let campoCantidad = document.createElement("input");
    campoCantidad.setAttribute('id', 'cantidad');
    campoCantidad.setAttribute('value', '');
    campoCantidad.setAttribute('placeHolder', 'cantidad');
    formHTML.appendChild(campoCantidad);
    let campoPrecio = document.createElement("input");
    campoPrecio.setAttribute('id', 'precio');
    campoPrecio.setAttribute('value', '');
    campoPrecio.setAttribute('placeHolder', 'precio');
    formHTML.appendChild(campoPrecio);

    let botonPost = document.createElement("button");
    botonPost.setAttribute('class', 'btn-primary');
    botonPost.setAttribute('id', 'postProducto');
    botonPost.addEventListener("click", addProducto);
    botonPost.textContent = 'agregar producto';
    formHTML.appendChild(botonPost);

    let botonGet = document.createElement("button");
    botonGet.setAttribute('class', 'btn-primary');
    botonGet.setAttribute('id', 'getProductos');
    botonGet.addEventListener("click", getProductos);
    botonGet.textContent = 'listar productos';
    formHTML.appendChild(botonGet);

    let botonDelete = document.createElement("button");
    botonDelete.setAttribute('class', 'btn-danger');
    botonDelete.setAttribute('id', 'deleteProductos');
    botonDelete.addEventListener("click", deleteProducto);
    botonDelete.textContent = 'borrar productos por ID';
    formHTML.appendChild(botonDelete);

    let botonUpdate = document.createElement("button");
    botonUpdate.setAttribute('class', 'btn-primary');
    botonUpdate.setAttribute('id', 'getProductos');
    botonUpdate.addEventListener("click", updateProducto);
    botonUpdate.textContent = 'actualizar producto';
    formHTML.appendChild(botonUpdate);
    //apartir de aca genera la tabla
    let colThead = document.createElement("thead");
    let colTr = document.createElement("tr");
    let colThID = document.createElement("th");
    let contenido = document.createTextNode("ID");
    colThID.appendChild(contenido);
    colTr.appendChild(colThID);

    let botonGetReporteProductoMasVendido= document.createElement("button");
    botonGetReporteProductoMasVendido.setAttribute('class', 'btn-primary');
    botonGetReporteProductoMasVendido.setAttribute('id', 'getReporte');
    botonGetReporteProductoMasVendido.addEventListener("click", getReporteProductoMasVendido);
    botonGetReporteProductoMasVendido.textContent = 'generar reporte producto mas vendido';
    formHTML.appendChild(botonGetReporteProductoMasVendido);

    let colThNombre = document.createElement("th");
    contenido = document.createTextNode("NombreProducto");
    colThNombre.appendChild(contenido);
    colTr.appendChild(colThNombre);

    let colThDescr = document.createElement("th");
    contenido = document.createTextNode("Descripcion");
    colThDescr.appendChild(contenido);
    colTr.appendChild(colThDescr);

    let colThCant = document.createElement("th");
    contenido = document.createTextNode("Cantidad");
    colThCant.appendChild(contenido);
    colTr.appendChild(colThCant);
    colThead.appendChild(colTr);

    let colThPrecio = document.createElement("th");
    contenido = document.createTextNode("Precio");
    colThPrecio.appendChild(contenido);
    colTr.appendChild(colThPrecio);
    colThead.appendChild(colTr);

    let colTbody = document.createElement("tbody");
    colTbody.setAttribute("id", "tbody");
    tableHTML.innerHTML = '';
    tableHTML.appendChild(colThead);
    tableHTML.appendChild(colTbody);
}

function crearContenidoRelacionesReporteTotalCliente() {
    let colThead = document.createElement("thead");
    let colTr = document.createElement("tr");
  
    let colThId = document.createElement("th");
    let contenido = document.createTextNode("ID");
    colThId.appendChild(contenido);
    colTr.appendChild(colThId);
  
    let colThNombre = document.createElement("th");
    contenido = document.createTextNode("Nombre");
    colThNombre.appendChild(contenido);
    colTr.appendChild(colThNombre);
  
    let colThApellido = document.createElement("th");
    contenido = document.createTextNode("Apellido");
    colThApellido.appendChild(contenido);
    colTr.appendChild(colThApellido);
  
    let colThDni= document.createElement("th");
    contenido = document.createTextNode("DNI");
    colThDni.appendChild(contenido);
    colTr.appendChild(colThDni);
  
    let colThInCantidad = document.createElement("th");
    contenido = document.createTextNode("Total");
    colThInCantidad.appendChild(contenido);
    colTr.appendChild(colThInCantidad);
  
    colThead.appendChild(colTr);
    let colTbody = document.createElement("tbody");
    tableHTML.innerHTML = '';
    tableHTML.appendChild(colThead);
    tableHTML.appendChild(colTbody);
  }

  function crearContenidoRelacionesReporteProductoMasVendido() {
    let colThead = document.createElement("thead");
    let colTr = document.createElement("tr");
    let colThID = document.createElement("th");
    let contenido = document.createTextNode("ID");
    colThID.appendChild(contenido);
    colTr.appendChild(colThID);
  
    let colThNombre = document.createElement("th");
    contenido = document.createTextNode("NombreProducto");
    colThNombre.appendChild(contenido);
    colTr.appendChild(colThNombre);
  
    let colThDescr = document.createElement("th");
    contenido = document.createTextNode("Descripcion");
    colThDescr.appendChild(contenido);
    colTr.appendChild(colThDescr);
  
    let colThCant = document.createElement("th");
    contenido = document.createTextNode("Stock");
    colThCant.appendChild(contenido);
    colTr.appendChild(colThCant);
    colThead.appendChild(colTr);
  
    let colThPrecio = document.createElement("th");
    contenido = document.createTextNode("Precio");
    colThPrecio.appendChild(contenido);
    colTr.appendChild(colThPrecio);
    colThead.appendChild(colTr);
  
    let colThVendido = document.createElement("th");
    contenido = document.createTextNode("Cant Ventas");
    colThVendido.appendChild(contenido);
    colTr.appendChild(colThVendido);
    colThead.appendChild(colTr);
  
    let colTbody = document.createElement("tbody");
    colTbody.setAttribute("id", "tbody");
    tableHTML.innerHTML = '';
    tableHTML.appendChild(colThead);
    tableHTML.appendChild(colTbody);
  }

  function crearContenidoRelacionesReporteVentasDia() {
    let colThead = document.createElement("thead");
    let colTr = document.createElement("tr");
  
    let colThFecha = document.createElement("th");
    let contenido = document.createTextNode("Fecha");
    colThFecha.appendChild(contenido);
    colTr.appendChild(colThFecha);
  
    let colThTotal = document.createElement("th");
    contenido = document.createTextNode("Total");
    colThTotal.appendChild(contenido);
    colTr.appendChild(colThTotal);
  
    colThead.appendChild(colTr);
    let colTbody = document.createElement("tbody");
    tableHTML.innerHTML = '';
    tableHTML.appendChild(colThead);
    tableHTML.appendChild(colTbody);
  }


//pedido REST para obtener los clientes
function getClientes() {
    let url = baseUrl + "clientes/";
    fetch(url)
    .then(res => res.json())
    .then(datos => {
        console.log(datos);
        setTablaClientes(datos)
    })
}

function getFacturas() {
    let url = baseUrl + "facturas/";
    fetch(url)
    .then(res => res.json())
    .then(datos => {
        console.log(datos);
        setTablaFacturas(datos)
    })
}

function getReporteTotalCliente() {
    crearContenidoRelacionesReporteTotalCliente()
    let url = baseUrl + "facturas/reporteClientes/";
    fetch(url)
    .then(res => res.json())
    .then(datos => {
        setTablaMatriculasReporteClientesTotal(datos)
    })
}
function getReporteProductoMasVendido() {
    crearContenidoRelacionesReporteProductoMasVendido()
    let url = baseUrl + "facturas/productoMasVendido/";
    fetch(url)
    .then(res => res.json())
    .then(datos => {
      setTablaMatriculasReporteProductoMasVendido(datos)
    })
  }
  function getReporteVentasDia() {
    crearContenidoRelacionesReporteVentasDia()
    let url = baseUrl + "facturas/reporteVentasDia/";
    fetch(url)
    .then(res => res.json())
    .then(datos => {
      setTablaMatriculasReporteVentasDia(datos)
    })
  }
//pedido REST para obtener los productos
function getProductos() {
    let url = baseUrl + "productos/";
    fetch(url)
    .then(res => res.json())
    .then(datos => {
        console.log(datos);
        setTablaProductos(datos)
    })
}

//pedido REST para agregar una carrera (campos: nombre)
function addCliente() {
    let idCliente = document.querySelector("#idCliente").value;
    let nombreCliente = document.querySelector("#nombreCliente").value;
    let apellidoCliente = document.querySelector("#apellidoCliente").value;
    let dniCliente = document.querySelector("#dniCliente").value;
    let objeto = {
        'id': idCliente,
        'apellido': apellidoCliente,
        'nombre': nombreCliente,
        'dni': dniCliente
    }
    let url = baseUrl + "clientes/";
    fetch(url, {
        "method": 'POST',
        "headers": {
            'Content-Type': 'application/json'
        },
        "body": JSON.stringify(objeto)
    })
        .then(function(r){
            let resultado = document.querySelector("#resultado");
            resultado.innerHTML = "POST Cliente status: " + r.status;
            if (r.status === 204) {
                resultado.innerHTML += " Ya existia un cliente con ese id";
            }
            else {
                getClientes();
            }
            setTimeout(function() {
				resultado.innerHTML = '';
				}, 3000);
        })
    .catch(function(error){
        console.log("Error en CREATE: " + error);
    })
}


function addProducto() {
    let idProducto = document.querySelector("#idProducto").value;
    let nombre = document.querySelector("#nombreProducto").value;
    let descripcion = document.querySelector("#descripcion").value;
    let cantidad = document.querySelector("#cantidad").value;
    let precio = document.querySelector("#precio").value;

    let objeto = {
        'id': idProducto,
        'nombre': nombre,
        'descripcion': descripcion,
        'stock': cantidad,
        'precio': precio,
    }
    let url = baseUrl + "productos/"
    fetch(url, {
        "method": 'POST',
        "headers": {
            'Content-Type': 'application/json'
        },
        "body": JSON.stringify(objeto)
    })
        .then(function(r){
            let resultado = document.querySelector("#resultado");
            resultado.innerHTML = "POST Estudiante status: " + r.status;
            if (r.status === 204) {
                resultado.innerHTML += " Ya existia un producto con ese nombre";
            } else {
                 getProductos();
            }
            setTimeout(function() {
				resultado.innerHTML = '';
				}, 3000);
        })
    .catch(function(error){
        console.log("Error en CREATE: " + error);
    })
}
function addFactura() {
    let idFactura = document.querySelector("#idFactura").value;
    let Fecha = document.querySelector("#Fecha").value;
    let cliente = document.querySelector("#idCliente").value;
    let producto1 = document.querySelector("#idProducto1").value;
    let producto2 = document.querySelector("#idProducto2").value;
    let producto3 = document.querySelector("#idProducto3").value;

    let objeto = {
            "id": idFactura,
            "fecha": Fecha,
            "cliente": {
                "id":cliente
                
            },
            "productos": [
                {
                    "id": producto1
                },
                {
                    "id":producto2
                },
                {
                    "id":producto3
                }
            ]
    }
    let url = baseUrl + "facturas/"
    fetch(url, {
        "method": 'POST',
        "headers": {
            'Content-Type': 'application/json'
        },
        "body": JSON.stringify(objeto)
    })
        .then(function(r){
            let resultado = document.querySelector("#resultado");
            resultado.innerHTML = "POST Estudiante status: " + r.status;
            if (r.status === 204) {
                resultado.innerHTML += " Ya existia una factura con ese nombre";
            } else {
                 getFacturas();
            }
            setTimeout(function() {
				resultado.innerHTML = '';
				}, 3000);
        })
    .catch(function(error){
        console.log("Error en CREATE: " + error);
    })
}


//incorpora el resultado de la consulta a una tabla de carreras
function setTablaClientes(datos) {
    let colTr;
    let colTdId;
    let contenidoId;
    let colTdNombre;
    let contenidoNombre;
    let colTdApellido;
    let contenidoApellido;
    let colTdDni;
    let contenidoDni;
    let tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    for (let index = 0; index < datos.length; index++) {
        colTr = document.createElement("tr");
        colTdId = document.createElement("td");
        contenidoId = document.createTextNode(datos[index].id);
        colTdId.appendChild(contenidoId);
        colTr.appendChild(colTdId);
        colTdNombre = document.createElement("td");
        contenidoNombre = document.createTextNode(datos[index].nombre);
        colTdNombre.appendChild(contenidoNombre);
        colTr.appendChild(colTdNombre);
        colTdApellido = document.createElement("td");
        contenidoApellido = document.createTextNode(datos[index].apellido);
        colTdApellido.appendChild(contenidoApellido);
        colTr.appendChild(colTdApellido);
        colTdDni = document.createElement("td");
        contenidoDni = document.createTextNode(datos[index].dni);
        colTdDni.appendChild(contenidoDni);
        colTr.appendChild(colTdDni);
        tbody.appendChild(colTr);
    }
}

function setTablaFacturas(datos){
    let colTr;
    let colTdId;
    let contenidoId;
    let colTdFecha;
    let contenidoFecha;

    let colTdCliente;
    let contenidoCliente;
    let colTdProductos;
    let contenidoProductos;
    let tbody = document.querySelector("tbody");
    console.log(tbody);
    tbody.innerHTML = "";
    for (let index = 0; index < datos.length; index++) {
        colTr = document.createElement("tr");
        colTdId = document.createElement("td");
        contenidoId = document.createTextNode(datos[index].id);
        colTdId.appendChild(contenidoId);
        colTr.appendChild(colTdId);
        colTdFecha = document.createElement("td");
        contenidoFecha = document.createTextNode(datos[index].fecha);
        colTdFecha.appendChild(contenidoFecha);
        colTr.appendChild(colTdFecha);


        colTdCliente = document.createElement("td");
        contenidoCliente = document.createTextNode(datos[index].cliente.nombre);
        colTdCliente.appendChild(contenidoCliente);
        colTr.appendChild(colTdCliente);
        colTdProductos = document.createElement("td");
        let prod="";
         for (let j = 0; j < datos[index].productos.length; j++) {
            prod+=j+" producto:"+datos[index].productos[j].nombre;
             
          }
          contenidoProductos = document.createTextNode(prod);
        colTdProductos.appendChild(contenidoProductos);
         colTr.appendChild(colTdProductos);


        tbody.appendChild(colTr);
    }

}
function setTablaProductos(datos) {
    let colTr;
    let colTdId;
    let contenidoId;
    let colTdNombre;
    let contenidoNombre;

    let colTdDescr;
    let contenidoDescr;
    let colTdcantidad;
    let contenidoCantidad;
    let colTdPrecio;
    let contenidoPrecio;
    let tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    for (let index = 0; index < datos.length; index++) {
        colTr = document.createElement("tr");
        colTdId = document.createElement("td");
        contenidoId = document.createTextNode(datos[index].id);
        colTdId.appendChild(contenidoId);
        colTr.appendChild(colTdId);
        colTdNombre = document.createElement("td");
        contenidoNombre = document.createTextNode(datos[index].nombre);
        colTdNombre.appendChild(contenidoNombre);
        colTr.appendChild(colTdNombre);


        colTdDescr = document.createElement("td");
        contenidoDescr = document.createTextNode(datos[index].descripcion);
        colTdDescr.appendChild(contenidoDescr);
        colTr.appendChild(colTdDescr);
        colTdcantidad = document.createElement("td");
        contenidoCantidad = document.createTextNode(datos[index].stock);
        colTdcantidad.appendChild(contenidoCantidad);
        colTr.appendChild(colTdcantidad);

        colTdPrecio = document.createElement("td");
        contenidoPrecio = document.createTextNode(datos[index].precio);
        colTdPrecio.appendChild(contenidoPrecio);
        colTr.appendChild(colTdPrecio);
        tbody.appendChild(colTr);
    }
}

function setTablaMatriculasReporteClientesTotal(datos) {
    let colTr;
    let colTdIDClienteReporte;
    let contenidoIDClienteReporte;
    let colTdNombreClientereporte;
    let contenidoNombreClienteReporte;
    let colTdApellidoClientereporte;
    let contenidoApellidoClienteReporte;
    let colTdDNIClienteReporte;
    let contenidoDNIClienteReporte;
    let colThInCantidad;
    let contenidoTotal;
    let tbody = document.querySelector("tbody");
    tbody.innerHTML = "";

    for (let index = 0; index < datos.length; index++) {
        colTr = document.createElement("tr");

        colTdIDClienteReporte = document.createElement("td");
        contenidoIDClienteReporte = document.createTextNode(datos[index].id);
        colTdIDClienteReporte.appendChild(contenidoIDClienteReporte);
        colTr.appendChild(colTdIDClienteReporte);

        colTdNombreClientereporte = document.createElement("td");
        contenidoNombreClienteReporte = document.createTextNode(datos[index].nombre);
        colTdNombreClientereporte.appendChild(contenidoNombreClienteReporte);
        colTr.appendChild(colTdNombreClientereporte);

        colTdApellidoClientereporte = document.createElement("td");
        contenidoApellidoClienteReporte= document.createTextNode(datos[index].apellido);
        colTdApellidoClientereporte.appendChild(contenidoApellidoClienteReporte);
        colTr.appendChild(colTdApellidoClientereporte);

        colTdDNIClienteReporte= document.createElement("td");
        contenidoDNIClienteReporte = document.createTextNode(datos[index].dni);
        colTdDNIClienteReporte.appendChild(contenidoDNIClienteReporte);
        colTr.appendChild(colTdDNIClienteReporte);

        colThInCantidad = document.createElement("td");
        contenidoTotal = document.createTextNode("$"+datos[index].total);
        colThInCantidad.appendChild(contenidoTotal);
        colTr.appendChild(colThInCantidad);

        tbody.appendChild(colTr);
    }
}
function setTablaMatriculasReporteProductoMasVendido(datos) {
    let colTr;
    let colTdId;
    let contenidoId;
    let colTdNombre;
    let contenidoNombre;
  
    let colTdDescr;
    let contenidoDescr;
    let colTdcantidad;
    let contenidoCantidad;
    let colTdPrecio;
    let colTdVentas
    let contenidoVentas
    let contenidoPrecio;
    let tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    for (let index = 0; index < datos.length; index++) {
      colTr = document.createElement("tr");
      colTdId = document.createElement("td");
      contenidoId = document.createTextNode(datos[index].id);
      colTdId.appendChild(contenidoId);
      colTr.appendChild(colTdId);
      colTdNombre = document.createElement("td");
      contenidoNombre = document.createTextNode(datos[index].nombre);
      colTdNombre.appendChild(contenidoNombre);
      colTr.appendChild(colTdNombre);
  
  
      colTdDescr = document.createElement("td");
      contenidoDescr = document.createTextNode(datos[index].descripcion);
      colTdDescr.appendChild(contenidoDescr);
      colTr.appendChild(colTdDescr);
  
      colTdcantidad = document.createElement("td");
      contenidoCantidad = document.createTextNode(datos[index].stock);
      colTdcantidad.appendChild(contenidoCantidad);
      colTr.appendChild(colTdcantidad);
  
      colTdPrecio = document.createElement("td");
      contenidoPrecio = document.createTextNode(datos[index].precio);
      colTdPrecio.appendChild(contenidoPrecio);
      colTr.appendChild(colTdPrecio);
  
      colTdVentas = document.createElement("td");
      contenidoVentas = document.createTextNode(datos[index].cantidad);
      colTdVentas.appendChild(contenidoVentas);
      colTr.appendChild(colTdVentas);
  
      tbody.appendChild(colTr);
    }
  }
  function setTablaMatriculasReporteVentasDia(datos) {
    let colTr;
    let colTdFecha;
    let contenidoFecha;
    let colTdTotal;
    let contenidoTotal;
    let tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
  
    for (let index = 0; index < datos.length; index++) {
      colTr = document.createElement("tr");
  
      colTdFecha= document.createElement("td");
      contenidoFecha = document.createTextNode(datos[index].fecha);
      colTdFecha.appendChild(contenidoFecha);
      colTr.appendChild(colTdFecha);
  
      colTdTotal = document.createElement("td");
      contenidoTotal = document.createTextNode(datos[index].total);
      colTdTotal.appendChild(contenidoTotal);
      colTr.appendChild(colTdTotal);
  
      tbody.appendChild(colTr);
    }
  }
function deleteCliente() {
    let idCliente = document.querySelector("#idCliente").value;
    console.log(idCliente);
    let url = baseUrl + "clientes/" + idCliente;
    fetch(url, {
        "method": 'DELETE',
        "headers": {
            'Content-Type': 'application/json'
        },
    })
    .then(function(r){
        let resultado = document.querySelector("#resultado");
        resultado.innerHTML = "DELETE Cliente status: " + r.status;
        if (r.status === 204) {
            resultado.innerHTML += " No existia un cliente con ese id";
        }
        else {
            getClientes();
        }
        setTimeout(function() {
            resultado.innerHTML = '';
            }, 3000);
    })
    .catch(function(error){
        console.log("Error en DELETE: " + error);
    })
}

function updateCliente() {
    let idCliente = document.querySelector("#idCliente").value;
    let nombreCliente = document.querySelector("#nombreCliente").value;
    let apellidoCliente = document.querySelector("#apellidoCliente").value;
    let dniCliente = document.querySelector("#dniCliente").value;
    let objeto = {
        'id': idCliente,
        'apellido': apellidoCliente,
        'nombre': nombreCliente,
        'dni': dniCliente
    }
    let url = baseUrl + "clientes/" + idCliente;
    fetch(url, {
        "method": 'PUT',
        "headers": {
            'Content-Type': 'application/json'
        },
        "body": JSON.stringify(objeto)
    })
        .then(function(r){
            let resultado = document.querySelector("#resultado");
            resultado.innerHTML = "PUT Cliente status: " + r.status;
            if (r.status === 204) {
                resultado.innerHTML += " No existia un cliente con ese id";
            }
            else {
                getClientes();
            }
            setTimeout(function() {
				resultado.innerHTML = '';
				}, 3000);
        })
    .catch(function(error){
        console.log("Error en UPDATE: " + error);
    })
}
function updateProducto() {
    let idProducto = document.querySelector("#idProducto").value;
    let nombreProducto = document.querySelector("#nombreProducto").value;
    let descripcion = document.querySelector("#descripcion").value;
    let cantidad = document.querySelector("#cantidad").value;
    let precio = document.querySelector("#precio").value;
    let objeto = {
        'id': idProducto,
        'nombre': nombreProducto,
        'descripcion': descripcion,
        'stock': cantidad,
        'precio': precio,
    }
    let url = baseUrl + "productos/" + idProducto;
    fetch(url, {
        "method": 'PUT',
        "headers": {
            'Content-Type': 'application/json'
        },
        "body": JSON.stringify(objeto)
    })
        .then(function(r){
            let resultado = document.querySelector("#resultado");
            resultado.innerHTML = "PUT Cliente status: " + r.status;
            if (r.status === 204) {
                resultado.innerHTML += " No existia un cliente con ese id";
            }
            else {
                getProductos();
            }
            setTimeout(function() {
				resultado.innerHTML = '';
				}, 3000);
        })
    .catch(function(error){
        console.log("Error en UPDATE: " + error);
    })
}
function updateFactura() {
    let idFactura = document.querySelector("#idFactura").value;
    let Fecha = document.querySelector("#Fecha").value;
    let cliente = document.querySelector("#idCliente").value;
    let producto1 = document.querySelector("#idProducto1").value;
    let producto2 = document.querySelector("#idProducto2").value;
    let producto3 = document.querySelector("#idProducto3").value;

    let objeto = {
            "id": idFactura,
            "fecha": Fecha,
            "cliente": {
                "id":cliente
                
            },
            "productos": [
                {
                    "id": producto1
                },
                {
                    "id":producto2
                },
                {
                    "id":producto3
                }
            ]
    }
    let url = baseUrl + "facturas/" + idFactura;
    fetch(url, {
        "method": 'PUT',
        "headers": {
            'Content-Type': 'application/json'
        },
        "body": JSON.stringify(objeto)
    })
        .then(function(r){
            let resultado = document.querySelector("#resultado");
            resultado.innerHTML = "PUT Cliente status: " + r.status;
            if (r.status === 204) {
                resultado.innerHTML += " No existia una factura con ese id";
            }
            else {
                getFacturas();
            }
            setTimeout(function() {
				resultado.innerHTML = '';
				}, 3000);
        })
    .catch(function(error){
        console.log("Error en UPDATE: " + error);
    })
}

function deleteProducto() {
    let idProducto = document.querySelector("#idProducto").value;
    console.log(idProducto);
    let url = baseUrl + "productos/" + idProducto;
    fetch(url, {
        "method": 'DELETE',
        "headers": {
            'Content-Type': 'application/json'
        },
    })
    .then(function(r){
        let resultado = document.querySelector("#resultado");
        resultado.innerHTML = "DELETE Cliente status: " + r.status;
        if (r.status === 204) {
            resultado.innerHTML += " No existia un cliente con ese id";
        }
        else {
            getProductos();
        }
        setTimeout(function() {
            resultado.innerHTML = '';
            }, 3000);
    })
    .catch(function(error){
        console.log("Error en DELETE: " + error);
    })
}

function deleteFactura() {
    let idFactura = document.querySelector("#idFactura").value;
    console.log(idFactura);
    let url = baseUrl + "facturas/" + idFactura;
    fetch(url, {
        "method": 'DELETE',
        "headers": {
            'Content-Type': 'application/json'
        },
    })
    .then(function(r){
        let resultado = document.querySelector("#resultado");
        resultado.innerHTML = "DELETE Cliente status: " + r.status;
        if (r.status === 204) {
            resultado.innerHTML += " No existia una factura  con ese id";
        }
        else {
            getFacturas();
        }
        setTimeout(function() {
            resultado.innerHTML = '';
            }, 3000);
    })
    .catch(function(error){
        console.log("Error en DELETE: " + error);
    })
}
