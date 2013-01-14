package org.vaadin.tltv.vprocjs.gwt.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class ProcessingJavascriptObject extends JavaScriptObject {

    protected ProcessingJavascriptObject() {
    }

    public final native void ellipse(float x, float y, float width, float height) /*-{
                                                                                  this.ellipse(x, y, width, height);
                                                                                  }-*/;

    public final native void ellipse(int x, int y, float width, float height) /*-{
                                                                               this.ellipse(x, y, width, height);
                                                                               }-*/;

    public final native void rect(int x, int y, int width, int height) /*-{
                                                                        this.rect(x, y, width, height);
                                                                        }-*/;

    public final native void rect(float x, float y, float width, float height) /*-{
                                                                                this.rect(x, y, width, height);
                                                                                }-*/;

    public final native void quad(int x1, int y1, int x2, int y2, int x3,
            int y3, int x4, int y4) /*-{
                                    this.quad(x1, y1, x2, y2, x3, y3, x4, y4);
                                    }-*/;

    public final native void triangle(int x1, int y1, int x2, int y2, int x3,
            int y3) /*-{
                    this.triangle(x1, y1, x2, y2, x3, y3);
                    }-*/;

    public final native void bezier(int x1, int y1, int x2, int y2, int x3,
            int y3, int x4, int y4) /*-{
                                    this.bezier(x1, y1, x2, y2, x3, y3, x4, y4);
                                    }-*/;

    public final native void line(int x1, int y1, int x2, int y2) /*-{
                                                                   this.line(x1, y1, x2, y2);
                                                                   }-*/;

    public final native void line(float x1, float y1, float x2, float y2) /*-{
                                                                           this.line(x1, y1, x2, y2);
                                                                           }-*/;

    public final native void arc(int x, int y, int width, int height,
            int start, int stop) /*-{
                                 this.arc(x, y, width, height, start, stop);
                                 }-*/;

    public final native void set(int x, int y, Object obj) /*-{
                                                                             this.set(x, y, obj);
                                                                             }-*/;

    public final native Object get(int x, int y) /*-{
                                                                   return this.get(x, y);
                                                                   }-*/;

    public final native void point(int x, int y) /*-{
                                                                   this.point(x, y);
                                                                   }-*/;

    public final native void strokeWeight(int w) /*-{
                                                                   this.strokeWeight(w);
                                                                   }-*/;

    public final native void strokeWeight(float w) /*-{
                                                                     this.strokeWeight(w);
                                                                     }-*/;

    public final native void stroke() /*-{
                                                      this.stroke();
                                                      }-*/;

    public final native void stroke(int c) /*-{
                                                             this.stroke(c);
                                                             }-*/;

    public final native void stroke(int r, int g, int b) /*-{
                                                                           this.stroke(r, g, b);
                                                                           }-*/;

    public final native void stroke(float r, float g, float b) /*-{
                                                                                 this.stroke(r, g, b);
                                                                                 }-*/;

    public final native void fill() /*-{
                                                    this.fill();
                                                    }-*/;

    public final native void fill(int r, int g, int b) /*-{
                                                                         this.fill(r, g, b);
                                                                         }-*/;

    public final native float abs(float number) /*-{
                                                                  return this.abs(number);
                                                                  }-*/;

    public final native float atan2(float number, float number2) /*-{
                                                                  return this.atan2(number, number2);
                                                                  }-*/;

    public final native float constrain(float min, float max) /*-{
                                                                                return this.constrain(min, max);
                                                                                }-*/;

    public final native float cos(float number) /*-{
                                                                  return this.cos(number);
                                                                  }-*/;

    public final native float degrees(float angle) /*-{
                                                                     return this.degrees(angle);
                                                                     }-*/;

    public final native void noFill() /*-{
                                                      this.noFill();
                                                      }-*/;

    public final native void noLoop() /*-{
                                                      this.noLoop();
                                                      }-*/;

    public final native void noSmooth() /*-{
                                                        this.noSmooth();
                                                        }-*/;

    public final native float noStroke() /*-{
                                                         this.noStroke();
                                                         }-*/;

    public final native float pow(float number, float exponent) /*-{
                                                                 return this.pow(number, exponent);
                                                                 }-*/;

    public final native float radians(float angle) /*-{
                                                                     return this.radians(angle);
                                                                     }-*/;

    public final native float sin(float number) /*-{
                                                                  return this.sin(number);
                                                                  }-*/;

    public final native void size(int width, int height) /*-{
                                                                           this.size(width, height);
                                                                           }-*/;

    public final native void smooth() /*-{
                                                      this.smooth();
                                                      }-*/;

    public final native float sqrt(float number) /*-{
                                                                   return this.sqrt(number);
                                                                   }-*/;

    public final native float noise(int x, int y, int z) /*-{
                                                                           return this.noise(x, y, z);
                                                                           }-*/;

    public final native float random(float min, float max) /*-{
                                                                             return this.random(min, max);
                                                                             }-*/;

    public final native float random(float min) /*-{
                                                                  return this.random(min);
                                                                  }-*/;

    public final native void background() /*-{
                                                          this.background();
                                                          }-*/;

    public final native void background(String imgPath) /*-{
                                                                          this.background(this.loadImage(imgPath));
                                                                          }-*/;

    public final native void background(int gray) /*-{
                                                                    this.background(gray);
                                                                    }-*/;

    public final native void background(int r, int g, int b) /*-{
                                                                               this.background(r, g, b);
                                                                               }-*/;

    public final native void beginShape(int type) /*-{
                                                                    this.beginShape(type);
                                                                    }-*/;

    public final native float bytee(float number) /*-{
                                                                    return this.bytee(number);
                                                                    }-*/;

    public final native float ceil(float number) /*-{
                                                                   return this.ceil(number);
                                                                   }-*/;

    public final native float clear() /*-{
                                                      this.clear();
                                                      }-*/;

    public final native void clear(int x, int y, int width, int height) /*-{
                                                                         this.clear(x, y, width, height);
                                                                         }-*/;

    public final native void colorMode(int mode, int range1) /*-{
                                                                               this.colorMode(mode, range1);
                                                                               }-*/;

    public final native void colorMode(int mode, int range1, int range2,
            int range3) /*-{
                        this.colorMode(mode, range1, range2, range3);
                        }-*/;

    public final native void colorMode(int mode, int range1, int range2,
            int range3, int range4) /*-{
                                    this.colorMode(mode, range1, range2, range3, range4);
                                    }-*/;

    public final native void curveTightness(int tightness) /*-{
                                                                             this.curveTightness(tightness);
                                                                             }-*/;

    public final native void curveVertex(int x, int y, int x2, int y2) /*-{
                                                                        this.curveVertex(x, y, x2, y2);
                                                                        }-*/;

    public final native int day() /*-{
                                                  return this.day();
                                                  }-*/;

    public final native float dist(int x1, int y1, int x2, int y2) /*-{
                                                                    return this.dist(x1, y1, x2, y2);
                                                                    }-*/;

    public final native void ellipseMode(int ellipseMode) /*-{
                                                                            this.ellipseMode(ellipseMode);
                                                                            }-*/;

    public final native void endShape(boolean close) /*-{
                                                                       this.endShape(close);
                                                                       }-*/;

    public final native void endShape() /*-{
                                                        this.endShape(true);
                                                        }-*/;

    public final native float floatt(float number) /*-{
                                                                     return this.floatt(number);
                                                                     }-*/;

    public final native float floor(float number) /*-{
                                                                    return this.floor(number);
                                                                    }-*/;

    public final native void frameRate(int rate) /*-{
                                                                   this.frameRate(rate);
                                                                   }-*/;

    public final native int hour() /*-{
                                                   return this.hour();
                                                   }-*/;

    public final native float inte(float number) /*-{
                                                                   return this.inte(rate);
                                                                   }-*/;

    public final native float lerp(float value1, float value2, float amt) /*-{
                                                                           return this.lerp(value1, value2, amt);
                                                                           }-*/;

    public final native void loop() /*-{
                                                    this.loop();
                                                    }-*/;

    public final native float max(float number, float number2) /*-{
                                                                                 return this.max(number, number2);
                                                                                 }-*/;

    public final native int millis() /*-{
                                                     return this.millis();
                                                     }-*/;

    public final native float min(float number, float number2) /*-{
                                                                                 return this.min(number, number2);
                                                                                 }-*/;

    public final native int minute() /*-{
                                                     return this.minute();
                                                     }-*/;

    public final native int month() /*-{
                                                    return this.month();
                                                    }-*/;

    public final native float norm(float number, float low, float high) /*-{
                                                                         return this.norm(number, low, high);
                                                                         }-*/;

    public final native void popMatrix() /*-{
                                                         this.popMatrix();
                                                         }-*/;

    public final native void pushMatrix() /*-{
                                                          this.pushMatrix();
                                                          }-*/;

    public final native void rectMode(int rectMode) /*-{
                                                                      this.rectMode(rectMode);
                                                                      }-*/;

    public final native void redraw() /*-{
                                                      this.redraw();
                                                      }-*/;

    public final native void rotate(float angle) /*-{
                                                                   this.rotate(angle);
                                                                   }-*/;

    public final native float round(float number) /*-{
                                                                    return this.round(number);
                                                                    }-*/;

    public final native void scale(int x, int y) /*-{
                                                                   this.scale(x, y);
                                                                   }-*/;

    public final native void scale(float x, float y) /*-{
                                                                       this.scale(x, y);
                                                                       }-*/;

    public final native int second() /*-{
                                                     return this.second();
                                                     }-*/;

    public final native float sq(float number) /*-{
                                                                 return this.sq(number);
                                                                 }-*/;

    public final native String str(float number) /*-{
                                                                   return this.str(number);
                                                                   }-*/;

    public final native void translate(int x, int y) /*-{
                                                                       this.translate(x, y);
                                                                       }-*/;

    public final native void translate(float x, float y) /*-{
                                                                           this.translate(x, y);
                                                                           }-*/;

    public final native void vertex(float x, float y, float x2, float y2,
            float x3, float y3) /*-{
                                this.vertex(x, y, x2, y2, x3, y3);
                                }-*/;

    public final native void vertex(int x, int y, int x2, int y2, int x3, int y3) /*-{
                                                                                  this.vertex(x, y, x2, y2, x3, y3);
                                                                                  }-*/;

    public final native void vertex(int x, int y) /*-{
                                                                    this.vertex(x, y);
                                                                    }-*/;

    public final native void vertex(float x, float y) /*-{
                                                                        this.vertex(x, y);
                                                                        }-*/;

    public final native void vertex(int x, int y, int x2, int y2) /*-{
                                                                   this.vertex(x, y, x2, y2);
                                                                   }-*/;

    public final native void vertex(float x, float y, float x2, float y2) /*-{
                                                                           this.vertex(x, y, x2, y2);
                                                                           }-*/;

    public final native int year() /*-{
                                                   return this.year();
                                                   }-*/;

    public final native void text(String str, int x, int y) /*-{
                                                                              this.text(str, x, y);
                                                                              }-*/;

    public final native void textSize(int size) /*-{
                                                                  this.textSize(str, size);
                                                                  }-*/;

    public final native void textFont(Object name, int size) /*-{
                                                                               this.textFont(name, size);
                                                                               }-*/;

    public final native int loadFont(String name) /*-{
                                                                    return this.loadFont(name);
                                                                    }-*/;

    public final native Object loadImage(String name) /*-{
                                                                        return this.loadImage(name);
                                                                        }-*/;

    public final native void exit() /*-{
                                                    this.exit();
                                                    }-*/;

    public final native void image(Object img, int x, int y) /*-{
                                                                               this.image(img, x, y);
                                                                               }-*/;

    public final native void image(Object img, int x, int y, int w, int h) /*-{
                                                                            this.image(img, x, y, w, h);
                                                                            }-*/;

    public final native void tint(int rgb, int a) /*-{
                                                                    this.tint(rgb, a);
                                                                    }-*/;

    public final native Object createImage(int w, int h, int mode) /*-{
                                                                    return this.createImage(w, h, mode);
                                                                    }-*/;

    public final native String nf(int num, int pad) /*-{
                                                                      return this.nf(num, pad);
                                                                      }-*/;

    public final native Object lerpColor(Object c1, Object c2, float amt) /*-{
                                                                           return this.lerpColor(c1, c2, amt);
                                                                           }-*/;

    public final native float alpha(Object aColor) /*-{
                                                                     return this.alpha(aColor);
                                                                     }-*/;

    public final native float blue(Object aColor) /*-{
                                                                    return this.blue(aColor);
                                                                    }-*/;

    public final native float green(Object aColor) /*-{
                                                                     return this.green(aColor);
                                                                     }-*/;

    public final native float red(Object aColor) /*-{
                                                                   return this.red(aColor);
                                                                   }-*/;

    public final native int color(int value1, int value2, int value3) /*-{
                                                                       return this.color(value1, value2, value3);
                                                                       }-*/;

    public final native Object color(int value1, int value2, int value3,
            int alpha) /*-{
                       return this.color(value1, value2, value3, alpha);
                       }-*/;

    public final native Object color(int gray) /*-{
                                                                 return this.color(gray);
                                                                 }-*/;

    public final native Object color(int gray, int alpha) /*-{
                                                                            return this.color(gray, alpha);
                                                                            }-*/;

    public final native Object color(String hex, int alpha) /*-{
                                                                              return this.color(hex, alpha);
                                                                              }-*/;

    public final native Object color(String hex) /*-{
                                                                   return this.color(hex);
                                                                   }-*/;

    public final native String[] loadStrings(String url) /*-{
                                                                           return this.loadStrings(url);
                                                                           }-*/;

    public final native int getFrameCount() /*-{
                                                            return this.frameCount;
                                                            }-*/;

    public final native int getHeight() /*-{
                                                        return this.height;
                                                        }-*/;

    public final native int getKey() /*-{
                                                     return this.key;
                                                     }-*/;

    public final native int getKeyCode() /*-{
                                                         return this.keyCode;
                                                         }-*/;

    public final native int getMouseButton() /*-{
                                                             return this.mouseButton;
                                                             }-*/;

    public final native int getMouseX() /*-{
                                                        return this.mouseX;
                                                        }-*/;

    public final native int getMouseY() /*-{
                                                        return this.mouseY;
                                                        }-*/;

    public final native int getPMouseX() /*-{
                                                         return this.pmouseX;
                                                         }-*/;

    public final native int getPMouseY() /*-{
                                                         return this.pmouseY;
                                                         }-*/;

    public final native int getWidth() /*-{
                                                       return this.width;
                                                       }-*/;

    public final native boolean isKeyPressed() /*-{
                                                               return this.keyPressed;
                                                               }-*/;

    public final native boolean isMousePressed() /*-{
                                                                 return this.mousePressedVar;
                                                                 }-*/;

    public final native void setFrameCount(int fc) /*-{
                                                                     this.frameCount = fc;
                                                                     }-*/;

    public final native float map(float value, float istart, float istop,
            float ostart, float ostop) /*-{
                                       return this.map(value, istart, istop, ostart, ostop);
                                       }-*/;

}
