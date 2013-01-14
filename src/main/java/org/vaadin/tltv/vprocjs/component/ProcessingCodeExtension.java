package org.vaadin.tltv.vprocjs.component;

import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingCodeState;

import com.vaadin.server.AbstractExtension;

/**
 * Abstract extension for the Java implementation of the sketches. This
 * extension allows us to write our own Java sketches. This acts as a
 * server-side link to the client-side code connector.
 * 
 */
public abstract class ProcessingCodeExtension extends AbstractExtension {

    private Processing owner;
    private String processingJavaCodeClass;

    public void extend(Processing owner) {
        this.owner = owner;
        super.extend(owner);
    }

    /**
     * Get identifier for the client side Java implementation class of the
     * active sketch.
     * 
     */
    public String getProcessingJavaCodeClass() {
        return processingJavaCodeClass;
    }

    /**
     * Set identifier for the client side Java implementation of the active
     * sketch. Full class name is preferred.
     * 
     * @param processingJavaCodeClass
     */
    public void setProcessingJavaCodeClass(String processingJavaCodeClass) {
        this.processingJavaCodeClass = processingJavaCodeClass;
        getState().setProcessingJavaCodeClass(this.processingJavaCodeClass);
    }

    @Override
    public ProcessingCodeState getState() {
        return (ProcessingCodeState) super.getState();
    }
}
