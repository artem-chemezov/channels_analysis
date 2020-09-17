package com.deutsche.groups;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class GroupConf {

    @Bean
    public HttpTransportClient httpTransportClient(){
        return new HttpTransportClient();
    }

    @Bean
    public VkApiClient vkApiClient(TransportClient transportClient){
        return new VkApiClient(transportClient);
    }

    @Bean
    public ServiceActor serviceActor(@Value("$appId") int appId, @Value("$accessToken") String accessToken){
        return new ServiceActor(appId, accessToken);
    }
}
