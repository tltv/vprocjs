package org.vaadin.tltv.vprocjs.test.server;

import org.vaadin.tltv.vprocjs.component.Processing;
import org.vaadin.tltv.vprocjs.component.Processing.ClickEvent;
import org.vaadin.tltv.vprocjs.component.Processing.KeyPressEvent;
import org.vaadin.tltv.vprocjs.component.Processing.KeyPressListener;
import org.vaadin.tltv.vprocjs.component.Processing.MouseClickListener;
import org.vaadin.tltv.vprocjs.component.Processing.MouseEnterEvent;
import org.vaadin.tltv.vprocjs.component.Processing.MouseEnterListener;
import org.vaadin.tltv.vprocjs.component.Processing.MouseLeaveEvent;
import org.vaadin.tltv.vprocjs.component.Processing.MouseLeaveListener;
import org.vaadin.tltv.vprocjs.component.Processing.MousePressEvent;
import org.vaadin.tltv.vprocjs.component.Processing.MousePressListener;
import org.vaadin.tltv.vprocjs.component.Processing.MouseReleaseEvent;
import org.vaadin.tltv.vprocjs.component.Processing.MouseReleaseListener;
import org.vaadin.tltv.vprocjs.component.Processing.MouseWheelEvent;
import org.vaadin.tltv.vprocjs.component.Processing.MouseWheelListener;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * UI for showing how the Processing component works with the plain Processing
 * language. </br>View contains a single TextBox where user may write some
 * Processing language code. Code changes are reflected immediately in a main
 * sub-window or multiple secondary sub-windows.</br> Demonstrates also some
 * basic event handling within the sub-windows.</br></br> This test doesn't
 * require any client-side coding.
 * 
 * @author Tltv
 * 
 */
public class ProcessingCodeTest extends VerticalLayout implements
        ValueChangeListener, MouseClickListener, MousePressListener,
        MouseReleaseListener, KeyPressListener, MouseEnterListener,
        MouseLeaveListener, MouseWheelListener {

    private final TextArea text;

    private Processing pro;

    private final Panel panel;

    private Window popup;

    private final Button openInNewPopup;
    private final Button refreshMainPopup;

    private final static String originalCode = "float[] x = new float[20];\n"
            + "float[] y = new float[20];\n" + "float segLength = 10;\n\n"
            + "void setup() {\n  size(320, 240);\n" + "  smooth();\n" + "}\n\n"
            + "void draw() {\n" + "  background(226);\n"
            + "  dragSegment(0, mouseX - 8, mouseY - 8);\n"
            + "  for(int i=0; i < x.length-1; i++) {\n"
            + "    dragSegment(i+1, x[i], y[i]);\n" + "  }\n}\n\n"
            + "void dragSegment(int i, float xin, float yin) {\n"
            + "  float dx = xin - x[i];\n" + "  float dy = yin - y[i];\n"
            + "  float angle = atan2(dy, dx);\n"
            + "  x[i] = xin - cos(angle) * segLength;\n"
            + "  y[i] = yin - sin(angle) * segLength;\n" + "  pushMatrix();\n"
            + "  translate(x[i], y[i]);\n" + "  rotate(angle);\n"
            + "  color c;\n" + "  if ( i % 3 == 1 )\n"
            + "    c = color(0, 0, 0, 255);\n" + "  else if ( i % 3 == 2 )\n"
            + "    c = color(255, 255, 0, 255);\n" + "  else\n"
            + "    c = color(255, 0, 0, 255);\n\n" + "  stroke( c );"
            + "  strokeWeight(10);\n" + "  line(0, 0, segLength, 0);\n"
            + "  if ( i == x.length - 1 )" + " {\n"
            + "    fill( c );\n    noStroke();\n"
            + "    beginShape(TRIANGLES);\n" + "    vertex(0, 5);\n"
            + "    vertex(-2 * segLength, 0);\n"
            + "    vertex(0, -5);\n    endShape();\n  }\n"
            + "  if ( i == 0 ) {\n" + "    noStroke();\n    fill(0, 255);\n"
            + "    ellipse(segLength, -2, 3, 3);\n"
            + "    ellipse(segLength, 2, 3, 3);\n  }\n" + "  popMatrix();\n}";

    public ProcessingCodeTest() {
        panel = new Panel("Vaadin component using Processing.js");
        panel.setContent(new GridLayout(2, 2));

        pro = newProcessingWidget(originalCode);

        text = new TextArea();
        text.setColumns(40);
        text.setRows(40);
        text.setValue(originalCode);
        text.addValueChangeListener(this);
        text.setImmediate(true);

        openInNewPopup = new Button("Run in a new subwindow",
                new ClickListener() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(
                            com.vaadin.ui.Button.ClickEvent event) {
                        String code = text.getValue();
                        openpopup(null, newProcessingWidget(code));
                    }
                });

        refreshMainPopup = new Button("Refresh main subwindow",
                new ClickListener() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(
                            com.vaadin.ui.Button.ClickEvent event) {
                        String code = originalCode;
                        if (text.getValue() != null
                                && !"".equals(text.getValue().toString().trim())) {
                            code = text.getValue().toString();
                        }
                        refreshMainSubWindow(code);
                    }
                });
        constructPanel();
        popup = openpopup(popup, pro);

        addComponent(panel);
    }

    private void constructPanel() {
        ((GridLayout) panel.getContent()).removeAllComponents();
        VerticalLayout buttons = new VerticalLayout();
        buttons.addComponent(refreshMainPopup);
        buttons.addComponent(openInNewPopup);

        ((GridLayout) panel.getContent()).addComponent(text, 0, 0, 0, 1);
        ((GridLayout) panel.getContent()).addComponent(buttons, 1, 0);
        panel.markAsDirty();
    }

    private Window openpopup(Window target, Processing newPro) {
        if (target == null) {
            target = new Window("");
            target.setWidth("325");
            target.setHeight("400");
            target.center();
            VerticalLayout vl = new VerticalLayout();
            vl.setMargin(false);
            vl.setSpacing(false);
            target.setContent(vl);
        }
        ((VerticalLayout) target.getContent()).removeAllComponents();
        ((VerticalLayout) target.getContent()).addComponent(newPro);

        ProcessingTestUI.getTestRoot().removeWindow(target);
        ProcessingTestUI.getTestRoot().addWindow(target);

        target.markAsDirty();
        return target;
    }

    private Processing newProcessingWidget(String code) {
        Processing p = new Processing();
        p.setProcessingCode(code);
        p.addListener((MouseClickListener) this);
        p.addListener((MousePressListener) this);
        p.addListener((KeyPressListener) this);
        p.addListener((MouseEnterListener) this);
        p.addListener((MouseLeaveListener) this);
        p.addListener((MouseWheelListener) this);
        return p;
    }

    private void refreshMainSubWindow(String code) {
        pro = newProcessingWidget(code);
        popup = openpopup(popup, pro);
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        String code = originalCode;
        if (event.getProperty().getValue() != null
                && !"".equals(event.getProperty().getValue().toString().trim())) {
            code = event.getProperty().getValue().toString();
        }
        refreshMainSubWindow(code);
    }

    @Override
    public void mouseClick(ClickEvent event) {

    }

    public Window getParentWindowFor(Component component) {
        while (component != null && !(component instanceof Window)) {
            component = component.getParent();
        }
        return (Window) component;
    }

    @Override
    public void mousePress(MousePressEvent event) {
        Processing p = event.getSource();
        Window w = getParentWindowFor(p);
        String newCaption = w.getCaption();
        if (newCaption.indexOf("(") != -1) {
            newCaption = newCaption.replaceAll("(?<=\\()\\d*\\,\\s\\d*(?=\\))",
                    "" + event.getX() + ", " + event.getY());
        } else {
            newCaption += " (" + event.getX() + ", " + event.getY() + ")";
        }
        w.setCaption(newCaption);
    }

    @Override
    public void mouseRelease(MouseReleaseEvent event) {

    }

    @Override
    public void keyPress(KeyPressEvent event) {
        Processing p = event.getSource();
        Window w = getParentWindowFor(p);
        String newCaption = w.getCaption();
        if (newCaption.indexOf("[") != -1) {
            newCaption = newCaption.replaceAll("(?<=\\[)\\-?\\d*(?=\\])", ""
                    + event.getKey());
        } else {
            newCaption += " [" + event.getKey() + "]";
        }
        w.setCaption(newCaption);
    }

    @Override
    public void mouseEnter(MouseEnterEvent event) {
        Processing p = event.getSource();
        Window w = getParentWindowFor(p);
        String newCaption = w.getCaption();
        if (newCaption.indexOf("*") == -1) {
            newCaption = "* " + newCaption;
        }
        w.setCaption(newCaption);
    }

    @Override
    public void mouseLeave(MouseLeaveEvent event) {
        Processing p = event.getSource();
        Window w = getParentWindowFor(p);
        String newCaption = w.getCaption();
        newCaption = newCaption.replaceAll("\\*\\s", "");
        w.setCaption(newCaption);
    }

    @Override
    public void mouseWheel(MouseWheelEvent event) {
        Processing p = event.getSource();
        Window w = getParentWindowFor(p);
        String newCaption = w.getCaption();
        if (newCaption.indexOf("{") != -1) {
            newCaption = newCaption.replaceAll("(?<=\\{)\\-?\\d*(?=\\})", ""
                    + event.getDeltaY());
        } else {
            newCaption += " {" + event.getDeltaY() + "}";
        }
        w.setCaption(newCaption);
    }
}
