def lerMatrizByUser(line, column):
    m = []
    print("\n")
    for j in range(line):
        linha = []
        for i in range(column):
            linha.append(int(input("Informe um elemento: ")))
        m.append(linha)
    return m

def lerFileTXT(name):
    file = open(name, "r")

    m = []
    lines = file.readline().split('/')

    for line in lines:
        elements = line.split(';')
        for i in range(len(elements)):
            elements[i] = float(elements[i])
        m.append(elements)

    file.close()

    return m

def toStringMatriz(matriz):
    print("\n")
    for linha in matriz:
        print(linha)

def somar(a, b):
    if len(a) == len(b) and len(a[0]) == len(b[0]):

        m = []

        for linha in range(len(a)):
            elements = []
            for coluna in range(len(a[0])):
                elements.append(float(a[linha][coluna]) + float(b[linha][coluna]))
            m.append(elements)
        
        return m
    else:
        return False

matriz = lerFileTXT("inserts.txt")
toStringMatriz(matriz)

matriz2 = lerMatrizByUser(3,3)
toStringMatriz(matriz2)

resultado = somar(matriz, matriz2)

if resultado != False:
    toStringMatriz(resultado)
else:
    print("\nErrorTypeFalseException: soma impossível")
