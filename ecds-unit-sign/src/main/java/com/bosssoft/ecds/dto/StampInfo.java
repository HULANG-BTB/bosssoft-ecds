package com.bosssoft.ecds.dto;



import com.itextpdf.text.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/15
 * @Content: 电子签章位置信息
 *      设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StampInfo {

    /**
     * stamp的位置信息 new Rectangle(300, 600, 630, 500)
     *      签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
     *      四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
     */
    @NotNull
    private Rectangle location;

    /**
     * 页码
     */
    @Pattern(regexp = " /^[0-9]+$/ ",message = "页码必须为大于0的正整数")
    private int page;

    /**
     * 签章名称
     */
    private String stampName;

}
