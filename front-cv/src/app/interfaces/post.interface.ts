export interface Post {
  id: number;
  title: string;
  description: string;
  company: string;
  location: string;
  type: string;
  status: 'ACTIVE' | 'CLOSED';
  createdAt: string;
  hrId: number;
  applications: number;
}

export interface PostResponse {
  content: Post[];
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
  first: boolean;
  last: boolean;
} 