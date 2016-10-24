function inicializar_formulario() {
    verificar_sesion_ajax();
    combo_seccion_ajax();
    limpiar_formulario();

    siguiente_campo("#id_vendedor", "#nombre_vendedor", false);
    siguiente_campo("#nombre_vendedor", "#id_seccion", false);
    siguiente_campo("#id_seccion", "#id_usuario", false);

// Acciones de Botones

    $("#id_usuario").on("change", function () {
        buscar_id_usuario();
    });
    $("#boton-buscar-vendedor").on("click", function () {
        buscar_vendedor($(this));
    });
    $("#boton-buscar-usuario").on("click", function () {
        buscar_usuario($(this));
    });
    $("#boton-agregar").on("click", function () {
        if (validar_formulario()) {
            agregar_usuario_ajax();
        }
    });
    $("#boton-modificar").on("click", function () {
        if (validar_formulario()) {
            modificar_ajax();
        }
    });
    $("#boton-eliminar").on("click", function () {
        confirmar("Mensaje del Sistema", "Esta seguro de eliminar este registro?", "Eliminar");
        $("#botonConfirmar").on("click", function () {
            eliminar_ajax();
        });
    });
    $("#boton-retornar").on("click", function () {
        $('aside').html("");
    });
}
//Cargar Seccion

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

// buscar USUARIOS

function buscar_vendedor($this) {
    $this.parent().parent().after("<div class='row'><div id='buscar-vendedor' class='col col-md-12 border-color'></div></div>");
    $("#buscar-vendedor").load("frm/crm/definiciones/vendedores/buscarVendedores.html", function () {
        buscar_nombre_vendedor_ajax();
    });
    $("#boton-buscar-vendedor").prop("disabled", true);
}

function buscar_nombre_vendedor_ajax() {
    var pDatosFormulario = '';
    var pUrl = 'vendedor/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscarNombre_vendedor_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscarNombre_vendedor_ajax_success(json) {
    $("#tbodyDatos").html(json.tabla);
}

function seleccionar_vendedor($this) {
    var id_vendedor = $this.find("td").eq(0).text();
    var nombre_vendedor = $this.find("td").eq(1).text();
    var id_seccion = $this.find("td").eq(2).text();
    var id_usuario = $this.find("td").eq(4).text();

    $("#id_vendedor").val(id_vendedor);
    $("#nombre_vendedor").val(nombre_vendedor);
    $("#id_seccion").val(id_seccion);
    $("#id_usuario").val(id_usuario);

    $("#buscar-vendedor").parent().remove();
    $("#boton-buscar-vendedor").prop("disabled", false);
    deshabilitar_agregar();
}
//Buscar usuario

function buscar_usuario($this) {
    $this.parent().parent().after("<div class='row'><div id='buscar-usuario' class='col col-md-12 border-color'></div></div>");
    $("#buscar-usuario").load("frm/crm/definiciones/vendedores/buscarUsuarios.html", function () {
        buscar_nombre_usuario_ajax();
    });
    $("#boton-buscar-usuario").prop("disabled", true);
}

function buscar_nombre_usuario_ajax() {
    var pDatosFormulario = '';
    var pUrl = 'usuario/buscar/nombre';
    var pBeforeSend = '';
    var pSuccess = 'buscarNombre_usuario_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscarNombre_usuario_ajax_success(json) {
    var datos = "";
    $.each(json, function (key, value) {
        datos += "<tr onclick='seleccionar_usuario($(this))'>";
        datos += "<td>" + value.id_usuario + "</td>";
        datos += "<td>" + value.nombre_usuario + "</td>";
        datos += "<td>" + value.usuario_usuario + "</td>";
        datos += "</tr>";
    });
    $("#tbodyDatos").html(datos);
}

function seleccionar_usuario($this) {
    var id_usuario = $this.find("td").eq(0).text();
    $("#id_usuario").val(id_usuario);
    $("#buscar-usuario").parent().remove();
    $("#boton-buscar-usuario").prop("disabled", false);
  }

// agregar
function agregar_vendedor_ajax() {
    var pDatosFormulario = $('#form-formulario').serialize();
    var pUrl = 'vendedor/agregar';
    var pBeforeSend = '';
    var pSuccess = 'agregar_usuario_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
function agregar_usuario_ajax_success(json) {
    if (json.ok) {
        limpiar_formulario();
        mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: El usuario fue Insertado', 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', json.mensaje, 'Aceptar', '');
    }
}

// modificar
function modificar_ajax() {
    var pDatosFormulario = $('#form-formulario').serialize();
    var pUrl = 'usuario/modificar';
    var pBeforeSend = '';
    var pSuccess = 'modificar_usuario_ajax_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function modificar_usuario_ajax_success(json) {
    if (json.ok) {
        limpiar_formulario();
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: Los Datos del Usuario fue Modificado', 'Aceptar', '');
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'Error:' + json.mensaje, 'Aceptar', '');
    }
}

// eliminar
function eliminar_ajax() {
    var datos_formulario = $('#form-formulario').serialize();
    $.ajax({
        type: 'POST',
        url: 'frm/crm/definiciones/usuarios/jsp/eliminarUsuario.jsp',
        data: datos_formulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $('#estadoServidor').css('display', 'block');
        },
        success: function (json) {
            if (json.ok) {
                $("#modalConfirmar").modal("hide");
                limpiar_formulario();
            } else {
                $("#mensajes").html("No se pudo recuperar los datos.");
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


// validacion del lado del cliente
function validar_formulario() {
    var ok = true;
    var id = $("#id_usuario").val();
    var nombre = $("#nombre_usuario").val();
    var usuario = $("#usuario_usuario").val();
    var clave = $("#clave_usuario").val();
    if (id === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo ID vacio', 'Aceptar', '');
        $("#id_usuario").focus();
        ok = false;
    } else if (nombre === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo NOMBRE vacio', 'Aceptar', '');
        $("#nombre_usuario").focus();
        ok = false;
    } else if (usuario === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo USUARIO vacio', 'Aceptar', '');
        $("#usuario_usuario").focus();
        ok = false;
    } else if (clave === "") {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: Campo CONTRASEÑA vacio', 'Aceptar', '');
        $("#clave_usuario").focus();
        ok = false;
    }
    return ok;
}

function cerrar_vendedor($this) {
    $this.parent().parent().parent().remove();
    $("#boton-buscar-vendedor").prop("disabled", false);
}

function limpiar_formulario() {
    $("#id_usuario").val("0");
    $("#nombre_usuario").val("");
    $("#usuario_usuario").val("");
    $("#clave_usuario").val("");
    habilitar_agregar();
    $("#id_usuario").focus();
    $("#id_usuario").select();
}
function agregar_linea() {
    $("#tbodyDetalle").prepend("<tr><td id='td-linea' colspan='4'></td></tr>");
    $("#td-linea").load("frm/crm/definiciones/usuarios/linea.html", function () {
        $("#id_usuariorol").val('0');
        $("#id_rol").focus();
        $("#id_rol").select();
    });
}
function editar_linea($this) {
    var id_usuariorol = $this.parent().parent().find("td").eq(0).text();
    var id_rol = $this.parent().parent().find("td").eq(1).text();
    var nombre_rol = $this.parent().parent().find("td").eq(2).text();
    $this.parent().parent().after("<tr><td id='td-linea' colspan='4'></td></tr>");
    $("#td-linea").load("frm/crm/definiciones/usuarios/linea.html", function () {
        $("#id_usuariorol").val(id_usuariorol);
        $("#id_rol").val(id_rol);
        $("#nombre_rol").val(nombre_rol);
        $("#id_rol").focus();
        $("#id_rol").select();
    });
}

function eliminar_linea($this) {
    var id_usuariorol = $this.parent().parent().find("td").eq(0).text();
    confirmar_mensaje('CONFIRMACIÓN', '¿Esta seguro de Eliminar?', 'SI', 'confirmarEliminar_linea_ajax(' + id_usuariorol + ')');
}
function confirmarEliminar_linea_ajax(id_usuariorol) {
    var pDatosFormulario = "&id_usuariorol=" + id_usuariorol;
    var pUrl = 'usuariorol/eliminar';
    var pBeforeSend = '';
    var pSuccess = 'confirmarEliminar_linea_ajax_success()';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}
function confirmarEliminar_linea_ajax_success() {
    var id_usuario = $("#id_usuario").val();
    usuariorolBuscarIdUsuario_ajax(id_usuario);
}

// UsuarioRoles

function buscar_rol($this) {
    $this.parent().parent().after("<div class='row'><div id='buscar-rol' class='col col-md-12'></div></div>");
    $("#buscar-rol").load("frm/crm/definiciones/usuarios/buscarRoles.html", function () {
        buscar_RolNoAsignado_ajax();
    });
    $("#boton-buscar-rol").prop("disabled", true);
}

function buscar_RolNoAsignado_ajax() {
    var id_usuario = $("#id_usuario").val();
    var pDatosFormulario = "&id_usuario=" + id_usuario;
    var pUrl = 'usuariorol/buscarRolNoAsignado';
    var pBeforeSend = '';
    var pSuccess = 'buscar_RolNoAsignado_success(json)';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function buscar_RolNoAsignado_success(json) {
    $("#tbodyDatos").html(json.detalle);
}

function seleccionar_rol($this) {
    var id = $this.find("td").eq(0).text();
    var nombre = $this.find("td").eq(1).text();
    $("#id_rol").val(id);
    $("#nombre_rol").val(nombre);
    $("#buscar-rol").parent().remove();
    $("#boton-buscar-rol").prop("disabled", false);
    $("#nombre_rol").focus();
}
function cerrar_rol($this) {
    $this.parent().parent().parent().remove();
    $("#boton-buscar-rol").prop("disabled", false);
}

function usuarioRol_agregar_ajax() {
    var id_usuario = $("#id_usuario").val();
    var id_rol = $("#id_rol").val();
    var pDatosFormulario = "&id_usuario=" + id_usuario + "&id_rol=" + id_rol;
    var pUrl = 'usuariorol/agregar';
    var pBeforeSend = '';
    var pSuccess = 'usuarioRol_agregar_ajax_success(json,' + id_usuario + ')';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function usuarioRol_agregar_ajax_success(json, id_usuario) {
    if (json.agregado) {
        mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: El Rol ha sido Agregado', 'Aceptar', '');
        usuariorolBuscarIdUsuario_ajax(id_usuario);
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: ' + json.mensaje, 'Aceptar', '');
    }
}

function usuarioRol_modificar_ajax() {
    var id_usuariorol = $("#id_usuariorol").val();
    var id_usuario = $("#id_usuario").val();
    var id_rol = $("#id_rol").val();
    var pDatosFormulario = "&id_usuario=" + id_usuario + "&id_rol=" + id_rol + "&id_usuariorol=" + id_usuariorol;
    var pUrl = 'usuariorol/modificar';
    var pBeforeSend = '';
    var pSuccess = 'usuarioRol_modificar_ajax_success(json,' + id_usuario + ')';
    var pError = 'ajax_error()';
    var pComplete = '';
    ajax(pDatosFormulario, pUrl, pBeforeSend, pSuccess, pError, pComplete);
}

function usuarioRol_modificar_ajax_success(json, id_usuario) {
    if (json.modificado) {
        mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: El Rol ha sido Modificado', 'Aceptar', '');
        usuariorolBuscarIdUsuario_ajax(id_usuario);
    } else {
        mostrar_mensaje('Mensaje del Sistema', 'ERROR: ' + json.mensaje, 'Aceptar', '');
    }
}

function habilitar_agregar() {
    $("#boton-agregar").prop("disabled", false);
    $("#boton-modificar").prop("disabled", true);
}

function deshabilitar_agregar() {
    $("#boton-agregar").prop("disabled", true);
    $("#boton-modificar").prop("disabled", false);
}


