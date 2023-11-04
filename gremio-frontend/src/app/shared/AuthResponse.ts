export interface AuthResponse {
  id: number;
  email: string;
  accessToken: string;
  refreshToken: string;
  role: string;
}
