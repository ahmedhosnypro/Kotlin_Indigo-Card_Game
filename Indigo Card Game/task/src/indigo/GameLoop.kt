package indigo

import indigo.data.Card

class GameLoop(private val deck: Deck) {
    private var firstPlayerScore = 0
    private var secondPlayerScore = 0
    private var tableSnapshot: List<Card>? = null
    private var isOver = false

    fun start() {
        deck.setup()
        while (!isOver) {
            println()
            deck.printTableCards()
            if (distributeIfNeeded()) break
            receiveCard(deck.currentPlayer.throwCards())
            deck.currentPlayer = if (deck.currentPlayer == deck.humanPlayer) deck.computerPlayer else deck.humanPlayer
        }
    }


    private fun receiveCard(card: Card): Boolean {
        val tableTopCard = deck.peak()
        deck.putCard(card)
        if (card.rank == tableTopCard?.rank || card.suit == tableTopCard?.suit) {
            winCurrentPlayerTableCards()
            calcScore()
            deck.lastPlayerWon = deck.currentPlayer
            println("${deck.currentPlayer.name} wins cards")
            printScore()
            printWonCards()
        }
        return isOver
    }

    private fun printScore() {
        println("Score: ${deck.humanPlayer.name} $firstPlayerScore - ${deck.computerPlayer.name} $secondPlayerScore")
    }

    private fun printWonCards() {
        println(
            "Cards: ${deck.humanPlayer.name} ${deck.humanPlayer.countWonCards()} - " + "${deck.computerPlayer.name} ${deck.computerPlayer.countWonCards()}"
        )
    }

    private fun calcScore() {
        firstPlayerScore = deck.humanPlayer.wonCardsPoints()
        secondPlayerScore = deck.computerPlayer.wonCardsPoints()

        if (firstPlayerScore + secondPlayerScore == 23) {
            calcLastScore()
        }
    }

    private fun calcLastScore() {
        deck.lastPlayerWon?.let { winAllCards(it) } ?: winAllCards(deck.humanPlayer)

        firstPlayerScore = deck.humanPlayer.wonCardsPoints()
        secondPlayerScore = deck.computerPlayer.wonCardsPoints()

        val firstPlayerCardCount = deck.humanPlayer.countWonCards()
        val secondPlayerCardCount = deck.computerPlayer.countWonCards()

        if (firstPlayerCardCount == secondPlayerCardCount) {
            firstPlayerScore += 3
        } else {
            if (firstPlayerCardCount > secondPlayerCardCount) {
                firstPlayerScore += 3
            } else {
                secondPlayerScore += 3
            }
        }
        isOver = true
    }

    private fun winCurrentPlayerTableCards() {
        tableSnapshot = deck.tableSnapshot()
        deck.currentPlayer.winCards(deck.winTableCards())
    }

    private fun winAllCards(pLayer: PLayer) {
        pLayer.winCards(deck.winAllCards())
    }

    /**
     * When both players have no cards in hand, Six cards are dealt to each player unless there are no more remaining
     * cards in the card deck.
     * Returns true if there are no more remaining cards in the [deck].
     */
    private fun distributeIfNeeded(): Boolean {
        if (deck.currentPlayer.countHandCards() == 0) {
            if (deck.cards.isEmpty()) {
                calcLastScore()
                printScore()
                printWonCards()
                println("Game Over")
                return true
            } else {
                deck.distributeCards()
            }
        }
        return false
    }
}