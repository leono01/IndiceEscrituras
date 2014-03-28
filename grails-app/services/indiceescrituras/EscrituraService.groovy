package indiceescrituras

import lion.*

class EscrituraService {

    static scope = "session"
    
    def borrarOtorgante(long idEscritura, long idOtorgante) {
        def escritura = Escritura.get(idEscritura)
        def otorgante = Otorgante.get(idOtorgante)
        def otorganteAEliminar
        escritura.otorgantes.each{
                        if(it.id == otorgante.id){
                            otorganteAEliminar = it
                        }
                      }
        escritura.removeFromOtorgantes(otorganteAEliminar)
        otorganteAEliminar.delete()
    }
    
    def borrarBeneficiario(long idEscritura, long idBeneficiario) {        
        def escritura = Escritura.get(idEscritura)
        def beneficiario = Beneficiario.get(idBeneficiario)
        def beneficiarioAEliminar
        escritura.beneficiarios.each{
                        if(it.id == beneficiario.id){
                            beneficiarioAEliminar = it
                        }
                      }
        escritura.removeFromBeneficiarios(beneficiarioAEliminar)
        beneficiarioAEliminar.delete()
    }
}
