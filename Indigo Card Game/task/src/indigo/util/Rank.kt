package indigo.util

enum class Rank(private val symbol: String) {
    KING("K"),
    QUEEN("Q"),
    JACK("J"),
    TEN("10"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ACE("A");

    override fun toString() = symbol
}