import { gql } from '@apollo/client/core';

export const RESET_PASSWORD_MUTATION = gql`
  mutation ResetPassword($userInput: UserInput!) {
    resetPassword(userInput: $userInput)
  }
`;
