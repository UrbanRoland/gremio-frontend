import { gql } from "apollo-angular";

const LOGIN_MUTATION = gql`
    mutation Login($email: String!, $password: String!) {
        login(email: $email, password: $password) {
            accessToken
        }
    }
`;

export default LOGIN_MUTATION;
