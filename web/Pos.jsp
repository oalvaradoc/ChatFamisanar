<%@page import="com.sun.xml.rpc.processor.modeler.j2ee.xml.string"%>
<%@page import="com.americas.bean.BeanLista"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='fmt'%>

<html style="" class=" js flexbox flexboxlegacy canvas canvastext webgl 
      no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets 
      rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations 
      csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent 
      video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths">
    <head>
        <meta charset="utf-8" content="text/html" contentType="text/html">

        <title>Chat Virtual - Famisanar</title>
        <link href="css/Site.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalertstyles.css" rel="stylesheet" type="text/css"/>
        <script src="js/modernizr-2.6.2.js" type="text/javascript"></script>
        <link href="css/icon.css" rel="stylesheet" type="text/css"/>
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script src="js/sweetalert.min.js" type="text/javascript"></script>
    </head>

    <style>
        #logimg{
            
            width: 100%;
        }
        #logosala{
            float: left;
            margin-left: 27px;
        }
        #contentForm{
            float: left;
            /*            border: blue 1px dashed;*/
            width: 40%;
            position: absolute;
        }
        #rowform{
            float: right;
            /* position: absolute; */
            /* padding-left: 10px; */
            margin-top: -223px;
            width: 85%;
            /* margin-right: -596px; */
            /*    border: 1px red dashed;*/
            margin-right: -462px;
            /*    margin-right: -190px;*/
            /*        border: 1px dashed green;*/
        }

        #contenInfo{
            width:800px;    
            height: 328px;
            /*            border: 1px red dashed;*/
            margin-left: -160px;
            display: block;
            text-align: justify;
        }

        #Dv_CompleteForm{
            float: right;
            /* margin-left: -150px; */
            margin-top: -276px;
            margin-right: -45px;
            width: 556px;
            /*    border: 1px red dashed;*/
        }

        footer{
            margin-top: 5%;
        }


        .input-field{
            margin-bottom: -15px;
        }
        #flogo{
            margin-left: 20%;
            width: 376px;
            height: 102px;
            margin-top: -12px;
        }
    </style>
    <body style="" onload="Validar()">

        <div class="container-fluid encabezadoapp" style="background-color: #135c95;">
            <div class="row">
                <div class="font-headerApp" style="float: left;margin-left: 115px;">
                    Bienvenido al Chat de EPS Famisanar
                </div>
                <img class="responsive-img" src="img/Logo_Famisanar.png" id="flogo">

            </div>
        </div>
        <div class="container mt15">
            &nbsp;

            <form  data-ajax="true"  data-ajax-loading="#progress" 
                   id="form0" method="post" class="" action="ServletValidaUsuarioPOS">    
                <div id="contenInfo">
                    <div class="row" id="logimg">
                        <div class="center-align">
                            <img class="responsive-img" id="logosala" accesskey="" src="img/Sala_PBS.jpg">

                        </div>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                    <div class="    " style="margin-top:1.5% !important;" id="contentForm">
                        <div class="col s12 l6 offset-l3">
                            <p style="text-align: left">
                            <h6> Se�or usuario le informamos que nuestro horario de atenci�n <strong> para el chat 
                                    del Plan de Beneficios en Salud (PBS) </strong> es de lunes a viernes de 7:30 am a 5:00 pm. </h6>
                            </p>
                            <p>
                                <strong>
                                    <h6>  Para comenzar, por favor diligencie el siguiente formulario y haga clic en Consultar y luego en 
                                        Iniciar Sesi�n</h6>                
                                </strong>
                            </p>
                        </div>
                    </div>
                    <div class="row" id="rowform">
                        <div class="input-field col s12 m3 l3" id="tipoDocumentoValidacion" style="width:30%">
                            <%
                                ArrayList<BeanLista> ListaTipoDocumento = (ArrayList<BeanLista>) request.getAttribute("listaDocumentoValida");
                                ArrayList<BeanLista> listaRequerimiento = (ArrayList<BeanLista>) request.getAttribute("listaRequerimiento");

                                String Id = request.getAttribute("selectDocumento").toString();
                            %>

                            <select name="tipoDocumentoValida" id="tipoDocumentoValida" 
                                    class="validate initialized" required="" aria-required="true" 
                                    data-tooltip-id="7cb15ad5-3725-0e23-d2ba-ed73c481cabf" placeholder="Tipo Documento"
                                    style="display: block; height: 0px; padding: 0px; width: 0px; position: absolute;"
                                    >
                                <option value="" disabled selected>Seleccione una opcion</option>
                                <%
                                    if (ListaTipoDocumento != null) {
                                        for (BeanLista l : ListaTipoDocumento) {
                                            if (Id.equals(l.getValor())) {
                                %>
                                <option value = "<%= l.getValor()%>" selected><%= l.getValor()%></option> 
                                <%
                                } else {
                                %>
                                <option value = "<%= l.getValor()%>"><%= l.getValor()%></option>   
                                <%          }
                                        }
                                    }
                                %>

                            </select>
                            <label for="documentType-select">Tipo Documento</label>
                        </div>

                        <div class="input-field col s12 m3 l3" id="identificacionValidacion">
                            <input id="identificacionValida" name="identificacionValida" 
                                   type="number" min="0" required="" aria-required="true" 
                                   class="tooltipped quantity" data-position="bottom" data-delay="50" 
                                   data-tooltip="Por favor ingrese un n�mero de documento" 
                                   data-tooltip-id="8b7382bd-1a94-b43f-cdd0-98b179a48722"
                                   placeholder="N�mero Documento"
                                   value="<fmt:out value="${identificacionValidado}" />"
                                   >
                            <label for="txt_document"  class="">N�mero Documento</label>

                        </div>
                        <div class="input-field col s12 m3 l3 center-align">
                            <button class="btn start-session-Chat waves-effect waves-light btn-small" 
                                    style="margin-top: 4% !important;" type="submit" value="submit" 
                                    id="btn_Consultar" name="btn_Consultar">
                                Consultar
                            </button>
                        </div>
                    </div>
                    <input name="Validador" id="Validador" value = "<fmt:out value="${hide}" />"
                           style="display:none">
                    <input name="Sesion" id="Sesion" value = "<fmt:out value="${Sesion}" />"
                           style="display:none">
                    <input name="Horario" id="Horario" value = "<fmt:out value="${Horario}" />"
                           style="display:none">
                </div>
            </form>

            <form action="ServletIngresaUsuarioPOS" data-ajax="true" id="form1" method="post" style="display:none" >    
                <input type="hidden" name="btn_Consultars" id="btn_Consultars">
                <input type="hidden" name="identificacionValidas" id="identificacionValidas">
                <input type="hidden" name="tipoDocumentoValidas" id="tipoDocumentoValidas" value="">
                <input name="IdCliente" id="IdCliente" value = "<fmt:out value="${IdCli}" />"
                       style="display:none">
                <div id="Dv_CompleteForm" name ="Dv_CompleteForm">
                    <div class="row">
                        <div class="input-field col s12 m6 l6">
                            <select id="ddl_requerimiento" name="ddl_requerimiento"
                                    class="validate initialized" required="" aria-required="true" 
                                    data-tooltip-id="7cb15ad5-3725-0e23-d2ba-ed73c481cabf" placeholder="Tipo Documento"
                                    style="display: block; height: 0px; padding: 0px; width: 0px; position: absolute;" 
                                    onchange="MostrarOtros()">
                                <option value="" disabled selected>Seleccione una opcion</option>
                                <%
                                    if (listaRequerimiento != null) {
                                        for (BeanLista l : listaRequerimiento) {
                                %>
                                <option value = "<%= l.getId()%>"><%= l.getValor()%></option>   
                                <%
                                        }
                                    }
                                %>
                            </select>
                            <label for="documentType-select">Seleccione el requerimiento</label>
                        </div>
                        <div class="input-field col s12 m6 l6" id = "otrosMostrar" name="otrosMostrar" style="display:none">
                            <select class="validate initialized" id="ddl_requerimientos" name="ddl_requerimientos" 
                                    required="" aria-required="true" size="5" data-tooltip="Por favor ingrese el campo" 
                                    data-tooltip-id="9e2e65e4-a5a7-8b41-cf5c-5d858c1e94413651" placeholder="Seleccione"
                                    >
                                <option value="" disabled selected>Seleccione una opcion</option>
                                <%
                                    ArrayList<BeanLista> listaRequerimientoPAC = (ArrayList<BeanLista>) request.getAttribute("listaRequerimientoPAC");

                                    if (listaRequerimientoPAC != null) {
                                        for (BeanLista l : listaRequerimientoPAC) {
                                %>
                                <option value = "<%= l.getId()%>"><%= l.getValor()%></option>   
                                <%
                                        }
                                    }
                                %>
                            </select>
                            <label>Seleccione</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6 l6">
                            <input id="nombreUsuario" onkeypress="" name="nombreUsuario" 
                                   type="text" class="tooltipped" required="" aria-required="true" data-position="bottom" 
                                   data-delay="50" data-tooltip="Por favor ingrese los Nombres y Apellidos" 
                                   value="<fmt:out value="${cli_Nombre1}" />"  placeholder="Nombres y Apellidos"
                                   >
                            <label for="txt_nombreCiudadadano">Nombres y Apellidos</label>
                        </div>

                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6 l6">
                            <input id="correo" type="email" class="validate tooltipped" 
                                   name="correo"
                                   onkeypress="this.value = this.value.toLowerCase();" 
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$" 
                                   data-position="bottom" data-delay="50" placeholder="Correo Electr�nico"
                                   data-tooltip="Por favor ingrese el correo electr�nico vigente de contacto" 
                                   value="<fmt:out value="${cli_Email}" />"
                                   >
                            <label for="email">Correo electr�nico</label>
                        </div>
                    </div>
                    <div class="row center-align">            
                        <div class="input-field col s12 m12 l12" id="div-container-envioInfoCorreo">
                            <input type="checkbox" id="cb_habeasEnvioCorreo" name="cb_habeasEnvioCorreo" onclick="Aceptacion()">
                            <label for="cb_habeasEnvioCorreo" style="margin-top: -24px;">�enviar la conversaci�n al correo electr�nico?</label>
                            <input type="hidden" name="envioConversacionEmail" id="envioConversacionEmail" >
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px;">
                        <div class="input-field col s12 m6 l6 offset-l5 offset-m5 center-on-small-only">
                            <button class="btn start-session-Chat waves-effect waves-light btn-large" 
                                    type="submit" value="submit" style="margin-left: -41px;">
                                Iniciar Sesi�n
                            </button>
                        </div>
                    </div>
                </div>

            </form>

        </div>

        <script src="js/jquery-2.1.2.min.js" type="text/javascript"></script>
        <script src="js/materialize.min.js" type="text/javascript"></script>
        <script type="text/javascript">
                //Desarrollador
                //CC 1010200410
                //Omar Alvarado Castillo
                                var i = 0;

                                function MostrarOtros()
                                {
                                    var valor = $("#ddl_requerimiento").val();


                                    if (valor == 1)
                                    {

                                        $("#otrosMostrar").css("display", "block");
                                    } else
                                    {
                                        $("#otrosMostrar").css("display", "none");
                                    }
                                }

                                function Validar()
                                {
  
                                    validaHorario();

                                    var validador = $("#Validador").val();
                                    var Sesion = $("#Sesion").val();
                            
                                    if (Sesion == "false") 
                                    {
                                        swal
                                            (
                                                    "�Atenci�n!",
                                                    "Le informamos que tiene una sesi�n activa por favor intente nuevamente despu�s de 10 minutos",
                                                    "info" 
                                                    ).then((value) => {
                                        location.href = "http://www.famisanar.com.co/";
                                       });
                                       validador = "No mostrar";
                                    } 
                                    
                                    if (validador == "Mostrar")
                                    {
                                        $("#form1").css("display", "block");
                                        $("#identificacionValidas").val($("#identificacionValida").val());
                                        $("#tipoDocumentoValidas").val($("#tipoDocumentoValida").val());
                                    }
                                }

                                function Aceptacion()
                                {

                                    var porId = document.getElementById("cb_habeasEnvioCorreo").checked;
                                    if (porId) {
                                        console.log('si obligatorio');
                                        $("#correo").attr("required", "required");
                                    } else {
                                        console.log('no obligatorio');
                                        $("#correo").removeAttr("required");
                                    }

                                    $("#envioConversacionEmail").val(porId);
                                }

                                function Mensaje()
                                {
                                    var tiempoSalaHoraInicio = "7";
                                    var tiempoSalaMintutosInicio = "30";

                                    var tiempoSalaHoraFin = "17";

                                    var control = new Date();

                                    var horaactual = control.getHours();
                                    var minutoactual = control.getMinutes();

                                    var diactual = control.getUTCDay();

                                    if (diactual == 0 || diactual == 6)
                                    {
                                        MensajeAlerta();
                                    } else
                                    {
                                        if (horaactual > tiempoSalaHoraInicio)
                                        {
                                            if (horaactual >= tiempoSalaHoraFin)
                                            {
                                                MensajeAlerta();
                                            }
                                        } else
                                        {
                                            if (horaactual == tiempoSalaHoraInicio)
                                            {
                                                if (minutoactual < tiempoSalaMintutosInicio)
                                                {
                                                    MensajeAlerta();
                                                }
                                            }
                                            if (horaactual < tiempoSalaHoraInicio)
                                            {
                                                MensajeAlerta();
                                            }
                                        }
                                    }
                                }

                                function MensajeAlerta()
                                {
                                    swal
                                            (
                                                    "�Atenci�n!",
                                                    "Le informamos que nuestro horario de atenci�n para el chat del PLAN OBLIGATORIO DE SALUD (POS)  es de lunes a viernes de 7:30 am a 5:00 pm.",
                                                    "info"
                                                    ).then((value) => {
                                        location.href = "http://www.famisanar.com.co/";
                                    });

                                }
                                
                                function validaHorario()
                                {
                                    var Horario = $("#Horario").val();
                                    
                                    if (Horario == "false") 
                                    {
                                        MensajeAlerta()
                                    }
                                    
                                }

                                $(document).ready(function () {
                                    $('select').material_select();
                                });
        </script>

        <div class="material-tooltip" id="7cb15ad5-3725-0e23-d2ba-ed73c481cabf">
            <span></span>
            <div class="backdrop" style="top: 0px; left: 0px;"></div>
        </div>
        <div class="material-tooltip" id="7cb15ad5-3725-0e23-d2ba-ed73c481cab7">
            <span></span>
            <div class="backdrop" style="top: 0px; left: 0px;"></div>
        </div>
        <div class="material-tooltip" id="8b7382bd-1a94-b43f-cdd0-98b179a48722" style="opacity: 0; margin-top: 0px; margin-left: 0px; display: none; left: 396.031px; top: 406.344px;">
            <span>Por favor ingrese un n�mero de documento</span>
            <div class="backdrop" style="top: 0px; left: 0px; opacity: 0; transform: scale(1); display: none; margin-left: 150.5px;"></div>
        </div>
        <div class="material-tooltip" id="458e29d4-29f9-4712-c9be-cd59df8bd016">
            <span>Por favor ingrese los Nombres y Apellidos del Ciudadano</span>
            <div class="backdrop" style="top: 0px; left: 0px;"></div>
        </div><div class="material-tooltip" id="71912dca-8d5e-8ea2-be65-ba1711c89d94">
            <span>Por favor ingrese el correo electr�nico vigente de contacto</span>
            <div class="backdrop" style="top: 0px; left: 0px;"></div>
        </div>
        <div class="material-tooltip" id="69fc82c1-2295-4c4f-be2c-6c8cf8901ab6">
            <span>Por favor ingrese un n�mero de contacto</span>
            <div class="backdrop" style="top: 0px; left: 0px;"></div>
        </div><div class="material-tooltip" id="4c67f8dd-440d-3a7c-716b-fd5418e5af44">
            <span></span>
            <div class="backdrop" style="top: 0px; left: 0px;"></div>
        </div><div class="material-tooltip" id="9e2e65e4-a5a7-8b41-cf5c-5d858c1e9449">
            <span></span>
            <div class="backdrop" style="top: 0px; left: 0px;"></div>
        </div>
        <div class="hiddendiv common"></div>

        <footer class="footer-app">
            <div class="row">
                <div>
                    <p>
                        <a href="http://www.famisanar.com.co" class="footer-logo"> 
                            <img src="img/footer-logo.png" alt="" style="width: 222px;">
                        </a>
                    </p>
                    � Todos los derechos reservados. Am�ricas B.P.S (2018)
                </div>
            </div>
        </footer>

    </body>
</html>