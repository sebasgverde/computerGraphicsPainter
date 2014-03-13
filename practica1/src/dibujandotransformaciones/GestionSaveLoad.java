/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dibujandotransformaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import paqueteMatematico.Vector2D;

/**
 *
 * @author sebastian
 */
public class GestionSaveLoad {
    
    public GestionSaveLoad()
    {}
    
    public ArrayList<FormaDib> cargarDibujo(String ruta)
    {
        try{
            FileInputStream fstream = new FileInputStream(ruta);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int numeroFiguras = Integer.parseInt(br.readLine());

            ArrayList<FormaDib> formas = new ArrayList<FormaDib>();

            for(int i = 0; i< numeroFiguras; i++)
            {
                int numeroPuntos = Integer.parseInt(br.readLine());
                
                FormaDib forma = new FormaDib();
                
                for(int j = 0; j < numeroPuntos; j++)
                {
                    String [] arreglo = br.readLine().split(" ");
                    System.out.println(arreglo [0] + " " + arreglo[1]);

                    double x = Double.parseDouble(arreglo[0]);
                    double y = Double.parseDouble(arreglo[1]);

                    Vector2D temp = new Vector2D(x, y, 1);

                    forma.puntos.add(temp);
                }
                forma.color = br.readLine();
                forma.nombre = br.readLine();
                formas.add(forma);
            }
           in.close();
           return formas;
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return null;
      } 
    }
    
    public void guardarDibujo(String ruta, ArrayList<FormaDib> formas)
    {
        try{
            PrintWriter  br = new PrintWriter(ruta,"UTF-8");

            int numeroFiguras = formas.size();
            br.println(numeroFiguras);

            for(int i = 0; i< numeroFiguras; i++)
            {
                int numeroPuntos = formas.get(i).puntos.size();
                br.println(numeroPuntos);
                
                FormaDib forma = formas.get(i);
                
                for(int j = 0; j < numeroPuntos; j++)
                {
                    br.println(forma.puntos.get(j).x + " " + forma.puntos.get(j).y);
                    //System.out.println(arreglo [0] + " " + arreglo[1]);
                }
                br.println(forma.color);
                br.println(forma.nombre);
            }
            br.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
      }   
    }
    
    public void cargarFigura()
    {}
    
    public void guardarFigura()
    {}
    
    
}
