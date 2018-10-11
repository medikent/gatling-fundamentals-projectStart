package io.medici.loadtesting.gatling.baseconfig.examples

// Common imports for all io.medici.loadtesting.gatling.simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BaseSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080/app/")
    .header("Accept", "application/json")
    //.proxy(Proxy("localhost", 8888).httpsPort(8888))

}
