package io.medici.loadtesting.gatling.simulations.examples

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.medici.loadtesting.gatling.baseconfig.examples.BaseSimulation

import scala.concurrent.duration.DurationInt

class FixedDurationLoadSimulation extends BaseSimulation {

  def getAllVideoGames() = {
    exec(
      http("Get all video games")
        .get("videogames")
        .check(status.is(200))
    )
  }

  def getSpecificVideoGame() = {
    exec(http("Get specific video game")
      .get("videogames/2")
      .check(status.is(200)))
  }

  val scn = scenario("Video Game DB")
      .forever() {
        exec(getAllVideoGames())
          .pause(5)
          .exec(getSpecificVideoGame())
          .pause(5)
          .exec(getAllVideoGames())
      }

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      atOnceUsers(10),
      rampUsers(50) over (30 seconds)
    ).protocols(httpConf.inferHtmlResources())
  ).maxDuration(1 minute)

}
