function buscarPermisos(id_rol) {
    var datosFormulario = "";
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $('#estadoServidor').css('display', 'block');
        },
        success: function (json) {
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
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            $('#estadoServidor').css('display', 'none');
        }
    });
}
function buscarNombrePermiso(id_rol) {
    var datosFormulario = "&id_rol=" + id_rol;
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#tbody-permisos").html(json.contenido);
            $("tbody-permisos tr").on("click", function () {
                var id = $(this).find("td:first").html();
                alert(id);
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarPermiso(id_rol, id_formulario, agregar, modificar, eliminar, listar) {
    var datosFormulario = "&id_rol=" + id_rol + "&id_formulario=" + id_formulario
            + "&agregar=" + agregar + "&modificar=" + modificar + "&eliminar=" + eliminar
            + "&listar=" + listar;
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $('#estadoServidor').css('display', 'block');
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            buscarNombrePermiso(id_rol);
            $("#id_rol").focus;
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $('#estadoServidor').css('display', 'none');
        }
    });
}
function modificarPermiso(objeto) {
    var id_permiso = objeto.parent().siblings().eq(0).html();
    var agregar = objeto.parent().siblings().eq(2).find("input").prop("checked");
    var modificar = objeto.parent().siblings().eq(3).find("input").prop("checked");
    var eliminar = objeto.parent().siblings().eq(4).find("input").prop("checked");
    var listar = objeto.parent().siblings().eq(5).find("input").prop("checked");

    var datosFormulario = "&id_permiso="+id_permiso
            +"&agregar="+agregar+"&modificar="+modificar
            +"&eliminar="+eliminar+"&listar="+listar;
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $('#estadoServidor').css('display', 'block');
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            buscarNombrePermiso($("#id_rol").val());
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $('#estadoServidor').css('display', 'none');
        }
    });
}
var id_permiso;
function recuperar_idpermiso(objeto){
    id_permiso = objeto.parent().siblings().eq(0).html();
}

function eliminarPermiso() {
    var datosFormulario = "&id_permiso="+id_permiso;
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $('#estadoServidor').css('display', 'block');
        },
        success: function (json) {
            buscarNombrePermiso($("#id_rol").val());
            $("#mensajes").html(json.mensaje);            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $('#estadoServidor').css('display', 'none');
        }
    });
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
function buscarNombreRol() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/buscarNombreRol.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda tr").on("click", function () {
                var id = $(this).find("td:first").html();
                var nombre = $(this).find("td").eq("1").html();
                $("#panelBuscar").html("");
                $("#id_rol").val(id);
                $("#nombre_rol").val(nombre);
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
                buscarNombrePermiso($("#id_rol").val());
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
// Formularios
function buscarIdFormulario() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/buscarIdFormulario.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#nombre_formulario").val(json.nombre_formulario);
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
function buscarNombreFormulario() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'archivos/crm/definiciones/permisos/jsp/buscarNombreFormulario.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
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
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
