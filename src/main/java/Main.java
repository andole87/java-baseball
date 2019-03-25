import java.util.Scanner;


public class Main {
    private static int isContinue = 1;

    public static void main(String[] args) {
        while (isContinue == 1) {
            playBall();
        }
    }

    static void playBall() {
        int strike = 0;
        int gameNumber;

        BaseBall bs = new BaseBall();
        gameNumber = bs.getGameNumber();
        do {
            System.out.print("숫자를 입력해주세요 : ");
            String input = getInputString();
            if (!bs.isValid(input)) {
                printInvalidArgMessage();
                continue;
            }
            Scores scores = bs.getReferee(gameNumber, input);
            strike = scores.getStrike();
            System.out.println(scores.getScoreBoard());
        } while (strike != 3);
        printVictory();
        wantContinue();
    }

    private static void printVictory() {
        String message = "3개의 숫자를 모두 맞히셨습니다! 게임종료";
        System.out.println(message);
    }

    private static void wantContinue() {
        int result = 1;
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = getInputString();
        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            printInvalidArgMessage();
            wantContinue();
        }
        if (result != 1 && result != 2) {
            printInvalidArgMessage();
            wantContinue();
        }
        isContinue = result;
    }

    private static void printInvalidArgMessage() {
        System.out.println("유효하지 않은 값입니다.");
    }

    private static String getInputString() {
        String result;
        Scanner sc = new Scanner(System.in);
        result = sc.nextLine();
        return result;
    }
}

class BaseBall {

    private int getRandomNumber() {
        int result;
        java.util.Random rd = new java.util.Random();
        result = (rd.nextInt(899) + 100);

        return result;
    }

    private boolean hasZero(int number) {
        return (Integer.toString(number).contains("0"));
    }

    private boolean hasZero(String str) {
        return str.contains("0");
    }

    private boolean isDuplicate(int number) {
        String strNumber = Integer.toString(number);
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            temp.append(strNumber);
            temp.deleteCharAt(i);
            String fin = temp.toString();
            if (fin.indexOf(strNumber.charAt(i)) != -1) {
                return true;
            }
            temp.setLength(0);
        }
        return false;
    }

    private boolean isDuplicate(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            sb.append(str);
            sb.deleteCharAt(i);
            String fin = sb.toString();
            if (fin.indexOf(str.charAt(i)) != -1) {
                return true;
            }
            sb.setLength(0);
        }
        return false;
    }

    int getGameNumber() {
        int gameNumber;
        do {
            gameNumber = getRandomNumber();
        } while (hasZero(gameNumber) || isDuplicate(gameNumber));

        return gameNumber;
    }

    private boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isInRange(String str) {
        return str.length() == 3;
    }

    boolean isValid(String str) {
        if (isDigit(str) && isInRange(str) && !hasZero(str) && !isDuplicate(str)) {
            return true;
        } else {
            return false;
        }
    }

    Scores getReferee(int gameNumber, String input) {
        int strike = 0;
        int ball = 0;
        String gameString = Integer.toString(gameNumber);

        for (int i = 0; i < 3; i++) {
            char needle = input.charAt(i);

            if (gameString.charAt(i) == needle) {
                strike++;
            } else if (gameString.indexOf(needle) > -1) {
                ball++;
            }
        }
        return new Scores(strike, ball);
    }


}

class Scores {
    private int strike;
    private int ball;

    Scores(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    int getStrike() {
        return strike;
    }

    String getScoreBoard() {
        StringBuilder sb = new StringBuilder();
        if (strike != 0) {
            sb.append(strike + " 스트라이크 ");
        }
        if (ball != 0) {
            sb.append(ball + "볼");
        }
        if (strike == 0 && ball == 0) {
            sb.append("낫싱");
        }
        return sb.toString();
    }
}