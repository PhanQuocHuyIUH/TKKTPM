import { z } from "zod";

export const listMoviesQuerySchema = z.object({
  limit: z.coerce.number().int().min(1).max(100).default(20),
  offset: z.coerce.number().int().min(0).default(0),
});

export const createMovieSchema = z.object({
  title: z.string().trim().min(1).max(255),
  description: z
    .string()
    .trim()
    .max(5000)
    .optional()
    .transform((value) => (value && value.length > 0 ? value : undefined)),
  durationMinutes: z.coerce.number().int().positive().max(1000),
  posterUrl: z
    .string()
    .trim()
    .url()
    .max(500)
    .optional()
    .transform((value) => (value && value.length > 0 ? value : undefined)),
});
