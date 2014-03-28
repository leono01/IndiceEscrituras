package lion



import org.junit.*
import grails.test.mixin.*

@TestFor(EscrituraController)
@Mock(Escritura)
class EscrituraControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/escritura/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.escrituraInstanceList.size() == 0
        assert model.escrituraInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.escrituraInstance != null
    }

    void testSave() {
        controller.save()

        assert model.escrituraInstance != null
        assert view == '/escritura/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/escritura/show/1'
        assert controller.flash.message != null
        assert Escritura.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/escritura/list'

        populateValidParams(params)
        def escritura = new Escritura(params)

        assert escritura.save() != null

        params.id = escritura.id

        def model = controller.show()

        assert model.escrituraInstance == escritura
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/escritura/list'

        populateValidParams(params)
        def escritura = new Escritura(params)

        assert escritura.save() != null

        params.id = escritura.id

        def model = controller.edit()

        assert model.escrituraInstance == escritura
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/escritura/list'

        response.reset()

        populateValidParams(params)
        def escritura = new Escritura(params)

        assert escritura.save() != null

        // test invalid parameters in update
        params.id = escritura.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/escritura/edit"
        assert model.escrituraInstance != null

        escritura.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/escritura/show/$escritura.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        escritura.clearErrors()

        populateValidParams(params)
        params.id = escritura.id
        params.version = -1
        controller.update()

        assert view == "/escritura/edit"
        assert model.escrituraInstance != null
        assert model.escrituraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/escritura/list'

        response.reset()

        populateValidParams(params)
        def escritura = new Escritura(params)

        assert escritura.save() != null
        assert Escritura.count() == 1

        params.id = escritura.id

        controller.delete()

        assert Escritura.count() == 0
        assert Escritura.get(escritura.id) == null
        assert response.redirectedUrl == '/escritura/list'
    }
}
