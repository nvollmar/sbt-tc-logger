package sbt.jetbrains.buildServer.sbtlogger

import sbt.internal.util.{Appender, SuppressedTraceContext}
import sbt.util.{ControlEvent, Level}

final class TeamCityAppender(
                              private[sbt] val name: String,
                              private[sbt] val properties: sbt.internal.util.ConsoleAppender.Properties,
                              private[sbt] val suppressedMessage: SuppressedTraceContext => Option[String],
                              tcLogAppender: _root_.jetbrains.buildServer.sbtlogger.LogAppender,
                              scope: String
                            ) extends Appender {

  private def flowId: String =    Thread.currentThread().threadId.toString

  override def appendLog(level: Level.Value, message: => String): Unit =
    tcLogAppender.log(level.toString, message, flowId)

  override def control(event: ControlEvent.Value, message: => String): Unit =
    tcLogAppender.log(Level.Info.toString, message, flowId)

  override def trace(t: => Throwable, traceLevel: Int): Unit =
    tcLogAppender.log(Level.Error.toString, t.toString, flowId)

  override def close(): Unit = ()
}
