package logistics.surgerylogistics;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Lager lager = new Lager();
        
        lager.AnlegenOperation("Crazy Operation", "Arzt", "OTA", "Patient");
        
        lager.anzeigeBestandsliste();
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

}
