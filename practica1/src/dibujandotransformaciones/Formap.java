/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dibujandotransformaciones;

import java.awt.Polygon;
import java.util.ArrayList;
import paqueteMatematico.Vector2D;

/**
 *
 * @author sebastian
 */
public class Formap {
  
    public ArrayList<Vector2D> puntos;
    public Polygon poligono;
  
  public Formap()
  {
      puntos = new ArrayList<Vector2D>();
  }
  
  public void setPuntos(ArrayList<Vector2D> form)
  {
      puntos = form;
  }
  
  public void crearPoligono()
  {
      int [] tempX = new int[puntos.size()];
      int [] tempY = new int[puntos.size()];
      
      for(int i = 0; i < puntos.size(); i++)
      {
          tempX[i] = (int)puntos.get(i).x;
          tempY[i] = (int)puntos.get(i).y;
      }
      
      poligono = new Polygon(tempX,tempY,puntos.size());
  }
    
}
