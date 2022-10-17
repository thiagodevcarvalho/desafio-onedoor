# Desafio-onedoor

Projeto desenvolvimento com springboot com Java 8, utilizando o banco local relacional H2. 

Depois de clonar/baixar o projeto executar os comandos:
- docker build -t onedoor/agendamento .  
- docker run -p 8080:8080 onedoor/agendamento


# Documentação da API

Depois de subir o container com o projeto rodando é possível acessar a documentação da API através do endereço: **http://localhost:8080/swagger-ui/**


# Base de dados relacional

O banco de dados da aplicação pode ser acessado através do endereço **http://localhost:8080/h2-console** , as credenciais são:
- Username: sa
- Password: sa
- url de conexão com db: jdbc:h2:mem:agendamento




# Exemplo de criação do agendamento

Existem duas maneiras(endpoints) de criar um agendamento:

- Passando o objeto completo no payload:
	{
	"dataHora" : "2022-10-08 22:59:02",
	"observacao" : "teste" ,
	"cliente" : {
							"nome" : "Thiago Melo_referencia",
							"cpf" : "07600521407"
},
"servico" : {
"codigo": "A1",
"descricao" : "Impressões",
"valor": 230
}
} 

- Passando como referência via GET o ID do cliente e o ID do serviço e no body os dados do angendamento: "/cliente/{clienteId}/servico/{servicoId}".


> Outros exemplos de endpoint e payload podem ser encontrados através do endereço: **http://localhost:8080/swagger-ui/**


