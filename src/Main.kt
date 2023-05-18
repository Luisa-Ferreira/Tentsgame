import java.io.File

fun criaMenu(): String {
    return """
Bem vindo ao jogo das tendas

1 - Novo jogo
0 - Sair
"""
}

fun validaTamanhoMapa(numLinhas: Int, numColunas: Int): Boolean {

    return (numLinhas == 6 && numColunas == 5) || (numLinhas == 6 && numColunas == 6) ||
            (numLinhas == 8 && numColunas == 8) || (numLinhas == 10 && numColunas == 10) ||
            (numLinhas == 8 && numColunas == 10) || (numLinhas == 10 && numColunas == 8)
}

fun validaDataNascimento(data: String?): String? {

    val datainvalida = "Data invalida"

    if (data == null || data.length != 10 || data[2] != '-' || data[5] != '-') {
        return datainvalida
    }

    val anostr = "" + data[6] + data[7] + data[8] + data[9]
    val ano = anostr.toInt()
    val messtr = "" + data[3] + data[4]
    val mes = messtr.toInt()
    val diastr = "" + data[0] + data[1]
    val dia = diastr.toInt()

    if (dia !in 1..31) {
        return datainvalida
    }

    if (mes !in 1..12) {
        return datainvalida
    }

    if (ano !in 1900..2022) {
        return datainvalida
    }

    if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
        if (dia > 30) {
            return datainvalida
        }
    }

    if (mes == 2) {
        if ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
            if (dia > 29) {
                return datainvalida
            }
        } else {
            if (dia > 28) {
                return datainvalida
            }
        }
    }

    val numero = "$anostr$messtr$diastr".toInt()
    if (numero >= 20041101) {
        return "Menor de idade nao pode jogar"
    }

    return null
}

fun criaLegendaVertical(linha: Int, mostraLegendaHorizontal: Boolean): String {
    var textofinal = ""

    if (!mostraLegendaHorizontal) {
        textofinal += "|"
    }
    if (mostraLegendaHorizontal) {
        if (linha + 1 >= 10) {
            textofinal += "${linha + 1} |"
        } else {
            textofinal += " ${linha + 1} |"
        }
    }

    return textofinal
}

fun criaLegendaHorizontal(numColunas: Int): String {
    var coluna = 1
    var letra = 'A'
    var letraInt = letra.code
    var legenda = ""

    while (coluna <= numColunas) {
        if (coluna > 1) {
            legenda += " "
        }

        legenda += "$letra"
        if (coluna != numColunas) {
            legenda += " |"
        }

        coluna++
        letraInt++
        letra = letraInt.toChar()
    }
    return legenda
}


fun criaLegendaContadoresHorizontal(contadoresHorizontal: Array<Int?>?): String {

    var textofinal = ""
    for (count in 0 until contadoresHorizontal!!.size) {

        if (contadoresHorizontal[count] == null) {
            if (count == 0) {
                textofinal += "   "
            } else {
                textofinal += "    "
            }

        } else {
            if (contadoresHorizontal.size == 2) {
                if (count == 0) {
                    textofinal += "${contadoresHorizontal[count]}  "
                } else {
                    textofinal += " ${contadoresHorizontal[count]}"
                }
            } else {
                if (count == 0) {
                    textofinal += "${contadoresHorizontal[count]}  "
                } else if (count == contadoresHorizontal.size - 1) {
                    textofinal += " ${contadoresHorizontal[count]}"
                } else {
                    textofinal += " ${contadoresHorizontal[count]}  "
                }
            }

        }
    }

    return textofinal
}

fun criaLegendaContadoresVertical(contadoresVertical: Array<Int?>): String {

    var textofinal = ""


    for (count in 0 until contadoresVertical.size) {

        if (contadoresVertical[count] == null) {
            if (count == 0) {
                textofinal += "   "
            } else {
                textofinal += "    "
            }

        } else {
            if (contadoresVertical.size == 2) {
                if (count == 0) {
                    textofinal += "${contadoresVertical[count]}  "
                } else {
                    textofinal += " ${contadoresVertical[count]}"
                }
            } else {
                if (count == 0) {
                    textofinal += "${contadoresVertical[count]}  "
                } else if (count == contadoresVertical.size - 1) {
                    textofinal += " ${contadoresVertical[count]}"
                } else {
                    textofinal += " ${contadoresVertical[count]}  "
                }
            }

        }
    }

    return textofinal

}


fun leContadoresDoFicheiro(numLines: Int, numColumns: Int, verticais:
Boolean): Array<Int?> {

    val contadores: Array<Int?> = arrayOfNulls(10)

    val file = File("${numLines}x${numColumns}.txt").readLines()

    for (i in file) {

        if (verticais) {
            val vertical = file[0].split(",")
            val result: Array<Int?> = arrayOfNulls(vertical.size)

            for (j in 0..vertical.size - 1) {
                if (vertical[j].toInt() == 0) {
                    result[j] = null

                } else {
                    result[j] = vertical[j].toInt()
                }
            }
            return result

        } else {
            val vertical = file[1].split(",")
            val result: Array<Int?> = arrayOfNulls(vertical.size)

            for (j in 0..vertical.size - 1) {
                if (vertical[j].toInt() == 0) {
                    result[j] = null

                } else {
                    result[j] = vertical[j].toInt()
                }
            }
            return result
        }
    }

    return contadores
}


fun leTerrenoDoFicheiro(numLines: Int,
                        numColumns: Int):
        Array<Array<String?>> {


    val file = File("${numLines}x${numColumns}.txt").readLines()
    val arrei = Array(numLines) { Array<String?>(numColumns) { null } }

    for (i in 2..file.size - 1) {
        val par = file[i].split(',')
        val numlin = par[0].toInt()
        val numcol = par[1].toInt()

        arrei[numlin][numcol] = "A"

    }
    return arrei
}

fun mostraContadorVertical(contadoresVerticais: Array<Int?>?): String {

    val colunasCount = contadoresVerticais!!.size - 1

    var contadorText = ""

    for (u in 0..colunasCount) {
        if (u == colunasCount) {
            if (contadoresVerticais[u] == null) {
                contadorText += "   "
            } else {
                contadorText += "  ${contadoresVerticais[u]}"
            }
        } else {
            if (contadoresVerticais[u] == null) {
                if (u != 0) {
                    if (contadoresVerticais[u - 1] == null) {
                        contadorText += "    "
                    } else {
                        contadorText += "   "
                    }
                } else {
                    contadorText += "   "
                }
            } else {
                if (contadoresVerticais[u + 1] != null) {
                    contadorText += "  ${contadoresVerticais[u]} "
                } else {
                    contadorText += "  ${contadoresVerticais[u]}  "
                }
            }
        }
    }
    return contadorText
}


fun criaTerreno(terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>?, contadoresHorizontais: Array<Int?>?,
                mostraLegendaHorizontal: Boolean = false, mostraLegendaVertical: Boolean = false): String {

    var terrenoText = ""

    if(contadoresVerticais!=null){
        if(mostraLegendaHorizontal){
            terrenoText += "  "
        }
        terrenoText += "   " + mostraContadorVertical(contadoresVerticais) + "\n"
    }

    if (mostraLegendaHorizontal) {
        terrenoText += "     | " + criaLegendaHorizontal(terreno[0].size) + "\n"
    }

    for (u in 0..terreno.size - 1) {
        if(contadoresHorizontais!= null){
            if (contadoresHorizontais[u]!= null) {
                terrenoText += contadoresHorizontais[u]
            }
        }
        if (mostraLegendaVertical) {
            if(contadoresHorizontais!= null){
                if(contadoresHorizontais[u]==null){
                    if (u + 1 >= 10) {
                        terrenoText += "  ${u + 1} |"
                    } else {
                        terrenoText += "   ${u + 1} |"
                    }
                }else{
                    if (u + 1 >= 10) {
                        terrenoText += " ${u + 1} |"
                    } else {
                        terrenoText += "  ${u + 1} |"
                    }
                }

            }else{
                if (u + 1 >= 10) {
                    terrenoText += "  ${u + 1} |"
                } else {
                    terrenoText += "   ${u + 1} |"
                }
            }
        } else {
            if(contadoresHorizontais!= null){
                terrenoText += "  |"
            }else{
                terrenoText += "     |"
            }
        }
        for (i in 0..terreno[0].size - 1) {
            val value = terreno[u][i]
            if (value == null) {
                if (i == terreno[0].size - 1) {
                    terrenoText += "  "
                } else {
                    terrenoText += "   |"
                }
            } else if(value == "A") {
                if (i == terreno[0].size - 1) {
                    terrenoText += " △"
                } else {
                    terrenoText += " △ |"
                }
            } else {
                if (i == terreno[0].size - 1) {
                    terrenoText += " T"
                } else {
                    terrenoText += " T |"
                }
            }
        }
        if(u!=terreno.size-1){
            terrenoText += "\n"
        }
    }
    return terrenoText
}


fun processaCoordenadas(coordenadasStr: String?, numLines: Int, numColumns: Int): Pair<Int, Int>? {


    val partes = coordenadasStr?.split(",")
    val par = Pair(numLines, numColumns)
    val letraInicial = 'A'.code
    val abc = letraInicial + numColumns - 1
    val conversaoLinha = numLines
    var inicio = 1


    if (numLines in 1..10) {
        if (numColumns in 1..10) {
            if (coordenadasStr != null) {
                if (coordenadasStr.length == 3) {
                    if (coordenadasStr[0].toString().toInt() in 1..numLines && coordenadasStr[1].toString() == ",") {
                        if (coordenadasStr[2].code in letraInicial..abc.toString().toInt()) {
                            val coordLines = coordenadasStr[0].toString().toInt()
                            val linhaFinal = coordLines - 1
                            val letra = coordenadasStr[2].code
                            val numero = letra - letraInicial

                            return Pair(linhaFinal, numero)

                        } else {
                            return null
                        }
                    }
                }
            }
        }
    }



    return null
}


fun temArvoreAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {

    val linha = coords.first
    val coluna = coords.second

    return (linha > 0 && terreno[linha - 1][coluna] == "A" ||
            linha < terreno.size - 1 && terreno[linha + 1][coluna] == "A" ||
            coluna > 0 && terreno[linha][coluna - 1] == "A" ||
            coluna < terreno[linha].size - 1 && terreno[linha][coluna + 1] == "A")

}


fun temTendaAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {

    val linha = coords.first
    val coluna = coords.second


    return (linha > 0 && terreno[linha - 1][coluna] == "T" ||
            linha < terreno.size - 1 && terreno[linha + 1][coluna] == "T" ||
            coluna > 0 && terreno[linha][coluna - 1] == "T" ||
            coluna < terreno[linha].size - 1 && terreno[linha][coluna + 1] == "T" ||
            linha > 0 && coluna > 0 && terreno[linha - 1][coluna - 1] == "T" ||
            linha > 0 && coluna < terreno[linha].size - 1 && terreno[linha - 1][coluna + 1] == "T" ||
            linha < terreno.size - 1 && coluna > 0 && terreno[linha + 1][coluna - 1] == "T" ||
            linha < terreno.size - 1 && coluna < terreno[linha].size - 1 && terreno[linha + 1][coluna + 1] == "T")

}


fun contaTendasColuna(terreno: Array<Array<String?>>, coluna: Int): Int {

    var count = 0
    for (linha in 0..terreno.size - 1) {
        if (terreno[linha][coluna] == "T") {
            count++
        }
    }
    return count
}


fun contaTendasLinha(terreno: Array<Array<String?>>, linha: Int): Int {
    var count = 0
    for (coluna in 0..terreno[linha].size - 1) {
        if (terreno[linha][coluna] == "T") {
            count++
        }
    }
    return count
}

fun colocaTenda(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {

    val num = coords.first
    val letra = coords.second

    if (!temTendaAdjacente(terreno, coords) && terreno[num][letra] == null && temArvoreAdjacente(terreno, coords)) {
        terreno[num][letra] = "T"
        return true

    } else if (!temTendaAdjacente(terreno, coords) && terreno[num][letra] == "T" && temArvoreAdjacente(terreno, coords)) {
        terreno[num][letra] = null
        return true

    } else {
        return false
    }

}

fun terminouJogo(terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>, contadoresHorizontais: Array<Int?>): Boolean {

    var verdade = true

    for (colunas in 1 until terreno.size) {
        for (linhas in 1 until terreno.size) {
            if (contaTendasLinha(terreno, linhas) == contadoresVerticais[linhas]) {

                if (contaTendasColuna(terreno, colunas) == contadoresHorizontais[colunas]) {
                    verdade = true
                }
                return false
            }
            return false
        }
    }


    return verdade
}


fun escolheMenu() {
    val opcao = readLine()?.toIntOrNull()

    if (opcao != 0 && opcao != 1) {
        println("Opcao invalida")
        mostrarMenu()
    } else if (opcao == 1) {
        pedirDados()
    }
}

fun mostrarMenu() {
    println(criaMenu())
    escolheMenu()
}

fun pedirDados() {
    var linhas = 0;
    var colunas = 0;

    do {
        println("Quantas linhas?")
        var opcao = readLine()?.toIntOrNull()

        if (opcao == null || opcao < 0) {
            opcao = null
            println("Resposta invalida")
        } else {
            linhas = opcao
        }

    } while (opcao == null)

    do {
        println("Quantas colunas?")

        var opcao = readLine()?.toIntOrNull()

        if (opcao == null || opcao < 0) {
            opcao = null
            println("Resposta invalida")
        } else {
            colunas = opcao
        }

    } while (opcao == null)

    if (!validaTamanhoMapa(linhas, colunas)) {
        println("Terreno invalido")
        mostrarMenu()
        return
    }

    var data = ""
    if (linhas == 10 && colunas == 10) {
        do {
            println("Qual a sua data de nascimento? (dd-mm-yyyy)")
            val opcao = readLine()?.toString()

            val validacao = validaDataNascimento(opcao)
            if (validacao != null) {

                if (validacao == "Menor de idade nao pode jogar") {
                    println(validacao)
                    mostrarMenu()
                    return
                } else {
                    println(validacao)
                }

            } else {
                data = opcao.toString()
            }

        } while (validacao != null)
    }
    val terreno = leTerrenoDoFicheiro(linhas, colunas)
    val contadoresverticais = leContadoresDoFicheiro(linhas, colunas, true)
    val contadoresHorizontal = leContadoresDoFicheiro(linhas, colunas, false)
    println(criaTerreno(terreno, contadoresverticais, contadoresHorizontal, true, true))



    do {
        println("Coordenadas da tenda? (ex: 1,B)")

        val coordenadas = readLine()?.toString()

        var validacao = processaCoordenadas(coordenadas, linhas, colunas)

        var continua = false

        if (coordenadas== "sair") {
            continua = false
            return

        } else if (validacao == null) {
            println("Coordenadas invalidas")
            continua = true

        } else if (colocaTenda(terreno, validacao)) {
            println(criaTerreno(terreno, contadoresverticais, contadoresHorizontal, true, true))
            if(terminouJogo(terreno, contadoresverticais, contadoresHorizontal)){
                println("Parabens! Terminou o jogo!")
                continua = false
            }else{
                continua = true
            }

        }else {
            println("Tenda nao pode ser colocada nestas coordenadas")
            continua = true

        }

    } while (continua)


}


fun main() {
    mostrarMenu()
}

/*
println("Tenda nao pode ser colocada nestas coordenadas")
        println(“Parabens! Terminou o jogo!”)

 */