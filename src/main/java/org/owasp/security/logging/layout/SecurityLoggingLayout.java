package org.owasp.security.logging.layout;

import org.owasp.security.logging.mdc.MDCFilter;
import org.slf4j.MDC;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

public class SecurityLoggingLayout extends LayoutBase<ILoggingEvent> {

	private static final Object LINE_SEP = System.getProperty("line.separator");
	private String prefix = "";
	private boolean printThreadName = true;

	public String doLayout(ILoggingEvent event) {
		StringBuffer sbuf = new StringBuffer(128);
		if (prefix != null) {
			sbuf.append(prefix + ": ");
		}
		sbuf.append(event.getTimeStamp()
				- event.getLoggerContextVO().getBirthTime());
		sbuf.append(" ");
		sbuf.append(event.getLevel());
		sbuf.append(event.getLoggerName());
		sbuf.append(" - ");
		sbuf.append(MDC.get(MDCFilter.LOGIN_ID));
		sbuf.append("@");
		sbuf.append(MDC.get(MDCFilter.IPADDRESS));
		sbuf.append(" ");
		sbuf.append(event.getFormattedMessage());
		sbuf.append(LINE_SEP);
		return sbuf.toString();
	}
}