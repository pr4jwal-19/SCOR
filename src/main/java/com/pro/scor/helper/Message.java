package com.pro.scor.helper;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Message {

    private String content;
    @Builder.Default
    private MsgType type = MsgType.blue;

}
