#Movies Battle

##Users
O jogo já possui dois usuários cadastrados: "igor" e "maria", com a senha: 123123 

Caso deseje criar um novo user, enviar uma request 'POST - localhost:8081/user' com: name, username e password

##Login
Envie request para 'POST - localhost:8081/login' passando username e password e capture o token gerado para utilizar os demais endpoints

##Game
Envie request para 'POST - localhost:8081/createGame' para criar um novo jogo

Para comecar a jogar e iniciar uma nova rodada de palpites use o endpoint 'GET - localhost:8081/round/init/{gameId}' e a API retornará um par de filmes

Para dar um palpite, envie uma requisição 'POST - localhost:8081/round/submit/{gameId}' com 'option' = 1 ou 2 para movie1 e movie2, respectivamente

Para finalizar o jogo use o endpoint 'POST - localhost:8081/finishGame/{gameId}'

##Ranking
Para consultar o ranking do jogo use o endpoint 'POST - localhost:8081/ranking' e a API retornará as 10 melhores pontuações.



