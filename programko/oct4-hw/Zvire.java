public class Zvire {
	protected int pocetNohou;
	protected String citoslovce;

	public String info() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Druh: ").append(this.getClass().getName()).append("\n")
		.append("Poddruh: ").append(super.getClass().getSuperclass().getName()).append("\n")
		.append("Pocet nohou: ").append(this.pocetNohou).append("\n")
		.append("Dela zvuk: ").append(this.citoslovce).append("\n");

		return stringBuilder.toString();
	}

	public String getCitoslovce() {
		return this.citoslovce;
	}
	public int getPocetNohou() {
		return this.pocetNohou;
	}
}