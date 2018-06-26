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
    <title>Encuesta - Famisanar</title>
    <!--<link href="css/autoptimize.css" rel="stylesheet" type="text/css" media="all"/>-->
    <link href="css/Site.css" rel="stylesheet" type="text/css"/>
    <link href="css/sweetalertstyles.css" rel="stylesheet" type="text/css"/>
    <script src="js/modernizr-2.6.2.js" type="text/javascript"></script>
    <link href="css/icon.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="js/sweetalert.min.js" type="text/javascript"></script>
</head>  
        <%
                ArrayList<BeanLista> listaEncuesta = (ArrayList<BeanLista>) request.getAttribute("listaEncuesta");
                int tamanoEncuesta = 0;
                if (listaEncuesta != null) { tamanoEncuesta = listaEncuesta.size();}
        %>
        <body style="" onfocus="">
    
    <div class="container-fluid encabezadoapp" style="background-color: #135c95;">
        <div class="row">
            <img class="responsive-img" src="img/logo.png" style="position: absolute;">
            <div class="font-headerApp" style="margin-left: 35.2%;">
                Encuesta de Satisfacción
            </div>
        </div>
    </div>
    <div class="container mt15">
        &nbsp;
        <div class="row">
            <div class="col s12">
                <blockquote>
                    Para nosotros es muy importante conocer su opinión sobre la calidad de nuestro servicio 
                    por eso lo invitamos a responder <%= tamanoEncuesta %> preguntas:
                </blockquote>
            </div>
        </div>
        <div class="row col s12 m12 l6">
            <div>
                <h5 class="header bold f-s-24">
                    En una escala de 1 a 4 donde: 1. Excelente 2. Bueno 3. Regular 4. Malo 
                </h5>
                <hr>
            </div>
        </div>
        <form  data-ajax="true"  data-ajax-loading="#progress" 
        id="form0" method="post" class="" action="ServletIngresaEncuesta">  


                <%
                    if (listaEncuesta != null) 
                        {
                            int i = 0;
                            int aumento = 0;
                                for(BeanLista l : listaEncuesta)
                                {
                                %>
                                
                                <div class="row">
                                    <div class="col s12 m8 l8" >
                                        <%= l.getValor() %>
                                    </div>
                                    <% aumento++; %>
                                    <div class="col s12 m4 l4">
                                        <span class="p-object-radio"></span>
                                        <% i++; %>
                                        <p class="p-object-radio">
                                            <input name="<%= aumento %>-question" type="radio" id="f-q-<%= i %>" value="1">
                                            <label for="f-q-<%= i %>" >
                                                1
                                            </label>
                                        </p>
                                        <% i++; %>
                                        <p class="p-object-radio">
                                            <input name="<%= aumento %>-question" type="radio" id="f-q-<%= i %>" value="2">
                                            <label for="f-q-<%= i %>">
                                                2
                                            </label>
                                        </p>
                                        <% i++; %>
                                        <p class="p-object-radio">
                                            <input name="<%= aumento %>-question" type="radio" id="f-q-<%= i %>" value="3">
                                            <label for="f-q-<%= i %>">
                                                3
                                            </label>
                                        </p>
                                        <% i++; %>
                                        <p class="p-object-radio">
                                            <input name="<%= aumento %>-question" type="radio" id="f-q-<%= i %>" value="4">
                                            <label for="f-q-<%= i %>">
                                                4
                                            </label>
                                        </p>
                                    </div>
                                </div>
                <%
                                   }
                        }
                %>
            <div class="row">
                <div class="col s12 center-align">
                    <div class="input-field col s12 l4 offset-l4">
                        <button class="btn start-session-btn waves-effect waves-light btn-large" onclick="Mensaje(); return false;">
                            GUARDAR RESPUESTAS
                        </button>
                    </div>
                </div>
            </div>
           <input name="identiContacto" id="identiContacto" value = "<fmt:out value="${idContacto}" />"
           style="display:none">
           <input name="identiRequeri" id="identiRequeri" value = "<fmt:out value="${idRequerimiento}" />"
           style="display:none">
           <input name="identiGenes" id="identiGenes" value = "<fmt:out value="${IdGenesys}" />"
           style="display:none">
        </form>
    </div>
    <script src="js/jquery-2.1.2.min.js" type="text/javascript"></script>
    <script src="js/materialize.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    var n;
    var countChecked = function() 
    {
    n = $( "input:checked" ).length;
    };
    countChecked();
 
    $( "input[type=checkbox]" ).on( "click", countChecked );
        
    function Mensaje()
    {
            n = $( "input:checked" ).length;
            
            if (n<4) 
            {
                swal("¡Atención!", "Por favor realice la calificación antes de almacenar datos...", "info");
            }
            else
            {
                swal("Satisfactorio!", "¡Gracias por permitirnos conocer su opinión sobre nuestro servicio!", "success")
                .then((value) => {
                $("#form0").submit();
              });
            }

        }
        
    </script>

</body>
    
</html>