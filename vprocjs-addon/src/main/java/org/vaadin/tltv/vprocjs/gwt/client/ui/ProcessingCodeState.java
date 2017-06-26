package org.vaadin.tltv.vprocjs.gwt.client.ui;

import com.vaadin.shared.AbstractComponentState;

public class ProcessingCodeState extends AbstractComponentState {

    private String processingJavaCodeClass;

    public void setProcessingJavaCodeClass(String processingJavaCodeClass) {
        this.processingJavaCodeClass = processingJavaCodeClass;
    }

    public String getProcessingJavaCodeClass() {
        return processingJavaCodeClass;
    }
}
