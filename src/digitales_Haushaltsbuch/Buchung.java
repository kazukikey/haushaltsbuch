package digitales_Haushaltsbuch;

public class Buchung {
	
	private String datum;
	private double betrag;
	private Typ typ;
	private Kategorie kategorie;
	private String beschreibung;

	public Buchung(String datum, double betrag, Typ typ, Kategorie kategorie, String beschreibung) throws Exception{
		if((datum.length() != 10 || datum.charAt(2) != '.') || datum.charAt(5) != '.') {
			throw new Exception("Datum hat falsches Format!"); 
		}
					
				this.datum = datum;
				this.betrag = betrag;
				this.typ = typ;
				this.kategorie = kategorie;
				this.beschreibung = beschreibung;
						
		
	}

	public String getDatum() {
		return datum;
	}

	public double getBetrag() {
		return betrag;
	}

	public Typ getTyp() {
		return typ;
	}

	public Kategorie getKategorie() {
		return kategorie;
	}

	public String getBeschreibung() {
		return beschreibung;
	}
	
	public int getMonatFromDatum(String datum) {
		String monat = datum.substring(3, 5);
		return Integer.parseInt(monat);
	  
	}


	@Override
	public String toString() {
		return "Buchung [datum=" + datum + ", betrag=" + betrag + ", typ=" + typ + ", kategorie=" + kategorie
				+ ", beschreibung=" + beschreibung + "]";
	}

	

}
