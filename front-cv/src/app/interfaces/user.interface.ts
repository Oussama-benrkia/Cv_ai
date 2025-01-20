export interface User {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  role: string;
  image: string;
}

export interface UserResponse {
  content: User[];
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
  first: boolean;
  last: boolean;
} 