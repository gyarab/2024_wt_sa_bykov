/*
 * Eins.java
 * Matrices playground and a simple text renderer.
 * Copyright (C) 2025 Martin Bykov
 * @author Martin Bykov
 * Uses Jan Lana's Gyarab2D library.
 */

package gyarab.grafika;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MartinBykov extends Gyarab2D {
    static final int IDX_AMOUNT = 30;
    static Framebuffer f;

    public class Vector4D {
        public double x, y, z, w;

        public Vector4D(double x, double y, double z, double w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }
    }

    public Vector4D multiplyByMatrix(Vector4D v, Matrix m) {
        return new Vector4D(
            m.get(0, 0) * v.x + m.get(0, 1) * v.y + m.get(0, 2) * v.z + m.get(0, 3) * v.w,
            m.get(1, 0) * v.x + m.get(1, 1) * v.y + m.get(1, 2) * v.z + m.get(1, 3) * v.w,
            m.get(2, 0) * v.x + m.get(2, 1) * v.y + m.get(2, 2) * v.z + m.get(2, 3) * v.w,
            m.get(3, 0) * v.x + m.get(3, 1) * v.y + m.get(3, 2) * v.z + m.get(3, 3) * v.w
         );
    }

    public void drawSquare(Matrix m, int idx, int top, int left, int bottom, int right) {
        int starty = Math.min(top, bottom);
        int startx = Math.min(left, right);
        int endy = Math.max(top, bottom);
        int endx = Math.max(left, right);
        double chx = 255/(endx - startx);
        double chy = 255/(endy - starty);
        for(int x = startx; x < endx; x++) {
            for(int y = starty; y < endy; y++) {
                Vector4D result = multiplyByMatrix(new Vector4D(x, y, 1, 1), m);
                f.namalujBodFake((int)result.x, (int)result.y, 
                    idx*(255/IDX_AMOUNT),
                    (int)Math.floor((x-startx)*chx),
                    (int)Math.floor((y-starty)*chy)
                );
            }
        }
    }

    public void drawCube(Matrix m, int idx, int top, int left, int bottom, int right, int front, int back) {
        int starty = Math.min(top, bottom);
        int startx = Math.min(left, right);
        int endy = Math.max(top, bottom);
        int endx = Math.max(left, right);
        int startz = Math.min(front, back);
        int endz = Math.max(front, back);
        int cr, cg, cb;
        for(int x = startx; x < endx; x++) {
            for(int y = starty; y < endy; y++) {
                for(int z = startz; z < endz; z++) {
                    cr = (x == startx) ? 255 : 0;
                    cb = (y == starty) ? 255 : 0;
                    cg = (z == startz) ? 255 : 0;
                    cr = (x == endx) ? 127 : cr;
                    cb = (y == endy) ? 127 : cb;
                    cg = (z == endz) ? 127 : cg;
                    Vector4D result = multiplyByMatrix(new Vector4D(x, y, z, 1), m);
                    f.namalujBodFake(
                        (int)result.x, (int)result.y,
                        cr,cg,cb);
                }
            }
        }
    }

    public void drawLine(int coord, boolean isVertical, int strength) {
        if(isVertical) for(int i = -100; i < 100; i++) {
            f.namalujBodFake(coord, i, strength);
        }
        else for(int i = -100; i < 100; i++) {
            f.namalujBodFake(i, coord, strength);
        }
    }

    public void drawGrid() {
        for(int i = -100; i < 100; i += 10) {
            for(int j = -100; j < 100; j++) {
                drawLine(i, false, 220);
                drawLine(i, true, 220);
            }
        }

        drawLine( 0, false, 100);
        drawLine( 0, true, 100);
    }

    public static boolean dir = false;

    public Matrix buildMatrix(double translateX, double translateY, double translateZ, 
        double scaleX, double scaleY, double scaleZ, 
        double rotX, double rotY, double rotZ) {
        double radX = rotX*Math.PI/180;
        double radY = rotY*Math.PI/180;
        double radZ = rotZ*Math.PI/180;
        double cx = Math.cos(radX);
        double sx = Math.sin(radX);
        double cy = Math.cos(radY);
        double sy = Math.sin(radY);
        double cz = Math.cos(radZ);
        double sz = Math.sin(radZ);

        //minified
        double[][]tData={{1.0,0.0,0.0,translateX},{0.0,1.0,0.0,translateY},{0.0,0.0,1.0,translateZ},{0.0,0.0,0.0,1.0},};double[][]rxData={{1.0,0.0,0.0,0.0},{0.0,cx,-sx,0.0},{0.0,sx,cx,0.0},{0.0,0.0,0.0,1.0},};double[][]ryData={{cy,0.0,sy,0.0},{0.0,1.0,0.0,0.0},{-sy,0.0,cy,0.0},{0.0,0.0,0.0,1.0},};double[][]rzData={{cz,-sz,0.0,0.0},{sz,cz,0.0,0.0},{0.0,0.0,1.0,0.0},{0.0,0.0,0.0,1.0},};double[][]sData={{scaleX,0.0,0.0,0.0},{0.0,scaleY,0.0,0.0},{0.0,0.0,scaleZ,0.0},{0.0,0.0,0.0,1.0},};

        Matrix T = new Matrix(tData);
        Matrix Rx = new Matrix(rxData);
        Matrix Ry = new Matrix(ryData);
        Matrix Rz = new Matrix(rzData);
        Matrix S = new Matrix(sData);

        //order should be SRT - lib reverses
        Matrix rs = T.times(Rx).times(Ry).times(Rz).times(S);
        return rs;
    }  

    public Matrix buildIdentity() {
        //minified
        double[][]idData={{1.0,0.0,0.0,0.0},{0.0,1.0,0.0,0.0},{0.0,0.0,1.0,0.0},{0.0,0.0,0.0,1.0},};
        return new Matrix(idData);
    }

    static final int LETTER_SIZE = 5;

    public class CharacterData {
        public char character;
        public int data[];

        public CharacterData(char c, int[] data) {
            this.character = c;
            this.data = data;
        }
    };

    //minified
    final static int[]idk={1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1};final static int[]aData={0,0,1,0,0,0,1,0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,0,1,0};final static int[]bData={0,1,1,0,0,0,1,0,1,0,0,1,1,0,0,0,1,0,1,0,0,1,1,0,0,};final static int[]cData={0,0,1,1,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,1,0,};final static int[]dData={0,1,1,0,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,1,0,0,};final static int[]eData={0,1,1,1,0,0,1,0,0,0,0,1,1,1,0,0,1,0,0,0,0,1,1,1,0,};final static int[]fData={0,1,1,1,0,0,1,0,0,0,0,1,1,1,0,0,1,0,0,0,0,1,0,0,0,};final static int[]gData={0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,0,1,0,1,0,0,0,1,1,0,};final static int[]hData={0,1,0,1,0,0,1,0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,0,1,0,};final static int[]iData={0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,};final static int[]jData={0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,1,0,0,1,1,1,0,};final static int[]kData={0,1,0,1,0,0,1,0,1,0,0,1,1,0,0,0,1,0,1,0,0,1,0,1,0,};final static int[]lData={0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,0,};final static int[]mData={1,0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1,};final static int[]nData={1,0,0,0,1,1,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,};final static int[]oData={0,0,1,0,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,};final static int[]pData={0,1,1,0,0,0,1,0,1,0,0,1,1,0,0,0,1,0,0,0,0,1,0,0,0,};final static int[]qData={0,0,1,0,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,1,0,0,1,0,1,};final static int[]rData={0,1,1,0,0,0,1,0,1,0,0,1,1,0,0,0,1,0,1,0,0,1,0,1,0,};final static int[]sData={0,0,1,1,0,0,1,0,0,0,0,0,1,1,0,0,0,0,1,0,0,1,1,1,0,};final static int[]tData={0,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,};final static int[]uData={0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,};final static int[]vData={0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,1,0,0,};final static int[]wData={1,0,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0,};final static int[]xData={1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,};final static int[]yData={0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,};final static int[]zData={0,1,1,1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,1,1,0,};final static int[]commaData={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,};final static int[]dotData={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,};final static int[]zeroData={0,0,1,0,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,0,1,0,0,};final static int[]oneData={0,0,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,1,1,0,};final static int[]twoData={0,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,1,0,0,0,1,1,1,0,};final static int[]threeData={0,1,1,0,0,0,0,0,1,0,0,1,1,1,0,0,0,0,1,0,0,1,1,0,0,};final static int[]fourData={0,1,0,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,1,0,0,0,0,1,0,};final static int[]fiveData={0,1,1,1,0,0,1,0,0,0,0,1,1,1,0,0,0,0,1,0,0,1,1,0,0,};final static int[]sixData={0,0,1,1,0,0,1,0,0,0,0,1,1,1,0,0,1,0,1,0,0,0,1,1,0,};final static int[]sevenData={0,1,1,1,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,};final static int[]eightData={0,1,1,1,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,1,1,1,0,};final static int[]nineData={0,0,1,0,0,0,1,0,1,0,0,1,1,1,0,0,0,0,1,0,0,1,1,0,0,};final static int[]exclamationData={0,1,1,1,0,0,1,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,};final static int[]arrowLeftData={0,0,0,1,0,0,1,1,0,0,1,1,1,1,1,0,1,0,0,0,0,0,1,0,0,};final static int[]arrowRightData={0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,0,0,0,1,0,0,0,1,0,0,};final static int[]apostropheData={0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};final static int[]quotationData={0,1,0,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};final static int[]colonData={0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,};final static int[]semiColonData={0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,};final static int[]minusData={0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,};final static int[]questionData={0,1,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,};

    final CharacterData letters[] = {
        //minified
        new CharacterData('A',aData),new CharacterData('B',bData),new CharacterData('C',cData),new CharacterData('D',dData),new CharacterData('E',eData),new CharacterData('F',fData),new CharacterData('G',gData),new CharacterData('H',hData),new CharacterData('I',iData),new CharacterData('J',jData),new CharacterData('K',kData),new CharacterData('L',lData),new CharacterData('M',mData),new CharacterData('N',nData),new CharacterData('O',oData),new CharacterData('P',pData),new CharacterData('Q',qData),new CharacterData('R',rData),new CharacterData('S',sData),new CharacterData('T',tData),new CharacterData('U',uData),new CharacterData('V',vData),new CharacterData('W',wData),new CharacterData('X',xData),new CharacterData('Y',yData),new CharacterData('Z',zData),new CharacterData(',',commaData),new CharacterData('.',dotData),new CharacterData('0',zeroData),new CharacterData('1',oneData),new CharacterData('2',twoData),new CharacterData('3',threeData),new CharacterData('4',fourData),new CharacterData('5',fiveData),new CharacterData('6',sixData),new CharacterData('7',sevenData),new CharacterData('8',eightData),new CharacterData('9',nineData),new CharacterData('!',exclamationData),new CharacterData('`',apostropheData),new CharacterData('"',quotationData),new CharacterData(':',colonData),new CharacterData(';',semiColonData),new CharacterData('-',minusData),new CharacterData('?',questionData),new CharacterData('\uAAAA',arrowLeftData),new CharacterData('\uBBBB',arrowRightData),
    };

    public void renderCharData(Matrix m, int[] arr, int x, int y, int r, int g, int b) {
        for(int dy = 0; dy < LETTER_SIZE; dy++) {
            for(int dx = 0; dx < LETTER_SIZE; dx++) {
                if(arr[dy*LETTER_SIZE+dx] == 1) {
                    Vector4D result = multiplyByMatrix(new Vector4D(x+dx, y-dy, 1, 1), m);
                    f.namalujBodFake(result.x, result.y, r, g, b);
                }
            }
        }
    }

    public void renderChar(Matrix m, char c, int x, int y, int r, int g, int b) {
        for (CharacterData letter : letters) {
            if(c == ' ') return;
            if (Character.toUpperCase(c) == letter.character) {
                renderCharData(m, letter.data, x, y, r, g, b);
                return;
            }
        }
       renderCharData(m, idk, x, y, r, g, b);
    };

    public void drawText(Matrix m, String s, int x, int y, int r, int g, int b) {
        int yoffset = 0;
        int xoffset = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '\n') {
                xoffset = 0;
                yoffset -= LETTER_SIZE + 1;
                continue;
            }
            renderChar(m, s.charAt(i), x+xoffset, y+yoffset, r, g, b);
            xoffset += LETTER_SIZE + 1;
        }
    }

    public class PixelRepresentation {
        public int r, g, b;
    };

    public class Framebuffer {
        Gyarab2D app;
        PixelRepresentation[][] pixels;

        public Framebuffer(Gyarab2D app) {
            this.app = app;
            this.pixels = new PixelRepresentation[this.app.maxXY*2][this.app.maxXY*2];
             for(int i = 0; i < this.app.maxXY*2; i++) {
                for(int j = 0; j < this.app.maxXY*2; j++) {
                    this.pixels[i][j] = new PixelRepresentation();
                    this.pixels[i][j].r = 255;
                    this.pixels[i][j].g = 255;
                    this.pixels[i][j].b = 255;
                }
            }
        }

        public void namalujBodFake(int x, int y, int r, int g, int b) {
            if(x < -this.app.maxXY || x >= this.app.maxXY || 
               y < -this.app.maxXY || y >= this.app.maxXY) {
                return; //out of bounds
            }
            this.pixels[x+this.app.maxXY][y+this.app.maxXY] = new PixelRepresentation();
            this.pixels[x+this.app.maxXY][y+this.app.maxXY].r = Math.max(Math.min(255, r), 0);
            this.pixels[x+this.app.maxXY][y+this.app.maxXY].g = Math.max(Math.min(255, g), 0);
            this.pixels[x+this.app.maxXY][y+this.app.maxXY].b = Math.max(Math.min(255, b), 0);
        }
        public void namalujBodFake(double x, double y, double r, double g, double b) {
            this.namalujBodFake((int)x, (int)y, (int)r, (int)g, (int)b);
        }
        public void namalujBodFake(int x, int y, int s) {
            this.namalujBodFake(x, y, s, s, s);
        }
        public void namalujBodFake(double x, double y, double s) {
            this.namalujBodFake(x, y, s, s, s);
        }        

        public void scaleColor(double r, double g, double b) {
            for(int i = 0; i < this.app.maxXY*2; i++) {
                for(int j = 0; j < this.app.maxXY*2; j++) {
                    this.pixels[i][j].r = (int)Math.min(255, (this.pixels[i][j].r * r));
                    this.pixels[i][j].g = (int)Math.min(255, (this.pixels[i][j].g * g));
                    this.pixels[i][j].b = (int)Math.min(255, (this.pixels[i][j].b * b));
                }
            }
        }

        public void subblur(int xleft, int ybottom, int xright, int ytop) {
            PixelRepresentation[][] newPixels = new PixelRepresentation[this.app.maxXY*2][this.app.maxXY*2];

            for(int i = 0; i < this.app.maxXY*2; i++) {
                for(int j = 0; j < this.app.maxXY*2; j++) {
                    //skip if out of bounds
                    if(i-this.app.maxXY < xleft || 
                    i-this.app.maxXY >= xright ||
                    this.app.maxXY-j < ybottom || 
                    this.app.maxXY-j >= ytop) {
                        newPixels[i][j] = this.pixels[i][j];
                    }
                    else {
                        int r = 0;
                        int g = 0;
                        int b = 0;
                        int amount = 0;

                        for(int dx = -1; dx <= 1; dx++) {
                            for(int dy = -1; dy <= 1; dy++) {
                                if(dx == 0 && dy == 0) continue;
                                if(i+dx < 0 || i+dx >= this.app.maxXY*2 || 
                                j+dy < 0 || j+dy >= this.app.maxXY*2) continue; //out of bounds
                                
                                r += this.pixels[i+dx][j+dy].r;
                                g += this.pixels[i+dx][j+dy].g;
                                b += this.pixels[i+dx][j+dy].b;
                                amount++;
                            }
                        }

                        newPixels[i][j] = new PixelRepresentation();
                        newPixels[i][j].r = r / Math.max(1, amount);
                        newPixels[i][j].g = g / Math.max(1, amount);
                        newPixels[i][j].b = b / Math.max(1, amount);
                    }
                }   
            }

            this.pixels = newPixels;
        }

        public void clear() {
            for(int i = 0; i < this.app.maxXY*2; i++) {
                for(int j = 0; j < this.app.maxXY*2; j++) {
                    this.pixels[i][j] = new PixelRepresentation();
                    this.pixels[i][j].r = 255;
                    this.pixels[i][j].g = 255;
                    this.pixels[i][j].b = 255;
                }
            }
        }

        //simple box blur
        public void blur() {
            this.subblur(-this.app.maxXY, -this.app.maxXY, this.app.maxXY, this.app.maxXY);
        }

        public void vignette(double size) {
            double maxDist = Math.sqrt(Math.pow(this.app.maxXY, 2) + Math.pow(this.app.maxXY, 2));
            for(int i = 0; i < this.app.maxXY*2; i++) {
                for(int j = 0; j < this.app.maxXY*2; j++) {
                    double dist = Math.sqrt(Math.pow(i-this.app.maxXY, 2) + Math.pow(j-this.app.maxXY, 2));
                    double factor = dist / maxDist; //avoid division by zero
                    this.pixels[i][j].r *= Math.max(0.0, Math.min(1.0, 1.0 - factor + size));
                    this.pixels[i][j].g *= Math.max(0.0, Math.min(1.0, 1.0 - factor + size));
                    this.pixels[i][j].b *= Math.max(0.0, Math.min(1.0, 1.0 - factor + size));
                }   
            }
        }

        public void fade(double factor) {
            for(int i = 0; i < this.app.maxXY*2; i++) {
                for(int j = 0; j < this.app.maxXY*2; j++) {
                    this.pixels[i][j].r *= factor;
                    this.pixels[i][j].g *= factor;
                    this.pixels[i][j].b *= factor;
                }   
            }
        }

        public void draw() {
             for(int i = 0; i < this.app.maxXY*2; i++) {
                for(int j = 0; j < this.app.maxXY*2; j++) {
                    this.app.namalujBod(
                        i-this.app.maxXY, j-this.app.maxXY, 
                        this.pixels[i][j].r, 
                        this.pixels[i][j].g, 
                        this.pixels[i][j].b
                    );
                }   
            }
        }
    };

    @Override
    public boolean maluj(int idx) {
        if(idx == 0) dir = !dir;

        f = new Framebuffer(this);

        String onegin = """
My uncle-high ideals inspire him;
he really forced one to admire him-
but when past joking he fell sick,
and never played a shrewder trick.
Let others learn from his example!
But God, how deadly dull to sample
sickroom attendance night and day
and never stir a foot away! And the
sly baseness,fit to throttle, of 
entertaining the half-dead: one 
smoothes the pillows down in bed,
and glumly serves the medicine
bottle, and sighs, and asks oneself
all through: \"When will the devil 
come for you?\" 
""";

        int offset = (dir ? idx : IDX_AMOUNT-idx)*5;
        
        drawGrid();

        drawSquare(buildMatrix(-100+offset, -80, 0, 2, 1, 1, 0, 0, 45+idx*5), (dir ? idx : IDX_AMOUNT-idx), 10, 20, -10, -20);

        drawText(
            buildMatrix(0, 0, 0, 1, 1, 1, 0, 0, 0),
             onegin, -102, 60,
             127, 127, 255);

        double transforms[][] = {
            {-50, -40, 0.25, 1}, {0, -40, 0.5, 2}, {50, -40, 1, 0},
            {-50, -70, 0.5, 2}, {0, -70, 1, 0}, {50, -70, 0.5, 2},
            {-50, -100, 1, 0}, {0, -100, 0.5, 2}, {50, -100, 0.25, 1}
        };

        for(double[] t : transforms) {
            drawCube(
                buildMatrix(
                    t[0], t[1], 0, 
                    t[2], t[2], t[2], 
                    0, (360/IDX_AMOUNT)*(idx+t[3]),  (360/IDX_AMOUNT)*(idx+t[3])),
                    (dir ? idx : IDX_AMOUNT-idx),
                10, -10, -10, 10, 10, -10
            );
        }

        drawCube(
            buildMatrix(
                100-offset, -80, 0, 
                2, 1, 1,
                0, (360/IDX_AMOUNT)*(idx+5),  (360/IDX_AMOUNT)*(idx+5)),
                (dir ? idx : IDX_AMOUNT-idx),
            10, -10, -10, 10, 10, -10
        );

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss, dd-MM-yyyy"));
        String cdas = "Ceske Drahy, narodni dopravce\nR 667 Rozmberk\nBrno hl.n. \uAAAA\uBBBB Plzen hl.n.\n"+time;
        drawText(buildMatrix(0, 100-idx/2, 0, 1, 1, 1, 0, 0, idx < IDX_AMOUNT/2 ? 5 : -5), cdas, -80, 0, 255, 0, 0);

        if(idx < IDX_AMOUNT/4.0) f.scaleColor(0.5, 1.0, 0.5);
        else if(idx < IDX_AMOUNT/2.0) f.scaleColor(1.0, 0.5, 0.5);
        else if(idx < IDX_AMOUNT*3/4.0) f.scaleColor(0.5, 0.5, 1.0);
        else f.scaleColor(1.0, 1.0, 1.0);
        
        f.subblur(-100+(idx*5), -100+(idx*5), -50+(idx*5), -50+(idx*5));
        f.vignette(0.3);
        f.fade(0.9);
        f.draw();
        f.clear();

        return idx < IDX_AMOUNT;
    }

    public static void main(String[] args) {
        launch();
    }
}
