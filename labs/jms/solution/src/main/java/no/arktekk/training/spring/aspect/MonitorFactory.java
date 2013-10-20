package no.arktekk.training.spring.aspect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Component
public class MonitorFactory {
	private Map<String, Monitor> monitors = new HashMap<String, Monitor>();

	public Monitor monitor(String monitorName) {
		if (!monitors.containsKey(monitorName)) {
			monitors.put(monitorName, new JamonMonitor(
					com.jamonapi.MonitorFactory.start(monitorName)));
		}
		return monitors.get(monitorName);
	}

	public String describeMonitors() {
		StringBuffer sb = new StringBuffer();
		sb.append("Usage statistics for ").append(monitors.keySet().size())
				.append(" monitored methods");
		for (String key : monitors.keySet()) {
			Monitor monitor = monitors.get(key);
			sb.append("\n\tMETHOD: ")
					.append(key.substring(key.lastIndexOf(".") + 1))
					.append(", Number of calls: ").append(monitor.hits())
					.append(", Average call time (ms): ")
					.append(monitor.averageCallTime());
		}
		// sb.append("\n");
		return sb.toString();
	}
}
