package br.com.m4rc310.tv.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tools.ant.Main;

public class MService extends br.com.m4rc310.gql.services.MService implements MConst {

	protected String getPomVersion() {
		Properties properties = new Properties();
        String version = null;
        try (InputStream inputStream = Main.class.getResourceAsStream("/META-INF/maven/br.com.m4rc310/TradeVue/pom.properties")) {
            properties.load(inputStream);
            version = properties.getProperty("version");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return version;
	}
}
