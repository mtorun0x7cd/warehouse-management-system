package wawi.fachlogik.kundesteuerung.service;

import java.util.List;
import wawi.fachlogik.kundesteuerung.grenzklassen.NachrichtGrenz;

public interface IKundeSteuerungNachricht {

    boolean sendeNachrichtAnSach(NachrichtGrenz n);

    List<NachrichtGrenz> nachrichtenAbrufen(int kid);

    boolean loescheNachricht(int nid);
    
    boolean setNachrichtGelesen(int nid);
}
