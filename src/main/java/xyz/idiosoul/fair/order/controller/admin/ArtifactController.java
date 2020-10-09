package xyz.idiosoul.fair.order.controller.admin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.idiosoul.fair.order.application.service.SkuService;
import xyz.idiosoul.fair.order.dto.SkuAddDTO;
import xyz.idiosoul.fair.order.util.RequestHeaderUtil;

/**
 * @author vincent
 */
@RestController
@RequestMapping("api/v1.0/admin/artifact")
public class ArtifactController {

    private SkuService skuService;

    public ArtifactController(SkuService skuService) {
        this.skuService = skuService;
    }

    @PostMapping
    public void add(@RequestBody SkuAddDTO skuAddDTO) {
        int adminId = RequestHeaderUtil.getAdminId();
        skuService.add(adminId, skuAddDTO);
    }

    @DeleteMapping
    public void delete(long skuId) {
        skuService.delete(skuId);
    }
}
