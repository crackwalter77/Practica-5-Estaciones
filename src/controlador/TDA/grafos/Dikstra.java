package controlador.TDA.grafos;

import controladores.ControladorEstacion;

public class Dikstra {

    private static final double INFINITO = Double.POSITIVE_INFINITY;

    public static String encontrarCaminoMinimo(double[][] grafo, int origen, int destino) {
        int n = grafo.length;
        double[] distancias = new double[n];
        boolean[] visitados = new boolean[n];
        int[] anterior = new int[n];

        for (int i = 0; i < n; i++) {
            distancias[i] = INFINITO;
            anterior[i] = -1;
        }

        distancias[origen] = 0;

        for (int contador = 0; contador < n - 1; contador++) {
            int u = encontrarMinimaDistancia(distancias, visitados);
            visitados[u] = true;

            for (int i = 0; i < n; i++) {
                if (!visitados[i] && grafo[u][i] != 0 && distancias[u] != INFINITO
                        && distancias[u] + grafo[u][i] < distancias[i]) {
                    distancias[i] = distancias[u] + grafo[u][i];
                    anterior[i] = u;
                }
            }
        }

        return camino(distancias, anterior, origen, destino);
    }

    private static int encontrarMinimaDistancia(double[] distancias, boolean[] visitados) {
        double min = INFINITO;
        int indice = -1;

        for (int i = 0; i < distancias.length; i++) {
            if (!visitados[i] && distancias[i] <= min) {
                min = distancias[i];
                indice = i;
            }
        }

        return indice;
    }

    private static String camino(double[] distancias, int[] anterior, int origen, int destino) {
        ControladorEstacion tele = new ControladorEstacion();
        var lista = tele.getDaoEstacion().listAll().toArray();

        StringBuilder resultado = new StringBuilder();
        resultado.append("").append(lista[origen].getNombre()).append(" hacia: ")
                .append(lista[destino].getNombre()).append(":\n");

        if (distancias[destino] == INFINITO) {
            resultado.append("No existe un camino");
            return resultado.toString();
        }

        int[] camino = new int[distancias.length];
        int idxCamino = 0;
        for (int at = destino; at != -1; at = anterior[at]) {
            camino[idxCamino++] = at;
        }

        for (int i = idxCamino - 1; i >= 0; i--) {
            resultado.append(lista[camino[i]].getNombre());
            if (i != 0) {
                resultado.append(" -> ");
            }
        }

        return resultado.toString();
    }

}
