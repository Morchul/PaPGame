package com.morchul.action;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.Creatures;

public class SimpleAction implements Action{

    private String action;
    private ActionExecutor executor;

    public SimpleAction(String action) {
        this.action = action;
        executor = new ActionExecutor();
    }

    @Override
    public void action(Anything source, Creatures target) {
        executor.exec(action);
    }

    @Override
    public void reverseAction(Anything source, Creatures target) {
        StringBuilder reverseAction = new StringBuilder();
        String[] commands = action.split(";");
        for(String s : commands){
            reverseAction.append(changeFirstOperator(s)).append(";");
        }
        executor.exec(reverseAction.toString());

    }

    private String changeFirstOperator(String s){
        for(int i = 0; i< s.length(); ++i){
            if(s.charAt(i) == '+') {
                s = s.replaceFirst("\\+","-");
                break;
            }
            if(s.charAt(i) == '-') {
                s = s.replaceFirst("-","\\+");
                break;
            }
            if(s.charAt(i) == '*') {
                s = s.replaceFirst("\\*","/");
                break;
            }
            if(s.charAt(i) == '/') {
                s = s.replaceFirst("/","\\*");
                break;
            }
        }
        return s;
    }

    @Override
    public String getActionText() {
        return action;
    }
}
