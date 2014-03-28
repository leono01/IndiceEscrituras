<%@ page import="lion.Escritura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="Escritura" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-escritura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-escritura" class="content scaffold-create" role="main">
			<h1>Capture los datos de la escritura a guardar.</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${escrituraInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${escrituraInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
                        
                        <g:if test="${message}">
                            <div id="alert" class="alert">
                              <strong>¡Atención!</strong> ${message}
                            </div>
                        </g:if>  
                        
			<g:form action="capturarEscritura" >
				<fieldset class="form">
					<g:render template="/escritura/form"/>
				</fieldset>
                                <fieldset class="buttons" style="align:right">
					<g:submitButton name="create" class="save" value="Siguiente" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
