package com.sell.viewobject;

import lombok.*;

import java.io.Serializable;

/**
 * Created by huhaoran on 2018/11/21 0021.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ResultVO implements Serializable {

    private static final long serialVersionUID = 6985033933911398240L;
    @NonNull
    private int code;
    @NonNull
    private String msg;
    private Object data;
}
