package repository;

import entity.NguoiDung;

public interface NguoiDungRepository {
    public NguoiDung dangnhap(String tentaikhoan, String password);
}