function inicializar_formulario() {
    verificar_sesion_ajax();
    buscar_estadovendedor_ajax();
// Iniciar toggle estado vendedor

    $("#toggle-estado-vendedor").bootstrapToggle();

// Inicializar datetimepicker
    $('#fecha_desde, #fecha_hasta').datetimepicker({
        startDate: new Date(),
        timepicker: false,
        format: 'd/m/Y'
    });
    $.datetimepicker.setLocale('es');

// Inicializar fechas
//      Formulario Ventas
    var fecha_hoy = hoyDMA();
    $("#fecha_desde").val(fecha_hoy);
    $("#fecha_hasta").val(fecha_hoy);

    listar_atencionventa_ajax();
}

// acciones de botones

$("#btn-actualizar-lista-asignacion").on('click', function () {
    listar_atencionventa_ajax();
});


// buscar asignacion pendiente

function ejecutar_buscar_asignacionpendiente() {
    setInterval(function () {
        buscar_asignacionpendiente_ajax();
    }, 10000);
}

function buscar_asignacionpendiente_ajax() {
    var pDatosFormulario = "";
    var pUrl = 'atencionventa/asignadopendiente';
    var pBeforeSend = '';
    var pSuccess = 'buscar_asignacionpendiente_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_asignacionpendiente_ajax_success(json) {
    $("#asignacion-pendiente").html(json.cantidad);
    $("#tbody-asignacion-pendiente").html(json.tabla);
}

// actualizar estado vendedor
$("#toggle-estado-vendedor").change(function () {
    var estado_vendedor = $(this).prop('checked');
    actualizar_estadovendedor_ajax(estado_vendedor);
});

function actualizar_estadovendedor_ajax(estado_vendedor) {
    var pDatosFormulario = "&estado_vendedor=" + estado_vendedor;
    var pUrl = 'vendedor/modificar/estado';
    var pBeforeSend = '';
    var pSuccess = 'actualizar_estadovendedor_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
function actualizar_estadovendedor_ajax_success(json) {
    if (json.modificado) {
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: El estado ha sido modificado', 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error: No se ha podido Modificar', 'Aceptar', '');
    }
}

function buscar_estadovendedor_ajax() {
    var pDatosFormulario = "";
    var pUrl = 'vendedor/buscar/idusuario';
    var pBeforeSend = '';
    var pSuccess = 'buscar_estadovendedor_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
//cambio el estado del componente mediante la clase, para evitar usar el atributo change, ya que con esto se
//ejecuta la funcion que actualiza el estado del vendedor
function buscar_estadovendedor_ajax_success(json) {
    var estado_vendedor = $(this).prop('checked');
    var id_estadovendedor = json.id_estadovendedor;
    if (id_estadovendedor === 1) {
        if (!estado_vendedor) {
            $("#toggle-estado-vendedor").parent().removeClass().addClass('toggle btn btn-success');
            $("#toggle-estado-vendedor").prop('checked', true);
        }
    } else {
        $("#toggle-estado-vendedor").parent().removeClass().addClass('toggle btn btn-danger off');
        $("#toggle-estado-vendedor").prop('checked', false);
    }
}

function listar_atencionventa_ajax() {
    var fecha_desde = $("#fecha_desde").val();
    var fecha_hasta = $("#fecha_hasta").val();

    var pDatosFormulario = "&fecha_desde=" + fecha_desde + "&fecha_hasta=" + fecha_hasta;
    var pUrl = 'atencionventa/listar';
    var pBeforeSend = '';
    var pSuccess = 'listar_atencionventa_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = 'ejecutar_buscar_asignacionpendiente()';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function listar_atencionventa_ajax_success(json) {
    $("#tbody-asignacion").html(json.tabla);
}