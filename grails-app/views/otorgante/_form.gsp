<%@ page import="lion.Otorgante" %>



<div class="fieldcontain ${hasErrors(bean: otorganteInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="otorgante.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${otorganteInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: otorganteInstance, field: 'escritura', 'error')} required">
	<label for="escritura">
		<g:message code="otorgante.escritura.label" default="Escritura" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="escritura" name="escritura.id" from="${lion.Escritura.list()}" optionKey="id" required="" value="${otorganteInstance?.escritura?.id}" class="many-to-one"/>
</div>

