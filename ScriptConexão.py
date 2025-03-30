import psycopg2
import sys

def conectar():
    connection = psycopg2.connect(
        host="localhost",
        database="SecondBD",
        user="xxxxxxxx",
        password="xxxxxxxxx",
    )
    return connection

def transportadora(nome, valor, validade):
    connection = conectar()
    inserir(connection, nome, valor, validade)
    connection.close()

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

if __name__ == "__main__": 
    if len(sys.argv) != 4:
        print("Uso: python script.py <nome> <valor> <data>")
        sys.exit(1)

    nome = sys.argv[1]
    valor = sys.argv[2]
    data = sys.argv[3]

    transportadora(nome, valor, data)
