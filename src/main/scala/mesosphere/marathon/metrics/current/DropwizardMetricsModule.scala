package mesosphere.marathon
package metrics.current

import akka.Done
import akka.actor.ActorRefFactory
import com.codahale.metrics.MetricRegistry
import kamon.metric.SubscriptionsDispatcher.TickMetricSnapshot
import mesosphere.marathon.metrics.current.reporters.statsd.StatsDReporter
import mesosphere.marathon.metrics.{Metrics, MetricsConf}
import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.servlet.ServletContextHandler

class DropwizardMetricsModule(val cliConf: MetricsConf) extends MetricsModule {
  override lazy val servletHandlers: Seq[Handler] = Seq(
    new api.HttpTransferMetricsHandler(new api.HTTPMetricsFilter(metrics)))

  private lazy val registry: MetricRegistry = new MetricRegistry
  override lazy val metrics: Metrics = new DropwizardMetrics(cliConf.metricsNamePrefix(), registry)

  override def instrumentedHandlerFor(servletContextHandler: ServletContextHandler): Handler = servletContextHandler
  override def registerServletInitializer(servletContextHandler: ServletContextHandler): Unit = ()

  override def snapshot(): Either[TickMetricSnapshot, MetricRegistry] = Right(registry)
  override def start(actorRefFactory: ActorRefFactory): Done = {
    if (cliConf.metricsStatsDReporter())
      actorRefFactory.actorOf(StatsDReporter.props(cliConf, registry), "StatsDReporter")
    Done
  }
}
