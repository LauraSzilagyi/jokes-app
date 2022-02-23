package com.laura.jokes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikeDislikeJokeDTO {

    private int likes;
    private int dislikes;
}
