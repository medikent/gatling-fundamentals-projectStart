import baseconfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends BaseSimulation {

  // 1 Common HTTP Configuration
  // get from BaseSimulation

  // 2 Scenario Definition
  val scn = scenario("Get All Games Scenario")
    .exec(http("Get All Games")
      .get("videogames"))

  // 3 Load Scenario
  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)


}
