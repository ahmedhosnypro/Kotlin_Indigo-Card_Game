package indigo.player

import indigo.data.Card

abstract class PLayer {
    var cards = mutableListOf<Card>()

    fun add(card: Card) {
        cards.add(card)
    }

    abstract fun play(): Card

    fun countCards() = cards.size
}