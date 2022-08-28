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