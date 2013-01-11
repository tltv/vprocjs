package org.vaadin.tltv.vprocjs.test.server;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import org.vaadin.tltv.vprocjs.component.Processing;
import org.vaadin.tltv.vprocjs.gwt.client.ui.test.ProcessingJavaCode1;
import org.vaadin.tltv.vprocjs.gwt.client.ui.test.ProcessingJavaCode2;

public class ProcessingSwitchJavaCodeTest extends VerticalLayout {

    public ProcessingSwitchJavaCodeTest() {
        final Processing pro = new Processing();
        final CustomProcessingCodeExtension codeExtension = new CustomProcessingCodeExtension();
        codeExtension.extend(pro);

        final Label codeLabel = new Label();

        codeExtension.setProcessingJavaCodeClass(ProcessingJavaCode1.class
                .getName());
        codeLabel.setValue(ProcessingJavaCode1.class.getName());

        addComponent(pro);
        addComponent(codeLabel);

        Button switchButton = new Button("Switch Java code on fly");
        switchButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (codeExtension.getProcessingJavaCodeClass().equals(
                        ProcessingJavaCode1.class.getName())) {
                    codeExtension
                            .setProcessingJavaCodeClass(ProcessingJavaCode2.class
                                    .getName());
                    codeLabel.setValue(ProcessingJavaCode2.class.getName());
                } else {
                    codeExtension
                            .setProcessingJavaCodeClass(ProcessingJavaCode1.class
                                    .getName());
                    codeLabel.setValue(ProcessingJavaCode1.class.getName());
                }
            }
        });
        addComponent(switchButton);
    }
}
