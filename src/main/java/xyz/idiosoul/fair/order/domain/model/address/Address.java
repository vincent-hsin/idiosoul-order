package xyz.idiosoul.fair.order.domain.model.address;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import xyz.idiosoul.fair.order.infrastructure.EntityBase;
import xyz.idiosoul.fair.order.vo.AddressVO;

import javax.persistence.Entity;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
public class Address extends EntityBase<Integer> {
    private int userId;
    private String regionCode;
    private String regionName;
    private String address;
    private String contactName;
    private String contactMobile;
    private boolean isDefault; // default是保留字

    protected Address() {
        // for Hibernate
    }

    public Address(int userId, String regionCode, String regionName, String address, String contactName,
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
     * 查看地址
     *
     * @return
     */
    public AddressVO view() {
        return new AddressVO(id, contactName, contactMobile, regionCode, regionName, address,
                getFullAddress(), isDefault);
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
