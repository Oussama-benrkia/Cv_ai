import { Post } from './post.model';

export interface PostuleRequest {
  postId: number;
  cvId: number;
}
export interface PostuleResponse {
  id: number;
  post: Post;
  postId: number;
  etudiantId: number;
  description:string;
  creatAt:Date;
  status: string;
}
