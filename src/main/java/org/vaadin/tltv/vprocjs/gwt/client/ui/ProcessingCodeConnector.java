/*
 * Copyright 2013 Tomi Virtanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
