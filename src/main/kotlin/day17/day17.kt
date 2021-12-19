package day17

private const val FILE_NAME = "day17-1.txt"

fun main() {
    val targetAreaRange = TargetAreaReader(FILE_NAME).getTargetAreaRange()
    val probeLauncher = ProbeLauncher(targetAreaRange)
    val highestPosition = probeLauncher.getHighestPositionThatReachesTarget()
    println(highestPosition)
}