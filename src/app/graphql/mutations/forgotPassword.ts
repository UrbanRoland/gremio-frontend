import { gql } from '@apollo/client/core';

export const FORGOT_PASSWORD_MUTATION = gql`
    mutation ForgotPassword($email: String!) {
        forgotPassword(email: $email)
    }
`;
