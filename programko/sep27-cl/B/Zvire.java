package B;

public abstract class Zvire {
    protected String size;
    protected int runSpeed;

    public abstract String delaZvuk();

    static private final String nameInfo = "Jsem ";
    static private final String soundInfo = "Ja delam zvuk: ";
    static private final String sizeInfo = "Muj rozmer je: ";
    static private final String speedInfo = "Moje rychlost behu je: ";

    public void oMne() {
        String name = this.getClass().getSimpleName();
        String sound = this.delaZvuk();
        String speed = Integer.toString(runSpeed);
        
        int maxLength = Math.max(
            Math.max(
            name.length() + nameInfo.length(), 
            sound.length() + soundInfo.length()
            ),
            Math.max(
            size.length() + sizeInfo.length(),
            speed.length() + speedInfo.length()
            )
        );

        String header = "*".repeat(maxLength + 4);

        System.out.println(String.format(
            "%s\n"+
            "* %s%-"+Integer.toString(maxLength - (nameInfo.length()))+"s *\n"+
            "* %s%-"+Integer.toString(maxLength - (soundInfo.length()))+"s *\n"+
            "* %s%-"+Integer.toString(maxLength - (sizeInfo.length()))+"s *\n"+
            "* %s%-"+Integer.toString(maxLength - (speedInfo.length()))+"s *\n"+
            "%s\n",
            header,
            nameInfo, this.getClass().getSimpleName(),
            soundInfo, this.delaZvuk(),
            sizeInfo, this.size,
            speedInfo, speed,
            header
        ));
    }
}
