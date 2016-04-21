package ohtu;

public class TennisGame {
    
    private int player1_score = 0;
    private int player2_score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1_score += 1;
        else
            player2_score += 1;
    }

    public String getScore() {
        if (player1_score==player2_score){
            return createTie();
        } else if (player1_score>=4 || player2_score>=4) {
            return createAdvantageOrWin();
        } else {
            return createScore();
        }
    }

    private String createScore() {
        String score="";
        score+= nameScore(player1_score);
        score+="-";
        score += nameScore(player2_score);
        return score;
    }

    private String nameScore(int tempScore) {
        switch(tempScore){
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            default:
                return "Forty";
        }
    }

    private String createAdvantageOrWin() {
        int difference = player1_score-player2_score;
        if (player1_score > player2_score) {
            return leader(difference, player1Name);
        } else {
            return leader(difference, player2Name);
        }
    }
    
    private String leader(int difference, String playerName) {
        if (difference == 1 || difference == -1) {
            return "Advantage " + playerName;
        } else {
            return "Win for " + playerName;
        }
    }
    
    private String createTie() {
        if (player1_score == 4) {
            return "Deuce";
        } else {
            return nameScore(player1_score) + "-All";
        }
    }
}