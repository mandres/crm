function inicializar_formulario() {
    verificar_sesion_ajax();

    $("#id_rol").focus();
    $("#id_rol").select();
    habilitar_agregar();

    siguiente_campo("#id_rol", "#nombre_rol", false);

    $("#id_rol").focusout(function (event) {
        if ($("#id_rol").val() === "") {
            $("#id_rol").val("0");
        }
        buscarIdRol();
    });
    $("#botonBuscarIdRol").on('click', function () {
        $("#buscar").load("frm/crm/definiciones/roles/buscarRol.html");
        $("#panelPrograma").fadeOut(100, function () {
            $("#buscar").fadeIn(100);
        });
    });
    $("#boton-agregar").on('click', function () {
        agregarRol();
    });
    $("#boton-modificar").on('click', function () {
        modificarRol();
    });
    $("#boton-eliminar").on('click', function () {
        confirmar_mensaje('Mensaje del Sistema', 'Esta seguro de eliminar este Rol?.', 'Aceptar', 'eliminarRol()');
    });
    $("#boton-salir").on('click', function () {
        $('aside').html("");
    });
}

function buscarIdRol() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'rol/buscarId',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_rol").val(json.id_rol);
            $("#nombre_rol").val(json.nombre_rol);

            $("#nombre_rol").focus();
            $("#nombre_rol").select();

            if (json.id_rol === 0) {
                habilitar_agregar();
            } else {
                deshabilitar_agregar();
            }
        },
        error: function (e) {
            mostrar_mensaje('Mensaje del Sistema', 'ERROR: No se puede conectar al servidor.', 'Aceptar', 'return true');
        },
        complete: function (objeto, exito, error) {
        }
    });
}

function buscarNombreRol() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'rol/buscarNombre',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
        },
        error: function (e) {
            mostrar_mensaje('Mensaje del Sistema', 'ERROR: No se puede conectar al servidor.', 'Aceptar', 'return true');
        },
        complete: function (objeto, exito, error) {
        }
    });
}

function agregarRol() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'rol/agregar',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

        },
        success: function (json) {
            if (json.agregado) {
                mostrar_mensaje('Mensaje del Sistema', 'AGREGADO: El rol fue Insertado', 'Aceptar', '');
                $("#id_rol").focus;
            }
        },
        error: function (e) {
            mostrar_mensaje('Mensaje del Sistema', 'ERROR: No se puede conectar al servidor.', 'Aceptar', 'return true');
        },
        complete: function (objeto, exito, error) {
        }
    });
}

function modificarRol() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'rol/modificar',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

        },
        success: function (json) {
            if (json.modificado) {
                mostrar_mensaje('Mensaje del Sistema', 'MODIFICADO: El rol fue Modificado', 'Aceptar', '');
                $("#id_rol").focus;
            }
        },
        error: function (e) {
            mostrar_mensaje('Mensaje del Sistema', 'ERROR: No se puede conectar al servidor.', 'Aceptar', 'return true');
        },
        complete: function (objeto, exito, error) {
        }
    });
}

function eliminarRol() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'rol/eliminar',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

        },
        success: function (json) {
            if (json.eliminado) {
                mostrar_mensaje('Mensaje del Sistema', 'ELIMINADO: El rol fue Eliminado', 'Aceptar', '');
                $("#id_rol").focus;
            }
        },
        error: function (e) {
            mostrar_mensaje('Mensaje del Sistema', 'ERROR: No se puede conectar al servidor.', 'Aceptar', 'return true');
        },
        complete: function (objeto, exito, error) {
        }
    });
}

function seleccionarRol($this) {
    var id = $this.find("td").eq(0).text();
    var nombre = $this.find("td").eq(1).text();
    $("#panelBuscar").html("");

    $("#id_rol").val(id);
    $("#nombre_rol").val(nombre);

    $("#buscar").fadeOut(100, function () {
        $("#panelPrograma").fadeIn(100, function () {
            $("#nombre_rol").focus();
            $("#nombre_rol").select();
        });
    });
    deshabilitar_agregar();
}

function inicializar_buscarRol() {
    buscarNombreRol();
    $("#bnombre_rol").focus();
    siguiente_campo("#bnombre_rol", "#botonBuscar", false);
    $("#botonBuscar").on("click", function () {
        $("#contenidoBusqueda").html("");
        $("#bpagina").val("1");
        buscarNombreRol();
        $("#bnombre_rol").focus();
        $("#bnombre_rol").select();
    });
    $("#anterior").on("click", function () {
        $("#contenidoBusqueda").html("");
        var pag = parseInt($("#bpagina").val());
        if (pag > 1) {
            $("#bpagina").val(pag - 1);
        }
        buscarNombreRol();
    });
    $("#siguiente").on("click", function () {
        $("#contenidoBusqueda").html("");
        var pag = parseInt($("#bpagina").val());
        $("#bpagina").val(pag + 1);
        buscarNombreRol();
    });

    $("#botonSalirBuscar").on('click', function () {
        $("#buscar").fadeOut(100, function () {
            $("#panelPrograma").fadeIn(100);
        });
    });
}