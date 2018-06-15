package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.morchul.model.abstractmodels.Creatures;

import static com.morchul.ui.StaticUIValues.INSPECTION_VIEW_TEXTFIELD_HEIGHT;
import static com.morchul.ui.StaticUIValues.INSPECTION_VIEW_TEXTFIELD_WIDTH;

public class InspectionViewValues {

    private Table table;
    private Creatures creatures;
    private Skin skin;

    private TextField hp;
    private TextField maxHp;
    private TextField mp;
    private TextField maxMp;
    private TextField reaction;
    private TextField will;
    private TextField strength;
    private TextField resistance;

    public InspectionViewValues(Creatures creatures, Skin skin) {
        this.creatures = creatures;
        this.skin = skin;
        this.table = new Table(skin);
        table.setDebug(false);
        creatures.addListener(this::update);

        hp = new TextField(creatures.getHp()+"", skin);
        hp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        maxHp = new TextField(creatures.getMaxHp()+"", skin);
        maxHp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        mp = new TextField(creatures.getMp()+"", skin);
        mp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        maxMp = new TextField(creatures.getMaxMp()+"", skin);
        maxMp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        reaction = new TextField(creatures.getReaction()+"", skin);
        reaction.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        will = new TextField(creatures.getWill()+"", skin);
        will.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        strength = new TextField(creatures.getStrength()+"", skin);
        strength.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        resistance = new TextField(creatures.getResistance()+"", skin);
        resistance.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        hp.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) { creatures.setHp(Integer.parseInt(hp.getText())); }}});

        maxHp.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) {creatures.setMaxHp(Integer.parseInt(maxHp.getText())); }}});

        mp.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) {creatures.setMp(Integer.parseInt(mp.getText())); }}});

        maxMp.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) {creatures.setMaxMp(Integer.parseInt(maxMp.getText())); }}});

        reaction.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) {creatures.setReaction(Integer.parseInt(reaction.getText())); }}});

        will.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) {creatures.setWill(Integer.parseInt(will.getText())); }}});

        strength.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) {creatures.setStrength(Integer.parseInt(strength.getText())); }}});

        resistance.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if(!focused) {creatures.setResistance(Integer.parseInt(resistance.getText())); }}});

        table.row().height(INSPECTION_VIEW_TEXTFIELD_HEIGHT);
        table.add("Hp:");
        table.add(hp).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);
        table.add("/");
        table.add(maxHp).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);
        table.row().height(INSPECTION_VIEW_TEXTFIELD_HEIGHT);
        table.add("Mp:");
        table.add(mp).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);
        table.add("/");
        table.add(maxMp).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);
        table.row().height(INSPECTION_VIEW_TEXTFIELD_HEIGHT);
        table.add("Reaction:");
        table.add(reaction).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);
        table.row().height(INSPECTION_VIEW_TEXTFIELD_HEIGHT);
        table.add("Will:");
        table.add(will).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);
        table.row().height(INSPECTION_VIEW_TEXTFIELD_HEIGHT);
        table.add("Strength:");
        table.add(strength).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);
        table.row().height(INSPECTION_VIEW_TEXTFIELD_HEIGHT);
        table.add("Resistance");
        table.add(resistance).width(INSPECTION_VIEW_TEXTFIELD_WIDTH);

    }

    public Table getTable(){
        return table;
    }

    public void update(){
       hp.setText(creatures.getHp()+"");
       maxHp.setText(creatures.getMaxHp()+"");
       mp.setText(creatures.getMp()+"");
       maxMp.setText(creatures.getMaxMp()+"");
       reaction.setText(creatures.getReaction()+"");
       will.setText(creatures.getWill()+"");
       strength.setText(creatures.getStrength()+"");
       resistance.setText(creatures.getResistance()+"");
    }
}
