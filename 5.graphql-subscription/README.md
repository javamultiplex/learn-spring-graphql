# Install RSocket client
brew install making/tap/rsc


## Query
rsc --request \
--route=graphql \
--dataMimeType="application/graphql+json" \
--data='{"query":"query { greeting { greeting } }"}' \
--debug tcp://localhost:9191


## Subscription
rsc --stream \
--route=graphql \
--dataMimeType="application/graphql+json" \
--data='{"query":"subscription { greetings { greeting } }"}' \
--debug tcp://localhost:9191

## Reference

https://www.youtube.com/watch?v=2paPPY9AmPw&list=PLgGXSWYM2FpNRPDQnAGfAHxMl3zUG2Run&index=6
