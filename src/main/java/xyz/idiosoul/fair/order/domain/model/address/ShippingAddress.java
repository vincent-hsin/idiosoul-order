package xyz.idiosoul.fair.order.domain.model.address;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "t_order_address")
@DynamicInsert
@DynamicUpdate
public class ShippingAddress extends EntityBase<Integer> {
    private int userId;
    private String regionCode;
    private String regionName;
    private String address;
    private String contactName;
    private String contactMobile;
    private boolean isDefault; // default是保留字

    protected ShippingAddress() {
        // for Hibernate
    }

    public ShippingAddress(int userId, String regionCode, String regionName, String address, String contactName,
                           String contactMobile) {
        this.userId = userId;
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.address = address;
        this.contactName = contactName;
        this.contactMobile = contactMobile;
    }

    /**
     * 修改地址
     *
     * @param regionCode
     * @param regionName
     * @param address
     * @param contactName
     * @param contactMobile
     */
    public void edit(String regionCode, String regionName, String address, String contactName, String contactMobile) {
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.address = address;
        this.contactName = contactName;
        this.contactMobile = contactMobile;
    }

    /**
     * 获取完整地址
     *
     * @return
     */
    public String getFullAddress() {
        return this.regionName + " " + this.address;
    }

    /**
     * 作为默认地址
     *
     * @return
     */
    public void asDefault() {
        this.isDefault = true;
    }

    /**
     * 取消作为默认地址
     *
     * @return
     */
    public void cancelDefault() {
        this.isDefault = false;
    }

    /**
     * 是否默认地址
     *
     * @return
     */
    public boolean isDefault() {
        return this.isDefault;
    }
}
