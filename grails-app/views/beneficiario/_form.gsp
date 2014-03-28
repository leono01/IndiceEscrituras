<%@ page import="lion.Beneficiario" %>



<div class="fieldcontain ${hasErrors(bean: beneficiarioInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="beneficiario.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${beneficiarioInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: beneficiarioInstance, field: 'escritura', 'error')} required">
	<label for="escritura">
		<g:message code="beneficiario.escritura.label" default="Escritura" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="escritura" name="escritura.id" from="${lion.Escritura.list()}" optionKey="id" required="" value="${beneficiarioInstance?.escritura?.id}" class="many-to-one"/>
</div>

