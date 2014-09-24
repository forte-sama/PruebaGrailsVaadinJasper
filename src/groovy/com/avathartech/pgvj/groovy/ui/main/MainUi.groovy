package com.avathartech.pgvj.groovy.ui.main

import com.avathartech.pgvj.services.ReporteService
import com.avathartech.pgvj.groovy.ui.reportes.VisorDeReportes
import com.vaadin.grails.Grails
import com.vaadin.server.StreamResource
import com.vaadin.server.VaadinService
import com.vaadin.ui.Button
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat

/**
 * Created by vacax on 09/23/14.
 */
class MainUi extends VerticalLayout {

    FormLayout formLayout;
    TextField txtParam1;
    TextField txtParam2;
    Button btnProcesar;



    /**
     *
     */
    public MainUi(){

        Label lbPrincipal=new Label("Prueba Jasper - Vaadin - Grails");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(lbPrincipal);

        //
        txtParam1=new TextField("Parametro 1:");
        txtParam1.setRequired(true);
        txtParam1.value="Param 1";

        txtParam2=new TextField("Parametro 2:");
        txtParam2.setRequired(true);
        txtParam2.value="Param 2";

        btnProcesar = new Button("Procesar Reporte", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent clickEvent) {

                visualizarReporte();
            }
        })

        //
        formLayout=new FormLayout();
        formLayout.addComponent(txtParam1)
        formLayout.addComponent(txtParam2)
        formLayout.addComponent(btnProcesar)

        //
        this.addComponent(horizontalLayout);
        this.addComponent(formLayout);

    }

    /**
     *
     */
    private void visualizarReporte(){
          if(txtParam1.isValid() && txtParam2.isValid()){
              //obteniendo el reporte.
              ByteArrayOutputStream outputStream = Grails.get(ReporteService).generarReporteSencillo(txtParam1.value, txtParam2.value, JasperExportFormat.PDF_FORMAT)
              //
              StreamResource recursoPDF = getStreamResource(outputStream, "PruebaReporte");
              recursoPDF.setMIMEType("application/pdf");

              VisorDeReportes visor = new VisorDeReportes(recursoPDF);
              getUI().addWindow(visor);

          }else{
              Notification.show("Debes completar los campos", Notification.Type.ERROR_MESSAGE);
          }
    }

    /**
     *
     * @param baos
     * @param nombre
     * @return
     */
    public static StreamResource getStreamResource(ByteArrayOutputStream baos, String nombre) {
        StreamResource.StreamSource source = new StreamResource.StreamSource() {
            public InputStream getStream() {
                ByteArrayOutputStream stream = baos
                InputStream input = new ByteArrayInputStream(stream.toByteArray());
                return input;

            }
        };
        StreamResource resource = new StreamResource(source, nombre + System.currentTimeMillis());
        return resource;
    }


}
