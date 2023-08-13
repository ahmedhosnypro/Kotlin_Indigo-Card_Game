package indigo

import kotlin.system.exitProcess

fun main() {
    println("Indigo Card Game")
    val humanFirst = whoIsFirst()
    GameLoop(
        Deck(
            PLayer("Player"),
            PLayer("Computer", true),
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