
<%@ page import="lion.Beneficiario" %>
<%@ page import="lion.Escritura" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'beneficiario.label', default: 'Beneficiario')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-beneficiario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-beneficiario" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list beneficiario">
			
				<g:if test="${beneficiarioInstance?.nombreBeneficiario}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="beneficiario.nombreBeneficiario.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${beneficiarioInstance}" field="nombreBeneficiario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escrituraInstance?.beneficiarios}">
				<li class="fieldcontain">
					<span id="escritura-label" class="property-label"><g:message code="beneficiario.escritura.label" default="Escritura" /></span>
                                            <g:each var="beneficiario" in="${escrituraInstance.beneficiarios}">
						<span class="property-value" aria-labelledby="escritura-label"><g:link controller="escritura" action="show" id="${beneficiario.id}">${beneficiario?.nombreBeneficiario} encodeAsHTML()}</g:link></span>
                                            </g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${beneficiarioInstance?.id}" />
					<g:link class="edit" action="edit" id="${beneficiarioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
