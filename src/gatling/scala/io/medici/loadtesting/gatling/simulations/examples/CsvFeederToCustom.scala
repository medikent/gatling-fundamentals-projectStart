package io.medici.loadtesting.gatling.simulations.examples

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.medici.loadtesting.gatling.baseconfig.examples.BaseSimulation

class CsvFeederToCustom extends BaseSimulation {

  var idNumbers = (1 to 10).iterator
  val customFeeder = Iterator.continually(Map("gameId" -> idNumbers.next()))

  def getSpecificVideoGame(): ChainBuilder = {
    repeat(10) {
      feed(customFeeder)
        .exec(http("Get Specific Video Game")
          .get("videogames/${gameId}")
          .check(status.is(200)))
        .pause(1)

    }
  }

  val scn = scenario("Video Game DB")
    .exec(getSpecificVideoGame())

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
