package de.meshcloud.example.springkotlin.util

import de.meshcloud.example.springkotlin.formatted
import org.slf4j.LoggerFactory
import java.util.*

object PerformanceLogger {

  private val log = LoggerFactory.getLogger(PerformanceLogger::class.java)

  // In Java there are Suppliers, Consumers, Predicates, BiConsumers, etc
  // see https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
  fun <T> logPerformance(fn: () -> T, name: String): T {
    log.info("starting '$name' at ${Date().formatted()}")
    val result = fn()
    log.info("finished '$name' at ${Date().formatted()}")
    return result
  }
}
