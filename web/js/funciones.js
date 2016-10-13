
// ACCESO

function inicializar_acceso() {
    $('[data-toggle="tooltip"]').tooltip();
    $('#panel-acceso').fadeIn("slow");
    document.getElementById('usuario_usuario').focus();
    siguiente_campo("#usuario_usuario", "#clave_usuario", false);
    siguiente_campo("#clave_usuario", "#boton-ingresar", false);
    $("#boton-ingresar").on("click", function () {
        validar_acceso();
    });
}

function validar_acceso() {
    var usuario = document.getElementById('usuario_usuario').value;
    var clave = document.getElementById('clave_usuario').value;
    if (usuario === "") {
        mostrar_mensaje('Mensaje del Sistema', 'Usuario no debe estar vacio.', 'Aceptar', 'focus_componente("#usuario_usuario")');
    } else if (clave === "") {
        mostrar_mensaje('Mensaje del Sistema', 'Contraseña no debe estar vacia.', 'Aceptar', 'focus_componente("#clave_usuario")');
    } else {
        validar_acceso_ajax();
    }
}

function validar_acceso_ajax() {
    var pDatosFormulario = $('#form-acceso').serialize();
    var pUrl = 'usuario/validar';
    var pBeforeSend = '';
    var pSuccess = 'validar_acceso_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function validar_acceso_ajax_success(json) {
    if (json.acceso) {
        location.href = './menu.html';
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Credencial incorrecta.', 'Aceptar', 'focus_componente("#usuario_usuario")');
    }
}

// MENU

function inicializar_menu() {
    verificar_sesion_ajax();
    generar_menu_ajax();
    $(".main_toggle_menu").on('click', function () {
        $('.menuv').toggleClass('ocultar_menu');
        if ($(".main_toggle_menu i").hasClass("icon-angle-left")) {
            $(".main_toggle_menu i").removeClass().addClass("icon-angle-right");
            $('.menuv').css('flex', '0 1 0px');
        } else {
            $(".main_toggle_menu i").removeClass().addClass("icon-angle-left");
            $('.menuv').css('flex', '0 0 260px');
        }
    });
    setting_usuario();
}

function setting_usuario() {
    $(".header__usuariologueado").on('click', function () {
        $('.setting_usuario').toggle('slow');
    });
}

function generar_menu_ajax() {
    var pDatosFormulario = '';
    var pUrl = 'permiso/generarMenu';
    var pBeforeSend = "";
    var pSuccess = 'desplegar_submenu(json)';
    var pError = '';
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function desplegar_submenu(json) {
    $(".menuv").html(json.menu);
    $(".nombreusuario").html("<a href='#' onclick='return false'>" + json.nombre_usuario + "</a>");
    $(".menuv a").on('click', function () {
        var ocultar = $(this).hasClass('ocultar');
        if (ocultar) {
            $(this).toggleClass('ocultar');
            $(this).parent().children('ul').fadeOut('slow');
            $(this).children("i").removeClass().addClass("icon-angle-down");
        } else {
            $(this).addClass('ocultar');
            $(this).parent().children('ul').fadeIn('slow');
            $(this).children("i").removeClass().addClass("icon-angle-up");
        }
    });
}

// FUNCIONES GENERALES

function ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete) {
    $.ajax({
        type: 'POST',
        url: pUrl,
        data: pDatosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            eval(pBeforeSend);
        },
        success: function (json) {
            eval(pSuccess);
        },
        error: function (e) {
            eval(pError);
        },
        complete: function (objeto, exito, error) {
            eval(pComplete);
        }
    });
}

function ajax_error() {
    mostrar_mensaje('Mensaje del Sistema', 'ERROR: No se pudo conectar al servidor.', 'Aceptar', '');
}

function mostrar_mensaje(pTitulo, pMensaje, pTextoBoton, pFuncion) {
    var modal = '<div id="modalMensaje" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">';
    modal += '<div class="modal-dialog" role="document">';
    modal += '<div class="modal-content">';
    modal += '  <div class="modal-header">';
    modal += '    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
    modal += '    <h4 class="modal-title" id="gridSystemModalLabel">' + pTitulo + '</h4>';
    modal += '  </div>';
    modal += '  <div class="modal-body">';
    modal += pMensaje;
    modal += '  </div>';
    modal += '  <div class="modal-footer">';
    modal += '    <button id="botonMensaje" type="button" class="btn btn-primary" data-dismiss="modal">';
    modal += '        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ' + pTextoBoton;
    modal += '    </button>';
    modal += '  </div>';
    modal += ' </div>';
    modal += '  </div>';
    modal += '</div>';
    $('body').append('<div id="mensaje"></div>');
    $('#mensaje').html(modal);
    $('#modalMensaje').modal('show');
    $('#modalMensaje').on('shown.bs.modal', function () {
        $('#botonMensaje').focus();
    });
    $('#modalMensaje').on('hidden.bs.modal', function () {
        eval(pFuncion);
        $("#mensaje").remove();
    });
}

function confirmar_mensaje(pTitulo, pMensaje, pTextoBoton, pFuncion) {
    var modal = '<div id="modalMensaje" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">';
    modal += '<div class="modal-dialog" role="document">';
    modal += '<div class="modal-content">';
    modal += '  <div class="modal-header">';
    modal += '    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
    modal += '    <h4 class="modal-title" id="gridSystemModalLabel">' + pTitulo + '</h4>';
    modal += '  </div>';
    modal += '  <div class="modal-body">';
    modal += pMensaje;
    modal += '  </div>';
    modal += '  <div class="modal-footer">';
    modal += '    <button id="botonMensaje" type="button" class="btn btn-primary" data-dismiss="modal">';
    modal += '        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ' + pTextoBoton;
    modal += '    </button>';
    modal += '    <button id="botonMensajeCancelar" type="button" class="btn btn-primary" data-dismiss="modal">';
    modal += '        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancelar';
    modal += '    </button>';
    modal += '  </div>';
    modal += ' </div>';
    modal += '  </div>';
    modal += '</div>';
    $('body').append('<div id="mensaje"></div>');
    $('#mensaje').html(modal);
    $('#modalMensaje').modal('show');
    $('#modalMensaje').on('shown.bs.modal', function () {
        $('#botonMensaje').focus();
    });
    $('#modalMensaje').on('hidden.bs.modal', function () {
        eval(pFuncion);
        $("#mensaje").remove();
    });
    $('#modalMensaje').on('hidden.bs.modal', function () {
        $('#modalMensaje').modal('hide');
    });
}

function siguiente_campo(actual, siguiente, preventDefault) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            if (preventDefault) {
                event.preventDefault();
            }
            focus_componente(siguiente);
        }
    });
}

function verificar_sesion_ajax() {
    var pDatosFormulario = "";
    var pUrl = "sessions/verificar";
    var pBeforeSend = "";
    var pSuccess = "verificar_sesion_ajax_success";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function verificar_sesion_ajax_success(json) {
    if (json.activo === "true") {
    } else {
        alert("entra aqui");
        location.href = "index.html";
    }
}

function cerrar_sesion_ajax() {
    var pDatosFormulario = "";
    var pUrl = "sessions/cerrar";
    var pBeforeSend = "";
    var pSuccess = "";
    var pError = "ajax_error()";
    var pComplete = "";
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function cargar_formulario(nombre) {
    $('aside').load(nombre);
}

function focus_componente(pComponente) {
    $(pComponente).focus();
    $(pComponente).select();
}

function ejecutar_funcion_enter(elemento, funcion) {
    $(elemento).on('keypress', function (event) {
        if (event.which === 13) {
            eval(funcion);
            event.preventDefault();
        }
    });
}

function habilitar_agregar() {
    $("#boton_agregar").prop("disabled", false);
    $("#boton_modificar").prop("disabled", true);
}

function deshabilitar_agregar() {
    $("#boton_agregar").prop("disabled", true);
    $("#boton_modificar").prop("disabled", false);
}