package com.dius.tennis

import com.dius.tennis.Game.{Advantage, Deuce, Player, Score}
import org.scalatest.{FunSpec, GivenWhenThen, Inside, Matchers}


/**
  * Created by sgopan on 22/01/17.
  */
class ScoreSpec extends FunSpec with GivenWhenThen with Inside with Matchers {

  describe("Score") {

    it("should create a score object") {

      Given("two players  \"player1\" and  \"player2\" ")
      val score = Score(Player("player1"), Player("player2"))

      Then("score should have player1 with name equal to  given input name \"player1\" ")
      assert("player1".equals(score.player1.name))

      And("player1.point should be 0")
      assert(score.player1.point == 0)

      And("player1.status should be None")
      assert(score.player1.status.isEmpty)

      Then("score should have player2 with name equal to  given input name \"player2\" ")
      assert("player2".equals(score.player2.name))

      And("player2.point should be 0")
      assert(score.player2.point == 0)

      And("player2.status should be None")
      assert(score.player2.status.isEmpty)

    }

    it("should allow changing of point  for \"player1\"") {

      Given("two players  \"player1\" and  \"player2\" ")
      val score = Score(Player("player1"), Player("player2"))

      When("a point is changed to 15 for player1")
      score.player1.point = 15

      Then("score should have player1 with name equal to  given input name \"player1\" ")
      assert("player1".equals(score.player1.name))

      And("player1.point should be 15")
      assert(score.player1.point == 15)

      And("player1.status should be None")
      assert(score.player1.status.isEmpty)

      Then("score should have player2 with name equal to  given input name \"player2\" ")
      assert("player2".equals(score.player2.name))

      And("player2.point should be 0")
      assert(score.player2.point == 0)

      And("player2.status should be None")
      assert(score.player2.status.isEmpty)

    }

    it("should allow changing of point  for \"player2\"") {

      Given("two players  \"player1\" and  \"player2\" ")
      val score = Score(Player("player1"), Player("player2"))

      When("a player2.point is changed to 15")
      score.player2.point = 15

      Then("score should have player1 with name equal to  given input name \"player1\" ")
      assert("player1".equals(score.player1.name))

      And("point should be 0 for player2")
      assert(score.player1.point == 0)

      And("player1.status should be None")
      assert(score.player1.status.isEmpty)

      Then("score should have player2 with name equal to  given input name \"player2\" ")
      assert("player2".equals(score.player2.name))

      And("player2.point should be 15")
      assert(score.player2.point == 15)

      And("player2.status should be None")
      assert(score.player2.status.isEmpty)

    }


    it("should allow changing of point and  status for \"player1\"") {

      Given("two players  \"player1\" and  \"player2\" ")
      val score = Score(Player("player1"), Player("player2"))

      When("a point is changed to 15 for player1")
      score.player1.point = 15

      When("a status is changed to Deuce for player1")
      score.player1.status = Some(Deuce)

      Then("score should have player1 with name equal to  given input name \"player1\" ")
      assert("player1".equals(score.player1.name))

      And("player1.point should be 15")
      assert(score.player1.point == 15)

      And("player1.status should be None")
      assert(score.player1.status.contains(Deuce))

      Then("score should have player2 with name equal to  given input name \"player2\" ")
      assert("player2".equals(score.player2.name))

      And("player2.point should be 0")
      assert(score.player2.point == 0)

      And("player2.status should be None")
      assert(score.player2.status.isEmpty)

    }

    it("should allow changing of point and  status for \"player2\"") {

      Given("two players  \"player1\" and  \"player2\" ")
      val score = Score(Player("player1"), Player("player2"))

      When("a point is changed to 15 for player2")
      score.player2.point = 15

      When("a status is changed to Deuce for player2")
      score.player2.status = Some(Deuce)

      Then("score should have player1 with name equal to  given input name \"player1\" ")
      assert("player1".equals(score.player1.name))

      And("player1.point should be 15")
      assert(score.player1.point == 0)

      And("player1.status should be None")
      assert(score.player1.status.isEmpty)

      Then("score should have player2 with name equal to  given input name \"player2\" ")
      assert("player2".equals(score.player2.name))

      And("player2.point should be 0")
      assert(score.player2.point == 15)

      And("player2.status should be None")
      assert(score.player2.status.contains(Deuce))

    }


    it("should allow changing of point and  status for \"player1\" and  \"player2\"") {

      Given("two players  \"player1\" and  \"player2\" ")
      val score = Score(Player("player1"), Player("player2"))

      When("a point is changed to 15 for player1")
      score.player1.point = 15

      When("a status is changed to Deuce for player1")
      score.player1.status = Some(Advantage)

      When("a point is changed to 15 for player2")
      score.player2.point = 15

      When("a status is changed to Deuce for player2")
      score.player2.status = Some(Deuce)

      Then("score should have player1 with name equal to  given input name \"player1\" ")
      assert("player1".equals(score.player1.name))

      And("player1.point should be 15")
      assert(score.player1.point == 15)

      And("player1.status should be None")
      assert(score.player1.status.contains(Advantage))

      Then("score should have player2 with name equal to  given input name \"player2\" ")
      assert("player2".equals(score.player2.name))

      And("player2.point should be 0")
      assert(score.player2.point == 15)

      And("player2.status should be None")
      assert(score.player2.status.contains(Deuce))

    }


  }

}
