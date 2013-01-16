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
