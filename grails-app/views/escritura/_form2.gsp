<%@ page import="lion.Escritura" %>

<div class="widget-box">
    <div class="widget-title">
      <span class="icon">
        <i class="icon-th"></i>
      </span>         

      </br>
    </div>
    <div class="widget-content nopadding">      

        <div class="fieldcontain ${hasErrors(bean: escrituraInstance, field: 'numeroDeEscritura', 'error')} ">
          <label for="numeroDeEscritura">
                  <g:message code="escritura.numeroDeEscritura.label" default="Número De Cotejo" />

          </label>
          <g:textField  style="with:400%;" name="numeroDeEscritura" value="${escrituraInstance?.numeroDeEscritura}"/>
        </div>
        
        <div class="fieldcontain ${hasErrors(bean: escrituraInstance, field: 'nombreOperacion', 'error')} ">
                <label for="nombreOperacion">
                        <g:message code="escritura.nombreOperacion.label" default="Nombre Operación" />

                </label>
                <g:textField name="nombreOperacion" value="${escrituraInstance?.nombreOperacion}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: escrituraInstance, field: 'volumen', 'error')} ">
                <label for="volumen">
                        <g:message code="escritura.volumen.label" default="Número de Libro" />

                </label>
                <g:textField name="volumen" value="${escrituraInstance?.volumen}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: escrituraInstance, field: 'folios', 'error')} ">
                <label for="folios">
                        <g:message code="escritura.folios.label" default="Folio" />

                </label>
                <g:textField name="folios" value="${escrituraInstance?.folios}"/>
        </div>

        <div class="fieldcontain ${hasErrors(bean: escrituraInstance, field: 'fecha', 'error')} required">
                <label for="fecha">
                        <g:message code="escritura.fecha.label" default="Fecha" />
                        <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="fecha" precision="day"  format="dd/MM/yy" value="${escrituraInstance?.fecha}"  />
        </div>
    </div>
</div>
