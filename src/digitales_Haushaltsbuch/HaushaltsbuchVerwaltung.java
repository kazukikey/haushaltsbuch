package digitales_Haushaltsbuch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HaushaltsbuchVerwaltung {
	public List<Buchung> alleBuchungen = new ArrayList<>();

	public HaushaltsbuchVerwaltung() {
		alleBuchungenLaden();
	}

	public void buchungAnlegen(Buchung buchung) {
		alleBuchungen.add(buchung);
		System.out.println("Buchung angelegt");
	}

	public void alleBuchungenAnzeigen() {
		
		
		System.out.println("Nr. | Datum      | Betrag   | Typ       | Kategorie    | Beschreibung");
		System.out.println("----|------------|----------|-----------|--------------|-------------");
		for (int i = 0; i < alleBuchungen.size(); i++) {
			System.out.format("%3d | %10s | %7.2f€ | %9s | %12s | %s%n", i + 1, alleBuchungen.get(i).getDatum(),
					alleBuchungen.get(i).getBetrag(), alleBuchungen.get(i).getTyp(),
					alleBuchungen.get(i).getKategorie(), alleBuchungen.get(i).getBeschreibung());
		}
	}

	public void buchungLöschen(int nummer) {
		if (nummer < 1 || nummer > alleBuchungen.size()) {
			System.out.println("Fehler"); 
		} else {
			alleBuchungen.remove((nummer - 1));		
			System.out.println("Buchung gelöscht");
		}
	}

	public void buchungBearbeiten(int nummer, Buchung buchung) {
		
		if (nummer < 1 || nummer > alleBuchungen.size()) {
			System.out.println("Fehler"); 
		} else {
			alleBuchungen.set((nummer - 1), buchung);
			System.out.println("Buchung bearbeitet");

		}
	}

	public void kontostand() {
		double kontostand = 0;

		for (int i = 0; i < alleBuchungen.size(); i++) {
			double betrag = alleBuchungen.get(i).getBetrag();
			if (alleBuchungen.get(i).getTyp() == Typ.EINNAHME) {
				kontostand += betrag;
			} else {
				kontostand -= betrag;
			}
		}
		System.out.format("Kontostand: %.2f€\n", kontostand);
	}

	public void alleBuchungenSpeichern() {
		


		try (BufferedWriter bw = new BufferedWriter(new FileWriter("buchungen.csv"))) {
			for (int i = 0; i < alleBuchungen.size(); i++) {
				bw.write(alleBuchungen.get(i).getDatum() + "," + alleBuchungen.get(i).getBetrag() + ","
						+ alleBuchungen.get(i).getTyp() + "," + alleBuchungen.get(i).getKategorie() + ","
						+ alleBuchungen.get(i).getBeschreibung());
				
					bw.newLine();
			}
			System.out.println("Buchungen erfolgreich gespeichert\n");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fehler");
		}
	}

	public void alleBuchungenLaden() {
		try (BufferedReader r = new BufferedReader(new FileReader("buchungen.csv"))) {
			System.out.println("Laden startet...");

			
			String line;
			while((line = r.readLine()) != null) {	
				String[] p = line.split(",");
				
				String datum = p[0];
				Double betrag = Double.parseDouble(p[1]);
				Typ typ = Typ.valueOf(p[2]);
				Kategorie kategorie = Kategorie.valueOf(p[3]);
				String beschreibung = p[4];
				
				Buchung buchung = new Buchung(datum,betrag,typ,kategorie,beschreibung);
				alleBuchungen.add(buchung);
				
			}
			System.out.print("Buchung erfolgreich geladen\n");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fehler");
	}
		}
	public void monatsAuswertung(int monat) {
		double einnahmen = 0;
		double ausgaben = 0;
		
		for(int i = 0; i< alleBuchungen.size();i++) {
			int monatGesucht = alleBuchungen.get(i).getMonatFromDatum(alleBuchungen.get(i).getDatum());
			if(monatGesucht == monat) {
				if(alleBuchungen.get(i).getTyp() == Typ.EINNAHME) {
					einnahmen += alleBuchungen.get(i).getBetrag();
				}else {
					ausgaben += alleBuchungen.get(i).getBetrag();

				}
			}
		}
		if(einnahmen == 0 && ausgaben == 0) {
			System.out.println("Keine Buchung im monat " + monat);
		}
		else {
		double saldo = einnahmen - ausgaben;
		System.out.format("Deine Einnahmen für den Monat %d ist: %.2f€\n",monat,einnahmen );
		System.out.format("Deine Ausgaben für den Monat %d ist: %.2f€\n",monat,ausgaben );
		System.out.format("Dein Saldo für den Monat %d ist: %.2f€\n",monat,saldo );
		}
			
	}
}