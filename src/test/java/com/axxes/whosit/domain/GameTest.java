package com.axxes.whosit.domain;

import com.axxes.whosit.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    private static List<Staff> staffs = new ArrayList<Staff>();
    {
        for (int i = 0; i <= 50; i++){
            Staff staff = new Staff();
            staff.setId(i+1);
            staff.setFirstName("Naam" + i);
            staff.setLastName("Achternaam" + i);
            staff.setGender( i%2==0? Gender.MALE : Gender.FEMALE);
            staff.setPictureUrl("test"+i);

            staffs.add(staff);
        }
    }

    private static Staff user = new Staff(21, "tester", "tester", Gender.MALE, "urlTester");

    private static AxxesUser axxesUser = new AxxesUser(21, user, "tester.tester@axxes.com", "tester");

    private Game game;

    @Before
    public void setUp(){
        game = new Game(staffs);
    }

    @Test
    public void testGenerateRandomAnswers(){
        assertThat(game.getRounds().size()).isEqualTo(20);

        for (int i = 0; i<20; i++){
            Round round = game.getRound(i);
            int firstIndex = game.getRounds().indexOf(round);
            int lastIndex = game.getRounds().lastIndexOf(round);
            assertThat(firstIndex).isEqualTo(lastIndex);
        }
    }

    @Test
    public void testIsCorrect(){
        int roundIndex = 0;
        Long correctAnswer = game.getRound(roundIndex).getCorrectAnswer();

        assertThat(correctAnswer).isEqualTo(game.getRound(roundIndex).getStaff().getId());
    }
}
