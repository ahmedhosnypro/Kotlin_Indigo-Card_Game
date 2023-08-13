package indigo

import indigo.data.Card
import kotlin.system.exitProcess

class PLayer(val name: String, private val isComputer: Boolean = false) {
    private var handCards = mutableListOf<Card>()
    private var wonCards = mutableListOf<Card>()


    fun throwCards(): Card {
        return if (isComputer) {
            val card = handCards.removeAt(0)
            println("$name plays $card")
            card
        } else {
            print("Cards in hand: ")
            handCards.forEachIndexed { index, card ->
                print("${index + 1})$card ")
            }

            val n = chooseCard()
            handCards.removeAt(n - 1)
        }
    }

    fun countHandCards() = handCards.size
    fun handCard(card: Card) = handCards.add(card)

    fun winCards(cardsOnTable: List<Card>) = wonCards.addAll(cardsOnTable)
    fun countWonCards() = wonCards.size
    fun wonCardsPoints() = wonCards.sumOf { it.rank.point }

    private fun chooseCard(): Int {
        println("\nChoose a card to play (1-${handCards.size}):")
        val n = readln()
        if (n.equals("exit", true)) {
            println("Game Over")
            exitProcess(0)
        }
        if (!n.matches(Regex("\\d+")) || n.toInt() !in 1..handCards.size) {
            return chooseCard()
        }
        return n.toInt()
    }
}