import { gql } from '@apollo/client/core';

export const REGISTRATION_MUTATION = gql`
    mutation RegisterUser($user: UserInput!) {
        registration(user: $user) {
            email
            lastName
            firstName
            id
            password
            refreshToken
            role
        }
    }
`;
