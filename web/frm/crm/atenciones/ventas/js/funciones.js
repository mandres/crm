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
function buscar_estadovendedor_ajax_success(json) {
    var id_estadovendedor = json.id_estadovendedor;
    if (id_estadovendedor === 1) {
        $("#toggle-estado-vendedor").bootstrapToggle('on');
    } else {
        $("#toggle-estado-vendedor").bootstrapToggle('off');
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
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function listar_atencionventa_ajax_success(json) {
    $("#tbody-asignacion").html(json.tabla);
}