package org.eda.packpractica1;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class GestorFicheros {
    private static GestorFicheros miGestor = null;

    private GestorFicheros(){}

    public static GestorFicheros getGestorFicheros(){
        if (miGestor == null) miGestor = new GestorFicheros();
        return miGestor;
    }


    public void crearActorYPelicula(int actorId, String nombre, int peliculaId, String titulo, int año){
        Actor temp = new Actor(nombre, actorId);
        Actor actor = ListaActores.getListaActores().obtenerActor(temp);
        if (actor == null){
            ListaActores.getListaActores().añadirActor(temp);
            actor = temp;
        }

        Pelicula temp2 = new Pelicula(titulo, año, peliculaId);
        Pelicula pelicula = ListaPeliculas.getListaPeliculas().obtenerPelicula(temp2);
        if (pelicula == null){
            ListaPeliculas.getListaPeliculas().añadir(temp2);
            pelicula = temp2;
        }

        actor.añadirPelicula(pelicula);
        pelicula.añadirActor(actor);

    }


    public boolean cargarTodosLosElementosDe(String path){
        File carpeta = new File(path);
        File[] ficheros = carpeta.listFiles();

        if (ficheros == null){
            System.out.println("No se ha podido leer la carpeta.");
            return false;
        }

        ArrayList<File> txts = new ArrayList<>();
        for (File f: ficheros){
            if (f.isFile() && f.getName().endsWith(".txt")){
                txts.add(f);
            }
        }

        for (File f: txts){
            cargarElementosDe(f.getPath());
        }

        return !txts.isEmpty();
    }


    /*
    * Pre: el archivo existe con formato "url_actor ### nombre_actor ### url_pelicula ### titulo_pelicula", ademas todas las URL empiezan por Q y tienen 32 caracteres 
    */
    public boolean cargarElementosDe(String path){
        try{
            Scanner fichero = new Scanner (new FileReader(path));
            String linea = "";
            String[] datos;
            int año = Integer.parseInt(path.substring(path.length()-8, path.length()-4));
            
            while(fichero.hasNextLine()){
                linea = fichero.nextLine();
                
                if (linea.isEmpty()) continue;
                
                datos = linea.split("\\s###\\s");
                int actorId = Integer.parseInt(datos[0].substring(32));
                int peliculaId = Integer.parseInt(datos[2].substring(32));
                
                if (("Q" + actorId).equals(datos[1])||("Q"+peliculaId).equals(datos[3])) continue;

                crearActorYPelicula(actorId, datos[1], peliculaId, datos[3], año);
                
            }
            fichero.close();
            return true;
        }
        
        catch (IOException e) {
                e.printStackTrace();
            }
            return false;
    }

    public boolean guardarDatosEn(String path){
        try {
            PrintWriter editor = new PrintWriter(path, "UTF-8");
            ListaActores.getListaActores().escribirEnDirectorioCon(editor);
            editor.close();
            return true;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }
}