package repository;

import entity.Item;
import entity.KhachHang;
import entity.ThietBi;
import javafx.collections.ObservableList;

import java.sql.*;

public interface ItemRepository{
    public ObservableList<Item> loadDataItem();
}
