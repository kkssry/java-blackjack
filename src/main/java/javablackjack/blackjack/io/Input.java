package javablackjack.blackjack.io;

import javablackjack.blackjack.NotAllowInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    private static final Logger log = LogManager.getLogger(Input.class);

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public int getint() throws IOException {
        return Integer.parseInt(bf.readLine());
    }

    public String getString() throws IOException {
        return bf.readLine();
    }

    public int HitOrStand() throws IOException {
        System.out.println("히트 : 1, 스탠드 : 2");
        int inputNumber = getint();
        if (inputNumber < 1 || inputNumber > 2) {
            throw new NotAllowInputException();
        }
        System.out.println(inputNumber);
        return inputNumber;
    }

    public int choiceHitOrstand() throws IOException {
        int choiceNumber = 0;
        try{
            choiceNumber =HitOrStand();
        }catch (NotAllowInputException e) {
            System.out.println("숫자를 잘못 입력하셨습니다.");
            choiceHitOrstand();
        }
        return choiceNumber;
    }

}
