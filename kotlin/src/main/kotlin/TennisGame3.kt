class TennisGame3(private val p1N: String, private val p2N: String) : TennisGame {

    private var p2: Int = 0
    private var p1: Int = 0

    override fun getScore(): String {
        if (p1 < 4 && p2 < 4 && p1 + p2 != 6) {
            val p = TennisScore.values().map { it.scoreName }
            val p1Score = p[p1]
            val p2Score = p[p2]
            val result = if (p1 == p2) DEUCE_UNDER_FORTY else p2Score
            return "$p1Score$HYPHEN$result"
        } else if (p1 == p2) {
            return DEUCE_SCORE_NAME
        } else {
            val name = if (p1 > p2) p1N else p2N
            val score = if ((p1 - p2) * (p1 - p2) == 1) ADVANTAGE_SCORE else WON_SCORE
            return "$score $name"
        }
    }

    override fun wonPoint(playerName: String) {
        upPlayerScore(playerName)
    }

    private fun upPlayerScore(winnerName: String) {
        if (winnerName === "player1")
            p1++
        else
            p2++
    }


}
