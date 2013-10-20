package no.arktekk.training.spring.task;

import no.arktekk.training.spring.aspect.MonitorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitorLogger {

	@Autowired
	MonitorFactory monitor;

	private static final Log LOG = LogFactory.getLog(MonitorLogger.class);

	@Scheduled(fixedRate = 15000)
	public void countAuctions() {
		if (LOG.isDebugEnabled()) {
			LOG.debug(monitor.describeMonitors());
		}
	}
}
