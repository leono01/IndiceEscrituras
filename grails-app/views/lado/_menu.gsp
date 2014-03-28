<a href="#" class="visible-phone"><i class="icon icon-home"></i>Menú</a>
<ul>
  <li><g:link controller="escritura"><i class="icon icon-home"></i><span>Capturar Escritura</span></g:link></li> 
  <li class="submenu">
    <a href="#"><i class="icon icon-envelope"></i><span>Imprimir Reportes</span> <span class="label">1</span></a>
    <ul>
      <li><g:link controller="escritura" action="imprimirEscritura">Reporte de Indice de Escrituras</g:link></li>
      <li><g:link controller="escritura" action="imprimirCotejo">Reporte de Indice de Cotejos</g:link></li>
    </ul>
  </li>
  <%--<li id="menuExpedientes" class="submenu open">
    <a href="#"><i class="icon icon-envelope"></i><span>Expedientes</span> <span class="label">4</span></a>
    <ul>
      <li><g:link controller="operacion" action="seleccionDeOperacion">Realizar Operación</g:link></li>
      <li><g:link controller="expediente" action="list">Mi Bandeja de Trabajo</g:link></li>
      <li><g:link controller="expediente" action="buscar" id="1">Búsqueda</g:link></li>
      <li><g:link controller="historialDelExpediente" action="interno">Historial</g:link></li>
    </ul>
  </li>--%>
</ul>