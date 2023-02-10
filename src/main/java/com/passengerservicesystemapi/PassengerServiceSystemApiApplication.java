package com.passengerservicesystemapi;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import com.passengerservicesystemapi.utilities.ToolsDTO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication()
@EnableWebSecurity
public class PassengerServiceSystemApiApplication {

	private final ToolsDTO to;
	private static String rootLocationOfLogFiles = null;
	private static String rollingFileName = null;
	private static String instanceName = null;
	private static final SecureRandom random = new SecureRandom();

	public PassengerServiceSystemApiApplication(ToolsDTO to) {
		this.to = to;
	}

	@Bean
	public RestTemplate getresttemplate(){
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(PassengerServiceSystemApiApplication.class, args);
	}


	@SuppressWarnings("rawtypes")
	@PostConstruct
	public void reconfigureLogs() {

		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
		if (rootLocationOfLogFiles == null)
			rootLocationOfLogFiles = System.getenv("rutaLogs");
		if (rootLocationOfLogFiles == null)
			rootLocationOfLogFiles = "logs";

		rootLocationOfLogFiles = rootLocationOfLogFiles
				.concat(File.separator).concat(getInstanceName())
				.concat(File.separator).concat(to.getApplicationName());

		if(rollingFileName == null)
			rollingFileName = to.getApplicationName();

		LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
		standard.addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss} %-5level: %msg%n%throwable");

		AppenderComponentBuilder console = builder.newAppender("Console", "Console");
		console.addAttribute("target", "SYSTEM_OUT");
		console.add(standard);
		builder.add(console);

		AppenderComponentBuilder rollingFile = builder.newAppender("RollingFile", "RollingFile");
		rollingFile.addAttribute("fileName",
				rootLocationOfLogFiles.concat(File.separator).concat(rollingFileName).concat(".log"));
		rollingFile.addAttribute("filePattern",
				rootLocationOfLogFiles.concat(File.separator).concat(rollingFileName).concat("_%d{yyyy-MM-dd}_%i.log"));
		rollingFile.add(standard);

		ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
				.addComponent(builder.newComponent("OnStartupTriggeringPolicy"))
				.addComponent(builder.newComponent("TimeBasedTriggeringPolicy").addAttribute("interval", 1)
						.addAttribute("modulate", true))
				.addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?"))
				.addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));
		rollingFile.addComponent(triggeringPolicies);
		builder.add(rollingFile);

		RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);
		rootLogger.add(builder.newAppenderRef("Console"));
		rootLogger.add(builder.newAppenderRef("RollingFile"));
		builder.add(rootLogger);

		Configurator.reconfigure(builder.build());

	}

	public synchronized static String getInstanceName() {
		if(instanceName != null)
			return instanceName;

		try {
			InetAddress netAddress = InetAddress.getLocalHost();
			if(netAddress != null)
				instanceName = netAddress.getHostName();
		} catch (UnknownHostException e) {
			instanceName = randomString(10, "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			e.printStackTrace();
		}

		return instanceName;
	}

	public static String randomString(int len, String posiblesChars)
	{
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ )
			sb.append( posiblesChars.charAt( random.nextInt(posiblesChars.length()) ) );
		return sb.toString();
	}
}
