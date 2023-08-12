package indigo.data

import indigo.util.Rank
import indigo.util.Suit

data class Card(val suit: Suit, val rank: Rank) {
    override fun toString() = "$rank$suit"
}