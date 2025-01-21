import { Post } from './post.model';

export interface PostuleRequest {
  postId: number;
  cvId: number;
}

export interface PostuleResponse {
  postId: number;
  description: string;
  message: string;
  pourcentage: number;
  creatAt: string;
  etudiantId: number;
  status: string;
}