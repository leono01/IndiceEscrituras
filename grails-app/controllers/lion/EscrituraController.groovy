package lion

import org.springframework.dao.DataIntegrityViolationException

class EscrituraController {

    def escrituraService
    def jasperService
    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    
    def flujo(){        
        redirect action: "capturarEscritura"
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [escrituraInstanceList: Escritura.list(params), escrituraInstanceTotal: Escritura.count()]
    }

    def create() {
        [escrituraInstance: new Escritura(params)]
    }

    def save() {
        def escrituraInstance = new Escritura(params)
        if (!escrituraInstance.save(flush: true)) {
            render(view: "create", model: [escrituraInstance: escrituraInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'escritura.label', default: 'Escritura'), escrituraInstance.id])
        redirect(action: "show", id: escrituraInstance.id)
    }

    def show(Long id) {
        def escrituraInstance = Escritura.get(id)
        if (!escrituraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'escritura.label', default: 'Escritura'), id])
            redirect(action: "list")
            return
        }

        [escrituraInstance: escrituraInstance]
    }

    def edit(Long id) {
        def escrituraInstance = Escritura.get(id)
        if (!escrituraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'escritura.label', default: 'Escritura'), id])
            redirect(action: "list")
            return
        }

        [escrituraInstance: escrituraInstance]
    }

    def update(Long id, Long version) {
        def escrituraInstance = Escritura.get(id)
        if (!escrituraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'escritura.label', default: 'Escritura'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (escrituraInstance.version > version) {
                escrituraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'escritura.label', default: 'Escritura')] as Object[],
                          "Another user has updated this Escritura while you were editing")
                render(view: "edit", model: [escrituraInstance: escrituraInstance])
                return
            }
        }

        escrituraInstance.properties = params

        if (!escrituraInstance.save(flush: true)) {
            render(view: "edit", model: [escrituraInstance: escrituraInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'escritura.label', default: 'Escritura'), escrituraInstance.id])
        redirect(action: "show", id: escrituraInstance.id)
    }

    def delete(Long id) {
        def escrituraInstance = Escritura.get(id)
        if (!escrituraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'escritura.label', default: 'Escritura'), id])
            redirect(action: "list")
            return
        }

        try {
            escrituraInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'escritura.label', default: 'Escritura'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'escritura.label', default: 'Escritura'), id])
            redirect(action: "show", id: id)
        }
    }
    
    
    def capturarEscrituraFlow = {
        init {
            action{                
                flow.escrituraInstance = new Escritura(numeroDeEscritura:"", nombreOperacion:"", volumen:"", folios:"", fecha:"")                
            }
            on ("success").to "escritura"
            on(Exception).to "handleError"
        }
        escritura{                        
            on("create"){                
                def escritura = new Escritura(params)
                flow.escrituraInstance = escritura               
                escritura.save(flush:true)
            }.to "participantes"            
        }
        
        participantes{
            on("agregarOtorgante"){                
                def escritura = flow.escrituraInstance                
                def otorgante = Otorgante.findByNombreOtorgante(params."nombreOtorgante".toString())
                if (otorgante){
                    escritura.addToOtorgantes(otorgante)
                }else{
                otorgante = new Otorgante(nombreOtorgante:params."nombreOtorgante".toString(),escrituraId:escritura.id)                
                otorgante.save(flush:true)
                escritura.addToOtorgantes(otorgante)
                }
                
            }.to "participantes"
            on("eliminarOtorgante"){
                escrituraService.borrarOtorgante(params.escrituraId as long,params.otorganteId as long)
                flash.message = "Se ha borrado el otorgante."
            }.to "participantes"
            on("agregarBeneficiario"){                
                def escritura = flow.escrituraInstance
                def beneficiario = Beneficiario.findByNombreBeneficiario(params."nombreBeneficiario".toString())
                if(beneficiario){                    
                    escritura.addToBeneficiarios(beneficiario)
                }else{
                    beneficiario = new Beneficiario(nombreBeneficiario:params."nombreBeneficiario".toString(),escritura:escritura.id)
                    beneficiario.save(flush:true)
                    escritura.addToBeneficiarios(beneficiario)
                }                                
            }.to "participantes"
            on("eliminarBeneficiario"){
                escrituraService.borrarBeneficiario(params.escrituraId as long, params.beneficiarioId as long)
            }.to "participantes"
            on("create"){
                def escritura = flow.escrituraInstance
                escritura.save(flush:true)
                flash.message="Se ha capturado la escritura " + escritura.numeroDeEscritura
            }.to "escritura"
            on("atras").to "escritura"
        }
        
        handleError{
            redirect(action:"index")
        }
    }
    def guardarEscrituras={
        def escritura = Escritura.findAll("from Escritura")        
        
        chain(controller:'jasper', action:'index', model: [data:null], params:params)
    }
}
