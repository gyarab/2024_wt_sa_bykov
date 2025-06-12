package gyarab.grafika;

public class TextRenderer extends Gyarab2D {
    static final int LETTER_SIZE = 5;

    public class CharacterData {
        public char character;
        public int data[];

        public CharacterData(char c, int[] data) {
            this.character = c;
            this.data = data;
        }
    };

    final static int[] idk = { 
        1, 1, 1, 1, 1,
        1, 0, 1, 0, 1,
        1, 1, 0, 1, 1,
        1, 0, 1, 0, 1,
        1, 1, 1, 1, 1
    };

    final static int[] aData = { 
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0, 
        0, 1, 1, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0
    };
    final static int[] bData = { 
        0, 1, 1, 0, 0, 
        0, 1, 0, 1, 0,
        0, 1, 1, 0, 0,
        0, 1, 0, 1, 0, 
        0, 1, 1, 0, 0,
    };
    final static int[] cData = { 
        0, 0, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 0, 0, 0,
        0, 1, 0, 0, 0, 
        0, 0, 1, 1, 0,
    };
    final static int[] dData = { 
        0, 1, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 0, 0,
    };
    final static int[] eData = { 
        0, 1, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 1, 1, 0,
    };
    final static int[] fData = { 
        0, 1, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 0, 0, 0,
    };
    final static int[] gData = { 
        0, 0, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 1, 0,
    };
    final static int[] hData = { 
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
    };
    final static int[] iData = { 
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] jData = { 
        0, 0, 0, 1, 0,
        0, 0, 0, 1, 0,
        0, 0, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0,
    };
    final static int[] kData = { 
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
    };
    final static int[] lData = {
        0, 0, 1, 0, 0, 
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 1, 0,
    };
    final static int[] mData = { 
        1, 0, 0, 0, 1,
        1, 1, 0, 1, 1,
        1, 0, 1, 0, 1,
        1, 0, 0, 0, 1,
        1, 0, 0, 0, 1,
    };
    final static int[] nData = { 
        1, 0, 0, 0, 1,
        1, 1, 0, 0, 1,
        1, 0, 1, 0, 1,
        1, 0, 0, 1, 1,
        1, 0, 0, 0, 1,
    };
    final static int[] oData = {
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] pData = { 
        0, 1, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 0, 0,
        0, 1, 0, 0, 0,
        0, 1, 0, 0, 0,
    };
    final static int[] qData = { 
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 1,
        0, 0, 1, 0, 1,
    };
    final static int[] rData = { 
        0, 1, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
    };
    final static int[] sData = { 
        0, 0, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 0, 1, 1, 0,
        0, 0, 0, 1, 0,
        0, 1, 1, 1, 0,
    }; 
    final static int[] tData = { 
        0, 1, 1, 1, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] uData = {
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] vData = {
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] wData = {
        1, 0, 0, 0, 1,
        1, 0, 0, 0, 1,
        1, 0, 1, 0, 1,
        1, 0, 1, 0, 1,
        0, 1, 0, 1, 0,
    };
    final static int[] xData = {
        1, 0, 0, 0, 1,
        0, 1, 0, 1, 0,
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0,
        1, 0, 0, 0, 1,
    };
    final static int[] yData = {
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] zData = {
        0, 1, 1, 1, 0,
        0, 0, 0, 1, 0,
        0, 0, 1, 0, 0,
        0, 1, 0, 0, 0,
        0, 1, 1, 1, 0,
    };
    final static int[] commaData = {
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 1, 0,
        0, 0, 1, 1, 0,
    };
    final static int[] dotData = {
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] zeroData = {
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] oneData = {
        0, 0, 1, 0, 0,
        0, 1, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 1, 1, 1, 0,
    };
    final static int[] twoData = {
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 0, 0, 1, 0,
        0, 0, 1, 0, 0,
        0, 1, 1, 1, 0,
    };
    final static int[] threeData = {
        0, 1, 1, 0, 0,
        0, 0, 0, 1, 0,
        0, 1, 1, 1, 0,
        0, 0, 0, 1, 0,
        0, 1, 1, 0, 0,
    };
    final static int[] fourData = {
        0, 1, 0, 1, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0,
        0, 0, 0, 1, 0,
        0, 0, 0, 1, 0,
    };
    final static int[] fiveData = {
        0, 1, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 1, 1, 0,
        0, 0, 0, 1, 0,
        0, 1, 1, 0, 0,
    };
    final static int[] sixData = {
        0, 0, 1, 1, 0,
        0, 1, 0, 0, 0,
        0, 1, 1, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 1, 0,
    };
    final static int[] sevenData = {
        0, 1, 1, 1, 0,
        0, 0, 0, 1, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
        0, 0, 1, 0, 0,
    };
    final static int[] eightData = {
        0, 1, 1, 1, 0,
        0, 1, 0, 1, 0,
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0,
    };
    final static int[] nineData = {
        0, 0, 1, 0, 0,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0,
        0, 0, 0, 1, 0,
        0, 1, 1, 0, 0,
    };


    final CharacterData letters[] = {
        new CharacterData('A', aData),
        new CharacterData('B', bData),
        new CharacterData('C', cData),
        new CharacterData('D', dData),
        new CharacterData('E', eData),
        new CharacterData('F', fData),
        new CharacterData('G', gData),
        new CharacterData('H', hData),
        new CharacterData('I', iData),
        new CharacterData('J', jData),
        new CharacterData('K', kData),
        new CharacterData('L', lData),
        new CharacterData('M', mData),
        new CharacterData('N', nData),
        new CharacterData('O', oData),
        new CharacterData('P', pData),
        new CharacterData('Q', qData),
        new CharacterData('R', rData),
        new CharacterData('S', sData),
        new CharacterData('T', tData),
        new CharacterData('U', uData),
        new CharacterData('V', vData),
        new CharacterData('W', wData),
        new CharacterData('X', xData),
        new CharacterData('Y', yData),
        new CharacterData('Z', zData),
        new CharacterData(',', commaData),
        new CharacterData('.', dotData),
        new CharacterData('0', zeroData),
        new CharacterData('1', oneData),
        new CharacterData('2', twoData),
        new CharacterData('3', threeData),
        new CharacterData('4', fourData),
        new CharacterData('5', fiveData),
        new CharacterData('6', sixData),
        new CharacterData('7', sevenData),
        new CharacterData('8', eightData),
        new CharacterData('9', nineData),
    };

    public void renderCharData(int[] arr, int x, int y) {
        for(int dy = 0; dy < LETTER_SIZE; dy++) {
            for(int dx = 0; dx < LETTER_SIZE; dx++) {
                if(arr[dy*LETTER_SIZE+dx] == 1) namalujBod(x+dx, y-dy);
            }
        }
    }

    public void renderChar(char c, int x, int y) {
        for (CharacterData letter : letters) {
            if(c == ' ') return;
            if (Character.toUpperCase(c) == letter.character) {
                renderCharData(letter.data, x, y);
                return;
            }
        }
       renderCharData(idk, x, y);
    };

    public void renderText(String s, int x, int y) {
        int yoffset = 0;
        int xoffset = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '\n') {
                xoffset = 0;
                yoffset -= LETTER_SIZE + 1;
                continue;
            }
            renderChar(s.charAt(i), x+xoffset, y+yoffset);
            xoffset += LETTER_SIZE + 1;
        }
    }
    
    @Override
    public boolean maluj(int idx) {
        renderText("budu hned zpet, jdu do chatky\njo a muj renderer umi radky\nABCDEFGHIJKLMNOPQRSTUVWXYZ.,\n123456789", -100, 10);
        return false;
    }

    public static void main(String[] args) {
        launch();
    }
}
