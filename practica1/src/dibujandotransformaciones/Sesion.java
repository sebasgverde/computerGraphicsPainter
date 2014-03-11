/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dibujandotransformaciones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import paqueteMatematico.*;

/**
 *
 * @author sebastian
 */
public class Sesion extends JPanel {
    
    int w;
    int h;
    Graphics2D g2d;
    
    boolean banderaGlobal = true;
    boolean bandRepintarTodo =false;
    
  public ArrayList<FormaDib> formas;
  
  Matriz2 mat;
  
  public void funcion()
  {
      formas = new ArrayList<FormaDib>();
      
      mat = new Matriz2(0,0,0,0);
     
  }
  
    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
            
      g2d = (Graphics2D) g;

      g2d.setColor(Color.BLUE);
      

      // size es el tamaÃƒÆ’Ã‚Â±o de la ventana.
      Dimension size = getSize();
      // Insets son los bordes y los tÃƒÆ’Ã‚Â­tulos de la ventana.
      Insets insets = getInsets();

      w =  (size.width - insets.left - insets.right);//496
      h =  size.height - insets.top - insets.bottom;//474
      
     /* int [] a = {0,100,200};
      int [] b = {200,200,300};
        Polygon pol = new Polygon(a, b, 3);
        g2d.drawPolygon(pol);
      g2d.drawPolygon(a,b , 3);
      g2d.fillPolygon(pol);
      g2d.drawLine(w/2, 0, w/2, h);
      g2d.drawLine(w,h/2,0, h/2);*/
      
      if(banderaGlobal)
        funcion();
      

       
     for(int j = 0; j< formas.size();j++)
       {
           // ArrayList<Vector2D> puntos = formas.get(j).puntos;

           /* for(int i = 0; i< conexiones.size();i=i)
            {
                int punto1 = conexiones.get(i++);
                int punto2 = conexiones.get(i++);
                g2d.drawLine((int)puntos.get(punto1).mapearX(w), (int)puntos.get(punto1).mapearY(h), (int)puntos.get(punto2).mapearX(w), (int)puntos.get(punto2).mapearY(h));
            }*/
            formas.get(j).crearPoligono();
            g2d.drawPolygon(formas.get(j).poligono);
       }   
  }
  
  public void agregarForma(ArrayList<Vector2D> nuevForm)
  {
      banderaGlobal = false;
      FormaDib f = new FormaDib();
      f.setPuntos(nuevForm);
      f.puntos = nuevForm;
      formas.add(f);
      repaint();
  }
  
  public int mapearX(int x, int w)
  {
      return (x+w/2);
  }
  
  public int mapearY(int y, int h)
  {
      return (h/2-y);
  }
  
   public void escalar(double x, double y)
  {
      banderaGlobal = false;
     Matriz2 s = mat.scale(x, y);
     
         FormaDib nuevF =new FormaDib();
      
      for(int i = 0; i < formas.get(formas.size()-1).puntos.size();i++)
      {
          //puntos.add(r.multVector(puntos.get(i)));
        nuevF.puntos.add(s.multVector(formas.get(formas.size()-1).puntos.get(i))); 
               System.out.println(nuevF.puntos.get(i).x);

      }
      formas.add(nuevF);
   repaint();
  }
  
  public void trasladar(int x, int y)
  {
      banderaGlobal = false;
   Matriz2 t = mat.translate(x, y);
   
    FormaDib nuevF =new FormaDib();
      
      for(int i = 0; i < formas.get(formas.size()-1).puntos.size();i++)
      {
          //puntos.add(r.multVector(puntos.get(i)));
        nuevF.puntos.add(t.multVector(formas.get(formas.size()-1).puntos.get(i))); 
               System.out.println(nuevF.puntos.get(i).x);

      }
      formas.add(nuevF);
   repaint();
  }
  
  public void rotar(double theta)
  {
      banderaGlobal=false;
      Matriz2 r = mat.rotation(theta);
      
      FormaDib nuevF =new FormaDib();
      
      for(int i = 0; i < formas.get(formas.size()-1).puntos.size();i++)
      {
          //puntos.add(r.multVector(puntos.get(i)));
        nuevF.puntos.add(r.multVector(formas.get(formas.size()-1).puntos.get(i))); 
               System.out.println(nuevF.puntos.get(i).x);

      }
      formas.add(nuevF);
      repaint();
  }
  
  public void draw()
  {
      repaint();
  }
 
    
    /**
     * Programa principal
     * @param args 
     */
    /*public static void main(String[] args) {
              // Crear un nuevo Frame
      JFrame frame = new JFrame("Lines");
      // Al cerrar el frame, termina la ejecuciÃƒÆ’Ã‚Â³n de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new DibujandoTransformaciones());
      // Asignarle tamaÃƒÆ’Ã‚Â±o
      frame.setSize(512+16, 512+38);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
    }*/
    

}