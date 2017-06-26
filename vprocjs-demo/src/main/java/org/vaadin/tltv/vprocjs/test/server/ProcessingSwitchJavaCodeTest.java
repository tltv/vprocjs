package org.vaadin.tltv.vprocjs.test.server;

import org.vaadin.tltv.vprocjs.component.Processing;
import org.vaadin.tltv.vprocjs.gwt.client.ui.test.ProcessingJavaCode1;
import org.vaadin.tltv.vprocjs.gwt.client.ui.test.ProcessingJavaCode2;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Sample to show how the Processing component can be used by its own Java API
 * to change client side Java implementation of the sketch on the fly.
 * 
 * @author Tltv
 * 
 */
public class ProcessingSwitchJavaCodeTest extends VerticalLayout {

    public ProcessingSwitchJavaCodeTest() {
        final Processing pro = new Processing();
        final CustomProcessingCodeExtension codeExtension = new CustomProcessingCodeExtension();
        codeExtension.extend(pro);

        final Label clientCodeLabel = new Label();
        final Label serverCodeLabel = new Label("Server-side UI code: "
                + this.getClass().getName());

        codeExtension.setProcessingJavaCodeClass(ProcessingJavaCode1.class
                .getName());
        clientCodeLabel.setValue("Sketch: "
                + ProcessingJavaCode1.class.getName());

        addComponent(new Label(
                "Canvas below is rendered by processing.js library. Sketch however is written in Java. Component allows you to switch the Java sketch on the fly and share variables between sketches."));
        addComponent(pro);
        addComponent(clientCodeLabel);
        addComponent(serverCodeLabel);

        Button switchButton = new Button("Switch Java sketch on fly");
        switchButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (codeExtension.getProcessingJavaCodeClass().equals(
                        ProcessingJavaCode1.class.getName())) {
                    codeExtension
                            .setProcessingJavaCodeClass(ProcessingJavaCode2.class
                                    .getName());
                    clientCodeLabel.setValue("Sketch: "
                            + ProcessingJavaCode2.class.getName());
                } else {
                    codeExtension
                            .setProcessingJavaCodeClass(ProcessingJavaCode1.class
                                    .getName());
                    clientCodeLabel.setValue("Sketch: "
                            + ProcessingJavaCode1.class.getName());
                }
            }
        });
        addComponent(switchButton);
    }
}
