package controladores;

import controlador.TDA.listas.LinkedList;
import controlador.dao.DataAccessObject;
import modelo.Estacion;

public class EstacionDAO extends DataAccessObject<Estacion> {

    private Estacion estacion;

    public EstacionDAO() {
        super(Estacion.class);
    }

    public void guardarTelefonia(String nombre, Double latitud, Double longitud, LinkedList<String> fotos) {

        estacion = new Estacion(nombre, latitud, longitud, fotos);

        save(estacion);

        estacion = null;
        
    }
}
