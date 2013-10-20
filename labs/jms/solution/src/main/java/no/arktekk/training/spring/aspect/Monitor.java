package no.arktekk.training.spring.aspect;

import org.joda.time.DateTime;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface Monitor {
    Monitor start();

    Monitor stop();

    Double hits();

    Double totalCallTime();

    DateTime lastAccessTime();

    Double averageCallTime();

    Double minimumCallTime();

    Double maximumCallTime();
}
