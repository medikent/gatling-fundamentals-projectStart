import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import simulations._

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