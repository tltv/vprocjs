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

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingServerRpc;
import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingState;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 * Server side Vaadin component wrapping processing.js javascript library.
 * </br></br>
 * 
 * Requires that processing-1.4.1.js file exist in the
 * org.vaadin.tltv.vprocjs.component package.
 * 
 * @author Tltv
 * 
 */
@JavaScript({ "processing-1.4.1.js" })
public class Processing extends CustomComponent implements ProcessingServerRpc {

    private static final long serialVersionUID = 1L;

    /** Current Processing Visualization Language code. */
    private String processingCode = "";

    private int mouseX = 0;
    private int mouseY = 0;

    private final List<MouseClickListener> mouseClickListeners = new ArrayList<MouseClickListener>();
    private final List<MousePressListener> mousePressListeners = new ArrayList<MousePressListener>();
    private final List<MouseReleaseListener> mouseReleaseListeners = new ArrayList<MouseReleaseListener>();
    private final List<MouseEnterListener> mouseEnterListeners = new ArrayList<MouseEnterListener>();
    private final List<MouseLeaveListener> mouseLeaveListeners = new ArrayList<MouseLeaveListener>();
    private final List<MouseWheelListener> mouseWheelListeners = new ArrayList<MouseWheelListener>();

    private final List<KeyPressListener> keyPressListeners = new ArrayList<KeyPressListener>();
    private final List<KeyReleaseListener> keyReleaseListeners = new ArrayList<KeyReleaseListener>();

    private static final Method MOUSE_CLICK_METHOD;
    private static final Method MOUSE_PRESS_METHOD;
    private static final Method MOUSE_RELEASE_METHOD;
    private static final Method KEY_PRESS_METHOD;
    private static final Method KEY_RELEASE_METHOD;
    private static final Method MOUSE_ENTER_METHOD;
    private static final Method MOUSE_LEAVE_METHOD;
    private static final Method MOUSE_WHEEL_METHOD;

    static {
        try {
            MOUSE_CLICK_METHOD = MouseClickListener.class.getDeclaredMethod(
                    "mouseClick", new Class[] { ClickEvent.class });
            MOUSE_PRESS_METHOD = MousePressListener.class.getDeclaredMethod(
                    "mousePress", new Class[] { MousePressEvent.class });
            MOUSE_RELEASE_METHOD = MouseReleaseListener.class
                    .getDeclaredMethod("mouseRelease",
                            new Class[] { MouseReleaseEvent.class });
            KEY_PRESS_METHOD = KeyPressListener.class.getDeclaredMethod(
                    "keyPress", new Class[] { KeyPressEvent.class });
            KEY_RELEASE_METHOD = KeyReleaseListener.class.getDeclaredMethod(
                    "keyRelease", new Class[] { KeyReleaseEvent.class });
            MOUSE_ENTER_METHOD = MouseEnterListener.class.getDeclaredMethod(
                    "mouseEnter", new Class[] { MouseEnterEvent.class });
            MOUSE_LEAVE_METHOD = MouseLeaveListener.class.getDeclaredMethod(
                    "mouseLeave", new Class[] { MouseLeaveEvent.class });
            MOUSE_WHEEL_METHOD = MouseWheelListener.class.getDeclaredMethod(
                    "mouseWheel", new Class[] { MouseWheelEvent.class });
        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen
            throw new java.lang.RuntimeException(
                    "Internal error finding methods in Processing");
        }
    }

    private AbsoluteLayout mainLayout;

    public Processing() {
        buildMainLayout();
        setCompositionRoot(mainLayout);
        registerRpc(this, ProcessingServerRpc.class);
    }

    private void buildMainLayout() {
        mainLayout = new AbsoluteLayout();
    }

    @Override
    public ProcessingState getState() {
        return (ProcessingState) super.getState();
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String code) {
        processingCode = code;
        getState().setProcessingCode((code != null) ? code : "");
    }

    @Override
    public void fireClick() {
        fireEvent(new Processing.ClickEvent(this));
    }

    @Override
    public void fireMousePress(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        fireEvent(new Processing.MousePressEvent(this, mouseX, mouseY));
    }

    @Override
    public void fireMouseRelease(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        fireEvent(new Processing.MouseReleaseEvent(this, mouseX, mouseY));
    }

    @Override
    public void fireKeyPress(int key) {
        fireEvent(new Processing.KeyPressEvent(this, key));
    }

    @Override
    public void fireKeyRelease() {
        fireEvent(new Processing.KeyReleaseEvent(this));
    }

    @Override
    public void fireMouseEnter() {
        fireEvent(new Processing.MouseEnterEvent(this));
    }

    @Override
    public void fireMouseLeave() {
        fireEvent(new Processing.MouseLeaveEvent(this));
    }

    @Override
    public void fireMouseWheel(boolean up, int deltaY) {
        fireEvent(new Processing.MouseWheelEvent(this, up, deltaY));
    }

    public void addMouseClickListener(MouseClickListener listener) {
        if (!mouseClickListeners.contains(listener)) {
            mouseClickListeners.add(listener);
            getState().setMouseClickListened(true);
            addListener(ClickEvent.class, listener, MOUSE_CLICK_METHOD);
        }
    }

    public void removeMouseClickListener(MouseClickListener listener) {
        removeListener(ClickEvent.class, listener, MOUSE_CLICK_METHOD);
        mouseClickListeners.remove(listener);
        if (mouseClickListeners.isEmpty()) {
            getState().setMouseClickListened(false);
        }
    }

    public void addMousePressListener(MousePressListener listener) {
        if (!mousePressListeners.contains(listener)) {
            mousePressListeners.add(listener);
            getState().setMousePressListened(true);
            addListener(MousePressEvent.class, listener, MOUSE_PRESS_METHOD);
        }
    }

    public void removeMousePressListener(MousePressListener listener) {
        removeListener(MousePressEvent.class, listener, MOUSE_PRESS_METHOD);
        mousePressListeners.remove(listener);
        if (mousePressListeners.isEmpty()) {
            getState().setMousePressListened(false);
        }
    }

    public void addMouseReleaseListener(MouseReleaseListener listener) {
        if (!mouseReleaseListeners.contains(listener)) {
            mouseReleaseListeners.add(listener);
            getState().setMouseReleaseListened(true);
            addListener(MouseReleaseEvent.class, listener, MOUSE_RELEASE_METHOD);
        }
    }

    public void removeMouseReleaseListener(MouseReleaseListener listener) {
        removeListener(MouseReleaseEvent.class, listener, MOUSE_RELEASE_METHOD);
        mouseReleaseListeners.remove(listener);
        if (mouseReleaseListeners.isEmpty()) {
            getState().setMouseReleaseListened(false);
        }
    }

    public void addKeyPressListener(KeyPressListener listener) {
        if (!keyPressListeners.contains(listener)) {
            keyPressListeners.add(listener);
            getState().setKeyPressListened(true);
            addListener(KeyPressEvent.class, listener, KEY_PRESS_METHOD);
        }
    }

    public void removeKeyPressListener(KeyPressListener listener) {
        removeListener(KeyPressEvent.class, listener, KEY_PRESS_METHOD);
        keyPressListeners.remove(listener);
        if (keyPressListeners.isEmpty()) {
            getState().setKeyPressListened(false);
        }
    }

    public void addKeyReleaseListener(KeyReleaseListener listener) {
        if (!keyReleaseListeners.contains(listener)) {
            keyReleaseListeners.add(listener);
            getState().setKeyReleaseListened(true);
            addListener(KeyReleaseEvent.class, listener, KEY_RELEASE_METHOD);
        }
    }

    public void removeKeyReleaseListener(KeyReleaseListener listener) {
        removeListener(KeyReleaseEvent.class, listener, KEY_RELEASE_METHOD);
        keyReleaseListeners.remove(listener);
        if (keyReleaseListeners.isEmpty()) {
            getState().setKeyReleaseListened(false);
        }
    }

    public void addMouseEnterListener(MouseEnterListener listener) {
        if (!mouseEnterListeners.contains(listener)) {
            mouseEnterListeners.add(listener);
            getState().setMouseEnterListened(true);
            addListener(MouseEnterEvent.class, listener, MOUSE_ENTER_METHOD);
        }
    }

    public void removeMouseEnterListener(MouseEnterListener listener) {
        removeListener(MouseEnterEvent.class, listener, MOUSE_ENTER_METHOD);
        mouseEnterListeners.remove(listener);
        if (mouseEnterListeners.isEmpty()) {
            getState().setMouseEnterListened(false);
        }
    }

    public void addMouseLeaveListener(MouseLeaveListener listener) {
        if (!mouseLeaveListeners.contains(listener)) {
            mouseLeaveListeners.add(listener);
            getState().setMouseLeaveListened(true);
            addListener(MouseLeaveEvent.class, listener, MOUSE_LEAVE_METHOD);
        }
    }

    public void removeMouseLeaveListener(MouseLeaveListener listener) {
        removeListener(MouseLeaveEvent.class, listener, MOUSE_LEAVE_METHOD);
        mouseLeaveListeners.remove(listener);
        if (mouseLeaveListeners.isEmpty()) {
            getState().setMouseEnterListened(false);
        }
    }

    public void addMouseWheelListener(MouseWheelListener listener) {
        if (!mouseWheelListeners.contains(listener)) {
            mouseWheelListeners.add(listener);
            getState().setMouseWheelListened(true);
            addListener(MouseWheelEvent.class, listener, MOUSE_WHEEL_METHOD);
        }
    }

    public void removeMouseWheelListener(MouseWheelListener listener) {
        removeListener(MouseWheelEvent.class, listener, MOUSE_WHEEL_METHOD);
        mouseWheelListeners.remove(listener);
        if (mouseWheelListeners.isEmpty()) {
            getState().setMouseWheelListened(false);
        }
    }

    /**
     * Get mouse x-coordinate.
     * 
     * @return
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Set mouse x-coordinate.
     * 
     * @param mouseX
     */
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    /**
     * Get mouse y-coordinate.
     * 
     * @return
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * Set mouse y-coordinate.
     * 
     * @param mouseX
     */
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public interface MouseClickListener extends Serializable {

        public void mouseClick(ClickEvent event);
    }

    public interface MousePressListener extends Serializable {

        public void mousePress(MousePressEvent event);
    }

    public interface MouseReleaseListener extends Serializable {

        public void mouseRelease(MouseReleaseEvent event);
    }

    public interface KeyPressListener extends Serializable {

        public void keyPress(KeyPressEvent event);
    }

    public interface KeyReleaseListener extends Serializable {

        public void keyRelease(KeyReleaseEvent event);
    }

    public interface MouseEnterListener extends Serializable {

        public void mouseEnter(MouseEnterEvent event);
    }

    public interface MouseLeaveListener extends Serializable {

        public void mouseLeave(MouseLeaveEvent event);
    }

    public interface MouseWheelListener extends Serializable {

        public void mouseWheel(MouseWheelEvent event);
    }

    public class ClickEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        public ClickEvent(Component source) {
            super(source);
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }
    }

    public class MousePressEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        private final int x;

        private final int y;

        public MousePressEvent(Component source, int x, int y) {
            super(source);
            this.x = x;
            this.y = y;
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public class MouseReleaseEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        private final int x;

        private final int y;

        public MouseReleaseEvent(Component source, int x, int y) {
            super(source);
            this.x = x;
            this.y = y;
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public class KeyPressEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        private final int key;

        public KeyPressEvent(Component source, int key) {
            super(source);
            this.key = key;
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }

        public int getKey() {
            return key;
        }
    }

    public class KeyReleaseEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        public KeyReleaseEvent(Component source) {
            super(source);
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }
    }

    public class MouseEnterEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        public MouseEnterEvent(Component source) {
            super(source);
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }
    }

    public class MouseLeaveEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        public MouseLeaveEvent(Component source) {
            super(source);
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }
    }

    public class MouseWheelEvent extends Component.Event {

        private static final long serialVersionUID = 1L;

        private final boolean up;

        private final int deltaY;

        public MouseWheelEvent(Component source, boolean up, int deltaY) {
            super(source);
            this.up = up;
            this.deltaY = deltaY;
        }

        @Override
        public Processing getSource() {
            return (Processing) super.getSource();
        }

        public boolean isUp() {
            return up;
        }

        public boolean isDown() {
            return !up;
        }

        public int getDeltaY() {
            return deltaY;
        }
    }

}
