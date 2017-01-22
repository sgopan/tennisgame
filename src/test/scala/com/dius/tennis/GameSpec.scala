package com.dius.tennis

import org.scalatest.{GivenWhenThen, Outcome}

/**
  * Created by sgopan on 22/01/17.
  */
class GameSpec extends org.scalatest.fixture.FunSpec with GivenWhenThen {

  type FixtureParam = Game

  override def withFixture(test: OneArgTest): Outcome = {
    val game = new Game("player1", "player2")
    test(game)
  }


  describe("Game") {

    it("should give score as \"0-0\" at start of game") { game =>
      assert("0-0".equals(game.score()))
    }

    it("should allow player1 to gain  15 points") { game =>

      Given("score is 0-0")

      When("player1 wins a point")
      game.pointWonBy("player1")

      Then("score should be 15-0 ")
      assert("15-0".equals(game.score()))
    }

    it("should allow player2 to gain  15 points") { game =>

      Given("score is 0-0")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be 0-15 ")
      assert("0-15".equals(game.score()))
    }

    it("should allow player1 and player2 to gain  15 points") { game =>

      Given("score is 0-0")

      When("player1 wins a point")
      game.pointWonBy("player1")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be 15-15 ")
      assert("15-15".equals(game.score()))
    }

    it("should allow player1 to gain 15 points") { game =>

      Given("score is 15-15")
      game.pointWonBy("player1")
      game.pointWonBy("player2")

      When("player1 wins a point")
      game.pointWonBy("player1")

      Then("score should be 30-15 ")
      assert("30-15".equals(game.score()))
    }


    it("should allow player2 to gain 15 points") { game =>

      Given("score is 15-15")
      game.pointWonBy("player1")
      game.pointWonBy("player2")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be 15-30 ")
      assert("15-30".equals(game.score()))
    }

    it("should allow player1 to gain  30 points") { game =>

      When("player1 wins two consecutive point")
      game.pointWonBy("player1")
      game.pointWonBy("player1")

      Then("score should be 30-0 ")
      assert("30-0".equals(game.score()))
    }

    it("should allow player2 to gain  30 points") { game =>

      When("player1 wins two consecutive point")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      Then("score should be 0-30 ")
      assert("0-30".equals(game.score()))
    }

    it("should allow player2 to gain 30 points") { game =>

      Given("score is 15-15")
      game.pointWonBy("player1")
      game.pointWonBy("player2")

      When("player1 wins a point")
      game.pointWonBy("player1")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be 30-30 ")
      assert("30-30".equals(game.score()))
    }

    it("should allow player1 to gain 10 points") { game =>

      Given("score is 30-30")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player1 wins a point")
      game.pointWonBy("player1")

      Then("score should be 40-30 ")
      assert("40-30".equals(game.score()))
    }

    it("should allow player2 to gain 10 points") { game =>

      Given("score is 30-30")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be 30-40 ")
      assert("30-40".equals(game.score()))
    }

    it("should allow to game to go in Deuce when player1 gains 10") { game =>

      Given("score is 30-40")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player1 wins a point")
      game.pointWonBy("player1")

      Then("score should be Deuce ")
      assert("Deuce".equals(game.score()))
    }

    it("should allow to game to go in Deuce when player2 gains 10") { game =>

      Given("score is 30-40")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be Deuce ")
      assert("Deuce".equals(game.score()))
    }

    it("should allow to player1 go in to Advantage") { game =>

      Given("score is Deuce")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player1 wins a point")
      game.pointWonBy("player1")

      Then("score should be Advantage player 1 ")
      assert("Advantage player1".equals(game.score()))
    }

    it("should allow to player2 go in to Advantage") { game =>

      Given("score is Deuce")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be Advantage player 2 ")
      assert("Advantage player2".equals(game.score()))
    }

    it("should allow the game come back to Deuce, scenario1") { game =>

      Given("score is Advantage player2")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player1 wins a point")
      game.pointWonBy("player1")

      Then("score should be Deuce ")
      assert("Deuce".equals(game.score()))
    }

    it("should allow the game come back to Deuce, scenario2") { game =>

      Given("score is Advantage player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player1")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be Deuce ")
      assert("Deuce".equals(game.score()))
    }


    it("should allow  player1 to win") { game =>

      Given("score is Advantage player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player1")

      When("player1 wins a point")
      game.pointWonBy("player1")

      Then("score should be player1 wins")
      assert("player1 wins".equals(game.score()))
    }

    it("should allow  player2 to win") { game =>

      Given("score is Advantage player2")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player2 wins a point")
      game.pointWonBy("player2")

      Then("score should be player2 wins")
      assert("player2 wins".equals(game.score()))
    }

    it("should allow to player1 to win Game with straight 4 points") { game =>

      Given("score is 0-0")


      When("player1 wins a 4 points")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")

      Then("score should be player1 wins")
      assert("player1 wins".equals(game.score()))
    }

    it("should allow to player2 to win Game with straight 4 points") { game =>

      Given("score is 0-0")

      When("player2 wins a 4 points")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      Then("score should be player2 wins")
      assert("player2 wins".equals(game.score()))
    }

    it("should allow to player1 to win Game with straight 3 points") { game =>

      Given("score is 15-15")
      game.pointWonBy("player1")
      game.pointWonBy("player2")

      When("player1 wins a 3 points")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player1")


      Then("score should be player1 wins")
      assert("player1 wins".equals(game.score()))
    }

    it("should allow to player2 to win Game with straight 3 points") { game =>

      Given("score is 15-15")
      game.pointWonBy("player1")
      game.pointWonBy("player2")

      When("player1 wins a 3 points")
      game.pointWonBy("player2")
      game.pointWonBy("player2")
      game.pointWonBy("player2")


      Then("score should be player2 wins")
      assert("player2 wins".equals(game.score()))
    }

    it("should allow to player1 to win Game with straight 2 points") { game =>

      Given("score is 30-30")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player1 wins a 2 point")
      game.pointWonBy("player1")
      game.pointWonBy("player1")

      Then("score should be player1 wins")
      assert("player1 wins".equals(game.score()))
    }

    it("should allow to player2 to win Game with straight 2 points") { game =>

      Given("score is 30-30")
      game.pointWonBy("player1")
      game.pointWonBy("player1")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      When("player2 wins a 2 point")
      game.pointWonBy("player2")
      game.pointWonBy("player2")

      Then("score should be player2 wins")
      assert("player2 wins".equals(game.score()))
    }


  }

}
