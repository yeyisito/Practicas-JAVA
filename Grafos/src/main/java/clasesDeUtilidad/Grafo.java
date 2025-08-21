/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDeUtilidad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author dieca
 */
public class Grafo {

    //Variables de clase
    //variable de tipo Lista<nodos>, para alamacenar todos los nodos agregados al grafo
    /*Recordadno que grafo tiene multiples atributos de por sí*/
    private List<Nodo> nodos;
    //Esta matriz, sera usada como la matriz de adyacencia que utilizaremos para representar las conexiones entre nodos
    /*De manera que represente el peso de la arista entre nodo i y nodo j*/
    public int[][] grafo;
    //Numero de nodos actuales
    private int numNodos;
    //Numero maximo de nodos que puede haber simulateamente en el grafo
    private int maxNodos;
    //Contador que indica cuantos nodos se han agregado 
    private int nodoActual;

    //Constructor que inicializa el grafo con el numero maximo permitido de nodos del grafo
    public Grafo(int numNodos) {
        this.maxNodos = numNodos;
        this.nodoActual = 0;
        //Creacion de la lista para almacenar los nodo
        this.nodos = new ArrayList<>();
        //Creacion de la matriz de adyacencia 
        this.grafo = new int[numNodos][numNodos];

        // Inicializar la matriz de adyacencia con el numero maximo de nodos
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (i == j) {
                    grafo[i][j] = 0; //La distancia de un nodo a sí mismo es 0
                } else {
                    grafo[i][j] = Integer.MAX_VALUE; //Indica que no hay conexión
                }
            }
        }
    }
    //--------------------METODOS DE CLASE-----------------------------//

    //Metodo agregarNodo, que, agrega un nuevo nodo al grafo
    public void agregarNodo(String nombre, int x, int y) {
        //Validacion por si se alcanza el numero maximo de nodos
        if (nodoActual >= maxNodos) {
            System.out.println("Se alcanzó el número máximo de nodos.");
            return;
        }
        //Creamos un nuevo objeto con los atributos respectivos: nombre, pos en x y pos en y
        Nodo nuevoNodo = new Nodo(nombre, x, y);
        nodos.add(nuevoNodo); //Se añade el objeto a la lista
        nodoActual++;
    }

    //Metodo agregarArista, agrega arista entre el origen y el destino
    public void agregarArista(int origen, int destino, int peso, boolean esDirigido) {
        //Checar si el grafo es dirigido o no es dirigido, si si la arista se agrega solo a una direccion
        if (origen >= 0 && origen < maxNodos && destino >= 0 && destino < maxNodos) {
            grafo[origen][destino] = peso; //Agregar la arista principal, el peso es entre el origen y el destino
            if (!esDirigido) {
                grafo[destino][origen] = peso; //Si no es dirigido, tambien agrega la arista inversa para diferenciarlos uno del otro
            }
        }
    }

    /*Clase interna auxiliar para el manejo de los resultados del algoritmo, esta es estatica ya que no la vamos a necesitar para nadamas que acceder rapidamente a los datos almacenados
   De esta manera no tenemos que crear otro objeto de GrafoSimplificado para poder acceder a estos datos contenidos*/
    public static class DijkstraDatos {

        //Almacena las distancias más cortas desde nodo inicio a cada uno de los nodos que esten dentro de la lista 
        public int[] distancias;
        //Almacena los nodos anteriores en la ruta mas corta hacia cada nodo
        public int[] predecesores;

        public DijkstraDatos(int[] distancias, int[] predecesores) {
            this.distancias = distancias;
            this.predecesores = predecesores;
        }
    }

    //Metodo dijkstra de tipo clase DijkstraDatos
    public DijkstraDatos dijkstra(int inicio) {
        //Arreglo de distancias de tamaño nodos.size (numero de nodos en la lista), esta almacenara la distancia mas corta desde el nodo origen hasta cada uno de los demas
        int[] distancias = new int[nodos.size()];
        //Lo mismo con la de predecesores, solo que esta alamacena el nodo anterior en la ruta mas corta para cada nodo
        int[] predecesores = new int[nodos.size()];

        //Inicializamos arreglo de distancias con Integer.MAX_VALUE, inicialmente como no sabemos ninguna de las distancias es conveniente dejar las distancias como si fueran "infinitas"
        //Esto, para que posteriormente no tengamos que estar reemplazando valores a conexiones inexistentes, y tambien es mas manejable en memoria
        Arrays.fill(distancias, Integer.MAX_VALUE);
        //Igualmente se inicializa el arreglo de predecesores, con -1, esto para indicar que al principio, no hay manera de saber el predecesor del nodo de inicio
        Arrays.fill(predecesores, -1);
        //distancias en el indice inicio siempre sera cero, ya que del nodo incio al nodo inicio hay una distancia de 0
        distancias[inicio] = 0;
        //arreglo de booleans necesario para saber si ya se iteró sobre los nodos o no 
        boolean[] visitado = new boolean[nodos.size()];

        for (int i = 0; i < nodos.size(); i++) {
            //u representa el indice del nodo actual que se va a checar
            int u = -1;
            for (int j = 0; j < nodos.size(); j++) {
                //logica para la busqueda del nodo no visitado con la distancia mínima
                //es decir, en cada iteracion, el bucle encuentra el nodo no visitado con la menor distancia desde el inicio
                /*si no se ha sido visitado el nodo en dicha iteracion, y el indice es identico a -1 (ningun nodo seleccionado)
                o la distancia del nodo j es menor que la distancia del nodo i*/
                if (!visitado[j] && (u == -1 || distancias[j] < distancias[u])) {
                    //si esto se cumple, se reasigna el valor indice del nodo actual al valor de j que ahora será el indice con la distancia mínima
                    u = j;
                }
            }
            //En caso de que la distancia en el indice u sea identica a nuestro valor que dejamos como infinito, significara que no hay mas nodos alcanzables por el inicio
            //por lo tanto podemos terminar el bucle
            if (distancias[u] == Integer.MAX_VALUE) {
                break; // No hay más nodos alcanzables
            }

            //así mismo que marcamos como la iteracion u como visitada
            visitado[u] = true;

            // Actualizar distancias de los nodos adyacentes, este bucle recorre los nodos v del grafo y verifica si es adayacente al nodo u
            for (int v = 0; v < nodos.size(); v++) {
                //planteamos la condicion para saber si son adyacentes, obviamente los nodos deben de haber sido visitados
                if (!visitado[v] && grafo[u][v] != Integer.MAX_VALUE) {
                    //calcula la nueva distancia minima, si esta es menor que la distancia actual almacenada en distancias[v], significa que se ha encontrado un camino mas optimo
                    int nuevaDistancia = distancias[u] + grafo[u][v];
                    if (nuevaDistancia < distancias[v]) {
                        //se actualiza la distancia[v] con el valor minimo
                        distancias[v] = nuevaDistancia;
                        //almacenamos en el mismo indice pero ahora con el nodo, esto posteriormente lo utilizare para obtener la ruta optima
                        predecesores[v] = u;
                    }
                }
            }
        }
        return new DijkstraDatos(distancias, predecesores);
    }
    
    //Metodo obtenerRutaOptima
    //Este metodo regresara una lista con la mejor ruta para llegar a determinado nodo, para este metodo si es importante que reciba tanto inicio como destino, sino no existe 
    //manera de saber a donde se quiere llegar y menos por donde es mejor llegar
    public List<Nodo> obtenerRutaOptima(int inicio, int destino, int[] predecesores) {
        //creamos la lista que vamos a devolver con los nodos por donde debe de pasar
        List<Nodo> ruta = new ArrayList<>();
        //recorremos los nodos predecesores para poder obtener la ruta optima
        //inicializamos con el valor del nodo de destino, ya que como si lo hicieramos en papel, es el primer nodo que analizamos para reconstruir la ruta
        //este bucle termina hasta que el indice de at sea -1 (nodo inicio)
        for (int at = destino; at != -1; at = predecesores[at]) {
            //agregamos los nodos a la lista de la ruta
            ruta.add(nodos.get(at));
        }
        //invertimos la lista de nodos
        Collections.reverse(ruta);
        //la regresamos para poder ser impresa.
        return ruta;
    }

//    private int obtenerNodoMinimo(int[] distancia, boolean[] visitado) {
//        int minDistancia = Integer.MAX_VALUE;
//        int minIndice = -1;
//
//        for (int i = 0; i < numNodos; i++) {
//            if (!visitado[i] && distancia[i] < minDistancia) {
//                minDistancia = distancia[i];
//                minIndice = i;
//            }
//        }
//        return minIndice;
//    }
}
