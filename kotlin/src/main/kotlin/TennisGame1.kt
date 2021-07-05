import kotlin.math.absoluteValue
import kotlin.math.max

const val DEUCE_UNDER_FORTY = "All"
const val DEUCE_SCORE_NAME = "Deuce"

const val ADVANTAGE_SCORE = "Advantage"
const val WON_SCORE = "Win for"

const val HYPHEN = "-"
const val BLANK = " "

enum class TennisScore(val score: Int, val scoreName: String) {
    LOVE(0, "Love"),
    FIFTEEN(1, "Fifteen"),
    THIRTY(2, "Thirty"),
    FORTY(3, "Forty")
}


class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var playerScore1: Int = 0
    private var playerScore2: Int = 0

    override fun wonPoint(playerName: String) {
        upPlayerScore(playerName)
    }

    private fun upPlayerScore(winnerName : String){
        if (winnerName === player1Name)
            playerScore1 += 1
        else
            playerScore2 += 1
    }

    private fun checkSameScore(player1Score: Int, player2Score: Int) = player1Score == player2Score
    private fun printDeuceScore(point: Int): String {
        return when (point) {
            TennisScore.LOVE.score -> TennisScore.LOVE.scoreName
            TennisScore.FIFTEEN.score -> TennisScore.FIFTEEN.scoreName
            TennisScore.THIRTY.score -> TennisScore.THIRTY.scoreName
            else -> return DEUCE_SCORE_NAME
        } + HYPHEN + DEUCE_UNDER_FORTY
    }

    private fun checkAdvantageOrWin(player1Score: Int, player2Score: Int) = player1Score >= 4 || player2Score >= 4
    private fun printGapType(pointGap: Int) = if (pointGap == 1) {
        ADVANTAGE_SCORE
    } else {
        WON_SCORE
    }

    private fun getOpponentPlayer(player1Score: Int, player2Score: Int, player1Name: String, player2Name: String): String {
        return max(player1Score, player2Score).run {
            if (this == player1Score) {
                player1Name
            } else {
                player2Name
            }
        }
    }

    private fun getPointRes(playerScore: Int): String {
        return when (playerScore) {
            TennisScore.LOVE.score -> TennisScore.LOVE.scoreName
            TennisScore.FIFTEEN.score -> TennisScore.FIFTEEN.scoreName
            TennisScore.THIRTY.score -> TennisScore.THIRTY.scoreName
            else -> TennisScore.FORTY.scoreName
        }
    }

    override fun getScore(): String {
        val player1Score = playerScore1
        val player2Score = playerScore2
        return when {
            checkSameScore(player1Score, player2Score) -> {
                printDeuceScore(player1Score)
            }
            checkAdvantageOrWin(player1Score, player2Score) -> {
                val gap = player1Score.minus(player2Score)
                printGapType(gap.absoluteValue) + BLANK + getOpponentPlayer(player1Score, player2Score, player1Name, player2Name)
            }
            else -> {
                listOf(player1Score, player2Score).joinToString(HYPHEN) { score ->
                    getPointRes(score)
                }
            }
        }
    }
}
