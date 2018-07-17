package mesosphere.marathon
package metrics

import org.rogach.scallop.ScallopConf

trait MetricsConf extends ScallopConf {

  lazy val graphite = opt[String](
    "reporter_graphite",
    descr = "URL to graphite agent. e.g. tcp://localhost:2003?prefix=marathon-test&interval=10 [deprecated since 1.7]",
    noshort = true
  )

  lazy val dataDog = opt[String](
    "reporter_datadog",
    descr = "URL to dogstatsd agent. e.g. udp://localhost:8125?prefix=marathon-test&tags=marathon&interval=10 [deprecated since 1.7]",
    noshort = true
  )

  lazy val averagingWindowSizeSeconds = opt[Long](
    "metrics_averaging_window",
    descr = "The length of the average window on metrics (in seconds) [deprecated since 1.7]",
    noshort = true
  )

  lazy val metricsNamePrefix = opt[String](
    name = "metrics_name_prefix",
    descr = "A prefix that is used when constructing metric names",
    default = Some("marathon"),
    argName = "prefix",
    noshort = true
  )

  lazy val metricsStatsDReporter = toggle(
    name = "metrics_statsd",
    descrYes = "Enable the StatsD reporter.",
    descrNo = "Disable the StatsD reporter.",
    prefix = "disable_",
    noshort = true,
    default = Some(false)
  )

  lazy val metricsStatsDHost = opt[String](
    name = "metrics_statsd_host",
    descr = "A remote hostname for the StatsD reporter.",
    argName = "host",
    validate = _.nonEmpty,
    noshort = true
  )

  lazy val metricsStatsDPort = opt[Int](
    name = "metrics_statsd_port",
    descr = "A remote port for the StatsD reporter.",
    argName = "port",
    validate = _ > 0,
    noshort = true
  )

  lazy val metricsStatsDTransmissionIntervalMs = opt[Long](
    name = "metrics_statsd_transmission_interval_ms",
    descr = "A transmission interval in milliseconds for the StatsD reporter.",
    argName = "interval",
    default = Some(10000),
    validate = _ > 0L,
    noshort = true
  )

  lazy val metricsPrometheusReporter = toggle(
    name = "metrics_prometheus",
    descrYes = "Enable the Prometheus reporter.",
    descrNo = "Disable the Prometheus reporter.",
    prefix = "disable_",
    noshort = true,
    default = Some(false)
  )
}
