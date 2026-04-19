import { Movie } from "@prisma/client";

import * as movieRepository from "./movie.repository";
import {
  CreateMovieInput,
  ListMoviesInput,
  ListMoviesOutput,
  MovieDto,
} from "./movie.types";

function toMovieDto(movie: Movie): MovieDto {
  return {
    id: movie.id,
    title: movie.title,
    description: movie.description,
    durationMinutes: movie.durationMinutes,
    posterUrl: movie.posterUrl,
    createdAt: movie.createdAt.toISOString(),
  };
}

export async function createMovie(input: CreateMovieInput): Promise<MovieDto> {
  const movie = await movieRepository.createMovie(input);
  return toMovieDto(movie);
}

export async function getMovies(
  input: ListMoviesInput,
): Promise<ListMoviesOutput> {
  const result = await movieRepository.getMovies(input);

  return {
    data: result.movies.map(toMovieDto),
    total: result.total,
    limit: input.limit,
    offset: input.offset,
  };
}
