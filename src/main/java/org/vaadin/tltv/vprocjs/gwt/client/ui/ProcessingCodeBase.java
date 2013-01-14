package org.vaadin.tltv.vprocjs.gwt.client.ui;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for the Java implementation of the Processing sketch. Client side
 * classes that extends this, can be used as a <code>ProcessingCode</code> for
 * the VProcessing widget.</br></br>
 * 
 * Processing functions are available via the <code>pro</code>
 * variable.</br></br>
 * 
 * Supports shared variables via the map <code>sharedVariables</code> that are
 * transferred between the sketches assigned for the owner VProcessing widget.
 * 
 * @author Tltv
 * 
 */
public class ProcessingCodeBase implements ProcessingCode {

    protected ProcessingJavascriptObject pro;
    protected VProcessing vprocessing;

    protected Map<Object, Object> sharedVariables;

    @Override
    public void setProcessing(VProcessing vprocessing,
            Map<Object, Object> sharedVariables) {
        this.vprocessing = vprocessing;
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

    @Override
    public void setProcessingJavascriptObject(
            ProcessingJavascriptObject proJsObj) {
        pro = proJsObj;
    }
}
