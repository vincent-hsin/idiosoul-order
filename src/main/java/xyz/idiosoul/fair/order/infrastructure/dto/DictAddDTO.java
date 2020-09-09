package xyz.idiosoul.fair.order.infrastructure.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 数据字典-新建
 *
 * @author xinw
 */
@ApiModel("新增数据字典")
@Data
public class DictAddDTO {

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text.trim();
    }

    public void setSeq(Integer seq) {
        if (seq == null) {
            seq = 0;
        }
        this.seq = seq;
    }

    @ApiModelProperty("代码")
    @NotNull(message = "代码不能为空")
    private Integer code;
    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String text;
    private Integer seq;
    private String remark;


}
