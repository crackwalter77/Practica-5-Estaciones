package controlador.TDA.grafos;

import controladores.ControladorEstacion;
import controlador.TDA.listas.LinkedList;

public class Floyd {

    private static final double INF = Double.MAX_VALUE;

    public static String encontrarCamino(double[][] grafo, int origen, int destino) {
        int n = grafo.length;

        double[][] destinos = new double[n][n];
        int[][] siguiente = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                destinos[i][j] = grafo[i][j];
                if (i != j && grafo[i][j] != INF) {
                    siguiente[i][j] = j;
                } else {
                    siguiente[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (destinos[i][k] != INF && destinos[k][j] != INF
                            && destinos[i][k] + destinos[k][j] < destinos[i][j]) {
                        destinos[i][j] = destinos[i][k] + destinos[k][j];
                        siguiente[i][j] = siguiente[i][k];
                    }
                }
            }
        }

        var caminoResultado = camino(origen, destino, siguiente).toArray();
        
        ControladorEstacion tele = new ControladorEstacion();
        
        var lista = tele.getDaoEstacion().listAll().toArray();
        
        if (caminoResultado.length == 0) {
            return "No existe un camino";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(lista[origen].getNombre()).append(" hacia ").append(lista[destino].getNombre()).append("\n");
            for (Integer node : caminoResultado) {
                sb.append(lista[node].getNombre()).append(" -> ");
            }
            
            sb.delete(sb.length() - 4, sb.length());
            return sb.toString();
        }
    }

    private static LinkedList<Integer> camino(int origen, int destino, int[][] siguiente) {
        LinkedList<Integer> camino = new LinkedList<>();
        if (siguiente[origen][destino] == -1) {
            return camino;
        }
        camino.add(origen);
        while (origen != destino) {
            origen = siguiente[origen][destino];
            camino.add(origen);
        }
        return camino;
    }

}
