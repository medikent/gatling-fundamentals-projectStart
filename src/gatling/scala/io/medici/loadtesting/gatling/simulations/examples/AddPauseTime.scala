package io.medici.loadtesting.gatling.simulations.examples

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.medici.loadtesting.gatling.baseconfig.examples.BaseSimulation

import scala.concurrent.duration.DurationInt

class AddPauseTime extends BaseSimulation {

  val scn = scenario("Video Game DB")
    .exec(http("Get all video games - 1st call")
    .get("videogames"/*URL*/))
    .pause(500.milliseconds)

    .exec(http("Get specific game")
    .get("videogames/1"))
    .pause(100.milliseconds, 200.milliseconds)

    .exec(http("Get all video games - 2nd call")
    .get("videogames"))
    .pause(300.milliseconds)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
