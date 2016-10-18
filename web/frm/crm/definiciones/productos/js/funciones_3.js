function inicializar_formulario() {
    limpiar_formulario();
    siguiente_campo("#id_producto", "#nombre_producto", false);
    siguiente_campo("#nombre_producto", "#codigo_producto", false);
    siguiente_campo("#codigo_producto", "#id_grupo", false);
    siguiente_campo("#id_grupo", "#id_temporada", false);
    siguiente_campo("#id_temporada", "#id_talle", false);
    siguiente_campo("#id_talle", "#id_categoria", false);
    siguiente_campo("#id_categoria", "#id_dependencia", false);
    siguiente_campo("#id_dependencia", "#id_sexo", false);
    siguiente_campo("#id_sexo", "#id_edad", false);
    $("#id_producto").on("change", function () {
        buscar_id_producto();
    });
    $("#boton-buscar-producto").on("click", function () {
        buscar_producto($(this));
    });
    $("#boton-agregar").on("click", function () {
        if (validar_formulario()) {
            agregar_producto_ajax();
        }
    });
    $("#boton-modificar").on("click", function () {
        if (validar_formulario()) {
            modificar_ajax();
        }
    });
    $("#boton-eliminar").on("click", function () {
        confirmar("Mensaje del Sistema", "Esta seguro de eliminar este registro?", "Eliminar");
        $("#botonConfirmar").on("click", function () {
            eliminar_ajax();
        });
    });
    $("#boton-retornar").on("click", function () {
        $('aside').html("");
    });
}

// buscar PRODUCTOS

function buscar_producto($this) {
    $this.parent().parent().after("<div class='row'><div id='buscar-producto' class='col col-md-12 border-color'></div></div>");
    $("#buscar-producto").load("frm/crm/definiciones/productos/buscarProductos.html", function () {
        buscar_nombre_producto_ajax();
    });
    $("#boton-buscar-producto").prop("disabled", true);
}

function buscar_nombre_producto_ajax() {
    var pDatosFormulario = '';
    var pUrl = 'producto/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscarNombre_producto_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscarNombre_producto_ajax_success(json) {
    var datos = "";
    $.each(json, function (key, value) {
        datos += "<tr onclick='seleccionar_producto($(this))'>";
        datos += "<td>" + value.id_producto + "</td>";
        datos += "<td>" + value.nombre_producto + "</td>";
        datos += "<td>" + value.producto_producto + "</td>";
        datos += "</tr>";
    });
    $("#tbodyDatos").html(datos);
}

function seleccionar_producto($this) {
    var id_producto = $this.find("td").eq(0).text();
    buscar_id_producto_ajax(id_producto);
}

function buscar_id_producto_ajax(id_producto) {
    var pDatosFormulario = "&id_producto=" + id_producto;
    var pUrl = 'producto/buscarId';
    var pBeforeSend = '';
    var pSuccess = 'recuperarDatos_desdeIdUsuario_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function recuperarDatos_desdeIdUsuario_ajax_success(json) {
    $("#id_producto").val(json.id_producto);
    $("#nombre_producto").val(json.nombre_producto);
    $("#producto_producto").val(json.producto_producto);
    $("#clave_producto").val("");

    $("#buscar-producto").parent().remove();
    deshabilitar_agregar();
    $("#boton-buscar-producto").prop("disabled", false);
    $("#nombre_producto").focus();
    $("#nombre_producto").select();
    productorolBuscarIdUsuario_ajax(json.id_producto);
}

function productorolBuscarIdUsuario_ajax(id_producto) {
    var pDatosFormulario = "&id_producto=" + id_producto;
    var pUrl = 'productorol/buscarIdUsuario';
    var pBeforeSend = '';
    var pSuccess = 'productorolBuscarIdUsuario_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function productorolBuscarIdUsuario_ajax_success(json) {
    $("#tbodyDetalle").html(json.detalle);
}


// agregar
function agregar_producto_ajax() {
    var pDatosFormulario = $('#form-formulario').serialize();
    var pUrl = 'producto/agregar';
    var pBeforeSend = '';
    var pSuccess = 'agregar_producto_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
function agregar_producto_ajax_success(json) {
    if (json.ok) {
        limpiar_formulario();
        mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: El producto fue Insertado', 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', json.mensaje, 'Aceptar', '');
    }
}

// modificar
function modificar_ajax() {
    var pDatosFormulario = $('#form-formulario').serialize();
    var pUrl = 'producto/modificar';
    var pBeforeSend = '';
    var pSuccess = 'modificar_producto_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function modificar_producto_ajax_success(json) {
    if (json.ok) {
        limpiar_formulario();
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: Los Datos del Usuario fue Modificado', 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error:' + json.mensaje, 'Aceptar', '');
    }
}

// eliminar
function eliminar_ajax() {
    var datos_formulario = $('#form-formulario').serialize();
    $.ajax({
        type: 'POST',
        url: 'frm/crm/definiciones/productos/jsp/eliminarUsuario.jsp',
        data: datos_formulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $('#estadoServidor').css('display', 'block');
        },
        success: function (json) {
            if (json.ok) {
                $("#modalConfirmar").modal("hide");
                limpiar_formulario();
            } else {
                $("#mensajes").html("No se pudo recuperar los datos.");
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            $('#estadoServidor').css('display', 'none');
        }
    });
}


// validacion del lado del cliente
function validar_formulario() {
    var ok = true;
    var id = $("#id_producto").val();
    var nombre = $("#nombre_producto").val();
    var producto = $("#producto_producto").val();
    var clave = $("#clave_producto").val();
    if (id === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo ID vacio', 'Aceptar', '');
        $("#id_producto").focus();
        ok = false;
    } else if (nombre === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo NOMBRE vacio', 'Aceptar', '');
        $("#nombre_producto").focus();
        ok = false;
    } else if (producto === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo USUARIO vacio', 'Aceptar', '');
        $("#producto_producto").focus();
        ok = false;
    } else if (clave === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo CONTRASEÑA vacio', 'Aceptar', '');
        $("#clave_producto").focus();
        ok = false;
    }
    return ok;
}

function cerrar_producto($this) {
    $this.parent().parent().parent().remove();
    $("#boton-buscar-producto").prop("disabled", false);
}

function limpiar_formulario() {
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#producto_producto").val("");
    $("#clave_producto").val("");
    habilitar_agregar();
    $("#id_producto").focus();
    $("#id_producto").select();
}
function agregar_linea() {
    $("#tbodyDetalle").prepend("<tr><td id='td-linea' colspan='4'></td></tr>");
    $("#td-linea").load("frm/crm/definiciones/productos/linea.html", function () {
        $("#id_productorol").val('0');
        $("#id_rol").focus();
        $("#id_rol").select();
    });
}
function editar_linea($this) {
    var id_productorol = $this.parent().parent().find("td").eq(0).text();
    var id_rol = $this.parent().parent().find("td").eq(1).text();
    var nombre_rol = $this.parent().parent().find("td").eq(2).text();
    $this.parent().parent().after("<tr><td id='td-linea' colspan='4'></td></tr>");
    $("#td-linea").load("frm/crm/definiciones/productos/linea.html", function () {
        $("#id_productorol").val(id_productorol);
        $("#id_rol").val(id_rol);
        $("#nombre_rol").val(nombre_rol);
        $("#id_rol").focus();
        $("#id_rol").select();
    });
}

function eliminar_linea($this){
    var id_productorol = $this.parent().parent().find("td").eq(0).text();
    confirmar_mensaje('CONFIRMACIÓN', '¿Esta seguro de Eliminar?', 'SI', 'confirmarEliminar_linea_ajax('+id_productorol+')');
}
function confirmarEliminar_linea_ajax(id_productorol){
     var pDatosFormulario = "&id_productorol="+id_productorol;
    var pUrl = 'productorol/eliminar';
    var pBeforeSend = '';
    var pSuccess = 'confirmarEliminar_linea_ajax_success()';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
function confirmarEliminar_linea_ajax_success(){
    var id_producto = $("#id_producto").val();
    productorolBuscarIdUsuario_ajax(id_producto);
}

// UsuarioRoles

function buscar_rol($this) {
    $this.parent().parent().after("<div class='row'><div id='buscar-rol' class='col col-md-12'></div></div>");
    $("#buscar-rol").load("frm/crm/definiciones/productos/buscarRoles.html", function () {
        buscar_RolNoAsignado_ajax();
    });
    $("#boton-buscar-rol").prop("disabled", true);
}

function buscar_RolNoAsignado_ajax() {
    var id_producto = $("#id_producto").val();
    var pDatosFormulario = "&id_producto="+id_producto;
    var pUrl = 'productorol/buscarRolNoAsignado';
    var pBeforeSend = '';
    var pSuccess = 'buscar_RolNoAsignado_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_RolNoAsignado_success(json){
    $("#tbodyDatos").html(json.detalle);
}

function seleccionar_rol($this) {
    var id = $this.find("td").eq(0).text();
    var nombre = $this.find("td").eq(1).text();
    $("#id_rol").val(id);
    $("#nombre_rol").val(nombre);
    $("#buscar-rol").parent().remove();
    $("#boton-buscar-rol").prop("disabled", false);
    $("#nombre_rol").focus();
}
function cerrar_rol($this) {
    $this.parent().parent().parent().remove();
    $("#boton-buscar-rol").prop("disabled", false);
}

function productoRol_agregar_ajax() {
    var id_producto = $("#id_producto").val();
    var id_rol = $("#id_rol").val();
    var pDatosFormulario = "&id_producto="+id_producto+"&id_rol="+id_rol;
    var pUrl = 'productorol/agregar';
    var pBeforeSend = '';
    var pSuccess = 'productoRol_agregar_ajax_success(json,'+id_producto+')';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function productoRol_agregar_ajax_success(json, id_producto) {
    if (json.agregado) {
        mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: El Rol ha sido Agregado', 'Aceptar', '');
        productorolBuscarIdUsuario_ajax(id_producto);
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: '+json.mensaje, 'Aceptar', '');
    }
}

function productoRol_modificar_ajax() {
    var id_productorol = $("#id_productorol").val();
    var id_producto = $("#id_producto").val();
    var id_rol = $("#id_rol").val();
    var pDatosFormulario = "&id_producto="+id_producto+"&id_rol="+id_rol+"&id_productorol="+id_productorol;
    var pUrl = 'productorol/modificar';
    var pBeforeSend = '';
    var pSuccess = 'productoRol_modificar_ajax_success(json,'+id_producto+')';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function productoRol_modificar_ajax_success(json, id_producto) {
    if (json.modificado) {
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: El Rol ha sido Modificado', 'Aceptar', '');
        productorolBuscarIdUsuario_ajax(id_producto);
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: '+json.mensaje, 'Aceptar', '');
    }
}


