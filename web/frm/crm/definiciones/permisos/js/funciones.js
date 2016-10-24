function inicializar_formulario() {
    verificar_sesion_ajax();

    $("#buscar").css("display", "none");
    $("#id_rol").focus();
    siguiente_campo("#id_formulario", "#botonBuscarIdFormulario", true);
    siguiente_campo("#id_rol", "#id_formulario", false);


// Botones
    $("#botonBuscarIdPermiso").on('click', function () {
        $("#buscar").load("buscar.html");
        $("#buscar").fadeIn("slow");
        $("#panelPrograma").fadeOut("slow");
    });
    $("#botonBuscarIdRol").on('click', function () {
        $("#buscar").load("frm/crm/definiciones/permisos/buscarRoles.html");
        $("#buscar").fadeIn("slow");
        $("#panelPrograma").fadeOut("slow");
    });
    $("#botonBuscarIdFormulario").on('click', function () {
        $("#buscar").load("frm/crm/definiciones/permisos/buscarFormularios.html");
        $("#buscar").fadeIn("slow");
        $("#panelPrograma").fadeOut("slow");
    });
    $("#btn-agregar-permiso").on('click', function () {
        var id_rol = $("#id_rol").val();
        var id_formulario = $(this).parent().siblings().eq(0).find("input").val();
        var agregar = $(this).parent().siblings().eq(2).find("input").prop("checked");
        var modificar = $(this).parent().siblings().eq(3).find("input").prop("checked");
        var eliminar = $(this).parent().siblings().eq(4).find("input").prop("checked");
        var listar = $(this).parent().siblings().eq(5).find("input").prop("checked");
        if (id_rol === "") {
            mostrar_mensaje('Mensaje del Sistema', 'Error: Dato Incompleto, Debe seleccionar un Formulario', 'Aceptar', '');
        } else {
            agregar_permiso_ajax(id_rol, id_formulario, agregar, modificar, eliminar, listar);
        }
    });
    $("#botonEliminarAlert").on('click', function () {
        eliminarPermiso();
        $('#confirmarEliminar').modal('hide');
    });
    $("#botonSalir").on('click', function () {
        $('aside').html("");
    });
}

function buscar_permiso_ajax() {
    var pDatosFormulario = $('#form-buscar').serialize();
    var pUrl = 'cliente/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_permiso_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_permiso_ajax_success(json) {
    $("#mensajes").html(json.mensaje);
    $("#id_rol").val(json.id_rol);
    $("#nombre_rol").val(json.nombre_rol);
    $("#id_formulario").val(json.id_formulario);
    $("#nombre_formulario").val(json.nombre_formulario);
    if (json.nuevo === "true") {
        $("#botonAgregar").prop('disabled', false);
        $("#botonModificar").prop('disabled', true);
        $("#botonEliminar").prop('disabled', true);
    } else {
        $("#botonAgregar").prop('disabled', true);
        $("#botonModificar").prop('disabled', false);
        $("#botonEliminar").prop('disabled', false);
    }
}

function buscar_nombrePermiso_ajax(id_rol) {
    var pDatosFormulario = "&id_rol=" + id_rol;
    var pUrl = 'permiso/buscarNombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_nombrePermiso_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombrePermiso_ajax_success(json) {
    $("#tbody-permisos").html(json.contenido);
    $("tbody-permisos tr").on("click", function () {
        var id = $(this).find("td:first").html();
        alert(id);
    });
}

function agregar_permiso_ajax(id_rol, id_formulario, agregar, modificar, eliminar, listar) {
    var datosFormulario = "&id_rol=" + id_rol + "&id_formulario=" + id_formulario
            + "&agregar=" + agregar + "&modificar=" + modificar + "&eliminar=" + eliminar
            + "&listar=" + listar;

    var pDatosFormulario = datosFormulario;
    var pUrl = 'permiso/agregar';
    var pBeforeSend = '';
    var pSuccess = 'agregar_permiso_ajax_success(json, ' + id_rol + ')';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function agregar_permiso_ajax_success(json, id_rol) {
    buscar_nombrePermiso_ajax(id_rol);
    $("#id_rol").focus;
}

function modificar_permiso_ajax(objeto) {
    var id_permiso = objeto.parent().siblings().eq(0).html();
    var agregar = objeto.parent().siblings().eq(2).find("input").prop("checked");
    var modificar = objeto.parent().siblings().eq(3).find("input").prop("checked");
    var eliminar = objeto.parent().siblings().eq(4).find("input").prop("checked");
    var listar = objeto.parent().siblings().eq(5).find("input").prop("checked");

    var datosFormulario = "&id_permiso=" + id_permiso
            + "&agregar=" + agregar + "&modificar=" + modificar
            + "&eliminar=" + eliminar + "&listar=" + listar;

    var pDatosFormulario = datosFormulario;
    var pUrl = 'permiso/modificar';
    var pBeforeSend = '';
    var pSuccess = 'modificar_permiso_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function modificar_permiso_ajax_success(json) {
    var id_rol = $("#id_rol").val();
    if (json.modificado) {
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: Se modifico el Registro', 'Aceptar', 'buscar_nombrePermiso_ajax(' + id_rol + ')');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error: No se ha podido modificar el Registro', 'Aceptar', '');
    }

}

var id_permiso;
function recuperar_idpermiso(objeto) {
    id_permiso = objeto.parent().siblings().eq(0).html();
    confirmar_mensaje('CONFIRMACIÓN', '¿Esta seguro de Eliminar?', 'SI', 'eliminar_permiso_ajax(' + id_permiso + ')');
}

function eliminar_permiso_ajax(id_permiso) {
    var pDatosFormulario = "&id_permiso=" + id_permiso;
    var pUrl = 'permiso/eliminar';
    var pBeforeSend = '';
    var pSuccess = 'eliminar_permiso_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function eliminar_permiso_ajax_success(json) {
    buscar_nombrePermiso_ajax($("#id_rol").val());
}

// Roles
function buscarIdRol() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/buscarIdRol.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#nombre_rol").val(json.nombre_rol);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscar_nombreRol_ajax() {
    var pDatosFormulario = $("#formBuscar").serialize();
    var pUrl = 'rol/buscarNombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_nombreRol_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombreRol_ajax_success(json) {
    $("#contenidoBusqueda").html(json.contenido);
    $("#contenidoBusqueda tr").on("click", function () {
        var id = $(this).find("td:first").html();
        var nombre = $(this).find("td").eq("1").html();
        $("#panelBuscar").html("");
        $("#id_rol").val(id);
        $("#nombre_rol").val(nombre);
        $("#buscar").fadeOut("slow");
        $("#panelPrograma").fadeIn("slow");
        buscar_nombrePermiso_ajax($("#id_rol").val());
    });
}

// Formularios

function buscar_idformulario_ajax() {
    var id_formulario = $("#id_formulario").val();
    var pDatosFormulario = "&id_formulario=" + id_formulario;
    var pUrl = 'formulario/buscarId';
    var pBeforeSend = '';
    var pSuccess = 'buscar_idformulario_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_idformulario_ajax_success(json) {
    $("#nombre_formulario").val(json.nombre_formulario);
}

function buscar_nombreformulario_ajax() {
    var id_rol = $("#id_rol").val();
    var pDatosFormulario = $("#formBuscar").serialize() + "&id_rol="+id_rol;
    var pUrl = 'formulario/buscarNombre';
    var pBeforeSend = '';
    var pSuccess = 'buscar_nombreformulario_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_nombreformulario_ajax_success(json) {
    $("#contenidoBusqueda").html(json.contenido);
    $("#contenidoBusqueda").fadeIn("slow");
    $("#contenidoBusqueda tr").on("click", function () {
        var id = $(this).find("td:first").html();
        var nombre_formulario = $(this).find("td").eq(1).html();
        $("#panelBuscar").html("");
        $("#id_formulario").val(id);
        $("#nombre_formulario").val(nombre_formulario);
        $("#buscar").fadeOut("slow");
        $("#panelPrograma").fadeIn("slow");
    });
}
