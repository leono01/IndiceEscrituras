package lion



import org.junit.*
import grails.test.mixin.*

@TestFor(BeneficiarioController)
@Mock(Beneficiario)
class BeneficiarioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/beneficiario/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.beneficiarioInstanceList.size() == 0
        assert model.beneficiarioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.beneficiarioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.beneficiarioInstance != null
        assert view == '/beneficiario/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/beneficiario/show/1'
        assert controller.flash.message != null
        assert Beneficiario.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/beneficiario/list'

        populateValidParams(params)
        def beneficiario = new Beneficiario(params)

        assert beneficiario.save() != null

        params.id = beneficiario.id

        def model = controller.show()

        assert model.beneficiarioInstance == beneficiario
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/beneficiario/list'

        populateValidParams(params)
        def beneficiario = new Beneficiario(params)

        assert beneficiario.save() != null

        params.id = beneficiario.id

        def model = controller.edit()

        assert model.beneficiarioInstance == beneficiario
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/beneficiario/list'

        response.reset()

        populateValidParams(params)
        def beneficiario = new Beneficiario(params)

        assert beneficiario.save() != null

        // test invalid parameters in update
        params.id = beneficiario.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/beneficiario/edit"
        assert model.beneficiarioInstance != null

        beneficiario.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/beneficiario/show/$beneficiario.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        beneficiario.clearErrors()

        populateValidParams(params)
        params.id = beneficiario.id
        params.version = -1
        controller.update()

        assert view == "/beneficiario/edit"
        assert model.beneficiarioInstance != null
        assert model.beneficiarioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/beneficiario/list'

        response.reset()

        populateValidParams(params)
        def beneficiario = new Beneficiario(params)

        assert beneficiario.save() != null
        assert Beneficiario.count() == 1

        params.id = beneficiario.id

        controller.delete()

        assert Beneficiario.count() == 0
        assert Beneficiario.get(beneficiario.id) == null
        assert response.redirectedUrl == '/beneficiario/list'
    }
}
