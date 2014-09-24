package com.avathartech.pgvj.groovy.ui.reportes

/**
 * Created by Sixto on 04/07/2014.
 */
import com.vaadin.server.StreamResource
import com.vaadin.ui.BrowserFrame
import com.vaadin.ui.Component
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.Window

/**
 * Created by Sixto on 2014-06-02.
 */
public class VisorDeReportes extends Window{

    private StreamResource myResource
    private BrowserFrame browser

    public VisorDeReportes(StreamResource resource){
        this.myResource = resource
        this.caption = "Visor de Reportes"
        this.modal = false
        this.resizable = false
        this.closable = true
        this.width = "90%"
        this.height = "90%"
        this.content = construirContenido()
    }

    private Component construirContenido(){
        browser = new BrowserFrame("Visor", myResource)
        browser.setSizeFull()

        VerticalLayout layoutVentana = new VerticalLayout()
        layoutVentana.setSizeFull()
        layoutVentana.setSpacing(true)
        layoutVentana.setMargin(true)
        layoutVentana.addComponent(browser)

        return layoutVentana
    }
}