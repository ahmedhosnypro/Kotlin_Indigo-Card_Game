package indigo.player

import indigo.data.Card
import kotlin.system.exitProcess

class HumanPlayer : PLayer("Player") {
    override fun throwCards(topCard: Card?): Card {
        print("Cards in hand: ")
        handCards.forEachIndexed { index, card ->
            print("${index + 1})$card ")
        }

        val n = chooseCard(topCard)
        return handCards.removeAt(n - 1)
    }

    override fun chooseCard(topCard: Card?): Int {
        println("\nChoose a card to play (1-${handCards.size}):")
        val n = readln()
        if (n.equals("exit", true)) {
            println("Game Over")
            exitProcess(0)
        }
        if (!n.matches(Regex("\\d+")) || n.toInt() !in 1..handCards.size) {
            return chooseCard(topCard)
        }
        return n.toInt()
    }
}