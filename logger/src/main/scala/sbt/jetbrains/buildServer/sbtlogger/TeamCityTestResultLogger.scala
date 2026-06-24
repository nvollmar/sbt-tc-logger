package sbt.jetbrains.buildServer.sbtlogger

import sbt.{Logger, TestResultLogger}
import sbt.Tests._

class TeamCityTestResultLogger extends TestResultLogger {
  def run(log: Logger, results: Output, taskName: String): Unit = {
    //default behaviour there is
    //TestResultLogger.SilentWhenNoTests.run(log, results, taskName)
    //we will just ignore to prevent appearing of 'exit code 1' when test failed
  }
}