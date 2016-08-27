package ru.martsv.voting;

import ru.martsv.voting.matcher.ModelMatcher;
import ru.martsv.voting.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static ru.martsv.voting.model.BaseEntity.START_SEQ;

/**
 * mart
 * 20.08.2016
 */
public class RestaurantTestData {

    public static final ModelMatcher<Restaurant> MATCHER = new ModelMatcher<>(Restaurant.class);

    public static final int RESTAURANT1_ID = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Эль Гаучо", "Стейк-хаус, Морепродукты, Аргентинская, Южноамериканская", "Большой Козловский переулок, д.3, корп. 2, Москва 107078, Россия");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Хачапури", "Восточноевропейская", "Украинский бульвар, д. 7, Москва 121059, Россия");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Стейк Хаус Бизон", "Американская, Стейк-хаус, Барбекю, Европейская, Центральноевропейская", "Профсоюзная, 66, Москва 117393, Россия");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Обломов", "Русская, Восточноевропейская, Европейская, Центральноевропейская", "1-й Монетчиковский пер., д. 5., Москва 115054, Россия");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT4, RESTAURANT3, RESTAURANT2, RESTAURANT1);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Restaurant", "Description", "Address");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Restaurant", "Description", "Address");
    }

}
