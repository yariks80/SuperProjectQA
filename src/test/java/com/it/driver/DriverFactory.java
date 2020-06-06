package com.it.driver;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.it.common.Constants.BASE_URL;

class DriverFactory {

    public static WebDriver getDriver() {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        BrowserMobProxy proxy = getProxyServer();
//        Proxy seleniumProxy = getSeleniumProxy(proxy);
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        WebDriver driver = null;
        String driverName = System.getProperty("driver");
        if (driverName != null) {
            if ("fireFox".equals(driverName)) {
                System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver26.exe");
                driver = new FirefoxDriver();
            } else if ("chrome".equals(driverName)) {
                System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver83.exe");
                driver = new ChromeDriver();;
            }

        } else {
            System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver83.exe");
            driver = new ChromeDriver();;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      //  Har har = proxy.newHar();
        driver.get(BASE_URL);
//        List<HarEntry> entries = har.getLog().getEntries();
//        for(HarEntry entry :entries){
//            System.out.println(entry.getRequest().getUrl());
//        }
        return driver;

    }

    public static BrowserMobProxy getProxyServer() {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
// above line is needed for application with invalid certificates
        proxy.start();
        return proxy;
    }

    public static Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
        try {
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Assert.fail("invalid Host Address");
        }
        return seleniumProxy;
    }
}