package io.medici.loadtesting.gatling.simulations.examples

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.medici.loadtesting.gatling.baseconfig.examples.BaseSimulation

class CheckResponseCode extends BaseSimulation {

  val scn = scenario("Video Game DB")
    .exec(http("Get all video games 1st call")
      .get("videogames")
      .check(status.is(200)))

    .exec(http("Get specific game")
      .get("videogames/1")
      .check(status.in(200 to 210)))

    .exec(http("Get all video games 2nd call")
    .get("videogames")
    .check(status.not(404), status.not(500)))

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
