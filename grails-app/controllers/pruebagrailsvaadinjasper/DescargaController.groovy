package pruebagrailsvaadinjasper

import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat

class DescargaController {

    def reporteService;

    def main(){
        redirect(uri: "/vd/")
    }

    /**
     *
     * @return
     */
    def index() {
        ByteArrayOutputStream byteReporte = reporteService.generarReporteSencillo("hola", "mundo", JasperExportFormat.PDF_FORMAT);
        response.contentType="application/pdf"
        response.addHeader("Content-Disposition","attachment; filename=Reporte_PDF.pdf")
        response.outputStream << byteReporte.toByteArray();
        response.flushBuffer();

    }
}
