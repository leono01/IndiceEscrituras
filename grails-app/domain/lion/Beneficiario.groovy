package lion

class Beneficiario implements Serializable {
    String nombreBeneficiario
    
    static belongsTo = [escritura:Escritura]
    
    static constraints = {
        nombreBeneficiario unique:true        
    }
    
    String toSotring(){
        "${nombreBeneficiario}"
    }
}
