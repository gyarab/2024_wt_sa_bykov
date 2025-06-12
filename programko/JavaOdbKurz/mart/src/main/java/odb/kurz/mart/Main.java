package odb.kurz.mart;

import java.util.Scanner;

public class Main {
    public static class HSV {
        public double h, s, v;

        @Override
        public String toString() {
            return new StringBuilder().append(h).append(" ").append(s).append(" ").append(v).toString();
        }
    };

    public static class RGB {
        public double r, g, b;

        public RGB() {
            this.r = 1;
            this.g = 0;
            this.b = 1;
        }
        public RGB(double r, double g, double b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public double min() {
            return Math.min(Math.min(this.r, this.g), this.b);
        }
        public double max() {
            return Math.max(Math.max(this.r, this.g), this.b);
        }

        public RGB multiplyBy(double val) {
            r *= val;
            g *= val;
            b *= val;
            return this;
        }

        public RGB normalize(double max) {
            r /= max;
            g /= max;
            b /= max;
            return this;
        }
        public RGB normalize() { 
            return this.normalize(255); 
        }
        public RGB autoNormalize() {
            double m = this.max();
            this.r /= m;
            this.g /= m;
            this.b /= m;
            return this;
        }

        @Override
        public String toString() {
            return new StringBuilder().append((int)r).append(" ").append((int)g).append(" ").append((int)b).toString();
        }

        public CMYK toCmyk() {
            //https://www.rapidtables.com/convert/color/rgb-to-cmyk.html

            CMYK rs = new CMYK();
            rs.k = 1.0-this.max();
            if(rs.k == 1.0) return rs;
            rs.c = (1.0-this.r-rs.k)/(1.0-rs.k);
            rs.m = (1.0-this.g-rs.k)/(1.0-rs.k);
            rs.y = (1.0-this.b-rs.k)/(1.0-rs.k);

            //System.out.println(rs);

            return rs;
        }

        public HSV toHSV() {
            //TODO

            HSV rs = new HSV();

            return rs;
        }
    }
    public static class CMYK {
        public double c, m, y, k;

        public CMYK() {
            this.c = 0;
            this.m = 0;
            this.y = 0;
            this.k = 0;
        }

        public CMYK multiplyBy(double val) {
            c *= val;
            m *= val;
            y *= val;
            k *= val;
            return this;
        }

        @Override
        public String toString() {
            return new StringBuilder().append((int)c).append(" ").append((int)m).append(" ").append((int)y).append(" ").append((int)k).toString();
        }
    }

    public static void main(String[] args) {
        RGB r = new RGB();
        Scanner s = new Scanner(System.in);
        r.r = Integer.parseInt(s.nextLine());
        r.g = Integer.parseInt(s.nextLine());
        r.b = Integer.parseInt(s.nextLine());
        s.close();

        r.normalize(255);
        CMYK c = r.toCmyk().multiplyBy(255);
        System.out.println(c);
    }
}