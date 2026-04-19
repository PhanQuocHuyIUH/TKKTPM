import { Router } from "express";

import { createMovieHandler, getMoviesHandler } from "./movie.controller";

const movieRouter = Router();

movieRouter.get("/", getMoviesHandler);
movieRouter.post("/", createMovieHandler);

export { movieRouter };
