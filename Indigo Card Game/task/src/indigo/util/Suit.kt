package indigo.util

enum class Suit(private val symbol: String) {
    DIAMOND("♦"),
    HEART("♥"),
    SPADE("♠"),
    CLUBS("♣");

    override fun toString() = symbol
}
