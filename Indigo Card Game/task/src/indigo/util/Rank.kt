package indigo.util

enum class Rank(private val symbol: String, val point: Int) {
    KING("K", 1),
    QUEEN("Q", 1),
    JACK("J", 1),
    TEN("10", 1),
    NINE("9", 0),
    EIGHT("8", 0),
    SEVEN("7", 0),
    SIX("6", 0),
    FIVE("5", 0),
    FOUR("4", 0),
    THREE("3", 0),
    TWO("2", 0),
    ACE("A", 1);

    override fun toString() = symbol
}