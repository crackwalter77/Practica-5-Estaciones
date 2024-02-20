package controladores;

import modelo.Conexion;
import controlador.dao.DataAccessObject;

public class ConexionDAO extends DataAccessObject<Conexion> {
    
    private Conexion union;
    
    public ConexionDAO(){
        super(Conexion.class);
    }
    
    public void guardarConexion(Integer origen, Integer destino){
        union = new Conexion(origen, destino);
        save(union);
        union = null;
    }
    
}
