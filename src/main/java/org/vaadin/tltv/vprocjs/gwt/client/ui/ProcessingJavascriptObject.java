package org.vaadin.tltv.vprocjs.gwt.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class ProcessingJavascriptObject extends JavaScriptObject {

    protected ProcessingJavascriptObject() {
    }

    public final native void ellipse(double x, double y, double width,
            double height) /*-{
                           this.ellipse(x, y, width, height);
                           }-*/;

    public final native void ellipse(int x, int y, double width, double height) /*-{
                                                                                this.ellipse(x, y, width, height);
                                                                                }-*/;

    public final native void rect(int x, int y, int width, int height) /*-{
                                                                       this.rect(x, y, width, height);
                                                                       }-*/;

    public final native void rect(double x, double y, double width,
            double height) /*-{
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

    public final native void bezierDetail(int detail) /*-{
                                                      this.bezierDetail(detail);
                                                      }-*/;

    public final native void bezierPoint(int a, int b, int c, int d, double t) /*-{
                                                                               this.bezierPoint(a, b, c, d, t);
                                                                               }-*/;

    public final native void bezierPoint(double a, double b, double c,
            double d, double t) /*-{
                                this.bezierPoint(a, b, c, d, t);
                                }-*/;

    public final native void bezierTangent(int a, int b, int c, int d, double t) /*-{
                                                                                 this.bezierTangent(a, b, c, d, t);
                                                                                 }-*/;

    public final native void bezierTangent(double a, double b, double c,
            double d, double t) /*-{
                                this.bezierTangent(a, b, c, d, t);
                                }-*/;

    public final native void curve(int x1, int y1, int x2, int y2, int x3,
            int y3, int x4, int y4) /*-{
                                    this.curve(x1, y1, x2, y2, x3, y3, x4, y4);
                                    }-*/;

    public final native void curve(double x1, double y1, double x2, double y2,
            double x3, double y3, double x4, double y4) /*-{
                                                        this.curve(x1, y1, x2, y2, x3, y3, x4, y4);
                                                        }-*/;

    public final native void curve(int x1, int y1, int z1, int x2, int y2,
            int z2, int x3, int y3, int z3, int x4, int y4, int z4) /*-{
                                                                    this.curve(x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4);
                                                                    }-*/;

    public final native void curve(double x1, double y1, double z1, double x2,
            double y2, double z2, double x3, double y3, double z3, double x4,
            double y4, double z4) /*-{
                                  this.curve(x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4);
                                  }-*/;

    public final native void curveDetail(int detail) /*-{
                                                     this.curveDetail(detail);
                                                     }-*/;

    public final native void curvePoint(int a, int b, int c, int d, double t) /*-{
                                                                              this.curvePoint(a, b, c, d, t);
                                                                              }-*/;

    public final native void curvePoint(double a, double b, double c, double d,
            double t) /*-{
                      this.curvePoint(a, b, c, d, t);
                      }-*/;

    public final native void curveTangent(double a, double b, double c,
            double d, double t) /*-{
                                this.curveTangent(a, b, c, d, t);
                                }-*/;

    public final native void curveTangent(int a, int b, int c, int d, double t) /*-{
                                                                                this.curveTangent(a, b, c, d, t);
                                                                                }-*/;

    public final native void line(int x1, int y1, int x2, int y2) /*-{
                                                                  this.line(x1, y1, x2, y2);
                                                                  }-*/;

    public final native void line(double x1, double y1, double x2, double y2) /*-{
                                                                              this.line(x1, y1, x2, y2);
                                                                              }-*/;

    public final native void arc(int x, int y, int width, int height,
            int start, int stop) /*-{
                                 this.arc(x, y, width, height, start, stop);
                                 }-*/;

    public final native void point(int x, int y) /*-{
                                                 this.point(x, y);
                                                 }-*/;

    public final native void strokeWeight(int w) /*-{
                                                 this.strokeWeight(w);
                                                 }-*/;

    public final native void strokeWeight(double w) /*-{
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

    public final native void stroke(double r, double g, double b) /*-{
                                                                  this.stroke(r, g, b);
                                                                  }-*/;

    public final native void fill() /*-{
                                    this.fill();
                                    }-*/;

    public final native void fill(int r, int g, int b) /*-{
                                                       this.fill(r, g, b);
                                                       }-*/;

    public final native double abs(double number) /*-{
                                                  return this.abs(number);
                                                  }-*/;

    public final native double atan2(double number, double number2) /*-{
                                                                    return this.atan2(number, number2);
                                                                    }-*/;

    public final native double constrain(double min, double max) /*-{
                                                                 return this.constrain(min, max);
                                                                 }-*/;

    public final native double cos(double number) /*-{
                                                  return this.cos(number);
                                                  }-*/;

    public final native double degrees(double angle) /*-{
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

    public final native double noStroke() /*-{
                                          this.noStroke();
                                          }-*/;

    public final native double pow(double number, double exponent) /*-{
                                                                   return this.pow(number, exponent);
                                                                   }-*/;

    public final native double radians(double angle) /*-{
                                                     return this.radians(angle);
                                                     }-*/;

    public final native double sin(double number) /*-{
                                                  return this.sin(number);
                                                  }-*/;

    public final native void size(int width, int height) /*-{
                                                         this.size(width, height);
                                                         }-*/;

    public final native void size(int width, int height, Object mode) /*-{
                                                                      this.size(width, height, mode);
                                                                      }-*/;

    public final native void smooth() /*-{
                                      this.smooth();
                                      }-*/;

    public final native void strokeCap(Object mode) /*-{
                                                    this.strokeCap(mode);
                                                    }-*/;

    public final native void strokeJoin(Object mode) /*-{
                                                     this.strokeJoin(mode);
                                                     }-*/;

    public final native double sqrt(double number) /*-{
                                                   return this.sqrt(number);
                                                   }-*/;

    public final native double noise(int x, int y, int z) /*-{
                                                          return this.noise(x, y, z);
                                                          }-*/;

    public final native void noiseDetail(int octaves) /*-{
                                                      this.noiseDetail(octaves);
                                                      }-*/;

    public final native void noiseDetail(int octaves, double falloff) /*-{
                                                                      this.noiseDetail(octaves, falloff);
                                                                      }-*/;

    public final native double noiseSeed(int value) /*-{
                                                    return this.noiseSeed(value);
                                                    }-*/;

    public final native double random(int low, int high) /*-{
                                                         return this.random(low, high);
                                                         }-*/;

    public final native double random(double low, double high) /*-{
                                                               return this.random(low, high);
                                                               }-*/;

    public final native double random(int min) /*-{
                                               return this.random(min);
                                               }-*/;

    public final native double random(double min) /*-{
                                                  return this.random(min);
                                                  }-*/;

    public final native double randomSeed(int value) /*-{
                                                     return this.randomSeed(value);
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

    public final native void beginShape(Object mode) /*-{
                                                     this.beginShape(mode);
                                                     }-*/;

    public final native void bezierVertex(int cx1, int cy1, int cx2, int cy2,
            int x, int y) /*-{
                          this.bezierVertex(cx1, cy1, cx2, cy2, x, y);
                          }-*/;

    public final native void bezierVertex(int cx1, int cy1, int cz1, int cx2,
            int cy2, int cz2, int x, int y, int z) /*-{
                                                   this.bezierVertex(cx1, cy1, cz1, cx2, cy2, cz2, x, y, z);
                                                   }-*/;

    public final native void bezierVertex(double cx1, double cy1, double cx2,
            double cy2, double x, double y) /*-{
                                            this.bezierVertex(cx1, cy1, cx2, cy2, x, y);
                                            }-*/;

    public final native void bezierVertex(double cx1, double cy1, double cz1,
            double cx2, double cy2, double cz2, double x, double y, double z) /*-{
                                                                              this.bezierVertex(cx1, cy1, cz1, cx2, cy2, cz2, x, y, z);
                                                                              }-*/;

    public final native double bytee(double number) /*-{
                                                    return this.bytee(number);
                                                    }-*/;

    public final native double ceil(double number) /*-{
                                                   return this.ceil(number);
                                                   }-*/;

    public final native double clear() /*-{
                                       this.clear();
                                       }-*/;

    public final native void clear(int x, int y, int width, int height) /*-{
                                                                        this.clear(x, y, width, height);
                                                                        }-*/;

    public final native void colorMode(int mode) /*-{
                                                 this.colorMode(mode);
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

    public final native void curveVertex(int x, int y) /*-{
                                                       this.curveVertex(x, y);
                                                       }-*/;

    public final native void curveVertex(double x, double y) /*-{
                                                             this.curveVertex(x, y);
                                                             }-*/;

    public final native void curveVertex(int x, int y, int z) /*-{
                                                                       this.curveVertex(x, y, z);
                                                                       }-*/;

    public final native void curveVertex(double x, double y, double z) /*-{
                                                                       this.curveVertex(x, y, z);
                                                                       }-*/;

    public final native int day() /*-{
                                  return this.day();
                                  }-*/;

    public final native double dist(int x1, int y1, int x2, int y2) /*-{
                                                                    return this.dist(x1, y1, x2, y2);
                                                                    }-*/;

    public final native void ellipseMode(int ellipseMode) /*-{
                                                          this.ellipseMode(ellipseMode);
                                                          }-*/;

    public final native void endShape(Object mode) /*-{
                                                     this.endShape(mode);
                                                     }-*/;

    public final native void endShape() /*-{
                                        this.endShape();
                                        }-*/;

    public final native void texture(Object img) /*-{
                                                 this.texture(img);
                                                 }-*/;

    public final native void textureMode(Object mode) /*-{
                                                      this.textureMode(mode);
                                                      }-*/;

    public final native double floatt(double number) /*-{
                                                     return this.floatt(number);
                                                     }-*/;

    public final native double floor(double number) /*-{
                                                    return this.floor(number);
                                                    }-*/;

    public final native void frameRate(int fps) /*-{
                                                 this.frameRate(fps);
                                                 }-*/;

    public final native int getFrameRate() /*-{
                                           return this.frameRate;
                                           }-*/;

    public final native int hour() /*-{
                                   return this.hour();
                                   }-*/;

    public final native double inte(double number) /*-{
                                                   return this.inte(rate);
                                                   }-*/;

    public final native double lerp(double value1, double value2, double amt) /*-{
                                                                              return this.lerp(value1, value2, amt);
                                                                              }-*/;

    public final native void loop() /*-{
                                    this.loop();
                                    }-*/;

    public final native double max(double number, double number2) /*-{
                                                                  return this.max(number, number2);
                                                                  }-*/;

    public final native int millis() /*-{
                                     return this.millis();
                                     }-*/;

    public final native double min(double number, double number2) /*-{
                                                                  return this.min(number, number2);
                                                                  }-*/;

    public final native int minute() /*-{
                                     return this.minute();
                                     }-*/;

    public final native int month() /*-{
                                    return this.month();
                                    }-*/;

    public final native double norm(double number, double low, double high) /*-{
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

    public final native void rotate(double angle) /*-{
                                                  this.rotate(angle);
                                                  }-*/;

    public final native void rotateX(double angle) /*-{
                                                   this.rotateX(angle);
                                                   }-*/;

    public final native void rotateY(double angle) /*-{
                                                   this.rotateY(angle);
                                                   }-*/;

    public final native void rotateZ(double angle) /*-{
                                                   this.rotateZ(angle);
                                                   }-*/;

    public final native double round(double number) /*-{
                                                    return this.round(number);
                                                    }-*/;

    public final native void scale(double size) /*-{
                                                this.scale(size);
                                                }-*/;

    public final native void scale(double x, double y) /*-{
                                                       this.scale(x, y);
                                                       }-*/;

    public final native void scale(double x, double y, double z) /*-{
                                                                 this.scale(x, y, z);
                                                                 }-*/;

    public final native int second() /*-{
                                     return this.second();
                                     }-*/;

    public final native double sq(double number) /*-{
                                                 return this.sq(number);
                                                 }-*/;

    public final native String str(double number) /*-{
                                                  return this.str(number);
                                                  }-*/;

    public final native void translate(int x, int y) /*-{
                                                     this.translate(x, y);
                                                     }-*/;

    public final native void translate(double x, double y) /*-{
                                                           this.translate(x, y);
                                                           }-*/;

    public final native void translate(int x, int y, int z) /*-{
                                                            this.translate(x, y, z);
                                                            }-*/;

    public final native void translate(double x, double y, double z) /*-{
                                                                     this.translate(x, y, z);
                                                                     }-*/;

    public final native void vertex(double x, double y, double x2, double y2,
            double x3, double y3) /*-{
                                  this.vertex(x, y, x2, y2, x3, y3);
                                  }-*/;

    public final native void vertex(int x, int y, int x2, int y2, int x3, int y3) /*-{
                                                                                  this.vertex(x, y, x2, y2, x3, y3);
                                                                                  }-*/;

    public final native void vertex(int x, int y) /*-{
                                                  this.vertex(x, y);
                                                  }-*/;

    public final native void vertex(double x, double y) /*-{
                                                        this.vertex(x, y);
                                                        }-*/;

    public final native void vertex(int x, int y, int x2, int y2) /*-{
                                                                  this.vertex(x, y, x2, y2);
                                                                  }-*/;

    public final native void vertex(double x, double y, double x2, double y2) /*-{
                                                                              this.vertex(x, y, x2, y2);
                                                                              }-*/;

    public final native int year() /*-{
                                   return this.year();
                                   }-*/;

    public final native void text(String str, int x, int y) /*-{
                                                            this.text(str, x, y);
                                                            }-*/;

    public final native void text(String str, double x, double y) /*-{
                                                                  this.text(str, x, y);
                                                                  }-*/;

    public final native void text(String str, int x, int y, int z) /*-{
                                                                   this.text(str, x, y, z);
                                                                   }-*/;

    public final native void text(String str, double x, double y, double z) /*-{
                                                                            this.text(str, x, y, z);
                                                                            }-*/;

    public final native void text(String str, int x, int y, int width,
            int height) /*-{
                        this.text(str, x, y, width, height);
                        }-*/;

    public final native void text(String str, double x, double y, double width,
            double height) /*-{
                           this.text(str, x, y, width, height);
                           }-*/;

    public final native void text(String str, int x, int y, int width,
            int height, int z) /*-{
                               this.text(str, x, y, width, height, z);
                               }-*/;

    public final native void text(String str, double x, double y, double width,
            double height, double z) /*-{
                                     this.text(str, x, y, width, height, z);
                                     }-*/;

    public final native void textSize(int size) /*-{
                                                this.textSize(size);
                                                }-*/;

    public final native void textSize(double size) /*-{
                                                   this.textSize(size);
                                                   }-*/;

    public final native void textAlign(Object align) /*-{
                                                     this.textAlign(align);
                                                     }-*/;

    public final native void textAlign(Object align, Object valign) /*-{
                                                                    this.textAlign(align, valign);
                                                                    }-*/;

    public final native void textLeading(int dist) /*-{
                                                   this.textLeading(dist);
                                                   }-*/;

    public final native void textLeading(double dist) /*-{
                                                      this.textLeading(dist);
                                                      }-*/;

    public final native void textMode(Object mode) /*-{
                                                   this.textMode(mode);
                                                   }-*/;

    public final native void textFont(Object name, int size) /*-{
                                                             this.textFont(name, size);
                                                             }-*/;

    public final native void textFont(Object name, double size) /*-{
                                                                this.textFont(name, size);
                                                                }-*/;

    public final native void textWidth(String data) /*-{
                                                    this.textWidth(data);
                                                    }-*/;

    public final native double textAscent() /*-{
                                                      return this.textAscent();
                                                      }-*/;

    public final native double textDescent() /*-{
                                             return this.textDescent();
                                             }-*/;

    public final native Object loadFont(String fontname) /*-{
                                                         return this.loadFont(fontname);
                                                         }-*/;

    public final native Object createFont(String name, double size) /*-{
                                                                    return this.createFont(name, size);
                                                                    }-*/;

    public final native Object createFont(String name, double size,
            boolean smooth) /*-{
                            return this.createFont(name, size, smooth);
                            }-*/;

    public final native Object createFont(String name, double size,
            boolean smooth, char[] charset) /*-{
                                            return this.createFont(name, size, smooth, charset);
                                            }-*/;

    public final native Object loadImage(String filename) /*-{
                                                          return this.loadImage(filename);
                                                          }-*/;

    public final native void noTint() /*-{
                                        this.noTint();
                                        }-*/;

    public final native Object requestImage(String filename) /*-{
                                                             return this.requestImage(filename);
                                                             }-*/;

    public final native Object requestImage(String filename, String extension) /*-{
                                                                               return this.requestImage(filename, extension);
                                                                               }-*/;

    public final native void exit() /*-{
                                    this.exit();
                                    }-*/;

    public final native void image(Object img, int x, int y) /*-{
                                                             this.image(img, x, y);
                                                             }-*/;

    public final native void image(Object img, double x, double y) /*-{
                                                                   this.image(img, x, y);
                                                                   }-*/;

    public final native void image(Object img, int x, int y, int width,
            int height) /*-{
                        this.image(img, x, y, width, height);
                        }-*/;

    public final native void image(Object img, double x, double y,
            double width, double height) /*-{
                                         this.image(img, x, y, width, height);
                                         }-*/;

    public final native void imageMode(Object mode) /*-{
                                                    this.imageMode(mode);
                                                    }-*/;

    public final native void tint(int gray) /*-{
                                            this.tint(gray);
                                            }-*/;

    public final native void tint(double gray) /*-{
                                               this.tint(gray);
                                               }-*/;

    public final native void tint(int gray, int a) /*-{
                                                   this.tint(gray, a);
                                                   }-*/;

    public final native void tint(double gray, double a) /*-{
                                                         this.tint(gray, a);
                                                         }-*/;

    public final native void tint(int r, int g, int b) /*-{
                                                       this.tint(r, g, b);
                                                       }-*/;

    public final native void tint(double r, double g, double b) /*-{
                                                                this.tint(r, g, b);
                                                                }-*/;

    public final native void tint(int r, int g, int b, int a) /*-{
                                                              this.tint(r, g, b, a);
                                                              }-*/;

    public final native void tint(double r, double g, double b, double a) /*-{
                                                                          this.tint(r, g, b, a);
                                                                          }-*/;

    public final native void tint(Object color) /*-{
                                                this.tint(color);
                                                }-*/;

    public final native void tint(Object color, int a) /*-{
                                                       this.tint(color, a);
                                                       }-*/;

    public final native void tint(Object color, double a) /*-{
                                                          this.tint(color, a);
                                                          }-*/;

    public final native void copy(int x, int y, int width, int height, int dx,
            int dy, int dwidth, int dheight) /*-{
                                             this.copy(x,y,width,height,dx,dy,dwidth,dheight);
                                             }-*/;

    public final native void copy(Object srcImg, int x, int y, int width,
            int height, int dx, int dy, int dwidth, int dheight) /*-{
                                                                 this.copy(srcImg,x,y,width,height,dx,dy,dwidth,dheight);
                                                                 }-*/;

    public final native void filter(Object mode) /*-{
                                                 this.filter(mode);
                                                 }-*/;

    public final native void filter(Object mode, double param) /*-{
                                                               this.filter(mode, param);
                                                               }-*/;

    public final native Object get() /*-{
                                     return this.get();
                                     }-*/;

    public final native Object get(int x, int y) /*-{
                                                 return this.get(x, y);
                                                 }-*/;

    public final native Object get(int x, int y, int width, int height) /*-{
                                                                        return this.get(x, y, width, height);
                                                                        }-*/;

    public final native void loadPixels() /*-{
                                          this.loadPixels();
                                          }-*/;

    public final native int[] getPixels() /*-{
                                          return this.pixels;
                                          }-*/;

    public final native void updatePixels() /*-{
                                            this.updatePixels();
                                            }-*/;

    public final native void set(int x, int y, Object obj) /*-{
                                                           this.set(x, y, obj);
                                                           }-*/;

    public final native Object createImage(int w, int h, int mode) /*-{
                                                                   return this.createImage(w, h, mode);
                                                                   }-*/;

    public final native Object createImage(int w, int h, Object mode) /*-{
                                                                      return this.createImage(w, h, mode);
                                                                      }-*/;

    public final native String nf(int num, int pad) /*-{
                                                    return this.nf(num, pad);
                                                    }-*/;

    public final native Object lerpColor(Object c1, Object c2, double amt) /*-{
                                                                           return this.lerpColor(c1, c2, amt);
                                                                           }-*/;

    public final native double alpha(Object aColor) /*-{
                                                    return this.alpha(aColor);
                                                    }-*/;

    public final native double blendColor(Object color1, Object color2,
            Object mode) /*-{
                         return this.blendColor(color1, color2, mode);
                         }-*/;

    public final native double blue(Object color) /*-{
                                                  return this.blue(color);
                                                  }-*/;

    public final native double brightness(Object color) /*-{
                                                        return this.brightness(color);
                                                        }-*/;

    public final native double green(Object aColor) /*-{
                                                    return this.green(aColor);
                                                    }-*/;

    public final native double hue(Object aColor) /*-{
                                                  return this.hue(aColor);
                                                  }-*/;

    public final native double red(Object aColor) /*-{
                                                  return this.red(aColor);
                                                  }-*/;

    public final native double saturation(Object aColor) /*-{
                                                         return this.saturation(aColor);
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

    public final native Object color(double gray) /*-{
                                                  return this.color(gray);
                                                  }-*/;

    public final native Object color(int gray, int alpha) /*-{
                                                          return this.color(gray, alpha);
                                                          }-*/;

    public final native Object color(double gray, double alpha) /*-{
                                                                return this.color(gray, alpha);
                                                                }-*/;

    public final native Object color(Object hex, int alpha) /*-{
                                                            return this.color(hex, alpha);
                                                            }-*/;

    public final native Object color(Object hex) /*-{
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

    public final native boolean isFocused() /*-{
                                            return this.focused;
                                            }-*/;

    public final native void setFrameCount(int fc) /*-{
                                                   this.frameCount = fc;
                                                   }-*/;

    public final native void noCursor() /*-{
                                        this.noCursor();
                                        }-*/;

    public final native boolean isOnline() /*-{
                                           return this.online;
                                           }-*/;

    public final native Object getScreen() /*-{
                                           return this.screen;
                                           }-*/;

    public final native int getScreenWidth() /*-{
                                                return this.screen.width;
                                                }-*/;

    public final native int getScreenHeight() /*-{
                                              return this.screen.height;
                                              }-*/;

    public final native double map(double value, double istart, double istop,
            double ostart, double ostop) /*-{
                                         return this.map(value, istart, istop, ostart, ostop);
                                         }-*/;

    public final native void box(int size) /*-{
                                           this.box(size);
                                           }-*/;

    public final native void box(double size) /*-{
                                              this.box(size);
                                              }-*/;

    public final native void box(int width, int height, int depth) /*-{
                                                                   this.box(width,height,depth);
                                                                   }-*/;

    public final native void box(double width, double height, double depth) /*-{
                                                                            this.box(width,height,depth);
                                                                            }-*/;

    public final native void sphere(int radius) /*-{
                                                this.sphere(radius);
                                                }-*/;

    public final native void sphere(double radius) /*-{
                                                   this.sphere(radius);
                                                   }-*/;

    public final native void sphereDetail(int res) /*-{
                                                   this.sphereDetail(res);
                                                   }-*/;

    public final native void sphereDetail(int ures, int vres) /*-{
                                                              this.sphereDetail(ures, vres);
                                                              }-*/;

    public final native void applyMatrix(double n00, double n01, double n02,
            double n03, double n04, double n05, double n06, double n07,
            double n08, double n09, double n10, double n11, double n12,
            double n13, double n14, double n15) /*-{
                                                    this.applyMatrix(n00, n01, n02, n03,
                                                    n04, n05, n06, n07,
                                                    n08, n09, n10, n11,
                                                    n12, n13, n14, n15);
                                                    }-*/;

    public final native void printMatrix() /*-{
                                           this.printMatrix();
                                           }-*/;

    public final native void resetMatrix() /*-{
                                           this.resetMatrix();
                                           }-*/;

    public final native void print(String data) /*-{
                                                this.print(data);
                                                }-*/;

    public final native void print(Object data) /*-{
                                                this.print(data);
                                                }-*/;

    public final native void print(String[] data) /*-{
                                                  this.print(data);
                                                  }-*/;

    public final native void print(Object[] data) /*-{
                                                  this.print(data);
                                                  }-*/;

    public final native void println(String data) /*-{
                                                  this.println(data);
                                                  }-*/;

    public final native void println(Object data) /*-{
                                                  this.println(data);
                                                  }-*/;

    public final native Object loadShape(String filename) /*-{
                                                          return this.loadShape(filename);
                                                          }-*/;

    public final native void shape(Object sh) /*-{
                                              this.shape(sh);
                                              }-*/;

    public final native void shape(Object sh, int x, int y) /*-{
                                                            this.shape(sh,x,y);
                                                            }-*/;

    public final native void shape(Object sh, double x, double y) /*-{
                                                                  this.shape(sh,x,y);
                                                                  }-*/;

    public final native void shape(Object sh, int x, int y, int width,
            int height) /*-{
                           this.shape(sh, x, y, width, height);
                           }-*/;

    public final native void shape(Object sh, double x, double y, double width,
            double height) /*-{
                           this.shape(sh, x, y, width, height);
                           }-*/;

    public final native void shapeMode(Object mode) /*-{
                                                    this.shapeMode(mode);
                                                    }-*/;

    public final native void createGraphics(int width, int height,
            Object renderer) /*-{
                             return this.createGraphics(width, height, renderer);
                             }-*/;

    public final native Object createGraphics(int width, int height,
            Object renderer, String filename) /*-{
                                              return this.createGraphics(width, height, renderer, filename);
                                              }-*/;

    public final native void hint(Object item) /*-{
                                               this.hint(item);
                                               }-*/;

    public final native void cursor() /*-{
                                      this.cursor();
                                      }-*/;

    public final native void cursor(Object mode) /*-{
                                                 this.cursor(mode);
                                                 }-*/;

    public final native void cursor(Object image, int x, int y) /*-{
                                                                this.cursor(image,x,y);
                                                                }-*/;

    public final native void ambientLight(int v1, int v2, int v3) /*-{
                                                                  this.ambientLight(v1,v2,v3);
                                                                  }-*/;

    public final native void ambientLight(double v1, double v2, double v3) /*-{
                                                                           this.ambientLight(v1,v2,v3);
                                                                           }-*/;

    public final native void ambientLight(int v1, int v2, int v3, int x, int y,
            int z) /*-{
                   this.ambientLight(v1,v2,v3,x,y,z);
                   }-*/;

    public final native void ambientLight(double v1, double v2, double v3,
            double x, double y, double z) /*-{
                                          this.ambientLight(v1,v2,v3,x,y,z);
                                          }-*/;

    public final native void directionalLight(int v1, int v2, int v3, int nx,
            int ny, int nz) /*-{
                            this.directionalLight(v1,v2,v3,nx,ny,nz);
                            }-*/;

    public final native void directionalLight(double v1, double v2, double v3,
            double nx, double ny, double nz) /*-{
                                             this.directionalLight(v1,v2,v3,nx,ny,nz);
                                             }-*/;

    public final native void lightFalloff(int constant, int linear,
            int quadratic) /*-{
                           this.lightFalloff(constant,linear,quadratic);
                           }-*/;

    public final native void lightFalloff(double constant, double linear,
            double quadratic) /*-{
                              this.lightFalloff(constant,linear,quadratic);
                              }-*/;

    public final native void lightSpecular(int v1, int v2, int v3) /*-{
                                                                   this.lightSpecular(v1,v2,v3);
                                                                   }-*/;

    public final native void lightSpecular(double v1, double v2, double v3) /*-{
                                                                            this.lightSpecular(v1,v2,v3);
                                                                            }-*/;

    public final native void lights() /*-{
                                      this.lights();
                                      }-*/;

    public final native void noLights() /*-{
                                        this.noLights();
                                        }-*/;

    public final native void normal(double nx, double ny, double nz) /*-{
                                                                     this.normal(nx,ny,nz);
                                                                     }-*/;

    public final native void pointLight(int v1, int v2, int v3, int nx, int ny,
            int nz) /*-{
                    this.pointLight(v1,v2,v3,nx,ny,nz);
                    }-*/;

    public final native void pointLight(double v1, double v2, double v3,
            double nx, double ny, double nz) /*-{
                                             this.pointLight(v1,v2,v3,nx,ny,nz);
                                             }-*/;

    public final native void spotLight(int v1, int v2, int v3, int nx, int ny,
            int nz, double angle, double concentration) /*-{
                                                        this.spotLight(v1,v2,v3,nx,ny,nz,angle,concentration);
                                                        }-*/;

    public final native void spotLight(double v1, double v2, double v3,
            double nx, double ny, double nz, double angle, double concentration) /*-{
                                                                                 this.spotLight(v1,v2,v3,nx,ny,nz,angle,concentration);
                                                                                 }-*/;

    public final native void beginCamera() /*-{
                                           this.beginCamera();
                                           }-*/;

    public final native void camera() /*-{
                                      this.camera();
                                      }-*/;

    public final native void camera(double eyeX, double eyeY, double eyeZ,
            double centerX, double centerY, double centerZ, double upX,
            double upY, double upZ) /*-{
                                    this.camera(eyeX, eyeY, eyeZ,centerX, centerY, centerZ,upX, upY, upZ);
                                    }-*/;

    public final native void endCamera() /*-{
                                         this.endCamera();
                                         }-*/;

    public final native void frustum(double left, double right, double bottom,
            double top, double near, double far) /*-{
                                                 this.frustum(left, right, bottom,top, near, far);
                                                 }-*/;

    public final native void ortho() /*-{
                                     this.ortho();
                                     }-*/;

    public final native void ortho(double left, double right, double bottom,
            double top, double near, double far) /*-{
                                                 this.ortho(left, right, bottom,top, near, far);
                                                 }-*/;

    public final native void perspective() /*-{
                                           this.perspective();
                                           }-*/;

    public final native void perspective(double fov, double aspect,
            double zNear, double zFar) /*-{
                                       this.perspective(fov, aspect, zNear,zFar);
                                       }-*/;

    public final native void printCamera() /*-{
                                           this.printCamera();
                                           }-*/;

    public final native void printProjection() /*-{
                                               this.printProjection();
                                               }-*/;

    public final native double modelX(int x, int y, int z) /*-{
                                                           return this.modelX(x,y,z);
                                                           }-*/;

    public final native double modelX(double x, double y, double z) /*-{
                                                                    return this.modelX(x,y,z);
                                                                    }-*/;

    public final native double modelY(int x, int y, int z) /*-{
                                                           return this.modelY(x,y,z);
                                                           }-*/;

    public final native double modelY(double x, double y, double z) /*-{
                                                                    return this.modelY(x,y,z);
                                                                    }-*/;

    public final native double modelZ(int x, int y, int z) /*-{
                                                           return this.modelZ(x,y,z);
                                                           }-*/;

    public final native double modelZ(double x, double y, double z) /*-{
                                                                    return this.modelZ(x,y,z);
                                                                    }-*/;

    public final native double screenX(int x, int y, int z) /*-{
                                                            return this.screenX(x,y,z);
                                                            }-*/;

    public final native double screenX(double x, double y, double z) /*-{
                                                                     return this.screenX(x,y,z);
                                                                     }-*/;

    public final native double screenY(int x, int y, int z) /*-{
                                                            return this.screenY(x,y,z);
                                                            }-*/;

    public final native double screenY(double x, double y, double z) /*-{
                                                                     return this.screenY(x,y,z);
                                                                     }-*/;

    public final native double screenZ(int x, int y, int z) /*-{
                                                            return this.screenZ(x,y,z);
                                                            }-*/;

    public final native double screenZ(double x, double y, double z) /*-{
                                                                     return this.screenZ(x,y,z);
                                                                     }-*/;

    public final native void ambient(int gray) /*-{
                                               this.ambient(gray);
                                               }-*/;

    public final native void ambient(double gray) /*-{
                                                  this.ambient(gray);
                                                  }-*/;

    public final native void ambient(Object color) /*-{
                                                   this.ambient(color);
                                                   }-*/;

    public final native void ambient(int v1, int v2, int v3) /*-{
                                                             this.ambient(v1,v2,v3);
                                                             }-*/;

    public final native void ambient(double v1, double v2, double v3) /*-{
                                                                      this.ambient(v1,v2,v3);
                                                                      }-*/;

    public final native void emissive(int gray) /*-{
                                                this.emissive(gray);
                                                }-*/;

    public final native void emissive(double gray) /*-{
                                                   this.emissive(gray);
                                                   }-*/;

    public final native void emissive(Object color) /*-{
                                                    this.emissive(color);
                                                    }-*/;

    public final native void emissive(int v1, int v2, int v3) /*-{
                                                              this.emissive(v1,v2,v3);
                                                              }-*/;

    public final native void emissive(double v1, double v2, double v3) /*-{
                                                                       this.emissive(v1,v2,v3);
                                                                       }-*/;

    public final native void shininess(double shine) /*-{
                                                     this.shininess(shine);
                                                     }-*/;

    public final native void specular(int gray) /*-{
                                                this.specular(gray);
                                                }-*/;

    public final native void specular(double gray) /*-{
                                                   this.specular(gray);
                                                   }-*/;

    public final native void specular(Object color) /*-{
                                                    this.specular(color);
                                                    }-*/;

    public final native void specular(int v1, int v2, int v3) /*-{
                                                              this.specular(v1,v2,v3);
                                                              }-*/;

    public final native void specular(double v1, double v2, double v3) /*-{
                                                                       this.specular(v1,v2,v3);
                                                                       }-*/;

}
