package com.avathartech.pgvj.services

import grails.transaction.Transactional
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

@Transactional
class ReporteService {

    def jasperService;

    /**
     * Ejemplo de prueba para visualizar un reporte de manera directa.
     * @param param1
     * @param param2
     * @param format
     * @return
     */
    public ByteArrayOutputStream generarReporteSencillo(String param1, String param2, JasperExportFormat format) {
        String rutaReporte = "SimpleTest/TestReport"

        def parametros = ["param1"   : param1,
                          "param2": param2,
                         ];

        //
        def reporte = new JasperReportDef(name: rutaReporte, fileFormat: format, parameters: parametros )

        //generando el reporte.
        ByteArrayOutputStream reporteGenerado = jasperService.generateReport(reporte);

        return reporteGenerado
    }

}
