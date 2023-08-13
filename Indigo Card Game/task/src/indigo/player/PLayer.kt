package indigo.player

import indigo.data.Card

abstract class PLayer(val name: String, private val isComputer: Boolean = false) {
    var handCards = mutableListOf<Card>()
    private var wonCards = mutableListOf<Card>()


    abstract fun throwCards(topCard: Card?): Card
    abstract fun chooseCard(topCard: Card?): Int

    fun countHandCards() = handCards.size
    fun handCard(card: Card) = handCards.add(card)

    fun winCards(cardsOnTable: List<Card>) = wonCards.addAll(cardsOnTable)
    fun countWonCards() = wonCards.size
    fun wonCardsPoints() = wonCards.sumOf { it.rank.point }

}