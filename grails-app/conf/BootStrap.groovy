import com.avathartech.pgvj.domains.Contacto

class BootStrap {

    def init = { servletContext ->

        println("Inicializando Proyecto de prueba de integración de Grails - Jasper - Vaadin");
        (1..50).each {
            new Contacto(nombre: "Nombre $it", telefono: '809-XXX-XX'+it).save();
        }

    }
    def destroy = {
    }
}
