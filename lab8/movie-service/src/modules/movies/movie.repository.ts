import { prisma } from "../../infrastructure/db/prisma";
import { CreateMovieInput, ListMoviesInput } from "./movie.types";

export async function createMovie(input: CreateMovieInput) {
  return prisma.movie.create({
    data: {
      title: input.title,
      description: input.description,
      durationMinutes: input.durationMinutes,
      posterUrl: input.posterUrl,
    },
  });
}

export async function getMovies(input: ListMoviesInput) {
  const [movies, total] = await prisma.$transaction([
    prisma.movie.findMany({
      orderBy: {
        createdAt: "desc",
      },
      skip: input.offset,
      take: input.limit,
    }),
    prisma.movie.count(),
  ]);

  return {
    movies,
    total,
  };
}
