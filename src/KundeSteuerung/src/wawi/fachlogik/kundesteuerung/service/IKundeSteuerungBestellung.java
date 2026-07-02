package wawi.fachlogik.kundesteuerung.service;

import java.util.List;
import wawi.fachlogik.kundesteuerung.grenzklassen.BestellungGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.ProduktGrenz;

public interface IKundeSteuerungBestellung {

    List<ProduktGrenz> getAktiveProdukte();

    ProduktGrenz getProduktByID(int pid);

    void berechneGesamt(BestellungGrenz b);

    boolean neueBestellungAnlegen(BestellungGrenz b);

    List<BestellungGrenz> getBestellungListe(int kid);

    boolean storniereBestellung(int bid);
}
