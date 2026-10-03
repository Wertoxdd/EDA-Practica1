package org.eda.packpractica1;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GestorFicheros {
    private static GestorFicheros miGestor = null;

    private GestorFicheros(){}

    public static GestorFicheros getGestorFicheros(){
        if (miGestor == null) miGestor = new GestorFicheros();
        return miGestor;
    }




    /*
    * Pre: el archivo existe con formato "url_actor ### nombre_actor ### url_pelicula ### titulo_pelicula", ademas todas las URL empiezan por Q y tienen 32 caracteres 
    */
    public boolean cargarElementosDe(String path){
        try{
            Scanner fichero = new Scanner (new FileReader(path));
            String linea = "";
            String[] datos;
            int anio = Integer.parseInt(path.substring(path.length()-8, path.length()-4));
            
            while(fichero.hasNextLine()){
                linea = fichero.nextLine();
                
                if (linea.isEmpty()) continue;
                
                datos = linea.split("\\s###\\s");
                int actorId = Integer.parseInt(datos[0].substring(32));
                int peliculaId = Integer.parseInt(datos[2].substring(32));
                
                if (("Q" + actorId).equals(datos[1])||("Q"+peliculaId).equals(datos[3])) continue;

                ArrayList<Actor> actores = ListaActores.getListaActores().obtenerActoresPorNombre(datos[1]);
                Actor actor = null;
                
                if (actores != null){
                    for(Actor a: actores){
                        if (a.tieneMismoId(actorId)){
                            actor = a; 
                            break;
                        }
                    }

                }

                if (actor == null){
                    actor = new Actor (datos[1], actorId);
                    ListaActores.getListaActores().anadirActor(actor);
                }

                ArrayList<Pelicula> peliculas = ListaPeliculas.getListaPeliculas().obtenerPeliculasPorTitulo(datos[3]);
                Pelicula pelicula = null;

                if (peliculas != null){
                    for (Pelicula p: peliculas){
                        if (p.tieneMismoId(peliculaId)){
                            pelicula = p;
                            break;
                        }
                    }
                }

                if (peliculas == null){
                    pelicula = new Pelicula(datos[3], anio, peliculaId);
                    ListaPeliculas.getListaPeliculas().anadir(pelicula);
                }

                
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
