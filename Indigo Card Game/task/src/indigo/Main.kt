package indigo

fun main() {
    val deck = Deck()

    var action = ""
    while (action != "exit") {
        println("Choose an action (reset, shuffle, get, exit):")
        action = readln()
        when (action) {
            "reset" -> {
                deck.reset()
                println("Card deck is reset.")
            }

            "shuffle" -> {
                deck.shuffle()
                println("Card deck is shuffled.")
            }

            "get" -> {
                println("Number of cards:")
                val n = readln()
                if (!n.matches(Regex("\\d+")) || n.toInt() !in 1..52) {
                    println("Invalid number of cards.")
                } else {
                    deck.get(n.toInt())
                }
            }

            "exit" -> {
                println("Bye")
            }

            else -> println("Wrong action.")
        }
    }
}


