<%@ page import="lion.Beneficiario" %>



<div class="fieldcontain ${hasErrors(bean: beneficiarioInstance, field: 'nombreBeneficiario', 'error')} ">
	<label for="nombre">
		<g:message code="beneficiario.nombreBeneficiario.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${beneficiarioInstance?.nombreBeneficiario}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: beneficiarioInstance, field: 'escritura', 'error')} required">
	<label for="escritura">
		<g:message code="beneficiario.escritura.label" default="Escritura" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="escritura" name="escritura.id" from="${lion.Escritura.list()}" optionKey="id" required="" value="${beneficiarioInstance?.escritura?.id}" class="many-to-one"/>
</div>

