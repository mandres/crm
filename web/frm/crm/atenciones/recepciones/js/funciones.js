
// RECEPCION

function inicializar_formulario() {
    verificar_sesion_ajax();
    $("#cedula_cliente").focus();
    combo_seccion_ajax();

// Funciones de siguiente campo con enter

    siguiente_campo("#cedula_cliente", "#ruc_cliente", false);
    siguiente_campo("#ruc_cliente", "#nombre_cliente", false);
    siguiente_campo("#nombre_cliente", "#direccion_cliente", false);
    siguiente_campo("#direccion_cliente", "#id_ciudad", false);
    siguiente_campo("#id_ciudad", "#fecha_nacimiento_cliente", false);
    siguiente_campo("#fecha_nacimiento_cliente", "#mail_cliente", false);
    siguiente_campo("#mail_cliente", "#telefono_cliente", false);
    siguiente_campo("#telefono_cliente", "#contacto_cliente", false);

// Inicializar datetimepicker
    $('#fecha_nacimiento_cliente, #fecha_desde, #fecha_hasta').datetimepicker({
        startDate: new Date(),
        timepicker: false,
        format: 'd/m/Y'
    });
    $.datetimepicker.setLocale('es');

// Inicializar funciones de los botones

    $("#botonAgregar").on('click', function () {
        if (validar_ficha_cliente()) {
            agregar_cliente_ajax();
        }
    });
    $("#botonModificar").on('click', function () {
        if (validar_ficha_cliente()) {
            modificar_cliente_ajax();
        }
    });
    $("#botonGenerarTicket").on('click', function () {
        var id_cliente = $("#id_cliente").val();
        if (id_cliente === "") {
            mostrar_mensaje('Mensaje del Sistema', 'Para Generar un Ticked, primero debe seleccionar un cliente', 'Aceptar', '');
        } else {
            atencion_agregar_ajax();
        }
    });
    $("#botonSalir").on('click', function () {
        $('aside').html("");
    });
    buscar_ciudad_por_nombre();

    // botones estado atencion
    $("#botones-estadosAtencion").on('click', 'button', function () {
        listar_atencion_ajax($(this).attr('id'));
    });

}

//Inicializar cargar datos secciones en combo

function combo_seccion_ajax() {
    var pDatosFormulario = "";
    var pUrl = 'seccion/generarLista';
    var pBeforeSend = '';
    var pSuccess = 'combo_seccion_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function combo_seccion_ajax_success(json) {
    $("#id_seccion").html(json.combo);
}

// VALIDAR DATOS FORMULARIO

function validar_ficha_cliente() {
    var ok = true;
    var cedula_cliente = $("#cedula_cliente").val();
    var ruc_cliente = $("#ruc_cliente").val();
    var nombre_cliente = $("#nombre_cliente").val();
    var direccion_cliente = $("#direccion_cliente").val();
    var id_ciudad = $("#id_ciudad").val();
    var fecha_nacimiento_cliente = $("#fecha_nacimiento_cliente").val();

    if (cedula_cliente === "" & ruc_cliente === "") {
        mostrar_mensaje('Mensaje del Sistema', 'Debe ingresar por lo menos el RUC o CEDULA del cliente', 'Aceptar', '$("#cedula_cliente").focus()');
        ok = false;
    } else if (nombre_cliente.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'Debe asignar un nombre de Cliente', 'Aceptar', '$("#nombre_cliente").focus()');
        ok = false;
    } else if (direccion_cliente.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'Debe asignar una direccion del Cliente', 'Aceptar', '$("#direccion_cliente").focus()');
        ok = false;
    } else if (id_ciudad === null) {
        mostrar_mensaje('Mensaje del Sistema', 'Debe seleccionar una Ciudad', 'Aceptar', '$("#id_ciudad").focus()');
        ok = false;
    } else if (fecha_nacimiento_cliente.length === 0) {
        mostrar_mensaje('Mensaje del Sistema', 'El dato Fecha de Nacimiento es Obligatorio', 'Aceptar', '$("#fecha_nacimiento_cliente").focus()');
        ok = false;
    }
    return ok;
}

// BUSCAR CLIENTE

function inicializar_buscar_cliente() {
    $("#bnombre").focus();
    siguiente_campo("#bnombre", "#botonBuscar", false);
    ejecutar_funcion_enter("#bnombre", 'buscar_cliente()');
    $("#boton-buscarCliente").on("click", function () {
        $("#tbody-clientes").html("");
        $("#bpagina").val("1");
        buscar_cliente();
        $("#bnombre").focus();
    });
    $("#anterior").on("click", function () {
        $("#contenidoBusqueda").html("");
        var pag = parseInt($("#bpagina").val());
        if (pag > 1) {
            $("#bpagina").val(pag - 1);
        }
        buscar_cliente();
    });
    $("#siguiente").on("click", function () {
        $("#contenidoBusqueda").html("");
        var pag = parseInt($("#bpagina").val());
        $("#bpagina").val(pag + 1);
        buscar_cliente();
    });

    $("#botonSalirBuscar").on('click', function () {
        $("#buscar").fadeOut(100, function () {
            $("#panelPrograma").fadeIn(100);
        });
    });
}

function buscar_cliente() {
    var pDatosFormulario = $('#form-buscar').serialize();
    var pUrl = 'cliente/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_cliente_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_cliente_success(json) {
    var datos = "";
    $.each(json, function (key, value) {
        datos += "<tr onclick='seleccionar_cliente($(this))'>";
        datos += "<td>" + value.id_cliente + "</td>";
        datos += "<td>" + value.codigo_cliente + "</td>";
        datos += "<td>" + value.cedula_cliente + "</td>";
        datos += "<td>" + value.ruc_cliente + "</td>";
        datos += "<td>" + value.nombre_cliente + "</td>";
        datos += "</tr>";
    });
    if (datos === "") {
        datos = "<tr style='color: red'><td  colspan=5>No se encuentra ningun cliente con los parametros ingresados ...</td></tr>";
    }

    $('#tbody-clientes').html(datos);
}

function seleccionar_cliente($this) {
    var id_cliente = $($this).find('td').eq(0).text();
    buscar_idcliente_ajax(id_cliente);
    var id_atencion = $("#id_atencion").val();
    if (id_atencion === "") {
        habilitar_generarTicket();
    } else {
        desabilitar_generarTicket();
    }
    $('.nav-pills li:eq(1) a').tab('show');
    deshabilitar_agregar();
}

function buscar_idcliente_ajax(id_cliente) {
    var pDatosFormulario = "&id_cliente=" + id_cliente;
    var pUrl = 'cliente/buscarId';
    var pBeforeSend = '';
    var pSuccess = 'buscar_cliente_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_cliente_ajax_success(json) {
    $('#id_cliente').val(json.id_cliente);
    $('#codigo_cliente').val(json.codigo_cliente);
    $('#cedula_cliente').val(json.cedula_cliente);
    $('#ruc_cliente').val(json.ruc_cliente);
    $('#nombre_cliente').val(json.nombre_cliente);
    $('#direccion_cliente').val(json.direccion_cliente);
    $('#id_ciudad').val(json.id_ciudad);
    $('#nombre_ciudad').val(json.nombre_ciudad);
    $('#fecha_nacimiento_cliente').val(json.fecha_nacimiento_cliente);
    $('#mail_cliente').val(json.mail_cliente);
    $('#telefono_cliente').val(json.telefono_cliente);
    $('#contacto_cliente').val(json.contacto_cliente);
    $("#buscar").fadeOut(100, function () {
        $("#panelPrograma").fadeIn(100);
    });
}

function buscar_ciudad_por_nombre() {
    var pDatosFormulario = '';
    var pUrl = 'ciudad/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_ciudad_por_nombre_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_ciudad_por_nombre_success(json) {
    var datos = "";
    $.each(json, function (key, value) {
        datos += "<option value='" + value.id_ciudad + "'>" + value.nombre_ciudad + "</option>";

    });
    $('#id_ciudad').html(datos);
}

// AGREGAR CLIENTE

function agregar_cliente_ajax() {
    var pDatosFormulario = $('#formPrograma').serialize();
    var pUrl = 'cliente/agregar';
    var pBeforeSend = '';
    var pSuccess = 'agregar_cliente_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function agregar_cliente_ajax_success(json) {
    if (json.agregado) {
        $("#id_cliente").val(json.id_cliente);
        mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: El Cliente Fue Registrado, ID CLIENTE: ' + json.id_cliente, 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error:' + json.mensaje, 'Aceptar', '');
    }
}

// MODIFICAR CLIENTE

function modificar_cliente_ajax() {
    var pDatosFormulario = $('#formPrograma').serialize();
    var pUrl = 'cliente/modificar';
    var pBeforeSend = '';
    var pSuccess = 'modificar_cliente_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function modificar_cliente_ajax_success(json) {
    if (json.modificado) {
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: El Cliente Fue Modificado', 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error:' + json.mensaje, 'Aceptar', '');
    }
}

// ATENCION

function atencion_agregar_ajax() {
    var pDatosFormulario = $('#formPrograma').serialize();
    var pUrl = 'atencion/agregar';
    var pBeforeSend = '';
    var pSuccess = 'atencion_agregar_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function atencion_agregar_ajax_success(json) {
    if (json.agregado) {
        mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: Fue Generado el Tiket N: ' + json.id_atencion, 'Aceptar', 'listar_atencion_ajax()');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error:' + json.mensaje, 'Aceptar', '');
    }
}

function listar_atencion_ajax(id_estadoatencion) {
    var pDatosFormulario = "&id_estadoatencion=" + id_estadoatencion;
    var pUrl = 'atencion/listar';
    var pBeforeSend = '';
    var pSuccess = 'listar_atencion_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function listar_atencion_ajax_success(json) {
    $("#tbody-atencion").html(json.tabla);
    $("#botones-estadosAtencion").children("#0").children('span').html(json.todos);
    $("#botones-estadosAtencion").children("#1").children('span').html(json.pendiente);
    $("#botones-estadosAtencion").children("#2").children('span').html(json.asignado);
    $("#botones-estadosAtencion").children("#3").children('span').html(json.atendiendo);
    $("#botones-estadosAtencion").children("#4").children('span').html(json.cerrado);
    $('.nav-pills li:eq(2) a').tab('show');
    desabilitar_generarTicket();
    seleccionarIdatencion();
}

function seleccionarIdatencion() {
    $("#tbody-atencion tr").on('click', function () {
        var id_atencion = $(this).find('td').eq(0).html();
        $("#tbody-atencion tr").each(function () {
            $('#td-linea').remove();
        });
        $(this).after("<tr><td id='td-linea' colspan='6'></td></tr>");
        $("#td-linea").load("frm/crm/atenciones/recepciones/linea.html", function () {
            buscarIdatencion(id_atencion);
        });
    });
}

function buscarIdatencion(id_atencion) {
    var pDatosFormulario = "&id_atencion=" + id_atencion;
    var pUrl = 'atencion/buscarId11111';
    var pBeforeSend = '';
    var pSuccess = 'atencion_buscarId_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function atencion_buscarId_ajax_success(json) {
    $("#id_vendedor").val(json.id_vendedor);
    $("#nombre_vendedor").val(json.nombre_vendedor);
    $("#fechahora_recepcion").val(json.fechahora_recepcion);
    $("#fechahora_asignado").val(json.fechahora_asignado);
    $("#fechahora_inicioatencion").val(json.fechahora_inicioatencion);
    $("#fechahora_finatencion").val(json.fechahora_finatencion);
}

// Habilitar y Desabilitar Botones

function habilitar_agregar() {
    $("#botonAgregar").removeClass('disabled');
    $("#botonModificar").addClass('disabled');
}

function deshabilitar_agregar() {
    $("#botonAgregar").addClass('disabled');
    $("#botonModificar").removeClass('disabled');
}

function habilitar_generarTicket() {
    $("#botonGenerarTicket").removeClass('disabled');
}

function desabilitar_generarTicket() {
    $("#botonGenerarTicket").addClass('disabled');
}
