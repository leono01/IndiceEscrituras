
<%@ page import="lion.Escritura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'escritura.label', default: 'Escritura')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-escritura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Mostrar listado</g:link></li>
				<li><g:link class="create" action="flujo">Capturar escritura</g:link></li>
			</ul>
		</div>
		<div id="show-escritura" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list escritura">
			
				<g:if test="${escrituraInstance?.numeroDeEscritura}">
				<li class="fieldcontain">
					<span id="numeroDeEscritura-label" class="property-label"><g:message code="escritura.numeroDeEscritura.label" default="Numero De Escritura" /></span>
					
						<span class="property-value" aria-labelledby="numeroDeEscritura-label"><g:fieldValue bean="${escrituraInstance}" field="numeroDeEscritura"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escrituraInstance?.nombreOperacion}">
				<li class="fieldcontain">
					<span id="nombreOperacion-label" class="property-label"><g:message code="escritura.nombreOperacion.label" default="Nombre Operacion" /></span>
					
						<span class="property-value" aria-labelledby="nombreOperacion-label"><g:fieldValue bean="${escrituraInstance}" field="nombreOperacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escrituraInstance?.volumen}">
				<li class="fieldcontain">
					<span id="volumen-label" class="property-label"><g:message code="escritura.volumen.label" default="Volumen" /></span>
					
						<span class="property-value" aria-labelledby="volumen-label"><g:fieldValue bean="${escrituraInstance}" field="volumen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escrituraInstance?.folios}">
				<li class="fieldcontain">
					<span id="folios-label" class="property-label"><g:message code="escritura.folios.label" default="Folios" /></span>
					
						<span class="property-value" aria-labelledby="folios-label"><g:fieldValue bean="${escrituraInstance}" field="folios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escrituraInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="escritura.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate  format="dd-MM-yy" date="${escrituraInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${escrituraInstance?.beneficiarios}">
				<li class="fieldcontain">
					<span id="beneficiarios-label" class="property-label"><g:message code="escritura.beneficiarios.label" default="Beneficiarios" /></span>
					
						<g:each in="${escrituraInstance.beneficiarios}" var="b">
						<span class="property-value" aria-labelledby="beneficiarios-label"><g:link controller="beneficiario" action="show" id="${b.id}">${b?.nombre.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${escrituraInstance?.otorgantes}">
				<li class="fieldcontain">
					<span id="otorgantes-label" class="property-label"><g:message code="escritura.otorgantes.label" default="Otorgantes" /></span>
					
						<g:each in="${escrituraInstance.otorgantes}" var="o">
						<span class="property-value" aria-labelledby="otorgantes-label"><g:link controller="otorgante" action="show" id="${o.id}">${o?.nombre.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${escrituraInstance?.id}" />
					<g:link class="edit" action="edit" id="${escrituraInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
