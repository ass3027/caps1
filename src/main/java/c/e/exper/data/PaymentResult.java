package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResult {
    String user_id;
    String pay_price;
    String pay_id;

    public PaymentResult(String user_id, String pay_price) {
        this.user_id = user_id;
        this.pay_price = pay_price;
    }
}
