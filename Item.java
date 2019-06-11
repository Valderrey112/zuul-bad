
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int weigth;
    private String descripcion;
    private String id;
    private boolean puedeCogerse;
    /**
     * Constructor for objects of class Item
     */
<<<<<<< HEAD
    public Item(int peso, String descripcion, String id, boolean puedeCogerse)
=======
    public Item(int weigth, String descripcion, String id, boolean puedeCogerse)
>>>>>>> cogersoltar
    {
       this.weigth = weigth;
       this.descripcion = descripcion;
       this.id = id;
       this.puedeCogerse = puedeCogerse;
    }
    
    /**
     * Devuelve el la descripcion y el peso del objeto.
     */
<<<<<<< HEAD
    public String getDescripcionCompleta(){
        return descripcion + "\nPesa: " + peso + " kilos" + " // ID: " + id;
=======
    public String getDescripcionItem(){
        return descripcion + "\nPesa: " + weigth + " kilos" + " // ID: " + id;
    }
    
    /**
     * Return the Item weigth
     *
     * @return    the Item weigth
     */
    public int getWeigth()
    {
        return weigth;
    }

    /**
     * Return the Item ID
     *
     * @return    the Item ID
     */
    public String getItemId()
    {
        return id;
>>>>>>> cogersoltar
    }
}
