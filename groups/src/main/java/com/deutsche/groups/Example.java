package com.deutsche.groups;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.SneakyThrows;

import java.util.List;

public class Example {
    @SneakyThrows
    public static void main(String[] args) {
        final String access_token = "16f6b79a16f6b79a16f6b79a431685465e116f616f6b79a49974c3ab0586c14c33c640c";

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        ServiceActor actor = new ServiceActor(7598532, access_token);//authResponse.getAccessToken());
        List<GroupFull> resp = vk.groups().getById(actor).groupId("tproger").execute();
        System.out.println(resp.get(0).getId());
        GetResponse getResponse = vk.wall().get(actor)
                .ownerId(-30666517)
                .count(3)
                .execute();
        getResponse.getItems().forEach(System.out::println);
        System.out.println(getResponse.getItems().get(4));
    }
}

