
// RECEPCION

function inicializar_formulario() {
    verificar_sesion_ajax();
    buscar_asignacion();
    $("#buscar").css("display", "none");
    $("#botonSalir").on('click', function () {
        $('aside').html("");
    });
}

// BUSCAR ASIGNACIONES

function buscar_asignacion() {
    var pDatosFormulario = '';
    var pUrl = 'asignacion/buscar';
    var pBeforeSend = '';
    var pSuccess = 'buscar_asignacion_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_asignacion_success(json) {
    var datos = "";
    $.each(json, function (key, value) {
        var selected1 = "";
        var selected2 = "";
        var selected3 = "";
        if(value.estado_asignacion==='Asignado') selected1 = 'selected';
        if(value.estado_asignacion==='Atendido') selected2 = 'selected';
        if(value.estado_asignacion==='Liberado') selected3 = 'selected';
        datos += "<tr>";
        datos += "<td>"+value.id_asignacion+"</td>";
        datos += "<td>"+value.id_cliente+"</td>";
        datos += "<td>"+value.nombre_cliente+"</td>";
        datos += "<td>"+value.id_usuarioasignador+"</td>";
        datos += "<td>"+value.nombre_usuarioasignador+"</td>";
        datos += "<td>"+value.id_usuarioasignado+"</td>";
        datos += "<td>"+value.nombre_usuarioasignado+"</td>";
        datos += "<td>"+value.asignado_asignacion+"</td>";
        datos += "<td>"+value.atendido_asignacion+"</td>";
        datos += "<td>"+value.liberado_asignacion+"</td>";
        datos += "<td class='centrado'><button class='btn btn-primary btn-sm'>Seleccionar</button></td>";
        datos += "<td class='centrado'>";
        datos += "   <select class='form-control input-sm'>";
        datos += "      <option value='Asignado' "+selected1+" >Asignado</option>";
        datos += "      <option value='Atendido' "+selected2+" >Atendido</option>";
        datos += "      <option value='Liberado' "+selected3+" >Liberado</option>";
        datos += "   <select>";
        datos += "</td>";
        datos += "<td><input type='text' value='"+value.observacion_asignacion+"' class='form-control input-sm' </td>";
        datos += "<td class='centrado'><button class='btn btn-primary btn-sm'>Grabar</button></td>";
        datos += "</tr>";
    });
    $('#tbody-asignaciones').html(datos);
}