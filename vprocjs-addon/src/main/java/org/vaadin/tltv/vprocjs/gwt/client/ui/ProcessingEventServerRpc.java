package org.vaadin.tltv.vprocjs.gwt.client.ui;

import com.vaadin.shared.communication.ServerRpc;

public interface ProcessingEventServerRpc extends ServerRpc {

    void mouseMoved();

    void mouseDragged();

    void mousePressed();

    void mouseClicked();

    void mouseReleased();

    void keyPressed();

    void keyReleased();

    void keyTyped();

}
