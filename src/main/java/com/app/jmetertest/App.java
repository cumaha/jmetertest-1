package com.app.jmetertest;

import java.util.Random;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.reporters.MailerResultCollector;
import org.apache.jmeter.samplers.SampleResult;

public class App extends AbstractJavaSamplerClient {
	private String label = "app";

	private String param;

	public void setupTest(JavaSamplerContext arg0) {
		System.out.println("setupTest");
	}

	public SampleResult runTest(JavaSamplerContext arg0) {
		param = arg0.getParameter("param");

		SampleResult sr;
		sr = new SampleResult();
		sr.setSampleLabel(label);
		try {
			sr.sampleStart();
			Random random = new Random();
			Thread.sleep(random.nextInt(1000));
			sr.setSuccessful(true);
			sr.setResponseCodeOK();
			sr.setRequestHeaders("200");
		} catch (Throwable e) {
			sr.setSuccessful(false);
		} finally {
			sr.sampleEnd();
		}
		return sr;
	}

	public void teardownTest(JavaSamplerContext arg0) {
	}

	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("param", "Hello World");
		return params;
	}
	
	public static void main(String[] args) {
		SampleResult sr;
		sr = new SampleResult();
		sr.setSampleLabel("app");
		try {
			sr.sampleStart();
			sr.setSuccessful(true);
			sr.setResponseCodeOK();
			sr.setRequestHeaders("200");
		} catch (Throwable e) {
			sr.setSuccessful(false);
		} finally {
			sr.sampleEnd();
		}
		MailerResultCollector collector = new MailerResultCollector();
	}

}
