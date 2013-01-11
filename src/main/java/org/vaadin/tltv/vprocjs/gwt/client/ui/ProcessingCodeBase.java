package org.vaadin.tltv.vprocjs.gwt.client.ui;

import java.util.HashMap;
import java.util.Map;

public class ProcessingCodeBase implements ProcessingCode {

    protected VProcessing pro;

    protected Map<Object, Object> sharedVariables;

    @Override
    public void setProcessing(VProcessing vprocessing,
            Map<Object, Object> sharedVariables) {
        this.pro = vprocessing;
        this.sharedVariables = sharedVariables;
        if (this.sharedVariables == null) {
            this.sharedVariables = new HashMap<Object, Object>();
        }
    }

    @Override
    public void setup() {
    }

    @Override
    public void draw() {
    }

    @Override
    public void mouseMoved() {
    }

    @Override
    public void mouseDragged() {
    }

    @Override
    public void mousePressed() {
    }

    @Override
    public void mouseClicked() {
    }

    @Override
    public void mouseReleased() {
    }

    @Override
    public void keyPressed() {
    }

    @Override
    public void keyReleased() {
    }

    @Override
    public void keyTyped() {
    }

    protected Object getVariable(Object key) {
        return sharedVariables.get(key);
    }

    protected Object setVariable(Object key, Object value) {
        return sharedVariables.put(key, value);
    }

    @Override
    public Map<Object, Object> getSharedVariables() {
        return sharedVariables;
    }
}
