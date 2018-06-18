package com.morchul.action;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public class SimpleAction implements Action {

    private String action;
    private ActionExecutor executor;

    public SimpleAction(String action) {
        this.action = action;
        executor = new ActionExecutor();
    }

    @Override
    public boolean action(Anything source, Creatures target) {
        if(!target.isDead())
            return executor.exec(action, source, target);
        return true;
    }

    @Override
    public boolean reverseAction(Anything source, Creatures target) {
        if(!target.isDead())
            return executor.exec(getReverseActionText(), source, target);
        return true;
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

    @Override
    public String getReverseActionText() {
        StringBuilder reverseAction = new StringBuilder();
        String[] commands = action.split(";");
        for(String s : commands){
            reverseAction.append(changeFirstOperator(s)).append(";");
        }
        return reverseAction.toString();
    }

    @Override
    public Anything getSource() {
        return executor.getSource();
    }

    @Override
    public Creatures getTarget() {
        return executor.getTarget();
    }
}
