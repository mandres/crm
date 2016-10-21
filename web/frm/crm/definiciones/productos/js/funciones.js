
// RECEPCION

function inicializar_formulario() {
    verificar_sesion_ajax();
    $("#codigo_producto").focus();

    siguiente_campo("#id_producto", "#nombre_producto", false);
    siguiente_campo("#nombre_producto", "#codigo_producto", false);
    siguiente_campo("#codigo_producto", "#id_grupo", false);
    siguiente_campo("#id_grupo", "#id_temporada", false);
    siguiente_campo("#id_temporada", "#id_talle", false);
    siguiente_campo("#id_talle", "#id_categoria", false);
    siguiente_campo("#id_categoria", "#id_dependencia", false);
    siguiente_campo("#id_dependencia", "#id_sexo", false);
    siguiente_campo("#id_sexo", "#id_edad", false);


    $("#botonAgregar").on('click', function () {
        if (validar_ficha_producto()) {
            agregar_producto_ajax();
        }
    });
    $("#botonModificar").on('click', function () {
        if (validar_ficha_producto()) {
            modificar_producto_ajax();
        }
    });
    $("#botonSalir").on('click', function () {
        $('aside').html("");
    });
}


/*function inicializar_formulario() {
    verificar_sesion_ajax();
    buscar_asignacion();
    $("#buscar").css("display", "none");
    $("#botonSalir").on('click', function () {
        $('aside').html("");
    });
}*/

// VALIDAR DATOS FORMULARIO

function validar_ficha_producto() {
    var ok = true;
    var nombre_producto = $("#nombre_producto").val();
    var codigo_producto = $("#codigo_producto").val();
    var id_grupo = $("#id_grupo").val();
    var id_temporada = $("#id_temporada").val();
    var id_talle = $("#id_talle").val();
    var id_categoria = $("#id_categoria").val();
    var id_dependencia = $("#id_dependencia").val();
    var id_sexo = $("#id_sexo").val();
    var id_edad = $("#id_edad").val();

    if (nombre_producto.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'Debe asignar un nombre al producto', 'Aceptar', '$("#nombre_producto").focus()');
        ok = false;
    } else if (codigo_producto.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'Debe asignar un código al producto', 'Aceptar', '$("#codigo_producto").focus()');
        ok = false;
    } else if (id_grupo.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'El producto debe pertenecer a un grupo ', 'Aceptar', '$("#id_grupo").focus()');
        ok = false;
    }else if (id_temporada.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'El producto debe tener una temporada ', 'Aceptar', '$("#id_temporada").focus()');
        ok = false;
    }else if (id_talle.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'El producto debe tener un talle ', 'Aceptar', '$("#id_talle").focus()');
        ok = false;
    }else if (id_categoria.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'El producto debe tener una categoria ', 'Aceptar', '$("#id_categoria").focus()');
        ok = false;
    }else if (id_dependencia.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'El producto debe tener una dependencia', 'Aceptar', '$("#id_dependencia").focus()');
        ok = false;
    }
    return ok;
}

// BUSCAR PRODUCTO

function inicializar_buscar_producto() {
    $("#bnombre").focus();
    siguiente_campo("#bnombre", "#botonBuscar", false);
    ejecutar_funcion_enter("#bnombre", 'buscar_producto()');
    $("#boton-buscarProducto").on("click", function () {
        $("#tbody-productos").html("");
        $("#bpagina").val("1");
        buscar_producto();
        $("#bnombre").focus();
    });
    $("#anterior").on("click", function () {
        $("#contenidoBusqueda").html("");
        var pag = parseInt($("#bpagina").val());
        if (pag > 1) {
            $("#bpagina").val(pag - 1);
        }
        buscar_producto();
    });
    $("#siguiente").on("click", function () {
        $("#contenidoBusqueda").html("");
        var pag = parseInt($("#bpagina").val());
        $("#bpagina").val(pag + 1);
       buscar_producto();
    });

    $("#botonSalirBuscar").on('click', function () {
        $("#buscar").fadeOut(100, function () {
            $("#panelPrograma").fadeIn(100);
        });
    });
}

function buscar_producto() {
    var pDatosFormulario = $('#form-buscar').serialize();
    var pUrl = 'producto/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_producto_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_producto_success(json) {
    var datos = "";
    $.each(json, function (key, value) {
        datos += "<tr onclick= 'seleccionar_producto($(this))'>";
        datos += "<td>" + value.id_producto + "</td>";
        datos += "<td>" + value.nombre_producto + "</td>";
        datos += "<td>" + value.codigo_producto+ "</td>";
        datos += "</tr>";
    });
    if (datos === "") {
        datos = "<tr style='color: red'><td  colspan=5>No se encuentra ningun producto con los parametros ingresados ...</td></tr>";
    }

    $('#tbody-productos').html(datos);
}

function seleccionar_producto($this) {
    var id_producto = $($this).find('td').eq(0).text();
    buscar_idproducto_ajax(id_producto);
    $('.nav-tabs li:eq(1) a').tab('show');
    deshabilitar_agregar();
}

function buscar_idproducto_ajax(id_producto) {
    var pDatosFormulario = "&id_producto=" + id_producto;
    var pUrl = 'producto/buscarId';
    var pBeforeSend = '';
    var pSuccess = 'buscar_producto_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_producto_ajax_success(json) {
    $('#id_producto').val(json.id_producto);
    $('#nombre_producto').val(json.nombre_producto);
    $('#codigo_producto').val(json.codigo_producto);
    $('#id_grupo').val(json.grupo);
    $('#nombre_grupo').val(json.nombre_grupo);
    $('#id_temporada').val(json.id_temporada);
    $('#nombre_temporada').val(json.nombre_temporada);
    $('#id_talle').val(json.id_talle);
    $('#nombre_talle').val(json.nombre_talle);
    $('#id_categoria').val(json.id_categoria);
    //$('#nombre_categoria').val(json.nombre_categoria);
    $('#id_dependencia').val(json.id_dependencia);
    $('#nombre_dependencia').val(json.nombre_dependencia);
    $('#id_sexo').val(json.id_sexo);
    $('#id_edad').val(json.id_edad);     
    $("#buscar").fadeOut(100, function () {
        $("#panelPrograma").fadeIn(100);
    });
}

// AGREGAR PRODUCTO

function agregar_producto_ajax() {
    var pDatosFormulario = $('#formPrograma').serialize();
    var pUrl = 'producto/agregar';
    var pBeforeSend = '';
    var pSuccess = 'agregar_producto_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function agregar_producto_ajax_success(json) {
    if (json.agregado) {
        $("#id_producto").val(json.id_producto);
        mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: El Producto Fue Registrado, ID PRODUCTO: ' + json.id_producto, 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error:' + json.mensaje, 'Aceptar', '');
    }
}

// MODIFICAR PRODUCTO

function modificar_producto_ajax() {
    var pDatosFormulario = $('#formPrograma').serialize();
    var pUrl = 'producto/modificar';
    var pBeforeSend = '';
    var pSuccess = 'modificar_prodcuto_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function modificar_prodcuto_ajax_success(json) {
    if (json.modificado) {
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: El Producto Fue Modificado', 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error:' + json.mensaje, 'Aceptar', '');
    }
}

function buscar_vendedor() {
    var pDatosFormulario = '';
    var pUrl = 'usuario/buscar/vendedor';
    var pBeforeSend = '';
    var pSuccess = 'buscar_vendedor_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);


// Habilitar y Desabilitar Botones

function habilitar_agregar() {
    $("#botonAgregar").prop("disabled", false);
    $("#botonModificar").prop("disabled", true);
}

function deshabilitar_agregar() {
    $("#botonAgregar").prop("disabled", true);
    $("#botonModificar").prop("disabled", false);
}
}

