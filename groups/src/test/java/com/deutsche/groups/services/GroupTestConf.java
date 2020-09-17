package com.deutsche.groups.services;

import com.deutsche.groups.GroupConf;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

@TestConfiguration
public class GroupTestConf {
    @Bean
    public HttpTransportClient httpTransportClient(){
        return new HttpTransportClient();
    }

    @Bean
    public VkApiClient vkApiClient(TransportClient transportClient){
        return new VkApiClient(transportClient);
    }

    @Bean
    public ServiceActor serviceActor(@Value("7598532") int appId, @Value("16f6b79a16f6b79a16f6b79a431685465e116f616f6b79a49974c3ab0586c14c33c640c") String accessToken){
        return new ServiceActor(appId, accessToken);
    }
}
