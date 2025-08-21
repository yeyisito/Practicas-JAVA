/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import clasesDeUtilidad.Grafo;
import clasesDeUtilidad.Nodo;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author dieca
 */
public class GrafoUI extends javax.swing.JFrame {

    private Grafo grafo;
    private List<Nodo> nodos;
    private JPanel panelDibujo;
    private JTextArea areaResultados;
    private JButton botonAgregarNodo;
    private JButton botonConectarNodos;
    private JButton botonDijkstra;
    private JButton botonAlternarModo;
    private int contadorNodos = 0;
    private boolean esGrafoDirigido = false;

    public GrafoUI() {
        initComponents();
        grafo = new Grafo(100);  // Capacidad inicial de 100 nodos
        nodos = new ArrayList<>();

        setTitle("Grafo y Dijkstra");
        setSize(1920, 1030);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarGrafo(g);
            }
        };
        
        panelDibujo.setBackground(Color.LIGHT_GRAY);
        add(panelDibujo, BorderLayout.CENTER);

        JPanel panelControles = new JPanel();
        botonAgregarNodo = new JButton("Agregar Nodo");
        botonConectarNodos = new JButton("Conectar Nodos");
        botonDijkstra = new JButton("Ejecutar Dijkstra");
        botonAlternarModo = new JButton("No Dirigido"); // Establecer que incialmente sea no dirigido

        areaResultados = new JTextArea(5, 30);
        areaResultados.setEditable(false);
        panelControles.add(botonAgregarNodo);
        panelControles.add(botonConectarNodos);
        panelControles.add(botonDijkstra);
        panelControles.add(new JScrollPane(areaResultados));
        panelControles.add(botonAlternarModo);

        add(panelControles, BorderLayout.SOUTH);
        configurarEventos();
    }
    
    //Configurador de eventos
    private void configurarEventos() {
        //adaptador de mouse para el evento de agregar nodo con el click, este dibuja obteniendo las coordenadas en X y las coordenadas en Y del moyse
        panelDibujo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                agregarNodoConClick(e.getX(), e.getY());
            }
        });
        //este boton es para agregar el nodo manualmente inputeando las coordenadas manualmente
        botonAgregarNodo.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre del nodo:");
            //validacion del nombre, no puede ser ni nula ni vacia
            if (nombre == null || nombre.isEmpty()) {
                return;
            }
            //recuperamos el valor en x
            int x = Integer.parseInt(JOptionPane.showInputDialog("Posición X del nodo:"));
            //recuperamos el valor en y
            int y = Integer.parseInt(JOptionPane.showInputDialog("Posición Y del nodo:"));
            //creamos un nuevo objeto de tipo nodo con sus respectivos atributos
            Nodo nodo = new Nodo(nombre, x, y);
            //lo metemos a la lista del nodos
            nodos.add(nodo);
            //igualmente lo "agregamos" al grfo en el metodo de agregarNodo
            grafo.agregarNodo(nombre, x, y);
            //repintamos el canva por si no se actualiza el panel, y mostrar el nodo nuevo
            panelDibujo.repaint();
        });
        
        //boton para conectar entre dos nodos
        botonConectarNodos.addActionListener(e -> {
            //pregunta cual es el origen
            String origen = JOptionPane.showInputDialog("Nodo de origen:");
            //pregunta cual es el destino para hacer la conexion
            String destino = JOptionPane.showInputDialog("Nodo de destino:");
            //preguntamos por el peso
            String pesoStr = JOptionPane.showInputDialog("Peso de la arista:");
            
            //validamos por que se ingrese algo y no se quede vacio
            if ((pesoStr == null || pesoStr.isEmpty()) || (origen == null || origen.isEmpty()) || (destino == null || destino.isEmpty())) {
                JOptionPane.showMessageDialog(this, "Valores no ingresados. Operación cancelada.");
                return;
            }
            //try catch para validar correctamente el peso como valor numerico
            try {
                //convertimos el peso a entero
                int peso = Integer.parseInt(pesoStr);
                //del origen buscamos el nodo identico en la lista de los nodos
                int idxOrigen = buscarNodo(origen);
                //lo mismo con el destino
                int idxDestino = buscarNodo(destino);
                
                //Si la busqueda fue exitosa dibujamos la arista
                if (idxOrigen != -1 && idxDestino != -1) {
                    grafo.agregarArista(idxOrigen, idxDestino, peso, esGrafoDirigido);
                    panelDibujo.repaint();
                //sino, mandamos el mensaje de que no ha sido encontrado ningun nodo
                } else {
                    JOptionPane.showMessageDialog(this, "Nodo no encontrado.");
                }
            //prevent si el peso convertido es NaN (not a number)
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Peso inválido. Debe ser un número entero.");
            }
        });
        
        //boton para ejecutar el algoritmo de dijkstra
        botonDijkstra.addActionListener(e -> {
            //Preguntamos por el nodo inicio
            String inicio = JOptionPane.showInputDialog("Nodo de inicio:");
            //Buscamos en la lista de nodos
            int idxInicio = buscarNodo(inicio);
            //Validamos por que la busqueda haya sido exitosa
            if (idxInicio != -1) {
                //ejecutamos el algoritmo de dijkstra
                Grafo.DijkstraDatos resultado = grafo.dijkstra(idxInicio);
                //mostramos distancias y rutas resultado es la distancia minima y el nodo inicio
                mostrarDistanciasYRutas(resultado, inicio);
            } else {
                //Le decimos al usuario que el nodo no fue encontrado
                JOptionPane.showMessageDialog(this, "Nodo no encontrado.");
            }
        });

        //Boton para alternar el modo, si lo queremos dirigido o no dirigido, usamos operador ternario para ahorrar lineas de codigo
        botonAlternarModo.addActionListener(e -> {
            //Alternamos entre si es dirigido, sino lo negamos
            esGrafoDirigido = !esGrafoDirigido;
            botonAlternarModo.setText(esGrafoDirigido ? "No Dirigido" : "Dirigido");
            JOptionPane.showMessageDialog(this, "El modo ha sido cambiado a: "
                    + (esGrafoDirigido ? "Dirigido" : "No Dirigido"));
        });
    }   
    
    //metodo de agregar nodo con el click, arriba recibia el handler de eventos con la posicion en x y en y
    private void agregarNodoConClick(int x, int y) {
        //Preguntamos que nombre se le va a colocar al nodo
        String nombre = JOptionPane.showInputDialog("Nombre del nodo:");
        //Validamos para no recibir cadenas vacias ni nulas
        if (nombre != null && !nombre.isEmpty()) {
            //Validamos por que no exista ningun nodo con un nombre identico, esto se hace facilmente haciendo la busqueda y preguntando si el resultado de la busqueda es identica
            if (buscarNodo(nombre) != -1) {
                JOptionPane.showMessageDialog(this, "El nombre del nodo ya existe. Por favor, elige otro nombre.");
                return;
            }
            //Creamos un nodo nuevo con sus respectivos atributos
            Nodo nodo = new Nodo(nombre, x, y);
            //Lo metemos a la lista de nodos
            nodos.add(nodo);
            //Igualmente al del grafo
            grafo.agregarNodo(nombre, x, y);
            //Refrescamos el canva
            panelDibujo.repaint();
        }
    }

    //Metodo para buscar un nodo
    private int buscarNodo(String nombre) {
        //Recorremos la lista de nodos
        for (int i = 0; i < nodos.size(); i++) {
            //Si el nombre del nodo en el indice actual de i, es igual al nombre que se inputeo, regresar el indice
            if (nodos.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        //Regresamos un "codigo de busqueda no encontrada"
        return -1;
    }

    //Metodo para mostrar distancias y rutas
    private void mostrarDistanciasYRutas(Grafo.DijkstraDatos resultado, String inicio) {
        //Creamos un stringBuilder que no es nadamas que una secuencia de caracteres, en este caso, imprime el texto de abajo con el nodo inicio
        StringBuilder resultadoTexto = new StringBuilder("Distancias y rutas desde el nodo " + inicio + ":\n");
        
        //recorremos los resultados de las distancias minimas 
        for (int i = 0; i < resultado.distancias.length; i++) {
            //el destino sera igual al nombre del nodo en la iteracion
            String destino = nodos.get(i).getNombre();
            //empezamos a añadirle cosas al StringBuilder, en este caso, es el nodo actual y dos puntos
            resultadoTexto.append("Nodo ").append(destino).append(": ");
            //si la distancia es igual a Integer.MAX_VALUE, mandamos a pantalla que es infinita, ya que este nodo no es alcanzable por nuestro nodo de inicio
            if (resultado.distancias[i] == Integer.MAX_VALUE) {
                resultadoTexto.append("Infinito\n");
            } else {
                //De lo contrario, construimos ahora con la distancia y la ruta
                resultadoTexto.append(resultado.distancias[i]).append(" Ruta: ");
                //Igualmente llenamos una lista con la ruta optima obtenida con ayuda del metodo de obtenerRutaOptima 
                List<Nodo> rutaOptima = grafo.obtenerRutaOptima(buscarNodo(inicio), i, resultado.predecesores);
               //Recorremos la lista de nodos en la ruta optima y la añadimos al resultado
                for (Nodo nodo : rutaOptima) {
                    resultadoTexto.append(nodo.getNombre()).append(" ");
                }
                //salto de linea
                resultadoTexto.append("\n");
            }
        }
        //Imprimimos los resultados al textArea
        areaResultados.setText(resultadoTexto.toString());
        showMessageDialog(null, resultadoTexto.toString());
    }

    
    private void dibujarGrafo(Graphics g) {
        //Hacemos un cast del objeto Graphics a Graphics2D para poder dibujar flechas
        Graphics2D g2d = (Graphics2D) g;

        //Dibujar los nodos
        for (Nodo nodo : nodos) {
            //Establecer el color para el nodo.
            g.setColor(Color.RED);
            //Dibujar el nodo como un circulo relleno, este recibe la posicion del nodo y su tamaño 30x30 px
            g.fillOval(nodo.getPosX(), nodo.getPosY(), 30, 30); 

            //Establecer el color del texto
            g.setColor(Color.GRAY);
            //Dibujar el nombre del nodo sobre el círculo, la posicion en Y está desfazada hacia arriba para evitar interpolacion con el nodos y que impida que se dibuje apropiadamente
            g.drawString(nodo.getNombre(), nodo.getPosX(), nodo.getPosY() - 5);
        }

        //Dibujamos las aristas entre los nodos.
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = i + 1; j < nodos.size(); j++) {
                //Verificamos si hay una conexión entre los nodos (i, j) basada en el valor en la matriz de adyacencia
                if (grafo.grafo[i][j] != Integer.MAX_VALUE) {
                    //Establecer el color para las aristas
                    g.setColor(Color.BLACK);

                    //Si el grafo es dirigido, dibujamos una flecha
                    if (esGrafoDirigido) {
                        //Configurar el grosor de la linea para las flechas
                        g2d.setStroke(new BasicStroke(2)); // Grosor de la linea en px
                        repaint();
                        //Dibujar la linea entre los nodos i y j
                        //Se suman 10 px a las coordenadas de los nodos para centrar la linea en el nodo
                        g2d.drawLine(nodos.get(i).getPosX() + 10, nodos.get(i).getPosY() + 10,
                                nodos.get(j).getPosX() + 10, nodos.get(j).getPosY() + 10);

                        //Calculamos el angulo de la flecha usando la formula atan2 para obtener el angulo entre los nodos
                        double angle = Math.atan2(nodos.get(j).getPosY() - nodos.get(i).getPosY(),
                                nodos.get(j).getPosX() - nodos.get(i).getPosX());

                        //Invocamos a la función drawArrow para dibujar la flecha en la dirección correcta
                        drawArrow(g2d, nodos.get(j).getPosX() + 10, nodos.get(j).getPosY() + 10, angle);
                    } else {
                        //Si el grafo no es dirigido, simplemente dibujamos una linea
                        g2d.drawLine(nodos.get(i).getPosX() + 10, nodos.get(i).getPosY() + 10,
                                nodos.get(j).getPosX() + 10, nodos.get(j).getPosY() + 10);
                                repaint();
                    }

                    //Dibujar el peso de la arista en el medio de la linea con simple logica de punto medio
                    g.drawString(String.valueOf(grafo.grafo[i][j]),
                            (nodos.get(i).getPosX() + nodos.get(j).getPosX()) / 2,
                            (nodos.get(i).getPosY() + nodos.get(j).getPosY()) / 2);
                }
            }
        }
    }

//Metodo para dibujar la flecha en una arista
    private void drawArrow(Graphics2D g2d, int x, int y, double angle) {
        //La longitud de la flecha
        int arrowLength = 10;
        //El ancho de las lineas de la flecha
        int arrowWidth = 5;

        //Calcular las coordenadas del primer vertice de la flecha
        int x1 = (int) (x - arrowLength * Math.cos(angle - Math.PI / 6));
        int y1 = (int) (y - arrowLength * Math.sin(angle - Math.PI / 6));
        // Calcular las coordenadas del segundo vertice de la flecha
        int x2 = (int) (x - arrowLength * Math.cos(angle + Math.PI / 6));
        int y2 = (int) (y - arrowLength * Math.sin(angle + Math.PI / 6));

        //Establecer el color de la flecha.
        g2d.setColor(Color.BLACK);

        //Dibujar las dos lineas de la flecha, desde el extremo de la linea hasta los dos vertices calculados
        g2d.drawLine(x, y, x1, y1);
        g2d.drawLine(x, y, x2, y2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GrafoUI ventana = new GrafoUI();
            ventana.setVisible(true);
        });
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

