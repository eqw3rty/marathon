---
title: Metrics
---

# Metrics

Marathon uses [Dropwizard Metrics](https://github.com/dropwizard/metrics)
for its metrics. You can query the current metric values via the
`/metrics` HTTP endpoint.

For the specific syntax see the
[metrics command-line flags]({{ site.baseurl }}/docs/command-line-flags.html#metrics-flags)
section.

## Stability of metric names

Although we try to prevent unnecessary disruptions, we do not provide
stability guarantees for metric names between major and minor releases.

## Metric types

Marathon has the following metric types:

* a `counter` is a monotonically increasing integer, for instance, the
  number of Mesos `revive` calls performed since Marathon became
  a leader.
* a `gauge` is a current measurement, for instance, the number of apps
  currently known to Marathon.
* a `histogram` is a distribution of values in a stream of measurements,
  for instance, the number of apps in group deployments.
* a `meter` measures the rate at which a set of events occur.
* a `timer` is a combination of a meter and a histogram, which measure
  the duration of events and the rate of their occurance.

Histograms and timers use
[Exponentially Decaying Reservoirs](https://metrics.dropwizard.io/4.0.0/manual/core.html#exponentially-decaying-reservoirs).

## Units of measurement

Metrics can be measured in the following units:

* none
* `bytes`
* `seconds`

## Metric names

All metric names are prefixed with `marathon` by default. The prefix can
be changed using `--metrics_prefix` command-line flag.

Metric name components are joined with dots. Components may have dashes
in them.

A metric type and a unit of measurement (if any) are appended to
a metric name. A couple of examples:

* `marathon.apps.active.gauge`
* `marathon.http.event-streams.responses.size.counter.bytes`


## Important metrics

* `marathon.apps.active.gauge` — the number of active apps.
* `marathon.deployments.active.gauge` — the number of active
  deployments.
* `marathon.deployments.counter` — the count of deployments received
  since the current Marathon instance became a leader.
* `marathon.deployments.dismissed.counter` — the count of deployments
  dismissed since the current Marathon instance became a leader.
* `marathon.groups.active.gauge` — the number of active groups.
* `marathon.http.event-streams.responses.size.counter.bytes` — the size
  of data sent to clients over event streams since the current Marathon
  instance became a leader.
* `marathon.http.requests.size.counter.bytes` — the total size of
  all requests since the current Marathon instance became a leader.
* `marathon.http.requests.size.gzipped.counter.bytes` — the total size
  of all gzipped requests since the current Marathon instance became
  a leader.
* `marathon.http.responses.size.counter.bytes` — the total size of all
  responses since the current Marathon instance became a leader.
* `marathon.http.responses.size.gzipped.counter.bytes` — the total size
  of all gzipped responses since the current Marathon instance became
  a leader.
* `marathon.leadership.duration.counter.seconds` — the duration of
  current leadership.
* `marathon.mesos.calls.revive.counter` — the count of Mesos `revive`
  calls made since the current Marathon instance became a leader.
* `marathon.mesos.calls.suppress.counter` — the count of Mesos
  `suppress` calls made since the current Marathon instance became
  a leader.
* `marathon.mesos.offer-operations.launch-group.counter` — the count of
  `LaunchGroup` offer operations made since the current Marathon
  instance became a leader.
* `marathon.mesos.offer-operations.launch.counter` — the count of
  `Launch` offer operations made since the current Marathon instance
  became a leader.
* `marathon.mesos.offer-operations.reserve.counter` — the count of
  `Reserve` offer operations made since the current Marathon instance
  became a leader.
* `marathon.mesos.offers.declined.counter` — the count of offers
  declined since the current Marathon instance became a leader.
* `marathon.mesos.offers.incoming.counter` — the count of offers
  received since the current Marathon instance became a leader.
* `marathon.mesos.offers.used.counter` — the count of offers used since
  the current Marathon instance became a leader.
* `marathon.persistence.gc.compaction-duration.counter.seconds` — the
  total time spent in the Marathon GC compaction phase by the current
  instance.
* `marathon.persistence.gc.runs.counter` — the count of Marathon GC runs
  since it became a leader.
* `marathon.persistence.gc.scanning-duration.counter.seconds` — the
  total time spend in the Marathon GC scanning phase by the current
  instance.
* `marathon.tasks.launched.counter` — the count of tasks launched by
  the current Marathon instance since it became a leader.
* `marathon.tasks.running.gauge` — the number of running tasks at the
  moment.
* `marathon.tasks.staged.gauge` — the number of tasks staged at the
  moment.
* `marathon.uptime.counter.seconds` — uptime of the current Marathon
  instance.

To be completed.
