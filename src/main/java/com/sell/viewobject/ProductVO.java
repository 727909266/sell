package com.sell.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huhaoran on 2018/11/21 0021.
 */
@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = 7513396658747991492L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private int categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
