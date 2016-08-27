package ru.martsv.voting;

import ru.martsv.voting.matcher.ModelMatcher;
import ru.martsv.voting.model.Menu;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static java.math.BigDecimal.valueOf;
import static ru.martsv.voting.model.BaseEntity.START_SEQ;

/**
 * mart
 * 23.08.2016
 */
public class MenuTestData {

    public static final ModelMatcher<Menu> MATCHER = new ModelMatcher<>(Menu.class);

    public static final int REST1_MENU1_ID = START_SEQ + 8;
    public static final int REST2_MENU1_ID = REST1_MENU1_ID + 4;

    public static final Menu REST1_MENU1 = new Menu(REST1_MENU1_ID, of(2016, Month.AUGUST, 21), "Севиче из тунца", valueOf(750));
    public static final Menu REST1_MENU2 = new Menu(REST1_MENU1_ID + 1, of(2016, Month.AUGUST, 21), "Сырное ассорти", valueOf(990));
    public static final Menu REST1_MENU3 = new Menu(REST1_MENU1_ID + 2, of(2016, Month.AUGUST, 20), "Бефстроганов", valueOf(990));
    public static final Menu REST1_MENU4 = new Menu(REST1_MENU1_ID + 3, of(2016, Month.AUGUST, 20), "Помидоры с красным луком", valueOf(650));
    public static final Menu REST2_MENU1 = new Menu(REST2_MENU1_ID, of(2016, Month.AUGUST, 21), "Курица Чкмерули в чесночном соусе", valueOf(490));
    public static final Menu REST2_MENU2 = new Menu(REST2_MENU1_ID + 1, of(2016, Month.AUGUST, 21), "Суп из мацони с зеленью и яйцом", valueOf(230));
    public static final Menu REST2_MENU3 = new Menu(REST2_MENU1_ID + 2, of(2016, Month.AUGUST, 20), "Крылья индейки в сладком ткемали", valueOf(490));
    public static final Menu REST2_MENU4 = new Menu(REST2_MENU1_ID + 3, of(2016, Month.AUGUST, 20), "Чахохбили со сладким перцем", valueOf(430));

    public static final List<Menu> REST1_MENU = Arrays.asList(REST1_MENU1, REST1_MENU2, REST1_MENU3, REST1_MENU4);

    public static Menu getCreated() {
        return new Menu(null, of(2016, Month.SEPTEMBER, 1), "Новое блюдо", valueOf(350));
    }

    public static Menu getUpdated() {
        return new Menu(REST1_MENU1_ID, REST1_MENU1.getDate(), "Обновленное блюдо", valueOf(490));
    }
}
