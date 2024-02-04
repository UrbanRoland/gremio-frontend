import { RoleType } from "../enums/RoleType";

export interface User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    refreshToken: string;
    passwordResetToken: string;
    role: RoleType;
}