package com.dius.tennis

import com.dius.tennis.Game.{Advantage, Deuce, Player}

import org.scalatest.{FunSpec, GivenWhenThen}

/**
  * Created by sgopan on 21/01/17.
  */
class PlayerSpec extends FunSpec with GivenWhenThen {

  describe("A Player") {

    it("should create a player with name ") {

      Given("A player name \"player1\" ")
      val player = Player("player1")

      Then("player.name should be equal  given input name ")
      assert("player1".equals(player.name))

      And("player.point should be 0")
      assert(player.point == 0)

      And("player.status should be None")
      assert(player.status.isEmpty)

    }

    it("should create a player with name and point") {
      
      Given("A player name \"player1\" and point 15")
      val player = Player("player1", 15)

      Then("player.name should be equal given input name ")
      assert("player1".equals(player.name))

      And("player.point should be 15")
      assert(player.point == 15)

      And("player.status should be None")
      assert(player.status.isEmpty)

    }

    it("should create a player with name, point and status") {

      Given("A player name \"player1\" , point 15 and status \"Deuce\" ")
      val player = Player("player1", 15, Some(Deuce))

      Then("player.name should be equal given input name ")
      assert("player1".equals(player.name))

      And("player.point should be 15")
      assert(player.point == 15)

      And("player.status should be None")
      assert(player.status.contains(Deuce))

    }

    it("should allow changing the point") {

      Given("A player name \"player1\" ")
      val player = Player("player1")

      When("a point is changed")
      player.point = 15

      Then("player.name should be equal given input name ")
      assert("player1".equals(player.name))

      And("player.point should be 15")
      assert(player.point == 15)

      And("player.status should be None")
      assert(player.status.isEmpty)

    }

    it("should allow changing the status") {

      Given("A player name \"player1\" ")
      val player = Player("player1")

      When("a status is changed")
      player.status = Some(Deuce)

      Then("player.name should be equal given input name ")
      assert("player1".equals(player.name))

      And("player.point should be 15")
      assert(player.point == 0)

      And("player.status should be Some(Deuce)")
      assert(player.status.contains(Deuce))

    }

    it("should allow changing of point and  status") {

      Given("A player name \"player1\" ")
      val player = Player("player1")

      When("a point is changed")
      player.point = 15

      When("a status is changed")
      player.status = Some(Advantage)

      Then("player.name should be equal given input name ")
      assert("player1".equals(player.name))

      And("player.point should be 15")
      assert(player.point == 15)

      And("player.status should be Some(Advantage)")
      assert(player.status.contains(Advantage))
      
    }

  }

}
