package logistics.surgerylogistics;

import java.util.HashMap;
import java.util.Map;

import java.text.SimpleDateFormat; 
import java.util.Date;
import java.io.*;
/**
 * Beschreiben Sie hier die Klasse Opertaion.
 * 
 * @author Maximilian Hertle und Ronny Volm 
 * @version 02.10.2020
 */
public class Operation
{
    private final Date datum;
    private String operationstyp;
    private String arzt;
    private String ota;
    private String patient;
    private Checkliste checkliste;

    /**
     * Spezieller Konstruktor für Objekte der Klasse Operation
     */
    public Operation(String Operationstyp, String Arzt, String Ota, String Patient) throws Exception
    {
        datum = new Date();
        setArzt(Arzt);
        setOTA(Ota);
        setPatient(Patient);
        setOperationstyp (Operationstyp);
        // if(Operationstyp.equals("") == false)
        // {operationstyp = Operationstyp;}
        // else{arzt = "nicht ausgewählt";}
        // checkliste = new Checkliste (Operationstyp);
    }

    /**
     * Standartkonstruktor für Objekte der Klasse Operation
     *
     */
    public Operation() throws Exception
    {
        datum = new Date();
        arzt = "Dr. Schmid";
        ota = "G.Maier";
        patient = "Müller";
        operationstyp = "Bypass";
        checkliste = new Checkliste (operationstyp);
    }

    /**
     * Dient zur Übermittlung von einer Sammlung von Daten an die Klasse Checkliste. Übermittelt werden die Anzahlen der verbrauchten Produkte. 
     *
     * @param Verbrauchsliste Liste mit Produktnamen und Verbrauchsanzahl
     */
    public void ChecklisteBearbeiten(HashMap<String, Integer> Verbrauchsliste) throws Exception
    {
        checkliste.ChecklisteBearbeiten(Verbrauchsliste);
    }

    /**
     * Holt alle Einträge der Checkliste mit passender Anzahl und gibt diese weiter.
     *
     * @return Eintragsliste mit Produktnamen und Anzahl
     */
    public HashMap<String, Integer> getCheckliste()
    {
        return checkliste.getEintraege();
    }

    /**
     * Speichert die gesamten Daten dieser inklusive Checklisteneinträge in einer Wortdatei (extern) ab.
     * (Zusatzauftage von Maximilian Hertle Teile 3/3)
     * <br> *optional*
     *
     */
    public void speicherDaten()
    {
        SimpleDateFormat Format1 = new SimpleDateFormat( 
                "yyyyMMdd");
        String datumformat1 = Format1.format(datum);
        File ordner = new File("Operationen/" + datumformat1);
        if(ordner.exists() == false){
            ordner.mkdirs();
        }  

        SimpleDateFormat Format2 = new SimpleDateFormat( 
                "yyyyMMdd-hhmm");
        String datumformat2 = Format2.format(datum);
        File datei = new File("Operationen/"+ datumformat1 + "/" + datumformat2 + "_" + patient + ".txt");

        if(datei.exists() == false){
            SimpleDateFormat Format3 = new SimpleDateFormat( 
                    "dd.MM.yyyy - hh:mm");
            String datumformat3 = Format3.format(datum);
            try{
                datei.createNewFile();
                FileWriter fw = new FileWriter(datei);
                fw.write("Datum: " + datumformat3 + "\n\n");
                fw.write("Patientenname: " + patient + "\n");
                fw.write("dokumentierende OTA: " + ota + "\n");
                fw.write("durchführender Arzt: " + arzt + "\n\n");
                fw.write("Operation: " + operationstyp + "\n\n");
                fw.write("Verbrauchsliste:\n");
                HashMap<String, Integer> liste = checkliste.getEintraege();
                for (Map.Entry <String, Integer> entry : liste.entrySet()) 
                { 
                    fw.write("    " + entry.getValue() + "     " + entry.getKey() + "\n"); 
                }
                fw.close();
            } catch (IOException e){
                e.printStackTrace(); 
            }
        }
    }

    /**
     * Ändert den Namen des Arztes.
     *
     * @param Arzt Arztname
     */
    public void setArzt(String Arzt)
    {
        if(Arzt.equals("") == false)
        {arzt = Arzt;}
    }

    /**
     * Gibt den Name des Arztes zurück.
     *
     * @return Arztname
     */
    public String getArzt()
    {
        return arzt;
    }

    /**
     * Ändert den Name der Operationstechnischenassistenten.
     *
     * @param OTA OTA-Mitarbeitername
     */
    public void setOTA(String OTA)
    {
        if(OTA.equals("") == false)
        {ota = OTA;}
    }

    /**
     * Gibt den Name des Operationstechnischenassistenten zurück.
     *
     * @return OTA-Mitarbeitername
     */
    public String getOTA()
    {
        return ota;
    }

    /**
     * Gibt den Operationstyp zurück.
     *
     * @return Operationstyp
     */
    public String getOperationstyp()
    {
        return operationstyp;
    }

    /**
     * Ändert den Operationstyp und die passende Checkliste.
     *
     * @param Operationstyp Operationstyp
     */
    public void setOperationstyp (String Operationstyp) throws Exception
    {
        if(Operationstyp.equals("") == false)
        {
            try{
                checkliste = new Checkliste (Operationstyp);
                operationstyp = Operationstyp;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    // /**
    // * Ändert das Datum.
    // *
    // * @param Datum Datum
    // */
    // public void setDatum(String Datum)   // 
    // {
    // datum = Datum;
    // }

    /**
     * Gibt das Datum aus.
     *
     * @return Datum
     */
    public Date getDatum()
    {
        return datum;
    }

    /**
     * Ändert den Patientenname.
     *
     * @param Patient Patientenname
     */
    public void setPatient(String Patient) 
    {
        if(Patient.equals("") == false)
        {patient = Patient;}
    }

    /**
     * Gibt den Patientenname.
     *
     * @return Patientenname
     */
    public String getPatient()
    {
        return patient;
    }
}