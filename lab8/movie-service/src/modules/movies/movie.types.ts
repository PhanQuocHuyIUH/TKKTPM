export interface CreateMovieInput {
  title: string;
  description?: string;
  durationMinutes: number;
  posterUrl?: string;
}

export interface MovieDto {
  id: string;
  title: string;
  description: string | null;
  durationMinutes: number;
  posterUrl: string | null;
  createdAt: string;
}

export interface ListMoviesInput {
  limit: number;
  offset: number;
}

export interface ListMoviesOutput {
  data: MovieDto[];
  total: number;
  limit: number;
  offset: number;
}
