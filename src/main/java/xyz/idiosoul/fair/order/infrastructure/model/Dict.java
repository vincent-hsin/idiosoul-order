package xyz.idiosoul.fair.order.infrastructure.model;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.Entity;

/**
 * 字典实体类
 */
@Entity
@Getter
@NoArgsConstructor
public class Dict extends EntityBase<Long> {
    public interface View {
    }

    private String label;
    @JsonView(View.class)
    private Integer code;
    @JsonView(View.class)
    private String text;
    @JsonView(View.class)
    private String remark;
    private Integer seq;

    public Dict(String label, Integer code, String text, Integer seq, String remark) {
        this.label = label;
        this.code = code;
        this.text = text;
        this.seq = seq;
        this.remark = remark;
    }
}
