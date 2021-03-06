package com.deutsche.groups.dao;

import com.vk.api.sdk.objects.wall.WallpostFull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VkDataDao {

    @Id
    private String id;

    private BigInteger date;
    private String text;
    private BigInteger owner_id;

    public VkDataDao(WallpostFull post) {
        this.id = post.getOwnerId() + "_" + post.getId();
        this.date = BigInteger.valueOf(post.getDate());
        this.text = post.getText();
        this.owner_id = BigInteger.valueOf(post.getOwnerId());
    }
}
