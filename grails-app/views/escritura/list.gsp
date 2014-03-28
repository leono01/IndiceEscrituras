
<%@ page import="lion.Escritura" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'escritura.label', default: 'Escritura')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <link rel="stylesheet" href="${resource(dir:'css',file:'pagination.css')}" />
	</head>
	<body>
		<a href="#list-escritura" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="flujo">Capturar escritura</g:link></li>
				
			</ul>
		</div>                               
                
		<div id="list-escritura" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numeroDeEscritura" title="${message(code: 'escritura.numeroDeEscritura.label', default: 'ESCRITURA')}" />
						
                                                <g:sortableColumn property="otorgantes" title="OTORGANTES" />
                                                
                                                <g:sortableColumn property="beneficiarios" title="BENEFICIARIOS" />
					
						<g:sortableColumn property="nombreOperacion" title="${message(code: 'escritura.nombreOperacion.label', default: 'OPERACIÓN')}" />
					
						<g:sortableColumn property="volumen" title="${message(code: 'escritura.volumen.label', default: 'VOLUMEN')}" />
					
						<g:sortableColumn property="folios" title="${message(code: 'escritura.folios.label', default: 'FOLIOS')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'escritura.fecha.label', default: 'FECHA')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${escrituraInstanceList}" status="i" var="escrituraInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${escrituraInstance.id}">${fieldValue(bean: escrituraInstance, field: "numeroDeEscritura")}</g:link></td>
                                                
                                                <td><table>
                                                      <tbody>
                                                        <g:if test="${escrituraInstance.otorgantes}">
                                                        <g:each var="otorgante" in="${escrituraInstance.otorgantes}" status="o">
                                                          <tr>                                                            
                                                            <td>
                                                                <label>${otorgante.nombre}</label>
                                                            </td>
                                                          </tr>
                                                        </g:each>
                                                        </g:if>
                                                      </tbody>
                                                    </table>
                                                </td>
                                                
                                                <td><table>
                                                      <tbody>
                                                        <g:if test="${escrituraInstance.beneficiarios}">
                                                        <g:each var="beneficiario" in="${escrituraInstance.beneficiarios}" status="o">
                                                          <tr>                                                            
                                                            <td>
                                                                <label>${beneficiario.nombre}</label>
                                                            </td>
                                                          </tr>
                                                        </g:each>
                                                        </g:if>
                                                      </tbody>
                                                    </table>
                                                </td>
					
						<td>${fieldValue(bean: escrituraInstance, field: "nombreOperacion")}</td>
					
						<td>${fieldValue(bean: escrituraInstance, field: "volumen")}</td>
					
						<td>${fieldValue(bean: escrituraInstance, field: "folios")}</td>
					
						<td><g:formatDate format="dd-MM-yy" date="${escrituraInstance.fecha}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${escrituraInstanceTotal}" />
			</div>
                        <div class="well">
                            <table>
                              <tbody>
                                <tr>
                                  <td>
                                    <g:jasperReport name="reporteEscrituras" jasper="reporteEscrituras" format="PDF" description="Guardar reportes (PDF)" action="guardarEscrituras" controller="escritura">
                                        <g:each in="${escrituraInstanceList}" status="i" var="escrituraInstance">
                                        <g:hiddenField name='escrituraId' value='${escrituraInstance.id}'/>
                                        <g:hiddenField name='numeroDeEscritura' value='${escrituraInstance.numeroDeEscritura}'/>
                                        </g:each>
                                    </g:jasperReport>    
                                  </td>
                                  <td>
                                    <g:jasperReport name="reporteEscrituras" jasper="reporteEscrituras" format="XLS" description="Guardar reportes (EXCEL)" action="guardarEscrituras" controller="escritura"/>
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                        </div>
		</div>
	</body>
</html>
