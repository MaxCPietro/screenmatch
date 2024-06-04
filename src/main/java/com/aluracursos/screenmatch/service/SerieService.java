package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDto;
import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    //Inyección de Dependecias
    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDto> getSeries() {
        return convierteDatos(serieRepository.findAll());
    }

    public List<SerieDto> getTop5Series() {
        return convierteDatos(serieRepository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDto> obtenerLanazamientosMasReciencientes(){
        return convierteDatos(serieRepository.lanzamientosMasRecientes());
    }

    public SerieDto getSerieById(long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDto(s.getId(),s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(),
                    s.getPoster(), s.getGenero(), s.getActores(), s.getSinopsis());
        } else return null;
    }
    public List<SerieDto> convierteDatos (List<Serie> serie) {
                return serie.stream().
                        map(s -> new SerieDto(s.getId(),s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(),
                        s.getPoster(), s.getGenero(), s.getActores(), s.getSinopsis()))
                        .collect(Collectors.toList());
    }


    public List<EpisodioDTO> obtenerTodasLasTemporadas(long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
            .map(e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        } else return null;
    }

    public List<EpisodioDTO> obtenerTemporadasPorNumero(long id, int numeroTemporada) {
     return serieRepository.obtenerTemporadaPorNumero(id, numeroTemporada).stream()
             .map(e-> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
             .collect(Collectors.toList());
    }

    public List<SerieDto> obtemerSeriesPorCategoria(String nombreGenero) {
        Categoria categoria = Categoria.fromEspanol(nombreGenero);
        return convierteDatos(serieRepository.findByGenero(categoria));
    }
}
