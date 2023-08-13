package indigo.player

import indigo.data.Card

class ComputerPlayer : PLayer() {
    override fun play(): Card {
        val card = cards.removeAt(0)
        println("Computer plays $card")
        return card
    }
}