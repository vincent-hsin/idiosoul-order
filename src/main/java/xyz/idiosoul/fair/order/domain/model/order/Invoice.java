package xyz.idiosoul.fair.order.domain.model.order;

import lombok.Data;
import xyz.idiosoul.fair.order.constant.InvoiceTitleTypeEnum;
import xyz.idiosoul.fair.order.constant.InvoiceTypeEnum;

@Data
//@ApiModel("发票信息")
public class Invoice {
//    @ApiModelProperty("发票类型[纸质，电子，增值税]")
    private InvoiceTypeEnum invoiceType;
//    @ApiModelProperty("发票title类型[个人，公司]")
    private InvoiceTitleTypeEnum invoiceTitleType;
//    @ApiModelProperty("（公司or个人）title名称")
    private String invoiceTitle;
//    @ApiModelProperty("公司税号")
    private String invoiceTaxpayerNo;
//    @ApiModelProperty("收件人电邮")
    private String invoiceReceiverEmail;
}