package controlador.util;

import controladores.EstacionDAO;
import javax.swing.JComboBox;
import modelo.Estacion;

public class Util {
    
    public static void combo(JComboBox cbx){
        
        Estacion[] estaciones = new EstacionDAO().listAll().toArray();
        
        cbx.removeAllItems();
        
        if(estaciones == null){
            return;
        }
        
        for(Estacion estacion : estaciones){
            
            cbx.addItem(estacion);
            
        }
        
    }
}
