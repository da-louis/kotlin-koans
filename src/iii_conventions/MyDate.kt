package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate>{
    override operator fun compareTo(date: MyDate): Int {
        return when{
            year != date.year -> year - date.year
            month != date.month -> month - date.month
            else -> dayOfMonth - date.dayOfMonth
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)
