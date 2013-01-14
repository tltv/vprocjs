package org.vaadin.tltv.vprocjs.test.server;

import org.vaadin.tltv.vprocjs.component.Processing;
import org.vaadin.tltv.vprocjs.gwt.client.ui.test.ProcessingCodeImplTest;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Sample to show how the Processing component can be used to create Processing
 * sketch by its own Java API.
 * 
 * @author Tltv
 * 
 */
public class ProcessingSimpleJavaCodeTest extends VerticalLayout {

    public ProcessingSimpleJavaCodeTest() {
        Processing pro = new Processing();
        CustomProcessingCodeExtension codeExtension = new CustomProcessingCodeExtension();
        codeExtension.extend(pro);

        final Label clientCodeLabel = new Label("Sketch: "
                + ProcessingCodeImplTest.class.getName());
        final Label serverCodeLabel = new Label("Server-side UI code: "
                + this.getClass().getName());

        codeExtension.setProcessingJavaCodeClass(ProcessingCodeImplTest.class
                .getName());

        addComponent(new Label(
                "Canvas below is rendered by processing.js library. Sketch however is written in Java. Component allows you to write sketch in a single client-side Java file."));
        addComponent(pro);
        addComponent(clientCodeLabel);
        addComponent(serverCodeLabel);
    }
}
