package class101.foo.io;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
public class ElasticsearchConfig {
    @Value("#{'${spring.data.elasticsearch.hosts}'.split(',')}")
    private List<String> hosts;

    @Value("${spring.data.elasticsearch.port}")
    private int port;

    @Bean
    public RestHighLevelClient getRestClient() {
        List<HttpHost> httpHosts = new ArrayList<>();
        for (String host : hosts) {
            httpHosts.add(new HttpHost(host, port, "http"));
        }
        RestClientBuilder builder = RestClient.builder(httpHosts.toArray(new HttpHost[httpHosts.size()]));

        return new RestHighLevelClient(builder);
    }
}
