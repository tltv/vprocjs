package org.vaadin.tltv.vprocjs.gwt.client.ui;

/**
 * Interface containing all supported processing.js methods.
 */
public interface ProcessingJs {

    void setup();

    void draw();

    void mouseMoved();

    void mouseDragged();

    void mousePressed();

    void mouseClicked();

    void mouseReleased();

    void keyPressed();

    void keyReleased();

    void keyTyped();

    void ellipse(int x, int y, int width, int height);

    void rect(int x, int y, int width, int height);

    void quad(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4);

    void triangle(int x1, int y1, int x2, int y2, int x3, int y3);

    void bezier(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4);

    void line(int x1, int y1, int x2, int y2);

    void arc(int x, int y, int width, int height, int start, int stop);

    void set(int x, int y, Object obj);

    Object get(int x, int y);

    void point(int x, int y);

    void strokeWeight(int w);

    void strokeWeight(float w);

    void stroke();

    void stroke(int c);

    void stroke(int r, int g, int b);

    void stroke(float r, float g, float b);

    void fill();

    void noLoop();

    void noSmooth();

    void smooth();

    void noFill();

    void noStroke();

    void size(int aWidth, int aHeight);

    float degrees(float aAngle);

    float radians(float aAngle);

    float atan2(float aNumber, float aNumber2);

    float sqrt(float aNumber);

    float constrain(float aNumber, float aMin, float aMax);

    float pow(float aNumber, float aExponent);

    float sin(float aNumber);

    float cos(float aNumber);

    float abs(float aNumber);

    float noise(int x, int y, int z);

    float random(float aMin, float aMax);

    float random(float aMin);

    float bytee(float aNumber);

    float floatt(float aNumber);

    float floor(float aNumber);

    float lerp(float value1, float value2, float amt);

    float norm(float aNumber, float low, float high);

    float round(float aNumber);

    float ceil(float aNumber);

    float max(float aNumber, float aNumber2);

    float min(float aNumber, float aNumber2);

    float inte(float aNumber);

    float sq(float aNumber);

    String str(float aNumber);

    void clear();

    void clear(int x, int y, int width, int height);

    void background();

    void background(String imgPath);

    void background(int gray);

    void background(int r, int g, int b);

    void frameRate(int aRate);

    void loop();

    void redraw();

    void popMatrix();

    void pushMatrix();

    void rotate(float aAngle);

    void scale(int x, int y);

    void scale(float x, float y);

    void translate(int x, int y);

    void translate(float x, float y);

    int millis();

    int second();

    int minute();

    int hour();

    int day();

    int month();

    int year();

    float dist(int x1, int y1, int x2, int y2);

    void ellipseMode(int aEllipseMode);

    void rectMode(int aRectMode);

    void curveTightness(int tightness);

    void curveVertex(int x, int y, int x2, int y2);

    void vertex(int x, int y, int x2, int y2, int x3, int y3);

    void endShape(boolean close);

    void beginShape(int type);

    void colorMode(int mode, int range1);

    void colorMode(int mode, int range1, int range2, int range3);

    void colorMode(int mode, int range1, int range2, int range3, int range4);

    void text(String str, int x, int y);

    void textSize(int size);

    void textFont(Object name, int size);

    int loadFont(String name);

    Object loadImage(String name);

    void exit();

    void image(Object img, int x, int y);

    void image(Object img, int x, int y, int w, int h);

    void tint(int rgb, int a);

    Object createImage(int w, int h, int mode);

    String nf(int num, int pad);

    Object lerpColor(Object c1, Object c2, float amt);

    float alpha(Object aColor);

    float blue(Object aColor);

    float green(Object aColor);

    float red(Object aColor);

    int color(int aValue1, int aValue2, int aValue3);

    Object color(int aValue1, int aValue2, int aValue3, int alpha);

    Object color(int gray);

    Object color(int gray, int alpha);

    Object color(String hex, int alpha);

    Object color(String hex);

    String[] loadStrings(String url);

    int getFrameCount();

    void setFrameCount(int frameCount);

    int getHeight();

    int getWidth();

    int getMouseButton();

    boolean isMousePressed();

    int getMouseX();

    int getMouseY();

    int getPMouseX();

    int getPMouseY();

    int getKey();

    int getKeyCode();

    boolean isKeyPressed();

    void vertex(int x, int y);

    void vertex(float x, float y);

    void vertex(int x, int y, int x2, int y2);

    void vertex(float x, float y, float x2, float y2);

    void vertex(float x, float y, float x2, float y2, float x3, float y3);

    float map(float value, float istart, float istop, float ostart, float ostop);
}
