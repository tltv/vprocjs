package org.vaadin.tltv.vprocjs.gwt.client.ui;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;

/**
 * Abstract Connector for the Java implementation of the sketches. This class
 * acts as a client-side link to the server.
 */
public abstract class ProcessingCodeConnector extends
        AbstractExtensionConnector {

    private VProcessing widget;

    @Override
    protected void extend(ServerConnector target) {
        // Get the extended widget
        widget = (VProcessing) ((ComponentConnector) target).getWidget();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        ProcessingCodeState state = getState();
        VProcessing widget = getWidget();
        widget.setUId(getConnectorId());
        widget.setProcessingJavaCode(getProcessingJavaCode(state
                .getProcessingJavaCodeClass()));
        widget.stateChanged();
    }

    /**
     * Get active implementation of the sketch.
     * 
     * @param codeClass
     *            Active processing java code class identifier
     * @return
     */
    public abstract ProcessingCode getProcessingJavaCode(String codeClass);

    public VProcessing getWidget() {
        return widget;
    }

    @Override
    public ProcessingCodeState getState() {
        return (ProcessingCodeState) super.getState();
    }
}
