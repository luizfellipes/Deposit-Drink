<h1>Escopo, pré-requisitos e objetivo.</h1>

<h3><p>Projeto que serve de referência para o desenvolvimento de software em Java.</p></h3>

É um projeto proposto para ser clonado e reutilizado, para ilustrar o projeto inclui:<br>
(a) Deposito de bebidas formada por orientação objeto com uma entidade principal e duas dependentes, tratamento de erros  e teste unitarios;<br>
(b) REST API para realizar estocamento de bebidas alcolicas e não alcolicas.<br>
(c) modelo de post e response:

Post:
~~~
{
  "data": "2024-06-05T15:07:27.725Z",
  "movimentType": "ENTRY",
  "responsible": "Employee",  
	"section": "A",
  "drink": {   
  "drinkType": "ALCOHOLIC",
    "drinkName": "string",
    "volume": 100
  }
}
~~~ 

Response:
~~~
{
	"id": 1,
	"data": "2024-06-05T15:07:27.725",
	"responsible": "Employee",
	"section": "A",
	"movimentType": "ENTRY",
	"drink": {
		"id": 1,
		"drinkType": "ALCOHOLIC",
		"volume": 100.0,
		"drinkName": "string",
		"totalVolumeInSection": 100.0
	}
}
~~~

availableSection Exemple
~~~

[
	{
		"Section: A": 100.0
	},
	{
		"Section: B": 0.0
	},
	{
		"Section: E": 0.0
	},
	{
		"Section: C": 0.0
	},
	{
		"Section: D": 0.0
	}
]

~~~

volumeSection Example: 
~~~

[
	{
		"Section: A, DrinkType: ALCOHOLIC, VolumeTotalInSection": 100.0
	}
]

~~~

getHistory
~~~

[
	{
		"id": 1,
		"data": "2024-06-05T15:07:27.725",
		"responsible": "Employee",
		"section": "A",
		"movimentType": "ENTRY",
		"drinkType": "ALCOHOLIC",
		"volume": 100.0,
		"drinkName": "string"
	}
]
~~~

drinkConfigPatching Example- para troca dos parametros pre estabalecidos<br>
Patching:
~~~
{
  "id": 1,
  "MAX_ALCOHOLIC_CAPACITY": 2000.0,
  "MAX_NONALCOHOLIC_CAPACITY": 1000.0,
	"PERMIT_SECTION": ["A", "B", "C", "D", "E", "F"],
   "DRINK_CAN_BE_TOGETHER": true
}
~~~

Response:
~~~
{
	"id": 1,
	"permit_SECTION": [
		"A",
		"B",
		"C",
		"D",
		"E",
		"F"
	],
	"max_ALCOHOLIC_CAPACITY": 2000.0,
	"max_NONALCOHOLIC_CAPACITY": 1000.0,
	"drink_CAN_BE_TOGETHER": true
}
~~~

<h3>Iniciando...</h3>

- jdk17
- clonar o projeto https://github.com/luizfellipes/DrinkDeposit.git<br>
- baixar todas as dependencias solicitada no pom.xml<br>
- Banco de Dados Relacional em memoria H2<br>
- Swagger http://localhost:8080/swagger-ui/index.html#/
- SonarCube http://localhost:9000/sessions/new?return_to=%2F

<h3>Executando a aplicação</h3>

Como se trata de uma aplicação teste rode ela pela classe SpringApplication.run, compile ou execute o Dockerfile.<br>

docker build -t drinkdeposit .<br>
docker run -p 8080:8080 drinkdeposit

para o sonarCube
docker-compose up na pasta raiz do projeto

<h3>Objetivo</h3>
Criar uma API Rest para estudo baseado em java com framework SpringBoot, para estudo e conhecimento