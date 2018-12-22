package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate>{
    override operator fun compareTo(other: MyDate): Int {
        return when{
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }

    operator fun plus(timeInterval: TimeInterval): MyDate {
        return this.addTimeIntervals(timeInterval, 1)
    }

    operator fun plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
        return this.addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.num)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(num: Int): RepeatedTimeInterval{
    return RepeatedTimeInterval(this, num)
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val num: Int)

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override operator fun contains(other: MyDate): Boolean {
        return start <= other && other <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        return DateIterator(this)
    }
}

class DateIterator(val dateRange: DateRange): Iterator<MyDate> {
    private var date: MyDate = dateRange.start

    override fun hasNext(): Boolean {
        return date <=  dateRange.endInclusive
    }

    override fun next(): MyDate {
        val tempDay = date
        date = date.nextDay()
        return tempDay
    }
}
