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
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
                                    <h5>Otorgantes</h5>        
                                    <table class="table table-bordered table-striped">
                                      <thead>
                                        <tr>
                                          <th>Nombre</th>
                                          <th></th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                      <g:if test="${escrituraInstance.otorgantes}">
                                      <g:each var="otorgante" in="${escrituraInstance.otorgantes}" status="o">
                                        <tr>
                                          <td>
                                            ${otorgante.nombreOtorgante}              
                                          </td>            
                                          <td>
                                        <g:form action="capturarEscritura">            
                                          <g:hiddenField name="escrituraId" value="${escrituraInstance.id}"/>
                                          <g:hiddenField name="otorganteId" value="${otorgante.id}"/>
                                          <g:submitButton 
                                            class="btn btn-danger btn-mini"
                                            name="eliminarOtorgante" 
                                            value="Eliminar"/>
                                        </g:form>
                                        </td>
                                        </tr>
                                      </g:each>
                                      </g:if>
                                      <g:form action="capturarEscritura">
                                          <g:hiddenField name="escrituraId" value="${escrituraInstance.id}"/>
                                        <tr>
                                          <td>
                                            <g:textField class="span3" name="nombreOtorgante" value=""/>                                                        
                                          </td>
                                          <td>                
                                            <g:submitButton class="btn btn-primary btn-mini" name="agregarOtorgante" value="Agregar" />
                                          </td>
                                        </tr>
                                      </g:form>
                                      </tbody>
                                    </table>

                                    <h5>Beneficiarios</h5>        
                                    <table class="table table-bordered table-striped">
                                      <thead>
                                        <tr>
                                          <th>Nombre</th>
                                          <th></th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                      <g:if test="${escrituraInstance.beneficiarios}">
                                      <g:each var="beneficiario" in="${escrituraInstance.beneficiarios}" status="o">
                                        <tr>
                                          <td>
                                            ${beneficiario.nombreBeneficiario}              
                                          </td>            
                                          <td>
                                        <g:form action="capturarEscritura">            
                                          <g:hiddenField name="escrituraId" value="${escrituraInstance.id}"/>
                                          <g:hiddenField name="beneficiarioId" value="${beneficiario.id}"/>
                                          <g:submitButton 
                                            class="btn btn-danger btn-mini"
                                            name="eliminarBeneficiario" 
                                            value="Eliminar"/>
                                        </g:form>
                                        </td>
                                        </tr>
                                      </g:each>
                                      </g:if>
                                      <g:form action="capturarEscritura">
                                          <g:hiddenField name="escrituraId" value="${escrituraInstance.id}"/>
                                        <tr>
                                          <td>
                                            <g:textField class="span3" name="nombreBeneficiario" value=""/>                                                        
                                          </td>
                                          <td>                
                                            <g:submitButton class="btn btn-primary btn-mini" name="agregarBeneficiario" value="Agregar" />
                                          </td>
                                        </tr>
                                      </g:form>
                                      </tbody>
                                    </table>
				</fieldset>
				<fieldset class="buttons">
                                    <g:submitButton name="atras" class="alert" value="Atras" />
                                    <g:submitButton name="create" class="save" value="Crear" />                                        
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
