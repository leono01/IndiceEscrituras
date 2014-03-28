package lion

class Escritura implements Serializable{
    String  numeroDeEscritura
    String  nombreOperacion
    String  volumen
    String  folios
    Date    fecha
    
    static hasMany = [otorgantes:Otorgante, beneficiarios:Beneficiario]
    
    static constraints = {
        numeroDeEscritura nullable:false, unique:true
        nombreOperacion   nullable:false
        volumen           nullable:false
        folios            nullable:false
        fecha             nullable:false
        otorgantes        nullable:true
        beneficiarios     nullable:true
    }
    
    static mapping = {
        otorgantes      cascade: 'all-delete-orphan'
        beneficiarios   cascade: 'all-delete-orphan'
    }
    
    String toString(){
        "${numeroDeEscritura}"
    }
}
