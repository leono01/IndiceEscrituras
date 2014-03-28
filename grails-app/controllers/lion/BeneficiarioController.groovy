package lion

import org.springframework.dao.DataIntegrityViolationException

class BeneficiarioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [beneficiarioInstanceList: Beneficiario.list(params), beneficiarioInstanceTotal: Beneficiario.count()]
    }

    def create() {
        [beneficiarioInstance: new Beneficiario(params)]
    }

    def save() {
        def beneficiarioInstance = new Beneficiario(params)
        if (!beneficiarioInstance.save(flush: true)) {
            render(view: "create", model: [beneficiarioInstance: beneficiarioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), beneficiarioInstance.id])
        redirect(action: "show", id: beneficiarioInstance.id)
    }

    def show(Long id) {
        def beneficiarioInstance = Beneficiario.get(id)
        if (!beneficiarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), id])
            redirect(action: "list")
            return
        }

        [beneficiarioInstance: beneficiarioInstance]
    }

    def edit(Long id) {
        def beneficiarioInstance = Beneficiario.get(id)
        if (!beneficiarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), id])
            redirect(action: "list")
            return
        }

        [beneficiarioInstance: beneficiarioInstance]
    }

    def update(Long id, Long version) {
        def beneficiarioInstance = Beneficiario.get(id)
        if (!beneficiarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (beneficiarioInstance.version > version) {
                beneficiarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'beneficiario.label', default: 'Beneficiario')] as Object[],
                          "Another user has updated this Beneficiario while you were editing")
                render(view: "edit", model: [beneficiarioInstance: beneficiarioInstance])
                return
            }
        }

        beneficiarioInstance.properties = params

        if (!beneficiarioInstance.save(flush: true)) {
            render(view: "edit", model: [beneficiarioInstance: beneficiarioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), beneficiarioInstance.id])
        redirect(action: "show", id: beneficiarioInstance.id)
    }

    def delete(Long id) {
        def beneficiarioInstance = Beneficiario.get(id)
        if (!beneficiarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), id])
            redirect(action: "list")
            return
        }

        try {
            beneficiarioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'beneficiario.label', default: 'Beneficiario'), id])
            redirect(action: "show", id: id)
        }
    }
}
