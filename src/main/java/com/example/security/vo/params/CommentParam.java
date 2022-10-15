package com.example.security.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentParam {
    private String content;
    private long parentId;
    private long articleId;
    private long toUid;
}
