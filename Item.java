
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int peso;
    private String descripcion;
    private String id;
    private boolean puedeCogerse;
    /**
     * Constructor for objects of class Item
     */
    public Item(int peso, String descripcion, String id, boolean puedeCogerse)
    {
       this.peso = peso;
       this.descripcion = descripcion;
       this.id = id;
       this.puedeCogerse = puedeCogerse;
    }
    
    /**
     * Devuelve el la descripcion y el peso del objeto.
     */
    public String getDescripcionCompleta(){
        return descripcion + "\nPesa: " + peso + " kilos" + " // ID: " + id;
    }
}
