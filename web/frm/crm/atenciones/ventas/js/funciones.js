function inicializar_formulario() {
    verificar_sesion_ajax();
    
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

function listar_atencionventa_ajax() {
    var fecha_desde = $("#fecha_desde").val();
    var fecha_hasta = $("#fecha_hasta").val();
    
    var pDatosFormulario = "&fecha_desde="+fecha_desde+"&fecha_hasta="+fecha_hasta;
    var pUrl = 'atencionventa/listar';
    var pBeforeSend = '';
    var pSuccess = 'listar_atencionventa_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function listar_atencionventa_ajax_success(json){
    $("#tbody-asignacion").html(json.tabla);
}