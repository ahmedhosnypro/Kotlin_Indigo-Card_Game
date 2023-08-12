package indigo

import indigo.data.Card
import indigo.util.Rank
import indigo.util.Suit

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        reset()
    }

    fun reset() {
        cards.clear()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun get(n: Int) {
        if (n > cards.size) {
            println("The remaining cards are insufficient to meet the request.")
        } else {
            repeat(n) {
                print(cards.removeAt(0).toString() + " ")
            }
            println()
        }
    }
}