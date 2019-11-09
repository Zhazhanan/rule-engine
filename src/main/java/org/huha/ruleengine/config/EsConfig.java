package org.huha.ruleengine.config;

import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Description
 * <br> es客户端配置
 *
 * @author WangKun
 * @date 2019/10/30
 **/
@Configuration
@ConfigurationProperties(prefix = "es.host")
@Setter
public class EsConfig {

    public List<String> list;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        HttpHost[] httpHosts = list.stream().map(v -> HttpHost.create(v)).toArray(HttpHost[]::new);
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
