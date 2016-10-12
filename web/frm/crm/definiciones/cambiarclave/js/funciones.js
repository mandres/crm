function cambiarClaveUsuario() {
    var pDatosFormulario = $('#formPrograma').serialize();
    var pUrl = 'usuario/cambiarClave';
    var pBeforeSend = '';
    var pSuccess = 'cambiarClave_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function cambiarClave_ajax_success(json){
    if (json.cambioclave) {
         mostrar_mensaje('Mensaje del Sistema', 'PROCESADO: Clave Actualizado', 'Aceptar', '');
    }else{
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: No se puedo actualizar el cambio de Clave.', 'Aceptar', '');
    }
}