package day4

import filereader.ResourcesFileReader


private fun main() {
    val drawnNumbersTrue = "87,12,53,23,31,70,37,79,95,16,72,9,98,92,5,74,17,60,96,80,75,11,73,33,3,84,81,2,97,93,59,13,77,52,69,83,51,64,48,82,7,49,20,8,36,66,19,0,99,41,91,78,42,40,62,63,57,39,55,47,29,50,58,34,27,43,30,35,22,28,4,14,26,32,10,88,46,65,90,76,38,6,71,67,44,68,86,25,21,24,56,94,18,89,61,15,1,45,54,85"
//    val drawnNumbersExample = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1"

    val boards = getBoards()
    val drawnNumbers = drawnNumbersTrue.split(",").map { it.toInt() }

    val losingExample = getLosingScore(boards, drawnNumbers)
    println(losingExample)
}

private fun getBoards(): List<List<List<Int>>> {
    return ResourcesFileReader().getStringValues("day4-1.txt")
        .asSequence()
        .map { it.split("[ \\t]+".toRegex()) }
        .map { strings -> strings
            .filter { string -> string.isNotEmpty() }
            .map { string -> string.toInt() } }
        .chunked(6)
        .map { it.filter { s -> s.isNotEmpty() } }
        .filter { it.isNotEmpty() }
        .toList()
}

private fun getLosingScore(boards: List<List<List<Int>>>, drawnNumbers: List<Int>): Int {
    val boardsByTurnsToWin = boards.groupBy { getTurnsToWin(it, drawnNumbers) }
    val boardsThatWinLast = boardsByTurnsToWin.maxByOrNull { it.key }
    val turns = boardsThatWinLast?.key ?: drawnNumbers.lastIndex
    val minimumNumbersDrawn = drawnNumbers.subList(0, turns + 1)
    val winningBoards = boardsThatWinLast?.value ?: emptyList()
    return winningBoards.map { calculateScore(it, minimumNumbersDrawn) }
        .minOrNull()
        ?: 0
}

//private fun getWinningScore(boards: List<List<List<Int>>>, drawnNumbers: List<Int>): Int {
//    val boardsByTurnsToWin = boards.groupBy { getTurnsToWin(it, drawnNumbers) }
//        .minByOrNull { it.key }
//    val turns = boardsByTurnsToWin?.key ?: drawnNumbers.lastIndex
//    val minimumNumbersDrawn = drawnNumbers.subList(0, turns)
//    val winningBoards = boardsByTurnsToWin?.value ?: emptyList()
//    return winningBoards.map { calculateScore(it, minimumNumbersDrawn) }
//        .maxOrNull()
//        ?: 0
//}

private fun getTurnsToWin(board: List<List<Int>>, drawnNumbers: List<Int>): Int {
    var turnsToWin = 0
    while (turnsToWin <= drawnNumbers.size) {
        val currentDrawnNumbers = drawnNumbers.subList(0, turnsToWin + 1)
        if (isWinning(board, currentDrawnNumbers)) {
            return turnsToWin
        }
        turnsToWin++
    }
    return Int.MAX_VALUE
}

private fun isWinning(board: List<List<Int>>, drawnNumbers: List<Int>): Boolean {
    val horizontal = board.any { row -> drawnNumbers.containsAll(row) }
    val vertical = board[1].indices.any {
        val column = board.map { currentBoard -> currentBoard[it] }
        drawnNumbers.containsAll(column)
    }
    return horizontal || vertical
}

private fun calculateScore(board: List<List<Int>>, drawnNumbers: List<Int>): Int {
    if (!isWinning(board, drawnNumbers)) {
        return 0
    }

    var sumUnmarkedNumbers = 0
    board.flatten()
        .filter { !drawnNumbers.contains(it) }
        .forEach { sumUnmarkedNumbers += it }
    return sumUnmarkedNumbers * drawnNumbers.last()
}