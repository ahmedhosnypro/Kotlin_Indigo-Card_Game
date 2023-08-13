package indigo

import indigo.data.Card
import indigo.player.PLayer
import indigo.util.Rank
import indigo.util.Suit

class Deck(val humanPlayer: PLayer, val computerPlayer: PLayer, private val humanFirst: Boolean) {
    val cards = mutableListOf<Card>()
    private val tableCards = mutableListOf<Card>()
    var currentPlayer = if (humanFirst) humanPlayer else computerPlayer
    var lastPlayerWon: PLayer? = null


    fun printTableCards() {
        if (tableCards.isEmpty()) {
            println("No cards on the table")
        } else {
            println("${tableCards.size} cards on the table, and the top card is ${peak()}")
        }
    }

    fun setup() {
        cards.clear()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        shuffle()
        repeat(4) {
            tableCards.add(cards.removeAt(0))
        }
        println("Initial cards on the table: ${tableCards.joinToString(" ")}")
        distributeCards()
    }

    private fun shuffle() = cards.shuffle()


    fun distributeCards() {
        repeat(6) {
            humanPlayer.handCard(cards.removeAt(0))
            computerPlayer.handCard(cards.removeAt(0))
        }
    }


    fun peak(): Card? = tableCards.lastOrNull()

    fun winTableCards(): List<Card> {
        val cardsOnTable = tableCards.toList()
        tableCards.clear()
        return cardsOnTable
    }

    fun winAllCards(): List<Card> {
        val wonCards = cards.toMutableList()
        cards.clear()
        wonCards.addAll(winTableCards())
        return wonCards
    }

    fun putCard(card: Card) = tableCards.add(card)

    fun tableSnapshot() = tableCards.toList()
}