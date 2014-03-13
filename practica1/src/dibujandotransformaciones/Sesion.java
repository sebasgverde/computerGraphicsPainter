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
  public FormaDib formaDibujadaAhora;
  public GestionSaveLoad saveLoad;
  
  Matriz2 mat;
  
  public void funcion()
  {
      formas = new ArrayList<FormaDib>();
      formaDibujadaAhora = new FormaDib();
      saveLoad = new GestionSaveLoad();
      
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
      
      
      formaDibujadaAhora.crearPoligono();
      g2d.drawPolyline(formaDibujadaAhora.tempX, formaDibujadaAhora.tempY,formaDibujadaAhora.puntos.size());
       
     for(int j = 0; j< formas.size();j++)
       {
           // ArrayList<Vector2D> puntos = formas.get(j).puntos;

            /*for(int i = 0; i< conexiones.size();i=i)
            {
                int punto1 = conexiones.get(i++);
                int punto2 = conexiones.get(i++);
                g2d.drawLine((int)puntos.get(punto1).mapearX(w), (int)puntos.get(punto1).mapearY(h), (int)puntos.get(punto2).mapearX(w), (int)puntos.get(punto2).mapearY(h));
            }*/
           if(formas.get(j).seleccionado)
                g2d.setColor(Color.GREEN);
           else
                g2d.setColor(Color.BLUE);
           
            formas.get(j).crearPoligono();
            g2d.drawPolygon(formas.get(j).poligono);
            
       }   
  }
  
  public void agregarForma(ArrayList<Vector2D> nuevForm, String nomFig)
  {
      banderaGlobal = false;
      FormaDib f = new FormaDib();
      f.nombre = nomFig;
      f.setPuntos(nuevForm);
      f.puntos = nuevForm;
      formas.add(f);
      repaint();
  }
  
  public void seleccionarFigura(int seleccion)
  {
      for(int i = 0; i < formas.size(); i++)
      {
          formas.get(i).seleccionado = false;
      }
      formas.get(seleccion).seleccionado = true;
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
  
  public void rotar(double theta, int indFig)
  {
      banderaGlobal=false;
      Matriz2 r = mat.rotation(theta);
      
      //FormaDib nuevF =new FormaDib();
      
      for(int i = 0; i < formas.get(formas.size()-1).puntos.size();i++)
      {
          //puntos.add(r.multVector(puntos.get(i)));
          formas.get(indFig).puntos.set(i, r.multVector(formas.get(indFig).puntos.get(i)));
        //nuevF.puntos.add(r.multVector(formas.get(formas.size()-1).puntos.get(i))); 
               System.out.println(formas.get(indFig).puntos.get(i).x);

      }
      //formas.add(nuevF);
      repaint();
  }
  
  public void draw()
  {
      repaint();
  }   
  
  public void guardarDibujo(String ruta)
  {
      saveLoad.guardarDibujo(ruta, formas);
  }
  
  public void cargarDibujo(String ruta)
  {
      banderaGlobal = false;
      formas = saveLoad.cargarDibujo(ruta);
      repaint();
  }
}