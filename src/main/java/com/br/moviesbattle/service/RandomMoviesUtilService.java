package com.br.moviesbattle.service;

import com.br.moviesbattle.dto.MovieDto;
import com.br.moviesbattle.mapper.MovieMapper;
import com.br.moviesbattle.model.Movie;
import com.br.moviesbattle.model.Round;
import com.br.moviesbattle.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RandomMoviesUtilService {

    public static final int QUANT_MOVIES = 30;

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    public List<MovieDto> getMovies(Long gameId) {
        Random random = new Random();

        Long movieId1;
        Long movieId2;

        Optional<Movie> movie1;
        Optional<Movie> movie2;

        Round gameRoundCheck = null;
        Round gameRoundCheckReverse = null;

        do {
            do {
                movieId1 = Long.valueOf(random.nextInt(QUANT_MOVIES));
                movie1 = movieRepository.findById(movieId1);
            } while (movie1.isEmpty());

            do {
                movieId2 = Long.valueOf(random.nextInt(QUANT_MOVIES));
                movie2 = movieRepository.findById(movieId2);
            } while (movie2.isEmpty() || movie2.get().equals(movie1.get()));

        } while (Objects.nonNull(gameRoundCheck) || Objects.nonNull(gameRoundCheckReverse));

        return Stream.of(movieMapper.toDto(movie1.get()), movieMapper.toDto(movie2.get())).collect(Collectors.toList());
    }

}
