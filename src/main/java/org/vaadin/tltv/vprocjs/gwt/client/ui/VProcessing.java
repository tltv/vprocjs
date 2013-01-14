package org.vaadin.tltv.vprocjs.gwt.client.ui;

import java.util.Map;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

public class VProcessing extends Composite implements ProcessingJs,
        ClickHandler, MouseWheelHandler, MouseUpHandler, MouseDownHandler,
        MouseOutHandler, MouseOverHandler {

    public static final String CLASSNAME = "VProcessing";

    private ProcessingServerRpc rpc;
    private ProcessingEventServerRpc eventrpc;

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
     * Default Processing code
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

        initWidget(panel);

        setStyleName(CLASSNAME);
        canvas.sinkEvents(Event.MOUSEEVENTS + Event.ONMOUSEWHEEL);
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
     * Get Processing code.
     * 
     * @return String
     */
    public String getProcessingCode() {
        return processingCode;
    }

    /**
     * Set Processing code.
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
                                                                                
                                                                               var p = root.processingjs;
                                                                               if(p) {
                                                                                  p.exit();
                                                                               }

                                                                               try {
                                                                                   p = new $wnd.Processing(targetcanvas, processingCode);
                                                                                   root.processingjs = p;
                                                                               } catch(e) {
                                                                               alert("Failed to execute processing code!\n\nError: " + e.message);
                                                                               }
                                                                               }-*/;

    native private static void initProcessingJs(VProcessing vprocessing,
            String canvasClass, Element root, String canvasid) /*-{
                                                               var targetcanvas = @org.vaadin.tltv.vprocjs.gwt.client.ui.VProcessing::getCanvas(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/user/client/Element;)(canvasid, canvasClass, root);
                                                               
                                                               var p = root.processingjs;
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
                                                                       
                                                                   root.processingjs = p;
                                                                   root.processingjs.setup();
                                                               } catch(e) {
                                                               alert("Failed to execute processing javascript code!\n\nError: " + e.message);
                                                               }
                                                                       }-*/;

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
     * Processing setup. When useProcessingCode is false, this method may be
     * overridden by the class extending this Widget.
     */
    @Override
    public void setup() {
        if (processingJavaCode != null) {
            processingJavaCode.setup();
        }
    };

    /**
     * Processing draw. When useProcessingCode is false, this method may be
     * overridden by the class extending this Widget.
     */
    @Override
    public void draw() {
        if (processingJavaCode != null) {
            processingJavaCode.draw();
        }
    };

    /* EVENT HANDLERS */

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void mouseMoved() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseMoved();
        }
        eventrpc.mouseMoved();
    };

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void mouseDragged() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseDragged();
        }
        eventrpc.mouseDragged();
    };

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void mousePressed() {
        if (processingJavaCode != null) {
            processingJavaCode.mousePressed();
        }
        eventrpc.mousePressed();
    };

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void mouseClicked() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseClicked();
        }
        eventrpc.mouseClicked();
    };

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void mouseReleased() {
        if (processingJavaCode != null) {
            processingJavaCode.mouseReleased();
        }
        eventrpc.mouseReleased();
    };

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void keyPressed() {
        if (processingJavaCode != null) {
            processingJavaCode.keyPressed();
        }
        eventrpc.keyPressed();
    };

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void keyReleased() {
        if (processingJavaCode != null) {
            processingJavaCode.keyReleased();
        }
        eventrpc.keyReleased();
    };

    /**
     * Override this when processing code is not used.
     */
    @Override
    public void keyTyped() {
        if (processingJavaCode != null) {
            processingJavaCode.keyTyped();
        }
        eventrpc.keyTyped();
    };

    /* PROCESSING METHODS */
    public void ellipse(float x, float y, float width, float height) {
        ellipse(getElement(), x, y, width, height);
    }

    native private static void ellipse(Element element, float x, float y,
            float width, float height) /*-{
                                       element.processingjs.ellipse(x, y, width, height);
                                       }-*/;

    @Override
    public void ellipse(int x, int y, int width, int height) {
        ellipse(getElement(), x, y, width, height);
    }

    native private static void ellipse(Element element, int x, int y,
            float width, float height) /*-{
                                       element.processingjs.ellipse(x, y, width, height);
                                       }-*/;

    @Override
    public void rect(int x, int y, int width, int height) {
        rect(getElement(), x, y, width, height);
    }

    native private static void rect(Element element, int x, int y, int width,
            int height) /*-{
                        element.processingjs.rect(x, y, width, height);
                        }-*/;

    public void rect(float x, float y, float width, float height) {
        rect(getElement(), x, y, width, height);
    }

    native private static void rect(Element element, float x, float y,
            float width, float height) /*-{
                                       element.processingjs.rect(x, y, width, height);
                                       }-*/;

    @Override
    public void quad(int x1, int y1, int x2, int y2, int x3, int y3, int x4,
            int y4) {
        quad(getElement(), x1, y1, x2, y2, x3, y3, x4, y4);
    }

    native private static void quad(Element element, int x1, int y1, int x2,
            int y2, int x3, int y3, int x4, int y4) /*-{
                                                    element.processingjs.quad(x1, y1, x2, y2, x3, y3, x4, y4);
                                                    }-*/;

    @Override
    public void triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        triangle(getElement(), x1, y1, x2, y2, x3, y3);
    }

    native private static void triangle(Element element, int x1, int y1,
            int x2, int y2, int x3, int y3) /*-{
                                            element.processingjs.triangle(x1, y1, x2, y2, x3, y3);
                                            }-*/;

    @Override
    public void bezier(int x1, int y1, int x2, int y2, int x3, int y3, int x4,
            int y4) {
        bezier(getElement(), x1, y1, x2, y2, x3, y3, x4, y4);
    }

    native private static void bezier(Element element, int x1, int y1, int x2,
            int y2, int x3, int y3, int x4, int y4) /*-{
                                                    element.processingjs.bezier(x1, y1, x2, y2, x3, y3, x4, y4);
                                                    }-*/;

    @Override
    public void line(int x1, int y1, int x2, int y2) {
        line(getElement(), x1, y1, x2, y2);
    }

    native private static void line(Element element, int x1, int y1, int x2,
            int y2) /*-{
                    element.processingjs.line(x1, y1, x2, y2);
                    }-*/;

    public void line(float x1, float y1, float x2, float y2) {
        line(getElement(), x1, y1, x2, y2);
    }

    native private static void line(Element element, float x1, float y1,
            float x2, float y2) /*-{
                                element.processingjs.line(x1, y1, x2, y2);
                                }-*/;

    @Override
    public void arc(int x, int y, int width, int height, int start, int stop) {
        arc(getElement(), x, y, width, height, start, stop);
    }

    native private static void arc(Element element, int x, int y, int width,
            int height, int start, int stop) /*-{
                                             element.processingjs.arc(x, y, width, height, start, stop);
                                             }-*/;

    @Override
    public void set(int x, int y, Object obj) {
        set(getElement(), x, y, obj);
    }

    native private static void set(Element element, int x, int y, Object obj) /*-{
                                                                              element.processingjs.set(x, y, obj);
                                                                              }-*/;

    @Override
    public Object get(int x, int y) {
        return get(getElement(), x, y);
    }

    native private static Object get(Element element, int x, int y) /*-{
                                                                    return element.processingjs.get(x, y);
                                                                    }-*/;

    @Override
    public void point(int x, int y) {
        point(getElement(), x, y);
    }

    native private static void point(Element element, int x, int y) /*-{
                                                                    element.processingjs.point(x, y);
                                                                    }-*/;

    @Override
    public void strokeWeight(int w) {
        strokeWeight(getElement(), w);
    }

    native private static void strokeWeight(Element element, int w) /*-{
                                                                    element.processingjs.strokeWeight(w);
                                                                    }-*/;

    @Override
    public void strokeWeight(float w) {
        strokeWeight(getElement(), w);
    }

    native private static void strokeWeight(Element element, float w) /*-{
                                                                      element.processingjs.strokeWeight(w);
                                                                      }-*/;

    @Override
    public void stroke() {
        stroke(getElement());
    }

    native private static void stroke(Element element) /*-{
                                                       element.processingjs.stroke();
                                                       }-*/;

    @Override
    public void stroke(int c) {
        stroke(getElement(), c);
    }

    native private static void stroke(Element element, int c) /*-{
                                                              element.processingjs.stroke(c);
                                                              }-*/;

    @Override
    public void stroke(int r, int g, int b) {
        stroke(getElement(), r, g, b);
    }

    native private static void stroke(Element element, int r, int g, int b) /*-{
                                                                            element.processingjs.stroke(r, g, b);
                                                                            }-*/;

    @Override
    public void stroke(float r, float g, float b) {
        stroke(getElement(), r, g, b);
    }

    native private static void stroke(Element element, float r, float g, float b) /*-{
                                                                                  element.processingjs.stroke(r, g, b);
                                                                                  }-*/;

    @Override
    public void fill() {
        fill(getElement());
    }

    native private static void fill(Element element) /*-{
                                                     element.processingjs.fill();
                                                     }-*/;

    public void fill(int r, int g, int b) {
        fill(getElement(), r, g, b);
    }

    native private static void fill(Element element, int r, int g, int b) /*-{
                                                                          element.processingjs.fill(r, g, b);
                                                                          }-*/;

    @Override
    public float abs(float number) {
        return abs(getElement(), number);
    }

    native private static float abs(Element element, float number) /*-{
                                                                   return element.processingjs.abs(number);
                                                                   }-*/;

    @Override
    public float atan2(float number, float number2) {
        return atan2(getElement(), number, number2);
    }

    native private static float atan2(Element element, float number,
            float number2) /*-{
                           return element.processingjs.atan2(number, number2);
                           }-*/;

    @Override
    public float constrain(float number, float min, float max) {
        return constrain(getElement(), min, max);
    }

    native private static float constrain(Element element, float min, float max) /*-{
                                                                                 return element.processingjs.constrain(min, max);
                                                                                 }-*/;

    @Override
    public float cos(float number) {
        return cos(getElement(), number);
    }

    native private static float cos(Element element, float number) /*-{
                                                                   return element.processingjs.cos(number);
                                                                   }-*/;

    @Override
    public float degrees(float angle) {
        return degrees(getElement(), angle);
    }

    native private static float degrees(Element element, float angle) /*-{
                                                                      return element.processingjs.degrees(angle);
                                                                      }-*/;

    @Override
    public void noFill() {
        noFill(getElement());
    }

    native private static void noFill(Element element) /*-{
                                                       element.processingjs.noFill();
                                                       }-*/;

    @Override
    public void noLoop() {
        noLoop(getElement());
    }

    native private static void noLoop(Element element) /*-{
                                                       element.processingjs.noLoop();
                                                       }-*/;

    @Override
    public void noSmooth() {
        noSmooth(getElement());
    }

    native private static void noSmooth(Element element) /*-{
                                                         element.processingjs.noSmooth();
                                                         }-*/;

    @Override
    public void noStroke() {
        noStroke(getElement());
    }

    native private static float noStroke(Element element) /*-{
                                                          element.processingjs.noStroke();
                                                          }-*/;

    @Override
    public float pow(float number, float exponent) {
        return pow(getElement(), number, exponent);
    }

    native private static float pow(Element element, float number,
            float exponent) /*-{
                            return element.processingjs.pow(number, exponent);
                            }-*/;

    @Override
    public float radians(float angle) {
        return radians(getElement(), angle);
    }

    native private static float radians(Element element, float angle) /*-{
                                                                      return element.processingjs.radians(angle);
                                                                      }-*/;

    @Override
    public float sin(float number) {
        return sin(getElement(), number);
    }

    native private static float sin(Element element, float number) /*-{
                                                                   return element.processingjs.sin(number);
                                                                   }-*/;

    @Override
    public void size(int width, int height) {
        size(getElement(), width, height);
    }

    native private static void size(Element element, int width, int height) /*-{
                                                                            element.processingjs.size(width, height);
                                                                            }-*/;

    @Override
    public void smooth() {
        smooth(getElement());
    }

    native private static void smooth(Element element) /*-{
                                                       element.processingjs.smooth();
                                                       }-*/;

    @Override
    public float sqrt(float number) {
        return sqrt(getElement(), number);
    }

    native private static float sqrt(Element element, float number) /*-{
                                                                    return element.processingjs.sqrt(number);
                                                                    }-*/;

    @Override
    public float noise(int x, int y, int z) {
        return noise(getElement(), x, y, z);
    }

    native private static float noise(Element element, int x, int y, int z) /*-{
                                                                            return element.processingjs.noise(x, y, z);
                                                                            }-*/;

    @Override
    public float random(float min, float max) {
        return random(getElement(), min, max);
    }

    native private static float random(Element element, float min, float max) /*-{
                                                                              return element.processingjs.random(min, max);
                                                                              }-*/;

    @Override
    public float random(float min) {
        return random(getElement(), min);
    }

    native private static float random(Element element, float min) /*-{
                                                                   return element.processingjs.random(min);
                                                                   }-*/;

    @Override
    public void background() {
        background(getElement());
    }

    native private static void background(Element element) /*-{
                                                           element.processingjs.background();
                                                           }-*/;

    @Override
    public void background(String imgPath) {
        if (imgPath == null || imgPath.trim().length() == 0) {
            return;
        }
        background(getElement(), imgPath);
    }

    native private static void background(Element element, String imgPath) /*-{
                                                                           element.processingjs.background(element.processingjs.loadImage(imgPath));
                                                                           }-*/;

    @Override
    public void background(int gray) {
        background(getElement(), gray);
    }

    native private static void background(Element element, int gray) /*-{
                                                                     element.processingjs.background(gray);
                                                                     }-*/;

    @Override
    public void background(int r, int g, int b) {
        background(getElement(), r, g, b);
    }

    native private static void background(Element element, int r, int g, int b) /*-{
                                                                                element.processingjs.background(r, g, b);
                                                                                }-*/;

    @Override
    public void beginShape(int type) {
        beginShape(getElement(), type);
    }

    native private static void beginShape(Element element, int type) /*-{
                                                                     element.processingjs.beginShape(type);
                                                                     }-*/;

    @Override
    public float bytee(float number) {
        return bytee(getElement(), number);
    }

    native private static float bytee(Element element, float number) /*-{
                                                                     return element.processingjs.bytee(number);
                                                                     }-*/;

    @Override
    public float ceil(float number) {
        return ceil(getElement(), number);
    }

    native private static float ceil(Element element, float number) /*-{
                                                                    return element.processingjs.ceil(number);
                                                                    }-*/;

    @Override
    public void clear() {
        clear(getElement());
    }

    native private static float clear(Element element) /*-{
                                                       element.processingjs.clear();
                                                       }-*/;

    @Override
    public void clear(int x, int y, int width, int height) {
        clear(getElement(), x, y, width, height);
    }

    native private static void clear(Element element, int x, int y, int width,
            int height) /*-{
                        element.processingjs.clear(x, y, width, height);
                        }-*/;

    @Override
    public void colorMode(int mode, int range1) {
        colorMode(getElement(), mode, range1);
    }

    native private static void colorMode(Element element, int mode, int range1) /*-{
                                                                                element.processingjs.colorMode(mode, range1);
                                                                                }-*/;

    @Override
    public void colorMode(int mode, int range1, int range2, int range3) {
        colorMode(getElement(), mode, range1, range2, range3);
    }

    native private static void colorMode(Element element, int mode, int range1,
            int range2, int range3) /*-{
                                    element.processingjs.colorMode(mode, range1, range2, range3);
                                    }-*/;

    @Override
    public void colorMode(int mode, int range1, int range2, int range3,
            int range4) {
        colorMode(getElement(), mode, range1, range2, range3, range4);
    }

    native private static void colorMode(Element element, int mode, int range1,
            int range2, int range3, int range4) /*-{
                                                element.processingjs.colorMode(mode, range1, range2, range3, range4);
                                                }-*/;

    @Override
    public void curveTightness(int tightness) {
        curveTightness(getElement(), tightness);
    }

    native private static void curveTightness(Element element, int tightness) /*-{
                                                                              element.processingjs.curveTightness(tightness);
                                                                              }-*/;

    @Override
    public void curveVertex(int x, int y, int x2, int y2) {
        curveVertex(getElement(), x, y, x2, y2);
    }

    native private static void curveVertex(Element element, int x, int y,
            int x2, int y2) /*-{
                            element.processingjs.curveVertex(x, y, x2, y2);
                            }-*/;

    @Override
    public int day() {
        return day(getElement());
    }

    native private static int day(Element element) /*-{
                                                   return element.processingjs.day();
                                                   }-*/;

    @Override
    public float dist(int x1, int y1, int x2, int y2) {
        return dist(getElement(), x1, y1, x2, y2);
    }

    native private static float dist(Element element, int x1, int y1, int x2,
            int y2) /*-{
                    return element.processingjs.dist(x1, y1, x2, y2);
                    }-*/;

    @Override
    public void ellipseMode(int ellipseMode) {
        ellipseMode(getElement(), ellipseMode);
    }

    native private static void ellipseMode(Element element, int ellipseMode) /*-{
                                                                             element.processingjs.ellipseMode(ellipseMode);
                                                                             }-*/;

    @Override
    public void endShape(boolean close) {
        endShape(getElement(), close);
    }

    native private static void endShape(Element element, boolean close) /*-{
                                                                        element.processingjs.endShape(close);
                                                                        }-*/;

    public void endShape() {
        endShape(getElement());
    }

    native private static void endShape(Element element) /*-{
                                                         element.processingjs.endShape(true);
                                                         }-*/;

    @Override
    public float floatt(float number) {
        return floatt(getElement(), number);
    }

    native private static float floatt(Element element, float number) /*-{
                                                                      return element.processingjs.floatt(number);
                                                                      }-*/;

    @Override
    public float floor(float number) {
        return floor(getElement(), number);
    }

    native private static float floor(Element element, float number) /*-{
                                                                     return element.processingjs.floor(number);
                                                                     }-*/;

    @Override
    public void frameRate(int rate) {
        frameRate(getElement(), rate);
    }

    native private static void frameRate(Element element, int rate) /*-{
                                                                    element.processingjs.frameRate(rate);
                                                                    }-*/;

    @Override
    public int hour() {
        return hour(getElement());
    }

    native private static int hour(Element element) /*-{
                                                    return element.processingjs.hour();
                                                    }-*/;

    @Override
    public float inte(float number) {
        return inte(getElement(), number);
    }

    native private static float inte(Element element, float number) /*-{
                                                                    return element.processingjs.inte(rate);
                                                                    }-*/;

    @Override
    public float lerp(float value1, float value2, float amt) {
        return lerp(getElement(), value1, value2, amt);
    }

    native private static float lerp(Element element, float value1,
            float value2, float amt) /*-{
                                     return element.processingjs.lerp(value1, value2, amt);
                                     }-*/;

    @Override
    public void loop() {
        loop(getElement());
    }

    native private static void loop(Element element) /*-{
                                                     element.processingjs.loop();
                                                     }-*/;

    @Override
    public float max(float number, float number2) {
        return max(getElement(), number, number2);
    }

    native private static float max(Element element, float number, float number2) /*-{
                                                                                  return element.processingjs.max(number, number2);
                                                                                  }-*/;

    @Override
    public int millis() {
        return millis(getElement());
    }

    native private static int millis(Element element) /*-{
                                                      return element.processingjs.millis();
                                                      }-*/;

    @Override
    public float min(float number, float number2) {
        return min(getElement(), number, number2);
    }

    native private static float min(Element element, float number, float number2) /*-{
                                                                                  return element.processingjs.min(number, number2);
                                                                                  }-*/;

    @Override
    public int minute() {
        return minute(getElement());
    }

    native private static int minute(Element element) /*-{
                                                      return element.processingjs.minute();
                                                      }-*/;

    @Override
    public int month() {
        return month(getElement());
    }

    native private static int month(Element element) /*-{
                                                     return element.processingjs.month();
                                                     }-*/;

    @Override
    public float norm(float number, float low, float high) {
        return norm(getElement(), number, low, high);
    }

    native private static float norm(Element element, float number, float low,
            float high) /*-{
                        return element.processingjs.norm(number, low, high);
                        }-*/;

    @Override
    public void popMatrix() {
        popMatrix(getElement());
    }

    native private static void popMatrix(Element element) /*-{
                                                          element.processingjs.popMatrix();
                                                          }-*/;

    @Override
    public void pushMatrix() {
        pushMatrix(getElement());
    }

    native private static void pushMatrix(Element element) /*-{
                                                           element.processingjs.pushMatrix();
                                                           }-*/;

    @Override
    public void rectMode(int rectMode) {
        rectMode(getElement(), rectMode);
    }

    native private static void rectMode(Element element, int rectMode) /*-{
                                                                       element.processingjs.rectMode(rectMode);
                                                                       }-*/;

    @Override
    public void redraw() {
        redraw(getElement());
    }

    native private static void redraw(Element element) /*-{
                                                       element.processingjs.redraw();
                                                       }-*/;

    @Override
    public void rotate(float angle) {
        rotate(getElement(), angle);
    }

    native private static void rotate(Element element, float angle) /*-{
                                                                    element.processingjs.rotate(angle);
                                                                    }-*/;

    @Override
    public float round(float number) {
        return round(getElement(), number);
    }

    native private static float round(Element element, float number) /*-{
                                                                     return element.processingjs.round(number);
                                                                     }-*/;

    @Override
    public void scale(int x, int y) {
        scale(getElement(), x, y);
    }

    native private static void scale(Element element, int x, int y) /*-{
                                                                    element.processingjs.scale(x, y);
                                                                    }-*/;

    @Override
    public void scale(float x, float y) {
        scale(getElement(), x, y);
    }

    native private static void scale(Element element, float x, float y) /*-{
                                                                        element.processingjs.scale(x, y);
                                                                        }-*/;

    @Override
    public int second() {
        return second(getElement());
    }

    native private static int second(Element element) /*-{
                                                      return element.processingjs.second();
                                                      }-*/;

    @Override
    public float sq(float number) {
        return sq(getElement(), number);
    }

    native private static float sq(Element element, float number) /*-{
                                                                  return element.processingjs.sq(number);
                                                                  }-*/;

    @Override
    public String str(float number) {
        return str(getElement(), number);
    }

    native private static String str(Element element, float number) /*-{
                                                                    return element.processingjs.str(number);
                                                                    }-*/;

    @Override
    public void translate(int x, int y) {
        translate(getElement(), x, y);
    }

    native private static void translate(Element element, int x, int y) /*-{
                                                                        element.processingjs.translate(x, y);
                                                                        }-*/;

    @Override
    public void translate(float x, float y) {
        translate(getElement(), x, y);
    }

    native private static void translate(Element element, float x, float y) /*-{
                                                                            element.processingjs.translate(x, y);
                                                                            }-*/;

    @Override
    public void vertex(float x, float y, float x2, float y2, float x3, float y3) {
        vertex(getElement(), x, y, x2, y2, x3, y3);
    }

    native private static void vertex(Element element, float x, float y,
            float x2, float y2, float x3, float y3) /*-{
                                                    element.processingjs.vertex(x, y, x2, y2, x3, y3);
                                                    }-*/;

    @Override
    public void vertex(int x, int y, int x2, int y2, int x3, int y3) {
        vertex(getElement(), x, y, x2, y2, x3, y3);
    }

    native private static void vertex(Element element, int x, int y, int x2,
            int y2, int x3, int y3) /*-{
                                    element.processingjs.vertex(x, y, x2, y2, x3, y3);
                                    }-*/;

    @Override
    public void vertex(int x, int y) {
        vertex(getElement(), x, y);
    }

    native private static void vertex(Element element, int x, int y) /*-{
                                                                     element.processingjs.vertex(x, y);
                                                                     }-*/;

    @Override
    public void vertex(float x, float y) {
        vertex(getElement(), x, y);
    }

    native private static void vertex(Element element, float x, float y) /*-{
                                                                         element.processingjs.vertex(x, y);
                                                                         }-*/;

    @Override
    public void vertex(int x, int y, int x2, int y2) {
        vertex(getElement(), x, y, x2, y2);
    }

    native private static void vertex(Element element, int x, int y, int x2,
            int y2) /*-{
                    element.processingjs.vertex(x, y, x2, y2);
                    }-*/;

    @Override
    public void vertex(float x, float y, float x2, float y2) {
        vertex(getElement(), x, y, x2, y2);
    }

    native private static void vertex(Element element, float x, float y,
            float x2, float y2) /*-{
                                element.processingjs.vertex(x, y, x2, y2);
                                }-*/;

    @Override
    public int year() {
        return year(getElement());
    }

    native private static int year(Element element) /*-{
                                                    return element.processingjs.year();
                                                    }-*/;

    @Override
    public void text(String str, int x, int y) {
        text(getElement(), str, x, y);
    }

    native private static void text(Element element, String str, int x, int y) /*-{
                                                                               element.processingjs.text(str, x, y);
                                                                               }-*/;

    @Override
    public void textSize(int size) {
        textSize(getElement(), size);
    }

    native private static void textSize(Element element, int size) /*-{
                                                                   element.processingjs.textSize(str, size);
                                                                   }-*/;

    @Override
    public void textFont(Object name, int size) {
        textFont(getElement(), name, size);
    }

    native private static void textFont(Element element, Object name, int size) /*-{
                                                                                element.processingjs.textFont(name, size);
                                                                                }-*/;

    @Override
    public int loadFont(String name) {
        return loadFont(getElement(), name);
    }

    native private static int loadFont(Element element, String name) /*-{
                                                                     return element.processingjs.loadFont(name);
                                                                     }-*/;

    @Override
    public Object loadImage(String name) {
        return loadImage(getElement(), name);
    }

    native private static Object loadImage(Element element, String name) /*-{
                                                                         return element.processingjs.loadImage(name);
                                                                         }-*/;

    @Override
    public void exit() {
        exit(getElement());
    }

    native private static void exit(Element element) /*-{
                                                     element.processingjs.exit();
                                                     }-*/;

    @Override
    public void image(Object img, int x, int y) {
        image(getElement(), img, x, y);
    }

    native private static void image(Element element, Object img, int x, int y) /*-{
                                                                                element.processingjs.image(img, x, y);
                                                                                }-*/;

    @Override
    public void image(Object img, int x, int y, int w, int h) {
        image(getElement(), img, x, y, w, h);
    }

    native private static void image(Element element, Object img, int x, int y,
            int w, int h) /*-{
                          element.processingjs.image(img, x, y, w, h);
                          }-*/;

    @Override
    public void tint(int rgb, int a) {
        tint(getElement(), rgb, a);
    }

    native private static void tint(Element element, int rgb, int a) /*-{
                                                                     element.processingjs.tint(rgb, a);
                                                                     }-*/;

    @Override
    public Object createImage(int w, int h, int mode) {
        return createImage(getElement(), w, h, mode);
    }

    native private static Object createImage(Element element, int w, int h,
            int mode) /*-{
                      return element.processingjs.createImage(w, h, mode);
                      }-*/;

    @Override
    public String nf(int num, int pad) {
        return nf(getElement(), num, pad);
    }

    native private static String nf(Element element, int num, int pad) /*-{
                                                                       return element.processingjs.nf(num, pad);
                                                                       }-*/;

    @Override
    public Object lerpColor(Object c1, Object c2, float amt) {
        return lerpColor(getElement(), c1, c2, amt);
    }

    native private static Object lerpColor(Element element, Object c1,
            Object c2, float amt) /*-{
                                  return element.processingjs.lerpColor(c1, c2, amt);
                                  }-*/;

    @Override
    public float alpha(Object aColor) {
        return alpha(getElement(), aColor);
    }

    native private static float alpha(Element element, Object aColor) /*-{
                                                                      return element.processingjs.alpha(aColor);
                                                                      }-*/;

    @Override
    public float blue(Object color) {
        return blue(getElement(), color);
    }

    native private static float blue(Element element, Object aColor) /*-{
                                                                     return element.processingjs.blue(aColor);
                                                                     }-*/;

    @Override
    public float green(Object color) {
        return green(getElement(), color);
    }

    native private static float green(Element element, Object aColor) /*-{
                                                                      return element.processingjs.green(aColor);
                                                                      }-*/;

    @Override
    public float red(Object color) {
        return red(getElement(), color);
    }

    native private static float red(Element element, Object aColor) /*-{
                                                                    return element.processingjs.red(aColor);
                                                                    }-*/;

    @Override
    public int color(int value1, int value2, int value3) {
        return color(getElement(), value1, value2, value3);
    }

    native private static int color(Element element, int value1, int value2,
            int value3) /*-{
                        return element.processingjs.color(value1, value2, value3);
                        }-*/;

    @Override
    public Object color(int value1, int value2, int value3, int alpha) {
        return color(getElement(), value1, value2, value3, alpha);
    }

    native private static Object color(Element element, int value1, int value2,
            int value3, int alpha) /*-{
                                   return element.processingjs.color(value1, value2, value3, alpha);
                                   }-*/;

    @Override
    public Object color(int gray) {
        return color(getElement(), gray);
    }

    native private static Object color(Element element, int gray) /*-{
                                                                  return element.processingjs.color(gray);
                                                                  }-*/;

    @Override
    public Object color(int gray, int alpha) {
        return color(getElement(), gray, alpha);
    }

    native private static Object color(Element element, int gray, int alpha) /*-{
                                                                             return element.processingjs.color(gray, alpha);
                                                                             }-*/;

    @Override
    public Object color(String hex, int alpha) {
        return color(getElement(), hex, alpha);
    }

    native private static Object color(Element element, String hex, int alpha) /*-{
                                                                               return element.processingjs.color(hex, alpha);
                                                                               }-*/;

    @Override
    public Object color(String hex) {
        return color(getElement(), hex);
    }

    native private static Object color(Element element, String hex) /*-{
                                                                    return element.processingjs.color(hex);
                                                                    }-*/;

    @Override
    public String[] loadStrings(String url) {
        return loadStrings(getElement(), url);
    }

    native private static String[] loadStrings(Element element, String url) /*-{
                                                                            return element.processingjs.loadStrings(url);
                                                                            }-*/;

    @Override
    public int getFrameCount() {
        return getFrameCount(getElement());
    }

    native private static int getFrameCount(Element element) /*-{
                                                             return element.processingjs.frameCount;
                                                             }-*/;

    @Override
    public int getHeight() {
        return getHeight(getElement());
    }

    native private static int getHeight(Element element) /*-{
                                                         return element.processingjs.height;
                                                         }-*/;

    @Override
    public int getKey() {
        return getKey(getElement());
    }

    native private static int getKey(Element element) /*-{
                                                      return element.processingjs.key;
                                                      }-*/;

    @Override
    public int getKeyCode() {
        return getKeyCode(getElement());
    }

    native private static int getKeyCode(Element element) /*-{
                                                          return element.processingjs.keyCode;
                                                          }-*/;

    @Override
    public int getMouseButton() {
        return getMouseButton(getElement());
    }

    native private static int getMouseButton(Element element) /*-{
                                                              return element.processingjs.mouseButton;
                                                              }-*/;

    @Override
    public int getMouseX() {
        return getMouseX(getElement());
    }

    native private static int getMouseX(Element element) /*-{
                                                         return element.processingjs.mouseX;
                                                         }-*/;

    @Override
    public int getMouseY() {
        return getMouseY(getElement());
    }

    native private static int getMouseY(Element element) /*-{
                                                         return element.processingjs.mouseY;
                                                         }-*/;

    @Override
    public int getPMouseX() {
        return getPMouseX(getElement());
    }

    native private static int getPMouseX(Element element) /*-{
                                                          return element.processingjs.pmouseX;
                                                          }-*/;

    @Override
    public int getPMouseY() {
        return getPMouseY(getElement());
    }

    native private static int getPMouseY(Element element) /*-{
                                                          return element.processingjs.pmouseY;
                                                          }-*/;

    @Override
    public int getWidth() {
        return getWidth(getElement());
    }

    native private static int getWidth(Element element) /*-{
                                                        return element.processingjs.width;
                                                        }-*/;

    @Override
    public boolean isKeyPressed() {
        return isKeyPressed(getElement());
    }

    native private static boolean isKeyPressed(Element element) /*-{
                                                                return element.processingjs.keyPressed;
                                                                }-*/;

    @Override
    public boolean isMousePressed() {
        return isMousePressed(getElement());
    }

    native private static boolean isMousePressed(Element element) /*-{
                                                                  return element.processingjs.mousePressedVar;
                                                                  }-*/;

    @Override
    public void setFrameCount(int frameCount) {
        setFrameCount(getElement(), frameCount);
    }

    native private static void setFrameCount(Element element, int fc) /*-{
                                                                      element.processingjs.frameCount = fc;
                                                                      }-*/;

    @Override
    public float map(float value, float istart, float istop, float ostart,
            float ostop) {
        return map(getElement(), value, istart, istop, ostart, ostop);
    }

    native private static float map(Element element, float value, float istart,
            float istop, float ostart, float ostop) /*-{
                                                    return element.processingjs.map(value, istart, istop, ostart, ostop);
                                                    }-*/;
}
