package wawi.gui.bootloader.steuer;

import wawi.gui.bootloader.grenz.LoginGUIAttributen;
import wawi.fachlogik.componentcontroller.service.IActivateComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import wawi.gui.admingui.gui.FrameAdmin;
import wawi.gui.kundegui.gui.FrameKunde;
import wawi.gui.lagerhaltergui.gui.FrameLagerhalter;
import wawi.gui.sachbearbeitergui.gui.FrameSachbearbeiter;

public class LoginVerwaltung {

    private List<IActivateComponent> actList;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("wawi/gui/bootloader/gui/Bundle");
    private final String ADM = bundle.getString("ADM");
    private final String LGR = bundle.getString("MNG");
    private final String CLK = bundle.getString("CLK");
    private final String CUS = bundle.getString("CUS");

    private FrameAdmin frameAdmin;
    private FrameKunde frameKunde;
    private FrameSachbearbeiter frameSachbearbeiter;
    private FrameLagerhalter frameLagerhalter;
    
    public LoginVerwaltung() {
        actList = new ArrayList<IActivateComponent>(6);
        checkComponentsAvailability();
    }

    public boolean doLogin(LoginGUIAttributen attributen) {
        boolean ret = false;
        IActivateComponent act = actList.get(getRollIndex(attributen.getRolle()));
        if (act != null) {
            if (act.activateComponent(attributen.getUserid())) {
                //act.getComponentGUI().setVisible(true);
                // TODO alle
                switch(act.getComponentType())
                {
                    case ADMIN:
                        frameAdmin = new FrameAdmin();
                        frameAdmin.setVisible(true);
                        break;
                        
                    case KUNDE:
                        frameKunde = new FrameKunde();
                        frameKunde.setVisible(true);
                        break;
                        
                    case SACHBEARBEITER:
                        frameSachbearbeiter = new FrameSachbearbeiter();
                        frameSachbearbeiter.setVisible(true);
                        break;
                        
                    case LAGERHALTER:
                        frameLagerhalter = new FrameLagerhalter();
                        frameLagerhalter.setVisible(true);
                        break;
                        
                }
                ret = true;
            }
        }
        return ret;
    }

    public boolean doLogout(LoginGUIAttributen attributen) {
        boolean ret = false;
        IActivateComponent act = actList.get(getRollIndex(attributen.getRolle()));
        if (act != null) {
            if (act.deactivateComponent()) {
                switch(act.getComponentType())
                {
                    case ADMIN:
                        frameAdmin.dispose();
                        break;
                        
                    case KUNDE:
                        frameKunde.dispose();
                        break;
                        
                    case SACHBEARBEITER:
                        frameSachbearbeiter.dispose();
                        break;
                        
                    case LAGERHALTER:
                        frameLagerhalter.dispose();
                        break;
                        
                }
                
                ret = true;
            }
        }
        return ret;
    }

    private int getRollIndex(String rolle) {
        int ret = -1;
        if (rolle.equals(ADM)) {
            ret = 0;
        } else if (rolle.equals(LGR)) {
            ret = 1;
        } else if (rolle.equals(CLK)) {
            ret = 2;
        } else if (rolle.equals(CUS)) {
            ret = 3;
        }
        return ret;
    }

    private void checkComponentsAvailability() {
        actList.add(new wawi.fachlogik.adminsteuerung.impl.IActivateComponentImpl());
        actList.add(new wawi.fachlogik.lagerhaltersteuerung.impl.IActivateComponentImpl());
        actList.add(new wawi.fachlogik.sachbearbeitersteuerung.impl.IActivateComponentImpl());
        actList.add(new wawi.fachlogik.kundesteuerung.impl.IActivateComponentImpl());
    }
}
