import { NextFunction, Request, Response } from "express";

import { createMovieSchema, listMoviesQuerySchema } from "./movie.schemas";
import * as movieService from "./movie.service";

export async function getMoviesHandler(
  req: Request,
  res: Response,
  next: NextFunction,
): Promise<void> {
  try {
    const query = listMoviesQuerySchema.parse(req.query);
    const movies = await movieService.getMovies(query);
    res.status(200).json(movies);
  } catch (error) {
    next(error);
  }
}

export async function createMovieHandler(
  req: Request,
  res: Response,
  next: NextFunction,
): Promise<void> {
  try {
    const body = createMovieSchema.parse(req.body);
    const movie = await movieService.createMovie(body);
    res.status(201).json(movie);
  } catch (error) {
    next(error);
  }
}
