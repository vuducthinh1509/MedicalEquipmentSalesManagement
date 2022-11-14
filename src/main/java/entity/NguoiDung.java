package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class NguoiDung {
    private String tentaikhoan;

    private String password;

    private Integer id;

    private Integer role;
}