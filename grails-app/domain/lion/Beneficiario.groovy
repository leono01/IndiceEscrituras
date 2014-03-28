package lion

class Beneficiario implements Serializable {
    String nombre    
    
    static constraints = {
        nombre unique:true        
    }
    
    String toSotring(){
        "${nombre}"
    }
}
