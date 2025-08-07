# jwt_based_Authentication
 this is jwt (Jason Web Token) based authentication app(lctr30)
- bearer token -> Authorization: Bearer <Token>  
  - (Bearer Token is Limited Time Token, to restrict token thief's act time)
- Token Signing -> Hashing algorithm 
  - token signing using hashing algorithm "SHA256" (payload+header+Secret) 
- Asymmetric Encrypting (Public key / Private key)

