import java.util.HashMap;
/**
 * Beschreiben Sie hier die Klasse Anwendungsklasse.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Anwendungsklasse
{
    private Lager lager;

    public Anwendungsklasse()
    {
        try{
            lager = new Lager();
        }catch(Exception e)
        {}
    }

    public void neueOperation(String Operationstyp, String Arzt, String OTA, String Patient)
    {
        try{
            lager.AnlegenOperation(Operationstyp,  Arzt, OTA, Patient); 
        }catch(Exception e)
        {}
    }

    public void verbrauchtenProdukteAngeben(String [] produkte, int [] anzahlen)
    {
        HashMap <String , Integer> liste = new HashMap <String , Integer>();

        for(int i=0; i<produkte.length; i++)
        {
            liste.put(produkte[i], anzahlen[i]);   
        }

        try{
            lager.ChecklisteBearbeiten(liste);
        }catch(Exception e)
        {}
    }

    public void Lagerbestand()
    {
        System.out.println(lager.anzeigeBestandsliste());  
    }

    public void Bestellungen()
    {

    }

    public void LagerAktualisieren()
    {

    }

}
