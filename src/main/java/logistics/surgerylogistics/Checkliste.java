package logistics.surgerylogistics;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;

import java.io.FileInputStream;
import java.io.*;
import java.lang.Exception;
/**
 * Beschreiben Sie hier die Klasse Checkliste.
 * 
 * @author Maximilian Hertle und Ronny Volm 
 * @version 09.10.2020
 */
public class Checkliste
{
    private final String nameliste;
    private HashMap<String, Integer> eintraege;

    /**
     * Spezieller Konstruktor für Objekte der Klasse Checkliste
     */
    public Checkliste(String NameListe) throws Exception
    {
        nameliste = NameListe;
        eintraege = new HashMap<String, Integer>();
        ChecklistenpunkteHinzufuegen();
        test();
    }

    /**
     * Standardkonstruktor für Objekte der Klasse Checkliste
     */
    public Checkliste() throws Exception
    {
        nameliste = "CL";
        eintraege = new HashMap<String, Integer>();
        ChecklistenpunkteHinzufuegen();
        test();
    }

    /**
     * Methode ChecklistenpunkteHinzufuegen (Zusatzauftage von Maximilian Hertle Teile 2/3)
     *
     */
    private void ChecklistenpunkteHinzufuegen() throws Exception
    { 
        XSSFWorkbook datei = null;
        try
        {
            datei = new XSSFWorkbook(new FileInputStream("Produktliste1.xlsx"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("hier");
            throw new Exception("dort");
        }

        try{
            Sheet blatt = datei.getSheetAt(0);

            Row zeile = blatt.getRow(1);
            Cell feld;

            int i = 9;
            int index = 0;
            boolean operationgefunden = false;
            while(i < zeile.getLastCellNum()) //operationgefunden == false || 
            {
                if(zeile.getCell(i) != null && nameliste.equals(zeile.getCell(i).getStringCellValue()))
                {
                    operationgefunden = true;
                    index = i;
                }
                i++;
            }   

            if(operationgefunden == true)
            {
                i = 4;
                zeile = blatt.getRow(i);
                Cell feldProduktart;
                Cell feldAnzahl;

                while(blatt.getLastRowNum() >= i) //feldProduktart.getCellType() != CellType.BLANK)
                {
                    if(zeile != null)
                    {
                        feldProduktart = zeile.getCell(1);
                        feldAnzahl = zeile.getCell(index);
                        if(feldAnzahl != null) // && feldAnzahl.getCellType() != CellType.BLANK)
                        {
                            String s = feldProduktart.getStringCellValue();
                            int f = (int)feldAnzahl.getNumericCellValue();
                            eintraege.put(s, f);
                        }
                    }
                    i++;
                    zeile = blatt.getRow(i);
                }
            }
            else
            {
                //Mitteilung
            }
        }catch(Exception e)
        {
            throw new Exception("Fehler beim Einlesen der Checkliste!");
        }
        finally{
            try{
                datei.close();
            }catch(Exception e){
                throw new Exception("Datei kann nicht geschlossen werden!");
            }
        }
    }

    private void test ()
    {

        for (Map.Entry <String, Integer> entry : eintraege.entrySet()) 
        { 
            System.out.println("x");
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
    }

    /**
     * Ändert die Wert der einzelnen Einträge auf den jeweiligen Verbrauchswert.
     *
     * @param Verbrauchsliste Liste mit Produktnamen und Verbrauchsanzahl
     */
    public void ChecklisteBearbeiten (HashMap<String, Integer> Verbrauchsliste)
    {
        for (Map.Entry <String, Integer> entry : Verbrauchsliste.entrySet()) 
        { 
            eintraege.put(entry.getKey(), entry.getValue()); 
        }
    }

    //Set-Methode setNameliste()
    //Änderung des Checklistenname ist nach einmaliger Eingabe zu Beginn nicht mehr möglich.

    /**
     * Gibt den Namen der Checkliste zurück.
     *
     * @return Checklistenname
     */
    public String getNameliste()
    {
        return nameliste; 
    }

    /**
     * Gibt alle Einträge zurück.
     *
     * @return Einträge
     */
    public HashMap<String, Integer> getEintraege()
    {
        return eintraege;   
    }

    /**
     * Setzt alle Einträge.
     *
     * @param Einträge
     */
    public void setEintraege(HashMap Eintraege)
    {
        eintraege = Eintraege;
    }
}
