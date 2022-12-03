package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Item {

    private String tenItem;

    private String modelItem;

    private String xuatXuItem;

    private Integer soLuongItem;

    private Integer giaItem;

    private String serialStringItem;

    private Integer totalPriceItem;

    public Item(String tenItem, String modelItem, String xuatXuItem, Integer soLuongItem, Integer giaItem){
        this.tenItem = tenItem;
        this.modelItem = modelItem;
        this.xuatXuItem = xuatXuItem;
        this.soLuongItem = soLuongItem;
        this.giaItem = giaItem;
    }

    public void copyItem(Item item){
        this.tenItem = item.getTenItem();
        this.modelItem = item.getModelItem();
        this.xuatXuItem = item.getXuatXuItem();
        this.giaItem = item.getGiaItem();
        this.soLuongItem = item.getSoLuongItem();
    }
}