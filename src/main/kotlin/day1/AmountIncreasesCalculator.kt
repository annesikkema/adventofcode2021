package day1

class AmountIncreasesCalculator {

    fun getIntValues(values: List<Int>): Int {

        val iterator = values.iterator()

        var previousValue: Int
        var currentValue = iterator.next()

        var amountOfIncreases = 0

        while (iterator.hasNext()) {
            previousValue = currentValue
            currentValue = iterator.next()

            if (currentValue > previousValue) {
                amountOfIncreases++
            }
        }

        return amountOfIncreases
    }

}