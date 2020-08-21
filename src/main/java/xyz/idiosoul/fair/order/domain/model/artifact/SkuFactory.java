package xyz.idiosoul.fair.order.domain.model.artifact;

import org.springframework.stereotype.Component;
import xyz.idiosoul.fair.order.dto.SkuAddDTO;
import xyz.idiosoul.fair.order.repository.SkuRepository;

/**
 * @author vincent
 */
@Component
public class SkuFactory {
    private SkuRepository skuRepository;

    public SkuFactory(SkuRepository skuRepository) {
        this.skuRepository = skuRepository;
    }

    public Sku of(SkuAddDTO skuAddDTO) {
        Sku sku = new Sku(skuAddDTO.getSize(), skuAddDTO.getColor(), skuAddDTO.getStyle(), skuAddDTO.getUnit(), skuAddDTO.getBrand(), skuAddDTO.getManufacturer(), skuAddDTO.getPrice());
        return skuRepository.save(sku);
    }

    public Sku of(long skuId) {
        return skuRepository.findById(skuId).orElseThrow(() -> new RuntimeException("sku不存在！"));
    }
}
