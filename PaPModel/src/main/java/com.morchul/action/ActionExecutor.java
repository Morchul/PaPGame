package com.morchul.action;


import com.morchul.model.Value;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.Random;

import static com.morchul.model.abstractmodels.Creatures.*;

public class ActionExecutor {

    private Anything source;
    private Creatures target;

    public ActionExecutor() { }

    public void exec(String action, Anything source, Creatures target){
        this.source = source;
        this.target = target;

        String[] commands = action.split(";");
        try {
            for (String command : commands) {
                execCommand(command);
            }
        }catch (Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    private void execCommand(String action) throws Exception {
        action = action.replaceAll("\\s+","");
        String[] splitet = action.split("=");
        String t = splitet[0];
        String a = splitet[1];

        int actionValue = getValue(a);
        setValue(t, actionValue);
    }

    private void setValue(String target, int value) throws Exception {
        String[] t = target.split(".");
        String type = t[0];
        String tValue = t[1];

        if(type.equals("target")){
            setTargetValue(tValue, value);
        } else if(type.equals("source")){
            setSourceValue(tValue, value);
        } else {
            throw new Exception("ERROR invalid type");
        }
    }

    private void setSourceValue(String valueN, int value) throws Exception{
        String valueName = valueN.substring(0,valueN.length()-1);
        String action = valueN.substring(valueN.length()-1);
        Value v = getSourceValueByName(valueName);
        if(v == null) throw new Exception("ERROR invalid valueName");
        switch (action){
            case "+":
                v.setValue(v.getValue() + value);
                break;
            case "-":
                v.setValue(v.getValue() - value);
                break;
            case "*":
                v.setValue(v.getValue() * value);
                break;
            case "/":
                v.setValue(v.getValue() / value);
                break;
        }
    }

    private Value getSourceValueByName(String name) throws Exception{
        for(Value v : source.getValues()){
            if(v.getName().equals(name))
                return v;
        }
        throw new Exception("Can't find value: " + name);
    }

    private void setTargetValue(String valueN, int value){
        String valueName = valueN.substring(0,valueN.length()-1);
        String action = valueN.substring(valueN.length()-1);
        switch (action){
            case "+":
                switch (valueName){
                    case HP:
                        value = (value + target.getHp() > target.getMaxHp()) ? target.getMaxHp() : value + target.getHp();
                        target.setValue(valueName,value);
                        break;
                    case MAX_HP:
                        target.setValue(valueName, value + target.getMaxHp());
                        break;
                    case MP:
                        value = (value + target.getMp() > target.getMaxMp()) ? target.getMaxMp() : value + target.getMp();
                        target.setValue(valueName, value);
                        break;
                    case MAX_MP:
                        target.setValue(valueName, value + target.getMaxMp());
                        break;
                    case REACTION:
                        target.setValue(valueName,value + target.getReaction());
                        break;
                    case WILL:
                        target.setValue(valueName,value + target.getWill());
                        break;
                    case STRENGTH:
                        target.setValue(valueName,value + target.getStrength());
                        break;
                    case RESISTANCE:
                        target.setValue(valueName,value + target.getResistance());
                        break;
                }
                break;
            case "-":
                switch (valueName){
                    case HP:
                        if(target.getHp() - value <= 0){
                            target.setValue(valueName,0);
                        } else {
                            target.setValue(valueName,target.getHp() - value);
                        }
                        break;
                    case MAX_HP:
                        if(target.getMaxHp() - value <= 0){
                            target.setValue(valueName,1);
                            target.setValue(HP, 1);
                        } else {
                            target.setValue(valueName,target.getMaxHp() - value);
                            if(target.getHp() > target.getMaxHp())
                                target.setValue(HP, target.getMaxHp());
                        }
                        break;
                    case MP:
                        if(target.getMp() - value <= 0){
                            target.setValue(valueName,0);
                        } else {
                            target.setValue(valueName,target.getMp() - value);
                        }
                        break;
                    case MAX_MP:
                        if(target.getMaxMp() - value <= 0){
                            target.setValue(valueName,1);
                            if(target.getMp() > target.getMaxMp())
                                target.setValue(MP,target.getMaxMp());
                        } else {
                            target.setValue(valueName,target.getMaxMp() - value);
                            if(target.getMp() > target.getMaxMp())
                                target.setValue(MP,target.getMaxMp());
                        }
                        break;
                    case REACTION:
                        if(target.getReaction() - value <= 0)
                            target.setValue(valueName,0);
                        else
                            target.setValue(valueName,target.getReaction() - value);
                        break;
                    case WILL:
                        if(target.getWill() - value <= 0)
                            target.setValue(valueName,0);
                        else
                            target.setValue(valueName,target.getWill() - value);
                        break;
                    case STRENGTH:
                        if(target.getStrength() - value <= 0)
                            target.setValue(valueName,0);
                        else
                            target.setValue(valueName,target.getStrength() - value);
                        break;
                    case RESISTANCE:
                        if(target.getResistance() - value <= 0)
                            target.setValue(valueName,0);
                        else
                            target.setValue(valueName,target.getResistance() - value);
                        break;
                }
                break;
            case "*":
                switch (valueName){
                    case HP:
                        value = (value * target.getHp() > target.getMaxHp()) ? target.getMaxHp() : value * target.getHp();
                        target.setValue(valueName,value);
                        break;
                    case MAX_HP:
                        target.setValue(valueName,value * target.getMaxHp());
                        break;
                    case MP:
                        value = (value * target.getMp() > target.getMaxMp()) ? target.getMaxMp() : value * target.getMp();
                        target.setValue(valueName,value);
                        break;
                    case MAX_MP:
                        target.setValue(valueName,value * target.getMaxMp());
                        break;
                    case REACTION:
                        target.setValue(valueName,value * target.getReaction());
                        break;
                    case WILL:
                        target.setValue(valueName,value * target.getWill());
                        break;
                    case STRENGTH:
                        target.setValue(valueName,value * target.getStrength());
                        break;
                    case RESISTANCE:
                        target.setValue(valueName,value * target.getResistance());
                        break;
                }
                break;
            case "/":
                switch (valueName){
                    case HP:
                        target.setValue(valueName,target.getHp() / value);
                        break;
                    case MAX_HP:
                        if(target.getMaxHp() / value <= 0){
                            target.setValue(valueName,1);
                            target.setValue(HP,1);
                        } else {
                            target.setValue(valueName,target.getMaxHp() / value);
                            if(target.getHp() > target.getMaxHp())
                                target.setValue(HP, target.getMaxHp());
                        }
                        break;
                    case MP:
                        target.setValue(valueName,target.getMp() / value);
                        break;
                    case MAX_MP:
                        if(target.getMaxMp() / value <= 0){
                            target.setValue(valueName,1);
                            target.setValue(MP,1);
                        } else {
                            target.setValue(valueName,target.getMaxMp() / value);
                            if(target.getMp() > target.getMaxMp())
                                target.setValue(MP,target.getMaxMp());
                        }
                        break;
                    case REACTION:
                        target.setValue(valueName,target.getReaction() / value);
                        break;
                    case WILL:
                        target.setValue(valueName,target.getWill() / value);
                        break;
                    case STRENGTH:
                        target.setValue(valueName,target.getStrength() / value);
                        break;
                    case RESISTANCE:
                        target.setValue(valueName,target.getResistance() / value);
                        break;
                }
                break;
        }
    }

    private int getValue(String action) throws Exception {
        if(action.charAt(0) != '-' && action.charAt(0) != '+'){
            action = "+" + action;
        }
        action = replaceRandom(action);
        action = replaceValueNames(action);
        return calcValue(action);
    }

    private int calcValue(String action) throws Exception{
        String[] values = action.split("[+\\-*/]");
        int result = 0;
        int counter = 1;
            for (int i = 0; i < action.length(); ++i) {
                switch (action.charAt(i)) {
                    case '+': result += Integer.parseInt(values[counter++]);
                        break;
                    case '-': result -= Integer.parseInt(values[counter++]);
                        break;
                    case '*': result *= Integer.parseInt(values[counter++]);
                        break;
                    case '/': result /= Integer.parseInt(values[counter++]);
                        break;
                }
            }
        return result;
    }

    private String replaceValueNames(String action) throws Exception {
        System.out.println("ReplaceValueNames: " + action);
        String[] values = action.split("[+\\-*/]");
        for(int i = 1; i < values.length; ++i){
            try {
                Integer.parseInt(values[i]);
            } catch (NumberFormatException e){
                System.out.println("Can't parse: " + values[i]);
                Value v = getSourceValueByName(values[i]);
                action = action.replaceAll(values[i], v.getValue()+"");
            }
        }
        return action;
    }

    private String replaceRandom(String action) throws Exception{
            while (action.matches(".*random\\(\\d+,\\d+\\).*")) {
                int i = action.indexOf("random");
                int is = action.indexOf("(", i);
                int ie = action.indexOf(")", i);
                String c = action.substring(is + 1, ie);
                String[] cs = c.split(",");
                int f = Integer.parseInt(cs[0]);
                int l = Integer.parseInt(cs[1]);
                Random random = new Random();
                int re = random.nextInt(l - f + 1) + f;
                action = action.replaceFirst("random\\(\\d+,\\d+\\)", re + "");
            }
        return action;
    }
}
