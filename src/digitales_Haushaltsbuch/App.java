package digitales_Haushaltsbuch;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		start();
		 
	}
	
	
	public static void start() {
		HaushaltsbuchVerwaltung haushaltsbuchVerwaltung = new HaushaltsbuchVerwaltung();
		Scanner myObj = new Scanner(System.in);
		
		punkteNennen();
		
		int userAnfrage = myObj.nextInt();  // Read user input
		myObj.nextLine();
		while(userAnfrage != 7) {
			int nummer;
			switch(userAnfrage) {
				case(1):
					try {
					System.out.print("Datum eingeben: ");
					String datum  = myObj.nextLine();
					
					System.out.print("Betrag eingeben: ");
					Double betrag = myObj.nextDouble();
					myObj.nextLine();
					
					System.out.print("Typ eingeben (EINNAHME/AUSGABE): ");
					Typ typ = Typ.valueOf(myObj.nextLine());
					
					System.out.print("Kategorie eingeben: (WOHNEN/LEBENSMITTEL/TRANSPORT/FREIZEIT/GEHALT/SONSTIGES): ");
					Kategorie kategorie = Kategorie.valueOf(myObj.nextLine());
					
					System.out.print("Beschreibung Eingeben: ");
					String beschreibung = myObj.nextLine();
					
					Buchung buchung = new Buchung(datum,betrag,typ,kategorie,beschreibung);
					haushaltsbuchVerwaltung.buchungAnlegen(buchung);
					
					} catch(Exception e) {
						System.out.println("Fehler!");
					}
					break;
					
				case(2):
					haushaltsbuchVerwaltung.alleBuchungenAnzeigen();
					
					break;
				case(3):
					haushaltsbuchVerwaltung.alleBuchungenAnzeigen();
					System.out.println("Welche Buchung wollen Sie Ändern? (Nummer):  ");
					nummer = myObj.nextInt();
					myObj.nextLine();
					try {
						System.out.print("Datum eingeben: ");
						String datum  = myObj.nextLine();
						
						System.out.print("Betrag eingeben: ");
						Double betrag = myObj.nextDouble();
						myObj.nextLine();
						
						System.out.print("Typ eingeben (EINNAHME/AUSGABE): ");
						Typ typ = Typ.valueOf(myObj.nextLine());
						
						System.out.print("Kategorie eingeben: (WOHNEN/LEBENSMITTEL/TRANSPORT/FREIZEIT/GEHALT/SONSTIGES): ");
						Kategorie kategorie = Kategorie.valueOf(myObj.nextLine());
						
						System.out.print("Beschreibung Eingeben: ");
						String beschreibung = myObj.nextLine();
						
						Buchung buchung = new Buchung(datum,betrag,typ,kategorie,beschreibung);
						haushaltsbuchVerwaltung.buchungBearbeiten(nummer, buchung);
						
						} catch(Exception e) {
							System.out.println("Fehler!");
						}
						break;	
					
				case(4):
					haushaltsbuchVerwaltung.alleBuchungenAnzeigen();
					System.out.println("Welche Buchung soll gelöscht werden(Nummer): ");
					nummer = myObj.nextInt();
					myObj.nextLine();
					haushaltsbuchVerwaltung.buchungLöschen(nummer);
					
					break;
				case(5):
					System.out.print("Welcher Monat? (in Format MM)");
					nummer = myObj.nextInt();
					haushaltsbuchVerwaltung.monatsAuswertung(nummer);
					break;
				case(6):
					haushaltsbuchVerwaltung.kontostand();
					
					break;	
				default: 
					System.out.println("Ungültige Zahl");
			}
			punkteNennen();
			userAnfrage = myObj.nextInt();
			myObj.nextLine();
		}
		haushaltsbuchVerwaltung.alleBuchungenSpeichern();
		System.out.println("beendet");
	}
	
	public static void punkteNennen() {
		System.out.println("1: Buchung Anlegen");
		System.out.println("2: Alle Buchungen Anzeigen");
		System.out.println("3: Buchung Bearbeiten");
		System.out.println("4: Buchung löschen");
		System.out.println("5: Monats Auswertung");
		System.out.println("6: Kontostand");
		System.out.println("7: Programm beenden");
		System.out.println("Was wollen Sie tun?");
	}

}
