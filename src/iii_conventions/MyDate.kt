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

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate> {
    override operator fun contains(date: MyDate): Boolean {
        return start <= date && date <= endInclusive
    }
}
