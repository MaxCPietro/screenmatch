package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDto;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping()
    public List<SerieDto> getSeries() {
        return servicio.getSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> getTop5Series() {
        return servicio.getTop5Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDto> getLanzamientosSeries() {
        return servicio.obtenerLanazamientosMasReciencientes();
    }

    @GetMapping("/{id}")
    public SerieDto getSerieById(@PathVariable long id) {
        return servicio.getSerieById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenerTodasLasTemporadas(@PathVariable long id) {
        return servicio.obtenerTodasLasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obtenerTemporadaPorNumero(@PathVariable long id, @PathVariable int numeroTemporada) {
        return servicio.obtenerTemporadasPorNumero (id, numeroTemporada);
    }

    @GetMapping("/catergoria/{nombreGenero}")
    public List<SerieDto> obtenerSeriePorGenero(@PathVariable String nombreGenero) {
        return servicio.obtemerSeriesPorCategoria(nombreGenero);
    }
}
