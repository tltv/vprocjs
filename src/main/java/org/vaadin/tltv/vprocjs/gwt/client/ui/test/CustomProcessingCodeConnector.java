package org.vaadin.tltv.vprocjs.gwt.client.ui.test;

import com.vaadin.shared.ui.Connect;

import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingCode;
import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingCodeConnector;
import org.vaadin.tltv.vprocjs.test.server.CustomProcessingCodeExtension;

@Connect(CustomProcessingCodeExtension.class)
public class CustomProcessingCodeConnector extends ProcessingCodeConnector {

    @Override
    public ProcessingCode getProcessingJavaCode(String codeClass) {
        if (ProcessingCodeImplTest.class.getName().equals(codeClass)) {
            return new ProcessingCodeImplTest();

        } else if (ProcessingJavaCode1.class.getName().equals(codeClass)) {
            return new ProcessingJavaCode1();

        } else if (ProcessingJavaCode2.class.getName().equals(codeClass)) {
            return new ProcessingJavaCode2();
        }
        return null;
    }
}
