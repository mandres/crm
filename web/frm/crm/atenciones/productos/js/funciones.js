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
  //  $("#fecha_desde").val(fecha_hoy);
  //  $("#fecha_hasta").val(fecha_hoy);
    
    listar_productos_ajax();
}

function listar_productos_ajax() {
   // var fecha_desde = $("#fecha_desde").val();
   // var fecha_hasta = $("#fecha_hasta").val();
    
  //  var pDatosFormulario = "&fecha_desde="+fecha_desde+"&fecha_hasta="+fecha_hasta;
   var pDatosFormulario = $('#form-buscar').serialize();
    var pUrl = 'productos/listar';
    var pBeforeSend = '';
    var pSuccess = 'listar_productos_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function listar_productos_ajax_success(json){
    $("#tbody-listado").html(json.tabla);
}

/* GRUPOS
 * 
 */

function buscar_grupo_por_nombre() {
    var pDatosFormulario = '';
    var pUrl = 'grupo/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_grupo_por_nombre_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_grupo_por_nombre_success(json) {
    var datos = "";
    $.each(json, function (key, value) {
        datos += "<option value='" + value.id_grupo + "'>" + value.nombre_grupo + "</option>";

    });
    $('#id_grupo').html(datos);
}