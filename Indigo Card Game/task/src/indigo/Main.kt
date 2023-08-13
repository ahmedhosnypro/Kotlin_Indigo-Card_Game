package indigo

import indigo.player.ComputerPlayer
import indigo.player.HumanPlayer
import indigo.player.PLayer
import kotlin.system.exitProcess

fun main() {
    println("Indigo Card Game")
    val playFirst = whoIsFirst()
    val firstPlayer: PLayer = if (playFirst) HumanPlayer() else ComputerPlayer()
    val secondPLayer: PLayer = if (playFirst) ComputerPlayer() else HumanPlayer()
    Deck(firstPlayer, secondPLayer).start()
}

fun whoIsFirst(): Boolean {
    println("Play first?")
    val action = readln().lowercase()
    if (action.equals("exit", true)) {
        println("Game Over")
        exitProcess(0)
    }
    if (action != "yes" && action != "no") {
        return whoIsFirst()
    }
    return action == "yes"
}
