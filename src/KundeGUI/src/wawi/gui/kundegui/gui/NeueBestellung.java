package wawi.gui.kundegui.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import wawi.fachlogik.kundesteuerung.grenzklassen.BestellungGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.BestellungspositionGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.EnumBestellStatus;
import wawi.fachlogik.kundesteuerung.grenzklassen.KundeGrenz;
import wawi.fachlogik.kundesteuerung.grenzklassen.ProduktGrenz;
import wawi.fachlogik.kundesteuerung.impl.IActivateComponentImpl;
import wawi.fachlogik.kundesteuerung.impl.IKundeSteuerungBestellungImpl;
import wawi.fachlogik.kundesteuerung.service.IKundeSteuerungBestellung;

public class NeueBestellung extends javax.swing.JFrame {

    private FrameKunde parent;
    private KundeGrenz kunde;
    private IKundeSteuerungBestellung kundeSteuerungBestellung;

    private BestellungGrenz bGrenz;
    private List<BestellungspositionGrenz> bposGrenz;

    public NeueBestellung(FrameKunde parent) {
        initComponents();

        this.kunde = IActivateComponentImpl.kunde;
        try {
            kundeSteuerungBestellung = new IKundeSteuerungBestellungImpl();
        } catch (Exception ex) {
            Logger.getLogger(NeueBestellung.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.parent = parent;

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

                int result = JOptionPane.showConfirmDialog(frame, "Diesen Dialog wirklich schließen?", "Neue Bestellung", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    parent.changeNeueBestellungBtnStatus(true);
                    frame.dispose();
                }
            }
        });

        bGrenz = new BestellungGrenz();
        bGrenz.setBestellungspositionsList(new ArrayList<>());
        bposGrenz = bGrenz.getBestellungspositionsList();
        bGrenz.setKunde(kunde);

        // Erstelldatum setzen:
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        jTextField3.setText(dateFormat.format(date));
        bGrenz.setAngelegt(date);

        // Bestellstatus setzen:
        jTextField4.setText("n");
        bGrenz.setStatus(EnumBestellStatus.n);

        // gesamtNetto-/Brutto initialisieren
        jTextField5.setText("0.0");
        jTextField6.setText("0.0");

        // Aktive Produkte laden
        setProdukte();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(906, 720));
        setMinimumSize(new java.awt.Dimension(906, 720));
        setType(java.awt.Window.Type.UTILITY);

        jButton1.setText("Bestellung Anlegen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Abbrechen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Lieferadresse");

        jLabel2.setText("Rechnungsadresse");

        jLabel3.setText("Erstelldatum");

        jLabel4.setText("Status");

        jLabel5.setText("gesamt Netto");

        jLabel6.setText("gesamt Brutto");

        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField3.setEnabled(false);

        jTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField4.setEnabled(false);

        jTextField5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField5.setEnabled(false);

        jTextField6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField6.setEnabled(false);

        jLabel7.setText("Verfügbare Produkte");

        jLabel8.setText("Ausgewählte Produkte");

        jButton3.setText("Produkt hinzufügen");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Produkt löschen");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel9.setText("Anzahl:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Beschreibung", "angelegt", "Stueckzahl", "Nettopreis", "mwstsatz"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Beschreibung", "angelegt", "Stueckzahl", "Nettopreis", "mwstsatz"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(50, 50, 50)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addComponent(jLabel7)
                .addGap(259, 259, 259)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(136, 136, 136)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(349, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setProdukte() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable2.getModel();
        tableModel.setRowCount(0);

        for (ProduktGrenz p : kundeSteuerungBestellung.getAktiveProdukte()) {
            tableModel.addRow(new Object[]{
                p.getProdid(),
                p.getName(),
                p.getBeschreibung(),
                p.getAngelegt(),
                p.getStueckzahl(),
                p.getNettopreis(),
                p.getMwstsatz()
            });
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Wollen Sie den Vorgang wirklich abrechen?", "Neue Bestellung", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            parent.changeNeueBestellungBtnStatus(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String lieferadresse = jTextField1.getText();
        String rechnungsadresse = jTextField2.getText();
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher mLieferAdd = p.matcher(lieferadresse);
        Matcher mRechAdd = p.matcher(rechnungsadresse);
        
        /*
            RegEx = Regular Expressions:
            ----------------------------
            Pattern p sucht nach allen Zeichen die nicht in a-z bzw. 0-9 (nicht case-sensitive) enthalten sind
            d.h. alles was gefunden werden kann sind Sonderzeichen und bei mXX.find() == true ist die Adresse nicht gültig,
            da die Adresse mind. ein Sonderzeichen enthält!
        */
        
        if (lieferadresse.length() == 0 || mLieferAdd.find()) {
            JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine gültige Lieferadresse angegeben!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
        } else if (rechnungsadresse.length() == 0 || mRechAdd.find()) {
            JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine gültige Rechnungsadresse angegeben!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
        } else {
            bGrenz.setLieferadresse(lieferadresse);
            bGrenz.setRechnungsadresse(rechnungsadresse);

            boolean result = kundeSteuerungBestellung.neueBestellungAnlegen(bGrenz);

            if (result) {
                JOptionPane.showOptionDialog(null, "Erfolg: Die Bestellung wurde angelegt!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
                parent.changeNeueBestellungBtnStatus(true);
                this.dispose();
            } else {
                JOptionPane.showOptionDialog(null, "Fehler: Die Bestelung konnte nicht angelegt werden!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Hilfsfunktion, diese überprüft ob alle Zeichen in einer Zeichenkette Numerisch sind (z.B. in Produktanzahl)
    private boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel tableModelActive = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel tableModelInsert = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable2.getSelectedRow();
        
        if (selectedRow >= 0) {
            int prodId = Integer.parseInt(tableModelActive.getValueAt(selectedRow, 0).toString());
            ProduktGrenz p = kundeSteuerungBestellung.getProduktByID(prodId);

            String anzahlText = jTextField7.getText();
            if (!anzahlText.equals("") && isNumeric(anzahlText)) {
                int anzahl = Integer.parseInt(anzahlText);
                
                if (anzahl <= 0) {
                    JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine gültige Anzahl angegeben!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
                } else if (anzahl > p.getStueckzahl()) {
                    JOptionPane.showOptionDialog(null, "Fehler: Sie können nicht mehr Auswählen als Verfuegbar (anzahl > stueckzahl)!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
                } else {
                    boolean prodFound = false;
                    for (int i = 0; i < tableModelInsert.getRowCount(); i++) {
                        int idInsert = Integer.valueOf(tableModelInsert.getValueAt(i, 0).toString());

                        if (idInsert == p.getProdid()) {
                            int stueckzahl_ausgewaehlt = Integer.valueOf(tableModelInsert.getValueAt(i, 4).toString());

                            if ((stueckzahl_ausgewaehlt + anzahl) > p.getStueckzahl()) {
                                JOptionPane.showOptionDialog(null, "Fehler: Ihre Anzahl ist zu groß und übersteigt die Stückzahl!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
                                return;
                            } else {
                                tableModelInsert.setValueAt((stueckzahl_ausgewaehlt + anzahl), i, 4);
                                prodFound = true;
                            }

                            for (BestellungspositionGrenz grenzItem : bposGrenz) {
                                if (Objects.equals(grenzItem.getProdukt().getProdid(), p.getProdid())) {
                                    grenzItem.setAnzahl(anzahl + stueckzahl_ausgewaehlt);
                                }
                            }
                        }
                    }

                    if (!prodFound) {
                        tableModelInsert.addRow(
                                new Object[]{
                                    p.getProdid(),
                                    p.getName(),
                                    p.getBeschreibung(),
                                    p.getAngelegt(),
                                    anzahl,
                                    p.getNettopreis(),
                                    p.getMwstsatz()
                                }
                        );

                        bposGrenz.add(new BestellungspositionGrenz(null, anzahl, p));
                    }

                    kundeSteuerungBestellung.berechneGesamt(bGrenz);
                    jTextField5.setText(String.format("%f", bGrenz.getGesamtNetto()));
                    jTextField6.setText(String.format("%f", bGrenz.getGesamtBrutto()));
                }
            } else {
                JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine gültige Anzahl angegeben!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
            }
        } else {
            JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine Zeile ausgewählt!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Produkt löschen
        DefaultTableModel tableModelInsert = (DefaultTableModel) jTable1.getModel();

        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow >= 0) {
            int prod_id = Integer.valueOf(tableModelInsert.getValueAt(selectedRow, 0).toString());
            int stueckzahl = Integer.valueOf(tableModelInsert.getValueAt(selectedRow, 4).toString());

            String anzahlText = jTextField7.getText();
            if (!anzahlText.equals("") && isNumeric(anzahlText)) {
                int anzahl = Integer.parseInt(anzahlText);

                if (anzahl <= 0) {
                    JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine gültige Anzahl angegeben!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
                    return;
                } else if (anzahl > stueckzahl) {
                    JOptionPane.showOptionDialog(null, "Fehler: Sie können nicht mehr Auswählen als Verfuegbar (anzahl > stueckzahl)!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
                    return;
                } else if ((stueckzahl - anzahl) == 0) { // remove row
                    int remIdx = -1;
                    for (BestellungspositionGrenz grenzItem : bposGrenz) {
                        if (prod_id != grenzItem.getProdukt().getProdid()) {
                            remIdx++;
                        }
                    }

                    if (remIdx >= 0) {
                        bposGrenz.remove(remIdx);
                    }

                    tableModelInsert.removeRow(selectedRow);
                } else { // decrement count
                    int neue_steuckzahl = stueckzahl - anzahl;

                    for (BestellungspositionGrenz grenzItem : bposGrenz) {
                        if (prod_id == grenzItem.getProdukt().getProdid()) {
                            grenzItem.setAnzahl(neue_steuckzahl);
                        }
                    }

                    tableModelInsert.setValueAt(neue_steuckzahl, selectedRow, 4);
                }

                kundeSteuerungBestellung.berechneGesamt(bGrenz);
                jTextField5.setText(String.format("%f", bGrenz.getGesamtNetto()));
                jTextField6.setText(String.format("%f", bGrenz.getGesamtBrutto()));
            } else {
                JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine gültige Anzahl angegeben!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
            }
        } else {
            JOptionPane.showOptionDialog(null, "Fehler: Sie haben keine Zeile ausgewählt!", "Neue Bestellung", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"OK"}, "OK");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
