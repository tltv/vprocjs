package org.vaadin.tltv.vprocjs.gwt.client.ui.test;

import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingCodeBase;

public class ProcessingJavaCode2 extends ProcessingCodeBase {

    private int radius = 75;

    @Override
    public void setup() {
        Integer r = (Integer) getVariable("radius");
        if (r == null) {
            radius = 75;
        } else {
            radius = r;
        }

        pro.size(200, 200);
        pro.background(200);
        pro.frameRate(10);
        pro.strokeWeight(1);
        pro.stroke(0, 0, 0);
        pro.noFill();
        pro.ellipse(75, 75, 75, radius);
        pro.loop();
    }

    @Override
    public void draw() {
        pro.background(200);
        pro.ellipse(75, 75, 75, radius);
        radius--;
        if (radius < 25) {
            radius = 25;
            pro.noLoop();
        }
        setVariable("radius", radius);
    }
}