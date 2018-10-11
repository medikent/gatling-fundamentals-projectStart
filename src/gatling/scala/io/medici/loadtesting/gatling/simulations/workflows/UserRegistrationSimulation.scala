package io.medici.loadtesting.gatling.simulations.workflows

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.medici.loadtesting.gatling.security.BitsyUserSecurity
import org.ajbrown.namemachine.NameGenerator
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.wallet.Wallet

class UserRegistrationSimulation extends Simulation {

  val networkParams = TestNet3Params.get()

  val httpConf = http
    .baseURL("http://mobile-edge-service.default.svc.cluster.local:2993/login")
    .header("Content-Type", "application/json")


  val scn = scenario("User Registration Workflow")
    .exec(http("Register a user")
      .post("mobile-register-user")
      .header("BITSY-ACCESS-SIG",
        BitsyUserSecurity.generateRegistrationMessage(
          new Wallet(networkParams),
          networkParams,
          UUID.randomUUID().toString(),
          s"${new NameGenerator().generateName().getFirstName()}.${new NameGenerator().generateName().getLastName()}@bitsy.com",
          "asdf1234").signedMessage)
        .check(status.is(200))
    )
}
