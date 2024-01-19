import psycopg2
import sys

# Função que faz a conexão c/banco (postgresql)
def conectar():
    connection = psycopg2.connect(
        host="localhost",
        database="SecondBD",
        user="xxxxxxxx",
        password="xxxxxxxxx",
    )
    return connection

# Função que passa os dados, como parametros, para a função de inserção
def transportadora(nome, valor, validade):
    connection = conectar()
    inserir(connection, nome, valor, validade)
    connection.close()

# Função que coloca os dados recebidos na tabela do bd
def inserir(connection, nome, valor, validade):
    cursor = connection.cursor()
    cursor.execute(
        """
        INSERT INTO teste (nome, valor, validade)
        VALUES (%s, %s, %s)
        """,
        (nome, valor, validade),
    )
    connection.commit()

# Script (explicação do código mais abaixo)
if __name__ == "__main__": 
    if len(sys.argv) != 4:
        print("Uso: python script.py <nome> <valor> <data>")
        sys.exit(1)

    nome = sys.argv[1]
    valor = sys.argv[2]
    data = sys.argv[3]

    transportadora(nome, valor, data)
    
#Explicando script:
"""
if __name__ == "__main__": # Validação p/ ver se o script está sendo executado como principal 
    if len(sys.argv) != 4: #Vê se foi inserido todos os parâmetros necessários
        print("Uso: python script.py <nome> <valor> <data>")
        sys.exit(1)

    #Atribui os valores da lista (com os argumentos passados qnd o script for chamado), com index, as respectivas variáveis
    nome = sys.argv[1]
    valor = sys.argv[2]
    data = sys.argv[3]

    transportadora(nome, valor, data) # Chama a função passando as variáveis como parâmetro 
"""