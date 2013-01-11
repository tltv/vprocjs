package org.vaadin.tltv.vprocjs.gwt.client.ui.test;

import org.vaadin.tltv.vprocjs.gwt.client.ui.ProcessingCodeBase;


public class ProcessingCodeImplTest extends ProcessingCodeBase {

    float radius = 50.0f;
    int X, Y;
    int nX, nY;
    int delay = 16;

    @Override
    public void setup() {
        pro.size(200, 200);
        pro.strokeWeight(10);
        pro.frameRate(15);
        X = pro.getWidth() / 2;
        Y = pro.getWidth() / 2;
        nX = X;
        nY = Y;
    }

    @Override
    public void draw() {
        radius = radius + pro.sin(pro.getFrameCount() / 4);
        X += (nX - X) / delay;
        Y += (nY - Y) / delay;
        pro.background(100);
        pro.fill(0, 121, 184);
        pro.stroke(255);
        pro.ellipse(X, Y, radius, radius);
    }

    @Override
    public void mouseMoved() {
        nX = pro.getMouseX();
        nY = pro.getMouseY();
    }
}
