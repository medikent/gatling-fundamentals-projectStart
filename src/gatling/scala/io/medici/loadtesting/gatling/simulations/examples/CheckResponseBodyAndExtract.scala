package io.medici.loadtesting.gatling.simulations.examples

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.medici.loadtesting.gatling.baseconfig.examples.BaseSimulation

class CheckResponseBodyAndExtract extends BaseSimulation {

  val scn = scenario("Video Game DB")

    .exec(http("Get specific game")
    .get("videogames/1")
    .check(jsonPath("$.name").is("Resident Evil 4")))

    .exec(http("Get all video games")
    .get("videogames")
    .check(jsonPath("$[1].id").saveAs("gameId")))

    .exec { session => println(session); session}

    .exec(http("get specific game with param")
    .get("videogames/${gameId}")
    .check(jsonPath("$.name").is("Gran Turismo 3"))
    .check(bodyString.saveAs("responseBody")))
    .exec { session => println(session("responseBody").as[String]); session}

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
