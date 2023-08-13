package indigo.player

import indigo.data.Card

class ComputerPlayer : PLayer("Computer", true) {


    override fun throwCards(topCard: Card?): Card {
        handCards.forEach { card ->
            print("$card ")
        }
        val n = chooseCard(topCard)
        val card = handCards.removeAt(n)
        println("\n$name plays $card")
        return card
    }

    override fun chooseCard(topCard: Card?): Int {
        // if there are no cards on the table
        if (topCard == null) {
            return indexIfNoCandidates()
        }

        // if there are 2 or more cards with the same [suit] of the top card
        val candidateSuitIndex = indexOfCandidateCard(2) { suit == topCard.suit }
        if (candidateSuitIndex != -1) {
            return candidateSuitIndex
        }

        // if there are 2 or more cards with the same [rank] of the top card
        val candidateRankIndex = indexOfCandidateCard(2) { rank == topCard.rank  }
        if (candidateRankIndex != -1) {
            return candidateRankIndex
        }

        // throw any candidate card
        val candidateCardIndex = indexOfCandidateCard(1) { suit == topCard.suit || rank == topCard.rank }
        if (candidateCardIndex != -1) {
            return candidateCardIndex
        }

        return indexIfNoCandidates()
    }

    private fun indexIfNoCandidates(): Int {
        //If there are cards in hand with the same suit, throw one of them at random (Example 4).
        val suitMap = handCards.groupBy { it.suit }
        val candidateSuit = suitMap.values.firstOrNull { it.size > 1 }
        if (candidateSuit != null) {
            return handCards.indexOf(candidateSuit.random())
        }

        //If there are no cards in hand with the same suit, but there are cards with the same rank
        val rankMap = handCards.groupBy { it.rank }
        val candidateRank = rankMap.values.firstOrNull { it.size > 1 }
        if (candidateRank != null) {
            return handCards.indexOf(candidateRank.random())
        }

        //If there are no cards in hand with the same suit or rank, throw any card at random.
        return handCards.indexOf(handCards.random())
    }


    private fun indexOfCandidateCard(matches: Int, matchProperty: Card.() -> Boolean): Int {
        val candidateCards = handCards.filter { it.matchProperty() }

        if (candidateCards.size >= matches) {
            return handCards.indexOf(candidateCards.random())
        }

        return -1
    }
}