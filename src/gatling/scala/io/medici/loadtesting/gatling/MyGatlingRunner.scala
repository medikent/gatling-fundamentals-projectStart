package io.medici.loadtesting.gatling

import java.util.UUID

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import io.medici.loadtesting.gatling.simulations._
import io.medici.loadtesting.gatling.security.BitsyUserSecurity
import io.medici.loadtesting.gatling.simulations.examples._
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.wallet.Wallet

object MyGatlingRunner {

  def main(args: Array[String]): Unit = {

    val props = new GatlingPropertiesBuilder
    props
      .simulationClass(classOf[MyFirstTest].getName)
      .simulationClass(classOf[AddPauseTime].getName)

    Gatling.fromMap(props.build)
  }

}

object CheckResponseCoderunner {
  def main(args:Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[CheckResponseCode].getName)
    Gatling.fromMap(props.build)
  }
}

object CheckResponseBodyRunner {
  def main(args:Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[CheckResponseBodyAndExtract].getName)
    Gatling.fromMap(props.build)
  }
}

object CodeReuseRunner {
  def main(args:Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[CodeReuseWithObjects].getName)
    Gatling.fromMap(props.build)
  }
}

object CsvFeederRunner {
  def main(args:Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[CsvFeeder].getName)
    Gatling.fromMap(props.build)
  }
}

object UserMessageRunner {
  def main(args: Array[String]): Unit = {
    val netParams = MainNetParams.get()
    val wallet = new Wallet(netParams)
    val userId = UUID.randomUUID().toString
    val deviceId = "asdf1234"
    val email = "admin@bitsy.com"
    val message = BitsyUserSecurity.generateRegistrationMessage(
      wallet,
      netParams,
      userId,
      deviceId,
      email
    )
    println(s"Message from wallet: ${message.toString()}")
  }
}

object CustomFeederRunner {
  def main(args: Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[CsvFeederToCustom].getName)
    Gatling.fromMap(props.build)
  }
}

object CustomGameFeederRunner {
  def main(args: Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[CustomGameFeeder].getName)
    Gatling.fromMap(props.build)
  }
}

object BasicLoadRunner {
  def main(args: Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[BasicLoadSimulation].getName)
    Gatling.fromMap(props.build)
  }
}

object RampUsersRunner {
  def main(args: Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[RampUsersLoadSimulation].getName)
    Gatling.fromMap(props.build)
  }
}

object FixedDurationRunner {
  def main(args: Array[String]): Unit = {
    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[FixedDurationLoadSimulation].getName)
    Gatling.fromMap(props.build)
  }
}