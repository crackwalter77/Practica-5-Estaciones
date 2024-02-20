package vista.tabla;

import controlador.TDA.listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Estacion;

public class TablaEstacion extends AbstractTableModel {

    private LinkedList<Estacion> datos;

    public LinkedList<Estacion> getDatos() {
        return datos;
    }

    public void setDatos(LinkedList<Estacion> datos) {
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        return datos.getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Estacion t = datos.toArray()[rowIndex];

        switch (columnIndex) {
            case 0:
                return t.getNombre();
            case 1:
                return t.getLatitud();
            case 2:
                return t.getLongitud();
            case 3:
                return t.getFotos().toArray()[0];
            case 4:
                return t.getFotos().toArray()[1];
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Latitud";
            case 2:
                return "Longitud";
            case 3:
                return "Foto 1";
            case 4:
                return "Foto 2";
            default:
                return null;
        }
    }

}
