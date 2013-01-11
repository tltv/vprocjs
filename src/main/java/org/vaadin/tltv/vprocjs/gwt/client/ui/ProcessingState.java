package org.vaadin.tltv.vprocjs.gwt.client.ui;

import com.vaadin.shared.AbstractComponentState;

public class ProcessingState extends AbstractComponentState {

    private String processingCode;

    private boolean mouseClickListened = false;
    private boolean mousePressListened = false;
    private boolean mouseReleaseListened = false;
    private boolean keyPressListened = false;
    private boolean keyReleaseListened = false;
    private boolean mouseEnterListened = false;
    private boolean mouseLeaveListened = false;
    private boolean mouseWheelListened = false;

    public String getProcessingCode() {
        return processingCode;
    }

    public boolean isMouseClickListened() {
        return mouseClickListened;
    }

    public boolean isMousePressListened() {
        return mousePressListened;
    }

    public boolean isMouseReleaseListened() {
        return mouseReleaseListened;
    }

    public boolean isKeyPressListened() {
        return keyPressListened;
    }

    public boolean isKeyReleaseListened() {
        return keyReleaseListened;
    }

    public boolean isMouseEnterListened() {
        return mouseEnterListened;
    }

    public boolean isMouseLeaveListened() {
        return mouseLeaveListened;
    }

    public boolean isMouseWheelListened() {
        return mouseWheelListened;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setMouseClickListened(boolean mouseClickListened) {
        this.mouseClickListened = mouseClickListened;
    }

    public void setMousePressListened(boolean mousePressListened) {
        this.mousePressListened = mousePressListened;
    }

    public void setMouseReleaseListened(boolean mouseReleaseListened) {
        this.mouseReleaseListened = mouseReleaseListened;
    }

    public void setKeyPressListened(boolean keyPressListened) {
        this.keyPressListened = keyPressListened;
    }

    public void setKeyReleaseListened(boolean keyReleaseListened) {
        this.keyReleaseListened = keyReleaseListened;
    }

    public void setMouseEnterListened(boolean mouseEnterListened) {
        this.mouseEnterListened = mouseEnterListened;
    }

    public void setMouseLeaveListened(boolean mouseLeaveListened) {
        this.mouseLeaveListened = mouseLeaveListened;
    }

    public void setMouseWheelListened(boolean mouseWheelListened) {
        this.mouseWheelListened = mouseWheelListened;
    }
}
