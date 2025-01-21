export interface UserResponse {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  image?:string;
  role: string;
  creatAt:Date;
}
export interface UserRequest {
  nom:string;
  prenom: string;
  email: string;
  password?: string;
  image?: File | null;
  role:string;
}
