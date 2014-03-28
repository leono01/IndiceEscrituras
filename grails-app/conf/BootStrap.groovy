import grails.util.Environment
class BootStrap {

    def init = { servletContext ->
        switch (Environment.current) {
        case Environment.DEVELOPMENT :
            
            break
        case Environment.PRODUCTION :
            break
        }
    }
    def destroy = {
    }
}
