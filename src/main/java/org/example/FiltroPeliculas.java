package org.example;

import java.io.*;
import java.util.ArrayList;

public class FiltroPeliculas {

    class Movie implements Serializable {
        private String id;
        private String title;
        private Integer year;
        private String director;
        private String genre;
        
    }

    public void filtrarPorGenero(String genero) {
        File csv = new File("peliculas.csv");
        ArrayList<Movie> movies = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(csv))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] datos = line.split(",");
                Movie movie = new Movie();
                movie.id = datos[0].trim();
                movie.title = datos[1].trim();
                movie.year = Integer.parseInt(datos[2].trim());
                movie.director = datos[3].trim();
                movie.genre = datos[4].trim();
                movies.add(movie);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error en la lectura " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("peliculasdef.csv"))) {
            for (Movie movie : movies) {
                if (movie.genre.equalsIgnoreCase(genero)) {
                    writer.write(movie.toString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }
    }
}
