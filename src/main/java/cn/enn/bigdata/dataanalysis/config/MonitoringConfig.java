package cn.enn.bigdata.dataanalysis.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class MonitoringConfig {

    @Value("${app.id}")
    private String appId;

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        String ip = getIp();
        return registry -> registry.config()
                .commonTags("ip", ip, "appId", appId) // (3)
                .meterFilter(MeterFilter.deny(id -> { // (4)
                    String uri = id.getTag("uri");
                    return uri != null && uri.startsWith("/swagger");
                }));
    }

    private String getIp() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ignored) {
        }
        if (ip != null) {
            return ip.getHostAddress();
        }
        return "unknown";
    }
}
