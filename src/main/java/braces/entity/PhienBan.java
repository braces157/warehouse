
package braces.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhienBan {
    private String maPhienBanSP;
    private Integer maSP;
    private Integer mauSac;
    private Integer maRam;
    private Integer maRom;
    private Integer soLuongTon;
    private Integer giaBan;
    private Integer giaNhap;
}
