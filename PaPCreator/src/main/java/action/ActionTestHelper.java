package action;


import com.morchul.action.Action;
import com.morchul.action.SimpleAction;
import com.morchul.model.SimpleValue;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.SimpleCreatures;
import com.morchul.model.models.Weapon;
import model.Value;

import java.util.List;

public class ActionTestHelper {

    private static Creatures c = new SimpleCreatures("","","","","","",new Type(Type.MONSTER),20,50,15,50,5,5,5,5,false);

    public static boolean testAction(String action, List<Value> values){
        Action a = new SimpleAction(action);

        Weapon w = new Weapon("","","", "","", "", new Type(Type.WEAPON),false, false,a, 50);
        for(Value v : values){
            w.addValue(new SimpleValue(v.name.get(),Integer.parseInt(v.value.get())));
        }

        boolean result = a.action(w,c);

        //DEBUG
//        System.out.println(c.getHp() + "/" +
//                c.getMaxHp() + "\n" +
//                c.getMp() + "/" +
//                c.getMaxMp());

        return result;
    }
}
