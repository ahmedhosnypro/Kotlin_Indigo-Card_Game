package indigo

import indigo.player.ComputerPlayer
import indigo.player.HumanPlayer
import kotlin.system.exitProcess

fun main() {
    println("Indigo Card Game")
    val humanFirst = whoIsFirst()
    GameLoop(
        Deck(
            HumanPlayer(),
            ComputerPlayer(),
            humanFirst
        )
    ).start()
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