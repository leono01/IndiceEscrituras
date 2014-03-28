package lion

import org.springframework.dao.DataIntegrityViolationException

class OtorganteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [otorganteInstanceList: Otorgante.list(params), otorganteInstanceTotal: Otorgante.count()]
    }

    def create() {
        [otorganteInstance: new Otorgante(params)]
    }

    def save() {
        def otorganteInstance = new Otorgante(params)
        if (!otorganteInstance.save(flush: true)) {
            render(view: "create", model: [otorganteInstance: otorganteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), otorganteInstance.id])
        redirect(action: "show", id: otorganteInstance.id)
    }

    def show(Long id) {
        def otorganteInstance = Otorgante.get(id)
        if (!otorganteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), id])
            redirect(action: "list")
            return
        }

        [otorganteInstance: otorganteInstance]
    }

    def edit(Long id) {
        def otorganteInstance = Otorgante.get(id)
        if (!otorganteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), id])
            redirect(action: "list")
            return
        }

        [otorganteInstance: otorganteInstance]
    }

    def update(Long id, Long version) {
        def otorganteInstance = Otorgante.get(id)
        if (!otorganteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (otorganteInstance.version > version) {
                otorganteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'otorgante.label', default: 'Otorgante')] as Object[],
                          "Another user has updated this Otorgante while you were editing")
                render(view: "edit", model: [otorganteInstance: otorganteInstance])
                return
            }
        }

        otorganteInstance.properties = params

        if (!otorganteInstance.save(flush: true)) {
            render(view: "edit", model: [otorganteInstance: otorganteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), otorganteInstance.id])
        redirect(action: "show", id: otorganteInstance.id)
    }

    def delete(Long id) {
        def otorganteInstance = Otorgante.get(id)
        if (!otorganteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), id])
            redirect(action: "list")
            return
        }

        try {
            otorganteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'otorgante.label', default: 'Otorgante'), id])
            redirect(action: "show", id: id)
        }
    }
}
