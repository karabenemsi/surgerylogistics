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
    

}
