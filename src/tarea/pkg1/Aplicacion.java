/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea.pkg1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author gonza
 */
public class Aplicacion extends JFrame {
    int lista=0;
    private Cliente [] clientes=new Cliente [3];
    

    //Etiquetas para identificar los campos de texto
    private JLabel numeroLabel;
    private JLabel nombreLabel;
    private JLabel edadLabel;
    private JLabel puntosLabel;
    private JButton button = new JButton("<-");
    private JButton button2 = new JButton("->");

    //Cadenas para las etiquetas
    private static String numeroString = "Número: ";
    private static String nombreString = "Nombre: ";
    private static String edadString = "Edad: ";
    private static String puntosString="Puntos: ";

    //Text fields para introducir números (ahora son de Swing: JTextField)
    private JTextField numeroField;
    private JTextField nombreField;
    private JTextField edadField;
    private JTextField puntosField;
    

    private boolean focusIsSet = false;

    public Aplicacion() {

        super("Aplicacion");
        
        clientes[0]= new Cliente(10,"Eduardo",20,80);
        clientes[1]= new Cliente(20,"Ana",24,40);
        clientes[2]= new Cliente(30,"Gonzalo",26,95);
        botones();
        //Lo que pasa si cerramos la ventana (sustituye al WindowListener
        //con windowClosing y System.exit(0) de la versión anterior).
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Crea las etiquetas.
        numeroLabel = new JLabel(numeroString);
        nombreLabel = new JLabel(nombreString);
        edadLabel = new JLabel(edadString);
        puntosLabel = new JLabel(puntosString);

        //Campo donde vamos a introducir el número
        numeroField = new JTextField(10);
        numeroField.setText(clientes [lista].getNumero()+"");
        //Lo que hace cuando pulsamos Enter
        
        numeroField.setEditable(false);
        //Campo que nos muestra el doble del numero introducido
        nombreField = new JTextField(20);
        nombreField.setEditable(false);
        nombreField.setText(clientes [lista].getNombre()+"");
        
        edadField = new JTextField(10);
        edadField.setEditable(false);
        edadField.setText(clientes [lista].getEdad()+"");
        
        puntosField =new JTextField(10);
        puntosField.setEditable(false);
        puntosField.setText(clientes [lista].getPuntos()+"");
        
        
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            lista--;   
            
            numeroField.setText(clientes[lista].getNumero() + "");
            nombreField.setText(clientes[lista].getNombre());
            edadField.setText(clientes[lista].getEdad() + "");
            puntosField.setText(clientes[lista].getPuntos() + "");
            botones();
            }
        });
        
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            lista++;   
            
            numeroField.setText(clientes[lista].getNumero() + "");
            nombreField.setText(clientes[lista].getNombre());
            edadField.setText(clientes[lista].getEdad() + "");
            puntosField.setText(clientes[lista].getPuntos() + "");
            botones();
            }
        });

        //Dispone la geometría de las etiquetas en un panel
        JPanel labelPane = new JPanel();
        labelPane.setLayout(new GridLayout(0, 1));
        labelPane.add(numeroLabel);
        labelPane.add(nombreLabel);
        labelPane.add(edadLabel);
        labelPane.add(puntosLabel);

        //Dispone los campos de texto en otro panel
        JPanel fieldPane = new JPanel();
        fieldPane.setLayout(new GridLayout(0, 1));
        fieldPane.add(numeroField);
        fieldPane.add(nombreField);
        fieldPane.add(edadField);
        fieldPane.add(puntosField);
        
        JPanel fieldPane2 = new JPanel();
        fieldPane2.setLayout(new GridLayout(1, 1));
        fieldPane2.add(button);
        fieldPane2.add(button2);

        //Incluye los dos paneles en otro panel,
        //etiquetas a la izquierda
        //y campos de texto a la derecha.
        JPanel contentPane = new JPanel();
        contentPane.setBorder(
               BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPane, BorderLayout.CENTER);
        contentPane.add(fieldPane, BorderLayout.EAST);
        contentPane.add(fieldPane2, BorderLayout.SOUTH);

        setContentPane(contentPane);  //this.setContentPane(contentPane);
          // es set, o sea, machado el contenedor del objeto "Aplicacion", hijo de JFrame

    }
private void botones(){
    button.setEnabled(lista > 0);
    button2.setEnabled(lista < clientes.length - 1);
}
    public static void main(String[] args) {
    Cliente [] clientes= new Cliente [3];
    
        // Toda la interfaz Swing se crea en el hilo de eventos (receta: se hace siempre así).
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final Aplicacion app = new Aplicacion();

                //Este WindowListener ya solo sirve para poner el foco.
                //(Para cerrar la ventana ya no hace falta: setDefaultCloseOperation.)
                app.addWindowListener(new WindowAdapter() {
                    //Pone el focus (foco de atencion)
                    public void windowActivated(WindowEvent e) {
                        app.setFocus();
                    }
                });
                app.pack();
                app.setVisible(true);
            }
        });
    }

    private void setFocus() {
        if (!focusIsSet) {
            numeroField.requestFocusInWindow();
            focusIsSet = true;
        }
    }
}
