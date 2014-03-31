package lion

class Otorgante implements Serializable{
    String nombreOtorgante
    
    static belongsTo=[escritura:Escritura]
    
    static constraints = {
        nombreOtorgante unique:true        
    }
    
    String toSotring(){
        "${nombreOtorgante}"
    }
}
