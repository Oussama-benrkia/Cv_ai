export interface Application {
  id: number;
  studentId: number;
  studentName: string;
  jobId: number;
  jobTitle: string;
  company: string;
  status: 'PENDING' | 'ACCEPTED' | 'REJECTED';
  appliedDate: string;
  cvId: number;
}

export interface ApplicationResponse {
  content: Application[];
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
  first: boolean;
  last: boolean;
} 