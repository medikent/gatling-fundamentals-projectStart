package simulations

import baseconfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class CodeReuseWithObjects extends BaseSimulation {

  val scn = scenario("Video Game DB")
    .exec(getAllVideoGames())
    .pause(5.milliseconds)
    .exec(getSpecificVideoGame())
    .pause(5.milliseconds)
    .exec(getAllVideoGames())


  def getAllVideoGames(): ChainBuilder = {
    repeat(3) {
      exec(http("Get all video games")
        .get("videogames")
        .check(status.is(200)))
    }

  }

  def getSpecificVideoGame(): ChainBuilder = {
    repeat(5) {
      exec(http("Get specific game")
        .get("videogames/1")
        .check(status.in(200 to 210)))
    }
  }

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
