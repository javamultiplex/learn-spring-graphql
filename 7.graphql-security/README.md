# Mutation

- Request
  - curl -v -u Rohit:pw http://localhost:8080/graphql \
    -H 'Content-Type: application/json' \
    --data-raw '{"query":"mutation { insert(name: \"Bhavna\"){name}}" }'
- Response
  - 503 Forbidden

<hr/>


- Request
    - curl -v -u Bhavan:pw http://localhost:8080/graphql \
      -H 'Content-Type: application/json' \
      --data-raw '{"query":"mutation { insert(name: \"Bhavna\"){name}}" }'
- Response
    - 200 OK

    
# Query

- Request
    - curl -v -u Rohit:pw http://localhost:8080/graphql \
      -H 'Content-Type: application/json' \
      --data-raw '{"query":"query { customerById(id:1){id,name}}" }'
- Response
    - 200 OK

<hr/>

- Request
    - curl -v -u Bhavan:pw http://localhost:8080/graphql \
      -H 'Content-Type: application/json' \
      --data-raw '{"query":"query { customerById(id:1){id,name}}" }'
- Response
    - 200 OK

## Reference

https://www.youtube.com/watch?v=1nZcHJaQgUY&list=PLgGXSWYM2FpNRPDQnAGfAHxMl3zUG2Run&index=7