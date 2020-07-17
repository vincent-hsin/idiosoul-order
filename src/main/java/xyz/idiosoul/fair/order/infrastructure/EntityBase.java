package xyz.idiosoul.fair.order.infrastructure;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity的公共基类
 *
 * @param <T>
 */
@Getter
@MappedSuperclass
public abstract class EntityBase<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    protected LocalDateTime createTime, modifyTime;
    protected Integer createUserId, modifyUserId;
    @Column(name = "is_deleted")
    protected boolean deleted;

    protected String features, future;

    public void delete() {
        deleted = true;
    }
}
