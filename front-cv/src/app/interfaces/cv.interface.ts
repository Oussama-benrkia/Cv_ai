export interface CV {
  id: number;
  studentId: number;
  studentName: string;
  title: string;
  education: string;
  experience: string;
  skills: string[];
  status: 'ACTIVE' | 'INACTIVE';
  createdAt: string;
  lastUpdated: string;
}

export interface CVResponse {
  content: CV[];
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
  first: boolean;
  last: boolean;
} 