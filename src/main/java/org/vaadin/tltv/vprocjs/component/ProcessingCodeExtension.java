package org.vaadin.tltv.vprocjs.component;

import com.vaadin.server.AbstractExtension;

import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingCodeState;

public abstract class ProcessingCodeExtension extends AbstractExtension {

    private Processing owner;
    private String processingJavaCodeClass;

    public void extend(Processing owner) {
        this.owner = owner;
        super.extend(owner);
    }

    public String getProcessingJavaCodeClass() {
        return processingJavaCodeClass;
    }

    public void setProcessingJavaCodeClass(String processingJavaCodeClass) {
        this.processingJavaCodeClass = processingJavaCodeClass;
        getState().setProcessingJavaCodeClass(this.processingJavaCodeClass);
    }

    @Override
    public ProcessingCodeState getState() {
        return (ProcessingCodeState) super.getState();
    }
}
