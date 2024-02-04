import { RoleType } from "../enums/RoleType";

export interface UserInput {
    id?: number;
    email?: string;
    password?: string;
    role?: RoleType;
    firstName?: string;
    lastName?: string;
    token?: string;
}
