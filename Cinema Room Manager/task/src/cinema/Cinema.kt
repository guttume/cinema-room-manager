package cinema

fun main() {
    // write your code here
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val cols = readLine()!!.toInt()
    val totalSeats = rows * cols
    var selectedSeat: Int
    var selectedRow: Int
    var seatsSold = 0
    var seatsSoldPercent = seatsSold / (totalSeats + 0.00)
    var currentIncome = 0
    val totalIncome = if (totalSeats <= 60) totalSeats * 10 else ((cols * (rows / 2)) * 10) + (cols * ((rows - (rows / 2))) * 8)
    var ans: Int
    val seats = Array(rows) { CharArray(cols) }

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            seats[i][j] = 'S'
        }
    }

    while (true) {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        ans = readLine()!!.toInt()
        println()

        when (ans) {
            1 -> {
                showTheSeats(seats, rows, cols)
            }
            2 -> {
                while(true) {
                    println("Enter a row number:")
                    selectedRow = readLine()!!.toInt()
                    println("Enter a seat number in that row:")
                    selectedSeat = readLine()!!.toInt()
                    if (selectedRow < 1 || selectedRow > rows || selectedSeat < 1 || selectedSeat > cols) {
                        println("Wrong input!")
                        println()
                        continue
                    }
                    if (seats[selectedRow - 1][selectedSeat - 1] == 'B') {
                        println("That ticket has already been purchased!")
                        println()
                        continue
                    }
                    seats[selectedRow - 1][selectedSeat - 1] = 'B'
                    seatsSold++
                    seatsSoldPercent = seatsSold / (totalSeats + 0.00)
                    val ticketPrice: Int = if (totalSeats <= 60) 10 else calculateTicketPrice(rows / 2, selectedRow)
                    currentIncome += ticketPrice

                    println("Ticket price: $$ticketPrice")
                    println()
                    break
                }
            }
            3 -> {
                println("Number of purchased tickets: $seatsSold")
                println("Percentage: %.2f".format(seatsSoldPercent * 100) + "%")
                println("Current income: $$currentIncome")
                println("Total income: $$totalIncome")
            }
            else -> break
        }
    }
}

private fun showTheSeats(seats: Array<CharArray>, rows: Int, cols: Int) {
    println("Cinema:")
    for (i in 0..cols) {
        if (i == 0) {
            print(" ")
        } else {
            print(i)
        }
        print(" ")
    }

    println()

    for (i in 0 until rows) {
        print("${i + 1} ")
        for (j in 0 until cols) {
            print("${seats[i][j]} ")
        }
        println()
    }
}

fun calculateTicketPrice(rows: Int, row: Int): Int {
    return if (row in 1..rows) 10 else 8
}
