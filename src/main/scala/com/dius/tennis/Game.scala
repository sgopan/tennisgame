  package com.dius.tennis

  import com.dius.tennis.Game.{Advantage, Deuce, Player, Score}

  /**
    * Created by sgopan on 22/01/17.
    */

  /**
    * Companion object for Game class to hold all the case classes
    */
  object Game {

    //Common trait for Deuce and Advantage
    sealed trait Status

    //Represents Advantage status in a game
    case object Advantage extends Status

    //Represents Deuce status in a game
    case object Deuce extends Status

    /**
      * Represents a Player in a game.    *
      * @param name   - The name of the player
      * @param point  - The point in a game (0,15, 30, 40, 60)
      * @param status - Its a Deuce, Advantage or None(default)
      */
    case class Player(name: String, var point: Int = 0, var status: Option[Status] = None)

    /**
      * Represents Score in a Game    *
      * @param player1 - The first player  object
      * @param player2 - - The second player  object
      */
    case class Score(player1: Player, player2: Player)
    
  }

  /**
    * This class represents a game in a tennis. The rules of the game are described below
    * A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
    * The running score of each game is described in a manner peculiar to tennis: scores from zero to three points are described
    * as 0, 15, 30, 40, respectively. If at least three points have been scored by each player, and the scores are equal, 
    * the score is "deuce". If at least three points have been scored by each side and a player has one more point than his 
    * opponent, the score of the game is "advantage" for the player in the lead.
    *
    * @param player1 - The name of the first player
    * @param player2 - The name of the second player
    */
  class Game(player1: String, player2: String) {

    private final val INCREMENT_BY_TEN = 10

    private final val INCREMENT_BY_FIFTEEN = 15

    private final val INCREMENT_BY_TWENTY = 20

    // current score
    private val gameScore = Score(Player(player1), Player(player2))


    /**
      * This method will update a point for the player 
      * @param player - The name of the player who won the point
      */
    def pointWonBy(player: String): Unit = {

      val FirstPlayer = player1
      val SecondPayer = player2

      player match {
        case FirstPlayer => updateScoreFor1stPlayer()
        case SecondPayer => updateScoreFor2ndPlayer()
      }

    }

    /**
      * This method capture game scenarios for first player and updates the score
      */
    private def updateScoreFor1stPlayer(): Unit = {

      this.gameScore match {
        //Player 1 entering Deuce.
        case Score(Player(_, 30, None), Player(_, 40, None)) => 
          this.gameScore.player1.point = this.gameScore.player1.point + INCREMENT_BY_TEN
          this.gameScore.player1.status = Some(Deuce)
          this.gameScore.player2.status = Some(Deuce)

        //Player1 entering Advantage.  
        case Score(Player(_, 40, Some(Deuce)), Player(_, 40, Some(Deuce))) => 
          this.gameScore.player1.status = Some(Advantage)
          this.gameScore.player2.status = None

        //Player2 is in Advantage state. Player1 wins a point and status returns to Deuce
        case Score(Player(_, 40, None), Player(_, 40, Some(Advantage))) => 
          this.gameScore.player1.status = Some(Deuce)
          this.gameScore.player2.status = Some(Deuce)

        //Player1 is in Advantage  state. Player1 wins a point and wins the game
        case Score(Player(_, 40, Some(Advantage)), Player(_, 40, None)) => 
          this.gameScore.player1.point = this.gameScore.player1.point + INCREMENT_BY_TWENTY
          this.gameScore.player1.status = None
          this.gameScore.player2.status = None

        // If current point is 30 increment by 10
        case Score(Player(_, 30, None), _) => this.gameScore.player1.point += INCREMENT_BY_TEN 

        // If current point is 40 increment by 20
        case Score(Player(_, 40, None), _) => this.gameScore.player1.point += INCREMENT_BY_TWENTY 

        //Any Other case simply increment the point
        case _ => this.gameScore.player1.point += INCREMENT_BY_FIFTEEN 

      }
    }

    /**
      * This method captures game scenarios for second player and and updates the score
      */
    private def updateScoreFor2ndPlayer(): Unit = {

      this.gameScore match {
        //Player 2 entering Deuce.
        case Score(Player(_, 40, None), Player(_, 30, None)) => 
          this.gameScore.player2.point = this.gameScore.player2.point + INCREMENT_BY_TEN
          gameScore.player2.status = Some(Deuce)
          gameScore.player1.status = Some(Deuce)

        //Player2 entering Advantage.
        case Score(Player(_, 40, Some(Deuce)), Player(_, 40, Some(Deuce))) => 
          this.gameScore.player2.status = Some(Advantage)
          this.gameScore.player1.status = None

        //Player1 is in Advantage state. Player2 wins a point and status returns to Deuce
        case Score(Player(_, 40, Some(Advantage)), Player(_, 40, None)) => 
          this.gameScore.player1.status = Some(Deuce)
          this.gameScore.player2.status = Some(Deuce)

        //Player2 is in Advantage  state. Player2 wins a point and wins the game
        case Score(Player(_, 40, None), Player(_, 40, Some(Advantage))) => 
          this.gameScore.player2.point = this.gameScore.player2.point + INCREMENT_BY_TWENTY
          this.gameScore.player2.status = None
          this.gameScore.player1.status = None

        //If current point is 30 increment by 10
        case Score(_, Player(_, 30, None)) => this.gameScore.player2.point += INCREMENT_BY_TEN 

        // If current point is 40 increment by 20
        case Score(_, Player(_, 40, None)) => this.gameScore.player2.point += INCREMENT_BY_TWENTY 

        //Any Other case simply increment th point
        case _ => this.gameScore.player2.point = this.gameScore.player2.point + INCREMENT_BY_FIFTEEN 

      }
    }

    /**
      * This method will return the current score for the tennis game.
      * @return - current score
      */
    def score(): String = {

      this.gameScore match {

        //Score at Deuce
        case Score(Player(_, 40, Some(Deuce)), Player(_, 40, Some(Deuce))) => "Deuce"

        //Player 1 Advantage
        case Score(Player(player, 40, Some(Advantage)), _) => "Advantage " + player

        //Player 2 Advantage
        case Score(_, Player(player, 40, Some(Advantage))) => "Advantage " + player

        //Player 1 wins
        case Score(Player(player, 60, None), _) => player + " wins"

        //Player 2 wins
        case Score(_, Player(player, 60, None)) => player + " wins"

        //All other cases, return the points separated by -
        case Score(Player(_, point1, _), Player(_, point2, _)) => point1 + "-" + point2
      }

    }

  }
