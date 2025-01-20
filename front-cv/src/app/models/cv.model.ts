export interface Cv {
  id: number;
  titre: string;
  file:File | null;
}
export interface CvResponse {
  id: number;
  titre: string;
  path: string;
  createdAt: Date;
}
