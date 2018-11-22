package com.sell.viewobject;

import lombok.*;

/**
 * Created by huhaoran on 2018/11/21 0021.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ResultVO {
    @NonNull
    private int code;
    @NonNull
    private String msg;
    private Object data;
}
