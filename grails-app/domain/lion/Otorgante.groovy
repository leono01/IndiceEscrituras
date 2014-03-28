package lion

class Otorgante implements Serializable{
    String nombre        
    
    static constraints = {
        nombre unique:true
    }
    
    String toSotring(){
        "${nombre}"
    }
}
