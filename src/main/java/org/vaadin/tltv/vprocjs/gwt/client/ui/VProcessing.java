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

import java.util.Map;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;

/**
 * Client side GWT widget wrapping processing.js javascript library. Made for
 * Vaadin Framework 7.</br></br>
 * 
 * By default, this widget has a simple sketch written with Processing
 * Visualization Language that draws a big X over the canvas.</br></br>
 * 
 * This default sketch can be overridden by the
 * {@link #setProcessingCode(String)} method, if you want to use Processing
 * Visualization Language.</br>
 * 
 * Or alternatively by {@link #setProcessingJavaCode(ProcessingCode)} method, if
 * you want to write sketch with Java (with GWT restrictions).
 * 
 * @author Tltv
 * 
 */
public class VProcessing extends Composite implements ClickHandler,
        MouseWheelHandler, MouseUpHandler, MouseDownHandler, MouseOutHandler,
        MouseOverHandler, KeyPressHandler, KeyUpHandler {

    public static final String CLASSNAME = "VProcessing";

    private ProcessingServerRpc rpc;
    private ProcessingEventServerRpc eventrpc;

    private ProcessingJavascriptObject proJsObj;

    private final VerticalPanel panel;

    private ApplicationConnection client;
    private String uidlId;

    protected Canvas canvas;

    private int mouseX = 0;
    private int mouseY = 0;

    /**
     * When useProcessingCode is true, <code>processingCode</code> will be used
     * with <code>defaultCode</code> as backup value for the null or empty
     * <code>processingCode</code>.
     */
    private boolean useProcessingCode = true;

    /**
     * Default Processing Visualization Language code.
     */
    private final String defaultCode = "void setup() {\n" + "size(300, 250);\n"
            + "smooth();\n" + "}\n" + "void draw() {\n"
            + "line(0,0,width,height);\n" + "line(width,0,0,height);\n" + "}";

    /**
     * Current Processing code.
     */
    private String processingCode = null;

    private boolean mouseClickListened = false;
    private boolean mousePressListened = false;
    private boolean mouseReleaseListened = false;
    private boolean mouseEnterListened = false;
    private boolean mouseLeaveListened = false;
    private boolean mouseWheelListened = false;
    private boolean keyPressListened = false;
    private boolean keyReleaseListened = false;

    /** Processing Java sketch instance. */
    private ProcessingCode processingJavaCode;
    private boolean processingJavaCodeChanged = false;
    private Map<Object, Object> sharedVariables = null;

    private String uid;

    public VProcessing() {

        panel = new VerticalPanel();

        canvas = Canvas.createIfSupported();
        canvas.setHeight(300 + "px");
        canvas.setWidth(300 + "px");

        canvas.setStyleName("processing");
        panel.add(canvas);

        canvas.addHandler(this, ClickEvent.getType());
        canvas.addHandler(this, MouseUpEvent.getType());
        canvas.addHandler(this, MouseDownEvent.getType());
        canvas.addHandler(this, MouseOutEvent.getType());
        canvas.addHandler(this, MouseOverEvent.getType());
        canvas.addHandler(this, MouseWheelEvent.getType());
        canvas.addHandler(this, KeyPressEvent.getType());
        canvas.addHandler(this, KeyUpEvent.getType());

        initWidget(panel);

        setStyleName(CLASSNAME);
        canvas.sinkEvents(Event.MOUSEEVENTS + Event.ONMOUSEWHEEL
                + Event.KEYEVENTS);
    }

    public void setRpc(ProcessingServerRpc rpc) {
        this.rpc = rpc;
    }

    public void setEventRpc(ProcessingEventServerRpc eventrpc) {
        this.eventrpc = eventrpc;
    }

    public void setCanvasSize(int width, int height) {
        canvas.setWidth(width + "px");
        canvas.setHeight(height + "px");
    }

    /**
     * Called when state is changed. Basically when server has changed it.
     */
    public void stateChanged() {
        if ("".equals(processingCode)) {
            processingCode = null;
        }

        initCanvas();
    }

    public void setUId(String uid) {
        this.uid = uid;
    }

    /**
     * Get Processing Visualization Language code.
     * 
     * @return String
     */
    public String getProcessingCode() {
        return processingCode;
    }

    /**
     * Set Processing Visualization Language code.
     * 
     * @return
     */
    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
        useProcessingCode = processingJavaCode == null
                || (processingCode != null && processingCode.trim().length() > 0);
    }

    /**
     * Set {@link ProcessingCode} to be used.
     * 
     * @param processingJavaCode
     */
    public void setProcessingJavaCode(ProcessingCode processingJavaCode) {
        if ((processingJavaCode == null && this.processingJavaCode != null)
                || (processingJavaCode != null && this.processingJavaCode == null)
                || (processingJavaCode != null && !processingJavaCode
                        .getClass().getName()
                        .equals(this.processingJavaCode.getClass().getName()))) {
            processingJavaCodeChanged = true;
        }

        if (this.processingJavaCode != null) {
            sharedVariables = this.processingJavaCode.getSharedVariables();
        }
        this.processingJavaCode = processingJavaCode;
        useProcessingCode = processingJavaCode == null;
    }

    /**
     * Returns true, when Processing code should be used.
     * 
     * @return
     */
    public boolean isUseProcessingCode() {
        return useProcessingCode;
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

    public boolean isMouseEnterListened() {
        return mouseEnterListened;
    }

    public boolean isMouseLeaveListened() {
        return mouseLeaveListened;
    }

    public boolean isMouseWheelListened() {
        return mouseWheelListened;
    }

    public boolean isKeyPressListened() {
        return keyPressListened;
    }

    public boolean isKeyReleaseListened() {
        return keyReleaseListened;
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

    public void setMouseEnterListened(boolean mouseEnterListened) {
        this.mouseEnterListened = mouseEnterListened;
    }

    public void setMouseLeaveListened(boolean mouseLeaveListened) {
        this.mouseLeaveListened = mouseLeaveListened;
    }

    public void setMouseWheelListened(boolean mouseWheelListened) {
        this.mouseWheelListened = mouseWheelListened;
    }

    public void setKeyPressListened(boolean keyPressListened) {
        this.keyPressListened = keyPressListened;
    }

    public void setKeyReleaseListened(boolean keyReleaseListened) {
        this.keyReleaseListened = keyReleaseListened;
    }

    int getCanvasAbsoluteTop() {
        return canvas.getAbsoluteTop();
    }

    int getCanvasAbsoluteLeft() {
        return canvas.getAbsoluteLeft();
    }

    native private static Element getCanvas(String canvasid,
            String canvasClass, Element root) /*-{
                                              if (canvasClass) {
                                              var targetcanvas = null;
                                              var cvs = root.getElementsByTagName('canvas');
                                              for ( var i = 0; i < cvs.length; i++ ) {
                                              if(cvs[i].className == canvasClass
                                              && cvs[i].id == canvasid) {
                                              targetcanvas = cvs[i];
                                              }
                                              }
                                              return targetcanvas;
                                              }
                                              return null;
                                              }-*/;

    native private static void initProcessingJsWithCode(
            VProcessing vprocessing, String canvasClass, Element root,
            String processingCode, String canvasid) /*-{

                                                                               var targetcanvas = @org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::getCanvas(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/user/client/Element;)(canvasid, canvasClass, root);
                                                                               targetcanvas.getContext('2d').clearRect(0, 0, targetcanvas.width, targetcanvas.height);
                                                                                
                                                                               var p =  vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::proJsObj;
                                                                               if(p) {
                                                                                  p.exit();
                                                                               }

                                                                               try {
                                                                                   p = new $wnd.Processing(targetcanvas, processingCode);
                                                                                   vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::proJsObj = p;
                                                                               } catch(e) {
                                                                               alert("Failed to execute processing code!\n\nError: " + e.message);
                                                                               }
                                                                               }-*/;

    native private static void initProcessingJs(VProcessing vprocessing,
            String canvasClass, Element root, String canvasid) /*-{
                                                               var targetcanvas = @org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::getCanvas(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/user/client/Element;)(canvasid, canvasClass, root);
                                                               
                                                               var p = vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::proJsObj;
                                                               if(p) {
                                                                  p.exit();
                                                               }
                                                               
                                                               try {
                                                                   p = new $wnd.Processing(targetcanvas);
                                                                   
                                                                   p.mouseMoved = function mouseMoved() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::mouseMoved()();
                                                                   }
                                                                   p.mouseDragged = function mouseDragged() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::mouseDragged()();
                                                                   }
                                                                   p.mousePressed = function mousePressed() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::mousePressed()();
                                                                   }
                                                                   p.mouseClicked = function mouseClicked() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::mouseClicked()();
                                                                   }
                                                                   p.mouseReleased = function mouseReleased() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::mouseReleased()();
                                                                   }
                                                                   p.keyPressed = function keyPressed() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::onKeyPress(I)(this.key);
                                                                   }
                                                                   p.keyReleased = function keyReleased() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::onKeyRelease()();
                                                                   }
                                                                   p.keyTyped = function keyTyped() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::keyTyped()();
                                                                   }
                                                                       
                                                                   p.setup = function setup() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::setup()();
                                                                   }
                                                                   p.draw = function draw() {
                                                                       vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::draw()();
                                                                   }
                                                                       
                                                                   vprocessing.@org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::proJsObj = p;
                                                                   p.setup();
                                                               } catch(e) {
                                                               alert("Failed to execute processing javascript code!\n\nError: " + e.message);
                                                               }
                                                                       }-*/;

    /**
     * Initializes the canvas and also the ProcessingJsObject. Detects the
     * changed sketch and updates the component.
     */
    private void initCanvas() {
        if (canvas.getStyleName() == null || canvas.getStyleName().length() < 1) {
            return;
        }

        String id = uid + "_canvas";
        canvas.getElement().setAttribute("id", id);

        if (processingJavaCode != null) {
            processingJavaCode.setProcessing(this, sharedVariables);
        }

        if (useProcessingCode) {
            String codeToUse = processingCode;
            if (codeToUse == null || codeToUse.trim().length() == 0) {
                codeToUse = defaultCode;
            }
            initProcessingJsWithCode(this, canvas.getStyleName(), getElement(),
                    codeToUse, id);
        } else {
            if (processingJavaCodeChanged) {
                initProcessingJs(this, canvas.getStyleName(), getElement(), id);
                processingJavaCodeChanged = false;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event
     * .dom.client.ClickEvent)
     */
    @Override
    public void onClick(ClickEvent event) {
        if (mouseClickListened) {
            rpc.fireClick();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseUpHandler#onMouseUp(com.google.gwt
     * .event.dom.client.MouseUpEvent)
     */
    @Override
    public void onMouseUp(MouseUpEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();

        if (mouseReleaseListened) {
            rpc.fireMouseRelease(mouseX, mouseY);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google
     * .gwt.event.dom.client.MouseDownEvent)
     */
    @Override
    public void onMouseDown(MouseDownEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();

        if (mousePressListened) {
            rpc.fireMousePress(mouseX, mouseY);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.user.client.ui.MouseListener#onMouseMove(com.google.gwt
     * .user.client.ui.Widget, int, int)
     */
    public void onMouseMove(Widget w, int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseOutHandler#onMouseOut(com.google
     * .gwt.event.dom.client.MouseOutEvent)
     */
    @Override
    public void onMouseOut(MouseOutEvent event) {
        if (mouseLeaveListened) {
            rpc.fireMouseLeave();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseOverHandler#onMouseOver(com.google
     * .gwt.event.dom.client.MouseOverEvent)
     */
    @Override
    public void onMouseOver(MouseOverEvent event) {
        if (mouseEnterListened) {
            rpc.fireMouseEnter();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseWheelHandler#onMouseWheel(com.google
     * .gwt.event.dom.client.MouseWheelEvent)
     */
    @Override
    public void onMouseWheel(MouseWheelEvent event) {
        if (mouseWheelListened) {
            boolean up = event.isNorth();
            rpc.fireMouseWheel(up, event.getDeltaY());
        }
    }

    @Override
    public void onKeyUp(KeyUpEvent keyUpEvent) {
        if (keyReleaseListened) {
            rpc.fireKeyRelease();
        }
    }

    @Override
    public void onKeyPress(KeyPressEvent keyPressEvent) {
        if (keyPressListened) {
            rpc.fireKeyPress(keyPressEvent.getCharCode());
        }
    }

    /**
     * Invoked on any key press.
     * 
     * @param key
     *            Processing.js key code.
     */
    public void onKeyPress(int key) {
        if (!isAttached()) {
            return;
        }

        if (keyPressListened) {
            rpc.fireKeyPress(key);
        }

        keyPressed();
    }

    /**
     * Invoked on any key release.
     */
    public void onKeyRelease() {
        if (!isAttached()) {
            return;
        }

        if (keyReleaseListened) {
            rpc.fireKeyRelease();
        }

        keyReleased();
    }

    /*****************************************************
     * Processing.js methods
     *****************************************************/

    /**
     * Processing setup-method. This method gets called first when
     * {@link #processingJavaCode} is given.
     */
    public void setup() {
        if (processingJavaCode != null) {
            // Update processing javascript object before setup call
            processingJavaCode.setProcessingJavascriptObject(proJsObj);
            processingJavaCode.setup();
        }
    };

    /**
     * Processing draw-method. This method gets called for every frame when
     * {@link #processingJavaCode} is given.
     */
    public void draw() {
        if (processingJavaCode != null) {
            processingJavaCode.draw();
        }
    };

    /* EVENT HANDLERS */

    /**
     * Processing mouseMoved-method. This method gets called when ever mouse is
     * moved over the canvas and {@link #processingJavaCode} is given.
     */
    public void mouseMoved() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseMoved();
        }
        eventrpc.mouseMoved();
    };

    /**
     * Processing mouseDragged-method. This method gets called when mouse is
     * dragged and {@link #processingJavaCode} is given.
     */
    public void mouseDragged() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseDragged();
        }
        eventrpc.mouseDragged();
    };

    /**
     * Processing mousePressed-method. This method gets called when mouse button
     * is pressed and {@link #processingJavaCode} is given.
     */
    public void mousePressed() {
        if (processingJavaCode != null) {
            processingJavaCode.mousePressed();
        }
        eventrpc.mousePressed();
    };

    /**
     * Processing mouseClicked-method. This method gets called when mouse button
     * is clicked and {@link #processingJavaCode} is given.
     */
    public void mouseClicked() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseClicked();
        }
        eventrpc.mouseClicked();
    };

    /**
     * Processing mouseReleased-method. This method gets called when mouse
     * button is released and {@link #processingJavaCode} is given.
     */
    public void mouseReleased() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseReleased();
        }
        eventrpc.mouseReleased();
    };

    /**
     * Processing keyPressed-method. This method gets called when key is pressed
     * and {@link #processingJavaCode} is given.
     */
    public void keyPressed() {
        if (processingJavaCode != null) {
            processingJavaCode.keyPressed();
        }
        eventrpc.keyPressed();
    };

    /**
     * Processing keyReleased-method. This method gets called when key is
     * released and {@link #processingJavaCode} is given.
     */
    public void keyReleased() {
        if (processingJavaCode != null) {
            processingJavaCode.keyReleased();
        }
        eventrpc.keyReleased();
    };

    /**
     * Processing keyTyped-method. This method gets called when key is typed and
     * {@link #processingJavaCode} is given.
     */
    public void keyTyped() {
        if (processingJavaCode != null) {
            processingJavaCode.keyTyped();
        }
        eventrpc.keyTyped();
    }
}
