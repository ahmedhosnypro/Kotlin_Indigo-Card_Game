package indigo.player

import indigo.data.Card
import kotlin.system.exitProcess

class HumanPlayer : PLayer() {
    override fun play(): Card {
        print("Cards in hand: ")
        cards.forEachIndexed { index, card ->
            print("${index + 1})$card ")
        }

        val n = chooseCard()
        return cards.removeAt(n - 1)
    }

    private fun chooseCard(): Int {
        println("\nChoose a card to play (1-${cards.size}):")
        val n = readln()
        if (n.equals("exit", true)) {
            println("Game Over")
            exitProcess(0)
        }
        if (!n.matches(Regex("\\d+")) || n.toInt() !in 1..cards.size) {
            return chooseCard()
        }
        return n.toInt()
    }
}