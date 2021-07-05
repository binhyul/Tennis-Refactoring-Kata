import kotlin.math.absoluteValue

class TennisGame2(private val player1Name: String, private val player2Name: String) : TennisGame {
    private var p1point: Int = 0
    private var p2point: Int = 0

    private var p1res: String = ""
    private var p2res: String = ""

    private fun getPointRes(playerScore: Int): String {
        return when (playerScore) {
            TennisScore.LOVE.score -> TennisScore.LOVE.scoreName
            TennisScore.FIFTEEN.score -> TennisScore.FIFTEEN.scoreName
            TennisScore.THIRTY.score -> TennisScore.THIRTY.scoreName
            else -> TennisScore.FORTY.scoreName
        }
    }

    private fun checkSameScore(player1Score: Int, player2Score: Int) = player1Score == player2Score


    override fun getScore(): String {
        if (checkSameScore(p1point, p2point)) {
            return if (p1point < 3) {
                getPointRes(p1point) + HYPHEN + DEUCE_UNDER_FORTY
            } else {
                DEUCE_SCORE_NAME
            }
        }

        p1res = getPointRes(p1point)
        p2res = getPointRes(p2point)
        val gap = p1point.minus(p2point).absoluteValue
        return if (p1point > p2point) {
            if (p1point < 4) {
                "$p1res-$p2res"
            } else {
                if (gap >= 2) {
                    "$WON_SCORE $player1Name"
                } else {
                    "$ADVANTAGE_SCORE $player1Name"
                }
            }
        } else {
            if (p2point < 4) {
                "$p1res-$p2res"
            } else {
                if (gap >= 2) {
                    "$WON_SCORE $player2Name"
                } else {
                    "$ADVANTAGE_SCORE $player2Name"
                }
            }
        }
    }

    fun SetP1Score(number: Int) {

        for (i in 0 until number) {
            P1Score()
        }

    }

    fun SetP2Score(number: Int) {

        for (i in 0 until number) {
            P2Score()
        }

    }

    fun P1Score() {
        p1point++
    }

    fun P2Score() {
        p2point++
    }

    override fun wonPoint(player: String) {
        if (player === "player1")
            P1Score()
        else
            P2Score()
    }
}