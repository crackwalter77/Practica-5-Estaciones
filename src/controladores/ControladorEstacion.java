package controladores;

import modelo.Conexion;
import controlador.TDA.grafos.DibujarGrafo;
import controlador.TDA.grafos.GrafoEtiquetadoDirigido;
import controlador.TDA.listas.LinkedList;
import controlador.util.Utilidades;
import java.io.File;
import javax.swing.JOptionPane;
import modelo.Estacion;

public class ControladorEstacion {
    
    private final EstacionDAO daoEstacion;
    private final ConexionDAO daoConexiones;
    private GrafoEtiquetadoDirigido<Estacion> grafoEstacionesDirigido;
    private LinkedList<Estacion> listaEstaciones;

    public ControladorEstacion() {
        daoEstacion = new EstacionDAO();
        daoConexiones = new ConexionDAO();
    }

    public void inicializarGrafo() throws Exception {

        listaEstaciones = daoEstacion.listAll();
        LinkedList<Conexion> conexiones = daoConexiones.listAll();

        Integer longitud = daoEstacion.listAll().getSize();

        grafoEstacionesDirigido = new GrafoEtiquetadoDirigido<>(longitud, Estacion.class);

        if (longitud > 0) {
            grafoEstacionesDirigido = new GrafoEtiquetadoDirigido(longitud, Estacion.class);
            for (int i = 0; i < longitud; i++) {
                grafoEstacionesDirigido.etiquetarVertice((i + 1), listaEstaciones.get(i));
            }

        }

        for (int i = 0; i < conexiones.getSize(); i++) {

            Conexion conex = conexiones.get(i);

            Double peso = Utilidades.distanciaDosPuntos(listaEstaciones.get(conex.getOrigen()).getLatitud(),
                    listaEstaciones.get(conex.getOrigen()).getLongitud(),
                    listaEstaciones.get(conex.getDestino()).getLatitud(),
                    listaEstaciones.get(conex.getDestino()).getLongitud());

            grafoEstacionesDirigido.insertarAristaE(listaEstaciones.get(conex.getOrigen()),
                    listaEstaciones.get(conex.getDestino()),
                    peso);

        }

    }

    public void conectar(int o, int d) throws Exception {

        inicializarGrafo();

        if (!grafoEstacionesDirigido.existe_arista(o + 1, d + 1)) {
            double peso = Utilidades.distanciaDosPuntos(listaEstaciones.get(o).getLatitud(),
                    listaEstaciones.get(o).getLongitud(),
                    listaEstaciones.get(d).getLatitud(),
                    listaEstaciones.get(d).getLongitud());

            grafoEstacionesDirigido.insertarAristaE(listaEstaciones.get(o), listaEstaciones.get(d), peso);

            daoConexiones.guardarConexion(o, d);
        }

        inicializarGrafo();

    }

    public EstacionDAO getDaoEstacion() {
        return daoEstacion;
    }

    public GrafoEtiquetadoDirigido<Estacion> getGrafoEstacionesDirigido() {
        try {
            inicializarGrafo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grafoEstacionesDirigido;
    }

    public void imprimirGrafo() {
        try {

            inicializarGrafo();

            DibujarGrafo dg = new DibujarGrafo();
            dg.crearArchivo(grafoEstacionesDirigido);

            String os = Utilidades.getOS();
            String dir = Utilidades.getDirProject();

            if (os.contains("Windows")) {
                Utilidades.abrirNavegadorPredeterminadoWindows(dir + File.separatorChar + "d3" + File.separatorChar + "grafo.html");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

}
