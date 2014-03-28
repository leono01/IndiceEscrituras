package lion



import org.junit.*
import grails.test.mixin.*

@TestFor(OtorganteController)
@Mock(Otorgante)
class OtorganteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/otorgante/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.otorganteInstanceList.size() == 0
        assert model.otorganteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.otorganteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.otorganteInstance != null
        assert view == '/otorgante/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/otorgante/show/1'
        assert controller.flash.message != null
        assert Otorgante.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/otorgante/list'

        populateValidParams(params)
        def otorgante = new Otorgante(params)

        assert otorgante.save() != null

        params.id = otorgante.id

        def model = controller.show()

        assert model.otorganteInstance == otorgante
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/otorgante/list'

        populateValidParams(params)
        def otorgante = new Otorgante(params)

        assert otorgante.save() != null

        params.id = otorgante.id

        def model = controller.edit()

        assert model.otorganteInstance == otorgante
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/otorgante/list'

        response.reset()

        populateValidParams(params)
        def otorgante = new Otorgante(params)

        assert otorgante.save() != null

        // test invalid parameters in update
        params.id = otorgante.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/otorgante/edit"
        assert model.otorganteInstance != null

        otorgante.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/otorgante/show/$otorgante.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        otorgante.clearErrors()

        populateValidParams(params)
        params.id = otorgante.id
        params.version = -1
        controller.update()

        assert view == "/otorgante/edit"
        assert model.otorganteInstance != null
        assert model.otorganteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/otorgante/list'

        response.reset()

        populateValidParams(params)
        def otorgante = new Otorgante(params)

        assert otorgante.save() != null
        assert Otorgante.count() == 1

        params.id = otorgante.id

        controller.delete()

        assert Otorgante.count() == 0
        assert Otorgante.get(otorgante.id) == null
        assert response.redirectedUrl == '/otorgante/list'
    }
}
