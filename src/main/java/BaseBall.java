/*
 * BaseBall 클래스는 야구게임의 기본 규칙과 로직을 담는다.
 * 메서드별 @param, @return 표시한다.
 */
import java.util.Random;

public class BaseBall {
    /**
     * @return  3자리 랜덤 숫자
     */
    int getRandomNumber() {
        int result;
        Random rd = new Random();
        result = rd.nextInt(899) + 100;
        return result;
    }

    /**
     * @return 3자리 게임에 쓸 수 있는 랜덤 숫자
     */
    int getGameNumber() {
        int gameNumber;

        do{
            gameNumber = getRandomNumber();
        }while(hasZero(gameNumber) || isDuplicate(gameNumber));

        return gameNumber;
    }

    /**
     *
     * @param   string 문자 형식의 숫자
     * @return  0을 포함하면 true
     */
    boolean hasZero(String string) {
        return string.contains("0");
    }

    /**
     * Overload
     *
     * @param number 세자리 숫자
     * @return 0을 포함하면 true
     */
    boolean hasZero(int number) {
        return hasZero(Integer.toString(number));
    }

    /**
     * 인자에 중복이 있는지 확인
     * @param   string 문자 형식의 숫자
     * @return  중복이 있으면 true
     */
    boolean isDuplicate(String string) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            sb.append(string);
            sb.deleteCharAt(i);
            String haystack = sb.toString();
            if (haystack.indexOf(string.charAt(i)) != -1) {
                return true;
            }
            sb.setLength(0);
        }

        return false;
    }

    /**
     * Overload
     *
     * @param number 숫자
     * @return 중복이 있으면 true
     */
    boolean isDuplicate(int number) {
        return isDuplicate(Integer.toString(number));
    }

    /**
     *숫자로 바꿀 수 있는 문자인지 확인한다.
     * @param   string 숫자문자
     * @return  숫자로 바꿀 수 있으면 true
     */
    boolean isDigit(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * 문자열 길이가 3인지 확인
     *
     * @param string 문자
     * @return 3이면 true
     */
    boolean isInRange(String string) {
        return string.length() == 3;
    }
}
