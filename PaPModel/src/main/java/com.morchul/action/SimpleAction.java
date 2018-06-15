package com.morchul.action;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public class SimpleAction implements Action{

    private String action;
    private ActionExecutor executor;

    public SimpleAction(String action) {
        this.action = action;
        executor = new ActionExecutor();
    }

    @Override
    public boolean action(Anything source, Creatures target) {
        return executor.exec(action, source, target);
    }

    @Override
    public boolean reverseAction(Anything source, Creatures target) {
        StringBuilder reverseAction = new StringBuilder();
        String[] commands = action.split(";");
        for(String s : commands){
            reverseAction.append(changeFirstOperator(s)).append(";");
        }
        return executor.exec(reverseAction.toString(), source, target);

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
