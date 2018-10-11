package io.medici.loadtesting.gatling.simulations.examples

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.body.StringBody
import io.medici.loadtesting.gatling.baseconfig.examples.BaseSimulation

import scala.util.Random

class CustomGameFeeder extends BaseSimulation {

  var idNumbers = (11 to 20).iterator
  val rnd = new Random()

  def randomString(length: Int) = {
    rnd.alphanumeric.filter(_.isLetter).take(length).mkString
  }

  val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  val now = LocalDate.now()
  def getRandomDate(startDate: LocalDate, random: Random): String = {
    startDate.minusDays(random.nextInt(30)).format(pattern)
  }




  val customFeeder = Iterator.continually(Map(
    "gameId" -> idNumbers.next(),
    "name" -> (s"Game-${randomString(5)}"),
    "releaseDate" -> getRandomDate(now, rnd),
    "reviewScore" -> rnd.nextInt(100),
    "category" -> s"Category-${randomString(6)}",
    "rating" -> s"Rating-${randomString(4)}"
  ))

  def postNewGames() = {
    repeat(5) {
      feed(customFeeder)
        .exec(http("Post New Game")
          .post("videogames/")
            .body(ElFileBody("NewGameTemplate.json")).asJSON
          .check(status.is(200)))
        .pause(1)
    }
  }

  val scn = scenario("Video Game DB Custom Feeder")
    .exec(postNewGames())

  setUp(scn.inject(atOnceUsers(1)))
    .protocols(httpConf)

}
