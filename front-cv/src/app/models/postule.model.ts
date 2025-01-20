export interface PostuleRequest {
  postId: number;
  cvId: number;
}
export interface PostuleResponse {
  id: number;
  postId: number;
  etudiantId: number;
  description:string;
  creatAt:Date
}
