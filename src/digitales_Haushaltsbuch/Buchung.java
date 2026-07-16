package digitales_Haushaltsbuch;

import java.time.LocalDate;

public class Buchung {
	
	private String datum;
	private double betrag;
	private Typ typ;
	private Kategorie kategorie;
	private String beschreibung;

	
	
	public Buchung(String datum, double betrag, Typ typ, Kategorie kategorie, String beschreibung) throws Exception{
		if(datum.length() != 10 || datum.charAt(2) != '.' || datum.charAt(5) != '.' || !isDatum(datum)||
		getMonatFromDatum(datum) > 12 || getMonatFromDatum(datum) <= 0|| getTagFromDatum(datum) > 31 ||getTagFromDatum(datum) <= 0 || getJahrFromDatum(datum) > LocalDate.now().getYear() ||getJahrFromDatum(datum) < 2000) {
			throw new Exception("Datum ist falsch!");  
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
	
	public int getTagFromDatum(String datum) {
		String tag = datum.substring(0, 2);
		return Integer.parseInt(tag);
	  
	}

	public int getJahrFromDatum(String datum) {
		String jahr = datum.substring(6, 10);
		return Integer.parseInt(jahr);
	}

	@Override
	public String toString() {
		return "Buchung [datum=" + datum + ", betrag=" + betrag + ", typ=" + typ + ", kategorie=" + kategorie
				+ ", beschreibung=" + beschreibung + "]";
	}
	
	public boolean isDatum(String datum) {
		
		for(int i = 0; i< datum.length(); i++) {
			if(i == 2 || i ==5) {
				continue;
			}
			char ergebnis = datum.charAt(i);
			if(!Character.isDigit(ergebnis)) {
				return false;
			}
			
			
		}
		return true;
		
	}

	

}
