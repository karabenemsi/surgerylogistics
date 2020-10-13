package logistics.surgerylogistics;


/**
 * DV-Projekt: OP-Materialcheckliste
 * Produktarten, die in den Checklisten vorkommen.
 * @author (Leonie Schmid, Meike Hotz)
 * @version (4.11.0) Eclipse IDE for Java Developers
 */

public class Produktart 
{

    private String Name;
    private String ID;
    private int aktBestand;
    private int minBestand;
    private int maxBestand;

    /**
     * Konstruktor mit Parameter	
     * @param Name
     * @param ID
     * @param aktBestand aktueller Bestand
     * @param minBestand Mindestbestand
     * @param maxBestand Maximalbestand
     */

    public Produktart(String Name, String ID, int minBestand, int maxBestand) 
    {
        this.Name = Name;
        this.ID = ID;
        aktBestand = maxBestand;
        this.minBestand = minBestand;
        this.maxBestand = maxBestand;
    }

    /**
     * Standardkonstruktor
     */

    public Produktart()
    {
        Name = "Kanuele";
        ID = "Kan_07";
        aktBestand = 60;
        minBestand = 40;
        maxBestand = 100;
    }

    /**
     * Get-Methode für Name	> Gibt Name zurück
     * @return Name
     */
    public String getName() 
    {
        return Name;
    }

    /**
     * Set-Methode für Name
     * @param name
     */

    public void setName(String name) 
    {
        Name = name;
    }

    /**
     * Get-Methode für ID > Gibt ID zurück
     * @return ID
     */

    public String getID() 
    {
        return ID;
    }

    /**
     * Set-Methode für ID
     * @param iD
     */

    public void setID(String iD) 
    {
        ID = iD;
    }

    /**
     * Get-Methode für aktBestand > Gibt aktuellen Bestand zurück	
     * @return aktBestand aktueller Bestand
     */

    public int getAktBestand() 
    {
        return aktBestand;
    }

    /**
     * Set-Methode für aktBestand 	
     * @param aktBestand aktueller Bestand
     */

    public void setAktBestand(int aktBestand) 
    {
        this.aktBestand = aktBestand;
    }

    /**
     * Get-Methode für minBestand > Gibt Mindestbestand zurück	
     * @return minBestand Mindestbestand
     */

    public int getMinBestand() 
    {
        return minBestand;
    }

    /**
     * Set-Methode für minBestand
     * @param minBestand Mindestbestand
     */

    public void setMinBestand(int minBestand) 
    {
        this.minBestand = minBestand;
    }

    /**
     * Get-Methode für maxBestand > Gibt Maximalbestand zurück	
     * @return maxBestand Maximalbestand
     */

    public int getMaxBestand() 
    {
        return maxBestand;
    }

    /**
     * Set-Methode für maxBestand	
     * @param maxBestand Maximalbestand
     */

    public void setMaxBestand(int maxBestand) 
    {
        this.maxBestand = maxBestand;
    }

    /**
     * Methode pruefeBestandVorOp() 
     * @param Anzahl
     * @return Wahrheitswert, ob Anzahl verfügbar
     * Funktion: Prüft, ob die für die Operation notwendigen Produkte verfügbar sind.	
     */	

    public boolean pruefeBestandVorOp (int Anzahl)
    {
        if(aktBestand - Anzahl >= 0)
        {
            return true; // Die geforderte Anzahl an Produkten ist verfügbar.
        }
        else
        {
            return false; // Die geforderte Anzahl an Produkten ist nicht verfügbar. 
        }
    }

    /**
     * Methode verringereBestand() 
     * @param Anzahl
     * Funktion: Der Bestand wird um die Anzahl verringert. 	
     */

    public void verringereBestand(int Anzahl)
    {
        int verringerterBestand = aktBestand - Anzahl;

        if (verringerterBestand < 0)
        {
            throw new IllegalArgumentException ("Achtung, der Bestand liegt unter null."); 
        }

        this.aktBestand = verringerterBestand;

        try
        {
            System.out.println (verringerterBestand);
        }

        catch (IllegalArgumentException e)
        {
            System.out.println("Fehler");
        }
    }

    /**
     * Methode erhoeheBestand() 
     * @param Anzahl
     * Funktion: Der Bestand wird um die Anzahl erhöht. 	
     */

    public void erhoeheBestand(int Anzahl)
    {
        int erhoehterBestand = aktBestand + Anzahl;

        if (erhoehterBestand > maxBestand)
        {
            throw new IllegalArgumentException ("Achtung, der maximale Bestand wurde überschritten.");

        }

        this.aktBestand = erhoehterBestand;

        try
        {
            System.out.println (erhoehterBestand);
        }

        catch (IllegalArgumentException e)
        {
            aktBestand = maxBestand;
            System.out.println("Der aktuelle Bestand entspricht dem Maximalbestand:" + aktBestand);
        }

    }

    /**
     * Methode pruefeUnterschreitung() 
     * @return Wahrheitswert, ob Mindestbestand unterschritten
     * Funktion: Es wird geprüft, ob der aktuelle Bestand unter dem Mindestbestand liegt.
     */	

    // Methode erst NACH OP

    public boolean pruefeUnterschreitung()
    {
        if(aktBestand < minBestand)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Methode berechneNachbestellmenge()
     * @return Nachbestellmenge
     * Funktion: Berechnet die Produktmenge, die nachbestellt werden muss.	
     */	

    public int berechneNachbestellmenge()
    {
        int Nachbestellmenge = maxBestand - aktBestand;

        return Nachbestellmenge;
    }

    /**
     * Methode AusgabeBestandProduktart()
     * @param AnzahlProduktart
     * @return aktBestand aktueller Bestand
     * Funktion: Gibt die Anzahl der Produktart aus.
     */		
    // public int AusgabeBestandProduktart(int AnzahlProduktart)
    // {
    // int aktBestand = AnzahlProduktart;

    // return aktBestand;

    // }
    public String AusgabeBestandProduktart() //NEU
    {
        int diffBestand = aktBestand - minBestand;
        return ID + "   " +  Name + "   " + diffBestand;
    }
}