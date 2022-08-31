/**
 * @author Yadunand Prem
 * @version CS2030S AY22/23 Semester 2
 */
public class Shop {
    private ShopCounter[] counters;

    public Shop(ShopCounter[] counters) {
        this.counters = counters;
    }

    public ShopCounter getAvailableCounter() {
        for (int i = 0; i < this.counters.length; i++) {
            if (this.counters[i].isAvailable())
                return counters[i];
        }
        return null;
    }
}
