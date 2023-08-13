package indigo

import indigo.data.Card
import indigo.player.PLayer
import indigo.util.Rank
import indigo.util.Suit

class Deck(private val firstPlayer: PLayer, private val secondPlayer: PLayer) {
    private val cards = mutableListOf<Card>()
    private val tableCards = mutableListOf<Card>()
    private var currentPlayer = firstPlayer

    fun start() {
        while (true) {
            if (currentPlayer.countCards() == 0) {
                if (cards.isEmpty()) {
                    printCardsOnTable()
                    println("Game Over")
                    break
                } else {
                    distributeCards()
                }
            }
            printCardsOnTable()
            tableCards.add(currentPlayer.play())
            println()
            currentPlayer = if (currentPlayer == firstPlayer) secondPlayer else firstPlayer
        }
    }

    private fun printCardsOnTable() = println("${tableCards.size} cards on the table, and the top card is ${peak()}")

    init {
        setup()
    }

    private fun setup() {
        cards.clear()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        repeat(4) {
            tableCards.add(cards.removeAt(0))
        }
        println("Initial cards on the table: ${tableCards.joinToString(" ")}")
        distributeCards()
    }

    private fun distributeCards() {
        repeat(6) {
            firstPlayer.add(cards.removeAt(0))
            secondPlayer.add(cards.removeAt(0))
        }
    }

    fun peak(): Card = tableCards.last()
}