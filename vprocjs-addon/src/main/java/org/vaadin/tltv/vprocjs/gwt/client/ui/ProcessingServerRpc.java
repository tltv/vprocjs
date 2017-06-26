package org.vaadin.tltv.vprocjs.gwt.client.ui;

import com.vaadin.shared.communication.ServerRpc;

public interface ProcessingServerRpc extends ServerRpc {

    void fireClick();

    void fireMousePress(int mouseX, int mouseY);

    void fireMouseRelease(int mouseX, int mouseY);

    void fireKeyPress(int key);

    void fireKeyRelease();

    void fireMouseEnter();

    void fireMouseLeave();

    void fireMouseWheel(boolean up, int deltaY);
}
